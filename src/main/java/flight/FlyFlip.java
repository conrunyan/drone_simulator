package flight;

public class FlyFlip implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "flip b";
	}

	public void displayBehaviorName() {
		System.out.println("Flips backward");
	}

}