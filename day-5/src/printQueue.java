import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class printQueue {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day-5/src/input.txt"));

        Map<Integer, List<Integer>> pageOrderingRules = parsePageOrderingRules(scanner);
        List<List<Integer>> pageUpdates = parsePageUpdates(scanner);

        List<List<Integer>> correctOrderUpdates = filterCorrectOrderUpdates(pageUpdates, pageOrderingRules);

        int middleUpdateTotal = calculateMiddleUpdateTotal(correctOrderUpdates);

        System.out.println(middleUpdateTotal);
    }

    private static Map<Integer, List<Integer>> parsePageOrderingRules(Scanner scanner) {
        Map<Integer, List<Integer>> pageOrderingRules = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            String[] parts = line.split("\\|");
            int page1 = Integer.parseInt(parts[0]);
            int page2 = Integer.parseInt(parts[1]);
            pageOrderingRules.computeIfAbsent(page1, _ -> new ArrayList<>()).add(page2);
        }
        return pageOrderingRules;
    }

    private static List<List<Integer>> parsePageUpdates(Scanner scanner) {
        List<List<Integer>> pageUpdates = new ArrayList<>();
        while (scanner.hasNextLine()) {
            pageUpdates.add(new ArrayList<>(Arrays.stream(scanner.nextLine().split(","))
                    .map(Integer::parseInt)
                    .toList()));
        }
        return pageUpdates;
    }

    private static List<List<Integer>> filterCorrectOrderUpdates(List<List<Integer>> pageUpdates, Map<Integer, List<Integer>> pageOrderingRules) {
        List<List<Integer>> correctOrderUpdates = new ArrayList<>();
        List<List<Integer>> incorrectOrderUpdates = new ArrayList<>();
        for (List<Integer> updates : pageUpdates) {
            boolean correct = true;
            for (int i = 0; i < updates.size(); i++) {
                for (int j = i + 1; j < updates.size(); j++) {
                    if (pageOrderingRules.getOrDefault(updates.get(j), Collections.emptyList()).contains(updates.get(i))) {
                        correct = false;
                        break;
                    }
                }
                if (!correct) break;
            }
            if (correct) {
                correctOrderUpdates.add(updates);
            } else {
                incorrectOrderUpdates.add(updates);
            }
        }
        correctlyOrderIncorrectlyOrderedUpdatesAndAdd(incorrectOrderUpdates, pageOrderingRules);
        return correctOrderUpdates;
    }

    private static int correctlyOrderIncorrectlyOrderedUpdatesAndAdd(List<List<Integer>> incorrectOrderUpdates, Map<Integer, List<Integer>> pageOrderingRules) {
        List<List<Integer>> fixedOrderUpdates = new ArrayList<>();
        for (List<Integer> updates : incorrectOrderUpdates) {
            for (int i = 0; i < updates.size(); i++) {
                for (int j = i + 1; j < updates.size(); j++) {
                    if (pageOrderingRules.getOrDefault(updates.get(j), Collections.emptyList()).contains(updates.get(i))) {
                        int current = updates.get(i);
                        int next = updates.get(j);
                        updates.set(i, next);
                        updates.set(j, current);
                    }
                }
            }
            fixedOrderUpdates.add(new ArrayList<>(updates)); // Add the fixed updates to the list
        }
        return calculateMiddleUpdateTotal(fixedOrderUpdates);
    }

    private static int calculateMiddleUpdateTotal(List<List<Integer>> correctOrderUpdates) {
        return correctOrderUpdates.stream()
                .mapToInt(updates -> updates.get(updates.size() / 2))
                .sum();
    }
}