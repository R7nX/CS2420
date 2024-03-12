package assign07;

public class Edge {
    public Vertex destination;

    public Edge(Vertex destination) {
        this.destination = destination;
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public String toString(){
        return this.destination.toString();
    }
}
