package cool.ast;

public class Formal {
    String name;
    String type;

    public Formal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void print() {
        System.out.print(name + ": " + type);
    }
}
