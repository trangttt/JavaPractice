public class DP223IEelOfFortune {
    public static void main(String[] args) {
        String s1 = args[0];
        String s2 = args[1];
        String s3 = s1.replaceAll("[^"+ s2 + "]", "");
        System.out.println(s3.toLowerCase().equals(s2.toLowerCase()));
    }
}
