package lab01.student;
import java.util.*;
public class GoodHashFunc {
	public static int computeHash(String str) {
		int h = 0;
		int n = 0;
		int [] hashArray = new int [str.length()];
		for (int i = 0; i<str.length(); i++) {
			char chr = str.charAt(i);
			hashArray[i] = chr*(int) Math.pow(31, str.length()-(i+1));
		}
		while (n < str.length()) {
			h += hashArray[n];
			n+=1;
		}
		return h;
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a String: ");
		String str = in.next();
		int output = computeHash(str);
		System.out.print("The computed has for the specified string is: ");
		System.out.print(output);
	}

}
