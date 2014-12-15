// Generated from /Users/asj/Dropbox/code/phd/aorta/framework/src/java/aorta/parser/AORTAParser.g4 by ANTLR 4.1
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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AORTAParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PATH=8, VAR=40, PIPE=19, DEACT=32, OPT=26, UNARY_OP=43, MATH_OP=41, SEMICOLON=16, 
		TELL=24, OPENSTRING=44, FULLSTOP=18, START_BLOCK=1, IF=5, TYPE=9, NUMBER=39, 
		TRUE=36, IS=37, SEND=34, STRLIT=50, NOT=17, GOAL=28, CAP=30, END_BRACKET=4, 
		END=13, ORG=29, BINARY_OP=42, COMMENT=47, DROP=35, ATOM=38, ORGANIZATION=7, 
		ROLE=20, ACHIEVE=25, START_BRACKET=3, OBJ=21, CLOSESTRING=49, OBL=22, 
		ENACT=31, ACT_BLOCK=10, WS=48, COMMA=14, EQUALS=11, COMMIT=33, BEL=27, 
		COLON=15, CLASSNAME=45, EXECUTE=6, VIOL=23, START=12, FILEPATH=46, END_BLOCK=2;
	public static final String[] tokenNames = {
		"<INVALID>", "'{'", "'}'", "'['", "']'", "'if'", "'=>'", "'organization'", 
		"'path'", "'type'", "'actions'", "'='", "'('", "')'", "','", "':'", "';'", 
		"'~'", "'.'", "'|'", "ROLE", "OBJ", "OBL", "VIOL", "TELL", "ACHIEVE", 
		"'opt'", "'bel'", "'goal'", "'org'", "'cap'", "ENACT", "DEACT", "COMMIT", 
		"'send'", "DROP", "'true'", "' is '", "ATOM", "NUMBER", "VAR", "MATH_OP", 
		"BINARY_OP", "'\\+'", "OPENSTRING", "CLASSNAME", "FILEPATH", "COMMENT", 
		"WS", "CLOSESTRING", "STRLIT"
	};
	public static final int
		RULE_aortaAgent = 0, RULE_rules = 1, RULE_ifRule = 2, RULE_actRule = 3, 
		RULE_option = 4, RULE_illForce = 5, RULE_formulas = 6, RULE_formula = 7, 
		RULE_action = 8, RULE_prolog = 9, RULE_prolog2 = 10, RULE_termBuilder = 11, 
		RULE_term = 12, RULE_string = 13, RULE_atom = 14, RULE_number = 15, RULE_var = 16, 
		RULE_struct = 17, RULE_args = 18, RULE_list = 19, RULE_listContents = 20, 
		RULE_listItem = 21;
	public static final String[] ruleNames = {
		"aortaAgent", "rules", "ifRule", "actRule", "option", "illForce", "formulas", 
		"formula", "action", "prolog", "prolog2", "termBuilder", "term", "string", 
		"atom", "number", "var", "struct", "args", "list", "listContents", "listItem"
	};

	@Override
	public String getGrammarFileName() { return "AORTAParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public AORTAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AortaAgentContext extends ParserRuleContext {
		public String name;
		public AgentBuilder agent;
		public RulesContext r;
		public TerminalNode EOF() { return getToken(AORTAParser.EOF, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public AortaAgentContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AortaAgentContext(ParserRuleContext parent, int invokingState, String name) {
			super(parent, invokingState);
			this.name = name;
		}
		@Override public int getRuleIndex() { return RULE_aortaAgent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterAortaAgent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitAortaAgent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitAortaAgent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AortaAgentContext aortaAgent(String name) throws RecognitionException {
		AortaAgentContext _localctx = new AortaAgentContext(_ctx, getState(), name);
		enterRule(_localctx, 0, RULE_aortaAgent);
		try {
			enterOuterAlt(_localctx, 1);
			{

				   Initialization init = new Initialization();
				   List<ReasoningRule> rules = new ArrayList<>();
				   
			{
			setState(45); ((AortaAgentContext)_localctx).r = rules();
			rules = ((AortaAgentContext)_localctx).r.r;
			}
			setState(48); match(EOF);

				 ((AortaAgentContext)_localctx).agent =  new AgentBuilder(name, init, rules);  
				 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RulesContext extends ParserRuleContext {
		public List<ReasoningRule> r;
		public IfRuleContext ifRule;
		public ActRuleContext actRule;
		public IfRuleContext ifRule(int i) {
			return getRuleContext(IfRuleContext.class,i);
		}
		public List<IfRuleContext> ifRule() {
			return getRuleContexts(IfRuleContext.class);
		}
		public List<ActRuleContext> actRule() {
			return getRuleContexts(ActRuleContext.class);
		}
		public ActRuleContext actRule(int i) {
			return getRuleContext(ActRuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((RulesContext)_localctx).r =  new ArrayList<>(); 
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(58);
				switch (_input.LA(1)) {
				case IF:
					{
					setState(52); ((RulesContext)_localctx).ifRule = ifRule();
					 _localctx.r.add(((RulesContext)_localctx).ifRule.rule); 
					}
					break;
				case NOT:
				case ROLE:
				case OBJ:
				case OBL:
				case VIOL:
				case SEND:
				case TRUE:
					{
					setState(55); ((RulesContext)_localctx).actRule = actRule();
					 _localctx.r.add(((RulesContext)_localctx).actRule.rule); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << NOT) | (1L << ROLE) | (1L << OBJ) | (1L << OBL) | (1L << VIOL) | (1L << SEND) | (1L << TRUE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfRuleContext extends ParserRuleContext {
		public IfRule rule;
		public FormulasContext formulas;
		public RulesContext rules;
		public TerminalNode IF() { return getToken(AORTAParser.IF, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public TerminalNode START_BLOCK() { return getToken(AORTAParser.START_BLOCK, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public IfRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterIfRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitIfRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitIfRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfRuleContext ifRule() throws RecognitionException {
		IfRuleContext _localctx = new IfRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(IF);
			setState(63); ((IfRuleContext)_localctx).formulas = formulas();
			setState(64); match(START_BLOCK);
			setState(65); ((IfRuleContext)_localctx).rules = rules();
			setState(66); match(END_BLOCK);
			 ((IfRuleContext)_localctx).rule =  new IfRule(((IfRuleContext)_localctx).formulas.fml, ((IfRuleContext)_localctx).rules.r); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActRuleContext extends ParserRuleContext {
		public ActionRule rule;
		public OptionContext option;
		public FormulasContext formulas;
		public ActionContext action;
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AORTAParser.COLON, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(AORTAParser.FULLSTOP, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode EXECUTE() { return getToken(AORTAParser.EXECUTE, 0); }
		public ActRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterActRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitActRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitActRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActRuleContext actRule() throws RecognitionException {
		ActRuleContext _localctx = new ActRuleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_actRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); ((ActRuleContext)_localctx).option = option();
			setState(70); match(COLON);
			setState(71); ((ActRuleContext)_localctx).formulas = formulas();
			setState(72); match(EXECUTE);
			setState(73); ((ActRuleContext)_localctx).action = action();
			setState(74); match(FULLSTOP);
			 ((ActRuleContext)_localctx).rule =  new ActionRule(((ActRuleContext)_localctx).option.fml, ((ActRuleContext)_localctx).formulas.fml, ((ActRuleContext)_localctx).action.aa); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public Term fml;
		public TermContext term;
		public TermContext t1;
		public IllForceContext illForce;
		public TermContext t2;
		public TermContext r;
		public TermContext o;
		public TermContext d;
		public TerminalNode NOT() { return getToken(AORTAParser.NOT, 0); }
		public TerminalNode OBJ() { return getToken(AORTAParser.OBJ, 0); }
		public TerminalNode OBL() { return getToken(AORTAParser.OBL, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode VIOL() { return getToken(AORTAParser.VIOL, 0); }
		public TerminalNode TRUE() { return getToken(AORTAParser.TRUE, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode SEND() { return getToken(AORTAParser.SEND, 0); }
		public IllForceContext illForce() {
			return getRuleContext(IllForceContext.class,0);
		}
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode ROLE() { return getToken(AORTAParser.ROLE, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			boolean pos = true;
			setState(128);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(80);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(78); match(NOT);
					pos=false;
					}
				}

				setState(82); match(ROLE);
				setState(83); match(START);
				setState(84); ((OptionContext)_localctx).term = term();
				setState(85); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("role", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 2:
				{
				setState(90);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(88); match(NOT);
					pos=false;
					}
				}

				setState(92); match(OBJ);
				setState(93); match(START);
				setState(94); ((OptionContext)_localctx).term = term();
				setState(95); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("obj", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 3:
				{
				setState(98); match(SEND);
				setState(99); match(START);
				setState(100); ((OptionContext)_localctx).t1 = ((OptionContext)_localctx).term = term();
				setState(101); match(COMMA);
				setState(102); ((OptionContext)_localctx).illForce = illForce();
				setState(103); match(COMMA);
				setState(104); ((OptionContext)_localctx).t2 = ((OptionContext)_localctx).term = term();
				setState(105); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("send", ((OptionContext)_localctx).t1.fml, ((OptionContext)_localctx).illForce.fml, ((OptionContext)_localctx).t2.fml); 
				}
				break;

			case 4:
				{
				setState(108); match(OBL);
				setState(109); match(START);
				setState(110); ((OptionContext)_localctx).r = ((OptionContext)_localctx).term = term();
				setState(111); match(COMMA);
				setState(112); ((OptionContext)_localctx).o = ((OptionContext)_localctx).term = term();
				setState(113); match(COMMA);
				setState(114); ((OptionContext)_localctx).d = ((OptionContext)_localctx).term = term();
				setState(115); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("obl", ((OptionContext)_localctx).r.fml, ((OptionContext)_localctx).o.fml, ((OptionContext)_localctx).d.fml); 
				}
				break;

			case 5:
				{
				setState(118); match(VIOL);
				setState(119); match(START);
				setState(120); ((OptionContext)_localctx).r = ((OptionContext)_localctx).term = term();
				setState(121); match(COMMA);
				setState(122); ((OptionContext)_localctx).o = ((OptionContext)_localctx).term = term();
				setState(123); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("viol", ((OptionContext)_localctx).r.fml, ((OptionContext)_localctx).o.fml); 
				}
				break;

			case 6:
				{
				setState(126); match(TRUE);
				 ((OptionContext)_localctx).fml =  Term.TRUE; 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IllForceContext extends ParserRuleContext {
		public Term fml;
		public TerminalNode TELL() { return getToken(AORTAParser.TELL, 0); }
		public TerminalNode ACHIEVE() { return getToken(AORTAParser.ACHIEVE, 0); }
		public IllForceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_illForce; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterIllForce(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitIllForce(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitIllForce(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IllForceContext illForce() throws RecognitionException {
		IllForceContext _localctx = new IllForceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_illForce);
		try {
			setState(134);
			switch (_input.LA(1)) {
			case TELL:
				enterOuterAlt(_localctx, 1);
				{
				setState(130); match(TELL);
				 ((IllForceContext)_localctx).fml =  new Struct("tell"); 
				}
				break;
			case ACHIEVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(132); match(ACHIEVE);
				 ((IllForceContext)_localctx).fml =  new Struct("achieve"); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulasContext extends ParserRuleContext {
		public Formula fml;
		public FormulaContext formula;
		public FormulasContext fmls;
		public TerminalNode NOT() { return getToken(AORTAParser.NOT, 0); }
		public TerminalNode TRUE() { return getToken(AORTAParser.TRUE, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public FormulasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterFormulas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitFormulas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitFormulas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulasContext formulas() throws RecognitionException {
		FormulasContext _localctx = new FormulasContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formulas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(136); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  ((FormulasContext)_localctx).formula.fml; 
				}
				break;

			case 2:
				{
				setState(139); match(TRUE);
				 ((FormulasContext)_localctx).fml =  new TrueFormula(); 
				}
				break;

			case 3:
				{
				setState(141); ((FormulasContext)_localctx).formula = formula();
				setState(142); match(COMMA);
				setState(143); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(((FormulasContext)_localctx).formula.fml, ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 4:
				{
				setState(146); match(NOT);
				setState(147); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  new NegatedFormula(((FormulasContext)_localctx).formula.fml); 
				}
				break;

			case 5:
				{
				setState(150); match(NOT);
				setState(151); ((FormulasContext)_localctx).formula = formula();
				setState(152); match(COMMA);
				setState(153); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(new NegatedFormula(((FormulasContext)_localctx).formula.fml), ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 6:
				{
				setState(156); match(NOT);
				setState(157); match(START);
				setState(158); ((FormulasContext)_localctx).fmls = formulas();
				setState(159); match(END);
				 ((FormulasContext)_localctx).fml =  new NegatedFormula(((FormulasContext)_localctx).fmls.fml); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public Formula fml;
		public PrologContext prolog;
		public TermContext term;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode OPT() { return getToken(AORTAParser.OPT, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode GOAL() { return getToken(AORTAParser.GOAL, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode ORG() { return getToken(AORTAParser.ORG, 0); }
		public TerminalNode CAP() { return getToken(AORTAParser.CAP, 0); }
		public TerminalNode BEL() { return getToken(AORTAParser.BEL, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			switch (_input.LA(1)) {
			case OPT:
				{
				setState(164); match(OPT);
				setState(165); match(START);
				setState(166); ((FormulaContext)_localctx).prolog = prolog();
				setState(167); match(END);
				 ((FormulaContext)_localctx).fml =  new OptionFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case BEL:
				{
				setState(170); match(BEL);
				setState(171); match(START);
				setState(172); ((FormulaContext)_localctx).prolog = prolog();
				setState(173); match(END);
				 ((FormulaContext)_localctx).fml =  new BeliefFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case GOAL:
				{
				setState(176); match(GOAL);
				setState(177); match(START);
				setState(178); ((FormulaContext)_localctx).prolog = prolog();
				setState(179); match(END);
				 ((FormulaContext)_localctx).fml =  new GoalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case ORG:
				{
				setState(182); match(ORG);
				setState(183); match(START);
				setState(184); ((FormulaContext)_localctx).prolog = prolog();
				setState(185); match(END);
				 ((FormulaContext)_localctx).fml =  new OrganizationalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case CAP:
				{
				setState(188); match(CAP);
				setState(189); match(START);
				setState(190); ((FormulaContext)_localctx).term = term();
				setState(191); match(END);
				 ((FormulaContext)_localctx).fml =  new CapabilityFormula(((FormulaContext)_localctx).term.fml); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public Action aa;
		public TermContext pl;
		public TermContext ag;
		public FormulaContext fml;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode DEACT() { return getToken(AORTAParser.DEACT, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode SEND() { return getToken(AORTAParser.SEND, 0); }
		public TerminalNode ENACT() { return getToken(AORTAParser.ENACT, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode DROP() { return getToken(AORTAParser.DROP, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode COMMIT() { return getToken(AORTAParser.COMMIT, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			switch (_input.LA(1)) {
			case ENACT:
				{
				setState(196); match(ENACT);
				setState(197); match(START);
				setState(198); ((ActionContext)_localctx).pl = term();
				setState(199); match(END);
				 ((ActionContext)_localctx).aa =  new EnactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DEACT:
				{
				setState(202); match(DEACT);
				setState(203); match(START);
				setState(204); ((ActionContext)_localctx).pl = term();
				setState(205); match(END);
				 ((ActionContext)_localctx).aa =  new DeactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case COMMIT:
				{
				setState(208); match(COMMIT);
				setState(209); match(START);
				setState(210); ((ActionContext)_localctx).pl = term();
				setState(211); match(END);
				 ((ActionContext)_localctx).aa =  new CommitAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DROP:
				{
				setState(214); match(DROP);
				setState(215); match(START);
				setState(216); ((ActionContext)_localctx).pl = term();
				setState(217); match(END);
				 ((ActionContext)_localctx).aa =  new DropAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case SEND:
				{
				setState(220); match(SEND);
				setState(221); match(START);
				setState(222); ((ActionContext)_localctx).ag = term();
				setState(223); match(COMMA);
				setState(224); ((ActionContext)_localctx).fml = formula();
				setState(225); match(END);
				 ((ActionContext)_localctx).aa =  new SendAction(((ActionContext)_localctx).ag.fml, ((ActionContext)_localctx).fml.fml); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrologContext extends ParserRuleContext {
		public Term fml;
		public Prolog2Context prolog2;
		public Prolog2Context prolog2() {
			return getRuleContext(Prolog2Context.class,0);
		}
		public PrologContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterProlog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitProlog(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitProlog(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prolog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); ((PrologContext)_localctx).prolog2 = prolog2();
			 ((PrologContext)_localctx).fml =  Term.createTerm((((PrologContext)_localctx).prolog2!=null?_input.getText(((PrologContext)_localctx).prolog2.start,((PrologContext)_localctx).prolog2.stop):null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Prolog2Context extends ParserRuleContext {
		public Term fml;
		public Prolog2Context pl;
		public Token UNARY_OP;
		public TermBuilderContext termBuilder;
		public TerminalNode SEMICOLON() { return getToken(AORTAParser.SEMICOLON, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode UNARY_OP() { return getToken(AORTAParser.UNARY_OP, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public Prolog2Context prolog2() {
			return getRuleContext(Prolog2Context.class,0);
		}
		public TermBuilderContext termBuilder() {
			return getRuleContext(TermBuilderContext.class,0);
		}
		public Prolog2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterProlog2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitProlog2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitProlog2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Prolog2Context prolog2() throws RecognitionException {
		Prolog2Context _localctx = new Prolog2Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_prolog2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(233); match(COMMA);
				setState(234); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(237); match(SEMICOLON);
				setState(238); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(241); match(START);
				setState(242); ((Prolog2Context)_localctx).pl = prolog2();
				setState(243); match(END);
				setState(244); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(247); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(248); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(251); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(252); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).termBuilder.fml; 
				}
				break;

			case 6:
				{
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermBuilderContext extends ParserRuleContext {
		public Term fml;
		public TermContext t3;
		public TermContext term;
		public TermContext t4;
		public Token MATH_OP;
		public TermContext t5;
		public TermContext t1;
		public Token BINARY_OP;
		public TermContext t2;
		public TerminalNode IS() { return getToken(AORTAParser.IS, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode BINARY_OP() { return getToken(AORTAParser.BINARY_OP, 0); }
		public TerminalNode MATH_OP() { return getToken(AORTAParser.MATH_OP, 0); }
		public TermBuilderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termBuilder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterTermBuilder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitTermBuilder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitTermBuilder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermBuilderContext termBuilder() throws RecognitionException {
		TermBuilderContext _localctx = new TermBuilderContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(258); ((TermBuilderContext)_localctx).t3 = ((TermBuilderContext)_localctx).term = term();
				setState(259); match(IS);
				setState(260); ((TermBuilderContext)_localctx).t4 = ((TermBuilderContext)_localctx).term = term();
				setState(261); ((TermBuilderContext)_localctx).MATH_OP = match(MATH_OP);
				setState(262); ((TermBuilderContext)_localctx).t5 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct("is", ((TermBuilderContext)_localctx).t3.fml, new Struct((((TermBuilderContext)_localctx).MATH_OP!=null?((TermBuilderContext)_localctx).MATH_OP.getText():null), ((TermBuilderContext)_localctx).t4.fml, ((TermBuilderContext)_localctx).t5.fml)); 
				}
				break;

			case 2:
				{
				setState(265); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 3:
				{
				setState(268); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(269); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(270); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct((((TermBuilderContext)_localctx).BINARY_OP!=null?((TermBuilderContext)_localctx).BINARY_OP.getText():null), ((TermBuilderContext)_localctx).t1.fml, ((TermBuilderContext)_localctx).t2.fml); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Term fml;
		public FormulaContext formula;
		public Token UNARY_OP;
		public TermContext t;
		public StringContext string;
		public StructContext struct;
		public AtomContext atom;
		public VarContext var;
		public NumberContext number;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode UNARY_OP() { return getToken(AORTAParser.UNARY_OP, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(275); ((TermContext)_localctx).formula = formula();
				((TermContext)_localctx).fml =  new Struct(((ReasoningFormula)((TermContext)_localctx).formula.fml).getType(), ((ReasoningFormula)((TermContext)_localctx).formula.fml).getFormula());
				}
				break;

			case 2:
				{
				setState(278); ((TermContext)_localctx).UNARY_OP = match(UNARY_OP);
				setState(279); ((TermContext)_localctx).t = term();
				((TermContext)_localctx).fml =  new Struct((((TermContext)_localctx).UNARY_OP!=null?((TermContext)_localctx).UNARY_OP.getText():null), ((TermContext)_localctx).t.fml);
				}
				break;

			case 3:
				{
				setState(282); ((TermContext)_localctx).string = string();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).string.fml; 
				}
				break;

			case 4:
				{
				setState(285); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 5:
				{
				setState(288); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 6:
				{
				setState(291); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 7:
				{
				setState(294); ((TermContext)_localctx).number = number();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).number.fml;
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public Struct fml;
		public Token FILEPATH;
		public TerminalNode FILEPATH() { return getToken(AORTAParser.FILEPATH, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299); ((StringContext)_localctx).FILEPATH = match(FILEPATH);
			 ((StringContext)_localctx).fml =  new Struct((((StringContext)_localctx).FILEPATH!=null?((StringContext)_localctx).FILEPATH.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Struct fml;
		public Token ATOM;
		public TerminalNode ATOM() { return getToken(AORTAParser.ATOM, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302); ((AtomContext)_localctx).ATOM = match(ATOM);
			 ((AtomContext)_localctx).fml =  new Struct((((AtomContext)_localctx).ATOM!=null?((AtomContext)_localctx).ATOM.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public Number fml;
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(AORTAParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305); ((NumberContext)_localctx).NUMBER = match(NUMBER);
			 String numStr = (((NumberContext)_localctx).NUMBER!=null?((NumberContext)_localctx).NUMBER.getText():null); ((NumberContext)_localctx).fml =  new Int(Integer.parseInt(numStr)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public Var fml;
		public Token VAR;
		public TerminalNode VAR() { return getToken(AORTAParser.VAR, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308); ((VarContext)_localctx).VAR = match(VAR);
			 ((VarContext)_localctx).fml =  new Var((((VarContext)_localctx).VAR!=null?((VarContext)_localctx).VAR.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructContext extends ParserRuleContext {
		public Struct fml;
		public Token ATOM;
		public ArgsContext args;
		public ListContext list;
		public TerminalNode ATOM() { return getToken(AORTAParser.ATOM, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_struct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(311); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(312); match(START);
				setState(313); ((StructContext)_localctx).args = args();
				setState(314); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(317); ((StructContext)_localctx).list = list();
				 ((StructContext)_localctx).fml =  ((StructContext)_localctx).list.fml; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<Term> fml;
		public TermContext term;
		public ArgsContext a;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ArgsContext)_localctx).fml =  new ArrayList<>(); 
			setState(331);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(323); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(326); ((ArgsContext)_localctx).term = term();
				setState(327); match(COMMA);
				setState(328); ((ArgsContext)_localctx).a = args();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); _localctx.fml.addAll(((ArgsContext)_localctx).a.fml); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public Struct fml;
		public ListContentsContext listContents;
		public TermContext t1;
		public TermContext t2;
		public TerminalNode START_BRACKET() { return getToken(AORTAParser.START_BRACKET, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode PIPE() { return getToken(AORTAParser.PIPE, 0); }
		public TerminalNode END_BRACKET() { return getToken(AORTAParser.END_BRACKET, 0); }
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_list);
		try {
			setState(345);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(333); match(START_BRACKET);
				setState(334); ((ListContext)_localctx).listContents = listContents();
				setState(335); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338); match(START_BRACKET);
				setState(339); ((ListContext)_localctx).t1 = term();
				setState(340); match(PIPE);
				setState(341); ((ListContext)_localctx).t2 = term();
				setState(342); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  new Struct(((ListContext)_localctx).t1.fml, ((ListContext)_localctx).t2.fml); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContentsContext extends ParserRuleContext {
		public Struct fml;
		public ListItemContext listItem;
		public ListContentsContext lc;
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public ListItemContext listItem() {
			return getRuleContext(ListItemContext.class,0);
		}
		public ListContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listContents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterListContents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitListContents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitListContents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContentsContext listContents() throws RecognitionException {
		ListContentsContext _localctx = new ListContentsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_listContents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ListContentsContext)_localctx).fml =  new Struct(); 
			setState(356);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(348); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(351); ((ListContentsContext)_localctx).listItem = listItem();
				setState(352); match(COMMA);
				setState(353); ((ListContentsContext)_localctx).lc = listContents();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); _localctx.fml.append(((ListContentsContext)_localctx).lc.fml); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListItemContext extends ParserRuleContext {
		public Term fml;
		public Prolog2Context prolog2;
		public Prolog2Context prolog2() {
			return getRuleContext(Prolog2Context.class,0);
		}
		public ListItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).enterListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAParserListener ) ((AORTAParserListener)listener).exitListItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAParserVisitor ) return ((AORTAParserVisitor<? extends T>)visitor).visitListItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_listItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358); ((ListItemContext)_localctx).prolog2 = prolog2();
			 ((ListItemContext)_localctx).fml =  ((ListItemContext)_localctx).prolog2.fml; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\64\u016c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3=\n\3\r\3\16\3>\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6S\n\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6]\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\3\7\3\7\3\7"+
		"\3\7\5\7\u0089\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a5\n\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c5\n\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e7\n\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0103\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0114\n\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\5\16\u012c\n\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u0143\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u014e"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u015c\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0167\n"+
		"\26\3\27\3\27\3\27\3\27\2\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,\2\2\u017d\2.\3\2\2\2\4\65\3\2\2\2\6@\3\2\2\2\bG\3\2\2\2\nO\3\2\2"+
		"\2\f\u0088\3\2\2\2\16\u00a4\3\2\2\2\20\u00c4\3\2\2\2\22\u00e6\3\2\2\2"+
		"\24\u00e8\3\2\2\2\26\u0102\3\2\2\2\30\u0113\3\2\2\2\32\u012b\3\2\2\2\34"+
		"\u012d\3\2\2\2\36\u0130\3\2\2\2 \u0133\3\2\2\2\"\u0136\3\2\2\2$\u0142"+
		"\3\2\2\2&\u0144\3\2\2\2(\u015b\3\2\2\2*\u015d\3\2\2\2,\u0168\3\2\2\2."+
		"/\b\2\1\2/\60\5\4\3\2\60\61\b\2\1\2\61\62\3\2\2\2\62\63\7\2\2\3\63\64"+
		"\b\2\1\2\64\3\3\2\2\2\65<\b\3\1\2\66\67\5\6\4\2\678\b\3\1\28=\3\2\2\2"+
		"9:\5\b\5\2:;\b\3\1\2;=\3\2\2\2<\66\3\2\2\2<9\3\2\2\2=>\3\2\2\2><\3\2\2"+
		"\2>?\3\2\2\2?\5\3\2\2\2@A\7\7\2\2AB\5\16\b\2BC\7\3\2\2CD\5\4\3\2DE\7\4"+
		"\2\2EF\b\4\1\2F\7\3\2\2\2GH\5\n\6\2HI\7\21\2\2IJ\5\16\b\2JK\7\b\2\2KL"+
		"\5\22\n\2LM\7\24\2\2MN\b\5\1\2N\t\3\2\2\2O\u0082\b\6\1\2PQ\7\23\2\2QS"+
		"\b\6\1\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7\26\2\2UV\7\16\2\2VW\5\32\16"+
		"\2WX\7\17\2\2XY\b\6\1\2Y\u0083\3\2\2\2Z[\7\23\2\2[]\b\6\1\2\\Z\3\2\2\2"+
		"\\]\3\2\2\2]^\3\2\2\2^_\7\27\2\2_`\7\16\2\2`a\5\32\16\2ab\7\17\2\2bc\b"+
		"\6\1\2c\u0083\3\2\2\2de\7$\2\2ef\7\16\2\2fg\5\32\16\2gh\7\20\2\2hi\5\f"+
		"\7\2ij\7\20\2\2jk\5\32\16\2kl\7\17\2\2lm\b\6\1\2m\u0083\3\2\2\2no\7\30"+
		"\2\2op\7\16\2\2pq\5\32\16\2qr\7\20\2\2rs\5\32\16\2st\7\20\2\2tu\5\32\16"+
		"\2uv\7\17\2\2vw\b\6\1\2w\u0083\3\2\2\2xy\7\31\2\2yz\7\16\2\2z{\5\32\16"+
		"\2{|\7\20\2\2|}\5\32\16\2}~\7\17\2\2~\177\b\6\1\2\177\u0083\3\2\2\2\u0080"+
		"\u0081\7&\2\2\u0081\u0083\b\6\1\2\u0082R\3\2\2\2\u0082\\\3\2\2\2\u0082"+
		"d\3\2\2\2\u0082n\3\2\2\2\u0082x\3\2\2\2\u0082\u0080\3\2\2\2\u0083\13\3"+
		"\2\2\2\u0084\u0085\7\32\2\2\u0085\u0089\b\7\1\2\u0086\u0087\7\33\2\2\u0087"+
		"\u0089\b\7\1\2\u0088\u0084\3\2\2\2\u0088\u0086\3\2\2\2\u0089\r\3\2\2\2"+
		"\u008a\u008b\5\20\t\2\u008b\u008c\b\b\1\2\u008c\u00a5\3\2\2\2\u008d\u008e"+
		"\7&\2\2\u008e\u00a5\b\b\1\2\u008f\u0090\5\20\t\2\u0090\u0091\7\20\2\2"+
		"\u0091\u0092\5\16\b\2\u0092\u0093\b\b\1\2\u0093\u00a5\3\2\2\2\u0094\u0095"+
		"\7\23\2\2\u0095\u0096\5\20\t\2\u0096\u0097\b\b\1\2\u0097\u00a5\3\2\2\2"+
		"\u0098\u0099\7\23\2\2\u0099\u009a\5\20\t\2\u009a\u009b\7\20\2\2\u009b"+
		"\u009c\5\16\b\2\u009c\u009d\b\b\1\2\u009d\u00a5\3\2\2\2\u009e\u009f\7"+
		"\23\2\2\u009f\u00a0\7\16\2\2\u00a0\u00a1\5\16\b\2\u00a1\u00a2\7\17\2\2"+
		"\u00a2\u00a3\b\b\1\2\u00a3\u00a5\3\2\2\2\u00a4\u008a\3\2\2\2\u00a4\u008d"+
		"\3\2\2\2\u00a4\u008f\3\2\2\2\u00a4\u0094\3\2\2\2\u00a4\u0098\3\2\2\2\u00a4"+
		"\u009e\3\2\2\2\u00a5\17\3\2\2\2\u00a6\u00a7\7\34\2\2\u00a7\u00a8\7\16"+
		"\2\2\u00a8\u00a9\5\24\13\2\u00a9\u00aa\7\17\2\2\u00aa\u00ab\b\t\1\2\u00ab"+
		"\u00c5\3\2\2\2\u00ac\u00ad\7\35\2\2\u00ad\u00ae\7\16\2\2\u00ae\u00af\5"+
		"\24\13\2\u00af\u00b0\7\17\2\2\u00b0\u00b1\b\t\1\2\u00b1\u00c5\3\2\2\2"+
		"\u00b2\u00b3\7\36\2\2\u00b3\u00b4\7\16\2\2\u00b4\u00b5\5\24\13\2\u00b5"+
		"\u00b6\7\17\2\2\u00b6\u00b7\b\t\1\2\u00b7\u00c5\3\2\2\2\u00b8\u00b9\7"+
		"\37\2\2\u00b9\u00ba\7\16\2\2\u00ba\u00bb\5\24\13\2\u00bb\u00bc\7\17\2"+
		"\2\u00bc\u00bd\b\t\1\2\u00bd\u00c5\3\2\2\2\u00be\u00bf\7 \2\2\u00bf\u00c0"+
		"\7\16\2\2\u00c0\u00c1\5\32\16\2\u00c1\u00c2\7\17\2\2\u00c2\u00c3\b\t\1"+
		"\2\u00c3\u00c5\3\2\2\2\u00c4\u00a6\3\2\2\2\u00c4\u00ac\3\2\2\2\u00c4\u00b2"+
		"\3\2\2\2\u00c4\u00b8\3\2\2\2\u00c4\u00be\3\2\2\2\u00c5\21\3\2\2\2\u00c6"+
		"\u00c7\7!\2\2\u00c7\u00c8\7\16\2\2\u00c8\u00c9\5\32\16\2\u00c9\u00ca\7"+
		"\17\2\2\u00ca\u00cb\b\n\1\2\u00cb\u00e7\3\2\2\2\u00cc\u00cd\7\"\2\2\u00cd"+
		"\u00ce\7\16\2\2\u00ce\u00cf\5\32\16\2\u00cf\u00d0\7\17\2\2\u00d0\u00d1"+
		"\b\n\1\2\u00d1\u00e7\3\2\2\2\u00d2\u00d3\7#\2\2\u00d3\u00d4\7\16\2\2\u00d4"+
		"\u00d5\5\32\16\2\u00d5\u00d6\7\17\2\2\u00d6\u00d7\b\n\1\2\u00d7\u00e7"+
		"\3\2\2\2\u00d8\u00d9\7%\2\2\u00d9\u00da\7\16\2\2\u00da\u00db\5\32\16\2"+
		"\u00db\u00dc\7\17\2\2\u00dc\u00dd\b\n\1\2\u00dd\u00e7\3\2\2\2\u00de\u00df"+
		"\7$\2\2\u00df\u00e0\7\16\2\2\u00e0\u00e1\5\32\16\2\u00e1\u00e2\7\20\2"+
		"\2\u00e2\u00e3\5\20\t\2\u00e3\u00e4\7\17\2\2\u00e4\u00e5\b\n\1\2\u00e5"+
		"\u00e7\3\2\2\2\u00e6\u00c6\3\2\2\2\u00e6\u00cc\3\2\2\2\u00e6\u00d2\3\2"+
		"\2\2\u00e6\u00d8\3\2\2\2\u00e6\u00de\3\2\2\2\u00e7\23\3\2\2\2\u00e8\u00e9"+
		"\5\26\f\2\u00e9\u00ea\b\13\1\2\u00ea\25\3\2\2\2\u00eb\u00ec\7\20\2\2\u00ec"+
		"\u00ed\5\26\f\2\u00ed\u00ee\b\f\1\2\u00ee\u0103\3\2\2\2\u00ef\u00f0\7"+
		"\22\2\2\u00f0\u00f1\5\26\f\2\u00f1\u00f2\b\f\1\2\u00f2\u0103\3\2\2\2\u00f3"+
		"\u00f4\7\16\2\2\u00f4\u00f5\5\26\f\2\u00f5\u00f6\7\17\2\2\u00f6\u00f7"+
		"\5\26\f\2\u00f7\u00f8\b\f\1\2\u00f8\u0103\3\2\2\2\u00f9\u00fa\7-\2\2\u00fa"+
		"\u00fb\5\26\f\2\u00fb\u00fc\b\f\1\2\u00fc\u0103\3\2\2\2\u00fd\u00fe\5"+
		"\30\r\2\u00fe\u00ff\5\26\f\2\u00ff\u0100\b\f\1\2\u0100\u0103\3\2\2\2\u0101"+
		"\u0103\3\2\2\2\u0102\u00eb\3\2\2\2\u0102\u00ef\3\2\2\2\u0102\u00f3\3\2"+
		"\2\2\u0102\u00f9\3\2\2\2\u0102\u00fd\3\2\2\2\u0102\u0101\3\2\2\2\u0103"+
		"\27\3\2\2\2\u0104\u0105\5\32\16\2\u0105\u0106\7\'\2\2\u0106\u0107\5\32"+
		"\16\2\u0107\u0108\7+\2\2\u0108\u0109\5\32\16\2\u0109\u010a\b\r\1\2\u010a"+
		"\u0114\3\2\2\2\u010b\u010c\5\32\16\2\u010c\u010d\b\r\1\2\u010d\u0114\3"+
		"\2\2\2\u010e\u010f\5\32\16\2\u010f\u0110\7,\2\2\u0110\u0111\5\32\16\2"+
		"\u0111\u0112\b\r\1\2\u0112\u0114\3\2\2\2\u0113\u0104\3\2\2\2\u0113\u010b"+
		"\3\2\2\2\u0113\u010e\3\2\2\2\u0114\31\3\2\2\2\u0115\u0116\5\20\t\2\u0116"+
		"\u0117\b\16\1\2\u0117\u012c\3\2\2\2\u0118\u0119\7-\2\2\u0119\u011a\5\32"+
		"\16\2\u011a\u011b\b\16\1\2\u011b\u012c\3\2\2\2\u011c\u011d\5\34\17\2\u011d"+
		"\u011e\b\16\1\2\u011e\u012c\3\2\2\2\u011f\u0120\5$\23\2\u0120\u0121\b"+
		"\16\1\2\u0121\u012c\3\2\2\2\u0122\u0123\5\36\20\2\u0123\u0124\b\16\1\2"+
		"\u0124\u012c\3\2\2\2\u0125\u0126\5\"\22\2\u0126\u0127\b\16\1\2\u0127\u012c"+
		"\3\2\2\2\u0128\u0129\5 \21\2\u0129\u012a\b\16\1\2\u012a\u012c\3\2\2\2"+
		"\u012b\u0115\3\2\2\2\u012b\u0118\3\2\2\2\u012b\u011c\3\2\2\2\u012b\u011f"+
		"\3\2\2\2\u012b\u0122\3\2\2\2\u012b\u0125\3\2\2\2\u012b\u0128\3\2\2\2\u012c"+
		"\33\3\2\2\2\u012d\u012e\7\60\2\2\u012e\u012f\b\17\1\2\u012f\35\3\2\2\2"+
		"\u0130\u0131\7(\2\2\u0131\u0132\b\20\1\2\u0132\37\3\2\2\2\u0133\u0134"+
		"\7)\2\2\u0134\u0135\b\21\1\2\u0135!\3\2\2\2\u0136\u0137\7*\2\2\u0137\u0138"+
		"\b\22\1\2\u0138#\3\2\2\2\u0139\u013a\7(\2\2\u013a\u013b\7\16\2\2\u013b"+
		"\u013c\5&\24\2\u013c\u013d\7\17\2\2\u013d\u013e\b\23\1\2\u013e\u0143\3"+
		"\2\2\2\u013f\u0140\5(\25\2\u0140\u0141\b\23\1\2\u0141\u0143\3\2\2\2\u0142"+
		"\u0139\3\2\2\2\u0142\u013f\3\2\2\2\u0143%\3\2\2\2\u0144\u014d\b\24\1\2"+
		"\u0145\u0146\5\32\16\2\u0146\u0147\b\24\1\2\u0147\u014e\3\2\2\2\u0148"+
		"\u0149\5\32\16\2\u0149\u014a\7\20\2\2\u014a\u014b\5&\24\2\u014b\u014c"+
		"\b\24\1\2\u014c\u014e\3\2\2\2\u014d\u0145\3\2\2\2\u014d\u0148\3\2\2\2"+
		"\u014e\'\3\2\2\2\u014f\u0150\7\5\2\2\u0150\u0151\5*\26\2\u0151\u0152\7"+
		"\6\2\2\u0152\u0153\b\25\1\2\u0153\u015c\3\2\2\2\u0154\u0155\7\5\2\2\u0155"+
		"\u0156\5\32\16\2\u0156\u0157\7\25\2\2\u0157\u0158\5\32\16\2\u0158\u0159"+
		"\7\6\2\2\u0159\u015a\b\25\1\2\u015a\u015c\3\2\2\2\u015b\u014f\3\2\2\2"+
		"\u015b\u0154\3\2\2\2\u015c)\3\2\2\2\u015d\u0166\b\26\1\2\u015e\u015f\5"+
		",\27\2\u015f\u0160\b\26\1\2\u0160\u0167\3\2\2\2\u0161\u0162\5,\27\2\u0162"+
		"\u0163\7\20\2\2\u0163\u0164\5*\26\2\u0164\u0165\b\26\1\2\u0165\u0167\3"+
		"\2\2\2\u0166\u015e\3\2\2\2\u0166\u0161\3\2\2\2\u0167+\3\2\2\2\u0168\u0169"+
		"\5\26\f\2\u0169\u016a\b\27\1\2\u016a-\3\2\2\2\22<>R\\\u0082\u0088\u00a4"+
		"\u00c4\u00e6\u0102\u0113\u012b\u0142\u014d\u015b\u0166";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}