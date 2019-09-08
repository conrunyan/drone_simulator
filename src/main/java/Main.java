import drone.DroneConnection;

public class Main {
    public static void main(String[] args) throws Exception {
        DroneConnection testDrone = new DroneConnection();
        String udpServerCmd = "python ./udp_server/udp_server.py";
        Runtime.getRuntime().exec(udpServerCmd);

        String userInputIP = "127.0.0.1";
        String userInputPort = "5005";

        testDrone.setInputConnectionIP(userInputIP);
        testDrone.setInputConnectionPort(userInputPort);

        testDrone.connectToDrone();

        testDrone.communicateWithDrone("KILL");
    }
}
