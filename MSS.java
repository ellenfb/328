/**
 * Class MSS Finds the maximum subsequence sum of an array using two functions, one with O(nlogn) and one with O(n)
 * @author Ellen Burger
 * @version 4/22/19
 */

/** Uses user input */
import java.util.Scanner;

/** Allows use of array functions */
import java.util.Arrays;

/** Generates random numbers */
import java.util.Random;

public class MSS {
	
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
	 * Finds the MSS of the given array. It repeatedly breaks down the array to find all possible SS's and returns the largest
	 * Complexity: O(nlogn)
	 * @param arr Array to be searched
	 * @param lo Lower bound of array being searched
	 * @param hi Upper bound of array being searched
	 * @return MSS of the results of each iteration
	 */
	public static int MSS_1_Subset(int [] arr, int lo, int hi) {
		
		/** Finding mid point to divide array with */
		int mid = (hi + lo)/2;
		
		/** When broken down array contains only one element. Returns positive infinity if element is negative */
		if(lo == hi) {
			return arr[hi];
		}
		
		/** Recursively calls the function with a smaller array segment each time, returning the smallest MSS of them all */
		return Math.max(MSS_1_Alg(arr, lo, hi, mid), Math.max(MSS_1_Subset(arr, lo, mid), MSS_1_Subset(arr, mid+1, hi)));
	}
	
	/**
	 * Function that finds the MSS of the array segments created in MSS_Subset
	 * This is called by MSS_1_Subset for each segment of the entire array
	 * @param arr Array to be searched
	 * @param lo Lower bound
	 * @param hi Upper bound
	 * @param mid Midpoint of the array
	 * @return Returns the MSS of the array segment
	 */
	public static int MSS_1_Alg(int [] arr, int lo, int hi, int mid) {
		
		int tmp = 0;
		int sumL = 0;
		int sumR = 0;
		
		/** Finds the sum of the left half, stores into sumL if it meets MSS conditions */
		for(int i = mid; i >= lo; i--) {
			tmp = tmp + arr[i];
			if(tmp > sumL) {
				sumL = tmp;
			}
		}
		
		/** Finds the sum of the right half, stores into sumL if it meets MSS conditions */
		tmp = 0;
		for(int i = mid + 1; i <= hi; i++) {
			tmp = tmp + arr[i];
			if(tmp > sumR) {
				sumR = tmp;
			}
		}
		
		/** Returns the segment's sum by adding its too halves together */
		tmp = sumL + sumR;
		return tmp;
		
	}
	
	/**
	 * Finds the MSS of the given array. It iterates through the array, recording the highest sum and resetting whenever it becomes < 0.
	 * Complexity: O(n)
	 * @param arr Array to be searched
	 * @param lo Lower bound of array being searched
	 * @param hi Upper bound of array being searched
	 * @return MSS of the array
	 */
	public static int MSS_2(int [] arr, int lo, int hi) {
		
		/** Keeps track of maximum sum found and current sum in tmp */
		int max = 0;
		int tmp = 0;
		
		/** Iterates through array */
		for(int i = 0; i <= hi; i++) {
			
			/** Adds array elements into tmp */
			tmp = tmp + arr[i];
			
			/** If new maximum found, set max to it */
			if(tmp > max) {
				max = tmp;
			}
			
			/** If tmp drops below zero, reset to zero */
			else if (tmp < 0) {
				tmp = 0;
			}
		}
		
		/** Return MSS */
		return max;
	}

	public static void main(String[] args) {

		/** Scanner and random objects */
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();
		
		/** Variables used to generate random array */
		int ranRange = 200;
		int ranStart = -100;
		
		/** Creating an array */
		System.out.println("Enter size of array.");
		int size = scan.nextInt();
		int [] array = new int [size];
		randomize(array, size, ran, ranRange, ranStart);
		System.out.println(Arrays.toString(array));
		
		/** Printing the results of each MSS fuction */
		System.out.println("MSS of O(nlogn) function: " + MSS_1_Subset(array, 0, size - 1));
		System.out.println("MSS of O(n) function: " + MSS_2(array, 0, size - 1));
		
		scan.close();
		
	}

}
