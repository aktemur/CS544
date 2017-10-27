package cool.ast;

import java.util.List;
import java.util.LinkedList;

public class Klass {
    String name;
    String supername;
    List<Feature> features = new LinkedList<Feature>();

    public Klass(String name, String supername, List<Feature> features) {
        this.name = name;
        this.supername = supername;
        this.features = features;
    }

    public void print(int level) {
        Util.indent(level);
        System.out.println("Class: " + name + " inherits " + supername);
        for(Feature feature: features) {
            feature.print(level + 1);
        }
    }
}
