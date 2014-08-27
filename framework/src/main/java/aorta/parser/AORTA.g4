grammar AORTA;

@header {
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
}

@lexer::members {
  private boolean init = false;
  private boolean action = false;
}
// grammar
aortaAgent[String name] returns [AgentBuilder agent] 
	: {
	   Initialization init = new Initialization();
	   List<ActionRule> actRules = new ArrayList<>();
	   } 
	(actionRules {actRules = $actionRules.rules;})
	EOF
	{
	 
	 $agent = new AgentBuilder(name, init, actRules);  
	 }
;
actionRules returns [List<ActionRule> rules] 
	: acts
	  { $rules = $acts.rules; }
;
acts returns [List<ActionRule> rules]
	: { $rules = new ArrayList<>(); }
	(act { $rules.add($act.rule); } 
	| act a=acts { $rules.add($act.rule); $rules.addAll($a.rules); });
act returns [ActionRule rule]
	: option COLON formulas EXECUTE action FULLSTOP
	  { $rule = new ActionRule($option.fml, $formulas.fml, $action.aa); }
;

option returns [Term fml] 
	: {boolean pos = true;}
	(
	  (NOT {pos=false;})? ROLE START term END { $fml = new Struct("role", $term.fml); if (!pos) { $fml = new Struct("~", $fml); } }
	| (NOT {pos=false;})? OBJ START term END { $fml = new Struct("obj", $term.fml); if (!pos) { $fml = new Struct("~", $fml); } }
	| SEND START term COMMA illForce COMMA term END  { $fml = new Struct("send", $term.fml, $illForce.fml, $term.fml); }
	);

illForce returns [Term fml]
	: TELL { $fml = new Struct("tell"); }
	| ACHIEVE { $fml = new Struct("achieve"); };

formulas returns [Formula fml]
	: (formula { $fml = $formula.fml; }
	| TRUE { $fml = new TrueFormula(); }
	| formula COMMA fmls=formulas { $fml = new ConjunctFormula($formula.fml, $fmls.fml); }
	| NOT formula { $fml = new NegatedFormula($formula.fml); }
	| NOT formula COMMA fmls=formulas { $fml = new ConjunctFormula(new NegatedFormula($formula.fml), $fmls.fml); }	  
	| NOT START fmls=formulas END { $fml = new NegatedFormula($fmls.fml); }
	);
formula returns [ReasoningFormula fml]
	: (OPT START prolog END { $fml = new OptionFormula($prolog.fml); }
	  | BEL START prolog END { $fml = new BeliefFormula($prolog.fml); }
	  | GOAL START prolog END { $fml = new GoalFormula($prolog.fml); }
	  | ORG START prolog END  { $fml = new OrganizationalFormula($prolog.fml); });
action returns [Action aa]
	: ( ENACT START pl=term END { $aa = new EnactAction($pl.fml); }
      | DEACT START pl=term END { $aa = new DeactAction($pl.fml); }
      | COMMIT START pl=term END { $aa = new CommitAction($pl.fml); }
      | DROP START pl=term END  { $aa = new DropAction($pl.fml); }
	  | SEND START ag=term COMMA fml=formula END { $aa = new SendAction($ag.fml, $fml.fml); }
	);

prolog returns [Term fml]: prolog2 { $fml = Term.createTerm($prolog2.text); };
prolog2 returns [Term fml]
	: (COMMA pl=prolog2 { $fml = new Struct(",", $pl.fml); }
	  | SEMICOLON pl=prolog2 { $fml = new Struct(";", $pl.fml); }
	  | START pl=prolog2 END prolog2 { $fml = $pl.fml; }
	  | UNARY_OP pl=prolog2 { $fml = new Struct($UNARY_OP.text, $pl.fml); } 
	  | termBuilder prolog2  { $fml = $termBuilder.fml; }
	  | );
termBuilder returns [Term fml]
	: ( t3=term ' is ' t4=term MATH_OP t5=term { $fml = new Struct("is", $t3.fml, new Struct($MATH_OP.text, $t4.fml, $t5.fml)); } 
	| term { $fml = $term.fml; }
	| t1=term BINARY_OP t2=term { $fml = new Struct($BINARY_OP.text, $t1.fml, $t2.fml); } );
term returns [Term fml]
	: (formula {$fml = new Struct($formula.fml.getType(), $formula.fml.getFormula());}
	  | struct {$fml = $struct.fml;}
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
list returns [Struct fml]
	: START_BRACKET listContents END_BRACKET { $fml = $listContents.fml; }
	| START_BRACKET t1=term PIPE t2=term END_BRACKET { $fml = new Struct($t1.fml, $t2.fml); };
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
EXECUTE: '=>' {action = true;};
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
NOT : '~';
FULLSTOP : '.' {action = false;};
PIPE : '|';
ROLE : 'role';
OBJ : 'obj';
TELL : 'tell';
ACHIEVE : 'achieve';
OPT : 'opt';
BEL : 'bel';
GOAL : 'goal';
ORG : 'org';
CONSIDER: 'consider';
DISREGARD: 'disregard';
ENACT: 'enact';
DEACT: 'deact';
COMMIT: 'commit';
SEND: {action}? 'send';
DROP: 'drop';
TRUE: 'true';

// Prolog specific tokens
// TODO: Match names with special characters (in '')
ATOM: [a-z][a-zA-Z0-9_]*;
NUMBER: '-'?[0-9]+;
VAR: {!init}? [A-Z_][a-zA-Z0-9_]*;
MATH_OP: ('+'|'-'|'*'|'/');
BINARY_OP: ('<'|'>'|'='|'=..'|'=:='|'=<'|'=='|'=\\='|'>'|'>='|'\\='|'\\=='|'+'|'-'|'*'|'/');
UNARY_OP: '\\+';

CLASSNAME : {init}? (
			  [a-zA-Z_$][a-zA-Z_$0-9]*'.'
			)*
			[a-zA-Z_$][a-zA-Z_$0-9]* {init = false;};
FILEPATH : '"' (~('\n'|'\r'|'"'))* '"' ; 

COMMENT : '%' ~('\r'|'\n')* -> skip; // comments
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines