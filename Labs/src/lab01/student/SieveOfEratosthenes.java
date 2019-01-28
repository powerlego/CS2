package lab01.student;

import java.util.Scanner;

public class SieveOfEratosthenes {
	public static int[] makeSieve(int upperBound) {
		int [] sieve = new int [upperBound];
		sieve[0] = 1;
		sieve[1] = 1;
		for (int i=2; i <= (int) Math.sqrt(upperBound); i++) {
			for (int j = (int)Math.pow(i, 2); j < upperBound; j+=i) {
				sieve[j] = 1;
			}
		}
		return sieve;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter an upper bound: ");
		int upperBound = in.nextInt();
		int [] sieve = makeSieve(upperBound);
		while (true) {
			System.out.print("Please enter a positive number (0 to quit): ");
			int number = in.nextInt();
			if (number == 0) {
				System.out.println("Goodbye!");
				break;
			}
			if (sieve[number]==0) {
				System.out.print(number);
				System.out.println(" is prime!");
			}
			else {
				System.out.print(number);
				System.out.println(" is not prime!");
			}
		}
		
	}
}
