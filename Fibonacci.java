import java.io.*;
import java.util.Arrays;

//also try golden ration sqrt 5 method

public class Fibonacci {
	private static final double ROOT5= Math.sqrt(5);	//used in nthFibonacciPhi(), only calculated here ONCE
	private static final double PHI = (1+ROOT5)/2;

	private String outputFileName = "runtimes.txt";
	private PrintWriter outputFile;


	public static void main(String[] args) {
		Fibonacci fibonacciCalculator = new Fibonacci();

		int nthFibonacci=15;
		System.out.println(nthFibonacci+" Fibonacci numbers using array Memoization "+Arrays.toString( Fibonacci.fibonacciMemoizationStoreValues(nthFibonacci) ));
		System.out.println(nthFibonacci+" Fibonacci numbers Recursively "+Arrays.toString( Fibonacci.fibonacciRecursiveStoreValues(nthFibonacci) ));
		System.out.println(nthFibonacci+" Fibonacci numbers using Phi "+Arrays.toString( Fibonacci.fibonacciPhiStoreValues(nthFibonacci) ));
		System.out.println();

		int nthFibonacciIndex=nthFibonacci-1;	//adjust for array positions indexing from 0
		System.out.println("Indexes start from 0. The 0th number in the array is the 0th fibonacci number");
		System.out.println("Memoized: the Fibonacci number in position "+nthFibonacciIndex+" is: "+Fibonacci.nthFibonacciMemoization(nthFibonacciIndex));
		System.out.println("Recursively: the Fibonacci number in position "+nthFibonacciIndex+" is: "+ Fibonacci.nthFibonacciRecursive(nthFibonacciIndex) );		// since recursive calculates 1 more than Memoized version
		System.out.println("PHI: the Fibonacci number in position "+nthFibonacciIndex+" is: "+Fibonacci.nthFibonacciPhi(nthFibonacciIndex));
		System.out.println("\n");

		// fibonacciCalculator.printStringToFile( callMethod("fibonacciRecursiveStoreValues", 10, 30) );		//save results to file
		System.out.println();
		// fibonacciCalculator.printStringToFile( callMethod("fibonacciMemoizationStoreValues", 10, 30) );
		System.out.println();
		fibonacciCalculator.printStringToFile( callMethod("fibonacciPhiStoreValues", 10, 30) );

		fibonacciCalculator.closeFile();
	}
	
	public Fibonacci(){
		try {
			outputFile = new PrintWriter(new File(outputFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't create file: \""+outputFileName+"\"");
		}
	}

	public void printStringToFile(String string){
		outputFile.println(string);
	}
	
	public void closeFile(){
		outputFile.close();
	}
	
	public static long[] fibonacciMemoizationStoreValues(int n){
		long[] fibonacciSequence = new long[n];	//array & sequence start @ 0, so needs an array 1 bigger to account for 0th position. This way all methods for calculating return the same result
		fibonacciSequence[0]=0;
		fibonacciSequence[1]=1;
		
		for(int i=2; i<fibonacciSequence.length; i++){
			fibonacciSequence[i]=fibonacciSequence[i-1] + fibonacciSequence[i-2];
		}
		return fibonacciSequence;
	}

	public static long nthFibonacciMemoization(int n){
		return fibonacciMemoizationStoreValues(n+1)[n];		//must generate (n+1) since arrays indexed from 0
	}
	
	//use recursive function & store values
	public static long[] fibonacciRecursiveStoreValues(int n){
		long[] fibonacciSequence = new long[n];
		
		for(int i=0; i<fibonacciSequence.length; i++){
			fibonacciSequence[i] = Fibonacci.nthFibonacciRecursive(i);
		}
		return fibonacciSequence;
	}
	
	//Calculates Fibonacci number recursively
	public static int nthFibonacciRecursive(int n){
		if(n <2){		//base cases for n=0 or n=1
			return n;
		}
		return nthFibonacciRecursive(n-1) + nthFibonacciRecursive(n-2);	//recursive cases
	}

	public static long[] fibonacciPhiStoreValues(int n){
		long[] fibonacciSequence = new long[n];
		
		for(int i=0; i<fibonacciSequence.length; i++){
			fibonacciSequence[i] = Fibonacci.nthFibonacciPhi(i);
		}
		return fibonacciSequence;
	}

	//Calculates Fibonacci number using PHI (Golden ratio)
	public static int nthFibonacciPhi(int n){
		double fibonacciNumberDouble = (1/ROOT5)*Math.pow(PHI, n);	//use PHI formula
		return (int)(Math.round(fibonacciNumberDouble));		//ROUND to nearest integer
	}


	//Call a method X numberOfCalls, & pass n=how many fibonacci numbers to calculate
	public static String callMethod(String methodName, int numberOfCalls, int n){
		System.out.println(methodName+": finding 1st "+n+" Fibonacci numbers "+numberOfCalls+" times");
		String message=methodName+": finding 1st "+n+" Fibonacci numbers "+numberOfCalls+" times" +"\n";
		
		long total =0;
		for(int i=0; i<=numberOfCalls; i++){	//0 & <= to ignore 0th iteration
			long start=System.nanoTime();

			//switch to see which method to call
			switch(methodName){
				case "fibonacciMemoizationStoreValues":
					Fibonacci.fibonacciMemoizationStoreValues(n);
					break;
				case "fibonacciRecursiveStoreValues":
					Fibonacci.fibonacciRecursiveStoreValues(n);
					break;
				case "fibonacciPhiStoreValues":
					Fibonacci.nthFibonacciPhi(n);
					break;
				default:
					System.out.println("Unknown method name");
					return "Unknown method name";
			}

			if(i > 0){		//ignore i==0
				long duration = System.nanoTime() - start;
				total+=duration;
				System.out.print(i+": \tTime=\t" + duration +"\tnanoSeconds \t(" + getSeconds(duration) +" seconds)" +"\n");
				message += i+": \tTime=\t" + duration +"\tnanoSeconds \t(" + getSeconds(duration) +" seconds)" +"\n";
			}
		}
		long average=total/numberOfCalls;
		System.out.println("Average of "+numberOfCalls+" runs=\t"+ average +"\tnanoSeconds \t(" + getSeconds(average) +" seconds)" +"\n");
		message+="Average of "+numberOfCalls+" runs=\t"+ average +"\tnanoSeconds \t(" + getSeconds(average) +" seconds)" +"\n";
		return message;
	}

	public static String callMethodCalc1Fibonacci(String methodName, int numberOfCalls, int n){

		return "";
	}
	
	public static double getSeconds(long nanoSeconds){
		return nanoSeconds / 1000000000.0;	//1 nanoSecond = 10^(-9) seconds
	}

}