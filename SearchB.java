/**
 * Class Search Searches an array with two different algorithms and determines their run times
 * @author Ellen Burger
 * @version 2/16/19
 */

/** Uses user input*/
import java.util.Scanner;

/**Records program runtime*/
import java.util.concurrent.TimeUnit;

/** Provides advanced arithmetic functions*/
import static java.lang.Math.*;

public class SearchB {
	
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
		
		/** Creates and sorts randomly generated array of specified size*/
		System.out.println("Enter Array Size");
		int size = sc.nextInt();
		int [] array = new int [size];
		
		/** Setting the key to a value outside of the array*/
		int key = 5000;
		
		/** Recording the worst possible linear and binary times*/
		long startTime = System.nanoTime();
		int linResult = linSearch(array, size, key);
		long endTime = System.nanoTime();
		long linElapsedTime = endTime - startTime;
		System.out.println("Worst possible linear time: " + (linElapsedTime/100) + " ns.");
		
		startTime = System.nanoTime();
		int biResult = biSearch(array, size, key);
		endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
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

