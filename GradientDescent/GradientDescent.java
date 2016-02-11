import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradientDescent {
	
	public static double getError(double t0, double t1, ArrayList<Double> xValues, ArrayList<Double> yValues) // Error function
	{
		double e = 0.0; // initializes the error variable
		int size = xValues.size(); // size of training data
		
		for (int i = 0; i < size; i++) // iterates through all the training data
		{
			e += (yValues.get(i) - (t1 * xValues.get(i) + t0)) * 2.0;
		}
		
		return (e / (double) size); // returns error
	}
	
	public static double[] gradientDescent(double t0, double t1, double a, ArrayList<Double> xValues, ArrayList<Double> yValues) // gradient descent function
	{
		
		int size = xValues.size(); // size of training data
		double t0_gradient = 0; // initializes t0_gradient
		double t1_gradient = 0; // initializes t1_gradient
		
		
		for (int i = 0; i < size; i++) // iterates through all the training data
		{
			t0_gradient += -(2.0/size) * (yValues.get(i) - ((t1*xValues.get(i)) + t0)); // updates t0_gradient
			t1_gradient += -(2.0/size) * xValues.get(i) * (yValues.get(i) - ((t1*xValues.get(i)) + t0)); // updates t1_gradient
		}
		
		double thetas[] = new double[2]; // defines array of size two, to be returned by this function
		
		thetas[0] = t0 - (a * t0_gradient); // updates theta 0
		thetas[1] = t1 - (a * t1_gradient); // updates theta 1
		
		return thetas; // returns new thetas
		
	}
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		String fileName;
		int n; // n -> number of iterations
		double t0, t1, a; // t0 -> theta 0; t1 -> theta 1; a -> learning rate
		
		System.out.print("Input file: ");
		fileName = in.next(); // stores the name of the file into fileName
		System.out.print("t0 guess: ");
		t0 = in.nextDouble(); // stores theta 0 into t0
		System.out.print("t1 guess: ");
		t1 = in.nextDouble(); // stores theta 1 into t1
		System.out.print("learning rate (a): ");
		a = in.nextDouble(); // stores the learning rate into a
		System.out.print("Iterations: ");
		n = in.nextInt(); // stores the number of iterations into n
		
		ArrayList<Double> xValues = new ArrayList<Double>(); // defines an ArrayList that stores all the training data's X values
		ArrayList<Double> yValues = new ArrayList<Double>(); // defines an ArrayList that stores all the training data's Y values
		
		FileInputStream inputFile = null;
		
		try {
			inputFile = new FileInputStream(fileName); // tries to open file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		Scanner inFile = new Scanner(inputFile); // opens scanner for the file
		
		
		while(inFile.hasNext()) // reads file until there is nothing else to read
		{
			xValues.add((double) inFile.nextInt()); // stores x value from line i into xValues
			yValues.add(inFile.nextDouble()); // stores y value from line i into yValues
		}
		
		// if you want to check the error before running the gradient descent function:
		//System.out.println("Error: "+getError(t0, t1, xValues, yValues));
		
		double thetas[] = new double[2]; // defines array of size 2 that will store values of thetas from the gradient function
		thetas[0] = t0; // first value of t0 is the one defined by user
		thetas[1] = t1; // first value of t1 is the one defined by user
		
		for (int i = 0; i < n; i++) // iterates n times
		{
			thetas = gradientDescent(thetas[0], thetas[1], a, xValues, yValues); // updates thetas in each iteration
		}
		
		//t0 = thetas[0];
		//t1 = thetas[1];
		
		
		
		System.out.println("t0: " + thetas[0] + " t1: " + thetas[1]); // prints both thetas
		//System.out.println("Error: "+getError(t0, t1, xValues, yValues));
		
		inFile.close(); // closes file scanner
		in.close(); // closes terminal scanner
	}
}
