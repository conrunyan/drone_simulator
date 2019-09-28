package flight;

import messages.Message;

public class FlyLeft implements FlightBehavior {

	private Integer flightDistance;

	public FlyLeft(int distance) {
		flightDistance = distance;
	}

	public Message flyInDirection() {
		// fly forward
		String command = "left " + flightDistance.toString();
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Flies forward 100 cm");
	}

}