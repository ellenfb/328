/**
 * Class Closest_to_Median Finds the k elements closest to the median of an array
 * @author Ellen Burger
 * @version 3/20/19
 */

/** Uses user input */
import java.util.Scanner;

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
		return arr[prt];
	}
	
	
	public static void quickSelectMedian(int [] arr, int lo, int size, int k, int med) {
		
		/** Stores values closest to median */
		int [] values;
		
		/** Iterates through values array */
		int c = 0;
		
		/** Size of array - 1, used in quickSelect */
		int up = size - 1;
		
		/** Size of array/2, used to locate positions of values around median */
		int pos = size/2;
		
		/** Start and end for array, declared below based on size of array and number of elements to be found */
		int start;
		int end;
		
		/** Even array size, odd k */
		if(size % 2 == 0 && k % 2 == 1) {
			start = pos - k/2 - 1;
			end = pos + k/2 + 1;
			values = new int [k + 2];
		}
		
		/** Odd array size, even k */
		else if(size % 2 == 1 && k % 2 == 0) {
			start = pos - k/2;
			end = pos + k/2 +1;
			values = new int [k + 2];
		}
		
		/** Even array size, even k */
		else if(size % 2 == 0 && k % 2 == 0) {
			start = pos - k/2;
			end = pos + k/2;
			values = new int [k];
		}
		
		/** Odd array size, odd k */
		else {
			start = pos - k/2 - 1;
			end = pos + k/2 + 2;
			values = new int [k + 3];
		}
		
		System.out.println(k + " numbers closest to median: ");
		
		/** Finds the values surrounding the array using quickSelect */
		for(int i = start; i < end; i++) {
			values[c] = quickSelect(arr, lo, up, i+1);
			c++;
		}
		
		/** If an odd number of elements in values, checks which outer bound's absolute value is greater and ignores it when printing */
		if(k % 2 == 1) {
			if(Math.abs(values[0]) > Math.abs(values[c-1])) {
				start = 1;
			}
		}
		else {
			start = 0;
			end = c;
		}
		
		/** Prints the array of values around median, skipping the median itself */
		for(int i = start; i < end; i++) {
			if(values[i] + med != med){
				System.out.print((values[i]+med) + " ");
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
			System.out.println("a[" + i + "] = " + a[i]);
		}
		
		
		
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
			median = (quickSelect(a, 0, n-1, n/2) + quickSelect(a, 0, n-1, n/2 + 1))/2;
		}
		else {
			/** if array size is odd, find middle number */
			median = quickSelect(a, 0, n-1, n/2 + 1);
		}
		System.out.println("Median = " + median + ".");
		
		
		
		/** 
		 * Saving the difference from the median (a[i] - median) into new array diff
		 * Time complexity is 0(n)
		 */
		int [] diff = new int [n];
		for(int i = 0; i < n; i++) {
			diff[i] = a[i] - median;
			System.out.println("diff[" + i + "] = " + diff[i]);
		}
		
		
		
		/** 
		 * Finding the k numbers closest to the median using a modified quickSelect 
		 * Time complexity is O(n)
		 */
		quickSelectMedian(diff, 0, n, k, median);
		
		/** Total time complexity = O(n) */
		
	}

}
