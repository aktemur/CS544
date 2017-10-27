package cool.ast;

public class Field extends Feature {
    String name;
    String type;
    Expr initialization;

    public Field(String name, String type, Expr initialization) {
        this.name = name;
        this.type = type;
        this.initialization = initialization;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("Field: " + name + ": " + type);
        if (initialization != null)
            initialization.print(level + 1);
    }
}
