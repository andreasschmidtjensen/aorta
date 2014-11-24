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
		T__0=1, START_BLOCK=2, END_BLOCK=3, START_BRACKET=4, END_BRACKET=5, IF=6, 
		EXECUTE=7, ORGANIZATION=8, PATH=9, TYPE=10, ACT_BLOCK=11, EQUALS=12, START=13, 
		END=14, COMMA=15, COLON=16, SEMICOLON=17, NOT=18, FULLSTOP=19, PIPE=20, 
		ROLE=21, OBJ=22, TELL=23, ACHIEVE=24, OPT=25, BEL=26, GOAL=27, ORG=28, 
		CAP=29, CONSIDER=30, DISREGARD=31, ENACT=32, DEACT=33, COMMIT=34, SEND=35, 
		DROP=36, TRUE=37, ATOM=38, NUMBER=39, VAR=40, MATH_OP=41, BINARY_OP=42, 
		UNARY_OP=43, CLASSNAME=44, FILEPATH=45, COMMENT=46, WS=47;
	public static final String[] tokenNames = {
		"<INVALID>", "' is '", "'{'", "'}'", "'['", "']'", "'if'", "'=>'", "'organization'", 
		"'path'", "'type'", "'actions'", "'='", "'('", "')'", "','", "':'", "';'", 
		"'~'", "'.'", "'|'", "'role'", "'obj'", "'tell'", "'achieve'", "'opt'", 
		"'bel'", "'goal'", "'org'", "'cap'", "'consider'", "'disregard'", "'enact'", 
		"'deact'", "'commit'", "'send'", "'drop'", "'true'", "ATOM", "NUMBER", 
		"VAR", "MATH_OP", "BINARY_OP", "'\\+'", "CLASSNAME", "FILEPATH", "COMMENT", 
		"WS"
	};
	public static final int
		RULE_aortaAgent = 0, RULE_rules = 1, RULE_ifRule = 2, RULE_actRule = 3, 
		RULE_option = 4, RULE_illForce = 5, RULE_formulas = 6, RULE_formula = 7, 
		RULE_action = 8, RULE_prolog = 9, RULE_prolog2 = 10, RULE_termBuilder = 11, 
		RULE_term = 12, RULE_atom = 13, RULE_number = 14, RULE_var = 15, RULE_struct = 16, 
		RULE_args = 17, RULE_list = 18, RULE_listContents = 19, RULE_listItem = 20;
	public static final String[] ruleNames = {
		"aortaAgent", "rules", "ifRule", "actRule", "option", "illForce", "formulas", 
		"formula", "action", "prolog", "prolog2", "termBuilder", "term", "atom", 
		"number", "var", "struct", "args", "list", "listContents", "listItem"
	};

	@Override
	public String getGrammarFileName() { return "AORTA.g4"; }

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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterAortaAgent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitAortaAgent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitAortaAgent(this);
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
			setState(43); ((AortaAgentContext)_localctx).r = rules();
			rules = ((AortaAgentContext)_localctx).r.r;
			}
			setState(46); match(EOF);

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
		public List<ActRuleContext> actRule() {
			return getRuleContexts(ActRuleContext.class);
		}
		public List<IfRuleContext> ifRule() {
			return getRuleContexts(IfRuleContext.class);
		}
		public IfRuleContext ifRule(int i) {
			return getRuleContext(IfRuleContext.class,i);
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitRules(this);
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
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(56);
				switch (_input.LA(1)) {
				case IF:
					{
					setState(50); ((RulesContext)_localctx).ifRule = ifRule();
					 _localctx.r.add(((RulesContext)_localctx).ifRule.rule); 
					}
					break;
				case NOT:
				case ROLE:
				case OBJ:
				case SEND:
					{
					setState(53); ((RulesContext)_localctx).actRule = actRule();
					 _localctx.r.add(((RulesContext)_localctx).actRule.rule); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << NOT) | (1L << ROLE) | (1L << OBJ) | (1L << SEND))) != 0) );
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
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterIfRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitIfRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitIfRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfRuleContext ifRule() throws RecognitionException {
		IfRuleContext _localctx = new IfRuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(IF);
			setState(61); ((IfRuleContext)_localctx).formulas = formulas();
			setState(62); match(START_BLOCK);
			setState(63); ((IfRuleContext)_localctx).rules = rules();
			setState(64); match(END_BLOCK);
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
		public TerminalNode EXECUTE() { return getToken(AORTAParser.EXECUTE, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(AORTAParser.FULLSTOP, 0); }
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AORTAParser.COLON, 0); }
		public ActRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterActRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitActRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitActRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActRuleContext actRule() throws RecognitionException {
		ActRuleContext _localctx = new ActRuleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_actRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); ((ActRuleContext)_localctx).option = option();
			setState(68); match(COLON);
			setState(69); ((ActRuleContext)_localctx).formulas = formulas();
			setState(70); match(EXECUTE);
			setState(71); ((ActRuleContext)_localctx).action = action();
			setState(72); match(FULLSTOP);
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AORTAParser.COMMA); }
		public TerminalNode SEND() { return getToken(AORTAParser.SEND, 0); }
		public TerminalNode OBJ() { return getToken(AORTAParser.OBJ, 0); }
		public TerminalNode NOT() { return getToken(AORTAParser.NOT, 0); }
		public IllForceContext illForce() {
			return getRuleContext(IllForceContext.class,0);
		}
		public TerminalNode ROLE() { return getToken(AORTAParser.ROLE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(AORTAParser.COMMA, i);
		}
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitOption(this);
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
			setState(106);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(78);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(76); match(NOT);
					pos=false;
					}
				}

				setState(80); match(ROLE);
				setState(81); match(START);
				setState(82); ((OptionContext)_localctx).term = term();
				setState(83); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("role", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 2:
				{
				setState(88);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(86); match(NOT);
					pos=false;
					}
				}

				setState(90); match(OBJ);
				setState(91); match(START);
				setState(92); ((OptionContext)_localctx).term = term();
				setState(93); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("obj", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 3:
				{
				setState(96); match(SEND);
				setState(97); match(START);
				setState(98); ((OptionContext)_localctx).t1 = ((OptionContext)_localctx).term = term();
				setState(99); match(COMMA);
				setState(100); ((OptionContext)_localctx).illForce = illForce();
				setState(101); match(COMMA);
				setState(102); ((OptionContext)_localctx).t2 = ((OptionContext)_localctx).term = term();
				setState(103); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("send", ((OptionContext)_localctx).t1.fml, ((OptionContext)_localctx).illForce.fml, ((OptionContext)_localctx).t2.fml); 
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
		public TerminalNode ACHIEVE() { return getToken(AORTAParser.ACHIEVE, 0); }
		public TerminalNode TELL() { return getToken(AORTAParser.TELL, 0); }
		public IllForceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_illForce; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterIllForce(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitIllForce(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitIllForce(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IllForceContext illForce() throws RecognitionException {
		IllForceContext _localctx = new IllForceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_illForce);
		try {
			setState(112);
			switch (_input.LA(1)) {
			case TELL:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); match(TELL);
				 ((IllForceContext)_localctx).fml =  new Struct("tell"); 
				}
				break;
			case ACHIEVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(110); match(ACHIEVE);
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
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode TRUE() { return getToken(AORTAParser.TRUE, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode NOT() { return getToken(AORTAParser.NOT, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public FormulasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterFormulas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitFormulas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitFormulas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulasContext formulas() throws RecognitionException {
		FormulasContext _localctx = new FormulasContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formulas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(114); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  ((FormulasContext)_localctx).formula.fml; 
				}
				break;

			case 2:
				{
				setState(117); match(TRUE);
				 ((FormulasContext)_localctx).fml =  new TrueFormula(); 
				}
				break;

			case 3:
				{
				setState(119); ((FormulasContext)_localctx).formula = formula();
				setState(120); match(COMMA);
				setState(121); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(((FormulasContext)_localctx).formula.fml, ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 4:
				{
				setState(124); match(NOT);
				setState(125); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  new NegatedFormula(((FormulasContext)_localctx).formula.fml); 
				}
				break;

			case 5:
				{
				setState(128); match(NOT);
				setState(129); ((FormulasContext)_localctx).formula = formula();
				setState(130); match(COMMA);
				setState(131); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(new NegatedFormula(((FormulasContext)_localctx).formula.fml), ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 6:
				{
				setState(134); match(NOT);
				setState(135); match(START);
				setState(136); ((FormulasContext)_localctx).fmls = formulas();
				setState(137); match(END);
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
		public TerminalNode GOAL() { return getToken(AORTAParser.GOAL, 0); }
		public TerminalNode OPT() { return getToken(AORTAParser.OPT, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode CAP() { return getToken(AORTAParser.CAP, 0); }
		public TerminalNode BEL() { return getToken(AORTAParser.BEL, 0); }
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode ORG() { return getToken(AORTAParser.ORG, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitFormula(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitFormula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			switch (_input.LA(1)) {
			case OPT:
				{
				setState(142); match(OPT);
				setState(143); match(START);
				setState(144); ((FormulaContext)_localctx).prolog = prolog();
				setState(145); match(END);
				 ((FormulaContext)_localctx).fml =  new OptionFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case BEL:
				{
				setState(148); match(BEL);
				setState(149); match(START);
				setState(150); ((FormulaContext)_localctx).prolog = prolog();
				setState(151); match(END);
				 ((FormulaContext)_localctx).fml =  new BeliefFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case GOAL:
				{
				setState(154); match(GOAL);
				setState(155); match(START);
				setState(156); ((FormulaContext)_localctx).prolog = prolog();
				setState(157); match(END);
				 ((FormulaContext)_localctx).fml =  new GoalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case ORG:
				{
				setState(160); match(ORG);
				setState(161); match(START);
				setState(162); ((FormulaContext)_localctx).prolog = prolog();
				setState(163); match(END);
				 ((FormulaContext)_localctx).fml =  new OrganizationalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case CAP:
				{
				setState(166); match(CAP);
				setState(167); match(START);
				setState(168); ((FormulaContext)_localctx).term = term();
				setState(169); match(END);
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
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode SEND() { return getToken(AORTAParser.SEND, 0); }
		public TerminalNode COMMIT() { return getToken(AORTAParser.COMMIT, 0); }
		public TerminalNode DROP() { return getToken(AORTAParser.DROP, 0); }
		public TerminalNode ENACT() { return getToken(AORTAParser.ENACT, 0); }
		public TerminalNode DEACT() { return getToken(AORTAParser.DEACT, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_action);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			switch (_input.LA(1)) {
			case ENACT:
				{
				setState(174); match(ENACT);
				setState(175); match(START);
				setState(176); ((ActionContext)_localctx).pl = term();
				setState(177); match(END);
				 ((ActionContext)_localctx).aa =  new EnactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DEACT:
				{
				setState(180); match(DEACT);
				setState(181); match(START);
				setState(182); ((ActionContext)_localctx).pl = term();
				setState(183); match(END);
				 ((ActionContext)_localctx).aa =  new DeactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case COMMIT:
				{
				setState(186); match(COMMIT);
				setState(187); match(START);
				setState(188); ((ActionContext)_localctx).pl = term();
				setState(189); match(END);
				 ((ActionContext)_localctx).aa =  new CommitAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DROP:
				{
				setState(192); match(DROP);
				setState(193); match(START);
				setState(194); ((ActionContext)_localctx).pl = term();
				setState(195); match(END);
				 ((ActionContext)_localctx).aa =  new DropAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case SEND:
				{
				setState(198); match(SEND);
				setState(199); match(START);
				setState(200); ((ActionContext)_localctx).ag = term();
				setState(201); match(COMMA);
				setState(202); ((ActionContext)_localctx).fml = formula();
				setState(203); match(END);
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterProlog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitProlog(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitProlog(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_prolog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); ((PrologContext)_localctx).prolog2 = prolog2();
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
		public TermBuilderContext termBuilder() {
			return getRuleContext(TermBuilderContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(AORTAParser.SEMICOLON, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public Prolog2Context prolog2() {
			return getRuleContext(Prolog2Context.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode UNARY_OP() { return getToken(AORTAParser.UNARY_OP, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public Prolog2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterProlog2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitProlog2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitProlog2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Prolog2Context prolog2() throws RecognitionException {
		Prolog2Context _localctx = new Prolog2Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_prolog2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(211); match(COMMA);
				setState(212); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(215); match(SEMICOLON);
				setState(216); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(219); match(START);
				setState(220); ((Prolog2Context)_localctx).pl = prolog2();
				setState(221); match(END);
				setState(222); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(225); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(226); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(229); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(230); prolog2();
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TerminalNode BINARY_OP() { return getToken(AORTAParser.BINARY_OP, 0); }
		public TerminalNode MATH_OP() { return getToken(AORTAParser.MATH_OP, 0); }
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TermBuilderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termBuilder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterTermBuilder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitTermBuilder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitTermBuilder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermBuilderContext termBuilder() throws RecognitionException {
		TermBuilderContext _localctx = new TermBuilderContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_termBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(236); ((TermBuilderContext)_localctx).t3 = ((TermBuilderContext)_localctx).term = term();
				setState(237); match(1);
				setState(238); ((TermBuilderContext)_localctx).t4 = ((TermBuilderContext)_localctx).term = term();
				setState(239); ((TermBuilderContext)_localctx).MATH_OP = match(MATH_OP);
				setState(240); ((TermBuilderContext)_localctx).t5 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct("is", ((TermBuilderContext)_localctx).t3.fml, new Struct((((TermBuilderContext)_localctx).MATH_OP!=null?((TermBuilderContext)_localctx).MATH_OP.getText():null), ((TermBuilderContext)_localctx).t4.fml, ((TermBuilderContext)_localctx).t5.fml)); 
				}
				break;

			case 2:
				{
				setState(243); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 3:
				{
				setState(246); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(247); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(248); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
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
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(253); ((TermContext)_localctx).formula = formula();
				((TermContext)_localctx).fml =  new Struct(((ReasoningFormula)((TermContext)_localctx).formula.fml).getType(), ((ReasoningFormula)((TermContext)_localctx).formula.fml).getFormula());
				}
				break;

			case 2:
				{
				setState(256); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 3:
				{
				setState(259); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 4:
				{
				setState(262); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 5:
				{
				setState(265); ((TermContext)_localctx).number = number();
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270); ((AtomContext)_localctx).ATOM = match(ATOM);
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273); ((NumberContext)_localctx).NUMBER = match(NUMBER);
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276); ((VarContext)_localctx).VAR = match(VAR);
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
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode ATOM() { return getToken(AORTAParser.ATOM, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_struct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(279); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(280); match(START);
				setState(281); ((StructContext)_localctx).args = args();
				setState(282); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(285); ((StructContext)_localctx).list = list();
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ArgsContext)_localctx).fml =  new ArrayList<>(); 
			setState(299);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(291); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(294); ((ArgsContext)_localctx).term = term();
				setState(295); match(COMMA);
				setState(296); ((ArgsContext)_localctx).a = args();
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
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TerminalNode PIPE() { return getToken(AORTAParser.PIPE, 0); }
		public TerminalNode START_BRACKET() { return getToken(AORTAParser.START_BRACKET, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public TerminalNode END_BRACKET() { return getToken(AORTAParser.END_BRACKET, 0); }
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_list);
		try {
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(301); match(START_BRACKET);
				setState(302); ((ListContext)_localctx).listContents = listContents();
				setState(303); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(306); match(START_BRACKET);
				setState(307); ((ListContext)_localctx).t1 = term();
				setState(308); match(PIPE);
				setState(309); ((ListContext)_localctx).t2 = term();
				setState(310); match(END_BRACKET);
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
		public ListItemContext listItem() {
			return getRuleContext(ListItemContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public ListContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listContents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterListContents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitListContents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitListContents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContentsContext listContents() throws RecognitionException {
		ListContentsContext _localctx = new ListContentsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_listContents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ListContentsContext)_localctx).fml =  new Struct(); 
			setState(324);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(316); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(319); ((ListContentsContext)_localctx).listItem = listItem();
				setState(320); match(COMMA);
				setState(321); ((ListContentsContext)_localctx).lc = listContents();
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
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitListItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitListItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_listItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326); ((ListItemContext)_localctx).prolog2 = prolog2();
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\61\u014c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3;\n\3\r\3\16\3<\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6Q\n\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6[\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6m\n\6\3\7\3\7\3\7\3\7\5\7s\n\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\b\u008f\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\t\u00af\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\n\u00d1\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u00ed\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00fe\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\5\16\u010f\n\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0123"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u012e\n\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u013c\n\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0147\n\25\3\26\3\26"+
		"\3\26\3\26\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\u0159"+
		"\2,\3\2\2\2\4\63\3\2\2\2\6>\3\2\2\2\bE\3\2\2\2\nM\3\2\2\2\fr\3\2\2\2\16"+
		"\u008e\3\2\2\2\20\u00ae\3\2\2\2\22\u00d0\3\2\2\2\24\u00d2\3\2\2\2\26\u00ec"+
		"\3\2\2\2\30\u00fd\3\2\2\2\32\u010e\3\2\2\2\34\u0110\3\2\2\2\36\u0113\3"+
		"\2\2\2 \u0116\3\2\2\2\"\u0122\3\2\2\2$\u0124\3\2\2\2&\u013b\3\2\2\2(\u013d"+
		"\3\2\2\2*\u0148\3\2\2\2,-\b\2\1\2-.\5\4\3\2./\b\2\1\2/\60\3\2\2\2\60\61"+
		"\7\2\2\3\61\62\b\2\1\2\62\3\3\2\2\2\63:\b\3\1\2\64\65\5\6\4\2\65\66\b"+
		"\3\1\2\66;\3\2\2\2\678\5\b\5\289\b\3\1\29;\3\2\2\2:\64\3\2\2\2:\67\3\2"+
		"\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\5\3\2\2\2>?\7\b\2\2?@\5\16\b\2@A\7"+
		"\4\2\2AB\5\4\3\2BC\7\5\2\2CD\b\4\1\2D\7\3\2\2\2EF\5\n\6\2FG\7\22\2\2G"+
		"H\5\16\b\2HI\7\t\2\2IJ\5\22\n\2JK\7\25\2\2KL\b\5\1\2L\t\3\2\2\2Ml\b\6"+
		"\1\2NO\7\24\2\2OQ\b\6\1\2PN\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\27\2\2ST\7"+
		"\17\2\2TU\5\32\16\2UV\7\20\2\2VW\b\6\1\2Wm\3\2\2\2XY\7\24\2\2Y[\b\6\1"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7\30\2\2]^\7\17\2\2^_\5\32\16\2_"+
		"`\7\20\2\2`a\b\6\1\2am\3\2\2\2bc\7%\2\2cd\7\17\2\2de\5\32\16\2ef\7\21"+
		"\2\2fg\5\f\7\2gh\7\21\2\2hi\5\32\16\2ij\7\20\2\2jk\b\6\1\2km\3\2\2\2l"+
		"P\3\2\2\2lZ\3\2\2\2lb\3\2\2\2m\13\3\2\2\2no\7\31\2\2os\b\7\1\2pq\7\32"+
		"\2\2qs\b\7\1\2rn\3\2\2\2rp\3\2\2\2s\r\3\2\2\2tu\5\20\t\2uv\b\b\1\2v\u008f"+
		"\3\2\2\2wx\7\'\2\2x\u008f\b\b\1\2yz\5\20\t\2z{\7\21\2\2{|\5\16\b\2|}\b"+
		"\b\1\2}\u008f\3\2\2\2~\177\7\24\2\2\177\u0080\5\20\t\2\u0080\u0081\b\b"+
		"\1\2\u0081\u008f\3\2\2\2\u0082\u0083\7\24\2\2\u0083\u0084\5\20\t\2\u0084"+
		"\u0085\7\21\2\2\u0085\u0086\5\16\b\2\u0086\u0087\b\b\1\2\u0087\u008f\3"+
		"\2\2\2\u0088\u0089\7\24\2\2\u0089\u008a\7\17\2\2\u008a\u008b\5\16\b\2"+
		"\u008b\u008c\7\20\2\2\u008c\u008d\b\b\1\2\u008d\u008f\3\2\2\2\u008et\3"+
		"\2\2\2\u008ew\3\2\2\2\u008ey\3\2\2\2\u008e~\3\2\2\2\u008e\u0082\3\2\2"+
		"\2\u008e\u0088\3\2\2\2\u008f\17\3\2\2\2\u0090\u0091\7\33\2\2\u0091\u0092"+
		"\7\17\2\2\u0092\u0093\5\24\13\2\u0093\u0094\7\20\2\2\u0094\u0095\b\t\1"+
		"\2\u0095\u00af\3\2\2\2\u0096\u0097\7\34\2\2\u0097\u0098\7\17\2\2\u0098"+
		"\u0099\5\24\13\2\u0099\u009a\7\20\2\2\u009a\u009b\b\t\1\2\u009b\u00af"+
		"\3\2\2\2\u009c\u009d\7\35\2\2\u009d\u009e\7\17\2\2\u009e\u009f\5\24\13"+
		"\2\u009f\u00a0\7\20\2\2\u00a0\u00a1\b\t\1\2\u00a1\u00af\3\2\2\2\u00a2"+
		"\u00a3\7\36\2\2\u00a3\u00a4\7\17\2\2\u00a4\u00a5\5\24\13\2\u00a5\u00a6"+
		"\7\20\2\2\u00a6\u00a7\b\t\1\2\u00a7\u00af\3\2\2\2\u00a8\u00a9\7\37\2\2"+
		"\u00a9\u00aa\7\17\2\2\u00aa\u00ab\5\32\16\2\u00ab\u00ac\7\20\2\2\u00ac"+
		"\u00ad\b\t\1\2\u00ad\u00af\3\2\2\2\u00ae\u0090\3\2\2\2\u00ae\u0096\3\2"+
		"\2\2\u00ae\u009c\3\2\2\2\u00ae\u00a2\3\2\2\2\u00ae\u00a8\3\2\2\2\u00af"+
		"\21\3\2\2\2\u00b0\u00b1\7\"\2\2\u00b1\u00b2\7\17\2\2\u00b2\u00b3\5\32"+
		"\16\2\u00b3\u00b4\7\20\2\2\u00b4\u00b5\b\n\1\2\u00b5\u00d1\3\2\2\2\u00b6"+
		"\u00b7\7#\2\2\u00b7\u00b8\7\17\2\2\u00b8\u00b9\5\32\16\2\u00b9\u00ba\7"+
		"\20\2\2\u00ba\u00bb\b\n\1\2\u00bb\u00d1\3\2\2\2\u00bc\u00bd\7$\2\2\u00bd"+
		"\u00be\7\17\2\2\u00be\u00bf\5\32\16\2\u00bf\u00c0\7\20\2\2\u00c0\u00c1"+
		"\b\n\1\2\u00c1\u00d1\3\2\2\2\u00c2\u00c3\7&\2\2\u00c3\u00c4\7\17\2\2\u00c4"+
		"\u00c5\5\32\16\2\u00c5\u00c6\7\20\2\2\u00c6\u00c7\b\n\1\2\u00c7\u00d1"+
		"\3\2\2\2\u00c8\u00c9\7%\2\2\u00c9\u00ca\7\17\2\2\u00ca\u00cb\5\32\16\2"+
		"\u00cb\u00cc\7\21\2\2\u00cc\u00cd\5\20\t\2\u00cd\u00ce\7\20\2\2\u00ce"+
		"\u00cf\b\n\1\2\u00cf\u00d1\3\2\2\2\u00d0\u00b0\3\2\2\2\u00d0\u00b6\3\2"+
		"\2\2\u00d0\u00bc\3\2\2\2\u00d0\u00c2\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d1"+
		"\23\3\2\2\2\u00d2\u00d3\5\26\f\2\u00d3\u00d4\b\13\1\2\u00d4\25\3\2\2\2"+
		"\u00d5\u00d6\7\21\2\2\u00d6\u00d7\5\26\f\2\u00d7\u00d8\b\f\1\2\u00d8\u00ed"+
		"\3\2\2\2\u00d9\u00da\7\23\2\2\u00da\u00db\5\26\f\2\u00db\u00dc\b\f\1\2"+
		"\u00dc\u00ed\3\2\2\2\u00dd\u00de\7\17\2\2\u00de\u00df\5\26\f\2\u00df\u00e0"+
		"\7\20\2\2\u00e0\u00e1\5\26\f\2\u00e1\u00e2\b\f\1\2\u00e2\u00ed\3\2\2\2"+
		"\u00e3\u00e4\7-\2\2\u00e4\u00e5\5\26\f\2\u00e5\u00e6\b\f\1\2\u00e6\u00ed"+
		"\3\2\2\2\u00e7\u00e8\5\30\r\2\u00e8\u00e9\5\26\f\2\u00e9\u00ea\b\f\1\2"+
		"\u00ea\u00ed\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00d5\3\2\2\2\u00ec\u00d9"+
		"\3\2\2\2\u00ec\u00dd\3\2\2\2\u00ec\u00e3\3\2\2\2\u00ec\u00e7\3\2\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ed\27\3\2\2\2\u00ee\u00ef\5\32\16\2\u00ef\u00f0\7\3"+
		"\2\2\u00f0\u00f1\5\32\16\2\u00f1\u00f2\7+\2\2\u00f2\u00f3\5\32\16\2\u00f3"+
		"\u00f4\b\r\1\2\u00f4\u00fe\3\2\2\2\u00f5\u00f6\5\32\16\2\u00f6\u00f7\b"+
		"\r\1\2\u00f7\u00fe\3\2\2\2\u00f8\u00f9\5\32\16\2\u00f9\u00fa\7,\2\2\u00fa"+
		"\u00fb\5\32\16\2\u00fb\u00fc\b\r\1\2\u00fc\u00fe\3\2\2\2\u00fd\u00ee\3"+
		"\2\2\2\u00fd\u00f5\3\2\2\2\u00fd\u00f8\3\2\2\2\u00fe\31\3\2\2\2\u00ff"+
		"\u0100\5\20\t\2\u0100\u0101\b\16\1\2\u0101\u010f\3\2\2\2\u0102\u0103\5"+
		"\"\22\2\u0103\u0104\b\16\1\2\u0104\u010f\3\2\2\2\u0105\u0106\5\34\17\2"+
		"\u0106\u0107\b\16\1\2\u0107\u010f\3\2\2\2\u0108\u0109\5 \21\2\u0109\u010a"+
		"\b\16\1\2\u010a\u010f\3\2\2\2\u010b\u010c\5\36\20\2\u010c\u010d\b\16\1"+
		"\2\u010d\u010f\3\2\2\2\u010e\u00ff\3\2\2\2\u010e\u0102\3\2\2\2\u010e\u0105"+
		"\3\2\2\2\u010e\u0108\3\2\2\2\u010e\u010b\3\2\2\2\u010f\33\3\2\2\2\u0110"+
		"\u0111\7(\2\2\u0111\u0112\b\17\1\2\u0112\35\3\2\2\2\u0113\u0114\7)\2\2"+
		"\u0114\u0115\b\20\1\2\u0115\37\3\2\2\2\u0116\u0117\7*\2\2\u0117\u0118"+
		"\b\21\1\2\u0118!\3\2\2\2\u0119\u011a\7(\2\2\u011a\u011b\7\17\2\2\u011b"+
		"\u011c\5$\23\2\u011c\u011d\7\20\2\2\u011d\u011e\b\22\1\2\u011e\u0123\3"+
		"\2\2\2\u011f\u0120\5&\24\2\u0120\u0121\b\22\1\2\u0121\u0123\3\2\2\2\u0122"+
		"\u0119\3\2\2\2\u0122\u011f\3\2\2\2\u0123#\3\2\2\2\u0124\u012d\b\23\1\2"+
		"\u0125\u0126\5\32\16\2\u0126\u0127\b\23\1\2\u0127\u012e\3\2\2\2\u0128"+
		"\u0129\5\32\16\2\u0129\u012a\7\21\2\2\u012a\u012b\5$\23\2\u012b\u012c"+
		"\b\23\1\2\u012c\u012e\3\2\2\2\u012d\u0125\3\2\2\2\u012d\u0128\3\2\2\2"+
		"\u012e%\3\2\2\2\u012f\u0130\7\6\2\2\u0130\u0131\5(\25\2\u0131\u0132\7"+
		"\7\2\2\u0132\u0133\b\24\1\2\u0133\u013c\3\2\2\2\u0134\u0135\7\6\2\2\u0135"+
		"\u0136\5\32\16\2\u0136\u0137\7\26\2\2\u0137\u0138\5\32\16\2\u0138\u0139"+
		"\7\7\2\2\u0139\u013a\b\24\1\2\u013a\u013c\3\2\2\2\u013b\u012f\3\2\2\2"+
		"\u013b\u0134\3\2\2\2\u013c\'\3\2\2\2\u013d\u0146\b\25\1\2\u013e\u013f"+
		"\5*\26\2\u013f\u0140\b\25\1\2\u0140\u0147\3\2\2\2\u0141\u0142\5*\26\2"+
		"\u0142\u0143\7\21\2\2\u0143\u0144\5(\25\2\u0144\u0145\b\25\1\2\u0145\u0147"+
		"\3\2\2\2\u0146\u013e\3\2\2\2\u0146\u0141\3\2\2\2\u0147)\3\2\2\2\u0148"+
		"\u0149\5\26\f\2\u0149\u014a\b\26\1\2\u014a+\3\2\2\2\22:<PZlr\u008e\u00ae"+
		"\u00d0\u00ec\u00fd\u010e\u0122\u012d\u013b\u0146";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}