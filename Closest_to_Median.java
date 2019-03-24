/**
 * Class Closest_to_Median Finds the k elements closest to the median of an array
 * @author Ellen Burger
 * @version 3/20/19
 */

/** Uses user input */
import java.util.Scanner;

/** Array functions */
import java.util.Arrays;

/** Generates random numbers */
import java.util.Random;

public class Closest_to_Median {
	
	/**
	 * Partitions an array and returns the new pivot
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
	
	/**
	 * Partitions a 2D array and returns the new pivot using the first column of the array, 
	 * the second column swapped alongside the first
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int findPartition(int arr[][], int lo, int up) {
		
		/** Pivot is set to the beginning of the array */
		int piv = arr[lo][0];
		int prt = up;
		int swap;
		
		/** Iterates down through array */
		for (int i = up; i > lo; i--) {
			
			/**
			 * If pivot is <= array element at i, swapped with element at position prt
			 * prt is then decremented so the position before it is the location of the next swap if any
			 */
			if (piv <= arr[i][0]) {
				
				swap = arr[i][0];
				arr[i][0] = arr[prt][0];
				arr[prt][0] = swap;
				
				swap = arr[i][1];
				arr[i][1] = arr[prt][1];
				arr[prt][1] = swap;
				
				prt--;
			}
		}
		
		/** prt and beginning of array are swapped, prt returned */
		swap = arr[prt][0];
		arr[prt][0] = arr[lo][0];
		arr[lo][0] = swap;
		
		swap = arr[prt][1];
		arr[prt][1] = arr[lo][1];
		arr[lo][1] = swap;
		
		return prt;
	}
	
	/**
	 * Searches a 2D array and prints the kth least element according to the first column
	 * @param arr Array being searched
	 * @param lo Lower bound
	 * @param up Upper bound
	 * @param k Position of the least element
	 * @return Kth least element
	 */
	public static int quickSelect(int [][] arr, int lo, int up, int k) {
		 
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
	
	/**
	 * Finds the k numbers closest to an array's median and prints them
	 * @param arr 2D array containing the difference between the median and every value of an array
	 *        The first column contains the absolute value of the array and is used to find the closest to median
	 *        The second column is the non-absolute values which are then added with the median to find the original values
	 * @param lo Start of the array
	 * @param size Length of array columns
	 * @param k Amount of values around median needing to be searched
	 * @param med Median
	 */
	public static void quickSelectMedian(int [][] arr, int lo, int size, int k, int med) {
		
		/** Find the kth element of the array where the element and previous ones are the closest median values */
		quickSelect(arr, lo, size-1, k);
		
		/** Prints the k elements, skipping the median itself if present */
		if (arr[0][0] == 0){
			System.out.println(k + " closest elements to median:");
			for(int i = 1; i < k+1; i++) {
				System.out.print((arr[i][1] + med) + " ");
			}
		}
		/** If the median is not present */
		else {
			System.out.println(k + " closest elements to median:");
			for(int i = 0; i < k; i++) {
				System.out.print((arr[i][1] + med) + " ");
			}
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
		 * Saving the difference from the median (a[i] - median) into new 2D array diff, absolute value of difference into column 1
		 * Time complexity is 0(n)
		 */
		int [][] diff = new int [n][2];
		for(int i = 0; i < n; i++) {
			diff[i][0] = Math.abs(a[i] - median);
			diff[i][1] = a[i] - median;
		}		
		
		/** 
		 * Finding the k numbers closest to the median using a modified quickSelect 
		 * Time complexity is O(n)
		 */
		quickSelectMedian(diff, 0, n, k, median);
		
		scan.close();
		
		/** Total time complexity = O(n) */
		
	}

}
