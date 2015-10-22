import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;


public class DP237EBrokenKeyboard {
    private static Optional<String> find(String charset) throws IOException {
        try (Stream<String> dict = Files.lines(Paths.get("enable1.txt"))) {
            return dict
                .filter(word -> word.matches("[" + charset + "]+"))
                .max(Comparator.comparing(String::length));
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File(args[0]));
        sc.nextLine();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Optional<String> result = find(line);
            System.out.println(line + "=" + result.orElse("NO MATCH."));
        }
    }
}
