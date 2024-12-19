package aocjava.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ceresSearch {
    public static int xmasCount = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("day-4/aocjava/input.txt"));

        List<List<String>> wordSearch = new ArrayList<>();
        while (scanner.hasNextLine()) {
            wordSearch.add(Arrays.stream(scanner.nextLine().split("")).toList());
        }

        for (int x = 0; x < wordSearch.size(); x++) {
            for (int y = 0; y < wordSearch.get(x).size(); y++) {
                if(wordSearch.get(x).get(y).equals("X")) {
                    xmasSearch(x, y, wordSearch, Direction.ANY);
                }
            }
        }

        System.out.println("XMAS count: " + xmasCount);
    }

    public static void xmasSearch(int x, int y, List<List<String>> graph, Direction direction) {
        String currentLetter = graph.get(x).get(y);

        if(currentLetter.equals("S")) {
            xmasCount++;
            return;
        }

        List<int[]> neighbours = getNeighbours(x, y, graph.size(), graph.get(x).size(), direction);
        String nextLetter = getNextLetter(currentLetter);
        List<int[]> nextNeighbours = new ArrayList<>();

        for(int[] neighbour: neighbours) {
            String neighbourLetter = graph.get(neighbour[0]).get(neighbour[1]);
            if(nextLetter.equals(neighbourLetter)) {
                nextNeighbours.add(neighbour);
            }
        }

        for(int[] neighbour : nextNeighbours) {
            Direction newDirection = direction.getDirection(neighbour, new int[]{x, y});
            xmasSearch(neighbour[0], neighbour[1], graph, newDirection);
        }
    }

    public static List<int[]> getNeighbours(int x, int y, int width, int height, Direction direction) {
        List<int[]> neighbours = new ArrayList<>();
        // Left neighbour
        if (x > 0) {
            if(direction == Direction.LEFT || direction == Direction.ANY) {
                neighbours.add(new int[]{x - 1, y});
            }
        }
        // Right neighbour
        if (x < width - 1) {
            if(direction == Direction.RIGHT || direction == Direction.ANY) {
                neighbours.add(new int[]{x + 1, y});
            }
        }
        // Top neighbour
        if (y > 0) {
            if(direction == Direction.TOP || direction == Direction.ANY) {
                neighbours.add(new int[]{x, y - 1});
            }
        }
        // Bottom neighbour
        if (y < height - 1) {
            if(direction == Direction.BOTTOM || direction == Direction.ANY) {
                neighbours.add(new int[]{x, y + 1});
            }
        }
        // Top left neighbour
        if (x > 0 && y > 0) {
            if(direction == Direction.TOP_LEFT || direction == Direction.ANY) {
                neighbours.add(new int[]{x - 1, y - 1});
            }
        }
        // Top right neighbour
        if (x < width - 1 && y > 0) {
            if(direction == Direction.TOP_RIGHT || direction == Direction.ANY) {
                neighbours.add(new int[]{x + 1, y - 1});
            }
        }
        // Bottom left neighbour
        if (x > 0 && y < height - 1) {
            if(direction == Direction.BOTTOM_LEFT || direction == Direction.ANY) {
                neighbours.add(new int[]{x - 1, y + 1});
            }
        }
        // Bottom right neighbour
        if (x < width - 1 && y < height - 1) {
            if(direction == Direction.BOTTOM_RIGHT || direction == Direction.ANY) {
                neighbours.add(new int[]{x + 1, y + 1});
            }
        }
        return neighbours;
    }

    public static String getNextLetter(String letter) {
        return switch (letter) {
            case "X" -> "M";
            case "M" -> "A";
            case "A" -> "S";
            case "S" -> throw new IllegalArgumentException("Shouldn't have reached here");
            default -> throw new IllegalArgumentException("Unexpected value: " + letter);
        };
    }

    public enum Direction {
        ANY,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT;

        public Direction getDirection(int[] neighbour, int[] current) {
            if(neighbour[0] == current[0] - 1 && neighbour[1] == current[1]) {
                return Direction.LEFT;
            } else if(neighbour[0] == current[0] + 1 && neighbour[1] == current[1]) {
                return Direction.RIGHT;
            } else if(neighbour[0] == current[0] && neighbour[1] == current[1] - 1) {
                return Direction.TOP;
            } else if(neighbour[0] == current[0] && neighbour[1] == current[1] + 1) {
                return Direction.BOTTOM;
            } else if(neighbour[0] == current[0] - 1 && neighbour[1] == current[1] - 1) {
                return Direction.TOP_LEFT;
            } else if(neighbour[0] == current[0] + 1 && neighbour[1] == current[1] - 1) {
                return Direction.TOP_RIGHT;
            } else if(neighbour[0] == current[0] - 1 && neighbour[1] == current[1] + 1) {
                return Direction.BOTTOM_LEFT;
            } else if(neighbour[0] == current[0] + 1 && neighbour[1] == current[1] + 1) {
                return Direction.BOTTOM_RIGHT;
            } else {
                return Direction.ANY;
            }

        }
    }
}
