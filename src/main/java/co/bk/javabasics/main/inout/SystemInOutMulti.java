package co.bk.javabasics.main.inout;

import java.util.Scanner;

public class SystemInOutMulti {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int numberOfLinesToRead = new Integer(s.nextLine());
		String[] result = new String[numberOfLinesToRead];
		String line = "";
		for(int i = 0; i < numberOfLinesToRead; i++) { // this loop will be run 3 times, as specified in the first line of input
		    result[i] = s.nextLine(); // each line of the input will be placed into the array.
		}
		System.out.println("size=" + result.length);
	}

}
