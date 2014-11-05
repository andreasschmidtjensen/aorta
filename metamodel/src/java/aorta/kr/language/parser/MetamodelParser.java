// Generated from C:\Dropbox\code\phd\aorta\metamodel\src\java\aorta\kr\language\parser\MetamodelParser.g4 by ANTLR 4.1
package aorta.kr.language.parser;

	import alice.tuprolog.Number;
	import alice.tuprolog.Struct;
	import alice.tuprolog.Prolog;
	import alice.tuprolog.Term;
	import alice.tuprolog.Theory;
	import alice.tuprolog.Var;

	import aorta.kr.language.model.*;
	
	import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MetamodelParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		END_BRACKET=2, LT=16, CLOSESTRING=29, NUMBER=20, STRLIT=30, SEMICOLON=10, 
		ATOM=19, MATH_OP=22, COLON=9, START_BRACKET=1, ROLES=3, WS=28, COMMA=15, 
		OBJECTIVES=4, IS=18, ENTAILS=8, OBLIGATIONS=6, FULLSTOP=11, DEPENDENCIES=5, 
		START=13, GT=17, PIPE=12, VAR=21, OPENSTRING=25, COMMENT=27, END=14, BINARY_OP=23, 
		STRING=26, RULES=7, UNARY_OP=24;
	public static final String[] tokenNames = {
		"<INVALID>", "'['", "']'", "'ROLES'", "'OBJECTIVES'", "'DEPENDENCIES'", 
		"'OBLIGATIONS'", "'RULES'", "':-'", "':'", "';'", "'.'", "'|'", "'('", 
		"')'", "','", "'<'", "GT", "' is '", "ATOM", "NUMBER", "VAR", "MATH_OP", 
		"BINARY_OP", "'\\+'", "OPENSTRING", "STRING", "COMMENT", "WS", "CLOSESTRING", 
		"STRLIT"
	};
	public static final int
		RULE_metamodel = 0, RULE_roles = 1, RULE_role = 2, RULE_objectiveList = 3, 
		RULE_objectives = 4, RULE_objective = 5, RULE_dependencies = 6, RULE_dependency = 7, 
		RULE_obligations = 8, RULE_obligation = 9, RULE_rules = 10, RULE_krrule = 11, 
		RULE_prolog = 12, RULE_prolog2 = 13, RULE_termBuilder = 14, RULE_term = 15, 
		RULE_string = 16, RULE_atom = 17, RULE_number = 18, RULE_var = 19, RULE_struct = 20, 
		RULE_args = 21, RULE_list = 22, RULE_listContents = 23, RULE_listItem = 24;
	public static final String[] ruleNames = {
		"metamodel", "roles", "role", "objectiveList", "objectives", "objective", 
		"dependencies", "dependency", "obligations", "obligation", "rules", "krrule", 
		"prolog", "prolog2", "termBuilder", "term", "string", "atom", "number", 
		"var", "struct", "args", "list", "listContents", "listItem"
	};

	@Override
	public String getGrammarFileName() { return "MetamodelParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MetamodelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MetamodelContext extends ParserRuleContext {
		public Metamodel mm;
		public RolesContext roles;
		public ObjectivesContext objectives;
		public DependenciesContext dependencies;
		public ObligationsContext obligations;
		public RulesContext rules;
		public DependenciesContext dependencies() {
			return getRuleContext(DependenciesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MetamodelParser.EOF, 0); }
		public RolesContext roles() {
			return getRuleContext(RolesContext.class,0);
		}
		public ObligationsContext obligations() {
			return getRuleContext(ObligationsContext.class,0);
		}
		public ObjectivesContext objectives() {
			return getRuleContext(ObjectivesContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public MetamodelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metamodel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterMetamodel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitMetamodel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitMetamodel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MetamodelContext metamodel() throws RecognitionException {
		MetamodelContext _localctx = new MetamodelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_metamodel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

				   List<Dependency> dependencyList = new ArrayList<>();
				   List<Obligation> obligationList = new ArrayList<>();
				   List<Rule> ruleList = new ArrayList<>();
				  
			setState(51); ((MetamodelContext)_localctx).roles = roles();
			setState(52); ((MetamodelContext)_localctx).objectives = objectives();
			setState(56);
			_la = _input.LA(1);
			if (_la==DEPENDENCIES) {
				{
				setState(53); ((MetamodelContext)_localctx).dependencies = dependencies();
				 dependencyList = ((MetamodelContext)_localctx).dependencies.dependencyList; 
				}
			}

			setState(61);
			_la = _input.LA(1);
			if (_la==OBLIGATIONS) {
				{
				setState(58); ((MetamodelContext)_localctx).obligations = obligations();
				 obligationList = ((MetamodelContext)_localctx).obligations.obligationList; 
				}
			}

			setState(66);
			_la = _input.LA(1);
			if (_la==RULES) {
				{
				setState(63); ((MetamodelContext)_localctx).rules = rules();
				 ruleList = ((MetamodelContext)_localctx).rules.ruleList; 
				}
			}

			setState(68); match(EOF);

				((MetamodelContext)_localctx).mm =  new Metamodel(((MetamodelContext)_localctx).roles.roleList, ((MetamodelContext)_localctx).objectives.objList, dependencyList, obligationList, ruleList); 
				
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

	public static class RolesContext extends ParserRuleContext {
		public List<Role> roleList;
		public RoleContext role;
		public List<RoleContext> role() {
			return getRuleContexts(RoleContext.class);
		}
		public RoleContext role(int i) {
			return getRuleContext(RoleContext.class,i);
		}
		public TerminalNode ROLES() { return getToken(MetamodelParser.ROLES, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public RolesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roles; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterRoles(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitRoles(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitRoles(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RolesContext roles() throws RecognitionException {
		RolesContext _localctx = new RolesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_roles);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((RolesContext)_localctx).roleList =  new ArrayList<>(); 
			setState(72); match(ROLES);
			setState(73); match(COLON);
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74); ((RolesContext)_localctx).role = role();
				 _localctx.roleList.add(((RolesContext)_localctx).role.r); 
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ATOM );
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

	public static class RoleContext extends ParserRuleContext {
		public Role r;
		public Token name;
		public ObjectiveListContext objectiveList;
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public TerminalNode ATOM() { return getToken(MetamodelParser.ATOM, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public ObjectiveListContext objectiveList() {
			return getRuleContext(ObjectiveListContext.class,0);
		}
		public RoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_role; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleContext role() throws RecognitionException {
		RoleContext _localctx = new RoleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_role);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); ((RoleContext)_localctx).name = match(ATOM);
			setState(82); match(COLON);
			setState(83); ((RoleContext)_localctx).objectiveList = objectiveList();
			setState(84); match(FULLSTOP);
			 ((RoleContext)_localctx).r =  new Role((((RoleContext)_localctx).name!=null?((RoleContext)_localctx).name.getText():null), ((RoleContext)_localctx).objectiveList.terms); 
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

	public static class ObjectiveListContext extends ParserRuleContext {
		public List<Term> terms;
		public TermContext term;
		public ObjectiveListContext ol;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MetamodelParser.SEMICOLON, 0); }
		public ObjectiveListContext objectiveList() {
			return getRuleContext(ObjectiveListContext.class,0);
		}
		public ObjectiveListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectiveList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterObjectiveList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitObjectiveList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitObjectiveList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectiveListContext objectiveList() throws RecognitionException {
		ObjectiveListContext _localctx = new ObjectiveListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_objectiveList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ObjectiveListContext)_localctx).terms =  new ArrayList<>(); 
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(88); ((ObjectiveListContext)_localctx).term = term();
				 _localctx.terms.add(((ObjectiveListContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(91); ((ObjectiveListContext)_localctx).term = term();
				setState(92); match(SEMICOLON);
				setState(93); ((ObjectiveListContext)_localctx).ol = objectiveList();
				 _localctx.terms.add(((ObjectiveListContext)_localctx).term.fml); _localctx.terms.addAll(((ObjectiveListContext)_localctx).ol.terms); 
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

	public static class ObjectivesContext extends ParserRuleContext {
		public List<Objective> objList;
		public ObjectiveContext objective;
		public ObjectiveContext objective(int i) {
			return getRuleContext(ObjectiveContext.class,i);
		}
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public TerminalNode OBJECTIVES() { return getToken(MetamodelParser.OBJECTIVES, 0); }
		public List<ObjectiveContext> objective() {
			return getRuleContexts(ObjectiveContext.class);
		}
		public ObjectivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterObjectives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitObjectives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitObjectives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectivesContext objectives() throws RecognitionException {
		ObjectivesContext _localctx = new ObjectivesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_objectives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ObjectivesContext)_localctx).objList =  new ArrayList<>(); 
			setState(99); match(OBJECTIVES);
			setState(100); match(COLON);
			setState(104); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101); ((ObjectivesContext)_localctx).objective = objective();
				 _localctx.objList.add(((ObjectivesContext)_localctx).objective.o); 
				}
				}
				setState(106); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << START_BRACKET) | (1L << COLON) | (1L << SEMICOLON) | (1L << FULLSTOP) | (1L << START) | (1L << COMMA) | (1L << ATOM) | (1L << NUMBER) | (1L << VAR) | (1L << UNARY_OP) | (1L << OPENSTRING))) != 0) );
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

	public static class ObjectiveContext extends ParserRuleContext {
		public Objective o;
		public PrologContext prolog;
		public ObjectiveListContext objectiveList;
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public ObjectiveListContext objectiveList() {
			return getRuleContext(ObjectiveListContext.class,0);
		}
		public ObjectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterObjective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitObjective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitObjective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectiveContext objective() throws RecognitionException {
		ObjectiveContext _localctx = new ObjectiveContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_objective);
		try {
			setState(118);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); ((ObjectiveContext)_localctx).prolog = prolog();
				setState(109); match(FULLSTOP);
				 ((ObjectiveContext)_localctx).o =  new Objective(((ObjectiveContext)_localctx).prolog.fml); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112); ((ObjectiveContext)_localctx).prolog = prolog();
				setState(113); match(COLON);
				setState(114); ((ObjectiveContext)_localctx).objectiveList = objectiveList();
				setState(115); match(FULLSTOP);
				 ((ObjectiveContext)_localctx).o =  new Objective(((ObjectiveContext)_localctx).prolog.fml, ((ObjectiveContext)_localctx).objectiveList.terms); 
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

	public static class DependenciesContext extends ParserRuleContext {
		public List<Dependency> dependencyList;
		public DependencyContext dependency;
		public List<DependencyContext> dependency() {
			return getRuleContexts(DependencyContext.class);
		}
		public TerminalNode DEPENDENCIES() { return getToken(MetamodelParser.DEPENDENCIES, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public DependencyContext dependency(int i) {
			return getRuleContext(DependencyContext.class,i);
		}
		public DependenciesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependencies; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterDependencies(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitDependencies(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitDependencies(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependenciesContext dependencies() throws RecognitionException {
		DependenciesContext _localctx = new DependenciesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dependencies);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((DependenciesContext)_localctx).dependencyList =  new ArrayList<>(); 
			setState(121); match(DEPENDENCIES);
			setState(122); match(COLON);
			setState(126); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123); ((DependenciesContext)_localctx).dependency = dependency();
				 _localctx.dependencyList.add(((DependenciesContext)_localctx).dependency.d); 
				}
				}
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ATOM );
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

	public static class DependencyContext extends ParserRuleContext {
		public Dependency d;
		public Token dependee;
		public Token dependant;
		public PrologContext prolog;
		public TerminalNode ATOM(int i) {
			return getToken(MetamodelParser.ATOM, i);
		}
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public List<TerminalNode> ATOM() { return getTokens(MetamodelParser.ATOM); }
		public TerminalNode GT() { return getToken(MetamodelParser.GT, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public DependencyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dependency; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterDependency(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitDependency(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitDependency(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DependencyContext dependency() throws RecognitionException {
		DependencyContext _localctx = new DependencyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dependency);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); ((DependencyContext)_localctx).dependee = match(ATOM);
			setState(131); match(GT);
			setState(132); ((DependencyContext)_localctx).dependant = match(ATOM);
			setState(133); match(COLON);
			setState(134); ((DependencyContext)_localctx).prolog = prolog();
			setState(135); match(FULLSTOP);
			 ((DependencyContext)_localctx).d =  new Dependency((((DependencyContext)_localctx).dependee!=null?((DependencyContext)_localctx).dependee.getText():null), (((DependencyContext)_localctx).dependant!=null?((DependencyContext)_localctx).dependant.getText():null), ((DependencyContext)_localctx).prolog.fml); 
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

	public static class ObligationsContext extends ParserRuleContext {
		public List<Obligation> obligationList;
		public ObligationContext obligation;
		public ObligationContext obligation(int i) {
			return getRuleContext(ObligationContext.class,i);
		}
		public List<ObligationContext> obligation() {
			return getRuleContexts(ObligationContext.class);
		}
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public TerminalNode OBLIGATIONS() { return getToken(MetamodelParser.OBLIGATIONS, 0); }
		public ObligationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterObligations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitObligations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitObligations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObligationsContext obligations() throws RecognitionException {
		ObligationsContext _localctx = new ObligationsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_obligations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ObligationsContext)_localctx).obligationList =  new ArrayList<>(); 
			setState(139); match(OBLIGATIONS);
			setState(140); match(COLON);
			setState(144); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141); ((ObligationsContext)_localctx).obligation = obligation();
				 _localctx.obligationList.add(((ObligationsContext)_localctx).obligation.o); 
				}
				}
				setState(146); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ATOM );
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

	public static class ObligationContext extends ParserRuleContext {
		public Obligation o;
		public Token roleName;
		public PrologContext obj;
		public PrologContext deadline;
		public PrologContext cond;
		public TerminalNode PIPE() { return getToken(MetamodelParser.PIPE, 0); }
		public List<PrologContext> prolog() {
			return getRuleContexts(PrologContext.class);
		}
		public TerminalNode LT() { return getToken(MetamodelParser.LT, 0); }
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public TerminalNode ATOM() { return getToken(MetamodelParser.ATOM, 0); }
		public PrologContext prolog(int i) {
			return getRuleContext(PrologContext.class,i);
		}
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public ObligationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obligation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterObligation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitObligation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitObligation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObligationContext obligation() throws RecognitionException {
		ObligationContext _localctx = new ObligationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_obligation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); ((ObligationContext)_localctx).roleName = match(ATOM);
			setState(149); match(COLON);
			setState(150); ((ObligationContext)_localctx).obj = prolog();
			setState(151); match(LT);
			setState(152); ((ObligationContext)_localctx).deadline = prolog();
			setState(153); match(PIPE);
			setState(154); ((ObligationContext)_localctx).cond = prolog();
			setState(155); match(FULLSTOP);
			 ((ObligationContext)_localctx).o =  new Obligation((((ObligationContext)_localctx).roleName!=null?((ObligationContext)_localctx).roleName.getText():null), ((ObligationContext)_localctx).obj.fml, ((ObligationContext)_localctx).deadline.fml, ((ObligationContext)_localctx).cond.fml); 
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
		public List<Rule> ruleList;
		public KrruleContext krrule;
		public List<KrruleContext> krrule() {
			return getRuleContexts(KrruleContext.class);
		}
		public TerminalNode RULES() { return getToken(MetamodelParser.RULES, 0); }
		public TerminalNode COLON() { return getToken(MetamodelParser.COLON, 0); }
		public KrruleContext krrule(int i) {
			return getRuleContext(KrruleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((RulesContext)_localctx).ruleList =  new ArrayList<>(); 
			setState(159); match(RULES);
			setState(160); match(COLON);
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(161); ((RulesContext)_localctx).krrule = krrule();
				 _localctx.ruleList.add(((RulesContext)_localctx).krrule.r); 
				}
				}
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==START_BRACKET || _la==ATOM );
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

	public static class KrruleContext extends ParserRuleContext {
		public Rule r;
		public StructContext struct;
		public PrologContext prolog;
		public TerminalNode ENTAILS() { return getToken(MetamodelParser.ENTAILS, 0); }
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public KrruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_krrule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterKrrule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitKrrule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitKrrule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KrruleContext krrule() throws RecognitionException {
		KrruleContext _localctx = new KrruleContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_krrule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); ((KrruleContext)_localctx).struct = struct();
			setState(169); match(ENTAILS);
			setState(170); ((KrruleContext)_localctx).prolog = prolog();
			setState(171); match(FULLSTOP);
			 ((KrruleContext)_localctx).r =  new Rule(((KrruleContext)_localctx).struct.fml, ((KrruleContext)_localctx).prolog.fml); 
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
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterProlog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitProlog(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitProlog(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_prolog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); ((PrologContext)_localctx).prolog2 = prolog2();
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
		public TerminalNode SEMICOLON() { return getToken(MetamodelParser.SEMICOLON, 0); }
		public TerminalNode START() { return getToken(MetamodelParser.START, 0); }
		public Prolog2Context prolog2() {
			return getRuleContext(Prolog2Context.class,0);
		}
		public TerminalNode COMMA() { return getToken(MetamodelParser.COMMA, 0); }
		public TerminalNode UNARY_OP() { return getToken(MetamodelParser.UNARY_OP, 0); }
		public TerminalNode END() { return getToken(MetamodelParser.END, 0); }
		public Prolog2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterProlog2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitProlog2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitProlog2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Prolog2Context prolog2() throws RecognitionException {
		Prolog2Context _localctx = new Prolog2Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_prolog2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(177); match(COMMA);
				setState(178); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(181); match(SEMICOLON);
				setState(182); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(185); match(START);
				setState(186); ((Prolog2Context)_localctx).pl = prolog2();
				setState(187); match(END);
				setState(188); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(191); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(192); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(195); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(196); prolog2();
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
		public TerminalNode BINARY_OP() { return getToken(MetamodelParser.BINARY_OP, 0); }
		public TerminalNode MATH_OP() { return getToken(MetamodelParser.MATH_OP, 0); }
		public TerminalNode IS() { return getToken(MetamodelParser.IS, 0); }
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TermBuilderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termBuilder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterTermBuilder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitTermBuilder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitTermBuilder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermBuilderContext termBuilder() throws RecognitionException {
		TermBuilderContext _localctx = new TermBuilderContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termBuilder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(202); ((TermBuilderContext)_localctx).t3 = ((TermBuilderContext)_localctx).term = term();
				setState(203); match(IS);
				setState(204); ((TermBuilderContext)_localctx).t4 = ((TermBuilderContext)_localctx).term = term();
				setState(205); ((TermBuilderContext)_localctx).MATH_OP = match(MATH_OP);
				setState(206); ((TermBuilderContext)_localctx).t5 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct("is", ((TermBuilderContext)_localctx).t3.fml, new Struct((((TermBuilderContext)_localctx).MATH_OP!=null?((TermBuilderContext)_localctx).MATH_OP.getText():null), ((TermBuilderContext)_localctx).t4.fml, ((TermBuilderContext)_localctx).t5.fml)); 
				}
				break;

			case 2:
				{
				setState(209); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 3:
				{
				setState(212); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(213); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(214); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
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
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
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
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(219); ((TermContext)_localctx).string = string();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).string.fml; 
				}
				break;

			case 2:
				{
				setState(222); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 3:
				{
				setState(225); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 4:
				{
				setState(228); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 5:
				{
				setState(231); ((TermContext)_localctx).number = number();
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
		public Token STRLIT;
		public TerminalNode STRLIT() { return getToken(MetamodelParser.STRLIT, 0); }
		public TerminalNode OPENSTRING() { return getToken(MetamodelParser.OPENSTRING, 0); }
		public TerminalNode CLOSESTRING() { return getToken(MetamodelParser.CLOSESTRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236); match(OPENSTRING);
			setState(237); ((StringContext)_localctx).STRLIT = match(STRLIT);
			setState(238); match(CLOSESTRING);
			 ((StringContext)_localctx).fml =  new Struct((((StringContext)_localctx).STRLIT!=null?((StringContext)_localctx).STRLIT.getText():null)); 
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
		public TerminalNode ATOM() { return getToken(MetamodelParser.ATOM, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241); ((AtomContext)_localctx).ATOM = match(ATOM);
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
		public TerminalNode NUMBER() { return getToken(MetamodelParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244); ((NumberContext)_localctx).NUMBER = match(NUMBER);
			 String numStr = (((NumberContext)_localctx).NUMBER!=null?((NumberContext)_localctx).NUMBER.getText():null); ((NumberContext)_localctx).fml =  new alice.tuprolog.Double(Double.parseDouble(numStr)); 
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
		public TerminalNode VAR() { return getToken(MetamodelParser.VAR, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247); ((VarContext)_localctx).VAR = match(VAR);
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
		public TerminalNode START() { return getToken(MetamodelParser.START, 0); }
		public TerminalNode ATOM() { return getToken(MetamodelParser.ATOM, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode END() { return getToken(MetamodelParser.END, 0); }
		public StructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructContext struct() throws RecognitionException {
		StructContext _localctx = new StructContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_struct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(250); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(251); match(START);
				setState(252); ((StructContext)_localctx).args = args();
				setState(253); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(256); ((StructContext)_localctx).list = list();
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
		public TerminalNode COMMA() { return getToken(MetamodelParser.COMMA, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ArgsContext)_localctx).fml =  new ArrayList<>(); 
			setState(270);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(262); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(265); ((ArgsContext)_localctx).term = term();
				setState(266); match(COMMA);
				setState(267); ((ArgsContext)_localctx).a = args();
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
		public TerminalNode PIPE() { return getToken(MetamodelParser.PIPE, 0); }
		public TerminalNode START_BRACKET() { return getToken(MetamodelParser.START_BRACKET, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public TerminalNode END_BRACKET() { return getToken(MetamodelParser.END_BRACKET, 0); }
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_list);
		try {
			setState(284);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(272); match(START_BRACKET);
				setState(273); ((ListContext)_localctx).listContents = listContents();
				setState(274); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277); match(START_BRACKET);
				setState(278); ((ListContext)_localctx).t1 = term();
				setState(279); match(PIPE);
				setState(280); ((ListContext)_localctx).t2 = term();
				setState(281); match(END_BRACKET);
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
		public TerminalNode COMMA() { return getToken(MetamodelParser.COMMA, 0); }
		public ListContentsContext listContents() {
			return getRuleContext(ListContentsContext.class,0);
		}
		public ListContentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listContents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterListContents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitListContents(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitListContents(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContentsContext listContents() throws RecognitionException {
		ListContentsContext _localctx = new ListContentsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_listContents);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ListContentsContext)_localctx).fml =  new Struct(); 
			setState(295);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(287); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(290); ((ListContentsContext)_localctx).listItem = listItem();
				setState(291); match(COMMA);
				setState(292); ((ListContentsContext)_localctx).lc = listContents();
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
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).enterListItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MetamodelParserListener ) ((MetamodelParserListener)listener).exitListItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MetamodelParserVisitor ) return ((MetamodelParserVisitor<? extends T>)visitor).visitListItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemContext listItem() throws RecognitionException {
		ListItemContext _localctx = new ListItemContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_listItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297); ((ListItemContext)_localctx).prolog2 = prolog2();
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3 \u012f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\5\2;\n\2\3\2\3\2\3\2\5\2@\n\2\3\2\3"+
		"\2\3\2\5\2E\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\6\3P\n\3\r\3\16\3"+
		"Q\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5c\n\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\6\6k\n\6\r\6\16\6l\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\5\7y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u0081\n\b\r\b\16\b"+
		"\u0082\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0093"+
		"\n\n\r\n\16\n\u0094\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00a7\n\f\r\f\16\f\u00a8\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5"+
		"\17\u00cb\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00dc\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ed\n\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0106\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\5\27\u0111\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\5\30\u011f\n\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\5\31\u012a\n\31\3\32\3\32\3\32\3\32\2\33\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2\2\u012e\2\64\3\2\2\2\4I\3\2"+
		"\2\2\6S\3\2\2\2\bY\3\2\2\2\nd\3\2\2\2\fx\3\2\2\2\16z\3\2\2\2\20\u0084"+
		"\3\2\2\2\22\u008c\3\2\2\2\24\u0096\3\2\2\2\26\u00a0\3\2\2\2\30\u00aa\3"+
		"\2\2\2\32\u00b0\3\2\2\2\34\u00ca\3\2\2\2\36\u00db\3\2\2\2 \u00ec\3\2\2"+
		"\2\"\u00ee\3\2\2\2$\u00f3\3\2\2\2&\u00f6\3\2\2\2(\u00f9\3\2\2\2*\u0105"+
		"\3\2\2\2,\u0107\3\2\2\2.\u011e\3\2\2\2\60\u0120\3\2\2\2\62\u012b\3\2\2"+
		"\2\64\65\b\2\1\2\65\66\5\4\3\2\66:\5\n\6\2\678\5\16\b\289\b\2\1\29;\3"+
		"\2\2\2:\67\3\2\2\2:;\3\2\2\2;?\3\2\2\2<=\5\22\n\2=>\b\2\1\2>@\3\2\2\2"+
		"?<\3\2\2\2?@\3\2\2\2@D\3\2\2\2AB\5\26\f\2BC\b\2\1\2CE\3\2\2\2DA\3\2\2"+
		"\2DE\3\2\2\2EF\3\2\2\2FG\7\2\2\3GH\b\2\1\2H\3\3\2\2\2IJ\b\3\1\2JK\7\5"+
		"\2\2KO\7\13\2\2LM\5\6\4\2MN\b\3\1\2NP\3\2\2\2OL\3\2\2\2PQ\3\2\2\2QO\3"+
		"\2\2\2QR\3\2\2\2R\5\3\2\2\2ST\7\25\2\2TU\7\13\2\2UV\5\b\5\2VW\7\r\2\2"+
		"WX\b\4\1\2X\7\3\2\2\2Yb\b\5\1\2Z[\5 \21\2[\\\b\5\1\2\\c\3\2\2\2]^\5 \21"+
		"\2^_\7\f\2\2_`\5\b\5\2`a\b\5\1\2ac\3\2\2\2bZ\3\2\2\2b]\3\2\2\2c\t\3\2"+
		"\2\2de\b\6\1\2ef\7\6\2\2fj\7\13\2\2gh\5\f\7\2hi\b\6\1\2ik\3\2\2\2jg\3"+
		"\2\2\2kl\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\13\3\2\2\2no\5\32\16\2op\7\r\2\2"+
		"pq\b\7\1\2qy\3\2\2\2rs\5\32\16\2st\7\13\2\2tu\5\b\5\2uv\7\r\2\2vw\b\7"+
		"\1\2wy\3\2\2\2xn\3\2\2\2xr\3\2\2\2y\r\3\2\2\2z{\b\b\1\2{|\7\7\2\2|\u0080"+
		"\7\13\2\2}~\5\20\t\2~\177\b\b\1\2\177\u0081\3\2\2\2\u0080}\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\17\3\2\2"+
		"\2\u0084\u0085\7\25\2\2\u0085\u0086\7\23\2\2\u0086\u0087\7\25\2\2\u0087"+
		"\u0088\7\13\2\2\u0088\u0089\5\32\16\2\u0089\u008a\7\r\2\2\u008a\u008b"+
		"\b\t\1\2\u008b\21\3\2\2\2\u008c\u008d\b\n\1\2\u008d\u008e\7\b\2\2\u008e"+
		"\u0092\7\13\2\2\u008f\u0090\5\24\13\2\u0090\u0091\b\n\1\2\u0091\u0093"+
		"\3\2\2\2\u0092\u008f\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\23\3\2\2\2\u0096\u0097\7\25\2\2\u0097\u0098\7\13"+
		"\2\2\u0098\u0099\5\32\16\2\u0099\u009a\7\22\2\2\u009a\u009b\5\32\16\2"+
		"\u009b\u009c\7\16\2\2\u009c\u009d\5\32\16\2\u009d\u009e\7\r\2\2\u009e"+
		"\u009f\b\13\1\2\u009f\25\3\2\2\2\u00a0\u00a1\b\f\1\2\u00a1\u00a2\7\t\2"+
		"\2\u00a2\u00a6\7\13\2\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5\b\f\1\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\27\3\2\2\2\u00aa\u00ab\5*\26\2\u00ab\u00ac"+
		"\7\n\2\2\u00ac\u00ad\5\32\16\2\u00ad\u00ae\7\r\2\2\u00ae\u00af\b\r\1\2"+
		"\u00af\31\3\2\2\2\u00b0\u00b1\5\34\17\2\u00b1\u00b2\b\16\1\2\u00b2\33"+
		"\3\2\2\2\u00b3\u00b4\7\21\2\2\u00b4\u00b5\5\34\17\2\u00b5\u00b6\b\17\1"+
		"\2\u00b6\u00cb\3\2\2\2\u00b7\u00b8\7\f\2\2\u00b8\u00b9\5\34\17\2\u00b9"+
		"\u00ba\b\17\1\2\u00ba\u00cb\3\2\2\2\u00bb\u00bc\7\17\2\2\u00bc\u00bd\5"+
		"\34\17\2\u00bd\u00be\7\20\2\2\u00be\u00bf\5\34\17\2\u00bf\u00c0\b\17\1"+
		"\2\u00c0\u00cb\3\2\2\2\u00c1\u00c2\7\32\2\2\u00c2\u00c3\5\34\17\2\u00c3"+
		"\u00c4\b\17\1\2\u00c4\u00cb\3\2\2\2\u00c5\u00c6\5\36\20\2\u00c6\u00c7"+
		"\5\34\17\2\u00c7\u00c8\b\17\1\2\u00c8\u00cb\3\2\2\2\u00c9\u00cb\3\2\2"+
		"\2\u00ca\u00b3\3\2\2\2\u00ca\u00b7\3\2\2\2\u00ca\u00bb\3\2\2\2\u00ca\u00c1"+
		"\3\2\2\2\u00ca\u00c5\3\2\2\2\u00ca\u00c9\3\2\2\2\u00cb\35\3\2\2\2\u00cc"+
		"\u00cd\5 \21\2\u00cd\u00ce\7\24\2\2\u00ce\u00cf\5 \21\2\u00cf\u00d0\7"+
		"\30\2\2\u00d0\u00d1\5 \21\2\u00d1\u00d2\b\20\1\2\u00d2\u00dc\3\2\2\2\u00d3"+
		"\u00d4\5 \21\2\u00d4\u00d5\b\20\1\2\u00d5\u00dc\3\2\2\2\u00d6\u00d7\5"+
		" \21\2\u00d7\u00d8\7\31\2\2\u00d8\u00d9\5 \21\2\u00d9\u00da\b\20\1\2\u00da"+
		"\u00dc\3\2\2\2\u00db\u00cc\3\2\2\2\u00db\u00d3\3\2\2\2\u00db\u00d6\3\2"+
		"\2\2\u00dc\37\3\2\2\2\u00dd\u00de\5\"\22\2\u00de\u00df\b\21\1\2\u00df"+
		"\u00ed\3\2\2\2\u00e0\u00e1\5*\26\2\u00e1\u00e2\b\21\1\2\u00e2\u00ed\3"+
		"\2\2\2\u00e3\u00e4\5$\23\2\u00e4\u00e5\b\21\1\2\u00e5\u00ed\3\2\2\2\u00e6"+
		"\u00e7\5(\25\2\u00e7\u00e8\b\21\1\2\u00e8\u00ed\3\2\2\2\u00e9\u00ea\5"+
		"&\24\2\u00ea\u00eb\b\21\1\2\u00eb\u00ed\3\2\2\2\u00ec\u00dd\3\2\2\2\u00ec"+
		"\u00e0\3\2\2\2\u00ec\u00e3\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ec\u00e9\3\2"+
		"\2\2\u00ed!\3\2\2\2\u00ee\u00ef\7\33\2\2\u00ef\u00f0\7 \2\2\u00f0\u00f1"+
		"\7\37\2\2\u00f1\u00f2\b\22\1\2\u00f2#\3\2\2\2\u00f3\u00f4\7\25\2\2\u00f4"+
		"\u00f5\b\23\1\2\u00f5%\3\2\2\2\u00f6\u00f7\7\26\2\2\u00f7\u00f8\b\24\1"+
		"\2\u00f8\'\3\2\2\2\u00f9\u00fa\7\27\2\2\u00fa\u00fb\b\25\1\2\u00fb)\3"+
		"\2\2\2\u00fc\u00fd\7\25\2\2\u00fd\u00fe\7\17\2\2\u00fe\u00ff\5,\27\2\u00ff"+
		"\u0100\7\20\2\2\u0100\u0101\b\26\1\2\u0101\u0106\3\2\2\2\u0102\u0103\5"+
		".\30\2\u0103\u0104\b\26\1\2\u0104\u0106\3\2\2\2\u0105\u00fc\3\2\2\2\u0105"+
		"\u0102\3\2\2\2\u0106+\3\2\2\2\u0107\u0110\b\27\1\2\u0108\u0109\5 \21\2"+
		"\u0109\u010a\b\27\1\2\u010a\u0111\3\2\2\2\u010b\u010c\5 \21\2\u010c\u010d"+
		"\7\21\2\2\u010d\u010e\5,\27\2\u010e\u010f\b\27\1\2\u010f\u0111\3\2\2\2"+
		"\u0110\u0108\3\2\2\2\u0110\u010b\3\2\2\2\u0111-\3\2\2\2\u0112\u0113\7"+
		"\3\2\2\u0113\u0114\5\60\31\2\u0114\u0115\7\4\2\2\u0115\u0116\b\30\1\2"+
		"\u0116\u011f\3\2\2\2\u0117\u0118\7\3\2\2\u0118\u0119\5 \21\2\u0119\u011a"+
		"\7\16\2\2\u011a\u011b\5 \21\2\u011b\u011c\7\4\2\2\u011c\u011d\b\30\1\2"+
		"\u011d\u011f\3\2\2\2\u011e\u0112\3\2\2\2\u011e\u0117\3\2\2\2\u011f/\3"+
		"\2\2\2\u0120\u0129\b\31\1\2\u0121\u0122\5\62\32\2\u0122\u0123\b\31\1\2"+
		"\u0123\u012a\3\2\2\2\u0124\u0125\5\62\32\2\u0125\u0126\7\21\2\2\u0126"+
		"\u0127\5\60\31\2\u0127\u0128\b\31\1\2\u0128\u012a\3\2\2\2\u0129\u0121"+
		"\3\2\2\2\u0129\u0124\3\2\2\2\u012a\61\3\2\2\2\u012b\u012c\5\34\17\2\u012c"+
		"\u012d\b\32\1\2\u012d\63\3\2\2\2\23:?DQblx\u0082\u0094\u00a8\u00ca\u00db"+
		"\u00ec\u0105\u0110\u011e\u0129";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}