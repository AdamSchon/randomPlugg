package org.ioopm.planner;

import java.util.*;

public class Node {
    private final String name;
    private final Set<Edge> connections;

    private Node(final String name, final Set<Edge> connections) {
        this.name = name;
        this.connections = connections;
    }

    public Node(final String name) {
        this(name, new HashSet<Edge>());
    }

    public String name() {
        return this.name;
    }

    public Edge getEdgeTo(Node n) {
        for (Edge e : this.connections) {
            if (e.otherEndOf(this) == n) return e;
        }
        return null;
    }
    
    public void connectTo(Edge edge) {
        this.connections.add(edge);
    }

    public Set<Node> adjacent() {
        Set<Node> adjacent = new HashSet<Node>();
        for (Edge e : this.connections) {
            adjacent.add(e.otherEndOf(this));
        }
        return adjacent;
    }

    public Set<Edge> connections() {
        return connections;
    }

    public String toString() {
        return this.name;
    }
}
