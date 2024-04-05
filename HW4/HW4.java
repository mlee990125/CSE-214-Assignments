

/** 
 * Name: Michael Lee
 * SBUID: 112424954
 * @param <E>
 */
public class HW4{
	private String operator;
	private String postfix;
	private String infix;
	Stackable<String> stack; // Don't change this
	final String[] VALID_OPS = {"-", "+", "*", "/"};
	
	public HW4() {
		infix = "";
		operator = "";
		postfix = "";
		this.stack = new Stack();
	}
	
	/*
	 * These methods should return the converted infix/postfix. 
	 * If there is a syntax error, return an empty string.
	 * State the big-O (w.r.t. the length of input string) and explain why.
	 */
	public boolean infixInvalidity(String infix) {
		if(infix.length()<=2) {
			return true;
		}else if(checkOperator(infix.charAt(0)) || checkOperator(infix.charAt(infix.length()-1))) {
			return true;
		} else {
			for(int i = 0; i < infix.length(); i++) {
				if(i%2 == 0) {
					if(checkOperator(infix.charAt(i)))
						return true;
				} else if(!checkOperator(infix.charAt(i)))
					return true;
					
			}
		}
		return false;
			
	}
	public boolean checkOperator(char c) {
		switch (c) {
		case '-':
			operator = "-";
			return true;
		case '+':
			operator = "+";
			return true;
		case '*':
			operator = "*";
			return true;
		case '/':
			operator = "/";
			return true;
		default:
			return false;
	
		}
	}
	
	public void popStack() {
		while(stack.index() > 0) {
			postfix = postfix + stack.pop();
		}
	}
	public void operatorStack() {
		if(stack.index() == 0) {
			stack.push(operator);
		}else if((operator == "*" || operator == "/") && (stack.peek() == "+" || stack.peek() == "-")) {
			stack.push(operator);
		}else if((operator == "+" || operator == "-") && (stack.peek() == "*" || stack.peek() == "/")) {
			popStack();
			stack.push(operator);
		}else {
			postfix = postfix + stack.pop();
			stack.push(operator);
		}
	}
	
	/*
	 * The infix2Postfix function runs at Big O of O(n). This is because the function has a for loop of n = infix.length.
	 * Everything inside the for loop is O(1) (checkOperator(), operatorStack(), postfix assignment), thus the loops is O(n).
	 * Outside the loop, popStack() function runs at O(n), but since its outside, the overall function is O(n). infixInvalidity()
	 * also runs at O(n). 
	 */
	public String infix2Postfix(String infix) {
		if(infixInvalidity(infix))
			return "";
		for(int i = 0; i < infix.length(); i++) {
			if(checkOperator(infix.charAt(i))) {
				operatorStack();	
			}else {
				postfix = postfix + infix.charAt(i);
			}
		}
		popStack();
		System.out.println(postfix);
		return postfix;
	}
	
	public boolean postfixInvalidity(String postfix) {
		if(postfix.length() <= 2) {
			return true;
		} else if(checkOperator(postfix.charAt(0))) {
			return true;
		} else if(checkOperator(postfix.charAt(1))) {
			return true;
		}else {
			return false;
		}
	}
	/*
	 * The postfix2Infix functions runs at O(n). postfixInvalidty() runs O(1), and there exists a for loop for n = postfix.length.
	 * Inside the loop, every function is O(1) (stack.pop, stack.push, checkOperator). Finally, everything proceeding the loop is 
	 * O(1) functions. 
	 */
	public String postfix2Infix(String postfix) {
		if(postfixInvalidity(postfix))
			return "";
		for(int i = 0; i < postfix.length(); i++) {
			if(!checkOperator(postfix.charAt(i))) {
				stack.push(Character.toString(postfix.charAt(i)));
			} else {
				String b = stack.pop();
				String a = stack.pop();
				stack.push(a + operator + b);
			}
		}
		infix = stack.pop();
		if(stack.index() != 0)
			return "";
		System.out.println(infix);
		return infix;
	}
	
	public static void main(String[] args) {
		HW4 hw = new HW4();
		String infix = "a+b";
		String postfix = "ab+";
		if(hw.infix2Postfix(infix).equals(postfix)) System.out.println("Success");
		if(hw.postfix2Infix(postfix).equals(infix)) System.out.println("Success");
		String badInfix = "a+-b";
		String badPostfix = "a+b";
		if(hw.infix2Postfix(badInfix).equals("")) System.out.println("Success");
		if(hw.postfix2Infix(badPostfix).equals("")) System.out.println("Success");
		
		
		
	}

	

	class Stack<E> implements Stackable<E>{
		public Object[] objectData;
		private int index;

		
		public Stack() {
			this.objectData = new Object[20];
			this.index = 0;
		}

		@Override
		public E peek() {
			// TODO Auto-generated method stub
			return (E) objectData[index-1];
		}

		@Override
		public void push(E obj) {
			objectData[index++] = obj;	
		}

		@Override
		public E pop() {
			E p = (E) objectData[index-1];
			index--;
			return p;
		}
		
		public int index() {
			return index;
		}
		
	}
	
}

/**
 * Your stack should use the following interface. You may use any of the data structures
 * that we looked at in class so far (but not the ones we haven't).
 * Note: Having more than one classes/interfaces in the same file is generally undesirable.
 * But we'll just keep it this way for the sake of simplicity.
 */
interface Stackable<E> {
	public int index();
	public E peek();
	public void push(E obj);
	public E pop();
}
