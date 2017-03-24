import java.util.Arrays;

public class Fibonacci {

	public static void main(String[] args) {
		//fibonacciRecursive();
		System.out.println(Arrays.toString( Fibonacci.fibonacciMemoization(10) ));
		System.out.println(( Fibonacci.fibonacciRecursive(10) ));
		System.out.println(Arrays.toString( Fibonacci.fibonacciRecursiveStoreValues(10) ));
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
	
	public static long[] fibonacciRecursiveStoreValues(int n){
		long[] fibonacciSequence = new long[n];
		
		for(int i=0; i<fibonacciSequence.length; i++){
			fibonacciSequence[i] = Fibonacci.fibonacciRecursive(i);
		}
		
		return fibonacciSequence;
	}
	
	public static int fibonacciRecursive(int n){
		if(n <2){
			return n;
		}
		return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
	}

}