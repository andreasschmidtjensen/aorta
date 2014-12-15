// Generated from /Users/asj/Dropbox/code/phd/aorta/framework/src/java/aorta/parser/AORTALexer.g4 by ANTLR 4.1
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
		START_BLOCK=1, END_BLOCK=2, START_BRACKET=3, END_BRACKET=4, IF=5, EXECUTE=6, 
		ORGANIZATION=7, PATH=8, TYPE=9, ACT_BLOCK=10, EQUALS=11, START=12, END=13, 
		COMMA=14, COLON=15, SEMICOLON=16, NOT=17, FULLSTOP=18, PIPE=19, ROLE=20, 
		OBJ=21, OBL=22, VIOL=23, TELL=24, ACHIEVE=25, OPT=26, BEL=27, GOAL=28, 
		ORG=29, CAP=30, ENACT=31, DEACT=32, COMMIT=33, SEND=34, DROP=35, TRUE=36, 
		IS=37, ATOM=38, NUMBER=39, VAR=40, MATH_OP=41, BINARY_OP=42, UNARY_OP=43, 
		OPENSTRING=44, CLASSNAME=45, FILEPATH=46, COMMENT=47, WS=48, CLOSESTRING=49, 
		STRLIT=50;
	public static final int STRINGMODE = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "STRINGMODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'{'", "'}'", "'['", "']'", "'if'", "'=>'", "'organization'", "'path'", 
		"'type'", "'actions'", "'='", "'('", "')'", "','", "':'", "';'", "'~'", 
		"'.'", "'|'", "ROLE", "OBJ", "OBL", "VIOL", "TELL", "ACHIEVE", "'opt'", 
		"'bel'", "'goal'", "'org'", "'cap'", "ENACT", "DEACT", "COMMIT", "'send'", 
		"DROP", "'true'", "' is '", "ATOM", "NUMBER", "VAR", "MATH_OP", "BINARY_OP", 
		"'\\+'", "OPENSTRING", "CLASSNAME", "FILEPATH", "COMMENT", "WS", "CLOSESTRING", 
		"STRLIT"
	};
	public static final String[] ruleNames = {
		"START_BLOCK", "END_BLOCK", "START_BRACKET", "END_BRACKET", "IF", "EXECUTE", 
		"ORGANIZATION", "PATH", "TYPE", "ACT_BLOCK", "EQUALS", "START", "END", 
		"COMMA", "COLON", "SEMICOLON", "NOT", "FULLSTOP", "PIPE", "ROLE", "OBJ", 
		"OBL", "VIOL", "TELL", "ACHIEVE", "OPT", "BEL", "GOAL", "ORG", "CAP", 
		"ENACT", "DEACT", "COMMIT", "SEND", "DROP", "TRUE", "IS", "ATOM", "NUMBER", 
		"VAR", "MATH_OP", "BINARY_OP", "UNARY_OP", "OPENSTRING", "CLASSNAME", 
		"FILEPATH", "COMMENT", "WS", "CLOSESTRING", "STRLIT"
	};


	  private boolean init = false;
	  private boolean action = false;
	  private boolean opt = true;


	public AORTALexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AORTALexer.g4"; }

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
		case 0: START_BLOCK_action((RuleContext)_localctx, actionIndex); break;

		case 4: IF_action((RuleContext)_localctx, actionIndex); break;

		case 5: EXECUTE_action((RuleContext)_localctx, actionIndex); break;

		case 14: COLON_action((RuleContext)_localctx, actionIndex); break;

		case 17: FULLSTOP_action((RuleContext)_localctx, actionIndex); break;

		case 43: OPENSTRING_action((RuleContext)_localctx, actionIndex); break;

		case 44: CLASSNAME_action((RuleContext)_localctx, actionIndex); break;

		case 46: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 47: WS_action((RuleContext)_localctx, actionIndex); break;

		case 48: CLOSESTRING_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void EXECUTE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: action = true; break;
		}
	}
	private void OPENSTRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: pushMode(STRINGMODE);  break;
		}
	}
	private void CLOSESTRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9: popMode();  break;
		}
	}
	private void COLON_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:  opt = false;  break;
		}
	}
	private void FULLSTOP_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: action = false; opt = true; break;
		}
	}
	private void CLASSNAME_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: init = false; break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: _channel = HIDDEN;  break;
		}
	}
	private void START_BLOCK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:  opt = true;  break;
		}
	}
	private void IF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:  opt = false;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19: return ROLE_sempred((RuleContext)_localctx, predIndex);

		case 20: return OBJ_sempred((RuleContext)_localctx, predIndex);

		case 21: return OBL_sempred((RuleContext)_localctx, predIndex);

		case 22: return VIOL_sempred((RuleContext)_localctx, predIndex);

		case 23: return TELL_sempred((RuleContext)_localctx, predIndex);

		case 24: return ACHIEVE_sempred((RuleContext)_localctx, predIndex);

		case 30: return ENACT_sempred((RuleContext)_localctx, predIndex);

		case 31: return DEACT_sempred((RuleContext)_localctx, predIndex);

		case 32: return COMMIT_sempred((RuleContext)_localctx, predIndex);

		case 34: return DROP_sempred((RuleContext)_localctx, predIndex);

		case 39: return VAR_sempred((RuleContext)_localctx, predIndex);

		case 44: return CLASSNAME_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ROLE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return opt;
		}
		return true;
	}
	private boolean ACHIEVE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return opt;
		}
		return true;
	}
	private boolean COMMIT_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8: return action;
		}
		return true;
	}
	private boolean OBJ_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return opt;
		}
		return true;
	}
	private boolean TELL_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return opt;
		}
		return true;
	}
	private boolean VAR_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10: return !init;
		}
		return true;
	}
	private boolean OBL_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return opt;
		}
		return true;
	}
	private boolean VIOL_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return opt;
		}
		return true;
	}
	private boolean ENACT_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: return action;
		}
		return true;
	}
	private boolean CLASSNAME_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 11: return init;
		}
		return true;
	}
	private boolean DEACT_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return action;
		}
		return true;
	}
	private boolean DROP_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9: return action;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\64\u0185\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4"+
		"+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3"+
		"\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!"+
		"\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\7\'\u0116\n\'\f\'\16\'\u0119\13"+
		"\'\3(\5(\u011c\n(\3(\6(\u011f\n(\r(\16(\u0120\3)\3)\3)\7)\u0126\n)\f)"+
		"\16)\u0129\13)\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\3+\3+\5+\u0144\n+\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\7.\u0150"+
		"\n.\f.\16.\u0153\13.\3.\7.\u0156\n.\f.\16.\u0159\13.\3.\3.\7.\u015d\n"+
		".\f.\16.\u0160\13.\3.\3.\3/\3/\7/\u0166\n/\f/\16/\u0169\13/\3/\3/\3\60"+
		"\3\60\7\60\u016f\n\60\f\60\16\60\u0172\13\60\3\60\3\60\3\61\6\61\u0177"+
		"\n\61\r\61\16\61\u0178\3\61\3\61\3\62\3\62\3\62\3\62\3\63\6\63\u0182\n"+
		"\63\r\63\16\63\u0183\2\64\4\3\2\6\4\1\b\5\1\n\6\1\f\7\3\16\b\4\20\t\1"+
		"\22\n\1\24\13\1\26\f\1\30\r\1\32\16\1\34\17\1\36\20\1 \21\5\"\22\1$\23"+
		"\1&\24\6(\25\1*\26\1,\27\1.\30\1\60\31\1\62\32\1\64\33\1\66\34\18\35\1"+
		":\36\1<\37\1> \1@!\1B\"\1D#\1F$\1H%\1J&\1L\'\1N(\1P)\1R*\1T+\1V,\1X-\1"+
		"Z.\b\\/\7^\60\1`\61\tb\62\nd\63\13f\64\1\4\2\3\r\3\2c|\6\2\62;C\\aac|"+
		"\3\2\62;\4\2C\\aa\5\2,-//\61\61\6\2&&C\\aac|\7\2&&\62;C\\aac|\5\2\f\f"+
		"\17\17$$\4\2\f\f\17\17\5\2\13\f\17\17\"\"\3\2$$\u0198\2\4\3\2\2\2\2\6"+
		"\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2"+
		"\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34"+
		"\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2("+
		"\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2"+
		"\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2"+
		"@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3"+
		"\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2"+
		"\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\3d\3\2\2\2"+
		"\3f\3\2\2\2\4h\3\2\2\2\6k\3\2\2\2\bm\3\2\2\2\no\3\2\2\2\fq\3\2\2\2\16"+
		"v\3\2\2\2\20{\3\2\2\2\22\u0088\3\2\2\2\24\u008d\3\2\2\2\26\u0092\3\2\2"+
		"\2\30\u009a\3\2\2\2\32\u009c\3\2\2\2\34\u009e\3\2\2\2\36\u00a0\3\2\2\2"+
		" \u00a2\3\2\2\2\"\u00a5\3\2\2\2$\u00a7\3\2\2\2&\u00a9\3\2\2\2(\u00ac\3"+
		"\2\2\2*\u00ae\3\2\2\2,\u00b4\3\2\2\2.\u00b9\3\2\2\2\60\u00be\3\2\2\2\62"+
		"\u00c4\3\2\2\2\64\u00ca\3\2\2\2\66\u00d3\3\2\2\28\u00d7\3\2\2\2:\u00db"+
		"\3\2\2\2<\u00e0\3\2\2\2>\u00e4\3\2\2\2@\u00e8\3\2\2\2B\u00ef\3\2\2\2D"+
		"\u00f6\3\2\2\2F\u00fe\3\2\2\2H\u0103\3\2\2\2J\u0109\3\2\2\2L\u010e\3\2"+
		"\2\2N\u0113\3\2\2\2P\u011b\3\2\2\2R\u0122\3\2\2\2T\u012a\3\2\2\2V\u0143"+
		"\3\2\2\2X\u0145\3\2\2\2Z\u0148\3\2\2\2\\\u014c\3\2\2\2^\u0163\3\2\2\2"+
		"`\u016c\3\2\2\2b\u0176\3\2\2\2d\u017c\3\2\2\2f\u0181\3\2\2\2hi\7}\2\2"+
		"ij\b\2\2\2j\5\3\2\2\2kl\7\177\2\2l\7\3\2\2\2mn\7]\2\2n\t\3\2\2\2op\7_"+
		"\2\2p\13\3\2\2\2qr\7k\2\2rs\7h\2\2st\3\2\2\2tu\b\6\3\2u\r\3\2\2\2vw\7"+
		"?\2\2wx\7@\2\2xy\3\2\2\2yz\b\7\4\2z\17\3\2\2\2{|\7q\2\2|}\7t\2\2}~\7i"+
		"\2\2~\177\7c\2\2\177\u0080\7p\2\2\u0080\u0081\7k\2\2\u0081\u0082\7|\2"+
		"\2\u0082\u0083\7c\2\2\u0083\u0084\7v\2\2\u0084\u0085\7k\2\2\u0085\u0086"+
		"\7q\2\2\u0086\u0087\7p\2\2\u0087\21\3\2\2\2\u0088\u0089\7r\2\2\u0089\u008a"+
		"\7c\2\2\u008a\u008b\7v\2\2\u008b\u008c\7j\2\2\u008c\23\3\2\2\2\u008d\u008e"+
		"\7v\2\2\u008e\u008f\7{\2\2\u008f\u0090\7r\2\2\u0090\u0091\7g\2\2\u0091"+
		"\25\3\2\2\2\u0092\u0093\7c\2\2\u0093\u0094\7e\2\2\u0094\u0095\7v\2\2\u0095"+
		"\u0096\7k\2\2\u0096\u0097\7q\2\2\u0097\u0098\7p\2\2\u0098\u0099\7u\2\2"+
		"\u0099\27\3\2\2\2\u009a\u009b\7?\2\2\u009b\31\3\2\2\2\u009c\u009d\7*\2"+
		"\2\u009d\33\3\2\2\2\u009e\u009f\7+\2\2\u009f\35\3\2\2\2\u00a0\u00a1\7"+
		".\2\2\u00a1\37\3\2\2\2\u00a2\u00a3\7<\2\2\u00a3\u00a4\b\20\5\2\u00a4!"+
		"\3\2\2\2\u00a5\u00a6\7=\2\2\u00a6#\3\2\2\2\u00a7\u00a8\7\u0080\2\2\u00a8"+
		"%\3\2\2\2\u00a9\u00aa\7\60\2\2\u00aa\u00ab\b\23\6\2\u00ab\'\3\2\2\2\u00ac"+
		"\u00ad\7~\2\2\u00ad)\3\2\2\2\u00ae\u00af\6\25\2\2\u00af\u00b0\7t\2\2\u00b0"+
		"\u00b1\7q\2\2\u00b1\u00b2\7n\2\2\u00b2\u00b3\7g\2\2\u00b3+\3\2\2\2\u00b4"+
		"\u00b5\6\26\3\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7d\2\2\u00b7\u00b8\7l\2"+
		"\2\u00b8-\3\2\2\2\u00b9\u00ba\6\27\4\2\u00ba\u00bb\7q\2\2\u00bb\u00bc"+
		"\7d\2\2\u00bc\u00bd\7n\2\2\u00bd/\3\2\2\2\u00be\u00bf\6\30\5\2\u00bf\u00c0"+
		"\7x\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7n\2\2\u00c3"+
		"\61\3\2\2\2\u00c4\u00c5\6\31\6\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7g\2\2"+
		"\u00c7\u00c8\7n\2\2\u00c8\u00c9\7n\2\2\u00c9\63\3\2\2\2\u00ca\u00cb\6"+
		"\32\7\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7j\2\2\u00ce"+
		"\u00cf\7k\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7x\2\2\u00d1\u00d2\7g\2\2"+
		"\u00d2\65\3\2\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7r\2\2\u00d5\u00d6\7"+
		"v\2\2\u00d6\67\3\2\2\2\u00d7\u00d8\7d\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da"+
		"\7n\2\2\u00da9\3\2\2\2\u00db\u00dc\7i\2\2\u00dc\u00dd\7q\2\2\u00dd\u00de"+
		"\7c\2\2\u00de\u00df\7n\2\2\u00df;\3\2\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2"+
		"\7t\2\2\u00e2\u00e3\7i\2\2\u00e3=\3\2\2\2\u00e4\u00e5\7e\2\2\u00e5\u00e6"+
		"\7c\2\2\u00e6\u00e7\7r\2\2\u00e7?\3\2\2\2\u00e8\u00e9\6 \b\2\u00e9\u00ea"+
		"\7g\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7e\2\2\u00ed"+
		"\u00ee\7v\2\2\u00eeA\3\2\2\2\u00ef\u00f0\6!\t\2\u00f0\u00f1\7f\2\2\u00f1"+
		"\u00f2\7g\2\2\u00f2\u00f3\7c\2\2\u00f3\u00f4\7e\2\2\u00f4\u00f5\7v\2\2"+
		"\u00f5C\3\2\2\2\u00f6\u00f7\6\"\n\2\u00f7\u00f8\7e\2\2\u00f8\u00f9\7q"+
		"\2\2\u00f9\u00fa\7o\2\2\u00fa\u00fb\7o\2\2\u00fb\u00fc\7k\2\2\u00fc\u00fd"+
		"\7v\2\2\u00fdE\3\2\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100\7g\2\2\u0100\u0101"+
		"\7p\2\2\u0101\u0102\7f\2\2\u0102G\3\2\2\2\u0103\u0104\6$\13\2\u0104\u0105"+
		"\7f\2\2\u0105\u0106\7t\2\2\u0106\u0107\7q\2\2\u0107\u0108\7r\2\2\u0108"+
		"I\3\2\2\2\u0109\u010a\7v\2\2\u010a\u010b\7t\2\2\u010b\u010c\7w\2\2\u010c"+
		"\u010d\7g\2\2\u010dK\3\2\2\2\u010e\u010f\7\"\2\2\u010f\u0110\7k\2\2\u0110"+
		"\u0111\7u\2\2\u0111\u0112\7\"\2\2\u0112M\3\2\2\2\u0113\u0117\t\2\2\2\u0114"+
		"\u0116\t\3\2\2\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118O\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u011c"+
		"\7/\2\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d"+
		"\u011f\t\4\2\2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0120\u0121\3\2\2\2\u0121Q\3\2\2\2\u0122\u0123\6)\f\2\u0123\u0127"+
		"\t\5\2\2\u0124\u0126\t\3\2\2\u0125\u0124\3\2\2\2\u0126\u0129\3\2\2\2\u0127"+
		"\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128S\3\2\2\2\u0129\u0127\3\2\2\2"+
		"\u012a\u012b\t\6\2\2\u012bU\3\2\2\2\u012c\u0144\4>@\2\u012d\u012e\7?\2"+
		"\2\u012e\u012f\7\60\2\2\u012f\u0144\7\60\2\2\u0130\u0131\7?\2\2\u0131"+
		"\u0132\7<\2\2\u0132\u0144\7?\2\2\u0133\u0134\7?\2\2\u0134\u0144\7>\2\2"+
		"\u0135\u0136\7?\2\2\u0136\u0144\7?\2\2\u0137\u0138\7?\2\2\u0138\u0139"+
		"\7^\2\2\u0139\u0144\7?\2\2\u013a\u0144\7@\2\2\u013b\u013c\7@\2\2\u013c"+
		"\u0144\7?\2\2\u013d\u013e\7^\2\2\u013e\u0144\7?\2\2\u013f\u0140\7^\2\2"+
		"\u0140\u0141\7?\2\2\u0141\u0144\7?\2\2\u0142\u0144\t\6\2\2\u0143\u012c"+
		"\3\2\2\2\u0143\u012d\3\2\2\2\u0143\u0130\3\2\2\2\u0143\u0133\3\2\2\2\u0143"+
		"\u0135\3\2\2\2\u0143\u0137\3\2\2\2\u0143\u013a\3\2\2\2\u0143\u013b\3\2"+
		"\2\2\u0143\u013d\3\2\2\2\u0143\u013f\3\2\2\2\u0143\u0142\3\2\2\2\u0144"+
		"W\3\2\2\2\u0145\u0146\7^\2\2\u0146\u0147\7-\2\2\u0147Y\3\2\2\2\u0148\u0149"+
		"\7$\2\2\u0149\u014a\3\2\2\2\u014a\u014b\b-\b\2\u014b[\3\2\2\2\u014c\u0157"+
		"\6.\r\2\u014d\u0151\t\7\2\2\u014e\u0150\t\b\2\2\u014f\u014e\3\2\2\2\u0150"+
		"\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2"+
		"\2\2\u0153\u0151\3\2\2\2\u0154\u0156\7\60\2\2\u0155\u014d\3\2\2\2\u0156"+
		"\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015a\3\2"+
		"\2\2\u0159\u0157\3\2\2\2\u015a\u015e\t\7\2\2\u015b\u015d\t\b\2\2\u015c"+
		"\u015b\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2"+
		"\2\2\u015f\u0161\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0162\b.\7\2\u0162"+
		"]\3\2\2\2\u0163\u0167\7$\2\2\u0164\u0166\n\t\2\2\u0165\u0164\3\2\2\2\u0166"+
		"\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016a\3\2"+
		"\2\2\u0169\u0167\3\2\2\2\u016a\u016b\7$\2\2\u016b_\3\2\2\2\u016c\u0170"+
		"\7\'\2\2\u016d\u016f\n\n\2\2\u016e\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170"+
		"\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0173\3\2\2\2\u0172\u0170\3\2"+
		"\2\2\u0173\u0174\b\60\t\2\u0174a\3\2\2\2\u0175\u0177\t\13\2\2\u0176\u0175"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017a\u017b\b\61\n\2\u017bc\3\2\2\2\u017c\u017d\7$\2\2"+
		"\u017d\u017e\3\2\2\2\u017e\u017f\b\62\13\2\u017fe\3\2\2\2\u0180\u0182"+
		"\n\f\2\2\u0181\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0181\3\2\2\2\u0183"+
		"\u0184\3\2\2\2\u0184g\3\2\2\2\20\2\3\u0117\u011b\u0120\u0127\u0143\u0151"+
		"\u0157\u015e\u0167\u0170\u0178\u0183";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}