/**
 * Class Closest_to_Median Finds the k elements closest to the median of an array
 * @author Ellen Burger
 * @version 3/20/19
 */

/** Uses user input */
import java.util.Scanner;
import java.util.Arrays;
/** Generates random numbers */
import java.util.Random;

public class Closest_to_Median {
	
	/**
	 * Finds the partition for the sort algorithm
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int findPartition(int arr[], int lo, int up) {
		
		/** Pivot is set to the beginning of the array */
		int piv = arr[lo];
		int prt = up;
		int swap;
		
		/** Iterates down through array */
		for (int i = up; i > lo; i--) {
			
			/**
			 * If pivot is <= array element at i, swapped with element at position prt
			 * prt is then decremented so the position before it is the location of the next swap if any
			 */
			if (piv <= arr[i]) {
				
				swap = arr[i];
				arr[i] = arr[prt];
				arr[prt] = swap;
				prt--;
			}
		}
		
		/** prt and beginning of array are swapped, prt returned */
		swap = arr[prt];
		arr[prt] = arr[lo];
		arr[lo] = swap;
		return prt;
	}
	
	/**
	 * Searches an array and prints the kth least element
	 * @param arr Array being searched
	 * @param lo Lower bound
	 * @param up Upper bound
	 * @param k Position of the least element
	 * @return Kth least element
	 */
	public static int quickSelect(int [] arr, int lo, int up, int k) {
		 
		int prt = findPartition(arr, lo, up); 
  
		while(prt - lo != k - 1) {
			if (prt - lo > k - 1) {  
				prt = findPartition(arr, lo, prt - 1);
			}
			else {
				prt = findPartition(arr, prt + 1, up);
			}
		}	
		return prt;
	}	
	
	public static void quickSelectMedian(int [] arr, int lo, int size, int k, int med) {
		
		quickSelect(arr, lo, size-1, k);
		
		System.out.println(Arrays.toString(arr));//test
		
		System.out.println(k + " closest elements to median:");
		for(int i = 1; i < k+1; i++) {
			System.out.print((arr[i] + med) + " ");
		}
	}

	/** Main method */
	public static void main(String[] args) {

		/** Randomizer and Scanner */
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
	
		/** Creating and filling an array randomly */
		System.out.println("Enter a positive integer.");
		int n = scan.nextInt();
		int [] a = new int [n];
		for(int i = 0; i < n; i++) {
			a[i] = ran.nextInt(200)-100;
		}
		System.out.println(Arrays.toString(a));
		
		
		
		/** Entering number between 1 and inputted array size */
		System.out.println("Enter a number between 1 and " + n + ".");
		int k = scan.nextInt();
		
		
		
		/** 
		 * Finds the median of the array using quickSelect 
		 * Time complexity is n + n or n which both = O(n)
		 */
		int median;
		if(n % 2 == 0) {
			/** If array size is even, find average of two numbers closest to median */
			median = a[(quickSelect(a, 0, n-1, n/2) + quickSelect(a, 0, n-1, n/2 + 1))/2];
		}
		else {
			/** if array size is odd, find middle number */
			median = a[quickSelect(a, 0, n-1, n/2 + 1)];
		}
		System.out.println("Median = " + median + ".");
		
		
		
		/** 
		 * Saving the difference from the median (a[i] - median) into new array diff
		 * Time complexity is 0(n)
		 */
		int [] diff = new int [n];
		for(int i = 0; i < n; i++) {
			diff[i] = Math.abs(a[i] - median);
		}
		System.out.println(Arrays.toString(diff));
		
		
		
		/** 
		 * Finding the k numbers closest to the median using a modified quickSelect 
		 * Time complexity is O(n)
		 */
		quickSelectMedian(diff, 0, n, k, median);
		
		/** Total time complexity = O(n) */
		
	}

}
