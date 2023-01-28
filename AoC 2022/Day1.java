import java.io.*;
import java.util.*;

public class Day1 {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("RockPaperScissors.txt"));
        int totalScore = 0;
        while (input.hasNextLine()) {
            int myPoints = getXYZWin(input);

            totalScore += myPoints;
        }

        System.out.println(totalScore);
    }

    public static int getXYZWin(Scanner input) {
        String theirHand = input.next();
        String myHand = input.next();
        int myNumber = 0;
        int myWin = 0;
        int theirNumber = 0;
        int myPoints = 0;

        if (theirHand.equals("A"))
            theirNumber = 1;
        else if (theirHand.equals("B"))
            theirNumber = 2;
        else if (theirHand.equals("C"))
            theirNumber = 3;

        if (myHand.equals("X"))
            myWin = -1;
        else if (myHand.equals("Y"))
            myWin = 0;
        else if (myHand.equals("Z"))
            myWin = 1;

        if (myWin > 0) {
            if (theirNumber == 1)
                myNumber = 2;
            if (theirNumber == 2)
                myNumber = 3;
            if (theirNumber == 3)
                myNumber = 1;
            myPoints += 6;
        } else if (myWin == 0) {
            myNumber = theirNumber;
            myPoints += 3;
        } else if (myWin < 0) {
            if (theirNumber == 1)
                myNumber = 3;
            if (theirNumber == 2)
                myNumber = 1;
            if (theirNumber == 3)
                myNumber = 2;
        }

        myPoints += myNumber;

        return myPoints;
    }

    public static int getXYZHand(Scanner input) {
        String theirHand = input.next();
        String myHand = input.next();
        int myNumber = 0;
        int theirNumber = 0;
        int myPoints = 0;

        if (theirHand.equals("A"))
            theirNumber = 1;
        else if (theirHand.equals("B"))
            theirNumber = 2;
        else if (theirHand.equals("C"))
            theirNumber = 3;

        if (myHand.equals("X"))
            myNumber = 1;
        else if (myHand.equals("Y"))
            myNumber = 2;
        else if (myHand.equals("Z"))
            myNumber = 3;

        myPoints += myNumber;

        if (myNumber == theirNumber)
            myPoints += 3;
        else if (theirNumber == 1 && myNumber == 2 || theirNumber == 2 && myNumber == 3
                || theirNumber == 3 && myNumber == 1)
            myPoints += 6;

        return myPoints;
    }
}
