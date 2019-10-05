package missions;

import connection.DroneConnection;
import flight.*;

import java.util.concurrent.TimeUnit;


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
			System.out.println("\tExecuting flight behavior: " + fb.flyInDirection());
			// Should test this with a dummy drone connection that receives the flight behavior and returns a message
			droneConnection.communicateWithDrone(fb.flyInDirection());
			try {
				Thread.sleep(this.timeBetweenCommands);
			}
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}