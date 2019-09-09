package missions;

import drone.DroneConnection;
import flight.FlightBehavior;

public abstract class Mission {

	private FlightBehavior[] flightPath;
	private int missionNumber;

	public void executeMission(DroneConnection droneConnection) throws Exception{
		// TODO - implement Mission.executeMission
		throw new UnsupportedOperationException();
	}

	public int getMissionNumber() {
		return this.missionNumber;
	}



}