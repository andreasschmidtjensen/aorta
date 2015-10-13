package aorta.jeditplugin;

import aorta.kr.language.model.Dependency;
import aorta.kr.language.model.Metamodel;
import aorta.kr.language.model.Norm;
import aorta.kr.language.model.Objective;
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
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import sidekick.Asset;

public class AortaMetamodelSideKickParser extends sidekick.SideKickParser {

	public static final String ID = "aorta_metamodel";

	SideKickParsedData pd = null;

	public AortaMetamodelSideKickParser() {
		super(ID);
	}

	@Override
	public SideKickParsedData parse(final Buffer buf, final DefaultErrorSource errorSource) {
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
			parser.addErrorListener(new BaseErrorListener(){ 
				@Override
				public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
					Token token = (Token) offendingSymbol;
					errorSource.addError(
							new DefaultErrorSource.DefaultError(
									errorSource, 
									ErrorSource.ERROR, 
									buf.getPath(), 
									token.getLine() > 0 ? token.getLine() - 1 : 0, 
									token.getStartIndex(), 
									0, 
									msg));
				}
			});


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
			if (!mm.getNorms().isEmpty()) {
				DefaultMutableTreeNode obligationsNode = new DefaultMutableTreeNode("NORMS", true);
				pd.root.add(obligationsNode);
				for (Norm obl : mm.getNorms()) {
					obligationsNode.add(new DefaultMutableTreeNode(obl.toProlog(), false));
				}
			}
			
			if (!mm.getRules().isEmpty()) {
				DefaultMutableTreeNode rulesNode = new DefaultMutableTreeNode("RULES", true);
				pd.root.add(rulesNode);
				for (Rule rule : mm.getRules()) {
					if (rule != null) {
						rulesNode.add(new DefaultMutableTreeNode(rule.toString(), false));
					}
				}
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

	public String toString() {
		return ID;
	}

}
