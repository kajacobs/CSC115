/*
 * Katherine Jacobs
 * V00783178
 * Last Modified Oct31/2018
 * ArithExpression.java
 * CSC 115 Assignment #3
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.Object;

public class ArithExpression {

	public TokenList postfixTokens;
	public TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 ||
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	public void infixToPostfix() {
		StringStack mystack = new StringStack();
		postfixTokens  = new TokenList(infixTokens.size());

		for(int i=0; i<infixTokens.size();i++){

			//if token is an operand
			if(isOperand(infixTokens.list[i])==0){
				postfixTokens.append(infixTokens.list[i]);
			}

			// if token is a "("
			if(isOperand(infixTokens.list[i])==2){
				mystack.push(infixTokens.list[i]);
			}

			// if token is a ")"
			if(isOperand(infixTokens.list[i])==3){
				while(!mystack.isEmpty() && !mystack.head.value.equals("(")){
					postfixTokens.append(mystack.pop());
				}
				if(mystack.head.value.equals("(")){
					mystack.pop();
				}
			}

			// if token is an operator
			if(isOperand(infixTokens.list[i]) == 1 && mystack.isEmpty()){
 			  mystack.push(infixTokens.list[i]);
 			}
 				else if(isOperand(infixTokens.list[i]) == 1 && !mystack.isEmpty()){
					if (evalPrecedence(infixTokens.list[i]) == 2){
					 while(!mystack.isEmpty() && !mystack.head.value.equals("(")){
							if(evalPrecedence(mystack.head.value) <= 2 || evalPrecedence(mystack.head.value)==3){
								postfixTokens.append(mystack.pop());
							}
					 }
						if(i!=infixTokens.size()){
							mystack.push(infixTokens.list[i]);
						}
							else if(i==infixTokens.size()){
								postfixTokens.append(infixTokens.list[i]);
							}
			 	} // end of if
				 else if(evalPrecedence(infixTokens.list[i]) == 1){
						while((!mystack.isEmpty() && !mystack.head.value.equals("("))&&
						(evalPrecedence(mystack.head.value)<= 1||evalPrecedence(mystack.head.value)==3)){
							postfixTokens.append(mystack.pop());
						} // end of while
						if(i!=infixTokens.size()){
				 			mystack.push(infixTokens.list[i]);
				 		} else if(i==infixTokens.size()){
				 				postfixTokens.append(infixTokens.list[i]);
							}
				  } // end of else if
					else if(evalPrecedence(infixTokens.list[i]) == 3){
						if(i!=infixTokens.size()){
							mystack.push(infixTokens.list[i]);
						} else if(i==infixTokens.size()){
							postfixTokens.append(infixTokens.list[i]);
							}
					}
			 } // end of if's
		} // end of for loop

		//empty stack into postFix
		while(!mystack.isEmpty()){
			postfixTokens.append(mystack.pop());
	}
} // end of infixToPostfix

// Returns ArithExpression as an infix expression
	public String getInfixExpression() {
		StringBuilder infix = new StringBuilder();

		for(int i=0; i<infixTokens.size();i++){
			if(i+1 != infixTokens.size()){
				infix.append(infixTokens.list[i]);
				infix.append(" ");
			} else {
				infix.append(infixTokens.list[i]);
				}
		}
		return infix.toString();
	} // end of getInfixExpression

	// Returns ArithExpression as a postFix expression
	public String getPostfixExpression() {
	 StringBuilder postfix = new StringBuilder();

	 for(int i=0; i<postfixTokens.size(); i++){
		 if (i+1 != postfixTokens.size()){
			 postfix.append(postfixTokens.list[i]);
			 postfix.append(" ");
		 } else {
			 postfix.append(postfixTokens.list[i]);
		 }
	 }
	 return postfix.toString();
 } // end of getPostfixExpression

	//Evaluates postfix expression and returns value as a double
	public double evaluate() throws InvalidExpressionException {
		StringStack evalstack = new StringStack();
		 double op1;
		 double op2;
		 double result = 0;
		 try{
		for(int i=0; i<postfixTokens.size();i++){
			if(isOperand(postfixTokens.list[i])==0){
				evalstack.push(postfixTokens.list[i]);
			}
			if(isOperand(postfixTokens.list[i]) == 1){
				op2 = Double.parseDouble(evalstack.pop());
				op1 = Double.parseDouble(evalstack.pop());

	  		switch (postfixTokens.list[i]){
					case "+":
						result = op1 + op2;
						break;
					case "-":
						result = op1 - op2;
						break;
					case "*":
						result = op1*op2;
						break;
					case "/":
						result = op1/op2;
						break;
					case "^":
						result = Math.pow(op1, op2);
				}
				evalstack.push(String.valueOf(result));
			}
		}
		result = Double.parseDouble(evalstack.pop());
	}
 	 catch (Exception e){
		throw new InvalidExpressionException();
	}
		return result;
	} // end of evaluate

  // helper method to determine operator
	public static int isOperand(String n){
		int results = 0;
		switch (n){
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
				results = 1;
				break;
			case "(":
			results = 2;
				break;
			case ")":
				results = 3;
				break;
		}
		return results;
	} // end of isOperand

	//helper method to determine precedence of operators
	public static int evalPrecedence(String n){
		int results = 0;
		switch (n) {
			case "*":
			case "/":
			case "%":
				results = 1;
				break;
			case "+":
			case "-":
				results = 2;
				break;
			case "^":
				results = 3;
				break;
		}
			return results;
	} // end of evalPrecendence


	public static void main(String[] args) {

		/*testing of isOperand() helper method
		*/

 		String test1 = "+";
 		String test2 = "/";
 		String test3 = "3";
 		String test4 = "(";

 		System.out.println(isOperand(test1));
 		System.out.println(isOperand(test2));
 		System.out.println(isOperand(test3));
 		System.out.println(isOperand(test4));

 		/*Testing of evalPrecedence() helper method
 		*/
 		String test5 = "*";
 		String test6 = "/";
 		String test7 = "+";
 		String test8 = "^";

 		System.out.println(evalPrecedence(test5));
 		System.out.println(evalPrecedence(test6));
 		System.out.println(evalPrecedence(test7));
 		System.out.println(evalPrecedence(test8));


	/*Testing of getInfixExpression() and getPostfixExpression()
	*/
		String [] test = {"2 + 5", "2 + 5 - 2","(2 + 50 / 5) * 2 ^2 + 20 / 4 + 2",	"(2 5) + 12"};

		ArithExpression a1 = new ArithExpression(test[0]);
		System.out.println(a1.getInfixExpression());
		System.out.println(a1.getPostfixExpression());

		ArithExpression a2 = new ArithExpression(test[1]);
		System.out.println(a2.getInfixExpression());
		System.out.println(a2.getPostfixExpression());

		ArithExpression a3 = new ArithExpression(test[2]);
		System.out.println(a3.getInfixExpression());
  	System.out.println(a3.getPostfixExpression());

		ArithExpression a6 = new ArithExpression(test[3]);
		System.out.println(a6.getInfixExpression());
		System.out.println(a6.getPostfixExpression());

		System.out.println(a1.evaluate());
		System.out.println(a2.evaluate());
		System.out.println(a3.evaluate());

	} // end of main method

} // end of Arith Expression
