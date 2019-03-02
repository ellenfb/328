/**
 * Class SqrtBinary Finds an integer's square root using a binary search algorithm, 
 * and analyzes a sorted binary array for the split location
 * @author Ellen Burger
 * @version 2/24/19
 */

/** Uses user input*/
import java.util.Scanner;

/** Generates random numbers*/
import java.util.Random;

/** Provides advanced arithmetic functions*/
import static java.lang.Math.*;

public class SqrtBinary {

	/**
	 * Method to calculate the square root of a number up to two decimal places
	 * @param n Integer that has its square root calculated
	 * @return mi The square root
	 */
	public static double sqrtSearch(int n) {
		int up = n;
		int lo = 0;
		double mi = 0;
		while (lo <= up) {
			mi = lo + (up - lo) / 2;
			//System.out.println("Mi = " + mi + ", lo = " + lo + ", up = " + up );//test
	        if (mi * mi == n) { 
	            return mi; 
	        }
	        if (mi * mi < n) {
	            lo = (int)mi + 1; 
	            //System.out.println("Mi^2 < n, lo = " + lo);//test
	        }
	        else{
	            up = (int)mi - 1;
	        	//System.out.println("Mi^2 > n, up = " + up);//test
	        }
		}
		
		/** If the square root is not a whole number, calculates to two decimal places*/
		double upD = up + 1;
		double loD = lo - 1;
		while (loD <= upD) {
			mi = loD + (upD - loD) / 2;
			//System.out.println("Mi = " + mi + ", loD = " + loD + ", upD = " + upD );//test
	        if (mi * mi < n) {
	            loD = mi + 0.1; 
	            //System.out.println("Mi^2 < n, loD = " + loD);//test
	        }
	        else{
	            upD = mi - 0.1;
	        	//System.out.println("Mi^2 > n, upD = " + upD);//test
	        }
		}

		upD = upD + 0.1;
		loD = loD - 0.1;
		System.out.println(upD + " " + loD);
		while (loD <= upD) {
			mi = loD + (upD - loD) / 2;
			//System.out.println("Mi = " + mi + ", loD = " + loD + ", upD = " + upD );//test
	        if (mi * mi < n) {
	            loD = mi + 0.01; 
	            //System.out.println("Mi^2 < n, loD = " + loD);//test
	        }
	        else{
	            upD = mi - 0.01;
	        	//System.out.println("Mi^2 > n, upD = " + upD);//test
	        }
		}
		mi = loD * 100;
		int m = (int)mi;
		mi = (double)m / 100;
		return mi;
	}
	
	/**
	 * Method to search for the split in a binary array
	 * @param arr Array being searched
	 * @param s Size of array
	 * @return Position of split if found, -1 if not
	 */
	public static int biSearch(int arr[], int s) {
		int up = s;
		int lo = 0;
		int mi = 0;
		while (lo <= up) {
			mi = lo + (up - lo) / 2;
			//System.out.println("lo = " + lo + ", up = " + up + ", mi = " + mi);//test
	        if (mi > 0 && mi <= s-1 && arr[mi] == 1 && arr[mi-1] == 0) { 
	            return mi; 
	        }
	        if (mi > 0 && mi <= s-1 && arr[mi] == 0) {
	            lo = mi + 1; 
	        }
	        else if(mi > 0 && mi <= s-1 && arr[mi] == 1){
	            up = mi - 1; 
	        }
	        else {
	        	return -1;
	        }
		}
		return -1;
	}
	
	/** Main method*/
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		/** Asks for an integer and returns the square root*/
		System.out.println("Enter an integer.");
		int n = sc.nextInt();
		System.out.println("Integer = " + n);
		System.out.println("Square root = " +sqrtSearch(n));
		
		/** Creates and locates the split location in a binary array*/		
		System.out.println("Enter Array Size.");
		int size = sc.nextInt();
		int [] array = new int [size];
		
		System.out.println("Fill the array starting with 0, shifting to 1 randomly.");
		for (int i = 0; i < size; i++) {
			array[i] = sc.nextInt();
		}
		int splt = biSearch(array, size);
		if (splt == -1) {
			System.out.println("There is no split.");
		}
		else {
			System.out.println("Split location = " + splt);
		}
		
		sc.close();
	}
}
