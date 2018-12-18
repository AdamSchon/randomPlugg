import java.lang.Math;

public class CarFinder {

  private Car<List> Cars;

  public CarFinder(){

  }

  public void addCar(Car c) {
    Cars.add(c);
  }

  public Car findClosestCar(int x, int y) {
    Car closestCar;
    int closest = 99999;
    for (Car c : Cars) {
      if (c.calc_distance(x,y) < closest) {
        closestCar = c;
      }
    }
    return c;
  }

  public static void main(String [] args) {
    CarFinder CF = new CarFinder();
    for (int i = 0; i < 10; i++) {
      Car newCar = new Car(i.toString(), Math.random()*10, Math.random()*10);
    }

    Car closest = CF.findClosestCar(1,1);
    System.out.println("Closest car to 1,1 is " + closest.getPos());
    Car closest = CF.findClosestCar(7,7);
    System.out.println("Closest car to 7,7 is " + closest.getPos());
  }
}
