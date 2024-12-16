package aocjava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class mullItOver {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("day-3/aocjava/input.txt");
        Scanner scanner = new Scanner(file);
        int total = 0;

        while(scanner.hasNextLine()) {
            total += partOne(scanner.nextLine());
        }

        System.out.println(total);
    }

    private static int partOne(String line) {
        Pattern mulPattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern extractNums = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");

        Optional<Integer> sum = mulPattern.matcher(line).results()
                .map(match -> {
                    String[] nums = extractNums.matcher(match.group()).results().findFirst().get().group().split(",");
                    return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                }).reduce(Integer::sum);

        return sum.get();
    }
}
