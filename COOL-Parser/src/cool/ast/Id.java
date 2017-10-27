package cool.ast;

public class Id extends Expr {
    String name;

    public Id(String name) {
        this.name = name;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Id: " + name);
    }
}
