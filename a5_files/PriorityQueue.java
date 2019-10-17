/* Katherine Jacobs
* V00783178
* CSC 115 Assignment #5
* Last Modified Dec 3/2018
*/
import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>> {

	private Heap<E> heap;

	/*
	* Creates new Priority Queue with a heap
	*/
	public PriorityQueue() {
	 this.heap = new Heap<E>();
	}

	/*
	* Removes first item that was inserted into the heap, returns the item
	*/
  public E dequeue() {
		return heap.removeRootItem();
  }

	/*
	* Adds an item into the heap
	*/
 	public void enqueue(E item) {
        heap.insert(item);
    }

	/*
	* Checks if the heap is empty
	*/
  public boolean isEmpty() {
			return heap.isEmpty();
  }

	/*
	* Looks at the item at the front of the queue
	*/
  public E peek() {
		return heap.getRootItem();
  }

    public static void main(String args []) {

		System.out.println("--Testing Priority Queue With ER Patient--");

		PriorityQueue <ER_Patient> p1 = new PriorityQueue <ER_Patient> ();
		System.out.println("--Testing is Empty---");
		System.out.println(p1.isEmpty());
		p1.enqueue(new ER_Patient("Walk-in"));
		System.out.println("--Testing Peek ---");
		System.out.println(p1.peek());
		System.out.println("--Testing is Empty---");
		System.out.println(p1.isEmpty());
		p1.enqueue(new ER_Patient("Major fracture"));
		System.out.println("--Testing Peek ---");
		System.out.println(p1.peek());
		p1.enqueue(new ER_Patient("Chronic"));
		System.out.println("--Testing Dequeue--");
		System.out.println(p1.dequeue());
		
    }
}
