package assign07;

import java.util.HashMap;
import java.util.List;

public class Graph<T> extends GraphUtility<T> {
    private final HashMap<T, Vertex<T>> vertices;

    public Graph() {
        vertices = new HashMap<T, Vertex<T>>();
    }

    public void addEdge(T source, T destination) {
        Vertex<T> vertex1;
        if (vertices.containsKey(source))
            vertex1 = vertices.get(source);
        else {
            vertex1 = new Vertex<T>(source);
            vertices.put(source, vertex1);
        }

        Vertex<T> vertex2;
        if (vertices.containsKey(destination))
            vertex2 = vertices.get(destination);
        else {
            vertex2 = new Vertex<T>(destination);
            vertices.put(destination, vertex2);
        }
    }

    public boolean dfs(T srcData, T dstData) {
        //TODO: Finish this fking method
        return false;
    }

    public List<T> bfs(T srcData, T dstData) {
        //TODO: Write this shit
        return null;
    }

    public List<T> topologicalSort() {
        //TODO: Finish this fkin thing alr bro
        return null;
    }

    public String toString() {
        //TODO: Finish this fking method also bro, work ur ass of fker
        return null;
    }

}
