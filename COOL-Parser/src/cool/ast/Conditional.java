package cool.ast;

public class Conditional extends Expr {
    Expr condition;
    Expr thenBranch;
    Expr elseBranch;

    public Conditional(Expr condition, Expr thenBranch, Expr elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("If:");
        condition.print(level + 1);
        thenBranch.print(level + 1);
        elseBranch.print(level + 1);
    }
}
