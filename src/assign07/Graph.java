package assign07;

import java.util.HashMap;

public class Graph<T> {
    private HashMap<T, Vertex<T>> vertices;

    public Graph(){
        vertices = new HashMap<T, Vertex<T>>();
    }

    public void addEdge(T source, T destination){
        Vertex<T> vertex1;
        if (vertices.containsKey(source))
            vertex1 = vertices.get(source);
        else{
            vertex1 = new Vertex<T>(source);
            vertices.put(source, vertex1);
        }

        Vertex<T> vertex2;
        if (vertices.containsKey(destination))
            vertex2 = vertices.get(destination);
        else{
            vertex2 = new Vertex<T>(destination);
            vertices.put(destination, vertex2);
        }
    }

}
