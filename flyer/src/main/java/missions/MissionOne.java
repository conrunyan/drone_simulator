package missions;

import connection.DroneConnection;
import flight.*;


public class MissionOne extends Mission {

	private int timeBetweenCommands;
	private FlightBehavior[] flightBehaviors;

	public MissionOne() {
		missionNumber = 1;
		flightBehaviors = new FlightBehavior[] {
			new FlyRight(100),
			new FlyLeft(100),
			new FlyFlip("b"),
		};
		timeBetweenCommands = 3000;
	}

	public void setTimeBetweenCommands(int timeToSleep) {
		timeBetweenCommands = timeToSleep;
	}

	public void executeSubMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			// Should test this with a dummy drone connection that receives the flight behavior and returns a message
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(timeBetweenCommands);
		}
	}

}