package assign07;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 *
 * @author Erin Parker
 * @version March 3, 2022
 */
public class Vertex<T> {

    // used to id the Vertex
    private T name;

    // adjacency list
    private LinkedList<Edge<T>> adj;
    public int indegree;

    /**
     * Creates a new Vertex object, using the given name.
     *
     * @param name - string used to identify this Vertex
     */
    public Vertex(T name) {
        this.name = name;
        this.adj = new LinkedList<Edge<T>>();
        this.indegree = 0;
    }

    /**
     * @return the string used to identify this Vertex
     */
    public T getName() {
        return this.name;
    }

    /**
     * Adds a directed edge from this Vertex to another.
     *
     * @param otherVertex - the Vertex object that is the destination of the edge
     */
    public void addEdge(Vertex<T> otherVertex) {
        adj.add(new Edge<>(otherVertex));
        otherVertex.indegree++;
    }

    /**
     * @return a iterator for accessing the edges for which this Vertex is the source
     */
    public Iterator<Edge<T>> edges() {
        return adj.iterator();
    }

    public LinkedList<Edge<T>> getNeighbors(){
        return adj;
    }

    /**
     * Generates and returns a textual representation of this Vertex.
     */
    public String toString() {
        String s = "Vertex " + name + " adjacent to vertices ";
        Iterator<Edge<T>> itr = adj.iterator();
        while(itr.hasNext())
            s += itr.next() + "  ";
        return s;
    }
}