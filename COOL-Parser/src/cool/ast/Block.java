package cool.ast;

import java.util.List;

public class Block extends Expr {
    List<Expr> exprs;

    public Block(List<Expr> exprs) {
        this.exprs = exprs;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Block: ");
        for (Expr expr: exprs) {
            expr.print(level + 1);
        }
    }
}
