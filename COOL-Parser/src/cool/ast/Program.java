package cool.ast;

import java.util.LinkedList;
import java.util.List;

public class Program {
    private List<Klass> classes;

    public Program(List<Klass> classes) {
        this.classes = classes;
    }

    public void print() {
        System.out.println("Program:");
        for (Klass klass: classes) {
            klass.print(1);
        }
    }
}
