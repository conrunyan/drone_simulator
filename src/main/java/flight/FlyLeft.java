package flight;

public class FlyLeft implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "left 100";
	}

	public void displayBehaviorName() {
		System.out.println("Flies forward 100 cm");
	}

}