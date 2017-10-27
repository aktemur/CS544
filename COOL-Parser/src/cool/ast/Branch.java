package cool.ast;

public class Branch {
    String name;
    String type;
    Expr expr;

    public Branch(String name, String type, Expr expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    public void print(int level) {
        Util.indent(level);
        System.out.println("Branch: " + name + ": " + type);
        expr.print(level + 1);
    }
}
