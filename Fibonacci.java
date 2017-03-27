import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

//also try golden ration sqrt 5 method

public class Fibonacci {
	private String outputFileName = "runtimes.txt";
	private PrintWriter outputFile;

	public static void main(String[] args) {
		Fibonacci fibonacciCalculator = new Fibonacci();

		int nthFibonacci=40;
		System.out.println(nthFibonacci+" Fibonacci numbers efficiently "+Arrays.toString( Fibonacci.fibonacciMemoization(nthFibonacci) ));
		System.out.println("Recursively: the Fibonacci number in position "+nthFibonacci+" is: "+ Fibonacci.nthFibonacciRecursive(nthFibonacci-1) );		//-1 since recursive calculates 1 more than Memoizaed version
		System.out.println("15 Fibonacci numbers recursively "+Arrays.toString( Fibonacci.nthFibonacciRecursiveStoreValues(14) ));


		// fibonacciCalculator.printStringToFile( callMethod("nthFibonacciRecursiveStoreValues",10, 30) );		//save results to file
		fibonacciCalculator.printStringToFile( callMethod("fibonacciMemoization",10, 30) );		//save results to file
		
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
	
	public static long[] fibonacciMemoization(int n){
		long[] fibonacciSequence = new long[n];
		fibonacciSequence[0]=0;
		fibonacciSequence[1]=1;
		
		for(int i=2; i<fibonacciSequence.length; i++){
			fibonacciSequence[i]=fibonacciSequence[i-1] + fibonacciSequence[i-2];
		}
		return fibonacciSequence;
	}
	
	//use recursive function & store values
	public static long[] nthFibonacciRecursiveStoreValues(int n){
		long[] fibonacciSequence = new long[n];
		
		for(int i=0; i<fibonacciSequence.length; i++){
			fibonacciSequence[i] = Fibonacci.nthFibonacciRecursive(i);
		}

		return fibonacciSequence;
	}
	
	//Calculates Fibonacci number recursively
	//Since it the base case starts @ 0, if you want the 10th fibonacci number, call nthFibonacciRecursive(9) to offset
	public static int nthFibonacciRecursive(int n){
		if(n <2){		//base cases for n=0 or n=1
			return n;
		}
		return nthFibonacciRecursive(n-1) + nthFibonacciRecursive(n-2);	//recursive cases
	}

	//throw away 1st iteration
	public static String callMethod(String methodName, int times, int n){
		System.out.println(methodName+": finding 1st "+n+" Fibonacci numbers "+times+" times");
		String message=methodName+": finding 1st "+n+" Fibonacci numbers "+times+" times" +"\n";
		
		long total =0;
		for(int i=0; i<=times; i++){	//0 & <= to ignore 0th iteration
			long start=System.nanoTime();

			//switch to see which method to call
			switch(methodName){
				case "fibonacciMemoization":
					// System.out.println("fibonacciMemoization");		//The method call is the time consuming operation
					fibonacciMemoization(n);
					break;
				case "nthFibonacciRecursiveStoreValues":
					// System.out.println("nthFibonacciRecursiveStoreValues");	//The method call is the time consuming operation
					nthFibonacciRecursiveStoreValues(n);
					break;
				default:
					System.out.println("Unknown method name");
					break;
			}

			if(i > 0){		//ignore i==0
				long duration = System.nanoTime() - start;
				total+=duration;
				System.out.print(i+": \tTime=\t" + duration +"\tnanoSeconds \t(" + getSeconds(duration) +" seconds)" +"\n");
				message += i+": \tTime=\t" + duration +"\tnanoSeconds \t(" + getSeconds(duration) +" seconds)" +"\n";
			}
		}
		long average=total/times;
		System.out.println("Average of "+times+" runs=\t"+ average +"\tnanoSeconds \t(" + getSeconds(average) +" seconds)" +"\n");
		message+="Average of "+times+" runs=\t"+ average +"\tnanoSeconds \t(" + getSeconds(average) +" seconds)" +"\n";
		return message;
	}
	
	public static double getSeconds(long nanoSeconds){
		return nanoSeconds / 1000000000.0;	//1 nanoSecond = 10^(-9) seconds
	}

}