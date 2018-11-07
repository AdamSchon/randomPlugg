package org.ioopm.planner;

public class Edge {
    /// Nodes this edge connects
    private final Node n1;
    private final Node n2;
    /// The travel time for this edge in minutes
    private final int weight;

    public Edge(final Node n1, final Node n2, final int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;
    }

    public final int weight() {
        return this.weight;
    }

    public final Node otherEndOf(final Node n) {
        if (this.n1 == n) return this.n2;
        if (this.n2 == n) return this.n1;
        throw new RuntimeException("Error in graph manipulation");
    }

    public String info() {
        return this.weight + " minuter";
    }
    
    public final String toString() {
        return "Don't use the toString() method!";
    }
}
