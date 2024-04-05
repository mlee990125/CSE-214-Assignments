/**
 * Homework 5: Due Oct. 22nd @ 23:59 Korea time.
 * Name: Michael Lee
 * SBUID: 112424954
 * 
 * You are to solve a collection of individual recursion puzzles. For each puzzle, you must implement
 * both a recursive and an iterative version. You are free to write helper methods that will do the
 * actual recursion. Not using a recursion in a recursive version will result in a zero score.
 * For each puzzle, clearly describe what your base and recursive cases are in the header comment.
 * Also provide a description of your approach in a single paragraph.
 * 
 * GENERAL INSTRUCTIONS (failure to follow these instructions will result in a deduction of points):
 * Your final submission should be your own work.
 * Do not import any unauthorized packages. 
 * Do not use any Java data structures unless told to do so.
 * Do not change the class or method names.
 * Submit a single HW5.java file with no package structure.
 * 50% off for late submissions, up to 24 hours.
 * Detailed instructions are given per puzzle.
 * 
 * RUBRIC
 * Correctness (70 points): See the individual method head for individual point allocation. 
 * Comments (30 points): 5 points for each puzzle. This should include your base and recursive 
 *                       cases descriptions, as well as the description of your overall approach.
 *                       Place this information in the comment block right before the implementation
 *                       of each puzzle.
 */
import java.util.*;

public class HW5 {

	/*
	 * 1. Enumerate all possible combinations letters in the given phone number digits. Use numCodes for the corresponding mnemonic.
	 * 'n' is a number string containing a sequence of digits, each between 2 and 9, inclusive.
	 * E.g., n = "45" --> your answer should be: {"GJ", "GK", "GL", "HK", "HK", "HL", "IJ", "IK", "IL"} (not necessarily in that order)
	 */
	static final String[] numCodes = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
	String output = "";
	
	public void backtrack(String combination, String digits){
        if(digits.length() == 0){
            output = output + combination + " ";
        }
        else{
            String digit = digits.substring(0, 1);
            String letters = numCodes[Integer.parseInt(digit)];
            for(int i = 0; i < letters.length(); i++){
                String letter = numCodes[Integer.parseInt(digit)].substring(i, i + 1);
                backtrack(combination + letter, digits.substring(1));
            }
        }
    }
	/*
	 * Recursively calls function backtrack. This function takes the current combination of letters and the digit its 
	 * working with. With the selected digit, it iterates through the letters corresponding to the digit and for each letter,
	 * it combines with the next iteration of the next letters. When there are no more letters, all the combinations are put
	 * together in one string. Then the string is split into a String array. 
	 */
	public String[] recursiveMnemonics(String n) { // 12 points
		if(n.length() != 0)
			backtrack(" ", n);
		String[] s = output.split(" ");
		return s;
		
	}
	
	public String[] iterativeMnemonics(String n) { // 2 points
		return null;
	}
	/*
	 * 2. Print all subset sums of a given array. A value of 0 is always part of the answer.
	 * (i.e., when NO elements are added)
	 * For example, when the input is {1, 2, 3}, the result should be the array 
	 * {0, 1, 2, 3, 3, 4, 5, 6} (singletons and duplicates should also be present)
	 */
	
	public int[] recursiveSubsetSum(int[] a) { // 12 points
		int[] result = new int[(int) Math.pow(2, a.length)];
		if(a.length == 0) {
			return result;
		}
		recursion(a, result, 0, 0, 0);
		return result;
		
	}
	public void recursion(int[] a, int[] result, int index, int i, int comb) {
		if(index == a.length) {
			result[i] = comb;
			return;
		}
	}
	
	public int[] iterativeSubsetSum(int[] a) { // 2 points
		int[] subsetSum = new int[(int) Math.pow(2, a.length)];
		
		for(int i = 0; i < a.length; i++) {
			findAllSum(subsetSum, a[i]);
		}
		return null;
	}
	public void findAllSum(int[] s, int n) {
		
	}
	
	
	/*
	 * 3. Skip every n-th element in the LinkedList (n > 1).
	 * For example, in a list 1-2-3-4-5-6 with n=3, the output is 1-2-4-5.
	 * Use only a LinkedList, but no other data structures.
	 */
	
