grammar RelationalAlgebra;

fragment DIGIT:         [0-9];
fragment LETTER:        [a-zA-Z];

LEFT_BRACKET:           '(';
RIGHT_BRACKET:          ')';
LEFT_SQUARE_BRACKET:    '[';
RIGHT_SQUARE_BRACKET:   ']';
SINGLE_QUOTE:           '\'';
NAME:                   (LETTER(LETTER|DIGIT)*)|DIGIT+;
PROJECTION:             ':PROJECT:';
RENAMING:             	':RENAME:';
PRODUCT:                ':PRODUCT:';
UNION:                  ':UPLUS:';
UNION_MAX:              ':UMAX:';
INTERSECTION:           ':INTERSECT:';
DIFFERENCE:             ':DIFF:';
ELIMINATE:              ':ELIM:';
SELECT:                 ':SELECT:';
AND:                    ':AND:';
OR:                     ':OR:';
EQUALITY:               ':=:';
INEQUALITY:             ':!=:';
LESS:                   ':<:';
LESS_EQUAL:             ':<=:';
GREATER:                ':>:';
GREATER_EQUAL:          ':>=:';
NOT:                    ':NOT:';

start:  raExpr;

raExpr: base #baseRelation
      | LEFT_BRACKET raExpr RIGHT_BRACKET #ParenthisisedExpr
      | raExpr UNION raExpr #Union
      | raExpr UNION_MAX raExpr  #UnionMax
      | raExpr PRODUCT raExpr  #Product
      | raExpr INTERSECTION raExpr #Intersection
      | raExpr DIFFERENCE raExpr  #Difference
      | PROJECTION LEFT_SQUARE_BRACKET attributes RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Projection
      | RENAMING LEFT_SQUARE_BRACKET subst (',' subst)* RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Renaming
      | ELIMINATE LEFT_BRACKET raExpr RIGHT_BRACKET #Eliminate
      | SELECT LEFT_SQUARE_BRACKET condition RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Selection
      ;

base:   NAME;

attributes: NAME (',' NAME)*;

subst: NAME '->' NAME;

condition:  term EQUALITY term #Equality
         |  term INEQUALITY term #Inequality
         |  term LESS term #Less
         |  term LESS_EQUAL term #LessEqual
         |  term GREATER term #Greater
         |  term GREATER_EQUAL term #GreaterEqual
         |  LEFT_BRACKET condition RIGHT_BRACKET AND LEFT_BRACKET condition RIGHT_BRACKET #And
         |  LEFT_BRACKET condition RIGHT_BRACKET OR LEFT_BRACKET condition RIGHT_BRACKET #Or
         |  NOT LEFT_BRACKET condition RIGHT_BRACKET #Not
         ;
         
term:   NAME
    |   SINGLE_QUOTE NAME SINGLE_QUOTE
    ;

WS:  [ \t\r\n] -> skip;