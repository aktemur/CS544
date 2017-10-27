package cool.ast;

public class Let extends Expr {
    String name;
    String type;
    Expr initialization;
    Expr body;

    public Let(String name, String type, Expr initialization, Expr body) {
        this.name = name;
        this.type = type;
        this.initialization = initialization;
        this.body = body;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Let: " + name + ": " + type);
        if (initialization != null)
            initialization.print(level + 1);
        body.print(level + 1);
    }
}
