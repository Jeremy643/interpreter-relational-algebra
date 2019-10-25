grammar RelationalAlgebra;

fragment DIGIT:         [0-9];
fragment LETTER:        [a-zA-Z];

LEFT_BRACKET:           '(';
RIGHT_BRACKET:          ')';
LEFT_SQUARE_BRACKET:    '[';
RIGHT_SQUARE_BRACKET:   ']';
NAME:                   LETTER+;
PROJECTION:             ':PROJECT:';
RENAMING:             	':RENAME:';
PRODUCT:                ':PRODUCT:';
UNION:                  ':UPLUS:';
UNION_MAX:              ':UMAX:';
INTERSECTION:           ':INTERSECT:';
DIFFERENCE:             ':DIFF:';

start:  raExpr;

raExpr: base #BaseRelation
      | LEFT_BRACKET raExpr RIGHT_BRACKET #ParenthesisedExpr
      | raExpr UNION raExpr #Union
      | raExpr UNION_MAX raExpr #UnionMax
      | raExpr PRODUCT raExpr #Product
      | raExpr INTERSECTION raExpr #Intersection
      | raExpr DIFFERENCE raExpr #Difference
      | PROJECTION LEFT_SQUARE_BRACKET attributes RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Projection
      | RENAMING LEFT_SQUARE_BRACKET subst (',' subst)* RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Renaming
      ;

base:   NAME;

attributes: NAME (',' NAME)*;

subst : NAME '->' NAME;

WS:  [ \t\r\n] -> skip;