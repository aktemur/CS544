package cool.parser;

import cool.ast.*;
import cool.scanner.Token;
import cool.scanner.TokenType;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static cool.scanner.TokenType.*;

public class Parser {
    LinkedList<Token> tokens;

    public Parser(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public Program parseProgram() {
        List<Klass> classes = new LinkedList<Klass>();

        do { // There has to be at least one class definition
            Klass klas = parseClass();
            classes.add(klas);
            expect(SEMICOLON);
        } while(!consumeIfMatches(EOF));

        return new Program(classes);
    }

    private Klass parseClass() {
        expect(CLASS);
        String name = expectAndGetTypeID();
        String superName = "Object";
        if (consumeIfMatches(INHERITS)) {
            superName = expectAndGetTypeID();
        }
        expect(LBRACE);
        List<Feature> features = parseFeatures();
        expect(RBRACE);
        return new Klass(name, superName, features);
    }

    private List<Feature> parseFeatures() {
        List<Feature> features = new LinkedList<Feature>();
        while (!nextTokenMatches(RBRACE)) {
            features.add(parseFeature());
            expect(SEMICOLON);
        }
        return features;
    }

    private Feature parseFeature() {
        String name = expectAndGetObjectID();
        if (consumeIfMatches(COLON)) {
            // Parsing a field
            String type = expectAndGetTypeID();
            Expr initialization = null;
            if (consumeIfMatches(LEFTARROW)) {
                initialization = parseExpr();
            }
            return new Field(name, type, initialization);
        } else if (consumeIfMatches(LPAREN)) {
            //TODO: Properly parse a method
            List<Formal> formals = new LinkedList<Formal>();
            String returnType = "PLACEHOLDER";
            Expr body = new Id("placeholder");
            return new Method(name, formals, returnType, body);
        } else {
            error("I was expecting to see a method or a field on line " + tokens.peek().line);
            return null;
        }
    }

    private Formal parseFormal() {
        String id = expectAndGetObjectID();
        expect(COLON);
        String type = expectAndGetTypeID();
        return new Formal(id, type);
    }

    private Expr parseExpr() {
        Expr letNotAssignExpr = parseLetNotAssignExpr();
        if (letNotAssignExpr != null) {
            return letNotAssignExpr;
        } else {
            return parseLevel2Expr();
        }
    }

    private Expr parseLetNotAssignExpr() {
        if (nextTokenMatches(LET)) {
            return parseLetExpr();
        } else if (nextTokenMatches(NOT)) {
            return parseNotExpr();
        } else if (nextTokenMatches(OBJECT_ID) && secondTokenMatches(LEFTARROW)) {
            return parseAssignExpr();
        } else {
            return null;
        }
    }

    private Expr parseLetExpr() {
        expect(LET);

        //TODO: We are parsing a single binding below.
        //      Fix this to parse multiple bindings.
        //      Idea: Push bindings into a stack as long as you see a COMMA that follows
        //      the binding you just parsed.
        //      Then pop the bindings back while building Let expresssions.

        String id = expectAndGetObjectID();
        expect(COLON);
        String type = expectAndGetTypeID();
        Expr init = null;
        if (consumeIfMatches(LEFTARROW)) {
            init = parseExpr();
        }
        expect(IN);
        Expr body = parseExpr();

        return new Let(id, type, init, body);
    }

    private Expr parseNotExpr() {
        expect(NOT);
        Expr expr = parseExpr();
        return new Not(expr);
    }

    private Expr parseAssignExpr() {
        String name = expectAndGetObjectID();
        expect(LEFTARROW);
        Expr rhs = parseExpr();
        return new Assignment(name, rhs);
    }

    private Expr parseLevel2Expr() {
        //TODO: Handle <, <=, = operators.
        //      Note that these operators do NOT associate.
        return parseLevel3Expr();
    }

    private Expr parseLevel3Expr() {
        Expr lhs = parseLevel4Expr();
        while (nextTokenMatches(PLUS) || nextTokenMatches(MINUS)) {
            BinaryOperator op = BinaryOperator.opForToken(tokens.pop());
            Expr rhs = parseLetNotAssignExpr();
            if (rhs == null) {
                rhs = parseLevel4Expr();
            }
            lhs = new Binary(op, lhs, rhs);
        }
        return lhs;
    }

    private Expr parseLevel4Expr() {
        // TODO: Handle * and / operators.
        return parseLevel5Expr();
    }

    private Expr parseLevel5Expr() {
        if (nextTokenMatches(ISVOID) || nextTokenMatches(TILDA)) {
            boolean isvoid = nextTokenMatches(ISVOID);
            tokens.pop();

            //TODO: Handle isvoid and ~ operators.
            //      Note that it possible to have an input like
            //      'not not isvoid x'
            Expr expr = new Id("placeholder");

            return isvoid ? new IsVoid(expr) : new Negation(expr);
        } else {
            return parseLevel6Expr();
        }
    }

    private Expr parseLevel6Expr() {
        Expr lhs = parseAtom();
        while (nextTokenMatches(AT) || nextTokenMatches(DOT)) {
            if (consumeIfMatches(AT)) {
                String type = expectAndGetTypeID();
                expect(DOT);
                String id = expectAndGetObjectID();
                List<Expr> arguments = parseArguments();
                lhs = new StaticDispatch(lhs, type, id, arguments);
            } else if (consumeIfMatches(DOT)) {
                String name = expectAndGetObjectID();
                List<Expr> arguments = parseArguments();
                lhs = new DynamicDispatch(lhs, name, arguments);
            }
        }
        return lhs;
    }

    private Expr parseAtom() {
        //TODO: Handle loop, block, case, new, parenthesized expr cases
        //TODO: Handle method calls. E.g: f(a,b)
        if (nextTokenMatches(OBJECT_ID)) {
            String name = expectAndGetObjectID();
            return new Id(name);
        } else if (consumeIfMatches(IF)) {
            Expr condition = parseExpr();
            expect(THEN);
            Expr thenBranch = parseExpr();
            expect(ELSE);
            Expr elseBranch = parseExpr();
            expect(FI);
            return new Conditional(condition, thenBranch, elseBranch);
        } else if (nextTokenMatches(NUMBER)) {
            Integer num = expectAndGetNumber();
            return new IntConst(num);
        } else if (nextTokenMatches(STRING)) {
            String str = expectAndGetString();
            return new StringConst(str);
        } else if (consumeIfMatches(TRUE)) {
            return new BoolConst(true);
        } else if (consumeIfMatches(FALSE)) {
            return new BoolConst(false);
        } else {
            error("Unexpected token: " + tokens.peek());
            return null;
        }
    }

    private List<Expr> parseArguments() {
        LinkedList<Expr> exprs = new LinkedList<>();
        expect(LPAREN);
        if (!consumeIfMatches(RPAREN)) {
            exprs.add(parseExpr());
            while (consumeIfMatches(COMMA)) {
                exprs.add(parseExpr());
            }
            expect(RPAREN);
        }
        return exprs;
    }

    private void expect(TokenType tokenType) {
        if (tokens.peek().type != tokenType) {
            error("I was expecting a " + tokenType + " on line " + tokens.peek().line);
        }
        tokens.pop();
    }

    private Token expectAndGet(TokenType tokenType) {
        if (tokens.peek().type != tokenType) {
            error("I was expecting a " + tokenType + " on line " + tokens.peek().line);
        }
        return tokens.pop();
    }

    private String expectAndGetTypeID() {
        return (String)expectAndGet(TYPE_ID).value;
    }

    private String expectAndGetObjectID() {
        return (String)expectAndGet(OBJECT_ID).value;
    }

    private Integer expectAndGetNumber() {
        return (Integer)expectAndGet(NUMBER).value;
    }

    private String expectAndGetString() {
        return (String)expectAndGet(STRING).value;
    }

    private boolean consumeIfMatches(TokenType type) {
        if (tokens.peek().type == type) {
            tokens.pop();
            return true;
        }
        return false;
    }

    private boolean nextTokenMatches(TokenType type) {
        return tokens.peek().type == type;
    }

    private boolean secondTokenMatches(TokenType type) {
        if (tokens.size() < 2) return false;
        return tokens.get(1).type == type;
    }

    private void error(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
