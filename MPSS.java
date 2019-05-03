/**
 * Class MPSS that finds the minimum positive subsequence sum of an array using divide and conquer algorithms
 * @author Ellen
 * @version 4/17/19
 */

/** Uses user input */
import java.util.Scanner;

/** Array functions */
import java.util.Arrays;

/** Generates random numbers */
import java.util.Random;

public class MPSS {
	
	/**
	 * Quick sorts an array descendingly
	 * @param arr Array to be sorted
	 * @param lo Lower bound
	 * @param hi Upper bound
	 */
	public static void quickSortDown(double arr [], int lo, int up) {
		
		/** If lo >= up, the current recursion is the last one */
		if (lo < up) {
			
			/** Finds a new pivot to partition around */
			int prt = findPartitionDown(arr, lo, up);
			
			/** Partitions into two subarrays, recursively sorting the entire array around pivots */
			quickSortDown(arr, lo, prt - 1);
			quickSortDown(arr, prt + 1, up);
		}	
	}
	
	/**
	 * Finds the partition for the sort down algorithm
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int findPartitionDown(double arr[], int lo, int up) {
		
		/** Pivot is set to the beginning of the array */
		double piv = arr[lo];
		int prt = up;
		
		/** Iterates down through array */
		for (int i = up; i > lo; i--) {
			
			/**
			 * If pivot is >= array element at i, swapped with element at position prt
			 * prt is then decremented so the position before it is the location of the next swap if any
			 */
			if (piv >= arr[i]) {
				
				swap(arr, prt, i);
				prt--;
			}
		}
		
