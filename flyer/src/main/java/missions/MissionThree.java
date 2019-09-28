package missions;

import connection.DroneConnection;
import flight.*;

public class MissionThree extends Mission {

	private int timeBetweenCommands;
	private FlightBehavior[] flightBehaviors;

	public MissionThree() {
		missionNumber = 3;
		flightBehaviors = new FlightBehavior[] {
			new FlyBackward(100),
			new FlyFlip("f"),
			new FlyForward(100),
			new FlyFlip("b"),
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