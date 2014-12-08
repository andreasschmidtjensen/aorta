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
			setState(90);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(81); ((RoleContext)_localctx).name = match(ATOM);
				setState(82); match(FULLSTOP);
				 ((RoleContext)_localctx).r =  new Role((((RoleContext)_localctx).name!=null?((RoleContext)_localctx).name.getText():null), new ArrayList<Term>()); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84); ((RoleContext)_localctx).name = match(ATOM);
				setState(85); match(COLON);
				setState(86); ((RoleContext)_localctx).objectiveList = objectiveList();
				setState(87); match(FULLSTOP);
				 ((RoleContext)_localctx).r =  new Role((((RoleContext)_localctx).name!=null?((RoleContext)_localctx).name.getText():null), ((RoleContext)_localctx).objectiveList.terms); 
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
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(93); ((ObjectiveListContext)_localctx).term = term();
				 _localctx.terms.add(((ObjectiveListContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(96); ((ObjectiveListContext)_localctx).term = term();
				setState(97); match(SEMICOLON);
				setState(98); ((ObjectiveListContext)_localctx).ol = objectiveList();
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
			setState(104); match(OBJECTIVES);
			setState(105); match(COLON);
			setState(109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(106); ((ObjectivesContext)_localctx).objective = objective();
				 _localctx.objList.add(((ObjectivesContext)_localctx).objective.o); 
				}
				}
				setState(111); 
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
			setState(123);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(113); ((ObjectiveContext)_localctx).prolog = prolog();
				setState(114); match(FULLSTOP);
				 ((ObjectiveContext)_localctx).o =  new Objective(((ObjectiveContext)_localctx).prolog.fml); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117); ((ObjectiveContext)_localctx).prolog = prolog();
				setState(118); match(COLON);
				setState(119); ((ObjectiveContext)_localctx).objectiveList = objectiveList();
				setState(120); match(FULLSTOP);
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
			setState(126); match(DEPENDENCIES);
			setState(127); match(COLON);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128); ((DependenciesContext)_localctx).dependency = dependency();
				 _localctx.dependencyList.add(((DependenciesContext)_localctx).dependency.d); 
				}
				}
				setState(133); 
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
			setState(135); ((DependencyContext)_localctx).dependee = match(ATOM);
			setState(136); match(GT);
			setState(137); ((DependencyContext)_localctx).dependant = match(ATOM);
			setState(138); match(COLON);
			setState(139); ((DependencyContext)_localctx).prolog = prolog();
			setState(140); match(FULLSTOP);
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
			setState(144); match(OBLIGATIONS);
			setState(145); match(COLON);
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146); ((ObligationsContext)_localctx).obligation = obligation();
				 _localctx.obligationList.add(((ObligationsContext)_localctx).obligation.o); 
				}
				}
				setState(151); 
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
			setState(153); ((ObligationContext)_localctx).roleName = match(ATOM);
			setState(154); match(COLON);
			setState(155); ((ObligationContext)_localctx).obj = prolog();
			setState(156); match(LT);
			setState(157); ((ObligationContext)_localctx).deadline = prolog();
			setState(158); match(PIPE);
			setState(159); ((ObligationContext)_localctx).cond = prolog();
			setState(160); match(FULLSTOP);
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
			setState(164); match(RULES);
			setState(165); match(COLON);
			setState(169); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166); ((RulesContext)_localctx).krrule = krrule();
				 _localctx.ruleList.add(((RulesContext)_localctx).krrule.r); 
				}
				}
				setState(171); 
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
		public AtomContext atom;
		public TerminalNode ENTAILS() { return getToken(MetamodelParser.ENTAILS, 0); }
		public StructContext struct() {
			return getRuleContext(StructContext.class,0);
		}
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public TerminalNode FULLSTOP() { return getToken(MetamodelParser.FULLSTOP, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
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
			setState(185);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(173); ((KrruleContext)_localctx).struct = struct();
				setState(174); match(ENTAILS);
				setState(175); ((KrruleContext)_localctx).prolog = prolog();
				setState(176); match(FULLSTOP);
				 ((KrruleContext)_localctx).r =  new Rule(((KrruleContext)_localctx).struct.fml, ((KrruleContext)_localctx).prolog.fml); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(179); ((KrruleContext)_localctx).atom = atom();
				setState(180); match(ENTAILS);
				setState(181); ((KrruleContext)_localctx).prolog = prolog();
				setState(182); match(FULLSTOP);
				 ((KrruleContext)_localctx).r =  new Rule(((KrruleContext)_localctx).atom.fml, ((KrruleContext)_localctx).prolog.fml); 
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
			setState(187); ((PrologContext)_localctx).prolog2 = prolog2();
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
			setState(213);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(190); match(COMMA);
				setState(191); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(",", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 2:
				{
				setState(194); match(SEMICOLON);
				setState(195); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct(";", ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 3:
				{
				setState(198); match(START);
				setState(199); ((Prolog2Context)_localctx).pl = prolog2();
				setState(200); match(END);
				setState(201); prolog2();
				 ((Prolog2Context)_localctx).fml =  ((Prolog2Context)_localctx).pl.fml; 
				}
				break;

			case 4:
				{
				setState(204); ((Prolog2Context)_localctx).UNARY_OP = match(UNARY_OP);
				setState(205); ((Prolog2Context)_localctx).pl = prolog2();
				 ((Prolog2Context)_localctx).fml =  new Struct((((Prolog2Context)_localctx).UNARY_OP!=null?((Prolog2Context)_localctx).UNARY_OP.getText():null), ((Prolog2Context)_localctx).pl.fml); 
				}
				break;

			case 5:
				{
				setState(208); ((Prolog2Context)_localctx).termBuilder = termBuilder();
				setState(209); prolog2();
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
			setState(230);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(215); ((TermBuilderContext)_localctx).t3 = ((TermBuilderContext)_localctx).term = term();
				setState(216); match(IS);
				setState(217); ((TermBuilderContext)_localctx).t4 = ((TermBuilderContext)_localctx).term = term();
				setState(218); ((TermBuilderContext)_localctx).MATH_OP = match(MATH_OP);
				setState(219); ((TermBuilderContext)_localctx).t5 = ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  new Struct("is", ((TermBuilderContext)_localctx).t3.fml, new Struct((((TermBuilderContext)_localctx).MATH_OP!=null?((TermBuilderContext)_localctx).MATH_OP.getText():null), ((TermBuilderContext)_localctx).t4.fml, ((TermBuilderContext)_localctx).t5.fml)); 
				}
				break;

			case 2:
				{
				setState(222); ((TermBuilderContext)_localctx).term = term();
				 ((TermBuilderContext)_localctx).fml =  ((TermBuilderContext)_localctx).term.fml; 
				}
				break;

			case 3:
				{
				setState(225); ((TermBuilderContext)_localctx).t1 = ((TermBuilderContext)_localctx).term = term();
				setState(226); ((TermBuilderContext)_localctx).BINARY_OP = match(BINARY_OP);
				setState(227); ((TermBuilderContext)_localctx).t2 = ((TermBuilderContext)_localctx).term = term();
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
			setState(247);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(232); ((TermContext)_localctx).string = string();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).string.fml; 
				}
				break;

			case 2:
				{
				setState(235); ((TermContext)_localctx).struct = struct();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).struct.fml;
				}
				break;

			case 3:
				{
				setState(238); ((TermContext)_localctx).atom = atom();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).atom.fml;
				}
				break;

			case 4:
				{
				setState(241); ((TermContext)_localctx).var = var();
				((TermContext)_localctx).fml =  ((TermContext)_localctx).var.fml;
				}
				break;

			case 5:
				{
				setState(244); ((TermContext)_localctx).number = number();
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
			setState(249); match(OPENSTRING);
			setState(250); ((StringContext)_localctx).STRLIT = match(STRLIT);
			setState(251); match(CLOSESTRING);
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
			setState(254); ((AtomContext)_localctx).ATOM = match(ATOM);
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
			setState(257); ((NumberContext)_localctx).NUMBER = match(NUMBER);
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
			setState(260); ((VarContext)_localctx).VAR = match(VAR);
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
			setState(272);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(263); ((StructContext)_localctx).ATOM = match(ATOM);
				setState(264); match(START);
				setState(265); ((StructContext)_localctx).args = args();
				setState(266); match(END);
				 ((StructContext)_localctx).fml =  new Struct((((StructContext)_localctx).ATOM!=null?((StructContext)_localctx).ATOM.getText():null), ((StructContext)_localctx).args.fml.toArray(new Term[0])); 
				}
				break;
			case START_BRACKET:
				{
				setState(269); ((StructContext)_localctx).list = list();
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
			setState(283);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(275); ((ArgsContext)_localctx).term = term();
				 _localctx.fml.add(((ArgsContext)_localctx).term.fml); 
				}
				break;

			case 2:
				{
				setState(278); ((ArgsContext)_localctx).term = term();
				setState(279); match(COMMA);
				setState(280); ((ArgsContext)_localctx).a = args();
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
			setState(297);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285); match(START_BRACKET);
				setState(286); ((ListContext)_localctx).listContents = listContents();
				setState(287); match(END_BRACKET);
				 ((ListContext)_localctx).fml =  ((ListContext)_localctx).listContents.fml; 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(290); match(START_BRACKET);
				setState(291); ((ListContext)_localctx).t1 = term();
				setState(292); match(PIPE);
				setState(293); ((ListContext)_localctx).t2 = term();
				setState(294); match(END_BRACKET);
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
			setState(308);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(300); ((ListContentsContext)_localctx).listItem = listItem();
				 _localctx.fml.append(((ListContentsContext)_localctx).listItem.fml); 
				}
				break;

			case 2:
				{
				setState(303); ((ListContentsContext)_localctx).listItem = listItem();
				setState(304); match(COMMA);
				setState(305); ((ListContentsContext)_localctx).lc = listContents();
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
			setState(310); ((ListItemContext)_localctx).prolog2 = prolog2();
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3 \u013c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\5\2;\n\2\3\2\3\2\3\2\5\2@\n\2\3\2\3"+
		"\2\3\2\5\2E\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\6\3P\n\3\r\3\16\3"+
		"Q\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4]\n\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5h\n\5\3\6\3\6\3\6\3\6\3\6\3\6\6\6p\n\6\r\6\16\6q\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7~\n\7\3\b\3\b\3\b\3\b\3\b\3\b\6"+
		"\b\u0086\n\b\r\b\16\b\u0087\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\6\n\u0098\n\n\r\n\16\n\u0099\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00ac\n\f\r\f\16\f"+
		"\u00ad\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00bc\n\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00d8"+
		"\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\5\20\u00e9\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00fa\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u0113\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u011e\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\5\30\u012c\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u0137\n\31\3\32\3\32\3\32\3\32\2\33\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\2\2\u013d\2\64\3\2\2\2\4I\3\2\2\2\6\\\3"+
		"\2\2\2\b^\3\2\2\2\ni\3\2\2\2\f}\3\2\2\2\16\177\3\2\2\2\20\u0089\3\2\2"+
		"\2\22\u0091\3\2\2\2\24\u009b\3\2\2\2\26\u00a5\3\2\2\2\30\u00bb\3\2\2\2"+
		"\32\u00bd\3\2\2\2\34\u00d7\3\2\2\2\36\u00e8\3\2\2\2 \u00f9\3\2\2\2\"\u00fb"+
		"\3\2\2\2$\u0100\3\2\2\2&\u0103\3\2\2\2(\u0106\3\2\2\2*\u0112\3\2\2\2,"+
		"\u0114\3\2\2\2.\u012b\3\2\2\2\60\u012d\3\2\2\2\62\u0138\3\2\2\2\64\65"+
		"\b\2\1\2\65\66\5\4\3\2\66:\5\n\6\2\678\5\16\b\289\b\2\1\29;\3\2\2\2:\67"+
		"\3\2\2\2:;\3\2\2\2;?\3\2\2\2<=\5\22\n\2=>\b\2\1\2>@\3\2\2\2?<\3\2\2\2"+
		"?@\3\2\2\2@D\3\2\2\2AB\5\26\f\2BC\b\2\1\2CE\3\2\2\2DA\3\2\2\2DE\3\2\2"+
		"\2EF\3\2\2\2FG\7\2\2\3GH\b\2\1\2H\3\3\2\2\2IJ\b\3\1\2JK\7\5\2\2KO\7\13"+
		"\2\2LM\5\6\4\2MN\b\3\1\2NP\3\2\2\2OL\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2"+
		"\2\2R\5\3\2\2\2ST\7\25\2\2TU\7\r\2\2U]\b\4\1\2VW\7\25\2\2WX\7\13\2\2X"+
		"Y\5\b\5\2YZ\7\r\2\2Z[\b\4\1\2[]\3\2\2\2\\S\3\2\2\2\\V\3\2\2\2]\7\3\2\2"+
		"\2^g\b\5\1\2_`\5 \21\2`a\b\5\1\2ah\3\2\2\2bc\5 \21\2cd\7\f\2\2de\5\b\5"+
		"\2ef\b\5\1\2fh\3\2\2\2g_\3\2\2\2gb\3\2\2\2h\t\3\2\2\2ij\b\6\1\2jk\7\6"+
		"\2\2ko\7\13\2\2lm\5\f\7\2mn\b\6\1\2np\3\2\2\2ol\3\2\2\2pq\3\2\2\2qo\3"+
		"\2\2\2qr\3\2\2\2r\13\3\2\2\2st\5\32\16\2tu\7\r\2\2uv\b\7\1\2v~\3\2\2\2"+
		"wx\5\32\16\2xy\7\13\2\2yz\5\b\5\2z{\7\r\2\2{|\b\7\1\2|~\3\2\2\2}s\3\2"+
		"\2\2}w\3\2\2\2~\r\3\2\2\2\177\u0080\b\b\1\2\u0080\u0081\7\7\2\2\u0081"+
		"\u0085\7\13\2\2\u0082\u0083\5\20\t\2\u0083\u0084\b\b\1\2\u0084\u0086\3"+
		"\2\2\2\u0085\u0082\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\17\3\2\2\2\u0089\u008a\7\25\2\2\u008a\u008b\7\23"+
		"\2\2\u008b\u008c\7\25\2\2\u008c\u008d\7\13\2\2\u008d\u008e\5\32\16\2\u008e"+
		"\u008f\7\r\2\2\u008f\u0090\b\t\1\2\u0090\21\3\2\2\2\u0091\u0092\b\n\1"+
		"\2\u0092\u0093\7\b\2\2\u0093\u0097\7\13\2\2\u0094\u0095\5\24\13\2\u0095"+
		"\u0096\b\n\1\2\u0096\u0098\3\2\2\2\u0097\u0094\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\23\3\2\2\2\u009b\u009c"+
		"\7\25\2\2\u009c\u009d\7\13\2\2\u009d\u009e\5\32\16\2\u009e\u009f\7\22"+
		"\2\2\u009f\u00a0\5\32\16\2\u00a0\u00a1\7\16\2\2\u00a1\u00a2\5\32\16\2"+
		"\u00a2\u00a3\7\r\2\2\u00a3\u00a4\b\13\1\2\u00a4\25\3\2\2\2\u00a5\u00a6"+
		"\b\f\1\2\u00a6\u00a7\7\t\2\2\u00a7\u00ab\7\13\2\2\u00a8\u00a9\5\30\r\2"+
		"\u00a9\u00aa\b\f\1\2\u00aa\u00ac\3\2\2\2\u00ab\u00a8\3\2\2\2\u00ac\u00ad"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\27\3\2\2\2\u00af"+
		"\u00b0\5*\26\2\u00b0\u00b1\7\n\2\2\u00b1\u00b2\5\32\16\2\u00b2\u00b3\7"+
		"\r\2\2\u00b3\u00b4\b\r\1\2\u00b4\u00bc\3\2\2\2\u00b5\u00b6\5$\23\2\u00b6"+
		"\u00b7\7\n\2\2\u00b7\u00b8\5\32\16\2\u00b8\u00b9\7\r\2\2\u00b9\u00ba\b"+
		"\r\1\2\u00ba\u00bc\3\2\2\2\u00bb\u00af\3\2\2\2\u00bb\u00b5\3\2\2\2\u00bc"+
		"\31\3\2\2\2\u00bd\u00be\5\34\17\2\u00be\u00bf\b\16\1\2\u00bf\33\3\2\2"+
		"\2\u00c0\u00c1\7\21\2\2\u00c1\u00c2\5\34\17\2\u00c2\u00c3\b\17\1\2\u00c3"+
		"\u00d8\3\2\2\2\u00c4\u00c5\7\f\2\2\u00c5\u00c6\5\34\17\2\u00c6\u00c7\b"+
		"\17\1\2\u00c7\u00d8\3\2\2\2\u00c8\u00c9\7\17\2\2\u00c9\u00ca\5\34\17\2"+
		"\u00ca\u00cb\7\20\2\2\u00cb\u00cc\5\34\17\2\u00cc\u00cd\b\17\1\2\u00cd"+
		"\u00d8\3\2\2\2\u00ce\u00cf\7\32\2\2\u00cf\u00d0\5\34\17\2\u00d0\u00d1"+
		"\b\17\1\2\u00d1\u00d8\3\2\2\2\u00d2\u00d3\5\36\20\2\u00d3\u00d4\5\34\17"+
		"\2\u00d4\u00d5\b\17\1\2\u00d5\u00d8\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7"+
		"\u00c0\3\2\2\2\u00d7\u00c4\3\2\2\2\u00d7\u00c8\3\2\2\2\u00d7\u00ce\3\2"+
		"\2\2\u00d7\u00d2\3\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\35\3\2\2\2\u00d9\u00da"+
		"\5 \21\2\u00da\u00db\7\24\2\2\u00db\u00dc\5 \21\2\u00dc\u00dd\7\30\2\2"+
		"\u00dd\u00de\5 \21\2\u00de\u00df\b\20\1\2\u00df\u00e9\3\2\2\2\u00e0\u00e1"+
		"\5 \21\2\u00e1\u00e2\b\20\1\2\u00e2\u00e9\3\2\2\2\u00e3\u00e4\5 \21\2"+
		"\u00e4\u00e5\7\31\2\2\u00e5\u00e6\5 \21\2\u00e6\u00e7\b\20\1\2\u00e7\u00e9"+
		"\3\2\2\2\u00e8\u00d9\3\2\2\2\u00e8\u00e0\3\2\2\2\u00e8\u00e3\3\2\2\2\u00e9"+
		"\37\3\2\2\2\u00ea\u00eb\5\"\22\2\u00eb\u00ec\b\21\1\2\u00ec\u00fa\3\2"+
		"\2\2\u00ed\u00ee\5*\26\2\u00ee\u00ef\b\21\1\2\u00ef\u00fa\3\2\2\2\u00f0"+
		"\u00f1\5$\23\2\u00f1\u00f2\b\21\1\2\u00f2\u00fa\3\2\2\2\u00f3\u00f4\5"+
		"(\25\2\u00f4\u00f5\b\21\1\2\u00f5\u00fa\3\2\2\2\u00f6\u00f7\5&\24\2\u00f7"+
		"\u00f8\b\21\1\2\u00f8\u00fa\3\2\2\2\u00f9\u00ea\3\2\2\2\u00f9\u00ed\3"+
		"\2\2\2\u00f9\u00f0\3\2\2\2\u00f9\u00f3\3\2\2\2\u00f9\u00f6\3\2\2\2\u00fa"+
		"!\3\2\2\2\u00fb\u00fc\7\33\2\2\u00fc\u00fd\7 \2\2\u00fd\u00fe\7\37\2\2"+
		"\u00fe\u00ff\b\22\1\2\u00ff#\3\2\2\2\u0100\u0101\7\25\2\2\u0101\u0102"+
		"\b\23\1\2\u0102%\3\2\2\2\u0103\u0104\7\26\2\2\u0104\u0105\b\24\1\2\u0105"+
		"\'\3\2\2\2\u0106\u0107\7\27\2\2\u0107\u0108\b\25\1\2\u0108)\3\2\2\2\u0109"+
		"\u010a\7\25\2\2\u010a\u010b\7\17\2\2\u010b\u010c\5,\27\2\u010c\u010d\7"+
		"\20\2\2\u010d\u010e\b\26\1\2\u010e\u0113\3\2\2\2\u010f\u0110\5.\30\2\u0110"+
		"\u0111\b\26\1\2\u0111\u0113\3\2\2\2\u0112\u0109\3\2\2\2\u0112\u010f\3"+
		"\2\2\2\u0113+\3\2\2\2\u0114\u011d\b\27\1\2\u0115\u0116\5 \21\2\u0116\u0117"+
		"\b\27\1\2\u0117\u011e\3\2\2\2\u0118\u0119\5 \21\2\u0119\u011a\7\21\2\2"+
		"\u011a\u011b\5,\27\2\u011b\u011c\b\27\1\2\u011c\u011e\3\2\2\2\u011d\u0115"+
		"\3\2\2\2\u011d\u0118\3\2\2\2\u011e-\3\2\2\2\u011f\u0120\7\3\2\2\u0120"+
		"\u0121\5\60\31\2\u0121\u0122\7\4\2\2\u0122\u0123\b\30\1\2\u0123\u012c"+
		"\3\2\2\2\u0124\u0125\7\3\2\2\u0125\u0126\5 \21\2\u0126\u0127\7\16\2\2"+
		"\u0127\u0128\5 \21\2\u0128\u0129\7\4\2\2\u0129\u012a\b\30\1\2\u012a\u012c"+
		"\3\2\2\2\u012b\u011f\3\2\2\2\u012b\u0124\3\2\2\2\u012c/\3\2\2\2\u012d"+
		"\u0136\b\31\1\2\u012e\u012f\5\62\32\2\u012f\u0130\b\31\1\2\u0130\u0137"+
		"\3\2\2\2\u0131\u0132\5\62\32\2\u0132\u0133\7\21\2\2\u0133\u0134\5\60\31"+
		"\2\u0134\u0135\b\31\1\2\u0135\u0137\3\2\2\2\u0136\u012e\3\2\2\2\u0136"+
		"\u0131\3\2\2\2\u0137\61\3\2\2\2\u0138\u0139\5\34\17\2\u0139\u013a\b\32"+
		"\1\2\u013a\63\3\2\2\2\25:?DQ\\gq}\u0087\u0099\u00ad\u00bb\u00d7\u00e8"+
		"\u00f9\u0112\u011d\u012b\u0136";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}