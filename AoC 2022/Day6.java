import java.io.*;
import java.util.*;

public class Day6 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("Day6.txt"));
        String packet = input.next();
        input.close();
        PacketInterpreter interpreter = new PacketInterpreter(packet);
        System.out.println(interpreter.getStringWithNoRepeats(14));
    }
}

class PacketInterpreter {
    String packet;

    PacketInterpreter(String _packet) {
        packet = _packet;
    }

    public int getStringWithNoRepeats(int lengthOfString) {
        int position = 0;
        outer: for (int packetPosition = lengthOfString; packetPosition < packet.length(); packetPosition++) {
            char[] markerCheck = packet.substring(packetPosition - lengthOfString, packetPosition).toCharArray();
            ArrayList<Character> markerCheckList = new ArrayList<Character>();
            for (char c : markerCheck) {
                if (markerCheckList.contains(c)) {
                    break;
                }

                markerCheckList.add(c);

                if (markerCheckList.size() >= lengthOfString) {
                    position = packetPosition;
                    break outer;
                }
            }
        }
        return position;
    }
}
