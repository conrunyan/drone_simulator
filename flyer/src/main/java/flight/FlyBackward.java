package flight;

import messages.Message;

public class FlyBackward implements FlightBehavior {

	private Integer flightDistance;

	public FlyBackward(int distance) {
		flightDistance = distance;
	}

	public Message flyInDirection() {
		// flies backwards
		String command = "back " + flightDistance.toString();
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Flies backwards 100 cm");
	}

}