/*
 * Katherine Jacobs
 * V00783178
 * Last Modified Oct31/2018
 * StackEmptyException.java
 * CSC 115 Assignment #3
 */

 public class StackEmptyException extends RuntimeException{

  /**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
   public StackEmptyException(String msg){
     super(msg);
   }

   /**
    * Creates an exception without a message.
    */
    public StackEmptyException(){
      super();
    }
 }
