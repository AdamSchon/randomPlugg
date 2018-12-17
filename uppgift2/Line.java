package org.ioopm.planner;

public class Line extends Edge {

  private int line;

  public Line(final Node n1, final Node n2, final int weight, int newLine) {
    super(final Node n1, final Node n2, final int weight);
    this.line = newLine;
  }
}
