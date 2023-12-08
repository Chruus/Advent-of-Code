//Christopher Petty

import java.io.*;
import java.util.*;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day3.txt"));
        String line = input.nextLine();
        char[][] grid = new char[140][140];
        grid[0] = line.toCharArray();
        int total = 0;

        for (int i = 1; i < 140; i++) {
            grid[i] = input.nextLine().toCharArray();
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (!Character.isDigit(grid[r][c]))
                    continue;
                if (isPartNumber(grid, r, c))
                    total += getPartNumber(grid, r, c);
                else
                    getPartNumber(grid, r, c);
            }
        }

        System.out.println(total);
    }

    public static int getPartNumber(char[][] grid, int row, int col) {
        String number = "";
        for (int c = col; c < grid[row].length; c++) {
            if (!Character.isDigit(grid[row][c]))
                break;
            number += grid[row][c];
            grid[row][c] = '.';
        }
        return Integer.parseInt(number);
    }

    public static boolean isPartNumber(char[][] temp, int row, int col) {
        if (row < 0 || row >= temp.length || col < 0 || col >= temp[0].length)
            return false;

        char[][] grid = new char[temp.length][temp[0].length];
        for (int r = 0; r < temp.length; r++) {
            for (int c = 0; c < temp[r].length; c++)
                grid[r][c] = temp[r][c];
        }

        grid[row][col] = '.';

        char u, d, l, r, ul, ur, dl, dr;
        u = d = l = r = ul = ur = dl = dr = '.';
        if (row > 0)
            u = grid[row - 1][col];
        if (row < grid.length - 2)
            d = grid[row + 1][col];
        if (col > 0)
            l = grid[row][col - 1];
        if (col < grid[0].length - 2)
            r = grid[row][col + 1];
        if (row > 0 && col > 0)
            ul = grid[row - 1][col - 1];
        if (row > 0 && col < grid[0].length - 1)
            ur = grid[row - 1][col + 1];
        if (row < grid.length - 2 && col > 0)
            dl = grid[row + 1][col - 1];
        if (row < grid.length - 2 && col < grid[0].length - 1)
            dr = grid[row + 1][col + 1];

        if (u != '.' && !Character.isDigit(u) || d != '.' && !Character.isDigit(d)
                || l != '.' && !Character.isDigit(l) || r != '.' && !Character.isDigit(r)
                || ul != '.' && !Character.isDigit(ul) || ur != '.' && !Character.isDigit(ul)
                || dl != '.' && !Character.isDigit(dl) || dr != '.' && !Character.isDigit(dr))
            return true;
        if (u == '.' && d == '.' && l == '.' && r == '.' && ul == '.' && ur == '.' && dl == '.' && dr == '.')
            return false;

        if (isPartNumber(grid, row, col + 1))
            return true;
        return false;
    }
}
