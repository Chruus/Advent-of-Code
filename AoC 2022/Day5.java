import java.io.*;
import java.util.*;

public class Day5 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("Day5.txt"));
        ArrayList<String> lines = new ArrayList<String>();
        String line = "";
        do {
            line = input.nextLine();
            lines.add(line);
        } while (line.indexOf("1") == -1);
        input.nextLine();
        CargoCrane cargo = new CargoCrane(lines, "CrateMover 9001");
        while (input.hasNextLine()) {
            line = input.nextLine();
            Scanner lineScan = new Scanner(line);
            lineScan.next();
            int ammount = lineScan.nextInt();
            lineScan.next();
            int start = lineScan.nextInt();
            lineScan.next();
            int end = lineScan.nextInt();
            // cargo.moveCargo(0, 1, 2);
            // break;
            cargo.moveCargo(ammount, start, end);
            if (!input.hasNextLine())
                lineScan.close();
        }

        System.out.println(cargo);
    }

}

class CargoCrane {
    char[][] stacksOfCargo;
    String model;

    public CargoCrane(ArrayList<String> lines, String _model) {
        Scanner lineScan;
        int numberOfStacks = 0;
        model = _model;
        lineScan = new Scanner(lines.get(lines.size() - 1));
        while (lineScan.hasNextInt()) {
            numberOfStacks++;
            lineScan.nextInt();
        }
        lines.remove(lines.size() - 1);

        int numberOfBoxes = 0;
        for (String line : lines) {
            lineScan = new Scanner(line);
            for (int stack = 0; stack < numberOfStacks; stack += 1) {
                String cargoSpace = line.substring(stack * 4 + 1, stack * 4 + 2);
                if (cargoSpace.matches(".*[A-Z].*")) {
                    numberOfBoxes++;
                }
            }
        }
        lineScan.close();

        stacksOfCargo = new char[numberOfStacks][numberOfBoxes];
        for (int stack = 0; stack < numberOfStacks; stack++) {
            for (int position = 0; position < numberOfBoxes; position++) {
                stacksOfCargo[stack][position] = ' ';
            }
        }

        for (int line = 0, position = lines.size() - 1; line < lines.size(); line++) {
            for (int stack = 0; stack < numberOfStacks; stack++) {
                char cargo = lines.get(line).substring(stack * 4 + 1, stack * 4 + 2).toCharArray()[0];
                stacksOfCargo[stack][position] = cargo;
            }
            position--;
        }
    }

    public void moveCargo(int cargoToMove, int start, int end) {
        start--;
        end--;
        cargoToMove--;
        ArrayList<Character> cargo = new ArrayList<Character>();
        for (int position = stacksOfCargo[start].length - 1, cargoMoved = 0; position >= 0; position--) {
            if (stacksOfCargo[start][position] != ' ') {
                if (cargoMoved > cargoToMove)
                    break;
                cargo.add(stacksOfCargo[start][position]);
                stacksOfCargo[start][position] = ' ';
                cargoMoved++;
            }
        }

        for (int position = 0; position < stacksOfCargo[start].length; position++) {
            if (stacksOfCargo[end][position] == ' ') {
                if (cargo.size() <= 0)
                    break;
                if (model.equals("CrateMover 9000")) {
                    stacksOfCargo[end][position] = cargo.get(0);
                    cargo.remove(0);
                }
                if (model.equals("CrateMover 9001")) {
                    stacksOfCargo[end][position] = cargo.get(cargo.size() - 1);
                    cargo.remove(cargo.size() - 1);
                }

            }
        }
    }

    public String toString() {
        String output = "";
        int stack = 1;
        for (char[] stacks : stacksOfCargo) {
            System.out.print(stack + ": ");
            for (char cargo : stacks) {
                if (cargo != ' ')
                    System.out.print("[" + cargo + "] ");
            }
            System.out.println();
            stack++;
        }
        return output;
    }
}
