package flight;

public class FlyRight implements FlightBehavior {

	public String flyInDirection() {
		// flies backwards 100 cm
		return "right 100";
	}

	public void displayBehaviorName() {
		System.out.println("Flies right 100 cm");
	}

}