		grammar Aorta;

options {
	k = 5;
}

@header {
package aorta.parser;

import aorta.syntax.ast.*;
import aorta.syntax.ast.metamodel.*;
import ail.syntax.ast.*;
import java.util.HashMap;
}

@members {
private static HashMap<String,Abstract_VarTerm> variables = new HashMap<String,Abstract_VarTerm>();
}

@lexer::header {
package aorta.parser;
}
	
@lexer::members {
public int plain_nesting = 0;
public int sq_nesting = 0;
public boolean stringterm = false;
public boolean metamodel = false;
public boolean action = false;
}

mas returns [Abstract_MAS mas] : {$mas = new Abstract_MAS();} 
	aortaagent { ArrayList<Abstract_AortaAgent> ags = new ArrayList<>(); ags.add($aortaagent.a); $mas.setAgs(ags); };

aortaagent returns [Abstract_AortaAgent a] : 
	{ $a = new Abstract_AortaAgent(); }
	(r=rule { $a.addAortaRule($r.rule); })+;
	
rule returns [Abstract_AortaRule rule] :
	opt=literal COLON c=conditions RULEARROW act=action POINT
	{ $rule = new Abstract_AortaRule($opt.l, $c.guard, $act.a); };
	
conditions returns [Abstract_Guard guard] :
	{ 
		$guard = new Abstract_Guard(); 
		boolean pos = true;
	}
	(NOT { pos=false; })? c=condition { $guard.add($c.guard, pos); } 
		(COMMA { pos = true; } (NOT { pos=false; })? c=condition { $guard.add($c.guard, pos); })*;
	
condition returns [Abstract_GuardAtom guard] :
	BEL OPEN l=literal CLOSE { $guard = new Abstract_GBelief(Abstract_GBelief.AILBel, $l.l); } |
	GOAL OPEN l=literal CLOSE { $guard = new Abstract_Goal($l.l, Abstract_Goal.achieveGoal); } |
	ORG OPEN l=literal CLOSE { $guard = new Abstract_GBelief(Abstract_GBelief.AILBel, $l.l); ((Abstract_GBelief)$guard).setDBnum(new Abstract_StringTermImpl("org")); } |
	OPT OPEN l=literal CLOSE { $guard = new Abstract_GBelief(Abstract_GBelief.AILBel, $l.l); ((Abstract_GBelief)$guard).setDBnum(new Abstract_StringTermImpl("opt")); } |
	TRUE { $guard = new Abstract_GBelief(Abstract_GBelief.GTrue); };

action returns [Abstract_Action a] : 
	ENACT OPEN r=pred CLOSE { $a = new Abstract_EnactAction($r.t); } |
	DEACT OPEN r=pred CLOSE { $a = new Abstract_DeactAction($r.t); } |
	COMMIT OPEN o=pred CLOSE { $a = new Abstract_CommitAction($o.t); } |
	DROP OPEN o=pred CLOSE { $a = new Abstract_DropAction($o.t); } |
	SEND OPEN ag=term COMMA m=pred CLOSE { $a = new Abstract_AortaSendAction($ag.t, $m.t); };

model returns [Abstract_Metamodel mm] :
	{ $mm = new Abstract_Metamodel(); }
	ROLES (r=role POINT { $mm.addRole($r.role); })+
	OBJECTIVES (o=obj POINT { $mm.addObjective($o.obj); })+
	(DEPENDENCIES (d=dep POINT { $mm.addDependency($d.dep); })+)?
	(NORMS (n=norm POINT { $mm.addConditionalNorm($n.norm); })+)?;

role returns [Abstract_Role role] : 
	{ $role = new Abstract_Role(); }
	n=CONST { $role.setName($n.getText()); } 
		(COLON l=literal { $role.addObjective($l.l); } (SEMI l2=literal { $role.addObjective($l2.l); })*)?;

obj returns [Abstract_Objective obj] :
	{ $obj = new Abstract_Objective(); }
	l=literal { $obj.setObjective($l.l); }
		(COLON l2=literal { $obj.addSubObjective($l2.l); } (SEMI l3=literal { $obj.addSubObjective($l3.l); })*)?;

dep returns [Abstract_Dependency dep] : 
	{ dep = new Abstract_Dependency(); }
	d1=CONST { $dep.setDependant($d1.getText()); } GREATER
	d2=CONST { $dep.setDependee($d2.getText()); } COLON
		l=literal { dep.setObjective($l.l); };

norm returns [Abstract_ConditionalNorm norm] : 
	{ $norm = new Abstract_ConditionalNorm(); }
	r=CONST { $norm.setRole($r.getText()); }
		(EQUALS a=var { $norm.setAgentVar($a.v); })?
		SQOPEN deon=CONST SQCLOSE COLON { $norm.setDeon($deon.getText()); }
		o=literal LESS { $norm.setObjective($o.l); }
		d=literal PIPE { $norm.setDeadline($d.l); }
		c=literal { $norm.setCondition($c.l); };


AORTA		: 'AORTA';

ROLES			: {metamodel = true;} 'ROLES:';
OBJECTIVES		: {metamodel}?=> 'OBJECTIVES:';
DEPENDENCIES	: {metamodel}?=> 'DEPENDENCIES:';
NORMS			: {metamodel}?=> 'NORMS:';


BEL		:	{plain_nesting == 0}?=> 'bel';
GOAL	:	{plain_nesting == 0}?=> 'goal';
ORG		:	{plain_nesting == 0}?=> 'org';
OPT		:	{plain_nesting == 0}?=> 'opt';

