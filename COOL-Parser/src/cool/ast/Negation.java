package cool.ast;

public class Negation extends Expr {
    Expr expr;

    public Negation(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Negate:");
        expr.print(level + 1);
    }
}
