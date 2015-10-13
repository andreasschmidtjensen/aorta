package aorta.jeditplugin;

import aorta.AortaAgent;
import aorta.kr.language.MetaLanguage;
import aorta.parser.AORTALexer;
import aorta.parser.AORTAParser;
import aorta.reasoning.ActionRule;
import aorta.reasoning.ReasoningRule;

import org.gjt.sp.jedit.Buffer;

import sidekick.SideKickParsedData;
import errorlist.DefaultErrorSource;
import errorlist.ErrorSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

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
    public SideKickParsedData parse(final Buffer buf, final DefaultErrorSource errorSource) {
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

            // TODO: name missing
            AORTAParser.AortaAgentContext aortaAgentContext = parser.aortaAgent("name missing");
            aortaAgentContext.agent.setBridge(null);
            aortaAgentContext.agent.setOrganizationPath("");

            AortaAgent agent = aortaAgentContext.agent.build("");
            List<ReasoningRule> rules = agent.getState().getRules();
            MetaLanguage ml = new MetaLanguage();

            for (ReasoningRule r : rules) {
                if (r instanceof ActionRule) {
                    ActionRule rule = (ActionRule) r;
                    if (!ml.inML(rule.getOption())) {
                        for (AortaIDE ide : aortaPluginInstance) {
                            ide.textArea.append("WARNING: Option " + rule.getOption() + " is not in the meta language.\n");
                        }
                    }
                }
            }

			// TODO: Validate file
        } catch (Exception e) {
			for (AortaIDE ide : aortaPluginInstance) {
				ide.textArea.append("Error in AORTA SideKick:" + e);
			}
            e.printStackTrace();
        }
        return pd;
    }

    public String toString() {
        return ID;
    }

}
