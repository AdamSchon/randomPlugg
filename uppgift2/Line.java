package org.ioopm.planner;

public class Line extends Edge {

  private int line;

  public Line(final Node n1, final Node n2, final int weight, int newLine) {
    super(n1, n2, weight);
    this.line = newLine;
  }
}
