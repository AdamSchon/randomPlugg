package org.ioopm.planner;

import java.util.*;


/// TODO: lägg till nya bågar --- OBS!! Du måste se till att de
/// nya bågarna används när grafen byggs upp också, vilket sker i addLine().
/// Du får själv lista ut vilken typ av båge som skall användas var.

public class Trip {
    private final Node start;
    private final Node destination;
    private List<Node> route;


    private class Line extends Edge {
      private int line;

      public Line(final Node n1, final Node n2, final int weight, int newLine) {
        super(n1, n2, weight);
        this.line = newLine;
      }

      public int getLine() {
        return(this.line);
      }
    }

    private class StopOver extends Edge {
      public StopOver(final Node n1, final Node n2) {
        super(n1, n2, 8);
      }
    }

    /// If line == [a, b, c] and weights == [8, 3], then
    /// this creates the following network:
    ///
    ///      8     3
    ///   a --- b --- c
    ///
    /// and adds a,b,c to the set of nodes in the network.
    public static void addLine(Set<Node> nodes, Node[] line, int[] weigths, int lineNumber) {
        /// TODO: Extend from Model 1 to Model 3
        ///
        /// Tip: you can ask if foo is in the set with node.contains(foo)
        /// Tip: Examples of node construction can be found in e.g. PrinsEugen.java
        for (int i = 0; i < line.length; ++i) {
            /// n is the n from the algorithm in the description
            Node n = line[i];
            /// existing is E from the algorithm in the description
            Set<Node> existing = Network.getNodesByName(line[i].name());
            /// TODO: write the rest of the logic here
            /// FWIW, the loop below shows how to connect two nodes with an Edge edge
            if (!existing.isEmpty()) {
              Node nPrime = new Node(n.name());
              for (Node n : existing) {
                StopOver edge = new StopOver(n, nPrime);
                n.connectTo(edge);
                nPrime.connectTo(edge);
                nodes.add(nPrime);
              }
              line[i] = nPrime;
            }
        }

        /// Connect the two nodes with a new Edge edge
        for (int i = 0; i < weigths.length; ++i) {
            Node from = line[i];
            Node to = line[i+1];
            Edge edge = new Line(from, to, weigths[i],lineNumber);
            from.connectTo(edge);
            to.connectTo(edge);
            nodes.add(from);
            nodes.add(to);
        }
    }

    /// Calculates the travel time for the proposed route
    public String routeToString() {
        /// TODO: Add route information (times, lines, stop-overs)
        /// Note, you can get information from edges using the info() method

        String result = "";

        final LinkedList<Node> tmp = new LinkedList<>(route);
        Node from = tmp.pop();
        while (tmp.isEmpty() == false) {
            Node to = tmp.pop();

            result += from.name() + "\n";

            result += "Line " + from.getEdgeTo(to).getLine() + ", " + from.getEdgeTo(to).weight() + " minuter \n";

            from = to;
        }
        result += from.name() + "\n";

        return result;
    }

    public int numberOfStopOvers() {
        /// TODO: Finish

        int stopOvers = 0;
        for (int i = 1; i > route.length(); i++) {
          if (!(route[i].getLine() == route[i-1].getLine())) {
            stopOvers++;
          }
        }
        return stopOvers;
    }

    /// No need to change or look at the functions below this line!

    /// Calculates the travel time for the proposed route -- should not need to change this
    public int travelTime() {
        int time = 0;

        final LinkedList<Node> tmp = new LinkedList<>(route);
        Node from = tmp.pop();
        while (tmp.isEmpty() == false) {
            Node to = tmp.pop();

            time += from.getEdgeTo(to).weight();

            from = to;
        }

        return time;
    }

    /// Create a new trip
    public Trip(final Node start, final Node destination) {
        this.start = start;
        this.destination = destination;
    }

    /// Get the intended start
    public Node start() {
        return this.start;
    }

    /// Get the intended destination
    public Node destination() {
        return this.destination;
    }

    /// Sets the proposed route for the trip
    public void setRoute(List<Node> route) {
        this.route = route;
    }
}
