import java.util.Scanner;

public class day2 {
	public static int fact(int num) {
		if (num == 1) {
			return 1;
		}
		return fact(num-1)*num;
	}
	public static void sums (int [] values) {
		int sum = 0;
		for (int i=0; i<10; i++) {
			sum += i;
			values [i] = sum;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [] chr = "o".toCharArray();
		for (int element : chr) {
			System.out.println(element);
		}
		
		
	}

}
