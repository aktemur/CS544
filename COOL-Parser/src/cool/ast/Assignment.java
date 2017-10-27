package cool.ast;

public class Assignment extends Expr {
    String name;
    Expr rhs;

    public Assignment(String name, Expr rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Assign: " + name);
        rhs.print(level + 1);
    }
}
