package cool.ast;

public class Not extends Expr {
    Expr expr;

    public Not(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Not:");
        expr.print(level + 1);
    }
}
