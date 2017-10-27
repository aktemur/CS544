package cool.ast;

import java.util.List;

public class StaticDispatch extends Expr {
    Expr receiver;
    String targetClass;
    String name;
    List<Expr> arguments;

    public StaticDispatch(Expr receiver, String targetClass, String name, List<Expr> arguments) {
        this.receiver = receiver;
        this.targetClass = targetClass;
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.println("StaDispatch: " + name + "@" + targetClass);
        receiver.print(level + 1);
        for (Expr arg: arguments) {
            arg.print(level + 1);
        }
    }
}
