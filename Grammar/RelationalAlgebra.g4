grammar RelationalAlgebra;

fragment DIGIT:         [0-9];
fragment LETTER:        [a-zA-Z];

LEFT_BRACKET:           '(';
RIGHT_BRACKET:          ')';
LEFT_SQUARE_BRACKET:    '[';
RIGHT_SQUARE_BRACKET:   ']';
NAME:                   LETTER+;
PROJECTION:             ':PROJECT:';
PRODUCT:                ':PRODUCT:';
UNION:                  ':UPLUS:';

start:  raExpr;

raExpr: base
      | unaryExpr
      | binaryExpr;

base:   NAME;

unaryExpr:  PROJECTION LEFT_SQUARE_BRACKET attributes RIGHT_SQUARE_BRACKET LEFT_BRACKET raExpr RIGHT_BRACKET;

binaryExpr: LEFT_BRACKET raExpr UNION raExpr RIGHT_BRACKET;

attributes: NAME
          | NAME ',' attributes;

Space:  (' ' | '\t')+{skip();};