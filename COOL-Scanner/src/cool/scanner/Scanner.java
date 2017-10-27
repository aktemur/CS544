package cool.scanner;

import java.util.LinkedList;

public class Scanner {
    private final String source;
    private final LinkedList<Token> tokens = new LinkedList<>();

    // Fields to keep track of where in the source code we are
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    public LinkedList<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        // Append the final "end-of-file" token
        tokens.add(new Token(TokenType.EOF, null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case '/': addToken(TokenType.SLASH); break;
            case '*': addToken(TokenType.STAR); break;
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;
            case '\n':
                line++;
                break;
            default:
                if (isDigit(c)) {
                    readNumber();
                } else {
                    addToken(TokenType.ERROR, "Unexpected character '" + c + "'.");
                    break;
                }
        }
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    // Like advance, but does not consume the character
    private char peek() {
        if (current >= source.length()) return '\0';
        return source.charAt(current);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object value) {
        tokens.add(new Token(type, value, line));
    }

    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private void readNumber() {
        while(isDigit(peek())) {
            advance();
        }
        String lexeme = source.substring(start, current);
        addToken(TokenType.NUMBER, Integer.valueOf(lexeme));
    }
}