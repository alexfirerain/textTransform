import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public class TextTransform {

    private static final Scanner input = new Scanner(System.in);

    private static String textTransform(String line) {

        StringBuilder processedA = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            processedA.append(cur);
            while (isWhitespace(cur) && i < line.length() - 1) {
                char next = line.charAt(++i);
                if (!isWhitespace(next)) {
                    processedA.append(next);
                    break;
                }
            }
        }
        line = processedA.toString();


        StringBuilder processedB = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            if (cur != '-') {
                processedB.append(cur);
            } else {
                while (i < line.length() - 1) {
                    char next = line.charAt(++i);
                    if (next != '-') {
                        processedB.insert(processedB.isEmpty() ? 0 : processedB.length() - 1, next);
                        break;
                    }
                }
            }
        }
        line = processedB.toString();


        StringBuilder processedC = new StringBuilder();
        line.chars().forEach(
                c -> processedC.append(c == '+' ? '!' : (char) c)
        );
        line = processedC.toString();


        StringBuilder processedD = new StringBuilder();
        List<Integer> digits = new ArrayList<>();
        line.chars().forEach(
            i -> {
                char c = (char) i;
                if (!isDigit(c))
                    processedD.append(c);
                else
                    digits.add(Integer.parseInt(String.valueOf(c)));
            }
        );
        if (!digits.isEmpty())
            processedD.append(" %s"
                    .formatted(digits.stream()
                            .mapToInt(Integer::intValue)
                            .sum()));
        line = processedD.toString();


        return line;
    }

    public static void main(String[] args) {
        while (true) {
            String line = input.nextLine();
            if (line.isBlank()) break;
            else System.out.println(textTransform(line));
        }
    }
}