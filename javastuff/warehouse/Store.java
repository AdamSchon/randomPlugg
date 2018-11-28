import java.util.LinkedList;

public class Store {
  Register[] registers;

  public Store(int nrOfRegisters) {
    registers = new Register[nrOfRegisters];
    for(int i=0; i<nrOfRegisters;i++){
      this.registers[i] = new Register();
    }
    registers[0].open();
  }

  public double getAverageQueueLength() {
    double len = 0;
    double nr = 0;
    for (int i=0; i<registers.length; i++) {
      if (registers[i].isOpen()) {
        len = len + registers[i].getQueueLength();
        nr++;
      }
    }
    return(len/nr);
  }

  public void newCustomer(Customer c) {
    Register shortest = registers[0];
    for (int i=1; i<registers.length; i++) {
      if (registers[i].isOpen() && shortest.getQueueLength() > registers[i].getQueueLength()) {
        shortest = registers[i];
      }
    }
    shortest.addToQueue(c);
  }

  public void step() {
    for (int i=0; i<registers.length; i++) {
      registers[i].step();
    }
  }

  public void openNewRegister() {
    for (int i=0; i<registers.length; i++) {
      if (!registers[i].isOpen()) {
        registers[i].open();
        break;
      }
    }
  }

  public LinkedList<Customer> getDoneCustomers() {
    LinkedList<Customer> Customers = new LinkedList<Customer>();
    int j = 0;
    for (int i=0; i<registers.length; i++) {
      if (registers[i].currentCustomerIsDone()) {
        Customers.add(registers[i].removeCurrentCustomer());
      }
    }
    return(Customers);
  }

  public String toString() {
    String res = "";
    for (int i=0; i<registers.length; i++) {
      res = res + registers[i].toString() + "\n";
    }
    return(res);
  }

  public static void main(String args[]) {
    Store s = new Store(5);
    Customer c = new Customer(1,1);
    Customer cc = new Customer(2,2);
    s.newCustomer(c);
    s.openNewRegister();
    s.newCustomer(cc);
    System.out.println(s.getAverageQueueLength());
    s.step();
    System.out.println(s.getDoneCustomers());
    System.out.println(s.getAverageQueueLength());
  }
}
