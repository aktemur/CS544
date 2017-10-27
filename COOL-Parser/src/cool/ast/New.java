package cool.ast;

public class New extends Expr {
    String type;

    public New(String type) {
        this.type = type;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("New: " + type);
    }
}
