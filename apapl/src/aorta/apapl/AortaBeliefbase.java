/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.apapl;

import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import aorta.AortaAgent;
import apapl.Logger;
import apapl.Parser;
import apapl.data.Literal;
import apapl.parser.ParseException;
import apapl.program.Beliefbase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asj
 */
public class AortaBeliefbase extends Beliefbase {

	private AortaAgent aortaAgent;
	
	private final List<Struct> initialBels = new ArrayList<>();

	public void setAortaAgent(AortaAgent aortaAgent) {
		this.aortaAgent = aortaAgent;
		
		// Initial beliefs from the apl file will be added 
		// before the AortaAgent has been setup, so we need 
		// to add them when it has been setup
		for (Struct s : initialBels) {
			aortaAgent.getState().getExternalAgent().addBelief(s);
		}
	}

	private void addToAorta(Literal literal) {
		Struct struct = TermConverter.apl2aorta(literal);
		addToAorta(struct);
	}
	
	private void addToAorta(Struct struct) {
		if (aortaAgent != null) {
			aortaAgent.getState().getExternalAgent().addBelief(struct);
		} else {
			initialBels.add(struct);
		}
	}
	
	private void removeFromAorta(Literal literal) {
		Struct struct = TermConverter.apl2aorta(literal);
		aortaAgent.getState().getExternalAgent().removeBelief(struct);
	}
	
	public void assertFromAorta(Literal literal) {
		super.assertBelief(literal);
	}
	
	@Override
	public void assertBelief(Literal literal) {
		super.assertBelief(literal);
		
		if (literal.getSign()) {
			addToAorta(literal);
		} else {
			literal.setSign(true); // otherwise it tries to remove \+ p
			removeFromAorta(literal);
		}
	}

	@Override
	public void assertBelief(String b) throws ParseException, IOException {
		super.assertBelief(b);
		
		for (String s : separateBelief(b)) {
			if (s.endsWith(".")) {
				s = s.substring(0, s.length() - 1);
			}
			addToAorta((Struct) Term.createTerm(s));
		}
	}

	@Override
	public void addFromFile(File beliefsFile, boolean shadow) throws IOException {
		super.addFromFile(beliefsFile, shadow);
		
		// if !shadow, assertBelief is used
		if (shadow) {
			BufferedReader in = new BufferedReader(new FileReader(beliefsFile));
			String belief = "";
			String s = in.readLine();
			while (s != null) {
				belief = belief + s+"\n";
				s = in.readLine();
			}
			addToAorta((Struct) Term.createTerm(Parser.stripComments(belief)));
			in.close();
		}
	}
	
	/**
	 * Converts a string consisting of multiple beliefs (either literals or rules) 
	 * each separated by a dot into a list of strings each corresponding to a 
	 * single belief.
	 * 
	 * @param b the string respresenting the beliefs
	 * @return the list of belief strings
	 */	
	private ArrayList<String> separateBelief(String b)
	{
		ArrayList<String> bs = new ArrayList<String>();
		String t = b;
		int i = t.indexOf(".");
		while (i>0)	{
			String s = t.substring(0,i);
			if (isTermSeparatingDot(s))	{
				s = s.trim();
				while (s.endsWith("\n")) s = s.substring(0,s.length()-1);
				while (s.startsWith("\n")) s = s.substring(1);
				s = s + ".";
				bs.add(s);
				if (t.length()>i) t = t.substring(i+1); else t = "";
				i = t.indexOf(".");
			}
			else i = t.indexOf(".",i+1);
		}
		return bs;
	}	
	
	/**
	 * Checks whether the number of opening parentheses in the string 
	 * equals the number of closing parentheses. Used to check whether
	 * a string preceding a dot is a full term, e.g. in a(b,1.0). only
	 * the string preceded by the second dot is a full term.
	 *  
	 * @return true if the number of opening parentheses equals the number
	 * of closing parentheses, false otherwise.
	 */
	private boolean isTermSeparatingDot(String s)
	{
		int t = 0;
		byte[] bytes = s.getBytes();
		
		for (int i=0; i<bytes.length; i++)
		{
			if (bytes[i]=='(') t++;
			if (bytes[i]==')') t--;
		}
		
		return t==0;
	}
	
	@Override
	public AortaBeliefbase clone() {
		AortaBeliefbase cloned = new AortaBeliefbase();
		cloned.setAortaAgent(aortaAgent);
		
		cloned.setLogger(new Logger());
		
		for (String b :  getBelief().getBeliefsAsStrings()) {
			cloned.getBelief().addPredicate(b);
		}
		
		return cloned;		
	}
}

