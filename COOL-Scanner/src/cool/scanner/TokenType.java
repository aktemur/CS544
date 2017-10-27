package cool.scanner;

public enum TokenType {
    // Single-character tokens
    MINUS, PLUS, SLASH, STAR,
    LBRACE, RBRACE, LPAREN, RPAREN,
    COMMA, COLON, AT, DOT, SEMICOLON,
    TILDA, LESSTHAN, EQ,

    // Multi-character operators
    LEFTARROW, RIGHTARROW, LESSTHANEQ,


    // Literals.
    TYPE_ID, OBJECT_ID, NUMBER, STRING,

    // Keywords.
    CLASS, ELSE, FALSE, FI, IF, IN, INHERITS,
    ISVOID, LET, LOOP, POOL, THEN, WHILE,
    CASE, ESAC, NEW, OF, NOT, TRUE,

    EOF, ERROR
}