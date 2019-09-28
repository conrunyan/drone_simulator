package flight;

import messages.Message;

public class FlyLiftOff implements FlightBehavior {

	public Message flyInDirection() {
		// lands drone
		String command = "takeoff";
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Drone takes off from current location.");
	}

}