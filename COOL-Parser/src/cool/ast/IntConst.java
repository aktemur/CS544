package cool.ast;

public class IntConst extends Expr {
    int value;

    public IntConst(int value) {
        this.value = value;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Int: " + value);
    }
}
