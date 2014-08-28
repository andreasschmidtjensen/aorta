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
		T__0=1, START_BLOCK=2, END_BLOCK=3, START_BRACKET=4, END_BRACKET=5, EXECUTE=6, 
		INIT_BLOCK=7, ORGANIZATION=8, PATH=9, TYPE=10, ORGANIZATION_TYPE=11, STRATEGY=12, 
		OPT_BLOCK=13, ACT_BLOCK=14, COORDINATION_BLOCK=15, EQUALS=16, START=17, 
		END=18, COMMA=19, COLON=20, SEMICOLON=21, NOT=22, FULLSTOP=23, PIPE=24, 
		ROLE=25, OBJ=26, TELL=27, ACHIEVE=28, OPT=29, BEL=30, GOAL=31, ORG=32, 
		CONSIDER=33, DISREGARD=34, ENACT=35, DEACT=36, COMMIT=37, SEND=38, DROP=39, 
		TRUE=40, ATOM=41, NUMBER=42, VAR=43, MATH_OP=44, BINARY_OP=45, UNARY_OP=46, 
		CLASSNAME=47, FILEPATH=48, COMMENT=49, WS=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"' is '", "'{'", "'}'", "'['", "']'", "'=>'", "'init'", "'organization'", 
		"'path'", "'type'", "ORGANIZATION_TYPE", "'strategy'", "'options'", "'actions'", 
		"'coordination'", "'='", "'('", "')'", "','", "':'", "';'", "'~'", "'.'", 
		"'|'", "'role'", "'obj'", "'tell'", "'achieve'", "'opt'", "'bel'", "'goal'", 
		"'org'", "'consider'", "'disregard'", "'enact'", "'deact'", "'commit'", 
		"'send'", "'drop'", "'true'", "ATOM", "NUMBER", "VAR", "MATH_OP", "BINARY_OP", 
		"'\\+'", "CLASSNAME", "FILEPATH", "COMMENT", "WS"
	};
	public static final String[] ruleNames = {
		"T__0", "START_BLOCK", "END_BLOCK", "START_BRACKET", "END_BRACKET", "EXECUTE", 
		"INIT_BLOCK", "ORGANIZATION", "PATH", "TYPE", "ORGANIZATION_TYPE", "STRATEGY", 
		"OPT_BLOCK", "ACT_BLOCK", "COORDINATION_BLOCK", "EQUALS", "START", "END", 
		"COMMA", "COLON", "SEMICOLON", "NOT", "FULLSTOP", "PIPE", "ROLE", "OBJ", 
		"TELL", "ACHIEVE", "OPT", "BEL", "GOAL", "ORG", "CONSIDER", "DISREGARD", 
		"ENACT", "DEACT", "COMMIT", "SEND", "DROP", "TRUE", "ATOM", "NUMBER", 
		"VAR", "MATH_OP", "BINARY_OP", "UNARY_OP", "CLASSNAME", "FILEPATH", "COMMENT", 
		"WS"
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
		case 5: EXECUTE_action((RuleContext)_localctx, actionIndex); break;

		case 6: INIT_BLOCK_action((RuleContext)_localctx, actionIndex); break;

		case 22: FULLSTOP_action((RuleContext)_localctx, actionIndex); break;

		case 46: CLASSNAME_action((RuleContext)_localctx, actionIndex); break;

		case 48: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 49: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: skip();  break;
		}
	}
	private void EXECUTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: action = true; break;
		}
	}
	private void INIT_BLOCK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: init = true; break;
		}
	}
	private void CLASSNAME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: init = false; break;
		}
	}
	private void FULLSTOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: action = false; break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 42: return VAR_sempred((RuleContext)_localctx, predIndex);

		case 46: return CLASSNAME_sempred((RuleContext)_localctx, predIndex);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\64\u019f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a4\n\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\7*\u013d\n*\f*\16*"+
		"\u0140\13*\3+\5+\u0143\n+\3+\6+\u0146\n+\r+\16+\u0147\3,\3,\3,\7,\u014d"+
		"\n,\f,\16,\u0150\13,\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3"+
		".\3.\3.\3.\3.\3.\3.\3.\3.\5.\u016b\n.\3/\3/\3/\3\60\3\60\3\60\7\60\u0173"+
		"\n\60\f\60\16\60\u0176\13\60\3\60\7\60\u0179\n\60\f\60\16\60\u017c\13"+
		"\60\3\60\3\60\7\60\u0180\n\60\f\60\16\60\u0183\13\60\3\60\3\60\3\61\3"+
		"\61\7\61\u0189\n\61\f\61\16\61\u018c\13\61\3\61\3\61\3\62\3\62\7\62\u0192"+
		"\n\62\f\62\16\62\u0195\13\62\3\62\3\62\3\63\6\63\u019a\n\63\r\63\16\63"+
		"\u019b\3\63\3\63\2\64\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\2\17\t\3\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%"+
		"\24\1\'\25\1)\26\1+\27\1-\30\1/\31\4\61\32\1\63\33\1\65\34\1\67\35\19"+
		"\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1"+
		"Y.\1[/\1]\60\1_\61\5a\62\1c\63\6e\64\7\3\2\f\3\2c|\6\2\62;C\\aac|\3\2"+
		"\62;\4\2C\\aa\5\2,-//\61\61\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\f\f\17\17"+
		"$$\4\2\f\f\17\17\5\2\13\f\17\17\"\"\u01b3\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2"+
		"\3g\3\2\2\2\5l\3\2\2\2\7n\3\2\2\2\tp\3\2\2\2\13r\3\2\2\2\rt\3\2\2\2\17"+
		"y\3\2\2\2\21\u0080\3\2\2\2\23\u008d\3\2\2\2\25\u0092\3\2\2\2\27\u00a3"+
		"\3\2\2\2\31\u00a5\3\2\2\2\33\u00ae\3\2\2\2\35\u00b6\3\2\2\2\37\u00be\3"+
		"\2\2\2!\u00cb\3\2\2\2#\u00cd\3\2\2\2%\u00cf\3\2\2\2\'\u00d1\3\2\2\2)\u00d3"+
		"\3\2\2\2+\u00d5\3\2\2\2-\u00d7\3\2\2\2/\u00d9\3\2\2\2\61\u00dc\3\2\2\2"+
		"\63\u00de\3\2\2\2\65\u00e3\3\2\2\2\67\u00e7\3\2\2\29\u00ec\3\2\2\2;\u00f4"+
		"\3\2\2\2=\u00f8\3\2\2\2?\u00fc\3\2\2\2A\u0101\3\2\2\2C\u0105\3\2\2\2E"+
		"\u010e\3\2\2\2G\u0118\3\2\2\2I\u011e\3\2\2\2K\u0124\3\2\2\2M\u012b\3\2"+
		"\2\2O\u0130\3\2\2\2Q\u0135\3\2\2\2S\u013a\3\2\2\2U\u0142\3\2\2\2W\u0149"+
		"\3\2\2\2Y\u0151\3\2\2\2[\u016a\3\2\2\2]\u016c\3\2\2\2_\u016f\3\2\2\2a"+
		"\u0186\3\2\2\2c\u018f\3\2\2\2e\u0199\3\2\2\2gh\7\"\2\2hi\7k\2\2ij\7u\2"+
		"\2jk\7\"\2\2k\4\3\2\2\2lm\7}\2\2m\6\3\2\2\2no\7\177\2\2o\b\3\2\2\2pq\7"+
		"]\2\2q\n\3\2\2\2rs\7_\2\2s\f\3\2\2\2tu\7?\2\2uv\7@\2\2vw\3\2\2\2wx\b\7"+
		"\2\2x\16\3\2\2\2yz\7k\2\2z{\7p\2\2{|\7k\2\2|}\7v\2\2}~\3\2\2\2~\177\b"+
		"\b\3\2\177\20\3\2\2\2\u0080\u0081\7q\2\2\u0081\u0082\7t\2\2\u0082\u0083"+
		"\7i\2\2\u0083\u0084\7c\2\2\u0084\u0085\7p\2\2\u0085\u0086\7k\2\2\u0086"+
		"\u0087\7|\2\2\u0087\u0088\7c\2\2\u0088\u0089\7v\2\2\u0089\u008a\7k\2\2"+
		"\u008a\u008b\7q\2\2\u008b\u008c\7p\2\2\u008c\22\3\2\2\2\u008d\u008e\7"+
		"r\2\2\u008e\u008f\7c\2\2\u008f\u0090\7v\2\2\u0090\u0091\7j\2\2\u0091\24"+
		"\3\2\2\2\u0092\u0093\7v\2\2\u0093\u0094\7{\2\2\u0094\u0095\7r\2\2\u0095"+
		"\u0096\7g\2\2\u0096\26\3\2\2\2\u0097\u0098\7i\2\2\u0098\u0099\7g\2\2\u0099"+
		"\u009a\7p\2\2\u009a\u009b\7g\2\2\u009b\u009c\7t\2\2\u009c\u009d\7k\2\2"+
		"\u009d\u00a4\7e\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7r\2\2\u00a0\u00a1"+
		"\7g\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a4\7c\2\2\u00a3\u0097\3\2\2\2\u00a3"+
		"\u009e\3\2\2\2\u00a4\30\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7v\2\2"+
		"\u00a7\u00a8\7t\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab"+
		"\7g\2\2\u00ab\u00ac\7i\2\2\u00ac\u00ad\7{\2\2\u00ad\32\3\2\2\2\u00ae\u00af"+
		"\7q\2\2\u00af\u00b0\7r\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7k\2\2\u00b2"+
		"\u00b3\7q\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7u\2\2\u00b5\34\3\2\2\2\u00b6"+
		"\u00b7\7c\2\2\u00b7\u00b8\7e\2\2\u00b8\u00b9\7v\2\2\u00b9\u00ba\7k\2\2"+
		"\u00ba\u00bb\7q\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7u\2\2\u00bd\36\3\2"+
		"\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2"+
		"\7t\2\2\u00c2\u00c3\7f\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7p\2\2\u00c5"+
		"\u00c6\7c\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7q\2\2"+
		"\u00c9\u00ca\7p\2\2\u00ca \3\2\2\2\u00cb\u00cc\7?\2\2\u00cc\"\3\2\2\2"+
		"\u00cd\u00ce\7*\2\2\u00ce$\3\2\2\2\u00cf\u00d0\7+\2\2\u00d0&\3\2\2\2\u00d1"+
		"\u00d2\7.\2\2\u00d2(\3\2\2\2\u00d3\u00d4\7<\2\2\u00d4*\3\2\2\2\u00d5\u00d6"+
		"\7=\2\2\u00d6,\3\2\2\2\u00d7\u00d8\7\u0080\2\2\u00d8.\3\2\2\2\u00d9\u00da"+
		"\7\60\2\2\u00da\u00db\b\30\4\2\u00db\60\3\2\2\2\u00dc\u00dd\7~\2\2\u00dd"+
		"\62\3\2\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1\7n\2\2\u00e1"+
		"\u00e2\7g\2\2\u00e2\64\3\2\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7d\2\2\u00e5"+
		"\u00e6\7l\2\2\u00e6\66\3\2\2\2\u00e7\u00e8\7v\2\2\u00e8\u00e9\7g\2\2\u00e9"+
		"\u00ea\7n\2\2\u00ea\u00eb\7n\2\2\u00eb8\3\2\2\2\u00ec\u00ed\7c\2\2\u00ed"+
		"\u00ee\7e\2\2\u00ee\u00ef\7j\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7g\2\2"+
		"\u00f1\u00f2\7x\2\2\u00f2\u00f3\7g\2\2\u00f3:\3\2\2\2\u00f4\u00f5\7q\2"+
		"\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7v\2\2\u00f7<\3\2\2\2\u00f8\u00f9\7"+
		"d\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7n\2\2\u00fb>\3\2\2\2\u00fc\u00fd"+
		"\7i\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100\7n\2\2\u0100"+
		"@\3\2\2\2\u0101\u0102\7q\2\2\u0102\u0103\7t\2\2\u0103\u0104\7i\2\2\u0104"+
		"B\3\2\2\2\u0105\u0106\7e\2\2\u0106\u0107\7q\2\2\u0107\u0108\7p\2\2\u0108"+
		"\u0109\7u\2\2\u0109\u010a\7k\2\2\u010a\u010b\7f\2\2\u010b\u010c\7g\2\2"+
		"\u010c\u010d\7t\2\2\u010dD\3\2\2\2\u010e\u010f\7f\2\2\u010f\u0110\7k\2"+
		"\2\u0110\u0111\7u\2\2\u0111\u0112\7t\2\2\u0112\u0113\7g\2\2\u0113\u0114"+
		"\7i\2\2\u0114\u0115\7c\2\2\u0115\u0116\7t\2\2\u0116\u0117\7f\2\2\u0117"+
		"F\3\2\2\2\u0118\u0119\7g\2\2\u0119\u011a\7p\2\2\u011a\u011b\7c\2\2\u011b"+
		"\u011c\7e\2\2\u011c\u011d\7v\2\2\u011dH\3\2\2\2\u011e\u011f\7f\2\2\u011f"+
		"\u0120\7g\2\2\u0120\u0121\7c\2\2\u0121\u0122\7e\2\2\u0122\u0123\7v\2\2"+
		"\u0123J\3\2\2\2\u0124\u0125\7e\2\2\u0125\u0126\7q\2\2\u0126\u0127\7o\2"+
		"\2\u0127\u0128\7o\2\2\u0128\u0129\7k\2\2\u0129\u012a\7v\2\2\u012aL\3\2"+
		"\2\2\u012b\u012c\7u\2\2\u012c\u012d\7g\2\2\u012d\u012e\7p\2\2\u012e\u012f"+
		"\7f\2\2\u012fN\3\2\2\2\u0130\u0131\7f\2\2\u0131\u0132\7t\2\2\u0132\u0133"+
		"\7q\2\2\u0133\u0134\7r\2\2\u0134P\3\2\2\2\u0135\u0136\7v\2\2\u0136\u0137"+
		"\7t\2\2\u0137\u0138\7w\2\2\u0138\u0139\7g\2\2\u0139R\3\2\2\2\u013a\u013e"+
		"\t\2\2\2\u013b\u013d\t\3\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e"+
		"\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013fT\3\2\2\2\u0140\u013e\3\2\2\2"+
		"\u0141\u0143\7/\2\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145"+
		"\3\2\2\2\u0144\u0146\t\4\2\2\u0145\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148V\3\2\2\2\u0149\u014a\6,\2\2\u014a"+
		"\u014e\t\5\2\2\u014b\u014d\t\3\2\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2"+
		"\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014fX\3\2\2\2\u0150\u014e"+
		"\3\2\2\2\u0151\u0152\t\6\2\2\u0152Z\3\2\2\2\u0153\u016b\4>@\2\u0154\u0155"+
		"\7?\2\2\u0155\u0156\7\60\2\2\u0156\u016b\7\60\2\2\u0157\u0158\7?\2\2\u0158"+
		"\u0159\7<\2\2\u0159\u016b\7?\2\2\u015a\u015b\7?\2\2\u015b\u016b\7>\2\2"+
		"\u015c\u015d\7?\2\2\u015d\u016b\7?\2\2\u015e\u015f\7?\2\2\u015f\u0160"+
		"\7^\2\2\u0160\u016b\7?\2\2\u0161\u016b\7@\2\2\u0162\u0163\7@\2\2\u0163"+
		"\u016b\7?\2\2\u0164\u0165\7^\2\2\u0165\u016b\7?\2\2\u0166\u0167\7^\2\2"+
		"\u0167\u0168\7?\2\2\u0168\u016b\7?\2\2\u0169\u016b\t\6\2\2\u016a\u0153"+
		"\3\2\2\2\u016a\u0154\3\2\2\2\u016a\u0157\3\2\2\2\u016a\u015a\3\2\2\2\u016a"+
		"\u015c\3\2\2\2\u016a\u015e\3\2\2\2\u016a\u0161\3\2\2\2\u016a\u0162\3\2"+
		"\2\2\u016a\u0164\3\2\2\2\u016a\u0166\3\2\2\2\u016a\u0169\3\2\2\2\u016b"+
		"\\\3\2\2\2\u016c\u016d\7^\2\2\u016d\u016e\7-\2\2\u016e^\3\2\2\2\u016f"+
		"\u017a\6\60\3\2\u0170\u0174\t\7\2\2\u0171\u0173\t\b\2\2\u0172\u0171\3"+
		"\2\2\2\u0173\u0176\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0177\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u0179\7\60\2\2\u0178\u0170\3"+
		"\2\2\2\u0179\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u017d\3\2\2\2\u017c\u017a\3\2\2\2\u017d\u0181\t\7\2\2\u017e\u0180\t\b"+
		"\2\2\u017f\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181"+
		"\u0182\3\2\2\2\u0182\u0184\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0185\b\60"+
		"\5\2\u0185`\3\2\2\2\u0186\u018a\7$\2\2\u0187\u0189\n\t\2\2\u0188\u0187"+
		"\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b"+
		"\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e\7$\2\2\u018eb\3\2\2\2\u018f"+
		"\u0193\7\'\2\2\u0190\u0192\n\n\2\2\u0191\u0190\3\2\2\2\u0192\u0195\3\2"+
		"\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195"+
		"\u0193\3\2\2\2\u0196\u0197\b\62\6\2\u0197d\3\2\2\2\u0198\u019a\t\13\2"+
		"\2\u0199\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u0199\3\2\2\2\u019b\u019c"+
		"\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\b\63\7\2\u019ef\3\2\2\2\17\2"+
		"\u00a3\u013e\u0142\u0147\u014e\u016a\u0174\u017a\u0181\u018a\u0193\u019b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}