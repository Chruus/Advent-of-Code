//Christopher Petty

import java.util.*;
import java.io.*;

public class Day1 {

    static String[] Digits = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
    static HashMap<String, String> DigitsKey = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        partOne();
        partTwo();
    }

    public static void partTwo() throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day1.txt"));
        int output = 0;

        DigitsKey.put("one", "1");
        DigitsKey.put("two", "2");
        DigitsKey.put("three", "3");
        DigitsKey.put("four", "4");
        DigitsKey.put("five", "5");
        DigitsKey.put("six", "6");
        DigitsKey.put("seven", "7");
        DigitsKey.put("eight", "8");
        DigitsKey.put("nine", "9");

        while (input.hasNextLine()) {
            String line = input.nextLine();
            TreeMap<Integer, String> indices = new TreeMap<>();

            for (String str : Digits) {
                int index = 0;
                while (line.indexOf(str, index) != -1) {
                    indices.put(line.indexOf(str, index), DigitsKey.get(str));
                    index = line.indexOf(str, index) + 1;
                }
            }

            String[] lineList = line.split("");
            for (int i = 0; i < lineList.length; i++)
                if (lineList[i].matches("\\d"))
                    indices.put(i, lineList[i]);

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int index : indices.keySet()) {
                if (index > max)
                    max = index;
                if (index < min)
                    min = index;
            }

            String temp = indices.get(min) + "" + indices.get(max);
            output += Integer.parseInt(temp);
        }
        System.out.println(output);
    }

    public static void partOne() throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day1.txt"));
        int output = 0;
        while (input.hasNextLine()) {
            String[] line = input.nextLine().split("");
            ArrayList<String> data = new ArrayList<String>();
            for (String str : line)
                data.add(str);

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).matches("\\d"))
                    continue;
                data.remove(i);
                i--;
            }

            if (data.size() < 1)
                continue;

            String temp = data.get(0) + "" + data.get(data.size() - 1);
            output += Integer.parseInt(temp);
        }
        System.out.println(output);
    }
}