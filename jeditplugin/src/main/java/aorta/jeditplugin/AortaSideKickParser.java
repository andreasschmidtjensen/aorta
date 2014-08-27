package aorta.jeditplugin;

import aorta.AortaAgent;
import aorta.kr.language.MetaLanguage;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.reasoning.ActionRule;

import org.gjt.sp.jedit.Buffer;

import sidekick.SideKickParsedData;
import errorlist.DefaultErrorSource;
import errorlist.ErrorSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.FailedPredicateException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class AortaSideKickParser extends sidekick.SideKickParser {

	public static final String ID = "aorta";

	SideKickParsedData pd = null;

    static Set<AortaIDE> aortaPluginInstance = new HashSet<AortaIDE>();
    
	public AortaSideKickParser() {
		super(ID);
	}

    public static void addPluginInstance(AortaIDE p) {
        aortaPluginInstance.add(p);
    }
    
	@Override
	public SideKickParsedData parse(Buffer buf, DefaultErrorSource errorSource) {
		String text;
		try {
			buf.readLock();
			text = buf.getText(0, buf.getLength());
		} finally {
			buf.readUnlock();
		}

		AORTAParser parser = null;
		try {
			parser = new AORTAParser(null);
			AORTALexer lexer = new AORTALexer(null);

			ANTLRInputStream input = new ANTLRInputStream(text);
			lexer.setInputStream(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			tokens.fill();

			parser.setTokenStream(tokens);
			parser.setTrace(false);

			parser.removeErrorListeners();
			parser.setErrorHandler(new BailErrorStrategy());

			// TODO: name missing
			AORTAParser.AortaAgentContext aortaAgentContext = parser.aortaAgent("name missing");
			aortaAgentContext.agent.setBridge(null);
			aortaAgentContext.agent.setOrganizationPath("");

			AortaAgent agent = aortaAgentContext.agent.build("");
			List<ActionRule> rules = agent.getState().getActionRules();
			MetaLanguage ml = new MetaLanguage();

			for (ActionRule rule : rules) {
				if (!ml.inML(rule.getOption())) {
					for (AortaIDE ide : aortaPluginInstance) {
						ide.textArea.append("WARNING: Option " + rule.getOption() + " is not in the meta language.\n");
					}
				}
			}
		
			
			// TODO: Validate file
			
//          create nodes 
//			pd = new SideKickParsedData(buf.getName());
//            for (Plan p: ag.getPL()) {
//                DefaultMutableTreeNode node = new PlanAsset(p, buf).createTreeNode();
//                pd.root.add(node);
//            }          

		} catch (ParseCancellationException ex) {
			if (ex.getCause() instanceof RecognitionException) {
				addError(parser, (RecognitionException) ex.getCause(), errorSource, buf.getPath());
			}
		} catch (Exception e) {
			System.out.println("Error in AORTA SideKick:" + e);
			e.printStackTrace();
		}
		return pd;
	}

	public static void addError(Parser recognizer, RecognitionException ex, DefaultErrorSource errorSource, String path) {
		if (ex.getOffendingToken() != null && errorSource != null) {
			int line = ex.getOffendingToken().getLine();
			if (line < 0) {
				line = 0;
			}

			String message = ex.toString();
			if (ex instanceof NoViableAltException) {
				NoViableAltException nva = (NoViableAltException) ex;
				TokenStream tokens = recognizer.getInputStream();
				String input;
				if (tokens != null) {
					if (nva.getStartToken().getType() == Token.EOF) {
						input = "<EOF>";
					} else {
						input = tokens.getText(nva.getStartToken(), nva.getOffendingToken());
					}
				} else {
					input = "<unknown input>";
				}
				message = "no viable alternative at input " + escapeWSAndQuote(input);
//			} else if (ex instanceof LexerNoViableAltException) {
			} else if (ex instanceof FailedPredicateException) {
				FailedPredicateException fpe = (FailedPredicateException) ex;
				message = "mismatched input " + getTokenErrorDisplay(fpe.getOffendingToken()) + " expecting " + fpe.getExpectedTokens().toString(recognizer.getTokenNames());
			} else if (ex instanceof InputMismatchException) {
				InputMismatchException ime = (InputMismatchException) ex;
				String ruleName = recognizer.getRuleNames()[recognizer.getContext().getRuleIndex()];
				message = "rule " + ruleName + " " + ime.getMessage();
			}

			errorSource.addError(new DefaultErrorSource.DefaultError(
					errorSource,
					ErrorSource.ERROR,
					path,
					line, 0, 0,
					message));
		}
	}

	protected static String getTokenErrorDisplay(Token t) {
		if (t == null) {
			return "<no token>";
		}
		String s = t.getText();
		if (s == null) {
			if (t.getType() == Token.EOF) {
				s = "<EOF>";
			} else {
				s = "<" + t.getType() + ">";
			}
		}
		return escapeWSAndQuote(s);
	}

	protected static String escapeWSAndQuote(String s) {
//		if ( s==null ) return s;
		s = s.replace("\n", "\\n");
		s = s.replace("\r", "\\r");
		s = s.replace("\t", "\\t");
		return "'" + s + "'";
	}

	public String toString() {
		return ID;
	}

}