		/** prt and beginning of array are swapped, prt returned */
		swap(arr, prt, lo);
		return prt;
	}

	/**
	 * Quick sorts an array
	 * @param arr Array to be sorted
	 * @param lo Lower bound
	 * @param hi Upper bound
	 */
	public static void quickSort(double arr [], int lo, int up) {
		
		/** If lo >= up, the current recursion is the last one */
		if (lo < up) {
			
			/** Finds a new pivot to partition around */
			int prt = findPartition(arr, lo, up);
			
			/** Partitions into two subarrays, recursively sorting the entire array around pivots */
			quickSort(arr, lo, prt - 1);
			quickSort(arr, prt + 1, up);
		}	
	}
	
	/**
	 * Finds the partition for the sort algorithm
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int findPartition(double arr[], int lo, int up) {
		
		/** Pivot is set to the beginning of the array */
		double piv = arr[lo];
		int prt = up;
		
		/** Iterates down through array */
		for (int i = up; i > lo; i--) {
			
			/**
			 * If pivot is <= array element at i, swapped with element at position prt
			 * prt is then decremented so the position before it is the location of the next swap if any
			 */
			if (piv <= arr[i]) {
				
				swap(arr, prt, i);
				prt--;
			}
		}
		
		/** prt and beginning of array are swapped, prt returned */
		swap(arr, prt, lo);
		return prt;
	}
	
	/**
	 * Finds the minimum positive sum subset of the left and right half of the given array
	 * In the context of the program, it repeatedly breaks down an array to find the MPSS of the entirety of each half
	 * @param arr Array to be searched
	 * @param lo Lower bound of array being searched
	 * @param hi Upper bound of array being searched
	 * @return MPSS of the results of each iteration
	 */
	public static double MPSS_Subset(double [] arr, int lo, int hi) {
		
		/** When broken down array contains only one element. Returns positive infinity if element is negative */
		if(lo == hi) {
			if(arr[lo] < 0) {
				return Double.POSITIVE_INFINITY;
			}
			return arr[lo];
		}
		
		/** Finding mid point to divide array with */
		int mid = (hi + lo)/2;
		
		/** Recursively calls the function with a smaller array segment each time, returning the smallest MPSS of them all */
		return Math.min(Math.min(MPSS_Subset(arr, lo, mid), MPSS_Subset(arr, mid+1, hi)), 
				MPSS_Alg(arr, lo, hi, mid));
	}
	
	/**
	 * Function that finds the MPSS of the array segments created in MPSS_Subset
	 * @param arr Array to be searched
	 * @param lo Lower bound
	 * @param hi Upper bound
	 * @param mid Midpoint of the array
	 * @return Returns the MPSS of the array segment
	 */
	public static double MPSS_Alg(double [] arr, int lo, int hi, int mid) {
		
		double tmp = 0;
		double sumL = Double.POSITIVE_INFINITY;
		double sumR = Double.POSITIVE_INFINITY;
		
		/** Finds the sum of the left half, stores into sumL if it meets MPSS conditions */
		for(int i = mid; i >= lo; i--) {
			tmp += arr[i];
			if(tmp >= 0 && tmp < sumL) {
				sumL = tmp;
			}
		}
		
		tmp = 0;
		
		/** Finds the sum of the right half, stores into sumL if it meets MPSS conditions */
		for(int i = mid + 1; i <= hi; i++) {
			tmp += arr[i];
			if(tmp >= 0 && tmp < sumR) {
				sumR = tmp;
			}
		}
		
		/** Returns the segment's sum by adding its too halves together */
		return sumL + sumR;
		
	}
	
	/**
	 * Finds the minimum positive subsequence sum using two subarrays or the array being searched
	 * @param sL Left half of the array being searched, containing its sequential sums sorted ascendingly
	 * @param sR Right half of the array being searched, containing its sequential sums sorted descendingly
	 */
	public static double MPSSMid(double [] sL, double [] sR) {
		
		int i= 0;
		int j = 0;
		double s = 0;
		double min = Double.POSITIVE_INFINITY;
		
		/** Iterates through each sum array until one is exhausted */
		while(i < sL.length && j < sR.length) {
			
			/** Adds the sums from one element of each array */
			//System.out.println(s + " = " + sL[i] + " + " + sR[j]);
			s = sL[i] + sR[j];
			
			/** If sum <= 0, try with the next element of left array */
			if(s <= 0) {
				//System.out.println("s = " + s + " <= zero");
				i++;
			}
			
			/** If sum < current minimum (starting at positive infinity), sets it as the new minimum and tries with next element of right array */
			else if(s < min) {
				//System.out.println("s = " + s + " < min = " + min);
				min = s;
				j++;
			}
			
			/** If sum > min, try with next element of right array */
			else {
				//System.out.println("s = " + s + " > min = " + min);
				j++;
			}
		}
		
		/** Sets middle as minimum subset across the middle of the array and returns it */
		double middle = min;
		
		/*if(middle == Double.POSITIVE_INFINITY) {
			System.out.println("No subset results in a positive result.");
		}
		else {
			System.out.println("MPSS Mid = " + middle);
		}*/
		
		return middle;
	}
	
	/**
	 * Randomizes the contents of an integer array
	 * @param arr Array to be randomized
	 * @param size Size of the array
	 * @param ran Random object to call random doubles
	 * @param range Width of the field of numbers to choose from
	 * @param start Starting location of the field of numbers
	 */
	public static void randomize(double arr [], int size, Random ran, int range, int start) {
		
		for(int i = 0; i < size; i++) {
			arr[i] = start + range * ran.nextDouble();
		}
	}
	
	/**
	 * Swaps the values of two integer objects
	 * @param inputA First integer to be swapped
	 * @param inputB Second integer to be swapped
	 */
	public static void swap(double arr [], int posA, int posB) {
		
		double temp = arr[posA];
		arr[posA] = arr[posB];
		arr[posB] = temp;
	}
	
	/**
	 * Trims the decimal values after one place
	 * @param Array with values to trim
	 * @param size Size of array
	 */
	public static void trim(double arr [], int size) {
		
		for(int i = 0; i < size; i++) {
			int tmp = (int)(arr[i] * 10);
			arr[i] = (double)tmp / 10;
		}
	}
	
	/**
	 * Trims the decimal values after one place
	 * @param Value to trim
	 */
	public static void trim(double value) {
		
		int tmp = (int)(value * 10);
		value = (double)tmp / 10;
	}
	
	public static void main(String[] args) {
		
		/** Randomizer and Scanner */
		Random ran = new Random();
		Scanner scan = new Scanner(System.in);
	
		/** Creating a randomized array of -20 to 20 of given size */
		/*System.out.println("Enter a positive integer for array size.");
		int size = scan.nextInt();
		double [] array = new double [size];
		randomize(array, size, ran, 40,-20);
		trim(array, size);*/
		
		/** Pre-made array currently in use */
		int size = 10;
		double [] array = {2, -3, 1, 4, -6, 10, -12, 5.2, 3.6, -8};
		
		System.out.println(Arrays.toString(array));//test
		
		
		/** Creating left and right subarrays and storing sums. Right array is one larger than left if array size is odd */
		double [] sL = new double [size/2];
		double [] sR;
		sL[0] = array[0];
		
		/** Filling left subarray */
		for(int i = 1; i < sL.length; i++) {
			sL[i] = array[i] + sL[i-1];
		}
		trim(sL, sL.length);
		quickSort(sL, 0, sL.length - 1);
		
		/** Declaring size of right subarray */
		if(size % 2 == 0) {
			sR = new double [size/2];
		}
		else {
			sR = new double [size/2 + 1];
		}
		
		/** Filling right subarray */
		sR[0] = array[size/2];
		for(int i = 1; i < sR.length; i++) {
			sR[i] = array[i + (size/2)] + sR[i-1];
		}
		trim(sR, sR.length);
		quickSortDown(sR, 0, sR.length - 1);
		
		/*System.out.println(Arrays.toString(sL));
		System.out.println(Arrays.toString(sR));*/
		
		/** Finding minimum sum within left and right half */
		double subset = MPSS_Subset(array, 0, size - 1);
		
		/** Finding minimum sum across entire array */
		double middle = MPSSMid(sL, sR);
		
		//System.out.println(subset + ", " + middle);//test
		
		/** Printing result */
		if(subset == Double.POSITIVE_INFINITY && middle == Double.POSITIVE_INFINITY) {
			System.out.println("Nothing results in positive sum.");
		}
		else if(subset < middle) {
			trim(subset);
			System.out.println("MPSS = " + subset);
		}
		else {
			trim(middle);
			System.out.println("MPSS = " + middle);
		}		
		
		scan.close();
	}

}
