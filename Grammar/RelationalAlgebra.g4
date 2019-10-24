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

start:  raExpr;

raExpr: base #BaseRelation
      | LEFT_BRACKET raExpr RIGHT_BRACKET #ParenthesisedExpr
      | raExpr UNION raExpr #Union
      | PROJECTION LEFT_SQUARE_BRACKET attributes RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Projection
      | RENAMING LEFT_SQUARE_BRACKET subst (',' subst)* RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET #Renaming
      ;

base:   NAME;

attributes: NAME (',' NAME)*;

subst : NAME '->' NAME;

WS:  [ \t\r\n] -> skip;