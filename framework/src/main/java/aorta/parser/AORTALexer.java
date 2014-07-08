// Generated from /Users/asj/Dropbox/code/phd/AORTA/Aorta/src/java/aorta/parser/AORTA.g4 by ANTLR 4.1

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
import aorta.reasoning.action.act.*;
import aorta.reasoning.action.coord.*;
import aorta.reasoning.action.opt.*;
import aorta.reasoning.coordination.*;
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
		START_BLOCK=1, END_BLOCK=2, START_BRACKET=3, END_BRACKET=4, EXECUTE=5, 
		INIT_BLOCK=6, ORGANIZATION=7, PATH=8, TYPE=9, ORGANIZATION_TYPE=10, STRATEGY=11, 
		OPT_BLOCK=12, ACT_BLOCK=13, COORDINATION_BLOCK=14, EQUALS=15, START=16, 
		END=17, COMMA=18, COLON=19, SEMICOLON=20, PLUS=21, MINUS=22, NOT=23, OPT=24, 
		BEL=25, GOAL=26, ORG=27, CONSIDER=28, DISREGARD=29, ENACT=30, DEACT=31, 
		COMMIT=32, SEND=33, SENDONCE=34, DROP=35, TRUE=36, ATOM=37, NUMBER=38, 
		VAR=39, UNARY_OP=40, BINARY_OP=41, CLASSNAME=42, FILEPATH=43, WS=44;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'{'", "'}'", "'['", "']'", "'=>'", "'init'", "'organization'", "'path'", 
		"'type'", "ORGANIZATION_TYPE", "'strategy'", "'options'", "'actions'", 
		"'coordination'", "'='", "'('", "')'", "','", "':'", "';'", "'+'", "'-'", 
		"'~'", "'opt'", "'bel'", "'goal'", "'org'", "'consider'", "'disregard'", 
		"'enact'", "'deact'", "'commit'", "'send'", "'sendonce'", "'drop'", "'true'", 
		"ATOM", "NUMBER", "VAR", "'\\+'", "BINARY_OP", "CLASSNAME", "FILEPATH", 
		"WS"
	};
	public static final String[] ruleNames = {
		"START_BLOCK", "END_BLOCK", "START_BRACKET", "END_BRACKET", "EXECUTE", 
		"INIT_BLOCK", "ORGANIZATION", "PATH", "TYPE", "ORGANIZATION_TYPE", "STRATEGY", 
		"OPT_BLOCK", "ACT_BLOCK", "COORDINATION_BLOCK", "EQUALS", "START", "END", 
		"COMMA", "COLON", "SEMICOLON", "PLUS", "MINUS", "NOT", "OPT", "BEL", "GOAL", 
		"ORG", "CONSIDER", "DISREGARD", "ENACT", "DEACT", "COMMIT", "SEND", "SENDONCE", 
		"DROP", "TRUE", "ATOM", "NUMBER", "VAR", "UNARY_OP", "BINARY_OP", "CLASSNAME", 
		"FILEPATH", "WS"
	};


	  private boolean init = false;


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
		case 5: INIT_BLOCK_action((RuleContext)_localctx, actionIndex); break;

		case 41: CLASSNAME_action((RuleContext)_localctx, actionIndex); break;

		case 43: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void INIT_BLOCK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: init = true; break;
		}
	}
	private void CLASSNAME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: init = false; break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 38: return VAR_sempred((RuleContext)_localctx, predIndex);

		case 41: return CLASSNAME_sempred((RuleContext)_localctx, predIndex);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2.\u0172\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u0091\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3"+
		"\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3"+
		"%\3&\3&\7&\u011c\n&\f&\16&\u011f\13&\3\'\5\'\u0122\n\'\3\'\6\'\u0125\n"+
		"\'\r\'\16\'\u0126\3(\3(\3(\7(\u012c\n(\f(\16(\u012f\13(\3)\3)\3)\3*\3"+
		"*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u014a"+
		"\n*\3+\3+\3+\7+\u014f\n+\f+\16+\u0152\13+\3+\7+\u0155\n+\f+\16+\u0158"+
		"\13+\3+\3+\7+\u015c\n+\f+\16+\u015f\13+\3+\3+\3,\3,\7,\u0165\n,\f,\16"+
		",\u0168\13,\3,\3,\3-\6-\u016d\n-\r-\16-\u016e\3-\3-\2.\3\3\1\5\4\1\7\5"+
		"\1\t\6\1\13\7\1\r\b\2\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61"+
		"\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1"+
		"K\'\1M(\1O)\1Q*\1S+\1U,\3W-\1Y.\4\3\2\n\3\2c|\6\2\62;C\\aac|\3\2\62;\4"+
		"\2C\\aa\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\f\f\17\17$$\5\2\13\f\17\17\""+
		"\"\u0184\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3\2\2\2\5]\3\2\2\2\7_\3\2"+
		"\2\2\ta\3\2\2\2\13c\3\2\2\2\rf\3\2\2\2\17m\3\2\2\2\21z\3\2\2\2\23\177"+
		"\3\2\2\2\25\u0090\3\2\2\2\27\u0092\3\2\2\2\31\u009b\3\2\2\2\33\u00a3\3"+
		"\2\2\2\35\u00ab\3\2\2\2\37\u00b8\3\2\2\2!\u00ba\3\2\2\2#\u00bc\3\2\2\2"+
		"%\u00be\3\2\2\2\'\u00c0\3\2\2\2)\u00c2\3\2\2\2+\u00c4\3\2\2\2-\u00c6\3"+
		"\2\2\2/\u00c8\3\2\2\2\61\u00ca\3\2\2\2\63\u00ce\3\2\2\2\65\u00d2\3\2\2"+
		"\2\67\u00d7\3\2\2\29\u00db\3\2\2\2;\u00e4\3\2\2\2=\u00ee\3\2\2\2?\u00f4"+
		"\3\2\2\2A\u00fa\3\2\2\2C\u0101\3\2\2\2E\u0106\3\2\2\2G\u010f\3\2\2\2I"+
		"\u0114\3\2\2\2K\u0119\3\2\2\2M\u0121\3\2\2\2O\u0128\3\2\2\2Q\u0130\3\2"+
		"\2\2S\u0149\3\2\2\2U\u014b\3\2\2\2W\u0162\3\2\2\2Y\u016c\3\2\2\2[\\\7"+
		"}\2\2\\\4\3\2\2\2]^\7\177\2\2^\6\3\2\2\2_`\7]\2\2`\b\3\2\2\2ab\7_\2\2"+
		"b\n\3\2\2\2cd\7?\2\2de\7@\2\2e\f\3\2\2\2fg\7k\2\2gh\7p\2\2hi\7k\2\2ij"+
		"\7v\2\2jk\3\2\2\2kl\b\7\2\2l\16\3\2\2\2mn\7q\2\2no\7t\2\2op\7i\2\2pq\7"+
		"c\2\2qr\7p\2\2rs\7k\2\2st\7|\2\2tu\7c\2\2uv\7v\2\2vw\7k\2\2wx\7q\2\2x"+
		"y\7p\2\2y\20\3\2\2\2z{\7r\2\2{|\7c\2\2|}\7v\2\2}~\7j\2\2~\22\3\2\2\2\177"+
		"\u0080\7v\2\2\u0080\u0081\7{\2\2\u0081\u0082\7r\2\2\u0082\u0083\7g\2\2"+
		"\u0083\24\3\2\2\2\u0084\u0085\7i\2\2\u0085\u0086\7g\2\2\u0086\u0087\7"+
		"p\2\2\u0087\u0088\7g\2\2\u0088\u0089\7t\2\2\u0089\u008a\7k\2\2\u008a\u0091"+
		"\7e\2\2\u008b\u008c\7q\2\2\u008c\u008d\7r\2\2\u008d\u008e\7g\2\2\u008e"+
		"\u008f\7t\2\2\u008f\u0091\7c\2\2\u0090\u0084\3\2\2\2\u0090\u008b\3\2\2"+
		"\2\u0091\26\3\2\2\2\u0092\u0093\7u\2\2\u0093\u0094\7v\2\2\u0094\u0095"+
		"\7t\2\2\u0095\u0096\7c\2\2\u0096\u0097\7v\2\2\u0097\u0098\7g\2\2\u0098"+
		"\u0099\7i\2\2\u0099\u009a\7{\2\2\u009a\30\3\2\2\2\u009b\u009c\7q\2\2\u009c"+
		"\u009d\7r\2\2\u009d\u009e\7v\2\2\u009e\u009f\7k\2\2\u009f\u00a0\7q\2\2"+
		"\u00a0\u00a1\7p\2\2\u00a1\u00a2\7u\2\2\u00a2\32\3\2\2\2\u00a3\u00a4\7"+
		"c\2\2\u00a4\u00a5\7e\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8"+
		"\7q\2\2\u00a8\u00a9\7p\2\2\u00a9\u00aa\7u\2\2\u00aa\34\3\2\2\2\u00ab\u00ac"+
		"\7e\2\2\u00ac\u00ad\7q\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7t\2\2\u00af"+
		"\u00b0\7f\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7c\2\2"+
		"\u00b3\u00b4\7v\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7"+
		"\7p\2\2\u00b7\36\3\2\2\2\u00b8\u00b9\7?\2\2\u00b9 \3\2\2\2\u00ba\u00bb"+
		"\7*\2\2\u00bb\"\3\2\2\2\u00bc\u00bd\7+\2\2\u00bd$\3\2\2\2\u00be\u00bf"+
		"\7.\2\2\u00bf&\3\2\2\2\u00c0\u00c1\7<\2\2\u00c1(\3\2\2\2\u00c2\u00c3\7"+
		"=\2\2\u00c3*\3\2\2\2\u00c4\u00c5\7-\2\2\u00c5,\3\2\2\2\u00c6\u00c7\7/"+
		"\2\2\u00c7.\3\2\2\2\u00c8\u00c9\7\u0080\2\2\u00c9\60\3\2\2\2\u00ca\u00cb"+
		"\7q\2\2\u00cb\u00cc\7r\2\2\u00cc\u00cd\7v\2\2\u00cd\62\3\2\2\2\u00ce\u00cf"+
		"\7d\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7n\2\2\u00d1\64\3\2\2\2\u00d2\u00d3"+
		"\7i\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7n\2\2\u00d6"+
		"\66\3\2\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7i\2\2\u00da"+
		"8\3\2\2\2\u00db\u00dc\7e\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de\7p\2\2\u00de"+
		"\u00df\7u\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7f\2\2\u00e1\u00e2\7g\2\2"+
		"\u00e2\u00e3\7t\2\2\u00e3:\3\2\2\2\u00e4\u00e5\7f\2\2\u00e5\u00e6\7k\2"+
		"\2\u00e6\u00e7\7u\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea"+
		"\7i\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7f\2\2\u00ed"+
		"<\3\2\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7c\2\2\u00f1"+
		"\u00f2\7e\2\2\u00f2\u00f3\7v\2\2\u00f3>\3\2\2\2\u00f4\u00f5\7f\2\2\u00f5"+
		"\u00f6\7g\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7e\2\2\u00f8\u00f9\7v\2\2"+
		"\u00f9@\3\2\2\2\u00fa\u00fb\7e\2\2\u00fb\u00fc\7q\2\2\u00fc\u00fd\7o\2"+
		"\2\u00fd\u00fe\7o\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7v\2\2\u0100B\3\2"+
		"\2\2\u0101\u0102\7u\2\2\u0102\u0103\7g\2\2\u0103\u0104\7p\2\2\u0104\u0105"+
		"\7f\2\2\u0105D\3\2\2\2\u0106\u0107\7u\2\2\u0107\u0108\7g\2\2\u0108\u0109"+
		"\7p\2\2\u0109\u010a\7f\2\2\u010a\u010b\7q\2\2\u010b\u010c\7p\2\2\u010c"+
		"\u010d\7e\2\2\u010d\u010e\7g\2\2\u010eF\3\2\2\2\u010f\u0110\7f\2\2\u0110"+
		"\u0111\7t\2\2\u0111\u0112\7q\2\2\u0112\u0113\7r\2\2\u0113H\3\2\2\2\u0114"+
		"\u0115\7v\2\2\u0115\u0116\7t\2\2\u0116\u0117\7w\2\2\u0117\u0118\7g\2\2"+
		"\u0118J\3\2\2\2\u0119\u011d\t\2\2\2\u011a\u011c\t\3\2\2\u011b\u011a\3"+
		"\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"L\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0122\7/\2\2\u0121\u0120\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0125\t\4\2\2\u0124\u0123\3\2"+
		"\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127"+
		"N\3\2\2\2\u0128\u0129\6(\2\2\u0129\u012d\t\5\2\2\u012a\u012c\t\3\2\2\u012b"+
		"\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2"+
		"\2\2\u012eP\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7^\2\2\u0131\u0132"+
		"\7-\2\2\u0132R\3\2\2\2\u0133\u014a\4>@\2\u0134\u0135\7?\2\2\u0135\u0136"+
		"\7\60\2\2\u0136\u014a\7\60\2\2\u0137\u0138\7?\2\2\u0138\u0139\7<\2\2\u0139"+
		"\u014a\7?\2\2\u013a\u013b\7?\2\2\u013b\u014a\7>\2\2\u013c\u013d\7?\2\2"+
		"\u013d\u014a\7?\2\2\u013e\u013f\7?\2\2\u013f\u0140\7^\2\2\u0140\u014a"+
		"\7?\2\2\u0141\u014a\7@\2\2\u0142\u0143\7@\2\2\u0143\u014a\7?\2\2\u0144"+
		"\u0145\7^\2\2\u0145\u014a\7?\2\2\u0146\u0147\7^\2\2\u0147\u0148\7?\2\2"+
		"\u0148\u014a\7?\2\2\u0149\u0133\3\2\2\2\u0149\u0134\3\2\2\2\u0149\u0137"+
		"\3\2\2\2\u0149\u013a\3\2\2\2\u0149\u013c\3\2\2\2\u0149\u013e\3\2\2\2\u0149"+
		"\u0141\3\2\2\2\u0149\u0142\3\2\2\2\u0149\u0144\3\2\2\2\u0149\u0146\3\2"+
		"\2\2\u014aT\3\2\2\2\u014b\u0156\6+\3\2\u014c\u0150\t\6\2\2\u014d\u014f"+
		"\t\7\2\2\u014e\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0155\7\60"+
		"\2\2\u0154\u014c\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015d\t\6"+
		"\2\2\u015a\u015c\t\7\2\2\u015b\u015a\3\2\2\2\u015c\u015f\3\2\2\2\u015d"+
		"\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0160\3\2\2\2\u015f\u015d\3\2"+
		"\2\2\u0160\u0161\b+\3\2\u0161V\3\2\2\2\u0162\u0166\7$\2\2\u0163\u0165"+
		"\n\b\2\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u0166\3\2\2\2\u0169\u016a\7$"+
		"\2\2\u016aX\3\2\2\2\u016b\u016d\t\t\2\2\u016c\u016b\3\2\2\2\u016d\u016e"+
		"\3\2\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0171\b-\4\2\u0171Z\3\2\2\2\16\2\u0090\u011d\u0121\u0126\u012d\u0149"+
		"\u0150\u0156\u015d\u0166\u016e";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}