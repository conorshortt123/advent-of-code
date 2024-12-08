import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class redNosedReports {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(partOne());
    }

    private static int partOne() throws FileNotFoundException {
        File file = new File("day-2/red-nosed-reports/java/input.txt");
        Scanner scanner = new Scanner(file);
        int safeReports = 0;

        while(scanner.hasNextLine()) {
            String report = scanner.nextLine();
            if(safeReport(report)) safeReports++;
        }

        return safeReports;
    }

    private static boolean safeReport(String report) {
        List<Integer> levelsList = Arrays.stream(report.split(" "))
                .map(Integer::parseInt)
                .toList();

        // Check levels are either increasing or decreasing by at least 1-3
        boolean increasing = levelsList.getFirst() < levelsList.getLast();
        for (int i = 0; i < levelsList.size() - 1; i++) {
            int current = levelsList.get(i);
            int next = levelsList.get(i+1);
            int diff = Math.abs(current - next);

            if(diff < 1 || diff > 3) return false;
            if(increasing && current > next) return false;
            if(!increasing && current < next) return false;
        }
        return true;
    }
}
