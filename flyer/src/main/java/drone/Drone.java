package drone;

import messages.Message;
import messages.Status;
import missions.*;
import connection.DroneConnection;
import state.DroneState;

public class Drone implements Runnable {

	private MissionOne missOne;
	private MissionTwo missTwo;
	private MissionThree missThree;
	private DroneConnection connection;
	private DroneConnection statusConnection;
	private Thread statusThread;
	private DroneFlyerState state;

	public Drone() throws Exception {
		this.statusConnection = new DroneConnection(0);
		initDrone();

	}

	public Drone(int port) throws Exception {
		this.statusConnection = new DroneConnection(port);
		initDrone();
	}

	private void initDrone() throws Exception {
		this.connection = new DroneConnection();
		this.missOne = new MissionOne();
		this.missTwo = new MissionTwo();
		this.missThree = new MissionThree();
		state = DroneFlyerState.getInstance();
	}

	public boolean startConnection() throws Exception {
		// make sure an IP address and Port have been set
		if (this.connection.getLocalIP().equals("N/A") || this.connection.getLocalPort().equals("N/A")) {
			System.out.printf("IP Address or Port have not been set. Current values: IP %s Port: %s\n", connection.getLocalIP(), connection.getLocalPort());
			return false;
		}

		this.connection.connectToDrone();

		// start up listener thread
		statusThread = new Thread(this, "drone_status");
		statusThread.start();

		return this.connection.getConnectionStatus();
	}

	@Override
	public void run() {
		try {
			listenForDroneUpdates();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	void listenForDroneUpdates() throws Exception {

		System.out.println("In drone state listener...");
		while (this.connection.getConnectionStatus()) {
			Message msg = this.statusConnection.listenForMessage();
			if (msg.getMessageType().equals("status")) {
				System.out.println("Updating status...: " + msg.getMessageText());
				this.state.updateState((Status)msg);
			}
		}
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
		this.connection.setRemoteIP(ipAddr);
	}
	public void setDronePort(String port) {
		this.connection.setLocalPort(port);
		this.connection.setRemotePort(Integer.parseInt(port));
	}

}