import java.io.*;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("Day8.txt"));
        Forest forest = new Forest(input);
        System.out.println("Visible Trees: " + forest.getNumberOfVisibleTrees());
        System.out.println("Scenic Score: " + forest.getHighestScenicScore());
    }
}

class Forest {
    private int[][] forest;
    private int highestScenicScore;
    private int numberOfVisibleTrees;

    public Forest(Scanner input) {
        ArrayList<String> treeRows = new ArrayList<String>();
        while (input.hasNextLine())
            treeRows.add(input.nextLine());

        forest = new int[treeRows.size()][treeRows.get(0).length()];
        for (int row = 0; row < forest.length; row++) {
            for (int column = 0; column < forest[0].length; column++) {
                forest[row][column] = Integer.parseInt(treeRows.get(row).substring(column, column + 1));
            }
        }
        numberOfVisibleTrees = getNumberOfVisibleTrees();
        highestScenicScore = getHighestScenicScore();
    }

    public int getHighestScenicScore() {
        if (highestScenicScore > 0)
            return highestScenicScore;

        highestScenicScore = Integer.MIN_VALUE;
        for (int row = 0; row < forest.length; row++) {
            for (int column = 0; column < forest[0].length; column++) {
                int scenicScore = getScenecScore(row, column);
                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }
        return highestScenicScore;
    }

    public int getNumberOfVisibleTrees() {
        if (numberOfVisibleTrees > 0)
            return numberOfVisibleTrees;

        numberOfVisibleTrees = 0;
        for (int row = 0; row < forest.length; row++) {
            for (int column = 0; column < forest[0].length; column++) {
                if (isVisible(row, column))
                    numberOfVisibleTrees++;
            }
        }
        return numberOfVisibleTrees;
    }

    public boolean isVisible(int row, int column) {
        int forestLength = forest.length - 1;
        int forestWidth = forest[0].length - 1;
        if (row == 0 || row == forestLength)
            return true;
        if (column == 0 || column == forestWidth)
            return true;

        boolean left = true, right = true, down = true, up = true;

        for (int pos = column - 1; pos >= 0; pos--) {
            if (forest[row][pos] >= forest[row][column])
                left = false;
        }

        for (int pos = column + 1; pos <= forestLength; pos++) {
            if (forest[row][pos] >= forest[row][column])
                right = false;
        }

        for (int pos = row - 1; pos >= 0; pos--) {
            if (forest[pos][column] >= forest[row][column])
                up = false;
        }

        for (int pos = row + 1; pos <= forestWidth; pos++) {
            if (forest[pos][column] >= forest[row][column])
                down = false;
        }
        if (up || down || left || right)
            return true;
        return false;
    }

    public int getScenecScore(int row, int column) {
        int leftCount = 0;
        for (int pos = column - 1; pos >= 0; pos--) {
            leftCount++;
            if (forest[row][pos] >= forest[row][column])
                break;
        }

        int rightCount = 0;
        for (int pos = column + 1; pos < forest[0].length; pos++) {
            rightCount++;
            if (forest[row][pos] >= forest[row][column])
                break;
        }

        int upCount = 0;
        for (int pos = row - 1; pos >= 0; pos--) {
            upCount++;
            if (forest[pos][column] >= forest[row][column])
                break;
        }

        int downCount = 0;
        for (int pos = row + 1; pos < forest.length; pos++) {
            downCount++;
            if (forest[pos][column] >= forest[row][column])
                break;
        }

        return upCount * downCount * leftCount * rightCount;
    }

    public String toString() {
        String output = "";
        for (int[] row : forest) {
            for (int tree : row) {
                output += "" + tree;
            }
            output += "\n\n";
        }
        return output;
    }
}