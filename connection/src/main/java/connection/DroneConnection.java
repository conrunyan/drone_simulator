package connection;

import java.net.*;
import java.nio.charset.StandardCharsets;
import messages.Message;

public class DroneConnection {

    // Class attributes
    private String inputConnectionPort;
    private String inputConnectionIP;
    private int connectionPort;
    private InetAddress connectionIP;
    private Boolean isConnected;
    private DatagramSocket udpClient;
    final private int MAX_NUM_OF_RETRIES = 3;

    public DroneConnection() throws Exception{
        inputConnectionPort = "N/A";
        inputConnectionIP = "N/A";
        isConnected = false;
        udpClient = new DatagramSocket();
        udpClient.setSoTimeout(1000);
    }

    // class public methods
    public String communicateWithDrone(Message message) throws Exception{

        // sends message, then waits for a return
        int tries = 0;
        Message reply = null;
        // can't send messages unless the drone is connected
        if (isConnected || message.getMessageText().equals("command")) {
            while (tries < this.MAX_NUM_OF_RETRIES) {
                sendMessage(message);
                reply = listenForMessage();

                if (reply != null && reply.equals("ok")) {
                    if (message.getMessageText().equals("command")) {
                        this.isConnected = true;
                    }
                    break;
                }
                tries++;
            }
        }
        else {
            System.out.println("ERROR: Cannot send drone command " + message + " until drone is in command mode");
        }
        return reply.getMessageText();
    }

    private void sendMessage(Message message) throws Exception{

        byte[] bytesToSend;
        DatagramPacket datagramPacket;


        bytesToSend = message.encode();
        datagramPacket = new DatagramPacket(bytesToSend, bytesToSend.length, this.connectionIP, this.connectionPort);
        udpClient.send(datagramPacket);
//		System.out.println("Sent " + message + " message to " + this.connectionIP.toString() + ":" + this.connectionPort);
    }

    private Message listenForMessage() throws Exception{

        byte[] bytesReceived;
        DatagramPacket datagramPacket;
        Message reply = null;

        bytesReceived = new byte[64];
        datagramPacket = new DatagramPacket(bytesReceived, 64);

        try {
            udpClient.receive(datagramPacket);
        }
        catch (SocketTimeoutException ex) {
            datagramPacket = null;
        }
        if (datagramPacket != null) {
//			System.out.println(String.format("Received %d bytes", datagramPacket.getLength()));
            reply = Message.decode(bytesReceived, 0, bytesReceived.length);
//			System.out.println("Messaged received: " + reply);
        }
        return reply;
    }

    public void connectToDrone() throws Exception{
        Message connectMessage = Message.decode("command".getBytes(), 0, "command".length());
        System.out.println("Connecting to drone....");
        communicateWithDrone(connectMessage);
        if (this.isConnected) {
            System.out.println("Successfully connected to drone!");
        }
        else {
            System.out.println("Oops, couldn't establish drone connection. Make sure you're on the Drone's WiFi.\n" +
                    "Also check your IP address, and port to make sure they're correct.");
        }
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
    public boolean getConnectionStatus() {
        return isConnected;
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