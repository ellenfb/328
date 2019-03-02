/**
 * Class Search Searches an array with two different algorithms and determines their run times
 * @author Ellen Burger
 * @version 2/16/19
 */

/** Uses user input*/
import java.util.Scanner;

/** Generates random numbers*/
import java.util.Random;

/**Records program runtime*/
import java.util.concurrent.TimeUnit;

/** Provides advanced arithmetic functions*/
import static java.lang.Math.*;

public class Search {
	
	/**
	 * Method to sort an array by ascending integers
	 * @param arr Array being sorted
	 * @return Sorted array
	 */
	public static int [] sort(int arr []) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length-1-i; j++) { 
				if(arr[j]>arr[j+1]) {
			       int temp=arr[j];
			       arr[j]=arr[j+1];
			       arr[j+1]=temp;
			    }
			}
		}
		return arr;
	}
	
	/**
	 * Method to search an array for a value using linear search
	 * @param arr Array being searched
	 * @param s Size of array
	 * @param n Number of times to repeat
	 * @param r Random number generator
	 * @return Position of key if found, -1 if not
	 */
	public static int linSearch(int arr[], int s, int n, Random r) {
		int key = r.nextInt(s);
		int found = -1;
		for (int j = 0; j < n-1; j++) {
			for (int i = 0; i < s; i++) {
				if (arr[i] == key) {
					found = i;
					key = r.nextInt(s);
					i = s;
				}
			}
		}
		return found;
	}
	
	/**
	 * Method to search an array for a value using binary search
	 * @param arr Array being searched
	 * @param s Size of array
	 * @param n Number of times to repeat
	 * @param r Random number generator
	 * @return Position of key if found, -1 if not
	 */
	public static int biSearch(int arr[], int s, int n, Random r) {
		int key = r.nextInt(s);
		int up = s;
		int lo = 0;
		int mi;
		for(int j = 0; j < n - 1; j++) {
			while (lo < up) {
				mi = lo + (up - lo) / 2;
				if (arr[mi] == key) { 
					biSearch(arr, s, 1, r);
					return mi; 
				}
				if (arr[mi] < key) {
					lo = mi + 1; 
				}
				else {
					up = mi - 1; 
				}
			}
		}
		return -1;
	}

	/** Main method*/
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		/** Creates and sorts randomly generated array of specified size*/
		System.out.println("Enter Array Size");
		int size = sc.nextInt();
		int [] array = new int [size];
		
		for (int i = 0; i < size; i++) {
			array[i] = ran.nextInt(2001) - 1000;
		}
		sort(array);
		
		/** Key that will be searched for*/
		int key;
		
		/** Records start time of algorithm*/
		long startTime = System.nanoTime();
		
		/** Running linear search 100 times and recording the runtime*/
		linSearch(array, size, 100, ran);
		
		/** Records end time then prints the average runtime*/
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Average time to find linear: " + (elapsedTime/100) + " ns.");
		
		/** Records start time of algorithm*/
		startTime = System.nanoTime();
		
		/** Running binary search 100 times and recording the runtime*/
		biSearch(array, size, 100, ran);
		
		/** Records end time then prints the average runtime*/
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("Average time to find binary: " + (elapsedTime/100) + " ns.");
				
		/** Close the scanner*/
		sc.close();
	}
}

