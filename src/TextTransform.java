import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public class TextTransform {

    private static final Scanner input = new Scanner(System.in);


    /*
        В описании задания ничего не сказано, можно ли добавлять методы.
        Так что пусть будут функциональные интерфейсы:
        не так уж часто удаётся ввинтить их в программу!
     */

    private static final Function<String, String> a = line -> {
        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            processed.append(cur);
            while (isWhitespace(cur) && i < line.length() - 1) {
                char next = line.charAt(++i);
                if (!isWhitespace(next)) {
                    processed.append(next);
                    break;
                }
            }
        }
        return processed.toString();
    };

    private static final Function<String, String> b = line -> {
        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            if (cur != '-') {
                processed.append(cur);
            } else {
                while (i < line.length() - 1) {
                    char next = line.charAt(++i);
                    if (next != '-') {
                        processed.insert(processed.isEmpty() ? 0 : processed.length() - 1, next);
                        break;
                    }
                }
            }
        }
        return processed.toString();
    };

    private static final Function<String, String> c = line -> {
        StringBuilder processed = new StringBuilder();
        line.chars().forEach(
                c -> processed.append(c == '+' ? '!' : (char) c)
        );
        return processed.toString();
    };

    private static final Function<String, String> d = line -> {
        StringBuilder processed = new StringBuilder();
        List<Integer> digits = new ArrayList<>();
        line.chars().forEach(
                i -> {
                    char c = (char) i;
                    if (!isDigit(c))
                        processed.append(c);
                    else
                        digits.add(Integer.parseInt(String.valueOf(c)));
                }
        );
        if (!digits.isEmpty())
            processed.append(" %s"
                    .formatted(digits.stream()
                            .mapToInt(Integer::intValue)
                            .sum()));
        return processed.toString();

    };

    /*
        В описании задания сигнатура метода указана без аргументов.
        Тогда это значит, что принимать строку надо внутри этого метода.
        Это будет как-то некрасиво, но если надо, тогда добавим ему в тело
        ```
            line = input.nextLine();
        ```
        а цикл в сценарии тогда будет иметь вид
        ```
            String s = textModifier();
            if (s.isBlank()) break;
            else System.out.println(s);
        ```
        .
     */
    public static String textModifier(String line) {

        List<Function<String, String>> executor = List.of(a, b, c, d);

        for (Function<String, String> f : executor)
            line = f.apply(line);

        return line;
    }

    public static void main(String[] args) {
        while (true) {
            String line = input.nextLine();
            if (line.isBlank()) break;
            else System.out.println(textModifier(line));
        }
    }
}