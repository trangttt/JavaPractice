import java.util.Scanner;
import java.util.stream.IntStream;

public class DP232EPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noLine = sc.nextInt();
        sc.nextLine();
        StringBuilder text = new StringBuilder("");
        for(int i=0; i<noLine; i++)
            text.append(sc.nextLine());
        String p = text.toString().toLowerCase().replaceAll("[^a-z]", "");
        int len;
        if ( IntStream.range(0, (len = p.length())/2)
            .filter(i -> p.charAt(i) == p.charAt(len - 1 - i))
            .count() == len/2)
            System.out.println("Palindrome");
        else 
            System.out.println("NONE");
    }
}
