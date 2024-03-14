package assign07;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

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

    private Vertex<T> findVertex (T data){
        for (Vertex<T> vertex : vertices.values()){
            if (vertex.equals(data))
                return vertex;
        }
        throw new IllegalArgumentException("Vertex with data " + data + " not found");
    }

    public boolean dfs(T srcData, T dstData) {
        //TODO: Finish this fking method
        return false;
    }

    public List<T> bfs(T srcData, T dstData) {
       Vertex<T> srcVertex = findVertex(srcData);
       Vertex<T> dstVertex = findVertex(dstData);

        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        Map<Vertex<T>, Vertex<T>> parentMap = new HashMap<>();
        List<T> path = new ArrayList<>();

        queue.offer(srcVertex);
        visited.add(srcVertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();

            while (current.edges().hasNext()) {
                Edge<T> curr = current.edges().next();
                Vertex<T> neighbor = curr.destination;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }

            if (current == dstVertex){
                Vertex<T> node = current;
                while (node != null){
                    path.add(0, node.getName());
                    node = parentMap.get(node);
                }
                return path;
            }
        }
        throw new IllegalArgumentException("No path found");
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
