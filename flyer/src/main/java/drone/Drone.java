package drone;

import missions.*;
import connection.DroneConnection;

public class Drone {

	private MissionOne missOne;
	private MissionTwo missTwo;
	private MissionThree missThree;
	private DroneConnection connection;

	public Drone() throws Exception{
		this.connection = new DroneConnection();
		this.missOne = new MissionOne();
		this.missTwo = new MissionTwo();
		this.missThree = new MissionThree();
	}

	public boolean startConnection() throws Exception{
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
			switch (missionID) {
				case 1 : {
					this.missOne.executeMission(this.connection);
					break;
				}
				case 2 : {
					this.missTwo.executeMission(this.connection);
					break;
				}
				case 3 : {
					this.missThree.executeMission(this.connection);
					break;
				}
				default : {
					System.out.println("WARNING: '" + missionID + "' is not a valid mission.");
				}
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
	}
	public void setDronePort(String port) {
		this.connection.setLocalPort(port);
	}

}