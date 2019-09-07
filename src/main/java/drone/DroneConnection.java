package drone;

import java.net.*;
//import java.nio.charset.StandardCharsets;

public class DroneConnection {

	// Class attributes
	private String connectionPort = "N/A";
	private String connectionIP = "N/A";
	private Boolean connectionStatus;
//	private DatagramSocket connection = new DatagramSocket();

	// class public methods
	public void sendMessage(String message) {
		// TODO - implement drone.DroneConnection.sendMessage
		throw new UnsupportedOperationException();
	}

	public String listenForMessage() {
		// TODO - implement drone.DroneConnection.listenForMessage
		throw new UnsupportedOperationException();
	}

	public void connectToDrone() {

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


	InetAddress parseIPAddress() throws UnknownHostException {
		// check if user has input an IP address
		if (connectionIP.equals("N/A")) {
			System.out.println("User has not input an IP address. Using default '192.168.10.1'");
			return InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
		}

		byte[] byteIPAddress = splitIP(this.connectionIP);
		return InetAddress.getByAddress(byteIPAddress);
	}

	byte[] splitIP(String ipAddress) {
		// assumes that user has entered a valid ip address (per Dr. Clyde, we did not need to validate user input for this one)
		String[] ipAddrElements = ipAddress.split("\\.");
		byte[] newIpAddr = new byte[ipAddrElements.length];

		for (int i = 0; i < newIpAddr.length; i++) {
			newIpAddr[i] = (byte) Integer.parseInt(ipAddrElements[i]);
		}

		return newIpAddr;
	}




}