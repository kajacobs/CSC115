/*
 * Katherine Jacobs
 * V00783178
 * Last Modified Oct31/2018
 * StringStack.java
 * CSC 115 Assignment #3
 */

public class StringStack {

	public Node head;
	public static int count = 0;

	// creates and empty stack
	public StringStack() {
	}

	// returns true if the stack is empty, false if not.
	public boolean isEmpty() {
		return (head == null);
	}

	// Returns top item on the stack, and removes it.
	public String pop() throws StackEmptyException {
		if (isEmpty()) throw new StackEmptyException("Stack is Empty");
			String popvalue = head.value;
			head = head.next;
			count--;
			return popvalue;
	}

	//Returns the top item on the stack without removing it.
	public String peek() throws StackEmptyException {
		if (isEmpty()) throw new StackEmptyException("Stack is Empty");
			return head.value;
	}

	//Pushes a String onto the stack
	public void push(String item) {
		Node oldHead = head;
		head = new Node();
		head.value = item;
		head.next = oldHead;
		count++;
	}

	//Empties the stack
	public void popAll() {
		head = null;
		count = 0;
	}


	public static void main(String[] args){

		StringStack test1 = new StringStack();
		test1.push("1");
		test1.push("2");
		test1.push("3");
		test1.pop();
		System.out.println(count);
		System.out.println(test1.pop());
		System.out.println(test1.peek());
		test1.popAll();
		System.out.println(count);
	}
}