ENACT	:	{action}?=>'enact';
DEACT	:	{action}?=>'deact';
DROP	:	{action}?=>'drop';
COMMIT	:	{action}?=>'commit';
SEND	:	{action}?=>'send';

RULEARROW :	'=>' {action = true;};

TRUE	:	{plain_nesting == 0}?=> 'true';


literal returns [Abstract_Literal l]:  (TRUE {$l = new Abstract_Literal(Abstract_Literal.LTrue);} | 
				NOT nt=pred {
				if ($nt.t instanceof Abstract_VarTerm) 
					{$l = (Abstract_VarTerm) $nt.t; $l.setNegated(false);}
					else { $l = new Abstract_Literal(Abstract_Literal.LNeg, new Abstract_Pred($nt.t));}}) | 
				t=pred {if ($t.t instanceof Abstract_VarTerm) 
				            {$l = (Abstract_VarTerm) $t.t;} 
				            else {$l = new Abstract_Literal(Abstract_Literal.LPos, new Abstract_Pred($t.t));}};

pred 	returns [Abstract_Predicate t]:	v=var {$t = $v.v;}| f=function {$t = $f.f;};
function returns [Abstract_Predicate f]: CONST {$f = new Abstract_Predicate($CONST.getText());} (OPEN terms[$f] CLOSE)?;
list returns [Abstract_ListTerm lt] : 
	{ $lt = new Abstract_ListTermImpl(); } 
	(SQOPEN SQCLOSE |
	SQOPEN h=literal { $lt.append($h.l); } (COMMA t=literal { $lt.append($t.l); } )* SQCLOSE
	);

terms[Abstract_Predicate f] : t=term {$f.addTerm($t.t);} (COMMA terms[$f])? ;
term	returns [Abstract_Term t]:  a = atom {$t = $a.t;} | s = stringterm {$t = $s.s;} | f=function {$t = $f.f;} | l=list { $t=$l.lt;};

atom	returns [Abstract_NumberTerm t]	:	n = numberstring {$t = new Abstract_NumberTermImpl($n.s);}| 
					v=var {$t = $v.v;} | OPEN a=arithexpr CLOSE {$t = $a.t;};
stringterm returns [Abstract_StringTerm s] : DOUBLEQUOTE  STRING DOUBLEQUOTE {		 
                   $s = new Abstract_StringTermImpl($STRING.getText());};

var 	returns [Abstract_VarTerm v]:	VAR {
	if (variables.containsKey($VAR.getText())) {
		$v = variables.get($VAR.getText());
		} else {
		$v = new Abstract_VarTerm($VAR.getText());
		variables.put($VAR.getText(), $v);
		}
	};

numberstring returns [String s] :	{$s = "";} (MINUS {$s += "-";})? (n1=NUMBER {$s += $n1.getText();}
					(POINT {$s += ".";} n2=NUMBER {$s += $n2.getText();})?);
equation returns[Abstract_Equation eq] : a1=arithexpr oper=eqoper a2=arithexpr  {$eq = new Abstract_Equation($a1.t, $oper.oper, $a2.t);};
eqoper returns [int oper] : LESS {$oper=Abstract_Equation.less;} | EQ {$oper=Abstract_Equation.equal;};

arithexpr returns [Abstract_NumberTerm t]	:	m=multexpr {$t = $m.t;} ( oper=addoper m1=multexpr {$t = new Abstract_ArithExpr($m.t, $oper.oper, $m1.t);})?;
multexpr returns [Abstract_NumberTerm t]	:	a=atom {$t = $a.t;} (oper=multoper a1=atom {$t = new Abstract_ArithExpr($a.t, $oper.oper, $a1.t);})?;

addoper	returns [int oper] :	(PLUS {$oper=Abstract_ArithExpr.plus;}| MINUS {$oper=Abstract_ArithExpr.minus;});
multoper returns [int oper] : (MULT {$oper=Abstract_ArithExpr.times;} | DIV {$oper=Abstract_ArithExpr.div;} | MOD {$oper=Abstract_ArithExpr.mod;});


EQUALS : {metamodel}?=> '=';
PIPE : {metamodel}?=> '|';

// General AIL Lexing stuff
COMMENT
    : '/*' .* '*/' {$channel=HIDDEN;}
    ;
LINE_COMMENT
    : '%' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
NEWLINE:'\r'? '\n' {skip();} ;
WS  :   (' '|'\t')+ {skip();} ;


OPEN	: 	'(' {plain_nesting++;};
CLOSE	:	')' {plain_nesting--;};
SQOPEN	:	'[' {sq_nesting++;};
SQCLOSE	:	']' {sq_nesting--;};
DOUBLEQUOTE
	:	'"' {if (stringterm) {stringterm = false;} else {stringterm = true;}};
NOT	:	'~';

STRING	:	{stringterm}?=> ('a'..'z'|'A'..'Z'|'0'..'9'|'_')+;
CONST 	: 	{!stringterm}?=>'a'..'z' ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
VAR	:	{!stringterm}?=>'A'..'Z' ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
NUMBER	:	{!stringterm}?=>'0'..'9' ('0'..'9')*;

// TODO: \=
LESS	:	'<';
GREATER :	'>';
EQ	: 	'==';
POINT	:	'.' {action=false;};
MULT	:	'*';
PLUS	:	'+';
MINUS	:	'-';
DIV	:	'/';
MOD	:	'%';

SHRIEK	:	'!';
COMMA	:	',';
SEMI	:	';';
COLON	:	':';
QUERY	:	'?';
