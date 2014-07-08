grammar AORTA;

@header {
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
}

@lexer::members {
  private boolean init = false;
}
// grammar
// TODO Auto-blocks (e.g. consider roles I am capable of as options, objectives I am responsible for etc)
aortaAgent[String name] returns [AgentBuilder agent] 
	: {
	   Initialization init = new Initialization();
	   List<OptionRule> optRules = null;
	   List<ActionRule> actRules = new ArrayList<>();
	   List<CoordinationRule> coordRules = null;
	   } 
	(init {init = $init.initialization;})?
	(optionRules {optRules = $optionRules.rules;})?
	(actionRules {actRules = $actionRules.rules;})?
	(coordinationRules {coordRules = $coordinationRules.rules;})?
	EOF
	{
	 
	 $agent = new AgentBuilder(name, init, optRules, actRules, coordRules);  
	 }
;
init returns [Initialization initialization] 
        : {
           $initialization = new Initialization(); 
          }
            INIT_BLOCK START_BLOCK 
                (strategy { $initialization.strategy = $strategy.clazz; })?
            END_BLOCK
;
strategy returns [String clazz] : STRATEGY COLON CLASSNAME { $clazz = $CLASSNAME.text; };
optionRules returns [List<OptionRule> rules] 
	: OPT_BLOCK START_BLOCK opts END_BLOCK
	  { $rules = $opts.rules; }
;
opts returns [List<OptionRule> rules]
	: { $rules = new ArrayList<>(); }
	(
	| opt o=opts { $rules.add($opt.rule); $rules.addAll($o.rules); })
;
opt returns [OptionRule rule]
	: START_BRACKET formulas END_BRACKET EXECUTE optAction
	  { $rule = new OptionRule($formulas.fml, $optAction.oa); }
;

actionRules returns [List<ActionRule> rules] 
	: ACT_BLOCK START_BLOCK acts END_BLOCK
	  { $rules = $acts.rules; }
;
acts returns [List<ActionRule> rules]
	: { $rules = new ArrayList<>(); }
	(act { $rules.add($act.rule); } 
	| act a=acts { $rules.add($act.rule); $rules.addAll($a.rules); });
act returns [ActionRule rule]
	: START_BRACKET formulas END_BRACKET EXECUTE actAction
	  { $rule = new ActionRule($formulas.fml, $actAction.aa); }
;

coordinationRules returns [List<CoordinationRule> rules]
	: COORDINATION_BLOCK START_BLOCK coords END_BLOCK
	{ $rules = $coords.rules; }
;
coords returns [List<CoordinationRule> rules]
	: { $rules = new ArrayList<>(); }
	 ( coord c=coords { $rules.add($coord.rule); $rules.addAll($c.rules); }
	| coord { $rules.add($coord.rule); } )
;
coord returns [CoordinationRule rule]
	: START_BRACKET changes END_BRACKET COLON START_BRACKET formulas END_BRACKET EXECUTE sendAction
	  { $rule = new CoordinationRule($changes.cts, $formulas.fml, $sendAction.sa); }
;
changes returns [List<ChangeTerm> cts]
	: { $cts = new ArrayList<>(); }
	  (change { $cts.add($change.ct); }
	| change COMMA c=changes { $cts.add($change.ct); $cts.addAll($c.cts); })
;
change returns [ChangeTerm ct]
	: { boolean addition = false; }
	(PLUS {addition=true;}|MINUS) formula
	{ $ct = new ChangeTerm(addition, $formula.fml); }
;
sendAction returns [SendAction sa]
	: SEND START var COMMA formula END
	{ $sa = new SendAction($var.fml, $formula.fml); }
;
//[+bel(O), -goal(O)] : [org(role(R), obj(O,R), rea(A,R))] => send(A, bel(O))


formulas returns [Formula fml]
	: (formula { $fml = $formula.fml; }
	| TRUE { $fml = new TrueFormula(); }
	| formula COMMA fmls=formulas { $fml = new ConjunctFormula($formula.fml, $fmls.fml); }
	| NOT formula { $fml = new NegatedFormula($formula.fml); }
	| NOT START fmls=formulas END { $fml = new NegatedFormula($fmls.fml); }
	);
formula returns [ReasoningFormula fml]
	: ( OPT START prolog END { $fml = new OptionFormula($prolog.fml); }
         | BEL START prolog END { $fml = new BeliefFormula($prolog.fml); }
         | GOAL START prolog END { $fml = new GoalFormula($prolog.fml); }
         | ORG START prolog END  { $fml = new OrganizationalFormula($prolog.fml); });
optAction returns [OptAction oa]
	: ( CONSIDER START prolog END { $oa = new ConsiderAction((Struct) $prolog.fml); }
      | DISREGARD START prolog END { $oa = new DisregardAction((Struct) $prolog.fml); }
	);
actAction returns [ActAction aa]
	: ( ENACT START pl=term END { $aa = new EnactAction($pl.fml); }
      | DEACT START pl=term END { $aa = new DeactAction($pl.fml); }
      | COMMIT START pl=term END { $aa = new CommitAction($pl.fml); }
      | DROP START pl=term END  { $aa = new DropAction($pl.fml); }
	  | SEND START ag=var COMMA fml=formula END { $aa = new SendAction($ag.fml, $fml.fml); }
	  | SENDONCE START ag=var COMMA fml=formula END { $aa = new SendOnceAction($ag.fml, $fml.fml); }	
	);


// TODO Actual parse as prolog (or just fetch from bel/org(...) directly?)
prolog returns [Term fml]: prolog2 { $fml = Term.createTerm($prolog2.text); };
prolog2 returns [Term fml]
	: (COMMA pl=prolog2 { $fml = new Struct(",", $pl.fml); }
	  | SEMICOLON pl=prolog2 { $fml = new Struct(";", $pl.fml); }
	  | START pl=prolog2 END prolog2 { $fml = $pl.fml; }
	  | UNARY_OP pl=prolog2 { $fml = new Struct($UNARY_OP.text, $pl.fml); } 
	  | termBuilder prolog2  { $fml = $termBuilder.fml; }
	  | );
termBuilder returns [Term fml]
	: (term { $fml = $term.fml; }
	| t1=term BINARY_OP t2=term { $fml = new Struct($BINARY_OP.text, $t1.fml, $t2.fml); } );
term returns [Term fml]
	: (struct {$fml = $struct.fml;}
	  | atom {$fml = $atom.fml;}
	  | var {$fml = $var.fml;}
	  | number {$fml = $number.fml;})
;
atom returns [Struct fml]: ATOM { $fml = new Struct($ATOM.text); };
number returns [Number fml] : NUMBER { String numStr = $NUMBER.text; $fml = new Int(Integer.parseInt(numStr)); };
var returns [Var fml]: VAR { $fml = new Var($VAR.text); };
struct returns [Struct fml]
	: (ATOM START args END { $fml = new Struct($ATOM.text, $args.fml.toArray(new Term[0])); }
	| list  { $fml = $list.fml; });
args returns [List<Term> fml]
	: { $fml = new ArrayList<>(); } 
	(term { $fml.add($term.fml); }
	| term COMMA a=args { $fml.add($term.fml); $fml.addAll($a.fml); } );
list returns [Struct fml]: START_BRACKET listContents END_BRACKET { $fml = $listContents.fml; };
listContents returns [Struct fml]
	: { $fml = new Struct(); }
	(listItem { $fml.append($listItem.fml); } 
	| listItem COMMA lc=listContents { $fml.append($listItem.fml); $fml.append($lc.fml); });
listItem returns [Term fml]: prolog2 { $fml = $prolog2.fml; };


// lexer
START_BLOCK: '{';
END_BLOCK: '}';
START_BRACKET: '[';
END_BRACKET: ']';
EXECUTE: '=>';
INIT_BLOCK: 'init' {init = true;}; 
ORGANIZATION : 'organization';
PATH: 'path';
TYPE: 'type';
ORGANIZATION_TYPE: ('generic'|'opera');
STRATEGY: 'strategy';
OPT_BLOCK: 'options';
ACT_BLOCK: 'actions';
COORDINATION_BLOCK: 'coordination';
EQUALS: '=';
START : '(';
END : ')';
COMMA: ',';
COLON: ':';
SEMICOLON: ';';
PLUS : '+';
MINUS : '-';
NOT : '~';
OPT : 'opt';
BEL : 'bel';
GOAL : 'goal';
ORG : 'org';
CONSIDER: 'consider';
DISREGARD: 'disregard';
ENACT: 'enact';
DEACT: 'deact';
COMMIT: 'commit';
SEND: 'send';
SENDONCE: 'sendonce';
DROP: 'drop';
TRUE: 'true';

// Prolog specific tokens
// TODO: Match names with special characters (in '')
ATOM: [a-z][a-zA-Z0-9_]*;
NUMBER: '-'?[0-9]+;
VAR: {!init}? [A-Z_][a-zA-Z0-9_]*;
UNARY_OP: '\\+';
BINARY_OP: ('<'|'>'|'='|'=..'|'=:='|'=<'|'=='|'=\\='|'>'|'>='|'\\='|'\\==');

CLASSNAME : {init}? (
			  [a-zA-Z_$][a-zA-Z_$0-9]*'.'
			)*
			[a-zA-Z_$][a-zA-Z_$0-9]* {init = false;};
FILEPATH : '"' (~('\n'|'\r'|'"'))* '"' ; 

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines