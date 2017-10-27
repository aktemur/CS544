package cool.ast;

public class Binary extends Expr {
    BinaryOperator op;
    Expr left;
    Expr right;

    public Binary(BinaryOperator op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Binary: " + op);
        left.print(level + 1);
        right.print(level + 1);
    }
}
