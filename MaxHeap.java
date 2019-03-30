/**
 * Class MaxHeap Creates a max heap and sorts it, then analyzes runtime compared to selection sort
 * @author Ellen Burger
 * @version 3/30/19
 */

/** Uses user input */
import java.util.Scanner;

/** Generates random numbers */
import java.util.Random;

public class MaxHeap {
	
	/**
	 * Randomizes the contents of an integer array
	 * @param arr Array to be randomized
	 * @param size Size of the array
	 * @param ran Random object to call random integers
	 * @param range Width of the field of numbers to choose from
	 * @param start Starting location of the field of numbers
	 */
	public static void randomize(int arr [], int size, Random ran, int range, int start) {
		
		for(int i = 0; i < size; i++) {
			arr[i] = ran.nextInt(range) + start;
		}
	}
	
	/**
	 * Swaps the values of two integer objects
	 * @param inputA First integer to be swapped
	 * @param inputB Second integer to be swapped
	 */
	public static void swap(int arr [], int posA, int posB) {
		
		int temp = arr[posA];
		arr[posA] = arr[posB];
		arr[posB] = temp;
	}
	
	/**
	 * Sorts an array via selection sort, breaking an array into segments that decrease in size 
	 * and placing the smallest found element at the beginning of the segment each iteration
	 * @param arr Array being sorted
	 * @param size Size of array
	 */
	public static void selectionSort(int arr [], int size) {
		
		int temp;
		
		/** Lower bound of segment of array that is being searched for a lowest value */
		for (int i = 0; i < size; i++) {
			
			/** temp = the first element of the segment */
			temp = arr[i];
			
			/** Iterates through the array segment starting at the lower bound + 1 */
			for (int j = i + 1; j < size; j++) {

				/** If a number is lower than the segment's first value, swap with the first value */
				if(arr[j] < temp) {
					
			       swap(arr, i, j);
			    }
			}
		}
	}
	
	/**
	 * Recursive function that reorders a three-node tree from a larger tree until it is a max-heap
	 * @param arr Array containing the max heap
	 * @param pos Position of the root of the miniature tree in the larger tree
	 * @param size Size of the entire heap
	 */
	public static void max_heapify(int arr [], int pos, int size) {
		
		/**
		 * max is the root index according to pos
		 * left is the left child's index
		 * right is the right child's index
		 */
		int max = pos;
		int left = 2 * pos + 1;
		int right = 2 * pos + 2;
		
		/** If left child exists and is greater than parent, parent's index = child's index */
		if(left < size && arr[left] > arr[max]) {
			max = left;
		}
		
		/** If left child exists and is greater than parent, parent's index = child's index */
		if(right < size && arr[right] > arr[max]) {
			max = right;
		}
		
		/** If an above if statement occurred, swaps the child with the parent then recursively repeats until the tree segment is sorted */
		if(max != pos) {
			swap(arr, max, pos);
			max_heapify(arr, max, size);
		}
		
	}
	
	/** 
	 * Builds a max heap from an array by iterating through the heap from leaves to root, 
	 * treating each node as the root of a three-node max heap that needs to be heapified
	 * @param arr Array containing the max heaps
	 * @param size Size of the heap
	 */
	public static void build_MaxHeap(int arr [], int size) {
		
		/** Decrements through the array starting at the first internal node */
		for(int i = (size - 1) / 2; i > -1; i--) {
			max_heapify(arr, i, size);
		}
		
	}
	
	/**
	 * Sorts an array by first building a max-heap from an array, then swapping the first and last
	 * elements of the array to "delete" the root. It does this for each element of the array,
	 * resulting in the array being sorted in ascending order
	 * @param arr Array to be heap-sorted
	 * @param size Size of the array
	 */
	public static void heap_sort(int arr [], int size) {
		
		/** Builds the max-heap from the given array */
		build_MaxHeap(arr, size);
		
		/**
		 * Decrements through the array, swapping the root with the end and heapifying
		 * Heapify treats the for loop's current position as size in order to not change 
		 * the position of the swapped root which is the greatest element in the array
		 */
		for(int i = size - 1; i > -1; i--) {
			swap(arr, 0, i);
			max_heapify(arr, 0, i);	
		}
		
	}
	
	/** Main method */
	public static void main(String [] args) {
	
		/** Randomizer and Scanner */
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
		
		/** Variables used to record average times */
		int repeat = 100;
		double heapSortTime = 0;
		double selectSortTime = 0;
	
		/** Creating an array */
		System.out.println("Enter a positive integer.");
		int size = scan.nextInt();
		int [] array = new int [size];
		
		
		
		/** Calling heap sort 100 times and recording the time needed, re-randomizing the array each iteration*/
		for(int i = 0; i < repeat; i++) {

			randomize(array, size, ran, 20000,-10000);
			
			double startTime = System.nanoTime();
			heap_sort(array, size);
			double endTime = System.nanoTime();
			
			heapSortTime += endTime - startTime;
		}
		heapSortTime = heapSortTime / repeat;
		System.out.println("Average heap-sort time: " + heapSortTime + " nanoseconds.");
		
		/** Displaying sorted array */
		System.out.print("Last ten digits of heap-sorted array: ");
		for(int i = size - 10; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
		
		
		/** Calling selection sort 100 times and recording the time needed, re-randomizing the array each iteration*/
		for(int i = 0; i < repeat; i++) {

			randomize(array, size, ran, 20000,-10000);
			
			double startTime = System.nanoTime();
			selectionSort(array, size);
			double endTime = System.nanoTime();
			
			selectSortTime += endTime - startTime;
		}
		selectSortTime = selectSortTime / repeat;
		System.out.println("Average selection sort time: " + selectSortTime + " nanoseconds.");
		
		/** Displaying sorted array */
		/*System.out.print("Last ten digits of selection-sorted array: ");
		for(int i = size - 10; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();*/
		
		
		scan.close();
		
	}
}
