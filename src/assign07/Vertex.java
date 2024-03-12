package assign07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex<T> {
    private T data;
    private LinkedList<Edge<T>> adj;

    public Vertex(T data){
        this.data = data;
        this.adj = new LinkedList<>();
    }

    public T getName(){
        return this.data;
    }

    public void addEdge(Vertex<T> otherVertex){
        adj.add(new Edge(otherVertex));
    }

    public Iterator<Edge<T>> edges(){
        return adj.iterator();
    }

    public String toString(){
        //TODO: Finish this fkin method bro, dont be fkin lazy
        return null;
    }
}
