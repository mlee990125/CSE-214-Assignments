/**
 * 
 * Name:
 * SBUID:
 * 
 * Do not import any unauthorized packages.
 *
 */
import java.util.*;
public class HW8Test {

	/*
	 * Sort in ASCENDING order. Any other ordering is considered incorrect.
	 * Provide a detailed analysis of the time and space complexity.
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
	public static void iterativeQuickSort(int[] a) {
		System.out.println(partition(0, a.length-1, a));
		
		
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
		
		
		return j;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		int size = rand.nextInt(15) + 1;
		int[] a = new int[size];
		for(int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt(100);
		}
		
		//int[] a = {10, 16, 8, 12, 15, 6, 9, 9, 5};
		HW8Test.iterativeMergeSort(a);
		//HW8Test.iterativeQuickSort(a);
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