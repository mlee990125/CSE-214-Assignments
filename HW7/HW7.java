/**
 * 
 * Name: Michael Lee
 * SBUID: 112424954
 * Do not import anything else.
 */
//import java.util.Random;
public class HW7 {
	int heapSize = 0;

	private Process[] heap; // Underlying list for heap representation that stores Process
	
	public HW7() {
	}
	
	/*
	 * Insert new object into the priority queue.
	 */
	
	/*
	 * This method checks if the heap size is zero in order to avoid array out of bounds exception (when checking its parent). If the heap size isn't zero
	 * the function adds the Process object to the end of heap, takes the current index pointer and recursively calls recursiveEnqueueAux. This recursive function 
	 * has the base case of when the current element is bigger than the parent or if it is the root node. In that case, the function stops. Else, it swaps with the parent
	 * until it reaches its base case.
	 */
	public void recursiveEnqueue(Process p) {
		if(heapSize == 0) {
			heap[heapSize++] = p;
			return;
		}else {
			int index = heapSize;
			heap[heapSize++] = p;
			recursiveEnqueueAux(heap[index], index);
		}
		
	}
	
	public void recursiveEnqueueAux(Process p, int index) {
		if(heapSize >= heap.length) {
			Process[] temp = new Process[heap.length * 2];
			System.arraycopy(heap, 0, temp, 0, heap.length);
			heap = temp;
		}

		if(p.compareTo(heap[(index-1)/2]) == 1 || index == 0) {
			return;
		}
		Process temp = heap[index];
		heap[index] = heap[(index - 1)/2];
		heap[(index - 1)/2] = temp;
		index = (index - 1) / 2;
		recursiveEnqueueAux(heap[index], index);
	}
	/*
	 * This method checks if the heapSize is greater than  or equal to the total heap length. In that case, the array
	 * is dynamically resized (doubled). Otherwise, the element is added to the end of the heap array and until it reaches 
	 * the root or the element is bigger than or equal to the parent. Big O is log n. 
	 */
	public void iterativeEnqueue(Process p) {
		
		if(heapSize >= heap.length) {
			Process[] temp = new Process[heap.length * 2];
			System.arraycopy(heap, 0, temp, 0, heap.length);
			heap = temp;
		}
		
		
		int index = heapSize;
		heap[heapSize++] = p;
		while(index > 0) {
			if(heap[index].compareTo(heap[(index - 1) /2]) == -1) {
				Process temp = heap[index];
				heap[index] = heap[(index - 1)/2];
				heap[(index - 1)/2] = temp;
				index = (index - 1) / 2;
			}else {
				break;
			}
		}
		
		
	}
	/*
	 * Remove the process with highest priority, and execute it.
	 * 'heap' should remain a valid heap after dequeue.
	 * Return the execution time. 
	 */
	
	/*
	 * It checks if the heapSize is zero to avoid Array out of bounds exception. Else, the index starts from zero,
	 * the return value is saved, and the last element is put on top of the heap. Then recursiveDequeueAux is called recursively. 
	 * This recursive function has the base case of when the index is greater than (heapSize - 1) / 2 or if the element
	 * is smaller than its children element. Otherwise, the element is swapped with the smaller of the two children and
	 * the index is readjusted. 
	 * 
	 */
	
	public int recursiveDequeue() {
		int pop;
		if(heapSize == 0) {
			return -1;
		}else {
			int index = 0;
			pop = heap[0].execTime;
			heap [0] = heap[heapSize-1];
			recursiveDequeueAux(index);
			
		}
		heap[--heapSize] = null;
		return pop;
		
	}
	
	public void recursiveDequeueAux(int index) {
		if(index > (heapSize - 1) / 2) {
			return;
		}else if(heap[index].compareTo(heap[2*index + 1]) == -1 ) {
			return;
		}
		int swapIndex = heap[2*index + 1].compareTo(heap[2*index + 2]) == -1 ? 2 * index + 1 : 2 * index + 2;
		Process temp = heap[index];
		heap[index] = heap[swapIndex];
		heap[swapIndex] = temp;
		index = swapIndex;
		recursiveDequeueAux(index);
		

	}
	
