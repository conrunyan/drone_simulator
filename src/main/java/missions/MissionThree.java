package missions;

import drone.DroneConnection;
import flight.*;

public class MissionThree extends Mission {

	private FlyBackward flyBack;
	private FlyForward flyForward;
	private FlyFlip flyFlip;
	private FlyLiftOff flyLiftOff;
	private FlyLand flyLand;
	private int missionNumber;
	private int timeBetweenCommands;
	private FlightBehavior[] flightBehaviors;

	public MissionThree() {
		flyBack = new FlyBackward();
		flyFlip = new FlyFlip();
		flyForward = new FlyForward();
		flyLiftOff = new FlyLiftOff();
		flyLand = new FlyLand();
		missionNumber = 1;
		flightBehaviors = new FlightBehavior[] {flyLiftOff, flyBack, flyFlip, flyForward, flyFlip, flyLand};
		timeBetweenCommands = 2000;
	}

	public void setTimeBetweenCommands(int timeToSleep) {
		timeBetweenCommands = timeToSleep;
	}

	public void executeMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(timeBetweenCommands);
		}
	}

}