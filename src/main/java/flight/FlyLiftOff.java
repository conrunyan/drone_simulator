package flight;

public class FlyLiftOff implements FlightBehavior {

	public String flyInDirection() {
		// drone takes off
		return "takeoff";
	}

	public void displayBehaviorName() {
		System.out.println("Drone takes off from current location.");
	}

}