	/*
	 * This function first checks if the heapSize is zero to avoid Index out of bounds exception. Otherwise,
	 * the root element is stored and the last element of heap is put at root. Then, while index <= (heapSize -1) / 2,
	 * it swaps the element with the smaller of the two children if the element is bigger than either children. It swaps
	 * until the element is put in the correct position and the last element of heap is replaced with null. Big O is log n
	 */
	
	public int iterativeDequeue() {
		if(heapSize == 0) {
			return -1;
		}
		int pop = heap[0].execTime;
		heap[0] = heap[heapSize-1];
		int index = 0;
		while(index <= (heapSize - 1) / 2) {
			if(heap[index].compareTo(heap[2*index + 1]) == 1) {
				int swapIndex = heap[2*index + 1].compareTo(heap[2*index + 2]) == -1 ? 2 * index + 1 : 2 * index + 2;
				Process temp = heap[index];
				heap[index] = heap[swapIndex];
				heap[swapIndex] = temp;
				index = swapIndex;
			}else {
				break;
			}
		}
		heap[--heapSize] = null;
		return pop;
		
	}
	
	/*
	 * Initialize the priority queue with the given set of objects.
	 */
	
	/*
	 * This method initializes are new heap with the given procs object array. It uses iterative Enqueue
	 * to correctly order the heap array with the random Process array. If the heap size is already greater than 0,
	 * the heap size is readjusted to be double the original size. Big O is n log n
	 */
	public void populateQ(Process[] procs) {
		if(heapSize != 0) {
			Process[] temp = new Process[heap.length * 2];
			System.arraycopy(heap, 0, temp, 0, heap.length);
			heap = temp;	
		}else {
			heap = new Process[procs.length];
			
		}
		
		for(int i = 0; i < procs.length; i++) {
			iterativeEnqueue(procs[i]); 
		}
		
	}
	
	
	public static void main(String[] args) {
		/*
		Random rand = new Random();
		Process[] procs = new Process[8];
		for(int i = 0; i < 8; i++) {
			procs[i] = new Process(i, rand.nextInt(30) + 1);
		}
		
		HW7 hw = new HW7();
		hw.populateQ(procs);
		System.out.println();
		for(Process e : hw.heap) {
			System.out.println(e);
		}
		System.out.println(hw.heapSize);

		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());
		System.out.println(hw.iterativeDequeue());

		for(Process e : hw.heap) {
			System.out.println(e);
		}
		System.out.println(hw.heapSize);
		*/

		/*
		Process sample = new Process(3, 15);
		hw.recursiveEnqueue(sample);
		for(Process e : hw.heap) {
			System.out.println(e);
		}
		*/
		/*
		Process[] procs2 = new Process[15];
		for(int i = 0; i < 15; i++) {
			procs2[i] = new Process(i, rand.nextInt(30) + 1);
		}
		hw.populateQ(procs2);
		System.out.println();
		for(Process e : hw.heap) {
			System.out.println(e);
		}
		*/

		
		

	}

}

/*
 * Class representing the process. 
 * You can modify the class any way you want, provided that the following are satisfied:
 * 1. The class name must not change.
 * 2. The fields should remain as they are. (You can add more, but don't change the names and types)
 */
class Process {
	int pid; // Unique. We will NOT test duplicate IDs.
	int execTime; // Expected execution time.
	
	public Process(int pid, int execTime) {
		this.pid = pid;
		this.execTime = execTime;
		// Fill in the rest as needed
	}
	
	public String toString() {
		return "id: " + pid + " execTime: " + execTime;
	}
	
	public int compareTo(Process p) {
		if(p == null || this == null) {
			return 0;
		}else if(this.execTime < p.execTime) {
			return -1;
		}else if(this.execTime > p.execTime) {
			return 1;
		}else {
			return 0;
		}
	}
}
