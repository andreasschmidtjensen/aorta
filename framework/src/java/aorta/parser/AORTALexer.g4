lexer grammar AORTALexer;

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
  private boolean action = false;
  private boolean opt = true;
}

START_BLOCK: '{' { opt = true; };
END_BLOCK: '}';
START_BRACKET: '[';
END_BRACKET: ']';
IF: 'if' { opt = false; };
EXECUTE: '=>' {action = true;};
ORGANIZATION : 'organization';
PATH: 'path';
TYPE: 'type';
ACT_BLOCK: 'actions';
EQUALS: '=';
START : '(';
END : ')';
COMMA: ',';
COLON: ':' { opt = false; };
SEMICOLON: ';';
NOT : '~';
FULLSTOP : '.' {action = false; opt = true;};
PIPE : '|';
ROLE : {opt}? 'role';
OBJ : {opt}? 'obj';
NORM : {opt}? 'norm';
DEON : {opt}? ('obliged' | 'forbidden');
VIOL : {opt}? 'viol';
TELL : {opt}? 'tell';
ACHIEVE : {opt}? 'achieve';
OPT : 'opt';
BEL : 'bel';
GOAL : 'goal';
ORG : 'org';
CAP : 'cap';
ENACT: {action}? 'enact';
DEACT: {action}? 'deact';
COMMIT: {action}? 'commit';
SEND: /*{action}?*/ 'send';
DROP: {action}? 'drop';
TRUE: 'true';

IS : ' is ';

// Prolog specific tokens
// TODO: Match names with special characters (in '')
ATOM: [a-z][a-zA-Z0-9_]*;
NUMBER: '-'?[0-9]+;
VAR: [A-Z_][a-zA-Z0-9_]*;
MATH_OP: ('+'|'-'|'*'|'/');
BINARY_OP: ('<'|'>'|'='|'=..'|'=:='|'=<'|'=='|'=\\='|'>'|'>='|'\\='|'\\=='|'+'|'-'|'*'|'/');
UNARY_OP: '\\+';

OPENSTRING : '\"' -> pushMode(STRINGMODE);

FILEPATH : '"' (~('\n'|'\r'|'"'))* '"' ; 

COMMENT : '%' ~('\r'|'\n')* -> skip; // comments
WS : [ \t\r\n]+ -> channel(HIDDEN) ; // skip spaces, tabs, newlines


mode STRINGMODE;

CLOSESTRING : '\"' -> popMode;

STRLIT : (~[\"])+ ;