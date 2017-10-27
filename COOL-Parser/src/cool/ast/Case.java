package cool.ast;

import java.util.List;

public class Case extends Expr {
    Expr expr;
    List<Branch> branches;

    public Case(Expr expr, List<Branch> branches) {
        this.expr = expr;
        this.branches = branches;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Case: ");
        expr.print(level + 1);
        for (Branch branch: branches) {
            branch.print(level + 1);
        }
    }
}
