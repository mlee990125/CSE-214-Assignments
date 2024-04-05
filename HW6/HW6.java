/**
 * Name: Michael Lee
 * SBUID:112424954
 * Do not use any unauthorized packages.
 * For all recursive methods, feel free to use helper methods.
 */
import java.util.Stack;

public class HW6 {
	private char[] charArray;
	
	//Node root;
	enum OP {PLUS, TIMES, MINUS, DIVIDE};
	String toS = "";
	String post = "";
	
	public HW6() {
	}
	private static NodeStack root;
	class NodeStack{
		Node n;
		NodeStack next;
		public NodeStack(Node n) {
			this.n = n;
			next = null;
		}
	}
	
	private class Node {
		Node left, right;
		char data;
		// Add more necessary fields
		
		public Node(char data) { // Modify the constructor accordingly
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	/*
	 * Build a parse tree, to be pointed by root, that represents 'expression'.
	 * Implement recursively.
	 */
	
	/*
	 * For this method, I used a stack of node to push and pop operands and operators. It didn't use 
	 * recursion but rather iterated through every character in a given string and used the insert 
	 * method. It takes a given input in a prefix expression then uses the stack to correctly form a 
	 * tree. 
	 */
	
	public void buildTree(String expression) {
		expression = toPrefix(expression);
		System.out.println(expression);

		for (int i = expression.length() - 1; i >= 0; i--) {
        	
            insert(expression.charAt(i));
        	//System.out.println(top.treeNode.data + " " + top.treeNode.left + " " + top.treeNode.right);
        }
	}
	
	private String toPrefix(String expression) {
		Stack<Character> operators = new Stack<>(); 
		Stack<String> operands = new Stack<>(); 
		  
		  	for (int i = 0; i < expression.length(); i++) {
		
		        if (expression.charAt(i) == '(') {
		            operators.push(expression.charAt(i)); 
		        } 
		        else if (expression.charAt(i) == ')') {
		  
		            while (!operators.empty() && operators.peek() != '(') {
		  
		                String op1 = operands.peek(); 
		                operands.pop(); 
		  
		                String op2 = operands.peek(); 
		                operands.pop(); 
		  
		                char op = operators.peek(); 
		                operators.pop(); 
		  
		                String tmp = op + op2 + op1; 
		                operands.push(tmp); 
		            } 
		  
		            operators.pop(); 
		        } 
		  

		        else if (isDigit(expression.charAt(i))) {
		            operands.push(expression.charAt(i) + ""); 
		        } 
		  
		        else {
		            while (!operators.empty() &&  
		                getPriority(expression.charAt(i)) <=  
		                    getPriority(operators.peek()))  
		                { 
		  
		                String op1 = operands.peek(); 
		                operands.pop(); 
		  
		                String op2 = operands.peek(); 
		                operands.pop(); 
		  
		                char op = operators.peek(); 
		                operators.pop(); 
		  
		                String tmp = op + op2 + op1; 
		                operands.push(tmp); 
		            } 
		  
		            operators.push(expression.charAt(i)); 
		        } 
		    } 

		    while (!operators.empty())  
		    { 
		        String op1 = operands.peek(); 
		        operands.pop(); 
		  
		        String op2 = operands.peek(); 
		        operands.pop(); 
		  
		        char op = operators.peek(); 
		        operators.pop(); 
		  
		        String tmp = op + op2 + op1; 
		        operands.push(tmp); 
		    } 
		    return operands.peek();
	}
	private static int getPriority(char C) {
	    if (C == '-' || C == '+') {
	        return 1; 
	    }else if (C == '*' || C == '/') {
	        return 2; 
	    }
	    return 0;
	}
	private void insert(char c) {
			if(isDigit(c)) {
				Node n = new Node(c);
				push(n);
			} else if(isOperator(c)) {
				Node newNode = new Node(c);
				newNode.left = pop();
				newNode.right = pop();
				push(newNode);
			}
	}
	private boolean isDigit(char ch)
    {
        return ch >= '0' && ch <= '9';
    }
	private boolean isOperator(char ch)
    {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
	private void push(Node n) {
		if(root == null) {
			root = new NodeStack(n);
		}else {
			NodeStack ns = new NodeStack(n);
			ns.next = root;
			root = ns;
			
		}
	}
	private Node pop() {
		Node returnNode = root.n;
		root = root.next;
		return returnNode;
	}
	
	
	/*
	 * Evaluate the expression represented by 'root'.
	 * Implement recursively. 
	 */
	
	/*
	 * this method used to check if a node in the tree is operand or operator. If it was a leaf node,
	 * aka a operand, it would return the data calculation. Else while, it would recursively call the eval
	 * method to its left and right branches. 
	 */
	public double eval() {
		return recursiveEval(root.n);
	}
	
	/*
	 * Evaluate the expression represented by 'root'.
	 * Implement iteratively. 
	 */
	public double iterativeEval() {
		//return iterativeEval(root.n);
		return 0;
	}
	
	public double recursiveEval(Node n) {
		if (n.left == null && n.right == null) {
            return n.data - '0';	
		} else {
	            double result = 0.0;
	            double left = recursiveEval(n.left);
	            double right = recursiveEval(n.right);
	            char operator = n.data;
	 
	            switch (operator)
	            {
	            case '+' : result = left + right; break;
	            case '-' : result = left - right; break;
	            case '*' : result = left * right; break;
	            case '/' : result = left / right; break;
	            default  : result = left + right; break;
	            }
	            return result;
	        }
	}
	
	/*
	 * Return the original infix notation. You shouldn't just return the stored input string.
	 * Implement recursively.
	 */
	
	/*
	 * traverses the tree inOrder to find the data and concatenate with other data to form 
	 * an infix string. Inorder traversing is done recursively on the left and right nodes.
	 */
	public String toString() {
		
		inOrder(root.n);
		return toS;
	}
	
	private void inOrder(Node n) {
		if(n != null) {
			inOrder(n.left);
			char c = n.data;
			toS = toS + c;
			inOrder(n.right);
		}
	}
	
	/*
	 * Return the postfix version of the expression.
	 * Implement recursively.
	 */
	
	/*
	 * traverses the tree postOrder to find the data and concatenate with other data to form 
	 * an postfix string. postorder traversing is done recursively on the left and right nodes.
	 */
	public String toPostfixString() {
		postOrder(root.n);
		return post;
	}
	private void postOrder(Node n) {
		if(n != null) {
			postOrder(n.left);
			postOrder(n.right);
			char c = n.data;
			post = post + c;
		}
	}
		
	public static void main(String[] args) {
		HW6 hw = new HW6();
		String s = "3+((5+9)*2)";
		hw.buildTree(s);
		System.out.println(hw.eval());
		System.out.println(hw.toString());
		System.out.println(hw.toPostfixString());
	}

}