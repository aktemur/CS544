package cool.ast;

import java.util.List;

public class DynamicDispatch extends Expr {
    Expr receiver;
    String name;
    List<Expr> arguments;

    public DynamicDispatch(Expr receiver, String name, List<Expr> arguments) {
        this.receiver = receiver;
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("DynDispatch: " + name);
        receiver.print(level + 1);
        for (Expr arg: arguments) {
            arg.print(level + 1);
        }
    }
}
