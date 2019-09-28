package missions;

import connection.DroneConnection;
import flight.*;

public class MissionTwo extends Mission {

	private int timeBetweenCommands;
	private FlightBehavior[] flightBehaviors;

	public MissionTwo() {
		missionNumber = 2;
		flightBehaviors = new FlightBehavior[] {
				new FlyBackward(100),
				new FlyLeft(100),
				new FlyForward(100),
				new FlyRight(100),
		};
		timeBetweenCommands = 2000;
	}

	public void setTimeBetweenCommands(int timeToSleep) {
		timeBetweenCommands = timeToSleep;
	}

	public void executeSubMission(DroneConnection droneConnection) throws Exception{
		for (FlightBehavior fb : this.flightBehaviors) {
			System.out.println("\tExecuting flight pattern: " + fb.flyInDirection());
			droneConnection.communicateWithDrone(fb.flyInDirection());
			Thread.sleep(timeBetweenCommands);
		}
	}

}