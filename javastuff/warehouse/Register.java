public class Register {
  private boolean open;
  private Queuee queue;

  public Register() {
    this.open = false;
    this.queue = new Queuee();
  }

  public void open() {
  this.open = true;
  }

  public void close() {
  this.open = false;
  }

  public boolean isOpen() {
    return(this.open);
  }

  public void step() {
    if(this.hasCustomers()) {
      queue.first().serve();
    }
  }

  public boolean hasCustomers(){
    if (queue.length() > 0) {
      return(true);
    } else {
      return(false);
    }
  }

  public boolean currentCustomerIsDone() {
    if (this.hasCustomers()){
      return(queue.first().isDone());
    } else {
      return(false);
    }
  }

  public void addToQueue(Customer c) {
    queue.enqueue(c);
  }

  public Customer removeCurrentCustomer() {
    return(queue.dequeue());
  }

  public int getQueueLength() {
    return(queue.length());
  }

  public String toString() {
      if (this.isOpen()) {
        if (this.getQueueLength() > 0){
          String res = queue.first().toString();
          for (int i = 1; i < this.getQueueLength(); i++) {
            res = res + "@";
          }
          return(res);
        }
        return("O [ ]");
      }
      return("X [ ]");
  }

  public static void main(String[] args){
    Register r = new Register();
    Customer c = new Customer(1,1);
    r.addToQueue(c);
    System.out.println(r.currentCustomerIsDone());
    r.step();
    System.out.println(r.currentCustomerIsDone());
    r.removeCurrentCustomer();
  }
}
