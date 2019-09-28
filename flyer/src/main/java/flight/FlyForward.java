package flight;

import messages.Message;

public class FlyForward implements FlightBehavior {

	private Integer flightDistance;

	public FlyForward(int distance) {
		flightDistance = distance;
	}

	public Message flyInDirection() {
		// fly forward
		String command = "forward " + flightDistance.toString();
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Flies forward 100 cm");
	}

}