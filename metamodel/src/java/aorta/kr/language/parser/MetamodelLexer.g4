lexer grammar MetamodelLexer;

@lexer::members {
	boolean dependencies = false;
}

START_BRACKET: '[';
END_BRACKET: ']';
ROLES: 'ROLES';
OBJECTIVES: 'OBJECTIVES';
DEPENDENCIES: 'DEPENDENCIES' {dependencies = true;};
OBLIGATIONS: 'OBLIGATIONS' {dependencies = false;};
RULES: 'RULES';
ENTAILS: ':-';
COLON: ':';
SEMICOLON: ';';
FULLSTOP: '.';
PIPE : '|';
START : '(';
END : ')';
COMMA: ',';
LT : '<';
GT : {dependencies}? '>';

IS : ' is ';

// Prolog
ATOM: [a-z][a-zA-Z0-9_]*;
NUMBER: '-'?[0-9]+('.'[0-9]+)?;
VAR: [A-Z_][a-zA-Z0-9_]*;
MATH_OP: ('+'|'-'|'*'|'/');
BINARY_OP: ('<'|'>'|'='|'=..'|'=:='|'=<'|'=='|'=\\='|'>='|'\\='|'\\=='|'+'|'-'|'*'|'/');
UNARY_OP: '\\+';

OPENSTRING : '\"' -> pushMode(STRINGMODE);

STRING : [a-zA-Z]+;

COMMENT : '%' ~('\r'|'\n')* -> skip; // comments
WS : [ \t\r\n]+ -> channel(HIDDEN); // skip spaces, tabs, newlines



mode STRINGMODE;

CLOSESTRING : '\"' -> popMode;

STRLIT : (~[\"])+ ;