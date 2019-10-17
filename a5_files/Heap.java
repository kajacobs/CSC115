/* Katherine Jacobs
* V00783178
* CSC 115 Assignment #5
* Last Modified Dec 3/2018
*/

import java.util.NoSuchElementException;
import java.util.Vector;

public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;
	private int pointer;
	private int root = 1;

	/*
	* Creates a heap using Vector<E> and initializes the size to 20; will increase 20 units
	* when Vector<E> is full.
	* Pointer is initialized to 1, index 0 will always be null.
	*/
	public Heap() {
		this.heapArray = new Vector<E>(20,20);
		pointer = 1;
		heapArray.add(null);
	}

	/*
	* Checks whether heap is empty.
	*/
	public boolean isEmpty(){
		return (pointer == 1);
	}

	/*
	* Returns the number of elements in the Heap
	*/
	public int size(){
		return pointer-1;
	}

	/*
	* Inserts an item into the Heap
	*/
	public void insert(E item){
		heapArray.insertElementAt(item, pointer);
		if (pointer != 1){
				bubbleUp();
		}
		pointer++;
	}

	/*
	* Removes root item from Heap.
	*/
	public E removeRootItem(){
		E rootitem = heapArray.get(root);
		heapArray.setElementAt(heapArray.get(pointer-1), root);
		heapArray.removeElementAt(pointer-1);
		pointer--;
		if (pointer > 1){
			bubbleDown();
		}

		return rootitem;
	}

	/*
	* Returns which item is currently at the root.
	*/
	public E getRootItem(){
		return heapArray.get(1);
	}

	/*
	* Traverses down the heap and sorts into a MaxHeap starting at the root
	*/
	private void bubbleDown(){
		int current = root;
		E tempswap;
		int child;
		while (true){
			this.print_vector();
			if (this.size() < 2*current){
				break;
			} else if (this.size() == 2*current){
					child = getLeftChild(current);
			} else {
					child = heapArray.get(getLeftChild(current)).compareTo(heapArray.get(getRightChild(current))) > 0 ? getLeftChild(current) : getRightChild(current);
			}
			if(heapArray.get(current).compareTo(heapArray.get(child)) < 0){
				tempswap = heapArray.get(child);
				heapArray.setElementAt(heapArray.get(current), child);
				heapArray.setElementAt(tempswap, current);
				current = child;
			} else {
				break;
			}
		}
	}

	/*
	* Traverses up the heap and sorts into a Maxheap starting at the last element.
	*/
	private void bubbleUp(){
		int current = pointer;
		E tempswap;
		while (current > 1 && heapArray.get(current).compareTo(heapArray.get(getParentindex(current))) > 0){
			tempswap = heapArray.get(getParentindex(current));
			heapArray.setElementAt(heapArray.get(current), getParentindex(current));
			heapArray.setElementAt(tempswap, current);
			current = getParentindex(current);
		}
	}

	/******** Tool methods ********/
	private int indexOf(E p){
		for (int i = 1; i < heapArray.capacity(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}

	private int getParentindex(int child){
		return child/2;
	}

	private int getLeftChild(int parent){
		return parent*2;
	}

	private int getRightChild(int parent){
		return (parent*2)+1;
	}

	/********  DEBUG USE methods ********/
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 1; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}

	public static void main(String args []){

		System.out.println("--Testing Heap with Ints---\n");
		Heap <Integer> heaptest = new Heap<Integer>();
		System.out.println("--Testing is Empty---");
		System.out.println(heaptest.isEmpty());
		System.out.println("\n--Testing Size--");
		System.out.println(heaptest.size());

		heaptest.insert(1);
		heaptest.insert(5);
		heaptest.insert(4);
		heaptest.insert(3);
		heaptest.insert(2);
		heaptest.print_vector();
		System.out.println("\n--Testing Remove Root Item--");
		heaptest.removeRootItem();
		System.out.println("");
		heaptest.print_vector();

		System.out.println("--Testing With ER Patient---\n");
		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		System.out.println("--Testing is Empty---");
		System.out.println(hp.isEmpty());
		hp.insert(new ER_Patient ("Chronic"));
		hp.insert(new ER_Patient("Life-threatening"));
		hp.insert(new ER_Patient("Major fracture"));
		hp.insert(new ER_Patient("Chronic"));
		hp.insert(new ER_Patient("Walk-in"));
		hp.insert(new ER_Patient("Major fracture"));
		hp.insert(new ER_Patient("Life-threatening"));
		System.out.println("\n --Testing Get Root Item--");
		System.out.println(hp.getRootItem());
		System.out.println("");
		hp.print_vector();
		System.out.println("\n --Testing Remove Root Item--");
		System.out.println(hp.removeRootItem());
		hp.print_vector();
		System.out.println("\n--Testing Size--");
		System.out.println(hp.size() +"\n");



	} // end of Main Class
} // end of Heap.Java
