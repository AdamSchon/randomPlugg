import java.util.Queue;
import java.util.LinkedList;

public class Queuee {
  Queue<Customer> queue;

  public Queuee(){
    this.queue = new LinkedList<Customer>();
  }

  public int length() {
    return(queue.size());
  }

  public void enqueue(Customer c){
    queue.add(c);
  }

  public Customer dequeue(){
  return queue.poll();
  }

  public Customer first(){
    return(queue.peek());
  }

  public static void main(String[] args){
    Customer c = new Customer(1,1);
    Queuee q = new Queuee();
    q.enqueue(c);
    System.out.println(q.first());
    System.out.println(q.length());
    q.dequeue();
    System.out.println(q.first());
  }
}
