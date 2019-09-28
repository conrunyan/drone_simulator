package flight;

import messages.Message;

public class FlyFlip implements FlightBehavior {

	private String flightDirection;

	public FlyFlip(String direction) {
		flightDirection = direction;
	}

	public Message flyInDirection() {
		// flips
		String command = "flip " + flightDirection;
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Flips backward");
	}

}