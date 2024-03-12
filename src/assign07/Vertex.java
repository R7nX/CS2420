package assign07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex {
    private String name;
    private LinkedList<Edge> adj;

    public Vertex(String name){
        this.name = name;
        this.adj = new LinkedList<>();
    }

    public String getName(){
        return this.name;
    }

    public void addEdge(Vertex otherVertex){
        adj.add(new Edge(otherVertex));
    }

    public Iterator<Edge> edges(){
        return adj.iterator();
    }

    public String toString(){
        //TODO: Finish this fkin method bro, dont be fkin lazy
        return null;
    }
}
