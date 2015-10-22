import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class DP229EDottieNumber {
    private static final double EPSILON = 0.00001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        double result = c/360 * Math.PI;
        while ( !(compare(result, result = Math.cos(result))) ){}
        Formatter formatter = new Formatter(System.out, Locale.US);
        formatter.format("%36.2f\n", result);
    }

    public static boolean compare(double a, double b){
        return a == b ? true : Math.abs(a-b) < EPSILON;
    }
}
