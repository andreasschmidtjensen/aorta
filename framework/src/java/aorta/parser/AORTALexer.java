// Generated from C:\Dropbox\code\phd\aorta\framework\src\java\aorta\parser\AORTA.g4 by ANTLR 4.1
package aorta.parser;

import alice.tuprolog.Number;
import alice.tuprolog.Int;
import alice.tuprolog.Struct;
import alice.tuprolog.Prolog;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.Var;

import aorta.kr.*;
import aorta.kr.language.*;
import aorta.parser.helper.*;
import aorta.reasoning.*;
import aorta.reasoning.action.*;
import aorta.reasoning.fml.*;

import java.io.IOException;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AORTALexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, START_BLOCK=2, END_BLOCK=3, START_BRACKET=4, END_BRACKET=5, IF=6, 
		EXECUTE=7, ORGANIZATION=8, PATH=9, TYPE=10, ACT_BLOCK=11, EQUALS=12, START=13, 
		END=14, COMMA=15, COLON=16, SEMICOLON=17, NOT=18, FULLSTOP=19, PIPE=20, 
		ROLE=21, OBJ=22, TELL=23, ACHIEVE=24, OPT=25, BEL=26, GOAL=27, ORG=28, 
		CAP=29, CONSIDER=30, DISREGARD=31, ENACT=32, DEACT=33, COMMIT=34, SEND=35, 
		DROP=36, TRUE=37, ATOM=38, NUMBER=39, VAR=40, MATH_OP=41, BINARY_OP=42, 
		UNARY_OP=43, CLASSNAME=44, FILEPATH=45, COMMENT=46, WS=47;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"' is '", "'{'", "'}'", "'['", "']'", "'if'", "'=>'", "'organization'", 
		"'path'", "'type'", "'actions'", "'='", "'('", "')'", "','", "':'", "';'", 
		"'~'", "'.'", "'|'", "'role'", "'obj'", "'tell'", "'achieve'", "'opt'", 
		"'bel'", "'goal'", "'org'", "'cap'", "'consider'", "'disregard'", "'enact'", 
		"'deact'", "'commit'", "'send'", "'drop'", "'true'", "ATOM", "NUMBER", 
		"VAR", "MATH_OP", "BINARY_OP", "'\\+'", "CLASSNAME", "FILEPATH", "COMMENT", 
		"WS"
	};
	public static final String[] ruleNames = {
		"T__0", "START_BLOCK", "END_BLOCK", "START_BRACKET", "END_BRACKET", "IF", 
		"EXECUTE", "ORGANIZATION", "PATH", "TYPE", "ACT_BLOCK", "EQUALS", "START", 
		"END", "COMMA", "COLON", "SEMICOLON", "NOT", "FULLSTOP", "PIPE", "ROLE", 
		"OBJ", "TELL", "ACHIEVE", "OPT", "BEL", "GOAL", "ORG", "CAP", "CONSIDER", 
		"DISREGARD", "ENACT", "DEACT", "COMMIT", "SEND", "DROP", "TRUE", "ATOM", 
		"NUMBER", "VAR", "MATH_OP", "BINARY_OP", "UNARY_OP", "CLASSNAME", "FILEPATH", 
		"COMMENT", "WS"
	};


	  private boolean init = false;
	  private boolean action = false;


	public AORTALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AORTA.g4"; }

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
		case 6: EXECUTE_action((RuleContext)_localctx, actionIndex); break;

		case 18: FULLSTOP_action((RuleContext)_localctx, actionIndex); break;

		case 43: CLASSNAME_action((RuleContext)_localctx, actionIndex); break;

		case 45: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 46: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: skip();  break;
		}
	}
	private void EXECUTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: action = true; break;
		}
	}
	private void CLASSNAME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: init = false; break;
		}
	}
	private void FULLSTOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: action = false; break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 39: return VAR_sempred((RuleContext)_localctx, predIndex);

		case 43: return CLASSNAME_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean VAR_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return !init;
		}
		return true;
	}
	private boolean CLASSNAME_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return init;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\61\u016d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"+
		"$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\7\'\u010b\n\'\f\'"+
		"\16\'\u010e\13\'\3(\5(\u0111\n(\3(\6(\u0114\n(\r(\16(\u0115\3)\3)\3)\7"+
		")\u011b\n)\f)\16)\u011e\13)\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+"+
		"\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\5+\u0139\n+\3,\3,\3,\3-\3-\3-\7-\u0141"+
		"\n-\f-\16-\u0144\13-\3-\7-\u0147\n-\f-\16-\u014a\13-\3-\3-\7-\u014e\n"+
		"-\f-\16-\u0151\13-\3-\3-\3.\3.\7.\u0157\n.\f.\16.\u015a\13.\3.\3.\3/\3"+
		"/\7/\u0160\n/\f/\16/\u0163\13/\3/\3/\3\60\6\60\u0168\n\60\r\60\16\60\u0169"+
		"\3\60\3\60\2\61\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\2\21\n\1\23"+
		"\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1"+
		"\'\25\3)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1"+
		";\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\4["+
		"/\1]\60\5_\61\6\3\2\f\3\2c|\6\2\62;C\\aac|\3\2\62;\4\2C\\aa\5\2,-//\61"+
		"\61\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\f\f\17\17$$\4\2\f\f\17\17\5\2\13"+
		"\f\17\17\"\"\u0180\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\3a\3\2\2\2\5f\3\2\2\2\7h\3\2\2\2\tj\3\2\2\2\13l\3\2\2\2\r"+
		"n\3\2\2\2\17q\3\2\2\2\21v\3\2\2\2\23\u0083\3\2\2\2\25\u0088\3\2\2\2\27"+
		"\u008d\3\2\2\2\31\u0095\3\2\2\2\33\u0097\3\2\2\2\35\u0099\3\2\2\2\37\u009b"+
		"\3\2\2\2!\u009d\3\2\2\2#\u009f\3\2\2\2%\u00a1\3\2\2\2\'\u00a3\3\2\2\2"+
		")\u00a6\3\2\2\2+\u00a8\3\2\2\2-\u00ad\3\2\2\2/\u00b1\3\2\2\2\61\u00b6"+
		"\3\2\2\2\63\u00be\3\2\2\2\65\u00c2\3\2\2\2\67\u00c6\3\2\2\29\u00cb\3\2"+
		"\2\2;\u00cf\3\2\2\2=\u00d3\3\2\2\2?\u00dc\3\2\2\2A\u00e6\3\2\2\2C\u00ec"+
		"\3\2\2\2E\u00f2\3\2\2\2G\u00f9\3\2\2\2I\u00fe\3\2\2\2K\u0103\3\2\2\2M"+
		"\u0108\3\2\2\2O\u0110\3\2\2\2Q\u0117\3\2\2\2S\u011f\3\2\2\2U\u0138\3\2"+
		"\2\2W\u013a\3\2\2\2Y\u013d\3\2\2\2[\u0154\3\2\2\2]\u015d\3\2\2\2_\u0167"+
		"\3\2\2\2ab\7\"\2\2bc\7k\2\2cd\7u\2\2de\7\"\2\2e\4\3\2\2\2fg\7}\2\2g\6"+
		"\3\2\2\2hi\7\177\2\2i\b\3\2\2\2jk\7]\2\2k\n\3\2\2\2lm\7_\2\2m\f\3\2\2"+
		"\2no\7k\2\2op\7h\2\2p\16\3\2\2\2qr\7?\2\2rs\7@\2\2st\3\2\2\2tu\b\b\2\2"+
		"u\20\3\2\2\2vw\7q\2\2wx\7t\2\2xy\7i\2\2yz\7c\2\2z{\7p\2\2{|\7k\2\2|}\7"+
		"|\2\2}~\7c\2\2~\177\7v\2\2\177\u0080\7k\2\2\u0080\u0081\7q\2\2\u0081\u0082"+
		"\7p\2\2\u0082\22\3\2\2\2\u0083\u0084\7r\2\2\u0084\u0085\7c\2\2\u0085\u0086"+
		"\7v\2\2\u0086\u0087\7j\2\2\u0087\24\3\2\2\2\u0088\u0089\7v\2\2\u0089\u008a"+
		"\7{\2\2\u008a\u008b\7r\2\2\u008b\u008c\7g\2\2\u008c\26\3\2\2\2\u008d\u008e"+
		"\7c\2\2\u008e\u008f\7e\2\2\u008f\u0090\7v\2\2\u0090\u0091\7k\2\2\u0091"+
		"\u0092\7q\2\2\u0092\u0093\7p\2\2\u0093\u0094\7u\2\2\u0094\30\3\2\2\2\u0095"+
		"\u0096\7?\2\2\u0096\32\3\2\2\2\u0097\u0098\7*\2\2\u0098\34\3\2\2\2\u0099"+
		"\u009a\7+\2\2\u009a\36\3\2\2\2\u009b\u009c\7.\2\2\u009c \3\2\2\2\u009d"+
		"\u009e\7<\2\2\u009e\"\3\2\2\2\u009f\u00a0\7=\2\2\u00a0$\3\2\2\2\u00a1"+
		"\u00a2\7\u0080\2\2\u00a2&\3\2\2\2\u00a3\u00a4\7\60\2\2\u00a4\u00a5\b\24"+
		"\3\2\u00a5(\3\2\2\2\u00a6\u00a7\7~\2\2\u00a7*\3\2\2\2\u00a8\u00a9\7t\2"+
		"\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac,\3\2"+
		"\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7d\2\2\u00af\u00b0\7l\2\2\u00b0.\3"+
		"\2\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7n\2\2\u00b4"+
		"\u00b5\7n\2\2\u00b5\60\3\2\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7e\2\2\u00b8"+
		"\u00b9\7j\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7x\2\2"+
		"\u00bc\u00bd\7g\2\2\u00bd\62\3\2\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7"+
		"r\2\2\u00c0\u00c1\7v\2\2\u00c1\64\3\2\2\2\u00c2\u00c3\7d\2\2\u00c3\u00c4"+
		"\7g\2\2\u00c4\u00c5\7n\2\2\u00c5\66\3\2\2\2\u00c6\u00c7\7i\2\2\u00c7\u00c8"+
		"\7q\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2\2\u00ca8\3\2\2\2\u00cb\u00cc"+
		"\7q\2\2\u00cc\u00cd\7t\2\2\u00cd\u00ce\7i\2\2\u00ce:\3\2\2\2\u00cf\u00d0"+
		"\7e\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7r\2\2\u00d2<\3\2\2\2\u00d3\u00d4"+
		"\7e\2\2\u00d4\u00d5\7q\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7u\2\2\u00d7"+
		"\u00d8\7k\2\2\u00d8\u00d9\7f\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7t\2\2"+
		"\u00db>\3\2\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7u\2"+
		"\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7i\2\2\u00e2\u00e3"+
		"\7c\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7f\2\2\u00e5@\3\2\2\2\u00e6\u00e7"+
		"\7g\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7c\2\2\u00e9\u00ea\7e\2\2\u00ea"+
		"\u00eb\7v\2\2\u00ebB\3\2\2\2\u00ec\u00ed\7f\2\2\u00ed\u00ee\7g\2\2\u00ee"+
		"\u00ef\7c\2\2\u00ef\u00f0\7e\2\2\u00f0\u00f1\7v\2\2\u00f1D\3\2\2\2\u00f2"+
		"\u00f3\7e\2\2\u00f3\u00f4\7q\2\2\u00f4\u00f5\7o\2\2\u00f5\u00f6\7o\2\2"+
		"\u00f6\u00f7\7k\2\2\u00f7\u00f8\7v\2\2\u00f8F\3\2\2\2\u00f9\u00fa\7u\2"+
		"\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7f\2\2\u00fdH\3\2"+
		"\2\2\u00fe\u00ff\7f\2\2\u00ff\u0100\7t\2\2\u0100\u0101\7q\2\2\u0101\u0102"+
		"\7r\2\2\u0102J\3\2\2\2\u0103\u0104\7v\2\2\u0104\u0105\7t\2\2\u0105\u0106"+
		"\7w\2\2\u0106\u0107\7g\2\2\u0107L\3\2\2\2\u0108\u010c\t\2\2\2\u0109\u010b"+
		"\t\3\2\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010dN\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0111\7/\2\2\u0110"+
		"\u010f\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0114\t\4"+
		"\2\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116P\3\2\2\2\u0117\u0118\6)\2\2\u0118\u011c\t\5\2\2\u0119"+
		"\u011b\t\3\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2"+
		"\2\2\u011c\u011d\3\2\2\2\u011dR\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0120"+
		"\t\6\2\2\u0120T\3\2\2\2\u0121\u0139\4>@\2\u0122\u0123\7?\2\2\u0123\u0124"+
		"\7\60\2\2\u0124\u0139\7\60\2\2\u0125\u0126\7?\2\2\u0126\u0127\7<\2\2\u0127"+
		"\u0139\7?\2\2\u0128\u0129\7?\2\2\u0129\u0139\7>\2\2\u012a\u012b\7?\2\2"+
		"\u012b\u0139\7?\2\2\u012c\u012d\7?\2\2\u012d\u012e\7^\2\2\u012e\u0139"+
		"\7?\2\2\u012f\u0139\7@\2\2\u0130\u0131\7@\2\2\u0131\u0139\7?\2\2\u0132"+
		"\u0133\7^\2\2\u0133\u0139\7?\2\2\u0134\u0135\7^\2\2\u0135\u0136\7?\2\2"+
		"\u0136\u0139\7?\2\2\u0137\u0139\t\6\2\2\u0138\u0121\3\2\2\2\u0138\u0122"+
		"\3\2\2\2\u0138\u0125\3\2\2\2\u0138\u0128\3\2\2\2\u0138\u012a\3\2\2\2\u0138"+
		"\u012c\3\2\2\2\u0138\u012f\3\2\2\2\u0138\u0130\3\2\2\2\u0138\u0132\3\2"+
		"\2\2\u0138\u0134\3\2\2\2\u0138\u0137\3\2\2\2\u0139V\3\2\2\2\u013a\u013b"+
		"\7^\2\2\u013b\u013c\7-\2\2\u013cX\3\2\2\2\u013d\u0148\6-\3\2\u013e\u0142"+
		"\t\7\2\2\u013f\u0141\t\b\2\2\u0140\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0142\3\2"+
		"\2\2\u0145\u0147\7\60\2\2\u0146\u013e\3\2\2\2\u0147\u014a\3\2\2\2\u0148"+
		"\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014b\3\2\2\2\u014a\u0148\3\2"+
		"\2\2\u014b\u014f\t\7\2\2\u014c\u014e\t\b\2\2\u014d\u014c\3\2\2\2\u014e"+
		"\u0151\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2"+
		"\2\2\u0151\u014f\3\2\2\2\u0152\u0153\b-\4\2\u0153Z\3\2\2\2\u0154\u0158"+
		"\7$\2\2\u0155\u0157\n\t\2\2\u0156\u0155\3\2\2\2\u0157\u015a\3\2\2\2\u0158"+
		"\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0158\3\2"+
		"\2\2\u015b\u015c\7$\2\2\u015c\\\3\2\2\2\u015d\u0161\7\'\2\2\u015e\u0160"+
		"\n\n\2\2\u015f\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165\b/"+
		"\5\2\u0165^\3\2\2\2\u0166\u0168\t\13\2\2\u0167\u0166\3\2\2\2\u0168\u0169"+
		"\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b"+
		"\u016c\b\60\6\2\u016c`\3\2\2\2\16\2\u010c\u0110\u0115\u011c\u0138\u0142"+
		"\u0148\u014f\u0158\u0161\u0169";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}