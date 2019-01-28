import java.util.ArrayList;
import java.util.Arrays;

import javax.lang.model.util.Elements;

/**
 * CSCI-142 Computer Science 2 Recitation Exercise
 * 01 - Java Intro
 * Polynomials
 *
 * Write a program that reads a polynomial from the command line into an
 * ArrayList (starting with the first term's coefficient).
 *
 * There are three functions to perform operations on the polynomial once it is
 * in list form:
 * 1. toString - convert the polynomial list into a string
 * 2. evaluate - evaluate the polynomial list for x=2
 * 3. derivative - compute the derivative of the polynomial list
 *
 * A sample run:
 * $ java Poly 2 4 0 1 5
 * args: [2, 4, 0, 1, 5]
 * polyList: [2, 4, 0, 1, 5]
 * toString: 5x^4 + 1x^3 + 4x + 2
 * evaluate(x=2): 98
 * derivative: 20x^3 + 3x^2 + 4
 *
 * This is the student starter code.
 */
public class Poly {
    /**
     * Convert a polynomial in a list into a string representation.
     * For example:
     *     to_string([2, 4, 0, 1, 5]) -> "5x^4 + 1x^3 + 4x + 2"
     * @param polyList the polynomial as an ArrayList
     * @return a string (String) of the polynomial
     */
	public static final int x = 2;
    public static String toString(ArrayList<Integer> polyList) {
        // TODO: Step 1 - Implement toString()
    	String output = "";
    	for (int i = 0; i< polyList.size(); i++) {
    		if (i == 0) {
    			output += polyList.get(i);
    		}
    		else {
    			output+= polyList.get(i).toString() + "x^" + i;
    		}
    	}
        return output;    // remove or change this line after implementing
    }
    public static int evaluate(String expression) {
    	int total = 0;
    	String [] parts = expression.split(" \\+ ",0);
    	for (String elements : parts) {
    		if (elements == "x") {
    			
    		}
    	}
    	return total;
    }
    /**
     * Evaluate the polynomial for a given x.
     * @param polyList the polynomial as an ArrayList
     * @param x the value of x to evaluate the polynomial for
     * @return the evaluation result (an int)
     */
    // TODO: Step 2 - Implement evaluate() with the method signature

    // TODO: Step 3 - Implement derivative() with the method signature and javadoc

    /**
     * The main function reads the polynomial in from the command line
     * into an ArrayList and then calls several other functions to manipulate
     * the polynomial.
     * @param args
     */
    public static void main(String[] args) {
    	evaluate("5x^4 + 1x^3 + 4x + 2");
       /* if (args.length < 1) {
            System.out.println("Usage: java Poly term0 ...");
        } else {
            System.out.println("args: " + Arrays.asList(args));
            // create an initially empty list for the polynomial
            ArrayList<Integer> polyList = new ArrayList<>();
            // store the coefficients of the polynomial starting with the constant term.
            for (String term : args) {
                polyList.add(Integer.parseInt(term));
            }

            // call functions to manipulate the polynomial and display the results
            System.out.println("polyList: " + (polyList));
            System.out.println("toString: " + toString(polyList));
            // System.out.println("evaluate(x=2): " + evaluate(polyList, 2));
            // System.out.println("derivative: " + toString(derivative(polyList)));
        }*/
    }
}