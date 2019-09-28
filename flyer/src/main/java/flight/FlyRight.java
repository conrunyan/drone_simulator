package flight;

import messages.Message;

public class FlyRight implements FlightBehavior {

	private Integer flightDistance;

	public FlyRight(int distance) {
		flightDistance = distance;
	}

	public Message flyInDirection() {
		// fly forward
		String command = "right " + flightDistance.toString();
		return Message.decode(command.getBytes(), 0, command.length());
	}

	public void displayBehaviorName() {
		System.out.println("Flies right 100 cm");
	}

}