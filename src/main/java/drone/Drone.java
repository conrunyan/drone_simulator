package drone;

import missions.*;

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

	public void setDroneIP(String ipAddr) throws Exception{
		this.connection.setInputConnectionIP(ipAddr);
	}

	public void setDronePort(String port) {
		this.connection.setInputConnectionPort(port);
	}

	public void startConnection() throws Exception{
		// make sure an IP address and Port have been set
		if (this.connection.getInputConnectionIP().equals("N/A") || this.connection.getInputConnectionPort().equals("N/A")) {
			System.out.printf("IP Address or Port have not been set. Current values: IP %s Port: %s", connection.getInputConnectionIP(), connection.getInputConnectionPort());
			return;
		}
		this.connection.connectToDrone();
	}

	public void flyMission(int missionID) {
		// TODO - implement drone.DronePilot.flyMission
		throw new UnsupportedOperationException();
	}

	public void endFlight() {
		// TODO - implement drone.DronePilot.endFlight
		throw new UnsupportedOperationException();
	}

}