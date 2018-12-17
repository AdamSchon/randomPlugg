package org.ioopm.planner;

public class TripPlanner {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("The program takes as input " + 
                               "the absolute name of a class that sets" + 
                               " up the graph network, e.g., 'org.ioopm.planner.PrinsEugen'.");
            return;
        }

        try {
            Class.forName(args[0]);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        Network network = new Network();

        Trip best = null;
        
        for (Node start : Network.getNodesByName("Kortedala")) {
            for (Node stop : Network.getNodesByName("S:t Sigfrids plan")) {
                Trip trip = new Trip(start, stop);
                Network.dijkstra(trip);

                if (best == null || best.travelTime() > trip.travelTime()) {
                    best = trip;
                }
            }
        }
                    
        System.out.println(best.routeToString());
        System.out.println("Total restid: " + best.travelTime() + " minuter, " + best.numberOfStopOvers() + " byten\n");
    }
}
