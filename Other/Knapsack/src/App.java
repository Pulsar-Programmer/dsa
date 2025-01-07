public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public static double knapsack(double[] values, double[] weights, double cap, int i, double max){
        if(i==values.length)
            return max;
        double with = 0;
        if(weights[i] <= cap){
            with = knapsack(values, weights, cap-weights[i], i+1, max+values[i]);
        }
        double without = knapsack(values, weights, cap, i+1, max);
        return Math.max(with, without);
    }
}
