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
		System.out.println("Recursively: the Fibonacci number in position "+nthFibonacci+" is: "+ Fibonacci.fibonacciRecursive(nthFibonacci-1) );		//-1 since recursive calculates 1 more than Memoizaed version
		System.out.println(Arrays.toString( Fibonacci.fibonacciRecursiveStoreValues(10) ));


		fibonacciCalculator.printStringToFile( callFibonacciRecursiveStoreValues(10, 20) );		//save results to file
		
		
		fibonacciCalculator.closeFile();
	}
	
	public Fibonacci(){
		try {
			outputFile = new PrintWriter(new File(outputFileName));
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't create "+outputFileName);
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
	public static long[] fibonacciRecursiveStoreValues(int n){
		long[] fibonacciSequence = new long[n];
		
		for(int i=0; i<fibonacciSequence.length; i++){
			fibonacciSequence[i] = Fibonacci.fibonacciRecursive(i);
		}

		return fibonacciSequence;
	}
	
	public static int fibonacciRecursive(int n){
		if(n <2){		//base cases for n=0 or n=1
			return n;
		}
		return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);	//recursive cases
	}

	//throw away 1st iteration
	public static String callFibonacciRecursiveStoreValues(int times, int n){
		System.out.println("Recursive: finding 1st "+n+" Fibonacci number "+times+" times");
		String message="Recursive: finding 1st "+n+" Fibonacci number "+times+" times" +"\n";
		long total =0;
		for(int i=0; i<=times; i++){	//0 & <= to ignore 0th iteration
			long start=System.nanoTime();
			fibonacciRecursiveStoreValues(n);		//the time consuming operation
			if(i > 0){		//ignore i==0
				long duration = System.nanoTime() - start;
				total+=duration;
				System.out.print(i+": \tTime=\t" + duration +"\tnanoSeconds" +"\n");
				message += i+": \tTime=\t" + duration +"\tnanoSeconds" +"\n";
			}
		}
		System.out.println("Average of "+times+" runs=\t"+ (total/times) +"\tnanoSeconds" +"\n");
		message+="Average of "+times+" runs=\t"+ (total/times) +"\tnanoSeconds" +"\n";
		return message;
	}
	
	public static double getSeconds(long nanoSeconds){
		return 0;
	}

}