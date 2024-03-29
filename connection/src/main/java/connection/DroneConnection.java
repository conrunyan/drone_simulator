package connection;

import java.net.*;

import messages.Message;

public class DroneConnection {

    // Class attributes
    private String localPort;
    private String localIP;
    private int remoteConnPort;
    private InetAddress remoteConnIP;
    private int localConnPort;
    private InetAddress localConnIP;
    private Boolean isConnected;
    private DatagramSocket udpClient;
    private Boolean verboseOutput;
    private Integer maxNumOfRetries = 3;
    private Integer socketTimeout;

    public DroneConnection() throws Exception{
        init();
        udpClient = new DatagramSocket(0);
        udpClient.setSoTimeout(socketTimeout);
    }

    public DroneConnection(int listenPort) throws Exception{
        init();
        udpClient = new DatagramSocket(listenPort);
//        udpClient.setSoTimeout(1000);
    }

    private void init() {
        localPort = "N/A";
        localIP = "N/A";
        verboseOutput = false;
        isConnected = false;
        socketTimeout = 1000;
    }

    // class public methods
    public Message communicateWithDrone(Message message) throws Exception{

        // sends message, then waits for a return
        int tries = 0;
        Message reply = Message.decode("error".getBytes(), 0, "error".length());
        // can't send messages unless the drone is connected
            while (tries < this.maxNumOfRetries) {
                sendMessage(message);
                reply = listenForMessage();

                if (reply != null && reply.getMessageText().equals("ok")) {
                    if (message.getMessageText().equals("command")) {
                        this.isConnected = true;
                    }
                    break;
                }
                tries++;
            }

        return reply;
    }

    public void sendMessage(Message message) throws Exception{

        byte[] bytesToSend;
        DatagramPacket datagramPacket;

        bytesToSend = message.encode();
        datagramPacket = new DatagramPacket(bytesToSend, bytesToSend.length, this.remoteConnIP, this.remoteConnPort);
        udpClient.send(datagramPacket);
        printDebuggingMessage("Sent " + message + " message to " + this.remoteConnIP.toString() + ":" + this.remoteConnPort);
    }

    private void printDebuggingMessage(String message) {
        if (verboseOutput) {
            System.out.println(message);
        }
    }

    public Message listenForMessage() throws Exception{

        byte[] bytesReceived;
        DatagramPacket datagramPacket;
        Message reply = Message.decode("error".getBytes(), 0, "error".length());

        bytesReceived = new byte[64];
        datagramPacket = new DatagramPacket(bytesReceived, 64);

        try {
            udpClient.receive(datagramPacket);
        }
        catch (SocketTimeoutException ex) {
            datagramPacket = null;
        }
        if (datagramPacket != null) {
            printDebuggingMessage(String.format("Received %d bytes", datagramPacket.getLength()));
            reply = Message.decode(bytesReceived, 0, bytesReceived.length);
            reply.setRemotePort(datagramPacket.getPort());
            reply.setRemoteIPAddr(datagramPacket.getAddress());
            printDebuggingMessage("Messaged received: " + reply);
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

    public String getLocalPort() {
        return this.localPort;
    }
    public String getLocalIP() {
        return this.localIP;
    }
    public int getLocalConnPort() { return udpClient.getLocalPort();	}
    public InetAddress getLocalConnIP() {
        return localConnIP;
    }
    public boolean getConnectionStatus() {
        return isConnected;
    }

    public Integer getMaxNumOfRetries() {
        return this.maxNumOfRetries;
    }

    public Integer getSocketTimeout() {
        return this.socketTimeout;
    }

    public void setVerboseOutput(boolean verboseStatus) {
        this.verboseOutput = verboseStatus;
    }

    public void setRemotePort(int remotePort) {
        this.remoteConnPort = remotePort;
    }

    public void setRemoteIP(String remoteIP) throws Exception {
        this.remoteConnIP = parseIPAddress(remoteIP);
    }

    public void setRemoteIP(InetAddress remoteIP) throws Exception {
        this.remoteConnIP = remoteIP;
    }

    public void setLocalPort(String localPort) {
        this.localPort = localPort;
        this.localConnPort = Integer.parseInt(localPort);
    }

    public void setLocalIP(String localIP) throws Exception {
        this.localIP = localIP;
        this.localConnIP = parseIPAddress(localIP);
    }

    public void setMaxNumOfRetries(Integer maxRetries) {
        this.maxNumOfRetries = maxRetries;
    }

    public void setSocketTimeout(Integer timeout) {
        this.socketTimeout = timeout;
        try {
            this.udpClient.setSoTimeout(this.socketTimeout);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    InetAddress parseIPAddress(String ipAddr) throws UnknownHostException {
        // check if user has input an IP address
        if (ipAddr.equals("N/A")) {
            System.out.println("User has not input an IP address. Using default '192.168.10.1'");
            return InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
        }

        byte[] byteIPAddress = splitIP(ipAddr);
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