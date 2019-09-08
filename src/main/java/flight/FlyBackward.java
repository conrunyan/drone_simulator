package flight;

public class FlyBackward implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "back 100";
	}

	public void displayBehaviorName() {
		System.out.println("Flies backwards 100 cm");
	}

}