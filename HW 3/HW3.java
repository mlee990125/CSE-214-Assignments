/**
 * Name: Michael Lee
 * SBU ID: 112424954
 * Describe your approach in a single paragraph.
 */
// Do not add any imports

public class HW3<E> {
	//Added constant capacity for the max rawData size and variable size to keep 
	// count of elements stored. 
	Object[] rawData;
	final int CAPACITY = 20;
	int size = 0;
	// You may add whatever fields/methods that are deemed necessary
	
	public HW3() {
		rawData = new Object[CAPACITY];
	}
	
	/*
	 * TODO: implement the following four methods with appropriate header comments. 
	 */
	
	/* Big O of inserFront is O(n).
	 * When System.arraycopy is called, the function copies and pastes elements 
	 * from one array to another n = size times. Other code lines are O(1). 
	 * This function throws an exception if the size is full. 
	 * If not full, the function copies the current array to another array with the
	 * first element inserted then reassigns rawData to the new array.
	 * Size is increased by 1. 
	 */
	
	public void insertFront(E obj) {
		if(size == CAPACITY)
			throw new ArrayIndexOutOfBoundsException();
		Object[] temp = new Object[CAPACITY];
		temp[0] = obj;
		System.arraycopy(rawData, 0, temp, 1, size);
		rawData = temp;
		size++;
	}
	
	/*
	 * Big O of inserRear is O(n).
	 * When System.arraycopy is called, the function copies and pastes elements 
	 * from one array to another n = size times. Other code lines are O(1). 
	 * This function throws an exception if the size is full. 
	 * If not full, the function copies the current array to another array with the
	 * last element inserted then reassigns rawData to the new array. 
	 * Size is increased by 1. 
	 */
	public void insertRear(E obj) {
		if(size == CAPACITY)
			throw new ArrayIndexOutOfBoundsException();
		Object[] temp = new Object[CAPACITY];
		System.arraycopy(rawData, 0, temp, 0, size);
		temp[size] = obj;
		rawData = temp;
		size++;
	}
	
	

	/*
	 * Return the deleted element.
	 * Your code should also take appropriate action when deleting the front item is not possible.
	 * (same for deleteRear(), getFront(), and getRear()) 
	 */
	
	/*
	 * Big O of deleteFront is O(n).
	 * When System.arraycopy is called, the function copies and pastes elements 
	 * from one array to another n = size - 1 times. Other code lines are O(1). 
	 * This function throws an exception if the first element is null. 
	 * If not null, the function copies the current array to another array with the
	 * first element deleted then reassigns rawData to the new array. 
	 * Size is decreased by 1. 
	 * Returns the first element. 
	 */
	public E deleteFront() {
		if(rawData[0] == null)
			throw new NullPointerException();
		Object[] temp = new Object[CAPACITY];
		E element = (E) rawData[0];
		System.arraycopy(rawData, 1, temp, 0, size-1);
		size--;
		rawData = temp;
		return element;
	}
	
	/*
	 * Big O of deleteRear is O(n).
	 * When System.arraycopy is called, the function copies and pastes elements 
	 * from one array to another n = size - 1 times. Other code lines are O(1). 
	 * This function throws an exception if the size is 0. 
	 * If the size isn't zero, the function copies the current array to another array with the
	 * last element deleted then reassigns rawData to the new array.
	 * Size is decreased by 1. 
	 * Last element is returned.
	 */
	
	public E deleteRear() {
		if(size == 0)
			throw new NullPointerException();
		Object[] temp = new Object[CAPACITY];
		E element = (E) rawData[size-1];
		System.arraycopy(rawData, 0, temp, 0, size-1);
		size--;
		rawData = temp;
		return element;
	}
		
	/*
	 * TODO: Implement getFront(), getRear(), and size() methods.
	 * I'm not providing the skeleton for these, so you'll have to come up with your own.
	 */
	
	
	//Throws an exception if the first element is null.
	//Else, returns the first element.
	public E getFront() {
		if(rawData[0] == null)
			throw new NullPointerException();
		return (E) rawData[0];
	}
	
	// Throws an exception if the last element is null.
	// Else, returns the last element.
	public E getRear() {
		if(rawData[size-1] == null)
			throw new NullPointerException();
		return (E) rawData[size-1];
	}
	
	// Returns the size of the elements stored. 
	public int size() {
		return size;
	}
	
	// Prints the elements stored in rawData. 
	public void print() {
		for(int i = 0; i < size; i++)
			System.out.print(rawData[i]);
		System.out.println();	
	}
	
	
	
	public static void main(String[] args) {
		HW3<Integer> hw = new HW3<Integer>();	
		
		
	
	}
}