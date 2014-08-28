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
		T__0=1, START_BLOCK=2, END_BLOCK=3, START_BRACKET=4, END_BRACKET=5, EXECUTE=6, 
		INIT_BLOCK=7, ORGANIZATION=8, PATH=9, TYPE=10, ORGANIZATION_TYPE=11, STRATEGY=12, 
		OPT_BLOCK=13, ACT_BLOCK=14, COORDINATION_BLOCK=15, EQUALS=16, START=17, 
		END=18, COMMA=19, COLON=20, SEMICOLON=21, NOT=22, FULLSTOP=23, PIPE=24, 
		ROLE=25, OBJ=26, TELL=27, ACHIEVE=28, OPT=29, BEL=30, GOAL=31, ORG=32, 
		CONSIDER=33, DISREGARD=34, ENACT=35, DEACT=36, COMMIT=37, SEND=38, DROP=39, 
		TRUE=40, ATOM=41, NUMBER=42, VAR=43, MATH_OP=44, BINARY_OP=45, UNARY_OP=46, 
		CLASSNAME=47, FILEPATH=48, COMMENT=49, WS=50;
	public static final String[] tokenNames = {
		"<INVALID>", "' is '", "'{'", "'}'", "'['", "']'", "'=>'", "'init'", "'organization'", 
		"'path'", "'type'", "ORGANIZATION_TYPE", "'strategy'", "'options'", "'actions'", 
		"'coordination'", "'='", "'('", "')'", "','", "':'", "';'", "'~'", "'.'", 
		"'|'", "'role'", "'obj'", "'tell'", "'achieve'", "'opt'", "'bel'", "'goal'", 
		"'org'", "'consider'", "'disregard'", "'enact'", "'deact'", "'commit'", 
		"'send'", "'drop'", "'true'", "ATOM", "NUMBER", "VAR", "MATH_OP", "BINARY_OP", 
		"'\\+'", "CLASSNAME", "FILEPATH", "COMMENT", "WS"
	};
	public static final int
		RULE_aortaAgent = 0, RULE_actionRules = 1, RULE_acts = 2, RULE_act = 3, 
		RULE_option = 4, RULE_illForce = 5, RULE_formulas = 6, RULE_formula = 7, 
		RULE_action = 8, RULE_prolog = 9, RULE_prolog2 = 10, RULE_termBuilder = 11, 
		RULE_term = 12, RULE_atom = 13, RULE_number = 14, RULE_var = 15, RULE_struct = 16, 
		RULE_args = 17, RULE_list = 18, RULE_listContents = 19, RULE_listItem = 20;
	public static final String[] ruleNames = {
		"aortaAgent", "actionRules", "acts", "act", "option", "illForce", "formulas", 
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
		public ActionRulesContext actionRules;
		public TerminalNode EOF() { return getToken(AORTAParser.EOF, 0); }
		public ActionRulesContext actionRules() {
			return getRuleContext(ActionRulesContext.class,0);
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
				   List<ActionRule> actRules = new ArrayList<>();
				   
			{
			setState(43); ((AortaAgentContext)_localctx).actionRules = actionRules();
			actRules = ((AortaAgentContext)_localctx).actionRules.rules;
			}
			setState(46); match(EOF);

				 
				 ((AortaAgentContext)_localctx).agent =  new AgentBuilder(name, init, actRules);  
				 
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

	public static class ActionRulesContext extends ParserRuleContext {
		public List<ActionRule> rules;
		public ActsContext acts;
		public ActsContext acts() {
			return getRuleContext(ActsContext.class,0);
		}
		public ActionRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterActionRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitActionRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitActionRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionRulesContext actionRules() throws RecognitionException {
		ActionRulesContext _localctx = new ActionRulesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_actionRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); ((ActionRulesContext)_localctx).acts = acts();
			 ((ActionRulesContext)_localctx).rules =  ((ActionRulesContext)_localctx).acts.rules; 
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

	public static class ActsContext extends ParserRuleContext {
		public List<ActionRule> rules;
		public ActContext act;
		public ActsContext a;
		public ActsContext acts() {
			return getRuleContext(ActsContext.class,0);
		}
		public ActContext act() {
			return getRuleContext(ActContext.class,0);
		}
		public ActsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterActs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitActs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitActs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActsContext acts() throws RecognitionException {
		ActsContext _localctx = new ActsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_acts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ActsContext)_localctx).rules =  new ArrayList<>(); 
			setState(60);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(53); ((ActsContext)_localctx).act = act();
				 _localctx.rules.add(((ActsContext)_localctx).act.rule); 
				}
				break;

			case 2:
				{
				setState(56); ((ActsContext)_localctx).act = act();
				setState(57); ((ActsContext)_localctx).a = acts();
				 _localctx.rules.add(((ActsContext)_localctx).act.rule); _localctx.rules.addAll(((ActsContext)_localctx).a.rules); 
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

	public static class ActContext extends ParserRuleContext {
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
		public ActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_act; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AORTAVisitor ) return ((AORTAVisitor<? extends T>)visitor).visitAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActContext act() throws RecognitionException {
		ActContext _localctx = new ActContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_act);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); ((ActContext)_localctx).option = option();
			setState(63); match(COLON);
			setState(64); ((ActContext)_localctx).formulas = formulas();
			setState(65); match(EXECUTE);
			setState(66); ((ActContext)_localctx).action = action();
			setState(67); match(FULLSTOP);
			 ((ActContext)_localctx).rule =  new ActionRule(((ActContext)_localctx).option.fml, ((ActContext)_localctx).formulas.fml, ((ActContext)_localctx).action.aa); 
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
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(73);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(71); match(NOT);
					pos=false;
					}
				}

				setState(75); match(ROLE);
				setState(76); match(START);
				setState(77); ((OptionContext)_localctx).term = term();
				setState(78); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("role", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 2:
				{
				setState(83);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(81); match(NOT);
					pos=false;
					}
				}

				setState(85); match(OBJ);
				setState(86); match(START);
				setState(87); ((OptionContext)_localctx).term = term();
				setState(88); match(END);
				 ((OptionContext)_localctx).fml =  new Struct("obj", ((OptionContext)_localctx).term.fml); if (!pos) { ((OptionContext)_localctx).fml =  new Struct("~", _localctx.fml); } 
				}
				break;

			case 3:
				{
				setState(91); match(SEND);
				setState(92); match(START);
				setState(93); ((OptionContext)_localctx).t1 = ((OptionContext)_localctx).term = term();
				setState(94); match(COMMA);
				setState(95); ((OptionContext)_localctx).illForce = illForce();
				setState(96); match(COMMA);
				setState(97); ((OptionContext)_localctx).t2 = ((OptionContext)_localctx).term = term();
				setState(98); match(END);
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
			setState(107);
			switch (_input.LA(1)) {
			case TELL:
				enterOuterAlt(_localctx, 1);
				{
				setState(103); match(TELL);
				 ((IllForceContext)_localctx).fml =  new Struct("tell"); 
				}
				break;
			case ACHIEVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(105); match(ACHIEVE);
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
			setState(135);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(109); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  ((FormulasContext)_localctx).formula.fml; 
				}
				break;

			case 2:
				{
				setState(112); match(TRUE);
				 ((FormulasContext)_localctx).fml =  new TrueFormula(); 
				}
				break;

			case 3:
				{
				setState(114); ((FormulasContext)_localctx).formula = formula();
				setState(115); match(COMMA);
				setState(116); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(((FormulasContext)_localctx).formula.fml, ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 4:
				{
				setState(119); match(NOT);
				setState(120); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  new NegatedFormula(((FormulasContext)_localctx).formula.fml); 
				}
				break;

			case 5:
				{
				setState(123); match(NOT);
				setState(124); ((FormulasContext)_localctx).formula = formula();
				setState(125); match(COMMA);
				setState(126); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(new NegatedFormula(((FormulasContext)_localctx).formula.fml), ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 6:
				{
				setState(129); match(NOT);
				setState(130); match(START);
				setState(131); ((FormulasContext)_localctx).fmls = formulas();
				setState(132); match(END);
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
		public ReasoningFormula fml;
		public PrologContext prolog;
		public TerminalNode GOAL() { return getToken(AORTAParser.GOAL, 0); }
		public TerminalNode OPT() { return getToken(AORTAParser.OPT, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
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
			setState(161);
			switch (_input.LA(1)) {
			case OPT:
				{
				setState(137); match(OPT);
				setState(138); match(START);
				setState(139); ((FormulaContext)_localctx).prolog = prolog();
				setState(140); match(END);
				 ((FormulaContext)_localctx).fml =  new OptionFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case BEL:
				{
				setState(143); match(BEL);
				setState(144); match(START);
				setState(145); ((FormulaContext)_localctx).prolog = prolog();
				setState(146); match(END);
				 ((FormulaContext)_localctx).fml =  new BeliefFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case GOAL:
				{
				setState(149); match(GOAL);
				setState(150); match(START);
				setState(151); ((FormulaContext)_localctx).prolog = prolog();
				setState(152); match(END);
				 ((FormulaContext)_localctx).fml =  new GoalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case ORG:
				{
				setState(155); match(ORG);
				setState(156); match(START);
				setState(157); ((FormulaContext)_localctx).prolog = prolog();
				setState(158); match(END);
				 ((FormulaContext)_localctx).fml =  new OrganizationalFormula(((FormulaContext)_localctx).prolog.fml); 
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
			setState(195);
			switch (_input.LA(1)) {
			case ENACT:
				{
				setState(163); match(ENACT);
				setState(164); match(START);
				setState(165); ((ActionContext)_localctx).pl = term();
				setState(166); match(END);
				 ((ActionContext)_localctx).aa =  new EnactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DEACT:
				{
				setState(169); match(DEACT);
				setState(170); match(START);
				setState(171); ((ActionContext)_localctx).pl = term();
				setState(172); match(END);
				 ((ActionContext)_localctx).aa =  new DeactAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case COMMIT:
				{
				setState(175); match(COMMIT);
				setState(176); match(START);
				setState(177); ((ActionContext)_localctx).pl = term();
				setState(178); match(END);
				 ((ActionContext)_localctx).aa =  new CommitAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case DROP:
				{
				setState(181); match(DROP);
				setState(182); match(START);
				setState(183); ((ActionContext)_localctx).pl = term();
				setState(184); match(END);
				 ((ActionContext)_localctx).aa =  new DropAction(((ActionContext)_localctx).pl.fml); 
				}
				break;
			case SEND:
				{
				setState(187); match(SEND);
				setState(188); match(START);
				setState(189); ((ActionContext)_localctx).ag = term();
				setState(190); match(COMMA);
				setState(191); ((ActionContext)_localctx).fml = formula();
				setState(192); match(END);
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
			setState(197); ((PrologContext)_localctx).prolog2 = prolog2();
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
			setState(223);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(200); match(COMMA);
				setState(201); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(204); match(SEMICOLON);
				setState(205); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(208); match(START);
				setState(209); ((Prolog2Context)_localctx).pl = prolog2();
				setState(210); match(END);
				setState(211); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(214); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(215); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(218); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(219); prolog2();
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
			setState(240);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(225); ((TermBuilderContext)_localctx).t3 = ((TermBuilderContext)_localctx).term = term();
				setState(226); match(1);
				setState(227); ((TermBuilderContext)_localctx).t4 = ((TermBuilderContext)_localctx).term = term();
				setState(228); ((TermBuilderContext)_localctx).MATH_OP = match(MATH_OP);
				setState(229); ((TermBuilderContext)_localctx).t5 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct("is", ((TermBuilderContext)_localctx).t3.fml, new Struct((((TermBuilderContext)_localctx).MATH_OP!=null?((TermBuilderContext)_localctx).MATH_OP.getText():null), ((TermBuilderContext)_localctx).t4.fml, ((TermBuilderContext)_localctx).t5.fml)); 
				}
				break;

			case 2:
				{
				setState(232); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 3:
				{
				setState(235); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(236); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(237); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
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
			setState(257);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(242); ((TermContext)_localctx).formula = formula();
				((TermContext)_localctx).fml =  new Struct(((TermContext)_localctx).formula.fml.getType(), ((TermContext)_localctx).formula.fml.getFormula());
				}
				break;

			case 2:
				{
				setState(245); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 3:
				{
				setState(248); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 4:
				{
				setState(251); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 5:
				{
				setState(254); ((TermContext)_localctx).number = number();
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
			setState(259); ((AtomContext)_localctx).ATOM = match(ATOM);
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
			setState(262); ((NumberContext)_localctx).NUMBER = match(NUMBER);
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
			setState(265); ((VarContext)_localctx).VAR = match(VAR);
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
			setState(277);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(268); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(269); match(START);
				setState(270); ((StructContext)_localctx).args = args();
				setState(271); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(274); ((StructContext)_localctx).list = list();
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
			setState(288);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(280); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(283); ((ArgsContext)_localctx).term = term();
				setState(284); match(COMMA);
				setState(285); ((ArgsContext)_localctx).a = args();
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
			setState(302);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290); match(START_BRACKET);
				setState(291); ((ListContext)_localctx).listContents = listContents();
				setState(292); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295); match(START_BRACKET);
				setState(296); ((ListContext)_localctx).t1 = term();
				setState(297); match(PIPE);
				setState(298); ((ListContext)_localctx).t2 = term();
				setState(299); match(END_BRACKET);
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
			setState(313);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(305); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(308); ((ListContentsContext)_localctx).listItem = listItem();
				setState(309); match(COMMA);
				setState(310); ((ListContentsContext)_localctx).lc = listContents();
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
			setState(315); ((ListItemContext)_localctx).prolog2 = prolog2();
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\64\u0141\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4?\n\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6L\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5"+
		"\6V\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6h\n\6\3\7\3\7\3\7\3\7\5\7n\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\5\b\u008a\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a4\n\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00c6\n\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e2\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00f3\n\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0104\n\16\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u0118\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u0123\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u0131\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u013c\n\25\3\26\3\26\3\26\3\26\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*\2\2\u014c\2,\3\2\2\2\4\63\3\2\2\2\6\66\3\2\2\2\b@\3\2\2"+
		"\2\nH\3\2\2\2\fm\3\2\2\2\16\u0089\3\2\2\2\20\u00a3\3\2\2\2\22\u00c5\3"+
		"\2\2\2\24\u00c7\3\2\2\2\26\u00e1\3\2\2\2\30\u00f2\3\2\2\2\32\u0103\3\2"+
		"\2\2\34\u0105\3\2\2\2\36\u0108\3\2\2\2 \u010b\3\2\2\2\"\u0117\3\2\2\2"+
		"$\u0119\3\2\2\2&\u0130\3\2\2\2(\u0132\3\2\2\2*\u013d\3\2\2\2,-\b\2\1\2"+
		"-.\5\4\3\2./\b\2\1\2/\60\3\2\2\2\60\61\7\2\2\3\61\62\b\2\1\2\62\3\3\2"+
		"\2\2\63\64\5\6\4\2\64\65\b\3\1\2\65\5\3\2\2\2\66>\b\4\1\2\678\5\b\5\2"+
		"89\b\4\1\29?\3\2\2\2:;\5\b\5\2;<\5\6\4\2<=\b\4\1\2=?\3\2\2\2>\67\3\2\2"+
		"\2>:\3\2\2\2?\7\3\2\2\2@A\5\n\6\2AB\7\26\2\2BC\5\16\b\2CD\7\b\2\2DE\5"+
		"\22\n\2EF\7\31\2\2FG\b\5\1\2G\t\3\2\2\2Hg\b\6\1\2IJ\7\30\2\2JL\b\6\1\2"+
		"KI\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\33\2\2NO\7\23\2\2OP\5\32\16\2PQ\7\24"+
		"\2\2QR\b\6\1\2Rh\3\2\2\2ST\7\30\2\2TV\b\6\1\2US\3\2\2\2UV\3\2\2\2VW\3"+
		"\2\2\2WX\7\34\2\2XY\7\23\2\2YZ\5\32\16\2Z[\7\24\2\2[\\\b\6\1\2\\h\3\2"+
		"\2\2]^\7(\2\2^_\7\23\2\2_`\5\32\16\2`a\7\25\2\2ab\5\f\7\2bc\7\25\2\2c"+
		"d\5\32\16\2de\7\24\2\2ef\b\6\1\2fh\3\2\2\2gK\3\2\2\2gU\3\2\2\2g]\3\2\2"+
		"\2h\13\3\2\2\2ij\7\35\2\2jn\b\7\1\2kl\7\36\2\2ln\b\7\1\2mi\3\2\2\2mk\3"+
		"\2\2\2n\r\3\2\2\2op\5\20\t\2pq\b\b\1\2q\u008a\3\2\2\2rs\7*\2\2s\u008a"+
		"\b\b\1\2tu\5\20\t\2uv\7\25\2\2vw\5\16\b\2wx\b\b\1\2x\u008a\3\2\2\2yz\7"+
		"\30\2\2z{\5\20\t\2{|\b\b\1\2|\u008a\3\2\2\2}~\7\30\2\2~\177\5\20\t\2\177"+
		"\u0080\7\25\2\2\u0080\u0081\5\16\b\2\u0081\u0082\b\b\1\2\u0082\u008a\3"+
		"\2\2\2\u0083\u0084\7\30\2\2\u0084\u0085\7\23\2\2\u0085\u0086\5\16\b\2"+
		"\u0086\u0087\7\24\2\2\u0087\u0088\b\b\1\2\u0088\u008a\3\2\2\2\u0089o\3"+
		"\2\2\2\u0089r\3\2\2\2\u0089t\3\2\2\2\u0089y\3\2\2\2\u0089}\3\2\2\2\u0089"+
		"\u0083\3\2\2\2\u008a\17\3\2\2\2\u008b\u008c\7\37\2\2\u008c\u008d\7\23"+
		"\2\2\u008d\u008e\5\24\13\2\u008e\u008f\7\24\2\2\u008f\u0090\b\t\1\2\u0090"+
		"\u00a4\3\2\2\2\u0091\u0092\7 \2\2\u0092\u0093\7\23\2\2\u0093\u0094\5\24"+
		"\13\2\u0094\u0095\7\24\2\2\u0095\u0096\b\t\1\2\u0096\u00a4\3\2\2\2\u0097"+
		"\u0098\7!\2\2\u0098\u0099\7\23\2\2\u0099\u009a\5\24\13\2\u009a\u009b\7"+
		"\24\2\2\u009b\u009c\b\t\1\2\u009c\u00a4\3\2\2\2\u009d\u009e\7\"\2\2\u009e"+
		"\u009f\7\23\2\2\u009f\u00a0\5\24\13\2\u00a0\u00a1\7\24\2\2\u00a1\u00a2"+
		"\b\t\1\2\u00a2\u00a4\3\2\2\2\u00a3\u008b\3\2\2\2\u00a3\u0091\3\2\2\2\u00a3"+
		"\u0097\3\2\2\2\u00a3\u009d\3\2\2\2\u00a4\21\3\2\2\2\u00a5\u00a6\7%\2\2"+
		"\u00a6\u00a7\7\23\2\2\u00a7\u00a8\5\32\16\2\u00a8\u00a9\7\24\2\2\u00a9"+
		"\u00aa\b\n\1\2\u00aa\u00c6\3\2\2\2\u00ab\u00ac\7&\2\2\u00ac\u00ad\7\23"+
		"\2\2\u00ad\u00ae\5\32\16\2\u00ae\u00af\7\24\2\2\u00af\u00b0\b\n\1\2\u00b0"+
		"\u00c6\3\2\2\2\u00b1\u00b2\7\'\2\2\u00b2\u00b3\7\23\2\2\u00b3\u00b4\5"+
		"\32\16\2\u00b4\u00b5\7\24\2\2\u00b5\u00b6\b\n\1\2\u00b6\u00c6\3\2\2\2"+
		"\u00b7\u00b8\7)\2\2\u00b8\u00b9\7\23\2\2\u00b9\u00ba\5\32\16\2\u00ba\u00bb"+
		"\7\24\2\2\u00bb\u00bc\b\n\1\2\u00bc\u00c6\3\2\2\2\u00bd\u00be\7(\2\2\u00be"+
		"\u00bf\7\23\2\2\u00bf\u00c0\5\32\16\2\u00c0\u00c1\7\25\2\2\u00c1\u00c2"+
		"\5\20\t\2\u00c2\u00c3\7\24\2\2\u00c3\u00c4\b\n\1\2\u00c4\u00c6\3\2\2\2"+
		"\u00c5\u00a5\3\2\2\2\u00c5\u00ab\3\2\2\2\u00c5\u00b1\3\2\2\2\u00c5\u00b7"+
		"\3\2\2\2\u00c5\u00bd\3\2\2\2\u00c6\23\3\2\2\2\u00c7\u00c8\5\26\f\2\u00c8"+
		"\u00c9\b\13\1\2\u00c9\25\3\2\2\2\u00ca\u00cb\7\25\2\2\u00cb\u00cc\5\26"+
		"\f\2\u00cc\u00cd\b\f\1\2\u00cd\u00e2\3\2\2\2\u00ce\u00cf\7\27\2\2\u00cf"+
		"\u00d0\5\26\f\2\u00d0\u00d1\b\f\1\2\u00d1\u00e2\3\2\2\2\u00d2\u00d3\7"+
		"\23\2\2\u00d3\u00d4\5\26\f\2\u00d4\u00d5\7\24\2\2\u00d5\u00d6\5\26\f\2"+
		"\u00d6\u00d7\b\f\1\2\u00d7\u00e2\3\2\2\2\u00d8\u00d9\7\60\2\2\u00d9\u00da"+
		"\5\26\f\2\u00da\u00db\b\f\1\2\u00db\u00e2\3\2\2\2\u00dc\u00dd\5\30\r\2"+
		"\u00dd\u00de\5\26\f\2\u00de\u00df\b\f\1\2\u00df\u00e2\3\2\2\2\u00e0\u00e2"+
		"\3\2\2\2\u00e1\u00ca\3\2\2\2\u00e1\u00ce\3\2\2\2\u00e1\u00d2\3\2\2\2\u00e1"+
		"\u00d8\3\2\2\2\u00e1\u00dc\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\27\3\2\2"+
		"\2\u00e3\u00e4\5\32\16\2\u00e4\u00e5\7\3\2\2\u00e5\u00e6\5\32\16\2\u00e6"+
		"\u00e7\7.\2\2\u00e7\u00e8\5\32\16\2\u00e8\u00e9\b\r\1\2\u00e9\u00f3\3"+
		"\2\2\2\u00ea\u00eb\5\32\16\2\u00eb\u00ec\b\r\1\2\u00ec\u00f3\3\2\2\2\u00ed"+
		"\u00ee\5\32\16\2\u00ee\u00ef\7/\2\2\u00ef\u00f0\5\32\16\2\u00f0\u00f1"+
		"\b\r\1\2\u00f1\u00f3\3\2\2\2\u00f2\u00e3\3\2\2\2\u00f2\u00ea\3\2\2\2\u00f2"+
		"\u00ed\3\2\2\2\u00f3\31\3\2\2\2\u00f4\u00f5\5\20\t\2\u00f5\u00f6\b\16"+
		"\1\2\u00f6\u0104\3\2\2\2\u00f7\u00f8\5\"\22\2\u00f8\u00f9\b\16\1\2\u00f9"+
		"\u0104\3\2\2\2\u00fa\u00fb\5\34\17\2\u00fb\u00fc\b\16\1\2\u00fc\u0104"+
		"\3\2\2\2\u00fd\u00fe\5 \21\2\u00fe\u00ff\b\16\1\2\u00ff\u0104\3\2\2\2"+
		"\u0100\u0101\5\36\20\2\u0101\u0102\b\16\1\2\u0102\u0104\3\2\2\2\u0103"+
		"\u00f4\3\2\2\2\u0103\u00f7\3\2\2\2\u0103\u00fa\3\2\2\2\u0103\u00fd\3\2"+
		"\2\2\u0103\u0100\3\2\2\2\u0104\33\3\2\2\2\u0105\u0106\7+\2\2\u0106\u0107"+
		"\b\17\1\2\u0107\35\3\2\2\2\u0108\u0109\7,\2\2\u0109\u010a\b\20\1\2\u010a"+
		"\37\3\2\2\2\u010b\u010c\7-\2\2\u010c\u010d\b\21\1\2\u010d!\3\2\2\2\u010e"+
		"\u010f\7+\2\2\u010f\u0110\7\23\2\2\u0110\u0111\5$\23\2\u0111\u0112\7\24"+
		"\2\2\u0112\u0113\b\22\1\2\u0113\u0118\3\2\2\2\u0114\u0115\5&\24\2\u0115"+
		"\u0116\b\22\1\2\u0116\u0118\3\2\2\2\u0117\u010e\3\2\2\2\u0117\u0114\3"+
		"\2\2\2\u0118#\3\2\2\2\u0119\u0122\b\23\1\2\u011a\u011b\5\32\16\2\u011b"+
		"\u011c\b\23\1\2\u011c\u0123\3\2\2\2\u011d\u011e\5\32\16\2\u011e\u011f"+
		"\7\25\2\2\u011f\u0120\5$\23\2\u0120\u0121\b\23\1\2\u0121\u0123\3\2\2\2"+
		"\u0122\u011a\3\2\2\2\u0122\u011d\3\2\2\2\u0123%\3\2\2\2\u0124\u0125\7"+
		"\6\2\2\u0125\u0126\5(\25\2\u0126\u0127\7\7\2\2\u0127\u0128\b\24\1\2\u0128"+
		"\u0131\3\2\2\2\u0129\u012a\7\6\2\2\u012a\u012b\5\32\16\2\u012b\u012c\7"+
		"\32\2\2\u012c\u012d\5\32\16\2\u012d\u012e\7\7\2\2\u012e\u012f\b\24\1\2"+
		"\u012f\u0131\3\2\2\2\u0130\u0124\3\2\2\2\u0130\u0129\3\2\2\2\u0131\'\3"+
		"\2\2\2\u0132\u013b\b\25\1\2\u0133\u0134\5*\26\2\u0134\u0135\b\25\1\2\u0135"+
		"\u013c\3\2\2\2\u0136\u0137\5*\26\2\u0137\u0138\7\25\2\2\u0138\u0139\5"+
		"(\25\2\u0139\u013a\b\25\1\2\u013a\u013c\3\2\2\2\u013b\u0133\3\2\2\2\u013b"+
		"\u0136\3\2\2\2\u013c)\3\2\2\2\u013d\u013e\5\26\f\2\u013e\u013f\b\26\1"+
		"\2\u013f+\3\2\2\2\21>KUgm\u0089\u00a3\u00c5\u00e1\u00f2\u0103\u0117\u0122"+
		"\u0130\u013b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}