/*
 * Katherine Jacobs
 * V00783178
 * Last Modified Oct31/2018
 * Node.java
 * CSC 115 Assignment #3
 */

public class Node {

  String value;
  Node next;

  // default constructor
  public Node(){
    this.value = null;
    this.next = null;
  }

// Constructor: takes in item and sets next to null
  public Node(String n){
    this.value = n;
    this.next = null;
  }

//Constructor: takes in item and sets next to another item
  public Node(String n, Node y){
    this.value = n;
    this.next = y;
  }
}
