package drone;

public class DroneConnection {

	private String connectionPort = "N/A";
	private String connectionIP = "N/A";
	private Boolean connectionStatus;

	/**
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		// TODO - implement drone.DroneConnection.sendMessage
		throw new UnsupportedOperationException();
	}

	public String listenForMessage() {
		// TODO - implement drone.DroneConnection.listenForMessage
		throw new UnsupportedOperationException();
	}

	public String getConnectionPort() {
		return this.connectionPort;
	}
	public String getConnectionIP() {
		return this.connectionIP;
	}
	public void setConnectionPort(String connectionPort) {
		this.connectionPort = connectionPort;
	}
	public void setConnectionIP(String connectionIP) {
		this.connectionIP = connectionIP;
	}




}