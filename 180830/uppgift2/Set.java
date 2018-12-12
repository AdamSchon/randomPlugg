import java.lang.Comparable;
import java.lang.Exception;


public class Set<T extends Comparable<T>> {

  private int maxCap;
  private T[] elements;
  private int size = 0;

  public Set(int maxSize) {
    if (maxSize > 0) {
      this.maxCap = maxSize;
      this.elements = (T[]) new Comparable[maxSize];
    } else {
      throw new IllegalArgumentException("Incorrect input");
    }
  }

  public Set() {
    new Set(16);
  }

  public boolean add(T elem) {
    if (this.size == maxCap) {
      throw new SetFullException("Set already full!");
    }

  }

  public class SetFullException<T extends Exception> {
    public SetFullException(String msg) { super(msg);}
  }
}
