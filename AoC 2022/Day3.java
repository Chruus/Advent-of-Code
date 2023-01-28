import java.io.*;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        partTwo();
    }

    public static void partTwo() throws IOException {
        Scanner input = new Scanner(new File("Day3.txt"));
        ArrayList<Character> commonItemList = new ArrayList<Character>();
        while (input.hasNextLine()) {
            String elf1 = input.nextLine();
            String elf2 = input.nextLine();
            String elf3 = input.nextLine();
            HashMap<Character, Integer> numberOfItems = new HashMap<Character, Integer>();
            for (int item = 0; item < elf1.length(); item++) {
                if (!numberOfItems.containsKey(elf1.charAt(item)))
                    numberOfItems.put(elf1.charAt(item), 1);
            }
            for (int item = 0; item < elf2.length(); item++) {
                if (numberOfItems.containsKey(elf2.charAt(item)))
                    numberOfItems.put(elf2.charAt(item), 2);
            }
            for (int item = 0; item < elf3.length(); item++) {
                if (numberOfItems.containsKey(elf3.charAt(item)) && numberOfItems.get(elf3.charAt(item)) == 2) {
                    commonItemList.add(elf3.charAt(item));
                    break;
                }
            }
        }

        int total = 0;
        for (char c : commonItemList)
            total += getPriority(c);
        System.out.println(total);
    }

    public static void partOne() throws IOException {
        Scanner input = new Scanner(new File("Day3.txt"));
        ArrayList<Character> misplacedList = new ArrayList<Character>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String firstArea = line.substring(0, line.length() / 2);
            String secondArea = line.substring(line.length() / 2, line.length());

            for (int item = 0; item < firstArea.length(); item++) {
                if (firstArea.indexOf(secondArea.charAt(item)) >= 0) {
                    misplacedList.add(firstArea.charAt(item));
                    // System.out.println(item + " " + firstArea.length());
                    break;
                }
            }
        }

        int total = 0;
        for (char c : misplacedList)
            total += getPriority(c);
        System.out.println(total);
    }

    public static int getPriority(char c) {
        if ((int) c > 96)
            return (int) c - 96;
        else
            return (int) c - 38;
    }
}
