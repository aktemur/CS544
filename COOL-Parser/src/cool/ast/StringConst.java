package cool.ast;

public class StringConst extends Expr {
    String value;

    public StringConst(String value) {
        this.value = value;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("String: \"" + value + "\""); //TODO: Unescape the value
    }
}
