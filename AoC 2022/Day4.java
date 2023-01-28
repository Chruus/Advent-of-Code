import java.io.*;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws IOException {
        ArrayList<pairOfElves> pairs = new ArrayList<pairOfElves>();
        Scanner input = new Scanner(new File("Day4.txt"));
        while (input.hasNextLine()) {
            pairs.add(new pairOfElves(input.nextLine()));
        }
        int counter = 0;

        for (pairOfElves pair : pairs) {
            if (pair.fullyCountains())
                counter++;
        }
        System.out.println(counter);
    }

}

class pairOfElves {
    ArrayList<Integer> elf1IDs, elf2IDs;

    public pairOfElves(String line) {
        String elf1 = line.split(",")[0];
        String elf2 = line.split(",")[1];
        elf1IDs = addAllIDs(elf1);
        elf2IDs = addAllIDs(elf2);
    }

    public ArrayList<Integer> addAllIDs(String input) {
        int start = Integer.parseInt(input.split("-")[0]);
        int end = Integer.parseInt(input.split("-")[1]);
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (int count = 0; count <= end - start; count++) {
            output.add(count + start);
        }
        return output;
    }

    public boolean partiallyContains() {
        for (int ID : elf1IDs) {
            if (elf2IDs.contains(ID))
                return true;
        }
        for (int ID : elf2IDs) {
            if (elf1IDs.contains(ID))
                return true;
        }
        return false;
    }

    public boolean fullyCountains() {
        if (elf1IDs.get(0) >= elf2IDs.get(0) && elf1IDs.get(elf1IDs.size() - 1) <= elf2IDs.get(elf2IDs.size() - 1)
                || elf1IDs.get(0) <= elf2IDs.get(0)
                        && elf1IDs.get(elf1IDs.size() - 1) >= elf2IDs.get(elf2IDs.size() - 1))
            return true;
        return false;

    }
}
