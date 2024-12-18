package aocjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class mullItOver {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("day-3/aocjava/input.txt");
        Scanner scanner = new Scanner(file);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        String line = String.join("", lines);

        System.out.println(partOne(line));
        System.out.println(partTwo(line));
    }

    private static Integer partOne(String line) {
        Pattern mulPattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern extractNums = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");

        return mulPattern.matcher(line).results()
                .map(match -> {
                    String[] nums = extractNums.matcher(match.group()).results().findFirst().get().group().split(",");
                    return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                }).reduce(0, Integer::sum);
    }

    private static Integer partTwo(String line) {
        Pattern mulPattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do(n't)?\\(\\)");
        Pattern extractNums = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");
        AtomicBoolean enabled = new AtomicBoolean(true);

        return mulPattern.matcher(line).results()
                .map(match -> {
                    String matchString = match.group();
                    if (matchString.equals("do()")) {
                        enabled.set(true);
                    } else if (matchString.equals("don't()")) {
                        enabled.set(false);
                    } else {
                        String[] actions = extractNums.matcher(matchString).results()
                                .findFirst().get().group().split(",");
                        if (enabled.get()) {
                            return Integer.parseInt(actions[0]) * Integer.parseInt(actions[1]);
                        }
                    }
                    return 0;
                }).reduce(0, Integer::sum);
    }
}
