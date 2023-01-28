import java.io.*;
import java.util.*;

public class Day2 {
    public static void main(String args[]) throws IOException {
        Scanner input = new Scanner(new File("calorieCounting.txt"));
        ArrayList<Integer> listOfCalories = new ArrayList<Integer>();
        listOfCalories.add(0);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.matches("^[0-9]+$"))
                listOfCalories.set(0, listOfCalories.get(0) + Integer.parseInt(line));
            else
                listOfCalories.add(0, 0);
        }

        Collections.sort(listOfCalories);
        int output = listOfCalories.get(listOfCalories.size() - 1) + listOfCalories.get(listOfCalories.size() - 2)
                + listOfCalories.get(listOfCalories.size() - 3);
        System.out.println(output);
    }
}