package cool.scanner;

public class Token {
    final TokenType type;
    final int line;
    final Object value; // Value of the number, identifier's name, etc.

    Token(TokenType type, Object value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    public String toString() {
        return "#" + line + " " + type + ((value == null) ? "" : (" " + value));
    }
}