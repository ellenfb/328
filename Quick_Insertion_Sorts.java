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

/** Provides advanced arithmetic functions*/
import static java.lang.Math.*;

public class Quick_Insertion_Sorts {
	
	/**
	 * Quick sorts an array
	 * @param arr Array to be sorted
	 * @param lo Lower bound
	 * @param hi Upper bound
	 */
	public static void quickSort(int arr [], int lo, int up) {
		
		if (lo < up) {
			int prt = partition(arr, lo, up);
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
		
		int x = 0;
		int y = 0;
		int z;
		
		while (x < s) {
			y = x;
			
			while (y > 0 && arr[y-1] > arr[y]) {
				z = arr[y];
				arr[y] = arr[y-1];
				arr[y-1] = z;
				y--;
			}
			
			x++;
		}
	}
	
	/**
	 * Finds the partition for the sort algorithm
	 * @param arr Array being partitioned
	 * @param lo Lower bound of algorithm
	 * @param up Upper bound of algorithm
	 * @return Partition
	 */
	public static int partition(int arr[], int lo, int up) {
		
		int up2 = arr[up];
		int lo2 = lo;
		int x;
		
		for (int i = lo; i < up - 1; i++) {
			
			if (arr[i] <= up2) {
				
				x = arr[lo2];
				arr[lo2] = arr[i];
				arr[i] = x;
				lo2++;
			}
		}
		x = arr[lo2];
		arr[lo2] = arr[up];
		arr[up] = x;
		return lo2;
	}
	
	public static void main(String[] args) {
	
		/** Variable declaration*/
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		long t1 = 0;
		long t2 = 0;
		
		/** Creates array*/
		System.out.println("Enter Array Size");
		int size = sc.nextInt();
		int [] a = new int [size];
		
		/** Calling quick sort 100 times, recording time*/
		for (int j = 0; j < 100; j++) {
			for (int i = 0; i < size; i++) {
				a[i] = ran.nextInt(10000) - 5000;
			}
			
			long startTime = System.nanoTime();
			quickSort(a, 0, size-1);
			long endTime = System.nanoTime();
			t1 += (endTime - startTime);
		}
		System.out.println("Quick Sort:");
		System.out.println("Average runtime: " + t1/100 + " ns.");
		
		/** Calling insertion sort 100 times, recording time*/
		for (int j = 0; j < 100; j++) {
			for (int i = 0; i < size; i++) {
				a[i] = ran.nextInt(10000) - 5000;
			}
			
			long startTime = System.nanoTime();
			quickSort(a, 0, size-1);
			long endTime = System.nanoTime();
			t2 += (endTime - startTime);
		}
		System.out.println("Insertion Sort:");
		System.out.println("Average runtime: " + t2/100 + " ns.");
		
		/** Calculating number of insertion sorts that can be done in one second*/
		System.out.println("Estimated insertion sorts in one second: " + 1000000000/t2 + ".");
		
		/** Closing the scanner*/
		sc.close();
	}

}
