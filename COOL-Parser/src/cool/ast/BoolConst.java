package cool.ast;

public class BoolConst extends Expr {
    boolean value;

    public BoolConst(boolean value) {
        this.value = value;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Bool: " + value);
    }
}
