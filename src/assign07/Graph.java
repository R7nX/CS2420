package assign07;

import java.util.HashMap;

public class Graph {
    private HashMap<String, Vertex> vertices;

    public Graph(){
        vertices = new HashMap<String, Vertex>();
    }

    public void addEdge(String source, String destination){
        Vertex vertex1;
        if (vertices.containsKey(source))
            vertex1 = vertices.get(source);
        else{
            vertex1 = new Vertex(source);
            vertices.put(source, vertex1);
        }

        Vertex vertex2;
        if (vertices.containsKey(destination))
            vertex2 = vertices.get(destination);
        else{
            vertex2 = new Vertex(destination);
            vertices.put(destination, vertex2);
        }
    }

}
