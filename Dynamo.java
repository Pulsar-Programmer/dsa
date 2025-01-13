import java.util.HashMap;

public class Dynamo {
	static HashMap<Integer, Long> memo = new HashMap<Integer,Long>();
	static HashMap<Integer, Integer> totals = new HashMap<Integer,Integer>();
	static int dumbDiceCount = 0;
	static int MCount = 0;
	static int knapDumb = 0, knapSmart = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fib(8));
		int[] denoms = {1,5,10,25};
		System.out.println(coinCount(denoms,10));
		System.out.println(diceCountD(8,24));
		System.out.println(dumbDiceCount);
		System.out.println("Memo "+ diceCountM(8,24));
		System.out.println(MCount);
		
		int[][] diceTable = new int[9][25];
		for(int i = 1; i<=6;i++)
			diceTable[1][i] = 1;
		for(int dice = 2; dice<9;dice++)
			for(int target = 1; target<25;target++) {
				for(int i = 1; i<=6;i++)
					if(target-i>=0)
						diceTable[dice][target] += diceTable[dice-1][target-i];
			}
		System.out.println(diceTable[8][24]);
		//8*24*6 is 
		
		System.out.println(newton(4,1000));
		double[] values = {100,200,50,300,400,25};
		double[] weights = {3,5,10,1,2,1};
		System.out.println(knapsack(values,weights,10,0,0));
		System.out.println(knapDumb);
		
		//System.out.println("Catalan");
		//System.out.println(catalan(5));
		System.out.println(knapSmart(values,weights,10,weights.length-1,0));
		System.out.println(knapSmart);
	}
	public static long catalan(int n) {
		long[] memo = new long[n+1];
		memo[0] = 1;
		return catalanH(n,memo);
	}
	public static long catalanH(int n, long[] memo) {
		if(n < 0)
			return 0;
		if(n==0||n==1)
			return 1;
		if(memo[n]!=0)
			return memo[n];
		long cat = 0;
		for(int i = 1;i<n;i++)
			cat += catalanH(i,memo)*catalanH(n-i,memo);
		memo[n] = cat;
		return cat;
	}
	
	
	public static double knapSmart(double[] values, double[] weights, double cap,int i,double max) {
		double[][] memo = new double[weights.length][(int)(cap+1)];
		for(int row =0; row<memo.length;row++)
			for(int col = 0; col<memo[row].length;col++)
				memo[row][col] = -1;
		return knapsackSmartH(values,weights,cap,i,memo);
	}
	public static double knapsackSmartH(double[] values, double[] weights, double cap,int i, double[][] memo) {
		knapSmart++;
		
		if(i==-1)
			return 0;
		
		if(memo[i][(int)cap]>-.9)
			return memo[i][(int)cap];
		double with = 0;
		if(weights[i]<=cap)
			with =  values[i] + knapsackSmartH(values,weights,cap-weights[i],i-1,memo);
		double without = knapsackSmartH(values, weights,cap,i-1,memo);
		memo[i][(int)cap] = Math.max(with, without);
		printMemo(memo);
		return memo[i][(int)cap];
	}
	public static double knapsack(double[] values, double[] weights, double cap,int i,double max) {
		knapDumb++;
		if(i==values.length)
			return max;
		double with = 0;
		if(weights[i]<=cap)
			with =  knapsack(values,weights,cap-weights[i],i+1,max+values[i]);
		double without = knapsack(values, weights,cap,i+1, max);
		return Math.max(with, without);
	}
	
	public static double newton(double x, int trials) {
		if(trials==0)
			return Double.POSITIVE_INFINITY;
		if(Math.abs(function(x))<.00001)
				return x;
		return newton(x-function(x)/derivative(x),trials-1);
	}
	private static double function(double x) {
        return Math.sin(x);
    }

    private static double derivative(double x) {
        return Math.cos(x);
    }
	public static int diceCountM(int dice, int target) {
		int[][] memo = new int[dice+1][target+1];
		return diceCountMH(dice,target,memo);
		
	}
	public static int diceCountMH(int dice, int target, int[][] memo) {
		MCount++;
		if(dice==0&&target==0)
			return 1;
		if(dice<=0||target<=0)
			return 0;
		if(memo[dice][target]!=0)
			return memo[dice][target];
		int count = 0;
		for(int i = 1; i<=6;i++) 
			count+= diceCountMH(dice-1,target-i,memo);
		memo[dice][target]= count;
		return count;
	}
	
	public static int diceCountD(int dice, int target) {
		dumbDiceCount++;
		if(dice==0&&target==0)
			return 1;
		if(dice<=0||target<=0)
			return 0;
		int count = 0;
		for(int i = 1; i<=6;i++) 
			count+= diceCountD(dice-1,target-i);
		return count;
	}
	
	public static long fib(int n) {
		if(n<=2)
			return 1;
		if(memo.containsKey(n))
			return memo.get(n);
		memo.put(n, fib(n-1)+fib(n-2));
		System.out.println(n);
		return memo.get(n);
	}
	public static long fibBad(int n) {
		if(n<=2)
			return 1;
		System.out.println(n);
		return  fibBad(n-1)+fibBad(n-2);
		
	}
	
	public static int coinCount(int[] denom, int total) {
		if(total<0)
			return Integer.MAX_VALUE;
		if(total==0)
			return 0;
		if(totals.containsKey(total))
			return totals.get(total);
		
		int count = Integer.MAX_VALUE;
		for(int coin:denom) {
			count = Math.min(count, coinCount(denom,total-coin)); 
		}
		count++;
		totals.put(total, count);
		return count;
	}
	public static void printMemo(double[][] memo) {
		System.out.println("----start----------");
		for(int i = 0; i<memo.length;i++) {
			for(int j = 0; j<memo[i].length;j++) {
				System.out.print("|"+memo[i][j]);
			}
			System.out.println();
		}
		System.out.println("----end----------");
	}
	

}
