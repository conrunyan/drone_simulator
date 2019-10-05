package flight;

import messages.Message;
import java.util.Scanner;

public class FlyWaitForUserInput implements FlightBehavior {

    private Scanner userInput;

    public FlyWaitForUserInput() {
        userInput = new Scanner(System.in);
    }

    public Message flyInDirection() {
        // fly forward
        String input = getUserInput();
        String command = determineCommand(input);
        return Message.decode(command.getBytes(), 0, command.length());
    }

    public void displayBehaviorName() {
        System.out.println("Flies right 100 cm");
    }

    private String getUserInput() {
        String input = null;
        System.out.println("Which direction do you want to flip? [l, r, b, f]");

        input = this.userInput.nextLine();

        return input;

    }

    private String determineCommand(String command) {
        String output = "stop";
        if (command.equals("l")) {
            output = "flip l";
        }
        else if (command.equals("r")) {
            output = "flip r";
        }
        else if (command.equals("f")) {
            output = "flip f";
        }
        else if (command.equals("b")) {
            output = "flip b";
        }
        else {
            output = "stop";
        }

        return output;
    }
}
