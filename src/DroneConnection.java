public class DroneConnection {

	private int connectionPort;
	private int connectionIP;
	private Boolean connectionStatus;

	/**
	 * 
	 * @param message
	 */
	public void sendMessage(byteString message) {
		// TODO - implement DroneConnection.sendMessage
		throw new UnsupportedOperationException();
	}

	public byteString listenForMessage() {
		// TODO - implement DroneConnection.listenForMessage
		throw new UnsupportedOperationException();
	}

	public int getConnectionPort() {
		return this.connectionPort;
	}

	/**
	 * 
	 * @param connectionPort
	 */
	public void setConnectionPort(int connectionPort) {
		this.connectionPort = connectionPort;
	}

	public int getConnectionIP() {
		return this.connectionIP;
	}

	/**
	 * 
	 * @param connectionIP
	 */
	public void setConnectionIP(int connectionIP) {
		this.connectionIP = connectionIP;
	}

}