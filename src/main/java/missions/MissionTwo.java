package missions;

import drone.DroneConnection;
import flight.*;

public class MissionTwo extends Mission {

	private FlyBackward flyBack;
	private FlyLeft flyLeft;
	private FlyRight flyRight;
	private FlyForward flyForward;
	private FlyLiftOff flyLiftOff;
	private FlyLand flyLand;
	private int missionNumber;
	private FlightBehavior[] flightBehaviors;

	public MissionTwo() {
		flyBack = new FlyBackward();
		flyLeft = new FlyLeft();
		flyRight = new FlyRight();
		flyForward = new FlyForward();
		flyLiftOff = new FlyLiftOff();
		flyLand = new FlyLand();
		missionNumber = 1;
		flightBehaviors = new FlightBehavior[] {flyLiftOff, flyBack, flyLeft, flyForward, flyRight, flyLand};
	}

	public void executeMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(2000);
		}
	}

}