package missions;

import drone.DroneConnection;
import flight.FlightBehavior;

public abstract class Mission {

	private FlightBehavior[] flightPath;

	public void executeMission(DroneConnection droneConnection) throws Exception{
	}
}