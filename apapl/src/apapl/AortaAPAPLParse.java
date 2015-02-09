/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apapl;

import aorta.apapl.AortaAPLModule;
import apapl.beliefinertia.BeliefInertiaParam;
import apapl.data.Tuple;
import apapl.parser.ParseException;
import apapl.parser.ParseModuleException;
import apapl.parser.ParsePrologException;
import apapl.parser.Parser2apl;
import apapl.parser.TokenMgrError;
import com.ugos.JIProlog.engine.JIPSyntaxErrorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 *
 * @author asj
 */
public class AortaAPAPLParse extends Parser {

	// the actual parser as generated by JavaCC
	private Parser2apl parser;
	
	private static String eol = System.getProperty("line.separator", "\n");
	
	/**
	 * Constructs a parser.
	 */
	public AortaAPAPLParse() {
		parser = new Parser2apl(new StringReader(""));
	}
	
	/**
	 * Parses a specification file, can be either a prolog or 2apl file. This method is 
	 * typically used to parse a module specification file. Then it is recursively called 
	 * by the parser for each included prolog/module specification file.  
	 * 
	 * @param file the main specification file
	 * @return the parsed APLModule and a list of files (main + includes) that specify 
	 *         this module
	 * @throws ParseModuleException
	 * @throws ParsePrologException
	 */
	public Tuple<APLModule, LinkedList<File>> parseFile(File file)
	throws ParseModuleException, ParsePrologException
	{
		// Create empty module to be filled by the parser
		AortaAPLModule a = new AortaAPLModule();

		// Create a list of include files, with the first file being the main file
		LinkedList<File> files = new LinkedList<File>();
		files.add(file);

		// The files list is being destructed, keep track of usedFiles
		LinkedList<File> usedFiles = new LinkedList<File>();

		// Obtain path of the main file
		String path = file.getParent() + File.separatorChar;

		if (file.getName().toLowerCase().endsWith(".pl")) 
		{ 
			parsePrologFile(a,file);
		}
		else
		{ 
			try
			{	
				File included = files.removeFirst();
				while (included != null)
				{ 
					File currentFile = new File(path + included.getName());
					if (!usedFiles.contains(currentFile))
					{ 
						usedFiles.add(currentFile);
						if (currentFile.toString().toLowerCase().endsWith(".2apl"))
							// we give the variable <files> along as an argument for
							// parseProgram so that we can store included files in there
							// and later reuse them here
							parseProgram(a, currentFile, files);
						else if (currentFile.toString().toLowerCase().endsWith(".pl"))
							parsePrologFile(a,currentFile);
					}
					else
					{ 
						throw( new ParseModuleException(file, "Cyclic or double file inclusion: " + currentFile) );
					}
					included = files.removeFirst();
				}
			}
			catch (NoSuchElementException e) {}
		}
		
		return( new Tuple<APLModule, LinkedList<File>>(a,usedFiles) );
	}

	/**
	 * Parses the actual module specification file. 
	 * 
	 * @param a the module that this specification file will be compiled to
	 * @param file the input file
	 * @param files files that are included
	 * @throws ParseModuleException
	 */
	private void parseProgram(APLModule a, File file, LinkedList<File> files)
	throws ParseModuleException
	{
		int BELIEFBASE = 0;
		int REST = 1;
		int state = REST;

		String rest = "";
		String belief = "";
		
		BufferedReader in = null;

		try 
		{ 
			in = new BufferedReader(new FileReader(file));
			
			// scan the file, store beliefs (prolog) in <belief>
			// and the rest in <rest>
			String s = in.readLine();
			while (s != null)
			{
				if (s.toLowerCase().trim().startsWith("beliefs")) 
				{
					state = BELIEFBASE;
				}
				else if (isHeading(s)) 
				{
					state = REST;
					rest += s + eol;
				}
				else if (state == REST)
				{
					rest += s + eol;
				}
				else if (state == BELIEFBASE) 
				{
					belief += s + eol;
				}
				s = in.readLine();
			}
			
			// save rest for error parsing			
			String restCode = stripComments(rest);
			
			// we need this so that we have the content in a global variable in javacc. 
			// this is a workaround, to prevent us from editing files which are generated by javacc: we do not want that!
			parser.setContent(restCode);
									
			parser.setBeliefLines(belief.split("\n", -1).length);
			
			parser.ReInit(new StringReader(restCode));
			parser.Program(a,files);
		
			a.getBeliefbase().assertBelief( stripComments(belief) );
						
			// only deliberate over steps that actually contain code
			a.updateDeliberation();
						
			if (BeliefInertiaParam.ENABLED)
			{
				a.setDirectBeliefUpdates(parser.assertBeliefs);
				a.initializeBeliefInertia();
			}
			
			in.close();
		}
		catch( IOException e )
		{ try{ in.close(); } catch (Exception ioe) {}
		  throw( new ParseModuleException( file, e.getMessage() ) );
		}
		catch( ParseException e )
		{ try{ in.close(); } catch (IOException ioe) {}
		  if (e.tokenImage != null) {
			  String errorMsg = prettyPrint(e, rest, belief);
			 
		      throw( new ParseModuleException(file, errorMsg) );
		  }
		  else {
			  // the error has been caught and parsed by our javacc parser: all we do now is print it
			  throw( new ParseModuleException(file, e.getMessage()) );
		  }
		}
		catch( JIPSyntaxErrorException e )
		{ 
			throw( new ParseModuleException(file, "Syntax error in belief base\n" + e.getMessage() ) );
		}
		catch( TokenMgrError e )
		{ 
			throw( new ParseModuleException(file, e.toString()) );
		}
	}
		
