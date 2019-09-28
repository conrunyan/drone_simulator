package flight;

import messages.Message;

public class FlyLand implements FlightBehavior {

	public Message flyInDirection() {
		// lands drone
		String command = "land";
		return Message.decode(command.getBytes(), 0, command.length());
	}



	public void displayBehaviorName() {
		System.out.println("Automatically lands the drone.");
	}

}