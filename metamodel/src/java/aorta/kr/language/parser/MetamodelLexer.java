// Generated from /Users/asj/Dropbox/code/phd/aorta/metamodel/src/java/aorta/kr/language/parser/MetamodelLexer.g4 by ANTLR 4.1
package aorta.kr.language.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MetamodelLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		START_BRACKET=1, END_BRACKET=2, ROLES=3, OBJECTIVES=4, DEPENDENCIES=5, 
		OBLIGATIONS=6, RULES=7, ENTAILS=8, COLON=9, SEMICOLON=10, FULLSTOP=11, 
		PIPE=12, START=13, END=14, COMMA=15, LT=16, GT=17, IS=18, ATOM=19, NUMBER=20, 
		VAR=21, MATH_OP=22, BINARY_OP=23, UNARY_OP=24, OPENSTRING=25, STRING=26, 
		COMMENT=27, WS=28, CLOSESTRING=29, STRLIT=30;
	public static final int STRINGMODE = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "STRINGMODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'['", "']'", "'ROLES'", "'OBJECTIVES'", "'DEPENDENCIES'", "'OBLIGATIONS'", 
		"'RULES'", "':-'", "':'", "';'", "'.'", "'|'", "'('", "')'", "','", "'<'", 
		"GT", "' is '", "ATOM", "NUMBER", "VAR", "MATH_OP", "BINARY_OP", "'\\+'", 
		"OPENSTRING", "STRING", "COMMENT", "WS", "CLOSESTRING", "STRLIT"
	};
	public static final String[] ruleNames = {
		"START_BRACKET", "END_BRACKET", "ROLES", "OBJECTIVES", "DEPENDENCIES", 
		"OBLIGATIONS", "RULES", "ENTAILS", "COLON", "SEMICOLON", "FULLSTOP", "PIPE", 
		"START", "END", "COMMA", "LT", "GT", "IS", "ATOM", "NUMBER", "VAR", "MATH_OP", 
		"BINARY_OP", "UNARY_OP", "OPENSTRING", "STRING", "COMMENT", "WS", "CLOSESTRING", 
		"STRLIT"
	};


		boolean dependencies = false;


	public MetamodelLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MetamodelLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 4: DEPENDENCIES_action((RuleContext)_localctx, actionIndex); break;

		case 5: OBLIGATIONS_action((RuleContext)_localctx, actionIndex); break;

		case 24: OPENSTRING_action((RuleContext)_localctx, actionIndex); break;

		case 26: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 27: WS_action((RuleContext)_localctx, actionIndex); break;

		case 28: CLOSESTRING_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void OBLIGATIONS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: dependencies = false; break;
		}
	}
	private void OPENSTRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: pushMode(STRINGMODE);  break;
		}
	}
	private void CLOSESTRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: popMode();  break;
		}
	}
	private void DEPENDENCIES_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: dependencies = true; break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: skip();  break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 16: return GT_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean GT_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return dependencies;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2 \u00f0\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\7\24\u0096\n\24\f\24\16\24\u0099\13\24\3\25\5\25\u009c\n\25\3\25"+
		"\6\25\u009f\n\25\r\25\16\25\u00a0\3\25\3\25\6\25\u00a5\n\25\r\25\16\25"+
		"\u00a6\5\25\u00a9\n\25\3\26\3\26\7\26\u00ad\n\26\f\26\16\26\u00b0\13\26"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00ca\n\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\33\6\33\u00d4\n\33\r\33\16\33\u00d5\3"+
		"\34\3\34\7\34\u00da\n\34\f\34\16\34\u00dd\13\34\3\34\3\34\3\35\6\35\u00e2"+
		"\n\35\r\35\16\35\u00e3\3\35\3\35\3\36\3\36\3\36\3\36\3\37\6\37\u00ed\n"+
		"\37\r\37\16\37\u00ee\2 \4\3\1\6\4\1\b\5\1\n\6\1\f\7\2\16\b\3\20\t\1\22"+
		"\n\1\24\13\1\26\f\1\30\r\1\32\16\1\34\17\1\36\20\1 \21\1\"\22\1$\23\1"+
		"&\24\1(\25\1*\26\1,\27\1.\30\1\60\31\1\62\32\1\64\33\4\66\34\18\35\5:"+
		"\36\6<\37\7> \1\4\2\3\13\3\2c|\6\2\62;C\\aac|\3\2\62;\4\2C\\aa\5\2,-/"+
		"/\61\61\4\2C\\c|\4\2\f\f\17\17\5\2\13\f\17\17\"\"\3\2$$\u0101\2\4\3\2"+
		"\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20"+
		"\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2"+
		"\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3"+
		"\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3"+
		"\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\3<\3\2\2\2\3>\3"+
		"\2\2\2\4@\3\2\2\2\6B\3\2\2\2\bD\3\2\2\2\nJ\3\2\2\2\fU\3\2\2\2\16d\3\2"+
		"\2\2\20r\3\2\2\2\22x\3\2\2\2\24{\3\2\2\2\26}\3\2\2\2\30\177\3\2\2\2\32"+
		"\u0081\3\2\2\2\34\u0083\3\2\2\2\36\u0085\3\2\2\2 \u0087\3\2\2\2\"\u0089"+
		"\3\2\2\2$\u008b\3\2\2\2&\u008e\3\2\2\2(\u0093\3\2\2\2*\u009b\3\2\2\2,"+
		"\u00aa\3\2\2\2.\u00b1\3\2\2\2\60\u00c9\3\2\2\2\62\u00cb\3\2\2\2\64\u00ce"+
		"\3\2\2\2\66\u00d3\3\2\2\28\u00d7\3\2\2\2:\u00e1\3\2\2\2<\u00e7\3\2\2\2"+
		">\u00ec\3\2\2\2@A\7]\2\2A\5\3\2\2\2BC\7_\2\2C\7\3\2\2\2DE\7T\2\2EF\7Q"+
		"\2\2FG\7N\2\2GH\7G\2\2HI\7U\2\2I\t\3\2\2\2JK\7Q\2\2KL\7D\2\2LM\7L\2\2"+
		"MN\7G\2\2NO\7E\2\2OP\7V\2\2PQ\7K\2\2QR\7X\2\2RS\7G\2\2ST\7U\2\2T\13\3"+
		"\2\2\2UV\7F\2\2VW\7G\2\2WX\7R\2\2XY\7G\2\2YZ\7P\2\2Z[\7F\2\2[\\\7G\2\2"+
		"\\]\7P\2\2]^\7E\2\2^_\7K\2\2_`\7G\2\2`a\7U\2\2ab\3\2\2\2bc\b\6\2\2c\r"+
		"\3\2\2\2de\7Q\2\2ef\7D\2\2fg\7N\2\2gh\7K\2\2hi\7I\2\2ij\7C\2\2jk\7V\2"+
		"\2kl\7K\2\2lm\7Q\2\2mn\7P\2\2no\7U\2\2op\3\2\2\2pq\b\7\3\2q\17\3\2\2\2"+
		"rs\7T\2\2st\7W\2\2tu\7N\2\2uv\7G\2\2vw\7U\2\2w\21\3\2\2\2xy\7<\2\2yz\7"+
		"/\2\2z\23\3\2\2\2{|\7<\2\2|\25\3\2\2\2}~\7=\2\2~\27\3\2\2\2\177\u0080"+
		"\7\60\2\2\u0080\31\3\2\2\2\u0081\u0082\7~\2\2\u0082\33\3\2\2\2\u0083\u0084"+
		"\7*\2\2\u0084\35\3\2\2\2\u0085\u0086\7+\2\2\u0086\37\3\2\2\2\u0087\u0088"+
		"\7.\2\2\u0088!\3\2\2\2\u0089\u008a\7>\2\2\u008a#\3\2\2\2\u008b\u008c\6"+
		"\22\2\2\u008c\u008d\7@\2\2\u008d%\3\2\2\2\u008e\u008f\7\"\2\2\u008f\u0090"+
		"\7k\2\2\u0090\u0091\7u\2\2\u0091\u0092\7\"\2\2\u0092\'\3\2\2\2\u0093\u0097"+
		"\t\2\2\2\u0094\u0096\t\3\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097"+
		"\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098)\3\2\2\2\u0099\u0097\3\2\2\2"+
		"\u009a\u009c\7/\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e"+
		"\3\2\2\2\u009d\u009f\t\4\2\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a8\3\2\2\2\u00a2\u00a4\7\60"+
		"\2\2\u00a3\u00a5\t\4\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a2\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9+\3\2\2\2\u00aa\u00ae\t\5\2\2\u00ab\u00ad"+
		"\t\3\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af-\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\t\6\2\2"+
		"\u00b2/\3\2\2\2\u00b3\u00ca\4>@\2\u00b4\u00b5\7?\2\2\u00b5\u00b6\7\60"+
		"\2\2\u00b6\u00ca\7\60\2\2\u00b7\u00b8\7?\2\2\u00b8\u00b9\7<\2\2\u00b9"+
		"\u00ca\7?\2\2\u00ba\u00bb\7?\2\2\u00bb\u00ca\7>\2\2\u00bc\u00bd\7?\2\2"+
		"\u00bd\u00ca\7?\2\2\u00be\u00bf\7?\2\2\u00bf\u00c0\7^\2\2\u00c0\u00ca"+
		"\7?\2\2\u00c1\u00c2\7@\2\2\u00c2\u00ca\7?\2\2\u00c3\u00c4\7^\2\2\u00c4"+
		"\u00ca\7?\2\2\u00c5\u00c6\7^\2\2\u00c6\u00c7\7?\2\2\u00c7\u00ca\7?\2\2"+
		"\u00c8\u00ca\t\6\2\2\u00c9\u00b3\3\2\2\2\u00c9\u00b4\3\2\2\2\u00c9\u00b7"+
		"\3\2\2\2\u00c9\u00ba\3\2\2\2\u00c9\u00bc\3\2\2\2\u00c9\u00be\3\2\2\2\u00c9"+
		"\u00c1\3\2\2\2\u00c9\u00c3\3\2\2\2\u00c9\u00c5\3\2\2\2\u00c9\u00c8\3\2"+
		"\2\2\u00ca\61\3\2\2\2\u00cb\u00cc\7^\2\2\u00cc\u00cd\7-\2\2\u00cd\63\3"+
		"\2\2\2\u00ce\u00cf\7$\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\32\4\2\u00d1"+
		"\65\3\2\2\2\u00d2\u00d4\t\7\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2"+
		"\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\67\3\2\2\2\u00d7\u00db"+
		"\7\'\2\2\u00d8\u00da\n\b\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2"+
		"\2\2\u00de\u00df\b\34\5\2\u00df9\3\2\2\2\u00e0\u00e2\t\t\2\2\u00e1\u00e0"+
		"\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\u00e6\b\35\6\2\u00e6;\3\2\2\2\u00e7\u00e8\7$\2\2"+
		"\u00e8\u00e9\3\2\2\2\u00e9\u00ea\b\36\7\2\u00ea=\3\2\2\2\u00eb\u00ed\n"+
		"\n\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef?\3\2\2\2\17\2\3\u0097\u009b\u00a0\u00a6\u00a8\u00ae"+
		"\u00c9\u00d5\u00db\u00e3\u00ee";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}