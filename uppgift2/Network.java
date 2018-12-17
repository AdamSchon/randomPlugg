package org.ioopm.planner;

/////////////////////////////////////////////////////
/// You do not need to understand this code!
/////////////////////////////////////////////////////

import java.util.*;

public class Network {
    public static final Set<Node> nodes = new HashSet<Node>();

    public static Set<Node> getNodesByName(String name) {
        Set<Node> result = new HashSet<Node>();
        for (Node n : nodes) {
            if (n.name().equals(name)) result.add(n);
        }
        return result;
    }

    private static Node dijkstraGetClosest(Node start, HashMap<Node, Integer> distances) {
        int distance = Integer.MAX_VALUE;
        Node end = start;
        for (Edge e : start.connections()) {
            final Node n = e.otherEndOf(start);
            final int weight = e.weight();
            if (distances.get(n) + weight < distance) {
                distance = distances.get(n) + weight;
                end = n;
            }
        }
        return end;
    }

    public static List<Node> dijkstra(final Trip trip) {
        final Node from = trip.start();
        final Node to = trip.destination();
        // Mark all nodes unvisited. Create a set of all the
        // unvisited nodes called the unvisited set.
        final Set<Node> unvisited = new HashSet<>(nodes);

        // Assign to every node a tentative distance value: set it
        // to zero for our initial node and to infinity for all
        // other nodes.
        final HashMap<Node, Integer> distances = new HashMap<>();
        for (Node n : unvisited) {
            distances.put(n, Integer.MAX_VALUE);
        }
        distances.put(from, 0);
        // Set the initial node as current.
        Node current = from;
        int distance = 0;

        while (true) {
            // For the current node, consider all of its unvisited
            // neighbors and calculate their tentative distances
            // through the current node.
            final Set<Edge> connections = current.connections();
            for (Edge e : connections) {
                final Node n = e.otherEndOf(current);
                if (unvisited.contains(n) == false) continue;

                // Compare the newly calculated tentative distance to
                // the current assigned value and assign the smaller
                // one.
                final int weight = e.weight();
                if (distances.get(n) > distance + weight) {
                    distances.put(n, distance + weight);
                }
            }

            // When we are done considering all of the unvisited
            // neighbors of the current node, mark the current node as
            // visited and remove it from the unvisited set.
            unvisited.remove(current);

            // If the destination node has been marked visited then
            // stop. The algorithm has finished.
            if (unvisited.contains(to) == false) break;

            // Otherwise, select the unvisited node that is marked
            // with the smallest tentative distance, set it as the new
            // "current node", and go back to step 3.
            Node closestToInitial = to;
            for (Node n : unvisited) {
                if (distances.get(closestToInitial) > distances.get(n)) {
                    closestToInitial = n;
                }
            }
            current = closestToInitial;
            distance = distances.get(current);
        }

        final List<Node> path = new LinkedList<>();
        path.add(0, to);
        do {
            final Node n = dijkstraGetClosest(path.get(0), distances);
            path.add(0, n);
        } while (path.get(0) != from);
        trip.setRoute(path);
        return path;
    }
}
