import java.lang.Math;

public class Car {
  private int xPos;
  private int yPos;
  private String name;

  public Car(String newName, int newX, int newY) {
    this.xPos = newX;
    this.yPos = newY;
    this.name = newName;
  }

  public int calc_distance(int x, int y) {
    return(Math.abs(x-this.xPos)+Math.abs(y-this.yPos));
  }

  public void newPos(int x, int y) {
    this.xPos = x;
    this.yPos = y;
  }

  public String getPos() {
    return(this.xPos + ", " + this.yPos);
  }
}
