package lab01.student;

import java.util.Scanner;

public class PrimalityTest {
	public static boolean isPrime(int number) {
		boolean prime = false;
		for (int i = 2; i<=number;i++) {
			if (number == 1) {
				prime = false;
				break;
			}
			else if ((number%i)==0 && i != number){
				prime = false;
				break;
			}
			else {
				prime = true;
			}
		}
		return prime;
	}
	public static void main(String[] args) {
		while (true) {
			System.out.print("Enter a number (0 to quit): ");
			Scanner in = new Scanner(System.in);
			int number = in.nextInt();
			if (number < 1) {
				System.out.println("Goodbye!");
				break;
			}
			boolean prime = isPrime(number);
			if (prime == true) {
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
