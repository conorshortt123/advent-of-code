import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class guardGallivant {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day-6/src/input.txt"));
        List<List<String>> map = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            map.add(Arrays.asList(line.split("")));
        }
        List<List<String>> visitedMap = traverseMap(map, findGuard(map));
        System.out.println(countVisited(visitedMap));
    }

    private static void printMap(List<List<String>> map) {
        for(List<String> row : map) {
            System.out.println(row.toString());
        }
    }

    private static int[] findGuard(List<List<String>> map) {
        for (int x = 0; x < map.size(); x++) {
            if(map.get(x).contains("^")) {
                return new int[]{x, map.get(x).indexOf("^")};
            }
        }
        throw new RuntimeException("Couldn't find guard");
    }

    private static List<List<String>> traverseMap(List<List<String>> map, int[] initialGuardLocation) {
        int x = initialGuardLocation[0];
        int y = initialGuardLocation[1];

        while (x > 0 && x < map.size()-1 && y > 0 && y < map.getFirst().size()-1) {
            switch (map.get(x).get(y)) {
                case "^" -> {
                    map.get(x).set(y, "X");
                    if (map.get(x - 1).get(y).equals("#")) {
                        y++;
                        map.get(x).set(y, ">");
                    } else {
                        x--;
                        map.get(x).set(y, "^");
                    }
                }
                case ">" -> {
                    map.get(x).set(y, "X");
                    if (map.get(x).get(y + 1).equals("#")) {
                        x++;
                        map.get(x).set(y, "v");
                    } else {
                        y++;
                        map.get(x).set(y, ">");
                    }
                }
                case "<" -> {
                    map.get(x).set(y, "X");
                    if (map.get(x).get(y - 1).equals("#")) {
                        x--;
                        map.get(x).set(y, "^");
                    } else {
                        y--;
                        map.get(x).set(y, "<");
                    }
                }
                case "v" -> {
                    map.get(x).set(y, "X");
                    if (map.get(x + 1).get(y).equals("#")) {
                        y--;
                        map.get(x).set(y, "<");
                    } else {
                        x++;
                        map.get(x).set(y, "v");
                    }
                }
            }
        }
        return map;
    }

    private static int countVisited(List<List<String>> visitedMap) {
        long total = 0;
        for(List<String> row : visitedMap) {
            total += row.stream().filter(s -> !s.equals(".") && !s.equals("#")).count();
        }

        return (int) total;
    }
}