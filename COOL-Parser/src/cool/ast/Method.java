package cool.ast;

import java.util.List;
import java.util.LinkedList;

public class Method extends Feature {
    String name;
    List<Formal> formals;
    String returnType;
    Expr body;

    public Method(String name, List<Formal> formals, String returnType, Expr body) {
        this.name = name;
        this.formals = formals;
        this.returnType = returnType;
        this.body = body;
    }

    @Override
    public void print(int level) {
        Util.indent(level);
        System.out.print("Method: " + name);
        System.out.print(" (");
        for (Formal formal : formals) {
            formal.print();
            System.out.print(", ");
        }
        System.out.println("): " + returnType);
        body.print(level + 1);
    }
}
