package aocjava.p2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ceresSearch {

    public static void main(String[] args) throws FileNotFoundException {
        int masCount = 0;
        Scanner scanner = new Scanner(new File("day-4/aocjava/input.txt"));

        List<List<String>> wordSearch = new ArrayList<>();
        while (scanner.hasNextLine()) {
            wordSearch.add(Arrays.stream(scanner.nextLine().split("")).toList());
        }

        for (int x = 1; x < wordSearch.size() - 1; x++) {
            for (int y = 1; y < wordSearch.get(x).size() - 1; y++) {
                if (wordSearch.get(x).get(y).equals("A")) {
                    masCount += masCrossSearch(x, y, wordSearch);
                }
            }
        }

        System.out.println("x-MAS count: " + masCount);
    }

//    M.S
//    .A.
//    M.S
    public static int masCrossSearch(int x, int y, List<List<String>> graph) {
        System.out.println(x + " " + y);
        Map<Direction, int[]> neighbours = getNeighbours(x, y, graph.size(), graph.get(x).size());

        String topRightLetter = graph.get(neighbours.get(Direction.TOP_RIGHT)[0]).get(neighbours.get(Direction.TOP_RIGHT)[1]);
        String bottomRightLetter = graph.get(neighbours.get(Direction.BOTTOM_RIGHT)[0]).get(neighbours.get(Direction.BOTTOM_RIGHT)[1]);
        String topLeftLetter = graph.get(neighbours.get(Direction.TOP_LEFT)[0]).get(neighbours.get(Direction.TOP_LEFT)[1]);
        String bottomLeftLetter = graph.get(neighbours.get(Direction.BOTTOM_LEFT)[0]).get(neighbours.get(Direction.BOTTOM_LEFT)[1]);

        String diag1 = String.join("", topRightLetter, bottomLeftLetter);
        String diag2 = String.join("", bottomRightLetter, topLeftLetter);

        if(((diag1.equals("SM") || diag1.equals("MS"))) && (diag2.equals("SM") || diag2.equals("MS"))) {
            return 1;
        }
        return 0;
    }

    public static Map<Direction, int[]> getNeighbours(int x, int y, int width, int height) {
        Map<Direction, int[]> neighbours = new HashMap<>();

        // Top left neighbour
        if (x > 0 && y > 0) {
            neighbours.put(Direction.TOP_LEFT, new int[]{x - 1, y - 1});
        }
        // Top right neighbour
        if (x < width - 1 && y > 0) {
            neighbours.put(Direction.TOP_RIGHT, new int[]{x + 1, y - 1});
        }
        // Bottom left neighbour
        if (x > 0 && y < height - 1) {
            neighbours.put(Direction.BOTTOM_LEFT, new int[]{x - 1, y + 1});
        }
        // Bottom right neighbour
        if (x < width - 1 && y < height - 1) {
            neighbours.put(Direction.BOTTOM_RIGHT, new int[]{x + 1, y + 1});
        }
        return neighbours;
    }

    public enum Direction {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT;
    }
}
