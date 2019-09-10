package missions;

import drone.DroneConnection;
import flight.*;


public class MissionOne extends Mission {

	private FlyRight flyRight;
	private FlyLeft flyLeft;
	private FlyFlip flyFlip;
	private FlyLiftOff flyLiftOff;
	private FlyLand flyLand;
	private int missionNumber;
	private FlightBehavior[] flightBehaviors;

	public MissionOne() {
		flyRight = new FlyRight();
		flyLeft = new FlyLeft();
		flyFlip = new FlyFlip();
		flyLiftOff = new FlyLiftOff();
		flyLand = new FlyLand();
		missionNumber = 1;
		flightBehaviors = new FlightBehavior[] {flyLiftOff, flyRight, flyLeft, flyFlip, flyLand};
	}

	public void executeMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(3000);
		}
	}

}