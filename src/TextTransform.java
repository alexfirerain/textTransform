import java.util.Scanner;

import static java.lang.Character.isWhitespace;

public class TextTransform {

    private static Scanner input;

    private static String textTransform(String line) {
        StringBuilder processed = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            Character cur = line.charAt(i);
            processed.append(cur);
            while (isWhitespace(cur) && i < line.length() - 1) {
                Character next = line.charAt(++i);
                if (!isWhitespace(next))
                    processed.append(next);
            }
        }

        line = processed.toString();
        processed = new StringBuilder();

        StringBuilder processedB = new StringBuilder();
        line.chars().forEach(
                c -> processedB.append(c == '+' ? '!' : c)
        );
        line = processedB.toString();


        for (int i = 0; i < line.length(); i++) {
            Character cur = line.charAt(i);
            processed.append(cur.equals('+') ? '!' : cur);
        }


        return processed.toString();
    }

    public static void main(String[] args) {
        while (true) {
            String line = input.nextLine();
            if (line.isBlank()) break;
            else System.out.println(textTransform(line));
        }
    }
}