package org.ioopm.planner;

/////////////////////////////////////////////////////
/// Sets up the network of the first test
/////////////////////////////////////////////////////

public class PrinsEugen {
    static {
        final Node kortedala  = new Node("Kortedala");
        final Node olskroken  = new Node("Olskroken");
        final Node haltalotta = new Node("Halta Lottas krog");
        final Node stsigfrid  = new Node("S:t Sigfrids plan");

        Trip.addLine(Network.nodes,
                     new Node[] { kortedala, olskroken, haltalotta, stsigfrid },
                     new int[] { 5, 7, 4 },
                     2);

        Trip.addLine(Network.nodes,
                     new Node[] { kortedala, haltalotta },
                     new int[] { 10 },
                     1);

    }
}


