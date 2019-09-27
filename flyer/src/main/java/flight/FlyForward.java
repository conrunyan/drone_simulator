package flight;

public class FlyForward implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "forward 100";
	}

	public void displayBehaviorName() {
		System.out.println("Flies forward 100 cm");
	}

}