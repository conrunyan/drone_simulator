package missions;

import connection.DroneConnection;
import flight.*;


public class MissionOne extends Mission {

	private FlyRight flyRight;
	private FlyLeft flyLeft;
	private FlyFlip flyFlip;
	private FlyLiftOff flyLiftOff;
	private FlyLand flyLand;
	private int missionNumber;
	private int timeBetweenCommands;
	private FlightBehavior[] flightBehaviors;

	public MissionOne() {
		flyRight = new FlyRight();
		flyLeft = new FlyLeft();
		flyFlip = new FlyFlip();
		flyLiftOff = new FlyLiftOff();
		flyLand = new FlyLand();
		missionNumber = 1;
		flightBehaviors = new FlightBehavior[] {flyLiftOff, flyRight, flyLeft, flyFlip, flyLand};
		timeBetweenCommands = 3000;
	}

	public void setTimeBetweenCommands(int timeToSleep) {
		timeBetweenCommands = timeToSleep;
	}

	public void executeMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			// Should test this with a dummy drone connection that receives the flight behavior and returns a message
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(timeBetweenCommands);
		}
	}

}