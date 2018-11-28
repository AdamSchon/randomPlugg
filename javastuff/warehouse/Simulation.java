import java.util.Random;
import java.util.LinkedList;

public class Simulation {
  Store store;
  int time;
  double intensity;
  int maxGroceries;
  int threshholdForNewRegisters;

  public Simulation() {
    this.store = new Store(5);
    this.time = 0;
    this.intensity = 0.5;
    this.maxGroceries = 10;
    this.threshholdForNewRegisters = 3;
  }

  public void step() {
    this.time++;
    store.step();
    if (Math.random() < this.intensity) {
      store.newCustomer(new Customer(this.time, this.maxGroceries/*Math.round(Math.random()*(this.maxGroceries-1)+1)*/));
    }
    if (store.getAverageQueueLength() > threshholdForNewRegisters) {
      store.openNewRegister();
    }
    LinkedList<Customer> doneCustomers = store.getDoneCustomers();
  }

  public String toString() {
    return(store.toString());
  }
}
