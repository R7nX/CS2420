package assign07;

import java.util.*;

public class Graph<T> {
    private HashMap<T, Vertex<T>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public void addEdge(T name1, T name2) {
        Vertex<T> vertex1;
        // if vertex already exists in graph, get its object
        if (vertices.containsKey(name1))
            vertex1 = vertices.get(name1);
            // else, create a new object and add to graph
        else {
            vertex1 = new Vertex<>(name1);
            vertices.put(name1, vertex1);
        }

        Vertex<T> vertex2;
        if (vertices.containsKey(name2))
            vertex2 = vertices.get(name2);
        else {
            vertex2 = new Vertex<>(name2);
            vertices.put(name2, vertex2);
        }

        // add new directed edge from vertex1 to vertex2
        vertex1.addEdge(vertex2);
    }

    public Vertex<T> findVertex(T data) {
        for (T vertex : vertices.keySet()) {
            if (vertex.equals(data))
                return vertices.get(data);

        }
        return null;
    }

    public boolean dfs(T srcData, T dstData) {
        if (!vertices.containsKey(srcData) || !vertices.containsKey(dstData))
            throw new IllegalArgumentException("The srcData or dstData was not found");

        Vertex<T> srcVertex = vertices.get(srcData);
        Vertex<T> dstVertex = vertices.get(dstData);

        if (srcVertex == null || dstVertex == null)
            return false;

        HashSet<Vertex<T>> visited = new HashSet<>();
        return dfsRecursive(srcVertex, dstVertex, visited);
    }

    private boolean dfsRecursive(Vertex<T> current, Vertex<T> dstVertex, HashSet<Vertex<T>> visited) {
        // If the current vertex is the destination vertex, return true
        if (current.equals(dstVertex))
            return true;

        // Assuming you have a method to get neighbors of a vertex
        for (Edge<T> edge : current.getNeighbors()) {
            Vertex<T> neighbor = edge.getDestination();
            if (!visited.contains(neighbor)) {
                visited.add(neighbor); // Mark the neighbor as visited
                if (dfsRecursive(neighbor, dstVertex, visited))
                    return true; // If destination found, return true
            }
        }

        return false; // Destination not found
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

            var Itr = current.edges();
            while (Itr.hasNext()) {
                Edge<T> curr = Itr.next();
                Vertex<T> neighbor = curr.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }

            // Reconstruct the path
            if (current.equals(dstVertex)) {
                Vertex<T> node = current;
                while (node != null) {
                    path.add(0, node.getName());
                    node = parentMap.get(node);
                }
                return path;
            }
        }
        throw new IllegalArgumentException("No path found");
    }

    /*
    Psudo code for this (delete later)

    for (each vertex in graph.vertices)
        if (vertex.indegree == 0)
            Q.enqueue();
        while (Q is not empty)
            u = Q.dequeue(); // u is the next ordered vertex
            for (each v neighbor of u)
                v.indegree--;
                if (v.indegree == 0)
                    Q.enqueue(v);
     */
    public List<T> topologicalSort() {
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<T> result = new ArrayList<>();

        // Enqueue vertices with indegree 0
        for (Vertex<T> vertex : vertices.values()) {
            if (vertex.indegree == 0) {
                queue.offer(vertex);
            }
        }

        // Process vertices in the queue
        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            result.add(current.getName());

//         Update indegrees of neighbors and enqueue them if indegree becomes 0
            var neighbors = current.edges();
            while (neighbors.hasNext()) {
                Edge<T> edge = neighbors.next();
                Vertex<T> neighbor = edge.getDestination();
                neighbor.indegree--;

                if (neighbor.indegree == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check if a cycle exists (if all vertices are not visited)
        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }
        return result;
    }
}

