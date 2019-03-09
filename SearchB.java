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
		
		/** Recording the worst possible linear time*/
		long startTime = System.nanoTime();
		int linResult = linSearch(array, size, key);
		long endTime = System.nanoTime();
		long linElapsedTime = endTime - startTime;
		System.out.println("Worst possible linear time: " + (linElapsedTime) + " microsec.");
		
		/** Recording the worst possible binary time*/
		startTime = System.nanoTime();
		int biResult = biSearch(array, size, key);
		endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Worst possible binary time: " + (elapsedTime) + " microsec.");
		
		/** Calculates time for a single binary step*/
		long step = (elapsedTime/ (long)Math.log(size));
		System.out.println("Time for a single binary step: " + step + " microsec.");
		
		/** Calculates time for single linear step*/
		long linStep = (linElapsedTime/size);
		
		/** n = 10^7*/
		int n = 10000000;
		
		/** Calculates worst-case time for each algorithm when n = 10^7*/		
		System.out.println("Worst possible linear time when n = 10^7: " + n * linStep + " microsec.");
		System.out.println("Worst possible binary time when n = 10^7: " + Math.log(n) * step + " microsec.");
				
		/** Close the scanner*/
		sc.close();
	}
}

