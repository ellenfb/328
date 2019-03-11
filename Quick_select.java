/**
 * Class Quick_select Finds the kth least element in an array
 * @author Ellen Burger
 * @version 3/11/19
 */

/** Uses user input */
import java.util.Scanner;

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
	public static int findPartition(int [] arr, int lo, int up) {
		
		int piv = arr[up], 
		prt = lo;
		int swap;
		for (int i = lo; i <= up - 1; i++) { 
			
			if (piv >= arr[i]) { 
				swap = arr[prt]; 
				arr[prt] = arr[i]; 
				arr[i] = swap; 
				prt++; 
            } 
        } 
		
		swap = arr[prt]; 
		arr[prt] = arr[up]; 
		arr[up] = swap; 
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
		 
		int prt = findPartition(arr, lo, up); 
  
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
	 * Finds and prints the three largest elements
	 * @param arr Array being searched
	 * @param lo Lower bound
	 * @param size Size of araay
	 */
	public static void quickSelectMax(int [] arr, int lo, int size) {
		 
		int up = size;
		int prt = -1;
		System.out.print("Max three = ");
		
		for(int k = size - 1; k <= size+1; k++){
			
			prt = findPartition(arr, lo, up); 
			while(prt - lo != k - 1) {
				if (prt - lo > k - 1) {  
					prt = findPartition(arr, lo, prt - 1);
				}
				else {
					prt = findPartition(arr, prt + 1, up);
				}
			}
			System.out.print(arr[prt] + " ");
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
			System.out.println("a[" + i + "] = " + array[i]);
		}
		
		/** Entering k and printing the kth least element */
		System.out.println("Enter a number between 1 and " + size + ".");
		int k = scan.nextInt();
		
		quickSelect(array, 0, size-1, k);
		
		/** Part B */
		quickSelectMax(array, 0, size - 1);
		
		scan.close();
	}

}
