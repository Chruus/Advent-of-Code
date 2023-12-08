//Christopher Petty

import java.io.*;
import java.util.*;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        partOne();
        partTwo();
    }

    public static void partTwo() throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day2.txt"));
        int total = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] datasets = line.substring(line.indexOf(":") + 2).strip().split(";");

            HashMap<String, Integer> minCubes = new HashMap<>();
            minCubes.put("red", Integer.MIN_VALUE);
            minCubes.put("green", Integer.MIN_VALUE);
            minCubes.put("blue", Integer.MIN_VALUE);

            for (String str : datasets) {
                HashMap<String, Integer> numCubes = new HashMap<>();
                String[] data = str.split(", ");
                for (String str2 : data) {
                    String[] temp = str2.strip().split(" ");
                    numCubes.put(temp[1], Integer.parseInt(temp[0]));
                }

                for (String test : numCubes.keySet()) {
                    if (numCubes.get(test) > minCubes.get(test))
                        minCubes.put(test, numCubes.get(test));
                }
            }

            total += minCubes.get("red") * minCubes.get("blue") * minCubes.get("green");
        }
        System.out.println(total);
    }

    public static void partOne() throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day2.txt"));
        int total = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            int gameNum = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.indexOf(":")));
            String[] datasets = line.substring(line.indexOf(":") + 2).strip().split(";");

            boolean passes = true;

            for (String str : datasets) {
                HashMap<String, Integer> numCubes = new HashMap<>();
                String[] data = str.split(", ");

                for (String str2 : data) {
                    String[] temp = str2.strip().split(" ");
                    numCubes.put(temp[1], Integer.parseInt(temp[0]));
                }

                if (numCubes.get("red") != null && numCubes.get("red") > 12
                        || numCubes.get("green") != null && numCubes.get("green") > 13
                        || numCubes.get("blue") != null && numCubes.get("blue") > 14) {
                    passes = false;
                    break;
                }
            }
            if (passes)
                total += gameNum;
        }
        System.out.println(total);
    }
}
