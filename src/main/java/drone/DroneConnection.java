package drone;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class DroneConnection {

	// Class attributes
	private String inputConnectionPort;
	private String inputConnectionIP;
	private int connectionPort;
	private InetAddress connectionIP;
	private Boolean connectionStatus;
	private DatagramSocket udpClient;
	final private int MAX_NUM_OF_RETRIES = 3;

	public DroneConnection() throws Exception{
		inputConnectionPort = "N/A";
		inputConnectionIP = "N/A";
		udpClient = new DatagramSocket();
		udpClient.setSoTimeout(1000);
	}

	// class public methods
	public void communicateWithDrone(String message) throws Exception{

		// sends message, then waits for a return
		int tries = 0;
		String reply;
		while (tries < this.MAX_NUM_OF_RETRIES) {
			sendMessage(message);
			reply = listenForMessage();

			if (reply != null && reply.equals("ok")) {
				break;
			}
			tries++;
		}
	}

	public void sendMessage(String message) throws Exception{

		byte[] bytesToSend;
		DatagramPacket datagramPacket;


		bytesToSend = message.getBytes(StandardCharsets.UTF_8);
		datagramPacket = new DatagramPacket(bytesToSend, bytesToSend.length, this.connectionIP, this.connectionPort);
		udpClient.send(datagramPacket);
		System.out.println("Sent " + message + " message to " + this.connectionIP.toString() + ":" + this.connectionPort);
	}

	public String listenForMessage() throws Exception{

		byte[] bytesReceived;
		DatagramPacket datagramPacket;
		String reply = null;

		bytesReceived = new byte[64];
		datagramPacket = new DatagramPacket(bytesReceived, 64);

		try {
			udpClient.receive(datagramPacket);
		}
		catch (SocketTimeoutException ex) {
			datagramPacket = null;
		}
		if (datagramPacket != null) {
			System.out.println(String.format("Received %d bytes", datagramPacket.getLength()));
			reply = new String(bytesReceived, 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
			System.out.println("Messaged received: " + reply);
		}
		return reply;
	}

	public void connectToDrone() throws Exception{
		String connectMessage = "command";
		System.out.println("Connecting to drone....");
		communicateWithDrone(connectMessage);
	}

	public String getInputConnectionPort() {
		return this.inputConnectionPort;
	}
	public String getInputConnectionIP() {
		return this.inputConnectionIP;
	}
	public int getConnectionPort() { return connectionPort;	}
	public InetAddress getConnectionIP() {
		return connectionIP;
	}

	public void setInputConnectionPort(String inputConnectionPort) {
		this.inputConnectionPort = inputConnectionPort;
		this.connectionPort = Integer.parseInt(inputConnectionPort);
	}
	public void setInputConnectionIP(String inputConnectionIP) throws Exception {
		this.inputConnectionIP = inputConnectionIP;
		this.connectionIP = parseIPAddress();
	}


	InetAddress parseIPAddress() throws UnknownHostException {
		// check if user has input an IP address
		if (inputConnectionIP.equals("N/A")) {
			System.out.println("User has not input an IP address. Using default '192.168.10.1'");
			return InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
		}

		byte[] byteIPAddress = splitIP(this.inputConnectionIP);
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