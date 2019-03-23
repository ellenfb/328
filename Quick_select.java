/**
 * Class Quick_select Finds the kth least element in an array
 * @author Ellen Burger
 * @version 3/11/19
 */

/** Uses user input */
import java.util.Scanner;

/** Array functions */
import java.util.Arrays;

/** Generates random numbers */
import java.util.Random;

public class Quick_select {
	
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
	 */
	public static void quickSelect(int [] arr, int lo, int up, int k) {
		 
		/** Find partition value for first run through */
		int prt = findPartition(arr, lo, up);
  
		/** 
		 * while loop because function does not use recursion 
		 * k - 1 because decrementing by one finds position in array terms (arrays start at 0, not 1)
		 * prt - lo because partition - lower bound equaling k - 1 signals the element is found on either array segment
		 */
		while(prt - lo != k - 1) {
			
			if (prt - lo > k - 1) {  
				prt = findPartition(arr, lo, prt - 1);
			}
			else {
				prt = findPartition(arr, prt + 1, up);
			}
		}
		
		System.out.println("Kth least: " + arr[prt]);
	}
	
	/**
	 * Finds and prints the m largest elements
	 * @param arr Array being searched
	 * @param lo Lower bound
	 * @param size Size of array
	 * @param m Largest numbers
	 */
	public static void quickSelectMax(int [] arr, int lo, int size, int m) {
		
		/** Find partition value for first run through */
		int prt = findPartition(arr, lo, size);
		
		/** */
		int k = size - m + 2;
  
		/** 
		 * while loop because function does not use recursion 
		 * k - 1 because decrementing by one finds position in array terms (arrays start at 0, not 1)
		 * prt - lo because partition - lower bound equaling k - 1 signals the element is found
		 */
		while(prt - lo != k - 1) {
			
			if (prt - lo > k - 1) {  
				prt = findPartition(arr, lo, prt - 1);
			}
			else {
				prt = findPartition(arr, prt + 1, size);
			}
		}
		
		System.out.print("Max numbers = ");
		for(int i = 0; i < m; i++) {
			System.out.print(arr[prt + i] + " ");
		}
		
	}
	
	/** Main method */
	public static void main(String [] args) {
	
		/** Randomizer and Scanner */
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
	
		/** Creating and filling an array randomly */
		System.out.println("Enter a positive integer.");
		int size = scan.nextInt();
		int [] array = new int [size];
		for(int i = 0; i < size; i++) {
			array[i] = ran.nextInt(200)-100;
		}
		System.out.println(Arrays.toString(array));
		
		/** Entering k and printing the kth least element */
		System.out.println("Enter a number between 1 and " + size + ".");
		int k = scan.nextInt();
		
		quickSelect(array, 0, size-1, k);
		
		/** Part B */
		System.out.println("How many max numbers?");
		int m = scan.nextInt();
		quickSelectMax(array, 0, size - 1, m);
		
		scan.close();
	}

}
