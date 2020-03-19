grammar RelationalAlgebra;

fragment DIGIT:         [0-9];
fragment LETTER:        [a-zA-Z];

LEFT_BRACKET:           '(';
RIGHT_BRACKET:          ')';
LEFT_SQUARE_BRACKET:    '[';
RIGHT_SQUARE_BRACKET:   ']';
SINGLE_QUOTE:           '\'';
NAME:                   (LETTER(LETTER|DIGIT|' ')*)|DIGIT+;
PROJECTION:             '<P>';
RENAMING:             	'<R>';
PRODUCT:                '<X>';
UNION:                  '<+>';
UNION_MAX:              '<U>';
INTERSECTION:           '<I>';
DIFFERENCE:             '<D>';
ELIMINATE:              '<E>';
SELECT:                 '<S>';
AND:                    '&';
OR:                     '|';
EQUALITY:               '=';
INEQUALITY:             '!=';
LESS:                   '<';
LESS_EQUAL:             '<=';
GREATER:                '>';
GREATER_EQUAL:          '>=';
NOT:                    '~';

start:  raExpr EOF;

raExpr: base #baseRelation
      | LEFT_BRACKET raExpr RIGHT_BRACKET #ParenthesizedExpr
      | raExpr PRODUCT raExpr  #Product
      | raExpr INTERSECTION raExpr #Intersection
      | raExpr UNION raExpr #Union
      | raExpr UNION_MAX raExpr  #UnionMax
      | raExpr DIFFERENCE raExpr  #Difference
      | PROJECTION LEFT_SQUARE_BRACKET attributes RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Projection
      | RENAMING LEFT_SQUARE_BRACKET subst (',' subst)* RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Renaming
      | ELIMINATE LEFT_BRACKET raExpr RIGHT_BRACKET #Eliminate
      | SELECT LEFT_SQUARE_BRACKET condition RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Selection
      ;

base:   NAME;

attributes: NAME (',' NAME)*;

subst: NAME '->' NAME;

condition:  LEFT_BRACKET condition RIGHT_BRACKET #ParenthesizedCond
         |  term EQUALITY term #Equality
         |  term INEQUALITY term #Inequality
         |  term LESS term #Less
         |  term LESS_EQUAL term #LessEqual
         |  term GREATER term #Greater
         |  term GREATER_EQUAL term #GreaterEqual
         |  condition AND condition #And
         |  condition OR condition #Or
         |  NOT LEFT_BRACKET condition RIGHT_BRACKET #Not
         ;
         
term:   NAME
    |   SINGLE_QUOTE NAME SINGLE_QUOTE
    ;

WS:  [ \t\r\n] -> skip;