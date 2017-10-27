package cool.ast;

public class IsVoid extends Expr {
    Expr expr;

    public IsVoid(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("IsVoid:");
        expr.print(level + 1);
    }
}
