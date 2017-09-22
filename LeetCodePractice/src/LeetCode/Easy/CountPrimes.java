package LeetCode.Easy;

public class CountPrimes {
	
	// Time Limit Exceeded 
    public int countPrimes(int n) {
	   int count = 0;
	   for (int i = 1; i < n; i++) {
	      if (isPrime(i)) count++;
	   }
	   return count;
    }
    
    private boolean isPrime(int num) {
	   if (num <= 1) return false;
	   // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
	   // to avoid repeatedly calling an expensive function sqrt().
	   for (int i = 2; i * i <= num; i++) {
	      if (num % i == 0) return false;
	   }
	   return true;
    }
    
    public int countPrimes_better(int n) {
		boolean notPrime[] = new boolean[n];

		int count = 0;
		for (int i=2; i<n; i++) {

			if (!notPrime[i]) {
				count++;

				for (int j=2; i*j < n; j++) {
					notPrime[i*j] = true;
				}
			}
		}

		return count++;
	}
    
    public static void main(String[] args) {
    	CountPrimes solution = new CountPrimes();
    	
    	System.out.println(solution.countPrimes_better(20));
    }
}
