/**
 * Class Quick_Insertion_Sorts Implements and records the runtime of both sorting methods
 * @author Ellen Burger
 * @version 3/7/19
 */

/** Uses user input*/
import java.util.Scanner;

/** Generates random numbers*/
import java.util.Random;

/**Records program runtime*/
import java.util.concurrent.TimeUnit;

public class Quick_Insertion_Sorts {
	
	/**
	 * Quick sorts an array
	 * @param arr Array to be sorted
	 * @param lo Lower bound
	 * @param hi Upper bound
	 */
	public static void quickSort(int arr [], int lo, int up) {
		
		if (lo < up) {
			int prt = findPartition(arr, lo, up);
			quickSort(arr, lo, prt - 1);
			quickSort(arr, prt + 1, up);
		}	
	}
	
	/**
	 * Insertion sorts an array
	 * @param arr Array to be sorted
	 * @param s Size of array
	 */
	public static void insertSort(int arr [], int s) {
		
		int i = 0;
		int j = 0;
		int swap;
		
		for(i = 0; i < s; i++) {
			j = i;
			
			while (j > 0 && arr[j-1] > arr[j]) {
				swap = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = swap;
				j--;
			}
		}
	}
	
	/**
	 * Finds the partition for the sort algorithm
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int findPartition(int arr[], int lo, int up) {
		
		int piv = arr[lo];
		int prt = lo + 1;
		int swap;
		
		for (int i = lo+1; i <= up; i++) {
			
			if (arr[i] >= piv) {
				
				if(i != prt) {
					swap = arr[i];
					arr[i] = arr[prt];
					arr[prt] = swap;
					prt++;
				}
			}
		}
		
		swap = arr[prt - 1];
		arr[prt - 1] = arr[lo];
		arr[lo] = swap;
		return prt - 1;
	}
	
	public static void main(String[] args) {
	
		/** Variable declaration*/
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		int repeat = 100;
		long t1 = 0;
		long t2 = 0;
		
		/** Creates array*/
		System.out.println("Enter Array Size");
		int size = sc.nextInt();
		int [] a = new int [size];
		
		/** Calling quick sort 100 times, recording time*/
		for (int j = 0; j < repeat; j++) {
			for (int i = 0; i < size; i++) {
				a[i] = ran.nextInt(10000) - 5000;
			}
			
			long startTime = System.nanoTime();
			quickSort(a, 0, size-1);
			long endTime = System.nanoTime();
			t1 += (endTime - startTime);
		}
		System.out.println("Quick Sort:");
		System.out.println("Average runtime: " + t1/repeat + " ns.");
		
		
		/** Calling insertion sort 100 times, recording time*/
		for (int j = 0; j < repeat; j++) {
			for (int i = 0; i < size; i++) {
				a[i] = ran.nextInt(10000) - 5000;
			}
			
			long startTime = System.nanoTime();
			insertSort(a, size);
			long endTime = System.nanoTime();
			t2 += (endTime - startTime);
		}
		
		System.out.println("Insertion Sort:");
		System.out.println("Average runtime: " + t2/repeat + " ns.");
		
		/** Calculating number of insertion sorts that can be done in one second*/
		System.out.println("Estimated insertion sorts in one second: " + 1000000000/t2 + ".");
		
		/** Closing the scanner*/
		sc.close();
	}

}
