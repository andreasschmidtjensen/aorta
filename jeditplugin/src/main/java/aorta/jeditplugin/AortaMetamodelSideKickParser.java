package aorta.jeditplugin;

import aorta.kr.language.model.Dependency;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Objective;
import aorta.kr.language.model.Obligation;
import aorta.kr.language.model.Role;
import aorta.kr.language.model.Rule;
import aorta.kr.language.parser.MetamodelLexer;
import aorta.kr.language.parser.MetamodelParser;

import org.gjt.sp.jedit.Buffer;

import sidekick.SideKickParsedData;
import errorlist.DefaultErrorSource;
import errorlist.ErrorSource;
import javax.swing.Icon;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
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
import sidekick.Asset;

public class AortaMetamodelSideKickParser extends sidekick.SideKickParser {

	public static final String ID = "aorta_metamodel";

	SideKickParsedData pd = null;

	public AortaMetamodelSideKickParser() {
		super(ID);
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

		MetamodelParser parser = null;
		try {
			parser = new MetamodelParser(null);
			MetamodelLexer lexer = new MetamodelLexer(null);

			ANTLRInputStream input = new ANTLRInputStream(text);
			lexer.setInputStream(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			tokens.fill();

			parser.setTokenStream(tokens);
			parser.setTrace(false);

			parser.removeErrorListeners();
			parser.setErrorHandler(new BailErrorStrategy());

			MetamodelParser.MetamodelContext modelContext = parser.metamodel();
			Metamodel mm = modelContext.mm;
			
			// TODO: Validate metamodel 
			
            // create nodes 
			pd = new SideKickParsedData(buf.getName());
			DefaultMutableTreeNode rolesNode = new DefaultMutableTreeNode("ROLES", true);
			pd.root.add(rolesNode);
			for (Role role : mm.getRoles()) {
				rolesNode.add(new DefaultMutableTreeNode(role.toProlog(), false));
			}
			
			DefaultMutableTreeNode objectivesNode = new DefaultMutableTreeNode("OBJECTIVES", true);
			pd.root.add(objectivesNode);
			for (Objective obj : mm.getObjectives()) {
				objectivesNode.add(new DefaultMutableTreeNode(obj.toProlog(), false));
			}
			
			if (!mm.getDependencies().isEmpty()) {
				DefaultMutableTreeNode dependenciesNode = new DefaultMutableTreeNode("DEPENDENCIES", true);
				pd.root.add(dependenciesNode);
				for (Dependency dep : mm.getDependencies()) {
					dependenciesNode.add(new DefaultMutableTreeNode(dep.toProlog(), false));
				}
			}
			if (!mm.getObligations().isEmpty()) {
				DefaultMutableTreeNode obligationsNode = new DefaultMutableTreeNode("OBLIGATIONS", true);
				pd.root.add(obligationsNode);
				for (Obligation obl : mm.getObligations()) {
					obligationsNode.add(new DefaultMutableTreeNode(obl.toProlog(), false));
				}
			}
			
			if (!mm.getRules().isEmpty()) {
				DefaultMutableTreeNode rulesNode = new DefaultMutableTreeNode("RULES", true);
				pd.root.add(rulesNode);
				for (Rule rule : mm.getRules()) {
					rulesNode.add(new DefaultMutableTreeNode(rule.toString(), false));
				}
			}

		} catch (ParseCancellationException ex) {
			if (ex.getCause() instanceof RecognitionException) {
				addError(parser, (RecognitionException) ex.getCause(), errorSource, buf.getPath());
			} else {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Error in Metamodel SideKick:" + e);
			e.printStackTrace();
		}
		return pd;
	}
	
	// TODO: Make tree of MM clickable
	class MMAsset extends Asset {
		
		private String text;
		
		public MMAsset(String text, Buffer buffer) {
			super(text);
//            this.start = toPos(buffer, p.getSrcInfo().getBeginSrcLine());
//            this.end   = toPos(buffer, p.getSrcInfo().getEndSrcLine());
		}

		@Override
		public Icon getIcon() {
			return null;
		}
		
        public String getShortString() {
            return name;
        }

        public String getLongString() {
            return name;
        }

        private Position toPos(Buffer buffer, int line) {
            if ((line - 1) > buffer.getLineCount())
                return buffer.createPosition(buffer.getLength() - 1);
            int offset = buffer.getLineStartOffset(line - 1);
            if (offset >= buffer.getLength()) {
                return buffer.createPosition(buffer.getLength() - 1);
            }
            return buffer.createPosition(offset);
        }

        public DefaultMutableTreeNode createTreeNode() {
            return new DefaultMutableTreeNode(this, true);
        }
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