	/*
	 * Recursively calls the recursiveSkipNthHelper function, where it takes the list it's working with, the new list the result will
	 * be stored, and int n to skip every n-th element. If the size of the list % n is not zero, then that element is stored inside 
	 * the new result list. This is recursively done until the base case, where the original list is empty. If so, the new list is 
	 * returned.
	 */
	public LinkedList<Integer> recursiveSkipNth(LinkedList<Integer> l, int n) { // 10 points
		
		LinkedList<Integer> newL = new LinkedList<Integer>();
		return recursiveSkipNthHelper(l, n, newL);
		
	}
	
	
	public LinkedList<Integer> recursiveSkipNthHelper(LinkedList<Integer> l, int n, LinkedList<Integer> newL){
		if(l.isEmpty()) {
			return newL;
		}
		if(l.size() % n != 0) {
			newL.addFirst(l.getLast());
		}
		l.removeLast();
		return recursiveSkipNthHelper(l, n, newL);
	}
	
		
	public LinkedList<Integer> iterativeSkipNth(LinkedList<Integer> l, int n) { // 1 points
		int c = 0;
		for(int i = 1; i < l.size()+c+1; i++) {
			if(i%n == 0) {
				l.remove(i-1-c);
				c = c + 1;
			}
		}
		return l;
	}
	
	/*
	 * 4. Sort a given integer Stack in descending order.
	 * For example, if the initial stack is: <TOP> 3-4-1-2-9 <BOTTOM>, the result should be
	 * <TOP> 9-4-3-2-1 <BOTTOM>.
	 * Use only a Stack, but no other data structures.
	 */
	
	/*
	 * Recursively calls the recursiveSortStackHelper function with the parameters of the original stack and an integer
	 * that keeps track of the minimum. First calls the recursiveSorthStack function to empty everything and stack all 
	 * variations of int i in the Call Stack. Then, each i is compared with each other as the Call Stack goes from s.size to 
	 * zero. 
	 */
	public Stack<Integer> recursiveSortStack(Stack<Integer> s) { // 12 points
	
		if(s.isEmpty()) return s;
		int i = s.pop();
		recursiveSortStack(s);
		recursiveSortStackHelper(s, i);
		return s;
	}
	public void recursiveSortStackHelper(Stack<Integer> s, int i){
		if(s.isEmpty() || i > s.peek()) {
			s.push(i);
			return;
		}
		int temp = s.pop();
		recursiveSortStackHelper(s, i);
		s.push(temp);
	}
	
	public Stack<Integer> iterativeSortStack(Stack<Integer> s) { // 2 points
		int i = 0;
		int sz = s.size();
		Integer[] arr = new Integer[sz];
		while(!s.isEmpty()) {
			arr[i]= s.pop();
			i++;
		}
		sort(arr);
		for(int j = 0; j < sz; j++) {
			s.push(arr[j]);
		}
		return s;
		
	}
	public void sort(Integer[] arr) {
		int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
	}
	
	/*
	 * 5. Even-odd sum. Return the sums of all even and odd numbers, respectively, in a given array.
	 * For example, when the array is {4, 2, 1, 5, 6}, it should return {12, 6} (in that order).
	 */
	
	/*
	 * Have two recursive functions, evenSum and oddSum. evenSum finds the sum of all even numbers by summing any numbers
	 * where n%2 is zero. Same for oddSum except n%2 is one. This happens until the base case, where the counter is less than zero.
	 * (The counter starts from the length of the array.)
	 */
	public int[] recursiveEvenOddSums(int[] a) { // 10 points
		int eSum = evenSum(a, a.length-1, 0);
		int oSum = oddSum(a, a.length-1, 0);
		return new int[] {eSum, oSum};
		
	}
	public int evenSum(int[] a, int l, int sum) {
		if(l < 0) {
			return sum;
		}
		if((a[l]) % 2 == 0) {
			sum = sum + a[l];
		}
		
		return evenSum(a, l-1, sum);
	}
	public int oddSum(int[] a, int l, int sum) {
		if(l < 0) {
			return sum;
		}
		if((a[l]) % 2 == 1) {
			sum = sum + a[l];
		}
		return oddSum(a, l - 1, sum);
	}
	
	
	
	public int[] iterativeEvenOddSums(int[] a) { // 1 point
		int eSum = 0;
		int oSum = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] % 2 == 0) {
				eSum = eSum + a[i];
			}else {
				oSum = oSum + a[i];
			}
		}
		return new int[] {eSum, oSum};
	}
	
	/*
	 * 6. Compute the total number of balls in a pyramid of a given level.
	 * In a ball-based pyramid, each ball in level n is supported by four balls in level n-1.
	 * Image: https://qph.fs.quoracdn.net/main-qimg-506622fcf66903d14206f01934e9d439-c
	 */
	
	/*
	 * returns the sum of all the levels squared until it reaches the 0th level, in which it returns a zero. the sums are stacked
	 * in the Call Stack. 
	 */
	public int recursivePyramid(int level) { // 5 points
		if(level == 0) {
			return 0;
		}
		return (level * level) + recursivePyramid(level - 1);
	}
	
	
	public int iterativePyramid(int level) { // 1 point
		int sum = 0;
		while(level > 0) {
			sum = sum + (level * level);
			level--;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		HW5 hw = new HW5();

		
	}
}