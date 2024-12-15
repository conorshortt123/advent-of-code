import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class redNosedReports {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(runner());
    }

    private static int runner() throws FileNotFoundException {
        File file = new File("day-2/red-nosed-reports/java/input.txt");
        Scanner scanner = new Scanner(file);
        int safeReports = 0;

        while (scanner.hasNextLine()) {
            String report = scanner.nextLine();
            if (safeReportDampener(report))
                safeReports++;
        }

        return safeReports;
    }

    static boolean safeReportDampener(String report) {
        List<Integer> levelsList = Arrays.stream(report.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        if (safeReport(levelsList)) {
            return true;
        }

        for (int i = 0; i < levelsList.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(levelsList);
            modifiedList.remove(i);
            if (safeReport(modifiedList)) {
                return true;
            }
        }

        return false;
    }

    static boolean safeReport(List<Integer> levelsList) {
        if (levelsList.size() < 2) {
            return true;
        }

        boolean increasing = levelsList.getFirst() < levelsList.getLast();

        for (int i = 0; i < levelsList.size() - 1; i++) {
            int current = levelsList.get(i);
            int next = levelsList.get(i + 1);
            int diff = Math.abs(current - next);

            if (diff < 1 || diff > 3) {
                return false;
            }
            if (increasing && current > next) {
                return false;
            }
            if (!increasing && current < next) {
                return false;
            }
        }

        return true;
    }
}