	/**
	 * Changes the standard error message of JavaCC so that it shows the line that contains the
	 * error and where on the line the error occurs.
	 * 
	 * @param rest the 2APL code without the beliefs
	 * @param belief the string the belief base of the 2APL code
	 * @param e the parse exception
	 * @return the parsed error message
	 */
	private static String prettyPrint(ParseException e, String rest, String belief) 
	{
		// size of the beliefbase.
		int bbSize = 
			(
				belief.equals("") ? 
					0 :									//force it to be 0 when the beliefbase is empty.
					belief.split(eol, -1).length
			);
		
		// Default error message
		String eMsg = e.getMessage();
				
		// use the first line of the error message and 
		// change the line numbering, because the line numbers don't match since we have taken out the beliefs
		String errorMsg = eMsg.split(eol, -1)[0].substring(0, eMsg.indexOf("at line")) + eol;
	
		// do some fancy parsing with the rest of the error messages: take out all the enters and spaces and replace them with a comma
		errorMsg += eMsg.substring(eMsg.indexOf(eol)).replaceAll(eol, "").replaceAll("[ ][.][.][.][ ][ ][ ][ ]", ", ").replaceAll("[ ][ ][ ]", "");

		// insert the actual line and column number
		int beginLine = e.currentToken.beginLine;
		
		// something awkward: if the first token causes problems, javacc will refer to line 0. we don't want this since line 0 does not exist
		beginLine = (beginLine == 0) ? 1 : beginLine;
		
		// it might happen (mostly at the beginning of a file) that we have no token yet.
		// we want to prevent a runtime error so we check for this
		if (e.currentToken.image == null) 
		{
			errorMsg += eol + eol + "--- no token image found! usually this means there is an error at the first non-comment token of your code.";
		}
		else 
		{
			errorMsg += "after the following code on line " + (beginLine + bbSize) + ", column " + e.currentToken.beginColumn + ":" + eol;
			// add line with error
			
			errorMsg += rest.split(eol, -1)[beginLine-1].trim() + eol;
		
			// pointer that shows where the error occurred
			for (int i=1;i<e.currentToken.beginColumn;i++) errorMsg += " ";
			for (int i=0;i<(e.currentToken.image.length()); i++) errorMsg += "^";
		}
		
		errorMsg += eol;
		
		return errorMsg;
	}
	
	/**
	 * Removes all the comments occurring in a string.
	 * Single-line comments are: % and //
	 * Multi-line comments are: \/* comments here *\/
	 * 
	 * @param s the string to strip
	 * @return the string without comment
	 */
	public static String stripComments(String s)
	{
		String r = "";
		String[] lines = Pattern.compile("$",Pattern.MULTILINE).split(s);
		boolean inBlock = false;
		
		for (int i=0; i<lines.length; i++)
		{ 
			String a = lines[i].trim();
			
			String c = "";
			
			for (int j=0; j<a.length(); j++) 
			{
				if (!inBlock && a.charAt(j)=='/' && j < a.length()-1 && a.charAt(j+1) == '*') 
				{
					inBlock = true;
					j++;
				}
				else if (inBlock && a.charAt(j)=='*' && j< a.length()-1 && a.charAt(j+1)=='/') 
				{
					inBlock = false;
					j++;
				}
				else if (!inBlock) c += a.charAt(j);
			}
			
			String[] patterns = {"//", "%"};
			
			for (int j=0; j < patterns.length; j++) {
				int b = c.indexOf(patterns[j]);
				
				if (b > -1) 
					c = c.substring(0,b);
			}
			
			r += c + "\n";
		}
		
		return r;
	}
	
	/**
	 * -------------------
	 * TODO: discuss parsing of Prolog file with Bas
	 * -------------------
	 * Parses a Prolog file and fills the belief base of the module with the beliefs as
	 * specified by this external prolog file.
	 * 
	 * @param a the module of which the belief base is to be filled
	 * @param file the prolog file to parse
	 * @throws ParsePrologException
	 */
	private void parsePrologFile(APLModule a, File file) throws ParsePrologException
	{
	  try
		{ BufferedReader in = new BufferedReader(new FileReader(file));
		  String belief = "";
		  String s = in.readLine();
		  while (s != null) {
		  	belief = belief + s+"\n";
		  	s = in.readLine();
		  }
		  a.getBeliefbase().assertBelief(stripComments(belief));
		  in.close();
		}
		catch( ParseException e )
		{ throw( new ParsePrologException(file, e ) );
		}
		catch( JIPSyntaxErrorException e )
		{ throw( new ParsePrologException(file, e.getMessage() ) );
		}
		catch( IOException e )
		{ throw( new ParsePrologException(file, e.getMessage() ) );
		}
		catch( TokenMgrError e )
		{ throw( new ParsePrologException(file, e.getMessage()) );
		}
	}
	
	/** 
	 * Determines whether a string corresponds to a keyword indicating a header 
	 * (start of a base such as belief base).
	 * 
	 * @param s the string to check
	 * @return true if s corresponds to a header, false otherwise
	 */
	private boolean isHeading(String s)
	{
		s = s.toLowerCase().trim();
		return	s.startsWith("beliefupdates")
		||		s.startsWith("goals")
		||		s.startsWith("pcrules")
		||		s.startsWith("pgrules")
		||		s.startsWith("prrules")
		||		s.startsWith("plans")
		||		s.startsWith("include")
		||		s.startsWith("end");
	}
}
