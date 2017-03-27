# Fibonacci Algorithms Runtime Comparison
Comparison between runtimes of a **recursive** implementation & a **memoized array** approach storing calculated values in an array to avoid recalculation.

F[0]=0  
F[1]=1  
F[i]=F[i-1]+F[i-2]

## Runtimes
Runtime output is printed to the screen as well as saved in a file. [View `runtimes.txt` for my sample runtimes](runtimes.txt)

## Warnings
- `nthFibonacciRecursiveStoreValues()` take a significant amount of time for **n>40**

## References
- [PHI Golden Ratio Fibonacci Formula](http://www.askamathematician.com/2011/04/q-is-there-a-formula-to-find-the-nth-term-in-the-fibonacci-sequence/)
