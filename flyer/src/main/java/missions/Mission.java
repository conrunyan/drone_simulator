package missions;

import connection.DroneConnection;
import flight.FlightBehavior;
import messages.Message;

public abstract class Mission {

	private FlightBehavior[] flightPath;
	int missionNumber;

	public void executeMission(DroneConnection droneConnection) throws Exception {
		String startCommand = "takeoff";
		String endCommand = "land";
		droneConnection.communicateWithDrone(Message.decode(startCommand.getBytes(), 0, startCommand.length()));
		executeSubMission(droneConnection);
		droneConnection.communicateWithDrone(Message.decode(endCommand.getBytes(), 0, endCommand.length()));
	}


	public int getMissionNumber() {
		return missionNumber;
	}

	abstract protected void executeSubMission(DroneConnection droneConnection) throws Exception;
}