package assign07;

public class Edge<T> {
    public Vertex<T> destination;

    public Edge(Vertex<T> destination) {
        this.destination = destination;
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public String toString(){
        return this.destination.toString();
    }
}
