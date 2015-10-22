import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class De {
    public static String columnize(String txtPath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(txtPath));
        String text = "";
        for (String line : lines) {

            line = line.replaceAll("(\\+)(-*)(\\+)", " ")
                        .replaceAll("\\|", " ")
                        .replaceAll("(  )(.*)(  )", "$1$3")
                        .trim();

            if (line.length() == 0) {
                text += "\n";
                continue;
            }

            if (line.charAt(line.length() - 1) == '-') {
                line = line.substring(0, line.length() - 1);
            } else {
                line += " ";
            }
            text += line;
        }

        return text;
    }
}

class Main {
    public static void main(String args[]) throws IOException {
        System.out.println(De.columnize("225E_input2.txt"));
    }
}
