import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class historianHysteria {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(partOne());
    }

    private static int partOne() throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int total = 0;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String[] locationIds = scanner.nextLine().split(" {3}");
            list1.add(Integer.parseInt(locationIds[0]));
            list2.add(Integer.parseInt(locationIds[1]));
        }

        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);

        for (int i = 0; i < list1.size(); i++) {
            total += Math.abs(list1.get(i) - list2.get(i));
        }

        return total;
    }
}
