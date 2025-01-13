import java.util.ArrayList;
import java.util.HashMap;

public class App {



    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        System.out.println(primeN(6));
        System.out.println(longestCollatz(2));

        
    }
    



    public static long primeN(int n){
        return primeHelp(n, 2, 1);
    }

    public static HashMap<Integer, Long> memo_primes = new HashMap();
    public static long primeHelp(int n, long i, int c){
        ///We find the placement of the next prime.
        int new_c = c;
        if(isPrime(i)){
            memo_primes.put(c, i);
            new_c++;
            ///We return if we found the desired prime.
            if(n == c){
                return i;
            }
        }
        ///We go to the next index to check for a prime.
        return primeHelp(n, i+1, new_c);
    }
    ///Checks whether a value is a prime or not.
    public static boolean isPrime(long n){
        if(n == 2){
            return true;
        }
        for(long prime : memo_primes.values()){
            if (n % prime == 0){
                return false;
            }
        }
        return true;
    }






    public static int longestCollatz(int n){
        return longestCollatzHelp(n, 0, 0);
    }
    public static HashMap<Integer, Integer> memo_collatz_2 = new HashMap<>();
    ///Goes through all of the chains to help.
    public static int longestCollatzHelp(int n, int i, int max){
        if (i >= n){
            return max;
        }
        var i_th = longestCollatzHelper(i);
        var new_max = Math.max(max, i_th);

        return longestCollatzHelp(n, i+1, new_max);
    }
    public static HashMap<Integer, Integer> memo_collatz = new HashMap<>();
    public static int longestCollatzHelper(int n){
        if(n == 1){
            return 1;
        }
        int new_n = n % 2 == 0 ? n / 2 : 3 * n + 1;
        Integer memo = memo_collatz.get(new_n);
        if(memo != null){
            return 1 + memo;
        }
        return 1 + longestCollatzHelper(new_n);
    }



}
