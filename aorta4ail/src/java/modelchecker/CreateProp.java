/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelchecker;

import ail.mas.MAS;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ail.syntax.Unifier;
import ail.syntax.VarTerm;
import ajpf.MCAPLAgent;
import ajpf.MCAPLLanguageAgent;
import ajpf.psl.MCAPLAgBelief;
import ajpf.psl.MCAPLOrgBelief;
import ajpf.psl.MCAPLOrgOption;
import ajpf.psl.MCAPLProperty;
import ajpf.psl.Until;
import ajpf.psl.ast.Property_AST;
import ajpf.psl.buchi.BuchiAutomaton;
import ajpf.psl.buchi.BuchiState;
import aorta.AortaMASBuilder;
import aorta.syntax.metamodel.ConditionalNorm;
import aorta.syntax.metamodel.Dependency;
import aorta.syntax.metamodel.Metamodel;
import aorta.syntax.metamodel.Norm;
import aorta.syntax.metamodel.Objective;
import aorta.syntax.metamodel.Rea;
import aorta.syntax.metamodel.Role;
import aorta.syntax.metamodel.Violation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author asj
 */
public class CreateProp {
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.out.println("Usage: java modelchecker.CreateProp <aortasetup-file> [Var=term(;Var=term)*]");
			return;
		}
		String masFile = args[0];
		String[] bindings = new String[0];
		if (args.length > 1) {
			bindings = args[1].split(";");
		}
		
		Unifier unif = new Unifier();
		for (String binding : bindings) {
			String[] bind = binding.split("=");
			String var = bind[0];
			String term = bind[1];
			unif.unifyTerms(new Predicate(term), new VarTerm(var));
		}
		
		AortaMASBuilder b = new AortaMASBuilder();
		MAS mas = b.getMAS(masFile);	
		Metamodel mm = b.getModel();
		List<MCAPLAgent> ags = new ArrayList<>();
		for (MCAPLLanguageAgent ag : mas.getMCAPLAgents()) {
			ags.add(new MCAPLAgent(ag, 0, null));
		}
		
		List<MCAPLProperty> result = new ArrayList<>();
		for (Role role : mm.getRoles()) {
			for (MCAPLAgent ag : ags) {
				for (MCAPLAgent ag2 : ags) {
					Rea rea = new Rea(ag2.getAgName(), role.getName());					
					MCAPLProperty prop = new MCAPLOrgBelief(ag, rea.getAsLiteral());					
					result.add(prop);
				}
				Literal opt = new Literal("role");
				opt.addTerm(new Predicate(role.getName()));
				result.add(new MCAPLOrgOption(ag, opt));
			}
		}
		for (Objective obj : mm.getObjectives()) {
			for (MCAPLAgent ag : ags) {
				Literal opt = new Literal("obj");
				Literal objective = obj.getObjective().clone();
				objective.apply(unif);
				
				opt.addTerm(objective);
				
				result.add(new MCAPLAgBelief(ag, objective));
				result.add(new MCAPLOrgOption(ag, opt));
			}
		}
		for (Dependency dep : mm.getDependencies()) {
			for (MCAPLAgent ag : ags) {
				Literal objective = dep.getObjective().clone();
				objective.apply(unif);
				
				Literal optAchieve = new Literal("send");
				optAchieve.addTerm(new Predicate(dep.getDependee()));
				optAchieve.addTerm(new Predicate("achieve"));
				optAchieve.addTerm(new Predicate(objective));
				
				Literal optTell = new Literal("send");
				optTell.addTerm(new Predicate(dep.getDependant()));
				optTell.addTerm(new Predicate("tell"));
				optTell.addTerm(new Predicate(objective));
				
				result.add(new MCAPLOrgOption(ag, optAchieve));
				result.add(new MCAPLOrgOption(ag, optTell));
			}
		}
		for (ConditionalNorm cNorm : mm.getConditionalNorms()) {
			for (MCAPLAgent ag : ags) {				
				for (MCAPLAgent ag2 : ags) {
					Unifier unifier = new Unifier();
					unifier.compose(unif);
					if (cNorm.hasAgentVar()) {
						unifier.unifyTerms(new Predicate(ag2.getAgName()), cNorm.getAgentVar());
					}
					Literal objective = cNorm.getObjective().clone();
					objective.setNegated(true);
					Literal deadline = cNorm.getDeadline().clone();
					deadline.setNegated(true);
					objective.apply(unifier);
					deadline.apply(unifier);

					Norm norm = new Norm(ag2.getAgName(), cNorm.getRole(), cNorm.getDeon(), objective, deadline);
					Violation viol = new Violation(ag2.getAgName(), cNorm.getRole(), cNorm.getDeon(), objective);
					
					result.add(new MCAPLOrgBelief(ag, norm.getAsLiteral()));
					result.add(new MCAPLOrgBelief(ag, viol.getAsLiteral()));
					result.add(new MCAPLOrgOption(ag, viol.getAsLiteral()));
					
					if (ag == ag2) {
						Literal opt = new Literal("norm");
						opt.addTerm(norm.getAsLiteral().getTerm(1));
						opt.addTerm(norm.getAsLiteral().getTerm(2));
						opt.addTerm(norm.getAsLiteral().getTerm(3));
						opt.addTerm(norm.getAsLiteral().getTerm(4));
						result.add(new MCAPLOrgOption(ag, opt));
					}
				}					
			}
		}
		
		System.out.println("Size: " + result.size());
		String property = "";
		for (int i = 0; i < result.size(); i++) {
			if (i > 0) {
				property += (" || ");
			}
			property += (result.get(i));
		}
		// TODO: FIX VARIBLES
		System.out.println(property);
		
		long time = System.currentTimeMillis();
		Property_AST ast = new Property_AST();
		ast.parse(property);
		MCAPLProperty prop = ast.toMCAPLNative();
				
		MCAPLProperty negprop = prop.negate();
		Set<Until> untils = negprop.getUntils();
		BuchiState init = new BuchiState(0, negprop, 1);
		BuchiAutomaton buchi = new BuchiAutomaton();
		buchi.init(init, untils);
		
		System.out.println("Büchi created in " + (System.currentTimeMillis() - time) + "ms");
	}
	
}
