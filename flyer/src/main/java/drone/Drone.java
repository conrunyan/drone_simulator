package drone;

import messages.Message;
import messages.Status;
import missions.*;
import connection.DroneConnection;
import state.DroneState;
import java.util.HashMap;

public class Drone {

	private DroneConnection connection;
	private HashMap<Integer, Mission> droneMissions;
	private DroneFlyerState state;

	public Drone() throws Exception {
		initDrone();
	}

	private void initDrone() throws Exception {
		this.connection = new DroneConnection();
		this.droneMissions = new HashMap<>();
		this.droneMissions.put(1, new MissionOne());
		this.droneMissions.put(2, new MissionTwo());
		this.droneMissions.put(3, new MissionThree());
		state = DroneFlyerState.getInstance();
	}

	public boolean startConnection() throws Exception {
		// make sure an IP address and Port have been set
		if (this.connection.getLocalIP().equals("N/A") || this.connection.getLocalPort().equals("N/A")) {
			System.out.printf("IP Address or Port have not been set. Current values: IP %s Port: %s\n", connection.getLocalIP(), connection.getLocalPort());
			return false;
		}

		this.connection.connectToDrone();

		return this.connection.getConnectionStatus();
	}

	public void flyMission(int missionID) throws Exception {
		// can't execute mission unless drone is connected
		if (getConnectionStatus()) {
			System.out.println("Executing mission: " + missionID);
			if (this.droneMissions.containsKey(missionID)) {
				this.droneMissions.get(missionID).executeMission(this.connection);
			}
			else {
				System.out.println("WARNING: '" + missionID + "' is not a valid mission.");
			}
		}
		else {
			System.out.println("ERROR: Drone is not connected. Please connect before flying mission.");
		}
	}

	public boolean getConnectionStatus() {
		return this.connection.getConnectionStatus();
	}
	public String getDroneIP() {
		return this.connection.getLocalIP();
	}
	public String getDronePort() {
		return this.connection.getLocalPort();
	}


	public void setDroneIP(String ipAddr) throws Exception{
		this.connection.setLocalIP(ipAddr);
		this.connection.setRemoteIP(ipAddr);
	}
	public void setDronePort(String port) {
		this.connection.setLocalPort(port);
		this.connection.setRemotePort(Integer.parseInt(port));
	}

}