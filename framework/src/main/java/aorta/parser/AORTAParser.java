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
		START_BLOCK=1, END_BLOCK=2, START_BRACKET=3, END_BRACKET=4, EXECUTE=5, 
		INIT_BLOCK=6, ORGANIZATION=7, PATH=8, TYPE=9, ORGANIZATION_TYPE=10, STRATEGY=11, 
		OPT_BLOCK=12, ACT_BLOCK=13, COORDINATION_BLOCK=14, EQUALS=15, START=16, 
		END=17, COMMA=18, COLON=19, SEMICOLON=20, PLUS=21, MINUS=22, NOT=23, OPT=24, 
		BEL=25, GOAL=26, ORG=27, CONSIDER=28, DISREGARD=29, ENACT=30, DEACT=31, 
		COMMIT=32, SEND=33, SENDONCE=34, DROP=35, TRUE=36, ATOM=37, NUMBER=38, 
		VAR=39, UNARY_OP=40, BINARY_OP=41, CLASSNAME=42, FILEPATH=43, WS=44;
	public static final String[] tokenNames = {
		"<INVALID>", "'{'", "'}'", "'['", "']'", "'=>'", "'init'", "'organization'", 
		"'path'", "'type'", "ORGANIZATION_TYPE", "'strategy'", "'options'", "'actions'", 
		"'coordination'", "'='", "'('", "')'", "','", "':'", "';'", "'+'", "'-'", 
		"'~'", "'opt'", "'bel'", "'goal'", "'org'", "'consider'", "'disregard'", 
		"'enact'", "'deact'", "'commit'", "'send'", "'sendonce'", "'drop'", "'true'", 
		"ATOM", "NUMBER", "VAR", "'\\+'", "BINARY_OP", "CLASSNAME", "FILEPATH", 
		"WS"
	};
	public static final int
		RULE_aortaAgent = 0, RULE_init = 1, RULE_strategy = 2, RULE_optionRules = 3, 
		RULE_opts = 4, RULE_opt = 5, RULE_actionRules = 6, RULE_acts = 7, RULE_act = 8, 
		RULE_coordinationRules = 9, RULE_coords = 10, RULE_coord = 11, RULE_changes = 12, 
		RULE_change = 13, RULE_sendAction = 14, RULE_formulas = 15, RULE_formula = 16, 
		RULE_optAction = 17, RULE_actAction = 18, RULE_prolog = 19, RULE_prolog2 = 20, 
		RULE_termBuilder = 21, RULE_term = 22, RULE_atom = 23, RULE_number = 24, 
		RULE_var = 25, RULE_struct = 26, RULE_args = 27, RULE_list = 28, RULE_listContents = 29, 
		RULE_listItem = 30;
	public static final String[] ruleNames = {
		"aortaAgent", "init", "strategy", "optionRules", "opts", "opt", "actionRules", 
		"acts", "act", "coordinationRules", "coords", "coord", "changes", "change", 
		"sendAction", "formulas", "formula", "optAction", "actAction", "prolog", 
		"prolog2", "termBuilder", "term", "atom", "number", "var", "struct", "args", 
		"list", "listContents", "listItem"
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
		public InitContext init;
		public OptionRulesContext optionRules;
		public ActionRulesContext actionRules;
		public CoordinationRulesContext coordinationRules;
		public OptionRulesContext optionRules() {
			return getRuleContext(OptionRulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(AORTAParser.EOF, 0); }
		public CoordinationRulesContext coordinationRules() {
			return getRuleContext(CoordinationRulesContext.class,0);
		}
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
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
	}

	public final AortaAgentContext aortaAgent(String name) throws RecognitionException {
		AortaAgentContext _localctx = new AortaAgentContext(_ctx, getState(), name);
		enterRule(_localctx, 0, RULE_aortaAgent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

				   Initialization init = new Initialization();
				   List<OptionRule> optRules = null;
				   List<ActionRule> actRules = new ArrayList<>();
				   List<CoordinationRule> coordRules = null;
				   
			setState(66);
			_la = _input.LA(1);
			if (_la==INIT_BLOCK) {
				{
				setState(63); ((AortaAgentContext)_localctx).init = init();
				init = ((AortaAgentContext)_localctx).init.initialization;
				}
			}

			setState(71);
			_la = _input.LA(1);
			if (_la==OPT_BLOCK) {
				{
				setState(68); ((AortaAgentContext)_localctx).optionRules = optionRules();
				optRules = ((AortaAgentContext)_localctx).optionRules.rules;
				}
			}

			setState(76);
			_la = _input.LA(1);
			if (_la==ACT_BLOCK) {
				{
				setState(73); ((AortaAgentContext)_localctx).actionRules = actionRules();
				actRules = ((AortaAgentContext)_localctx).actionRules.rules;
				}
			}

			setState(81);
			_la = _input.LA(1);
			if (_la==COORDINATION_BLOCK) {
				{
				setState(78); ((AortaAgentContext)_localctx).coordinationRules = coordinationRules();
				coordRules = ((AortaAgentContext)_localctx).coordinationRules.rules;
				}
			}

			setState(83); match(EOF);

				 
				 ((AortaAgentContext)_localctx).agent =  new AgentBuilder(name, init, optRules, actRules, coordRules);  
				 
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

	public static class InitContext extends ParserRuleContext {
		public Initialization initialization;
		public StrategyContext strategy;
		public StrategyContext strategy() {
			return getRuleContext(StrategyContext.class,0);
		}
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public TerminalNode START_BLOCK() { return getToken(AORTAParser.START_BLOCK, 0); }
		public TerminalNode INIT_BLOCK() { return getToken(AORTAParser.INIT_BLOCK, 0); }
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitInit(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			           ((InitContext)_localctx).initialization =  new Initialization(); 
			          
			setState(87); match(INIT_BLOCK);
			setState(88); match(START_BLOCK);
			setState(92);
			_la = _input.LA(1);
			if (_la==STRATEGY) {
				{
				setState(89); ((InitContext)_localctx).strategy = strategy();
				 _localctx.initialization.strategy = ((InitContext)_localctx).strategy.clazz; 
				}
			}

			setState(94); match(END_BLOCK);
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

	public static class StrategyContext extends ParserRuleContext {
		public String clazz;
		public Token CLASSNAME;
		public TerminalNode CLASSNAME() { return getToken(AORTAParser.CLASSNAME, 0); }
		public TerminalNode STRATEGY() { return getToken(AORTAParser.STRATEGY, 0); }
		public TerminalNode COLON() { return getToken(AORTAParser.COLON, 0); }
		public StrategyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strategy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterStrategy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitStrategy(this);
		}
	}

	public final StrategyContext strategy() throws RecognitionException {
		StrategyContext _localctx = new StrategyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_strategy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(STRATEGY);
			setState(97); match(COLON);
			setState(98); ((StrategyContext)_localctx).CLASSNAME = match(CLASSNAME);
			 ((StrategyContext)_localctx).clazz =  (((StrategyContext)_localctx).CLASSNAME!=null?((StrategyContext)_localctx).CLASSNAME.getText():null); 
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

	public static class OptionRulesContext extends ParserRuleContext {
		public List<OptionRule> rules;
		public OptsContext opts;
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public TerminalNode OPT_BLOCK() { return getToken(AORTAParser.OPT_BLOCK, 0); }
		public OptsContext opts() {
			return getRuleContext(OptsContext.class,0);
		}
		public TerminalNode START_BLOCK() { return getToken(AORTAParser.START_BLOCK, 0); }
		public OptionRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterOptionRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitOptionRules(this);
		}
	}

	public final OptionRulesContext optionRules() throws RecognitionException {
		OptionRulesContext _localctx = new OptionRulesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(OPT_BLOCK);
			setState(102); match(START_BLOCK);
			setState(103); ((OptionRulesContext)_localctx).opts = opts();
			setState(104); match(END_BLOCK);
			 ((OptionRulesContext)_localctx).rules =  ((OptionRulesContext)_localctx).opts.rules; 
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

	public static class OptsContext extends ParserRuleContext {
		public List<OptionRule> rules;
		public OptContext opt;
		public OptsContext o;
		public OptContext opt() {
			return getRuleContext(OptContext.class,0);
		}
		public OptsContext opts() {
			return getRuleContext(OptsContext.class,0);
		}
		public OptsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterOpts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitOpts(this);
		}
	}

	public final OptsContext opts() throws RecognitionException {
		OptsContext _localctx = new OptsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_opts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((OptsContext)_localctx).rules =  new ArrayList<>(); 
			setState(113);
			switch (_input.LA(1)) {
			case END_BLOCK:
				{
				}
				break;
			case START_BRACKET:
				{
				setState(109); ((OptsContext)_localctx).opt = opt();
				setState(110); ((OptsContext)_localctx).o = opts();
				 _localctx.rules.add(((OptsContext)_localctx).opt.rule); _localctx.rules.addAll(((OptsContext)_localctx).o.rules); 
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

	public static class OptContext extends ParserRuleContext {
		public OptionRule rule;
		public FormulasContext formulas;
		public OptActionContext optAction;
		public TerminalNode EXECUTE() { return getToken(AORTAParser.EXECUTE, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode START_BRACKET() { return getToken(AORTAParser.START_BRACKET, 0); }
		public TerminalNode END_BRACKET() { return getToken(AORTAParser.END_BRACKET, 0); }
		public OptActionContext optAction() {
			return getRuleContext(OptActionContext.class,0);
		}
		public OptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterOpt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitOpt(this);
		}
	}

	public final OptContext opt() throws RecognitionException {
		OptContext _localctx = new OptContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_opt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(START_BRACKET);
			setState(116); ((OptContext)_localctx).formulas = formulas();
			setState(117); match(END_BRACKET);
			setState(118); match(EXECUTE);
			setState(119); ((OptContext)_localctx).optAction = optAction();
			 ((OptContext)_localctx).rule =  new OptionRule(((OptContext)_localctx).formulas.fml, ((OptContext)_localctx).optAction.oa); 
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
		public TerminalNode ACT_BLOCK() { return getToken(AORTAParser.ACT_BLOCK, 0); }
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public TerminalNode START_BLOCK() { return getToken(AORTAParser.START_BLOCK, 0); }
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
	}

	public final ActionRulesContext actionRules() throws RecognitionException {
		ActionRulesContext _localctx = new ActionRulesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_actionRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(ACT_BLOCK);
			setState(123); match(START_BLOCK);
			setState(124); ((ActionRulesContext)_localctx).acts = acts();
			setState(125); match(END_BLOCK);
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
	}

	public final ActsContext acts() throws RecognitionException {
		ActsContext _localctx = new ActsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_acts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ActsContext)_localctx).rules =  new ArrayList<>(); 
			setState(136);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(129); ((ActsContext)_localctx).act = act();
				 _localctx.rules.add(((ActsContext)_localctx).act.rule); 
				}
				break;

			case 2:
				{
				setState(132); ((ActsContext)_localctx).act = act();
				setState(133); ((ActsContext)_localctx).a = acts();
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
		public FormulasContext formulas;
		public ActActionContext actAction;
		public TerminalNode EXECUTE() { return getToken(AORTAParser.EXECUTE, 0); }
		public ActActionContext actAction() {
			return getRuleContext(ActActionContext.class,0);
		}
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode START_BRACKET() { return getToken(AORTAParser.START_BRACKET, 0); }
		public TerminalNode END_BRACKET() { return getToken(AORTAParser.END_BRACKET, 0); }
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
	}

	public final ActContext act() throws RecognitionException {
		ActContext _localctx = new ActContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_act);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(START_BRACKET);
			setState(139); ((ActContext)_localctx).formulas = formulas();
			setState(140); match(END_BRACKET);
			setState(141); match(EXECUTE);
			setState(142); ((ActContext)_localctx).actAction = actAction();
			 ((ActContext)_localctx).rule =  new ActionRule(((ActContext)_localctx).formulas.fml, ((ActContext)_localctx).actAction.aa); 
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

	public static class CoordinationRulesContext extends ParserRuleContext {
		public List<CoordinationRule> rules;
		public CoordsContext coords;
		public TerminalNode COORDINATION_BLOCK() { return getToken(AORTAParser.COORDINATION_BLOCK, 0); }
		public TerminalNode END_BLOCK() { return getToken(AORTAParser.END_BLOCK, 0); }
		public TerminalNode START_BLOCK() { return getToken(AORTAParser.START_BLOCK, 0); }
		public CoordsContext coords() {
			return getRuleContext(CoordsContext.class,0);
		}
		public CoordinationRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coordinationRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterCoordinationRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitCoordinationRules(this);
		}
	}

	public final CoordinationRulesContext coordinationRules() throws RecognitionException {
		CoordinationRulesContext _localctx = new CoordinationRulesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_coordinationRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(COORDINATION_BLOCK);
			setState(146); match(START_BLOCK);
			setState(147); ((CoordinationRulesContext)_localctx).coords = coords();
			setState(148); match(END_BLOCK);
			 ((CoordinationRulesContext)_localctx).rules =  ((CoordinationRulesContext)_localctx).coords.rules; 
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

	public static class CoordsContext extends ParserRuleContext {
		public List<CoordinationRule> rules;
		public CoordContext coord;
		public CoordsContext c;
		public CoordContext coord() {
			return getRuleContext(CoordContext.class,0);
		}
		public CoordsContext coords() {
			return getRuleContext(CoordsContext.class,0);
		}
		public CoordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterCoords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitCoords(this);
		}
	}

	public final CoordsContext coords() throws RecognitionException {
		CoordsContext _localctx = new CoordsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_coords);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((CoordsContext)_localctx).rules =  new ArrayList<>(); 
			setState(159);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(152); ((CoordsContext)_localctx).coord = coord();
				setState(153); ((CoordsContext)_localctx).c = coords();
				 _localctx.rules.add(((CoordsContext)_localctx).coord.rule); _localctx.rules.addAll(((CoordsContext)_localctx).c.rules); 
				}
				break;

			case 2:
				{
				setState(156); ((CoordsContext)_localctx).coord = coord();
				 _localctx.rules.add(((CoordsContext)_localctx).coord.rule); 
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

	public static class CoordContext extends ParserRuleContext {
		public CoordinationRule rule;
		public ChangesContext changes;
		public FormulasContext formulas;
		public SendActionContext sendAction;
		public ChangesContext changes() {
			return getRuleContext(ChangesContext.class,0);
		}
		public TerminalNode EXECUTE() { return getToken(AORTAParser.EXECUTE, 0); }
		public SendActionContext sendAction() {
			return getRuleContext(SendActionContext.class,0);
		}
		public TerminalNode END_BRACKET(int i) {
			return getToken(AORTAParser.END_BRACKET, i);
		}
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public TerminalNode START_BRACKET(int i) {
			return getToken(AORTAParser.START_BRACKET, i);
		}
		public List<TerminalNode> START_BRACKET() { return getTokens(AORTAParser.START_BRACKET); }
		public TerminalNode COLON() { return getToken(AORTAParser.COLON, 0); }
		public List<TerminalNode> END_BRACKET() { return getTokens(AORTAParser.END_BRACKET); }
		public CoordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_coord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterCoord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitCoord(this);
		}
	}

	public final CoordContext coord() throws RecognitionException {
		CoordContext _localctx = new CoordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_coord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(START_BRACKET);
			setState(162); ((CoordContext)_localctx).changes = changes();
			setState(163); match(END_BRACKET);
			setState(164); match(COLON);
			setState(165); match(START_BRACKET);
			setState(166); ((CoordContext)_localctx).formulas = formulas();
			setState(167); match(END_BRACKET);
			setState(168); match(EXECUTE);
			setState(169); ((CoordContext)_localctx).sendAction = sendAction();
			 ((CoordContext)_localctx).rule =  new CoordinationRule(((CoordContext)_localctx).changes.cts, ((CoordContext)_localctx).formulas.fml, ((CoordContext)_localctx).sendAction.sa); 
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

	public static class ChangesContext extends ParserRuleContext {
		public List<ChangeTerm> cts;
		public ChangeContext change;
		public ChangesContext c;
		public ChangesContext changes() {
			return getRuleContext(ChangesContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public ChangeContext change() {
			return getRuleContext(ChangeContext.class,0);
		}
		public ChangesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_changes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterChanges(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitChanges(this);
		}
	}

	public final ChangesContext changes() throws RecognitionException {
		ChangesContext _localctx = new ChangesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_changes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ChangesContext)_localctx).cts =  new ArrayList<>(); 
			setState(181);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(173); ((ChangesContext)_localctx).change = change();
				 _localctx.cts.add(((ChangesContext)_localctx).change.ct); 
				}
				break;

			case 2:
				{
				setState(176); ((ChangesContext)_localctx).change = change();
				setState(177); match(COMMA);
				setState(178); ((ChangesContext)_localctx).c = changes();
				 _localctx.cts.add(((ChangesContext)_localctx).change.ct); _localctx.cts.addAll(((ChangesContext)_localctx).c.cts); 
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

	public static class ChangeContext extends ParserRuleContext {
		public ChangeTerm ct;
		public FormulaContext formula;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(AORTAParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(AORTAParser.PLUS, 0); }
		public ChangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_change; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitChange(this);
		}
	}

	public final ChangeContext change() throws RecognitionException {
		ChangeContext _localctx = new ChangeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_change);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 boolean addition = false; 
			setState(187);
			switch (_input.LA(1)) {
			case PLUS:
				{
				setState(184); match(PLUS);
				addition=true;
				}
				break;
			case MINUS:
				{
				setState(186); match(MINUS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(189); ((ChangeContext)_localctx).formula = formula();
			 ((ChangeContext)_localctx).ct =  new ChangeTerm(addition, ((ChangeContext)_localctx).formula.fml); 
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

	public static class SendActionContext extends ParserRuleContext {
		public SendAction sa;
		public VarContext var;
		public FormulaContext formula;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public TerminalNode COMMA() { return getToken(AORTAParser.COMMA, 0); }
		public TerminalNode SEND() { return getToken(AORTAParser.SEND, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public SendActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sendAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterSendAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitSendAction(this);
		}
	}

	public final SendActionContext sendAction() throws RecognitionException {
		SendActionContext _localctx = new SendActionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_sendAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(SEND);
			setState(193); match(START);
			setState(194); ((SendActionContext)_localctx).var = var();
			setState(195); match(COMMA);
			setState(196); ((SendActionContext)_localctx).formula = formula();
			setState(197); match(END);
			 ((SendActionContext)_localctx).sa =  new SendAction(((SendActionContext)_localctx).var.fml, ((SendActionContext)_localctx).formula.fml); 
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
	}

	public final FormulasContext formulas() throws RecognitionException {
		FormulasContext _localctx = new FormulasContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_formulas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(200); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  ((FormulasContext)_localctx).formula.fml; 
				}
				break;

			case 2:
				{
				setState(203); match(TRUE);
				 ((FormulasContext)_localctx).fml =  new TrueFormula(); 
				}
				break;

			case 3:
				{
				setState(205); ((FormulasContext)_localctx).formula = formula();
				setState(206); match(COMMA);
				setState(207); ((FormulasContext)_localctx).fmls = formulas();
				 ((FormulasContext)_localctx).fml =  new ConjunctFormula(((FormulasContext)_localctx).formula.fml, ((FormulasContext)_localctx).fmls.fml); 
				}
				break;

			case 4:
				{
				setState(210); match(NOT);
				setState(211); ((FormulasContext)_localctx).formula = formula();
				 ((FormulasContext)_localctx).fml =  new NegatedFormula(((FormulasContext)_localctx).formula.fml); 
				}
				break;

			case 5:
				{
				setState(214); match(NOT);
				setState(215); match(START);
				setState(216); ((FormulasContext)_localctx).fmls = formulas();
				setState(217); match(END);
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
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			switch (_input.LA(1)) {
			case OPT:
				{
				setState(222); match(OPT);
				setState(223); match(START);
				setState(224); ((FormulaContext)_localctx).prolog = prolog();
				setState(225); match(END);
				 ((FormulaContext)_localctx).fml =  new OptionFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case BEL:
				{
				setState(228); match(BEL);
				setState(229); match(START);
				setState(230); ((FormulaContext)_localctx).prolog = prolog();
				setState(231); match(END);
				 ((FormulaContext)_localctx).fml =  new BeliefFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case GOAL:
				{
				setState(234); match(GOAL);
				setState(235); match(START);
				setState(236); ((FormulaContext)_localctx).prolog = prolog();
				setState(237); match(END);
				 ((FormulaContext)_localctx).fml =  new GoalFormula(((FormulaContext)_localctx).prolog.fml); 
				}
				break;
			case ORG:
				{
				setState(240); match(ORG);
				setState(241); match(START);
				setState(242); ((FormulaContext)_localctx).prolog = prolog();
				setState(243); match(END);
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

	public static class OptActionContext extends ParserRuleContext {
		public OptAction oa;
		public PrologContext prolog;
		public TerminalNode CONSIDER() { return getToken(AORTAParser.CONSIDER, 0); }
		public TerminalNode START() { return getToken(AORTAParser.START, 0); }
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode DISREGARD() { return getToken(AORTAParser.DISREGARD, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public OptActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterOptAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitOptAction(this);
		}
	}

	public final OptActionContext optAction() throws RecognitionException {
		OptActionContext _localctx = new OptActionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_optAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			switch (_input.LA(1)) {
			case CONSIDER:
				{
				setState(248); match(CONSIDER);
				setState(249); match(START);
				setState(250); ((OptActionContext)_localctx).prolog = prolog();
				setState(251); match(END);
				 ((OptActionContext)_localctx).oa =  new ConsiderAction((Struct) ((OptActionContext)_localctx).prolog.fml); 
				}
				break;
			case DISREGARD:
				{
				setState(254); match(DISREGARD);
				setState(255); match(START);
				setState(256); ((OptActionContext)_localctx).prolog = prolog();
				setState(257); match(END);
				 ((OptActionContext)_localctx).oa =  new DisregardAction((Struct) ((OptActionContext)_localctx).prolog.fml); 
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

	public static class ActActionContext extends ParserRuleContext {
		public ActAction aa;
		public TermContext pl;
		public VarContext ag;
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
		public TerminalNode SENDONCE() { return getToken(AORTAParser.SENDONCE, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode DROP() { return getToken(AORTAParser.DROP, 0); }
		public TerminalNode ENACT() { return getToken(AORTAParser.ENACT, 0); }
		public TerminalNode DEACT() { return getToken(AORTAParser.DEACT, 0); }
		public TerminalNode END() { return getToken(AORTAParser.END, 0); }
		public ActActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).enterActAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AORTAListener ) ((AORTAListener)listener).exitActAction(this);
		}
	}

	public final ActActionContext actAction() throws RecognitionException {
		ActActionContext _localctx = new ActActionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_actAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			switch (_input.LA(1)) {
			case ENACT:
				{
				setState(262); match(ENACT);
				setState(263); match(START);
				setState(264); ((ActActionContext)_localctx).pl = term();
				setState(265); match(END);
				 ((ActActionContext)_localctx).aa =  new EnactAction(((ActActionContext)_localctx).pl.fml); 
				}
				break;
			case DEACT:
				{
				setState(268); match(DEACT);
				setState(269); match(START);
				setState(270); ((ActActionContext)_localctx).pl = term();
				setState(271); match(END);
				 ((ActActionContext)_localctx).aa =  new DeactAction(((ActActionContext)_localctx).pl.fml); 
				}
				break;
			case COMMIT:
				{
				setState(274); match(COMMIT);
				setState(275); match(START);
				setState(276); ((ActActionContext)_localctx).pl = term();
				setState(277); match(END);
				 ((ActActionContext)_localctx).aa =  new CommitAction(((ActActionContext)_localctx).pl.fml); 
				}
				break;
			case DROP:
				{
				setState(280); match(DROP);
				setState(281); match(START);
				setState(282); ((ActActionContext)_localctx).pl = term();
				setState(283); match(END);
				 ((ActActionContext)_localctx).aa =  new DropAction(((ActActionContext)_localctx).pl.fml); 
				}
				break;
			case SEND:
				{
				setState(286); match(SEND);
				setState(287); match(START);
				setState(288); ((ActActionContext)_localctx).ag = var();
				setState(289); match(COMMA);
				setState(290); ((ActActionContext)_localctx).fml = formula();
				setState(291); match(END);
				 ((ActActionContext)_localctx).aa =  new SendAction(((ActActionContext)_localctx).ag.fml, ((ActActionContext)_localctx).fml.fml); 
				}
				break;
			case SENDONCE:
				{
				setState(294); match(SENDONCE);
				setState(295); match(START);
				setState(296); ((ActActionContext)_localctx).ag = var();
				setState(297); match(COMMA);
				setState(298); ((ActActionContext)_localctx).fml = formula();
				setState(299); match(END);
				 ((ActActionContext)_localctx).aa =  new SendOnceAction(((ActActionContext)_localctx).ag.fml, ((ActActionContext)_localctx).fml.fml); 
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
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_prolog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304); ((PrologContext)_localctx).prolog2 = prolog2();
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
	}

	public final Prolog2Context prolog2() throws RecognitionException {
		Prolog2Context _localctx = new Prolog2Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_prolog2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(307); match(COMMA);
				setState(308); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(311); match(SEMICOLON);
				setState(312); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(315); match(START);
				setState(316); ((Prolog2Context)_localctx).pl = prolog2();
				setState(317); match(END);
				setState(318); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(321); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(322); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(325); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(326); prolog2();
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
		public TermContext term;
		public TermContext t1;
		public Token BINARY_OP;
		public TermContext t2;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TerminalNode BINARY_OP() { return getToken(AORTAParser.BINARY_OP, 0); }
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
	}

	public final TermBuilderContext termBuilder() throws RecognitionException {
		TermBuilderContext _localctx = new TermBuilderContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_termBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(332); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 2:
				{
				setState(335); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(336); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(337); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
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
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(342); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 2:
				{
				setState(345); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 3:
				{
				setState(348); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 4:
				{
				setState(351); ((TermContext)_localctx).number = number();
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
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356); ((AtomContext)_localctx).ATOM = match(ATOM);
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
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359); ((NumberContext)_localctx).NUMBER = match(NUMBER);
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
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362); ((VarContext)_localctx).VAR = match(VAR);
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
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_struct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(365); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(366); match(START);
				setState(367); ((StructContext)_localctx).args = args();
				setState(368); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(371); ((StructContext)_localctx).list = list();
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
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ArgsContext)_localctx).fml =  new ArrayList<>(); 
			setState(385);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(377); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(380); ((ArgsContext)_localctx).term = term();
				setState(381); match(COMMA);
				setState(382); ((ArgsContext)_localctx).a = args();
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
		public TerminalNode START_BRACKET() { return getToken(AORTAParser.START_BRACKET, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public TerminalNode END_BRACKET() { return getToken(AORTAParser.END_BRACKET, 0); }
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
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); match(START_BRACKET);
			setState(388); ((ListContext)_localctx).listContents = listContents();
			setState(389); match(END_BRACKET);
			 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
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
	}

	public final ListContentsContext listContents() throws RecognitionException {
		ListContentsContext _localctx = new ListContentsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_listContents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ListContentsContext)_localctx).fml =  new Struct(); 
			setState(401);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(393); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(396); ((ListContentsContext)_localctx).listItem = listItem();
				setState(397); match(COMMA);
				setState(398); ((ListContentsContext)_localctx).lc = listContents();
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
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_listItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403); ((ListItemContext)_localctx).prolog2 = prolog2();
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3.\u0199\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\5\2E\n\2\3\2\3\2\3\2\5\2J\n\2\3\2\3\2\3\2\5\2O\n\2\3\2\3"+
		"\2\3\2\5\2T\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3_\n\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6"+
		"t\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u008b\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a2\n\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00b8\n\16\3\17\3\17\3\17\3\17\5\17\u00be\n"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\5\21\u00df\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u00f9\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0107\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u0131\n\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u014d\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u0157\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\5\30\u0165\n\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0179\n\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0184\n\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0194"+
		"\n\37\3 \3 \3 \3 \2!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>\2\2\u019c\2@\3\2\2\2\4X\3\2\2\2\6b\3\2\2\2\bg\3\2\2\2\n"+
		"m\3\2\2\2\fu\3\2\2\2\16|\3\2\2\2\20\u0082\3\2\2\2\22\u008c\3\2\2\2\24"+
		"\u0093\3\2\2\2\26\u0099\3\2\2\2\30\u00a3\3\2\2\2\32\u00ae\3\2\2\2\34\u00b9"+
		"\3\2\2\2\36\u00c2\3\2\2\2 \u00de\3\2\2\2\"\u00f8\3\2\2\2$\u0106\3\2\2"+
		"\2&\u0130\3\2\2\2(\u0132\3\2\2\2*\u014c\3\2\2\2,\u0156\3\2\2\2.\u0164"+
		"\3\2\2\2\60\u0166\3\2\2\2\62\u0169\3\2\2\2\64\u016c\3\2\2\2\66\u0178\3"+
		"\2\2\28\u017a\3\2\2\2:\u0185\3\2\2\2<\u018a\3\2\2\2>\u0195\3\2\2\2@D\b"+
		"\2\1\2AB\5\4\3\2BC\b\2\1\2CE\3\2\2\2DA\3\2\2\2DE\3\2\2\2EI\3\2\2\2FG\5"+
		"\b\5\2GH\b\2\1\2HJ\3\2\2\2IF\3\2\2\2IJ\3\2\2\2JN\3\2\2\2KL\5\16\b\2LM"+
		"\b\2\1\2MO\3\2\2\2NK\3\2\2\2NO\3\2\2\2OS\3\2\2\2PQ\5\24\13\2QR\b\2\1\2"+
		"RT\3\2\2\2SP\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\2\2\3VW\b\2\1\2W\3\3\2\2"+
		"\2XY\b\3\1\2YZ\7\b\2\2Z^\7\3\2\2[\\\5\6\4\2\\]\b\3\1\2]_\3\2\2\2^[\3\2"+
		"\2\2^_\3\2\2\2_`\3\2\2\2`a\7\4\2\2a\5\3\2\2\2bc\7\r\2\2cd\7\25\2\2de\7"+
		",\2\2ef\b\4\1\2f\7\3\2\2\2gh\7\16\2\2hi\7\3\2\2ij\5\n\6\2jk\7\4\2\2kl"+
		"\b\5\1\2l\t\3\2\2\2ms\b\6\1\2nt\3\2\2\2op\5\f\7\2pq\5\n\6\2qr\b\6\1\2"+
		"rt\3\2\2\2sn\3\2\2\2so\3\2\2\2t\13\3\2\2\2uv\7\5\2\2vw\5 \21\2wx\7\6\2"+
		"\2xy\7\7\2\2yz\5$\23\2z{\b\7\1\2{\r\3\2\2\2|}\7\17\2\2}~\7\3\2\2~\177"+
		"\5\20\t\2\177\u0080\7\4\2\2\u0080\u0081\b\b\1\2\u0081\17\3\2\2\2\u0082"+
		"\u008a\b\t\1\2\u0083\u0084\5\22\n\2\u0084\u0085\b\t\1\2\u0085\u008b\3"+
		"\2\2\2\u0086\u0087\5\22\n\2\u0087\u0088\5\20\t\2\u0088\u0089\b\t\1\2\u0089"+
		"\u008b\3\2\2\2\u008a\u0083\3\2\2\2\u008a\u0086\3\2\2\2\u008b\21\3\2\2"+
		"\2\u008c\u008d\7\5\2\2\u008d\u008e\5 \21\2\u008e\u008f\7\6\2\2\u008f\u0090"+
		"\7\7\2\2\u0090\u0091\5&\24\2\u0091\u0092\b\n\1\2\u0092\23\3\2\2\2\u0093"+
		"\u0094\7\20\2\2\u0094\u0095\7\3\2\2\u0095\u0096\5\26\f\2\u0096\u0097\7"+
		"\4\2\2\u0097\u0098\b\13\1\2\u0098\25\3\2\2\2\u0099\u00a1\b\f\1\2\u009a"+
		"\u009b\5\30\r\2\u009b\u009c\5\26\f\2\u009c\u009d\b\f\1\2\u009d\u00a2\3"+
		"\2\2\2\u009e\u009f\5\30\r\2\u009f\u00a0\b\f\1\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009a\3\2\2\2\u00a1\u009e\3\2\2\2\u00a2\27\3\2\2\2\u00a3\u00a4\7\5\2"+
		"\2\u00a4\u00a5\5\32\16\2\u00a5\u00a6\7\6\2\2\u00a6\u00a7\7\25\2\2\u00a7"+
		"\u00a8\7\5\2\2\u00a8\u00a9\5 \21\2\u00a9\u00aa\7\6\2\2\u00aa\u00ab\7\7"+
		"\2\2\u00ab\u00ac\5\36\20\2\u00ac\u00ad\b\r\1\2\u00ad\31\3\2\2\2\u00ae"+
		"\u00b7\b\16\1\2\u00af\u00b0\5\34\17\2\u00b0\u00b1\b\16\1\2\u00b1\u00b8"+
		"\3\2\2\2\u00b2\u00b3\5\34\17\2\u00b3\u00b4\7\24\2\2\u00b4\u00b5\5\32\16"+
		"\2\u00b5\u00b6\b\16\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7"+
		"\u00b2\3\2\2\2\u00b8\33\3\2\2\2\u00b9\u00bd\b\17\1\2\u00ba\u00bb\7\27"+
		"\2\2\u00bb\u00be\b\17\1\2\u00bc\u00be\7\30\2\2\u00bd\u00ba\3\2\2\2\u00bd"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\5\"\22\2\u00c0\u00c1\b"+
		"\17\1\2\u00c1\35\3\2\2\2\u00c2\u00c3\7#\2\2\u00c3\u00c4\7\22\2\2\u00c4"+
		"\u00c5\5\64\33\2\u00c5\u00c6\7\24\2\2\u00c6\u00c7\5\"\22\2\u00c7\u00c8"+
		"\7\23\2\2\u00c8\u00c9\b\20\1\2\u00c9\37\3\2\2\2\u00ca\u00cb\5\"\22\2\u00cb"+
		"\u00cc\b\21\1\2\u00cc\u00df\3\2\2\2\u00cd\u00ce\7&\2\2\u00ce\u00df\b\21"+
		"\1\2\u00cf\u00d0\5\"\22\2\u00d0\u00d1\7\24\2\2\u00d1\u00d2\5 \21\2\u00d2"+
		"\u00d3\b\21\1\2\u00d3\u00df\3\2\2\2\u00d4\u00d5\7\31\2\2\u00d5\u00d6\5"+
		"\"\22\2\u00d6\u00d7\b\21\1\2\u00d7\u00df\3\2\2\2\u00d8\u00d9\7\31\2\2"+
		"\u00d9\u00da\7\22\2\2\u00da\u00db\5 \21\2\u00db\u00dc\7\23\2\2\u00dc\u00dd"+
		"\b\21\1\2\u00dd\u00df\3\2\2\2\u00de\u00ca\3\2\2\2\u00de\u00cd\3\2\2\2"+
		"\u00de\u00cf\3\2\2\2\u00de\u00d4\3\2\2\2\u00de\u00d8\3\2\2\2\u00df!\3"+
		"\2\2\2\u00e0\u00e1\7\32\2\2\u00e1\u00e2\7\22\2\2\u00e2\u00e3\5(\25\2\u00e3"+
		"\u00e4\7\23\2\2\u00e4\u00e5\b\22\1\2\u00e5\u00f9\3\2\2\2\u00e6\u00e7\7"+
		"\33\2\2\u00e7\u00e8\7\22\2\2\u00e8\u00e9\5(\25\2\u00e9\u00ea\7\23\2\2"+
		"\u00ea\u00eb\b\22\1\2\u00eb\u00f9\3\2\2\2\u00ec\u00ed\7\34\2\2\u00ed\u00ee"+
		"\7\22\2\2\u00ee\u00ef\5(\25\2\u00ef\u00f0\7\23\2\2\u00f0\u00f1\b\22\1"+
		"\2\u00f1\u00f9\3\2\2\2\u00f2\u00f3\7\35\2\2\u00f3\u00f4\7\22\2\2\u00f4"+
		"\u00f5\5(\25\2\u00f5\u00f6\7\23\2\2\u00f6\u00f7\b\22\1\2\u00f7\u00f9\3"+
		"\2\2\2\u00f8\u00e0\3\2\2\2\u00f8\u00e6\3\2\2\2\u00f8\u00ec\3\2\2\2\u00f8"+
		"\u00f2\3\2\2\2\u00f9#\3\2\2\2\u00fa\u00fb\7\36\2\2\u00fb\u00fc\7\22\2"+
		"\2\u00fc\u00fd\5(\25\2\u00fd\u00fe\7\23\2\2\u00fe\u00ff\b\23\1\2\u00ff"+
		"\u0107\3\2\2\2\u0100\u0101\7\37\2\2\u0101\u0102\7\22\2\2\u0102\u0103\5"+
		"(\25\2\u0103\u0104\7\23\2\2\u0104\u0105\b\23\1\2\u0105\u0107\3\2\2\2\u0106"+
		"\u00fa\3\2\2\2\u0106\u0100\3\2\2\2\u0107%\3\2\2\2\u0108\u0109\7 \2\2\u0109"+
		"\u010a\7\22\2\2\u010a\u010b\5.\30\2\u010b\u010c\7\23\2\2\u010c\u010d\b"+
		"\24\1\2\u010d\u0131\3\2\2\2\u010e\u010f\7!\2\2\u010f\u0110\7\22\2\2\u0110"+
		"\u0111\5.\30\2\u0111\u0112\7\23\2\2\u0112\u0113\b\24\1\2\u0113\u0131\3"+
		"\2\2\2\u0114\u0115\7\"\2\2\u0115\u0116\7\22\2\2\u0116\u0117\5.\30\2\u0117"+
		"\u0118\7\23\2\2\u0118\u0119\b\24\1\2\u0119\u0131\3\2\2\2\u011a\u011b\7"+
		"%\2\2\u011b\u011c\7\22\2\2\u011c\u011d\5.\30\2\u011d\u011e\7\23\2\2\u011e"+
		"\u011f\b\24\1\2\u011f\u0131\3\2\2\2\u0120\u0121\7#\2\2\u0121\u0122\7\22"+
		"\2\2\u0122\u0123\5\64\33\2\u0123\u0124\7\24\2\2\u0124\u0125\5\"\22\2\u0125"+
		"\u0126\7\23\2\2\u0126\u0127\b\24\1\2\u0127\u0131\3\2\2\2\u0128\u0129\7"+
		"$\2\2\u0129\u012a\7\22\2\2\u012a\u012b\5\64\33\2\u012b\u012c\7\24\2\2"+
		"\u012c\u012d\5\"\22\2\u012d\u012e\7\23\2\2\u012e\u012f\b\24\1\2\u012f"+
		"\u0131\3\2\2\2\u0130\u0108\3\2\2\2\u0130\u010e\3\2\2\2\u0130\u0114\3\2"+
		"\2\2\u0130\u011a\3\2\2\2\u0130\u0120\3\2\2\2\u0130\u0128\3\2\2\2\u0131"+
		"\'\3\2\2\2\u0132\u0133\5*\26\2\u0133\u0134\b\25\1\2\u0134)\3\2\2\2\u0135"+
		"\u0136\7\24\2\2\u0136\u0137\5*\26\2\u0137\u0138\b\26\1\2\u0138\u014d\3"+
		"\2\2\2\u0139\u013a\7\26\2\2\u013a\u013b\5*\26\2\u013b\u013c\b\26\1\2\u013c"+
		"\u014d\3\2\2\2\u013d\u013e\7\22\2\2\u013e\u013f\5*\26\2\u013f\u0140\7"+
		"\23\2\2\u0140\u0141\5*\26\2\u0141\u0142\b\26\1\2\u0142\u014d\3\2\2\2\u0143"+
		"\u0144\7*\2\2\u0144\u0145\5*\26\2\u0145\u0146\b\26\1\2\u0146\u014d\3\2"+
		"\2\2\u0147\u0148\5,\27\2\u0148\u0149\5*\26\2\u0149\u014a\b\26\1\2\u014a"+
		"\u014d\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u0135\3\2\2\2\u014c\u0139\3\2"+
		"\2\2\u014c\u013d\3\2\2\2\u014c\u0143\3\2\2\2\u014c\u0147\3\2\2\2\u014c"+
		"\u014b\3\2\2\2\u014d+\3\2\2\2\u014e\u014f\5.\30\2\u014f\u0150\b\27\1\2"+
		"\u0150\u0157\3\2\2\2\u0151\u0152\5.\30\2\u0152\u0153\7+\2\2\u0153\u0154"+
		"\5.\30\2\u0154\u0155\b\27\1\2\u0155\u0157\3\2\2\2\u0156\u014e\3\2\2\2"+
		"\u0156\u0151\3\2\2\2\u0157-\3\2\2\2\u0158\u0159\5\66\34\2\u0159\u015a"+
		"\b\30\1\2\u015a\u0165\3\2\2\2\u015b\u015c\5\60\31\2\u015c\u015d\b\30\1"+
		"\2\u015d\u0165\3\2\2\2\u015e\u015f\5\64\33\2\u015f\u0160\b\30\1\2\u0160"+
		"\u0165\3\2\2\2\u0161\u0162\5\62\32\2\u0162\u0163\b\30\1\2\u0163\u0165"+
		"\3\2\2\2\u0164\u0158\3\2\2\2\u0164\u015b\3\2\2\2\u0164\u015e\3\2\2\2\u0164"+
		"\u0161\3\2\2\2\u0165/\3\2\2\2\u0166\u0167\7\'\2\2\u0167\u0168\b\31\1\2"+
		"\u0168\61\3\2\2\2\u0169\u016a\7(\2\2\u016a\u016b\b\32\1\2\u016b\63\3\2"+
		"\2\2\u016c\u016d\7)\2\2\u016d\u016e\b\33\1\2\u016e\65\3\2\2\2\u016f\u0170"+
		"\7\'\2\2\u0170\u0171\7\22\2\2\u0171\u0172\58\35\2\u0172\u0173\7\23\2\2"+
		"\u0173\u0174\b\34\1\2\u0174\u0179\3\2\2\2\u0175\u0176\5:\36\2\u0176\u0177"+
		"\b\34\1\2\u0177\u0179\3\2\2\2\u0178\u016f\3\2\2\2\u0178\u0175\3\2\2\2"+
		"\u0179\67\3\2\2\2\u017a\u0183\b\35\1\2\u017b\u017c\5.\30\2\u017c\u017d"+
		"\b\35\1\2\u017d\u0184\3\2\2\2\u017e\u017f\5.\30\2\u017f\u0180\7\24\2\2"+
		"\u0180\u0181\58\35\2\u0181\u0182\b\35\1\2\u0182\u0184\3\2\2\2\u0183\u017b"+
		"\3\2\2\2\u0183\u017e\3\2\2\2\u01849\3\2\2\2\u0185\u0186\7\5\2\2\u0186"+
		"\u0187\5<\37\2\u0187\u0188\7\6\2\2\u0188\u0189\b\36\1\2\u0189;\3\2\2\2"+
		"\u018a\u0193\b\37\1\2\u018b\u018c\5> \2\u018c\u018d\b\37\1\2\u018d\u0194"+
		"\3\2\2\2\u018e\u018f\5> \2\u018f\u0190\7\24\2\2\u0190\u0191\5<\37\2\u0191"+
		"\u0192\b\37\1\2\u0192\u0194\3\2\2\2\u0193\u018b\3\2\2\2\u0193\u018e\3"+
		"\2\2\2\u0194=\3\2\2\2\u0195\u0196\5*\26\2\u0196\u0197\b \1\2\u0197?\3"+
		"\2\2\2\26DINS^s\u008a\u00a1\u00b7\u00bd\u00de\u00f8\u0106\u0130\u014c"+
		"\u0156\u0164\u0178\u0183\u0193";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}