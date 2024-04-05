/**
 * 
 * Name: Michael Lee
 * SBUID: 112424954
 * 
 * Do not import any unauthorized packages.
 *
 */
public class HW8 {

	/*
	 * Sort in ASCENDING order. Any other ordering is considered incorrect.
	 * Provide a detailed analysis of the time and space complexity.
	 */
	
	/*
	 * iterativeMergeSort takes n log n operation as the sorting algorithm uses 
	 * the divide and conquer method. The element is compared with next one in iteration of 
	 * times 2. So, for example, if the length of array was 8, it would be 2, 4, 8. Or in other words
	 * log (base 2) 8 = 3 times of iteration done. Within this log n time iteration, every element is compared,
	 * meaning the comparison was done n times. Since n comparisons were done within log n iterations,
	 * the total algorithm takes n log n times on average. 
	 */
	public static void iterativeMergeSort(int[] a) {
		if(a == null) {
			System.err.println("Null Array");
			return;			
		}
		int low, mid, high = 0, i = 2;
		while(i <= a.length) {
			for(int j = 0; j + i - 1 < a.length; j = j + i) {
				low = j;
				high = j + i - 1;
				mid = (low + high)/2;
				iterativeMergeSortAux(a, low, mid, high);
			}
			i = i * 2;
		}		
		if(high < a.length - 1) mergeRest(a, high);

		
	}
	
	public static void iterativeMergeSortAux(int[] a, int low, int mid, int high) {
		int[] left = new int[mid - low + 1];
		int[] right = new int[left.length];
		for(int i = 0; i < left.length; i++) {
			left[i] = a[low + i];
			right[i] = a[mid + 1 + i];
		}
		sort(left, right, a, low);


	}
	
	public static void mergeRest(int[] a, int high) {
		int[] left = new int[high + 1];
		int[] right = new int[a.length - high - 1];
		for(int i = 0; i < left.length; i++) {
			left[i] = a[i];
		}
		for(int i = 0; i < right.length; i++) {
			right[i] = a[high + i + 1];
		}
		sort(left, right, a, 0);
		
		
	}
		
	public static void sort( int[] left, int[] right, int[] a, int low) {
		int leftCounter = 0, rightCounter = 0, index = low;
		while(true) {
			if(leftCounter >= left.length || rightCounter >= right.length) {
				break;
			}
			if(left[leftCounter] < right[rightCounter]) {
				a[index] = left[leftCounter];
				leftCounter++;
			}else {
				a[index] = right[rightCounter];
				rightCounter++;
			}
			index++;
		}
		for(;leftCounter < left.length; leftCounter++) a[index++] = left[leftCounter];
		for(;rightCounter < right.length; rightCounter++) a[index++] = right[rightCounter];
	
	}
		
		
		
		
	
	
	/*
	 * Sort in ASCENDING order. Any other ordering is considered incorrect.
	 * Provide a detailed analysis of the time and space complexity.	 
	 */
	
	/*
	 * iterativeQuickSort method takes n log n operation on average case. As each element in the array
	 * is responsible for finding its position within the array, the iteration done is log n times. Since partitioning
	 * takes n times (as you have to compare with every element once) but with each partition, the iteration is halved of 
	 * n. I.e compare 8 elements -> partition once -> compare 4 elements -> etc (on avg case). Therefore, since the main iteration
	 * takes log n times and partitioning takes n times, Big O is n log n. 
	 * 
	 */
	public static void iterativeQuickSort(int[] a) {

	}
	
	public static int partition(int low, int high, int[] a) {
		int pivot = a[low];
		int i = low + 1, j = high - 1;
		while(i < j) {
			while(a[i] <= pivot) {
				i++;
			}
			while(a[j] > pivot) {
				j--;
			}
			swap(a, i, j);
		}
		swap(a, i, j);
		swap(a, low, j);
		return j;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void main(String[] args) {
	
		
		int[] a = {10, 3, 5, 2, 1, 1, -2, 64, 9, 7, 452, 8};
		HW8.iterativeMergeSort(a);
		
		for(int e : a) {
			System.out.println(e);
		}
		for(int i = 0; i < a.length - 1; i++) {
			if(a[i] > a[i + 1]) {
				System.err.println("Wrong order @ index " + i);
				break;
			}
		}
	}

}