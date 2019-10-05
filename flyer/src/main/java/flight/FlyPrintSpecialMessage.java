package flight;

import messages.Message;

public class FlyPrintSpecialMessage implements FlightBehavior {

    private String specialMessage;

    public FlyPrintSpecialMessage(String message) {
        specialMessage = message;
    }

    public Message flyInDirection() {
        // fly forward
        String command = "ok";
        System.out.println(specialMessage);
        return Message.decode(command.getBytes(), 0, command.length());
    }

    public void displayBehaviorName() {
        System.out.println("Flies right 100 cm");
    }
}
