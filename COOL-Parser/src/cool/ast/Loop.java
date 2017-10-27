package cool.ast;

public class Loop extends Expr {
    Expr condition;
    Expr body;

    public Loop(Expr condition, Expr body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Loop: ");
        condition.print(level + 1);
        body.print(level + 1);
    }
}
