package flight;

public class FlyLand implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "land";
	}

	public void displayBehaviorName() {
		System.out.println("Automatically lands the drone.");
	}

}