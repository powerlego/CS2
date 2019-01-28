package lab01.student;
import static turtle.Turtle.*;

import java.util.Scanner;
public class HTree {
	public static final int MAX_SEGMENT_LENGTH = 200;
	public static void init(int length, int depth) {
		Turtle.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
		Turtle.title("H-Tree, depth: "+ depth);
	}
	public static void drawHTree(double length, int depth) {
		if (depth > 0) {
			Turtle.forward(length/2);
			Turtle.left(90);
			Turtle.forward(length/2);
			Turtle.right(90);
			drawHTree(length/2, depth-1);
			Turtle.right(90);
			Turtle.forward(length);
			Turtle.left(90);
			drawHTree(length/2, depth-1);
			Turtle.left(90);
			Turtle.forward(length/2);
			Turtle.left(90);
			Turtle.forward(length);
			Turtle.right(90);
			Turtle.forward(length/2);
			Turtle.right(90);
			Turtle.right(90);
			Turtle.forward(length);
			Turtle.left(90);
			drawHTree(length/2, depth-1);
			Turtle.left(90);
			Turtle.forward(length/2);
			Turtle.right(90);
			Turtle.forward(length/2);
		}
	}
	public static void main(String [] args) {
		int depth = Integer.parseInt(args[0]);
		if (depth < 0) {
			System.out.println("The depth must be greater than or equal to 0");
		}
		else {
			init(MAX_SEGMENT_LENGTH, depth);
			drawHTree(MAX_SEGMENT_LENGTH, depth);
			System.out.println("Press X to close...");
		}
	}
}
