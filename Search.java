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
	 * @param k Key being searched for
	 * @return Position of key if found, -1 if not
	 */
	public static int linSearch(int arr[], int s, int k) {
		for (int i = 0; i < s; i++) {
			if (arr[i] == k) {
				return i;
			}
		}
		return -1;	//if not found
	}
	
	/**
	 * Method to search an array for a value using binary search
	 * @param arr Array being searched
	 * @param s Size of array
	 * @param k Key being searched for
	 * @return Position of key if found, -1 if not
	 */
	public static int biSearch(int arr[], int s, int k) {
		int up = s;
		int lo = 0;
		int mi;
		while (lo < up) {
			mi = lo + (up - lo) / 2;
	        if (arr[mi] == k) { 
	            return mi; 
	        }
	        if (arr[mi] < k) {
	            lo = mi + 1; 
	        }
	        else {
	            up = mi - 1; 
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
		
		/** Linear and binary search results*/
		int linResult;
		int biResult;
		
		/** Records start time of algorithm*/
		long startTime = System.nanoTime();
		
		/** Running linear search 100 times and recording the runtime*/
		for (int i = 0; i < 100; i++) {
			key = array[ran.nextInt(size)];
			linResult = linSearch(array, size, key);
		}
		
		/** Records end time then prints the average runtime*/
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Average time to find linear: " + (elapsedTime/100) + " ns.");
		
		/** Records start time of algorithm*/
		startTime = System.nanoTime();
		
		/** Running binary search 100 times and recording the runtime*/
		for (int i = 0; i < 100; i++) {
			key = array[ran.nextInt(size)];
			biResult = biSearch(array, size, key);
		}
		
		/** Records end time then prints the average runtime*/
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("Average time to find binary: " + (elapsedTime/100) + " ns.");
		
		/** Setting the key to a value outside of the array*/
		key = 5000;
		
		/** Recording the worst possible linear and binary times*/
		startTime = System.nanoTime();
		linResult = linSearch(array, size, key);
		endTime = System.nanoTime();
		long linElapsedTime = endTime - startTime;
		System.out.println("Worst possible linear time: " + (linElapsedTime/100) + " ns.");
		
		startTime = System.nanoTime();
		biResult = biSearch(array, size, key);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		System.out.println("Worst possible binary time: " + (elapsedTime/100) + " ns.");
		
		/** Calculates time for a single binary step*/
		double step = 2;
		double inc = 1;
		while (step < elapsedTime/100) {
			step = pow(step, inc);
			inc = inc*2;
		}
		System.out.println("Time for a single binary step: " + (elapsedTime/100)/step + "ns.");
		
		/** Calculates worst-case time for each algorithm when n = 10^7 which is about 23*/
		double linStep = 2;
		inc = 1;
		while (linStep < linElapsedTime/100) {
			linStep = pow(linStep, inc);
			inc = inc*2;
		}
		
		System.out.println("Worst possible linear time when n = 10^7: " + 23 * linStep + " ns.");
		System.out.println("Worst possible binary time when n = 10^7: " + 23 * step + " ns.");
				
		/** Close the scanner*/
		sc.close();
	}
}

