import drone.DroneConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DroneConnection testDrone = new DroneConnection();
        String udpServerCmd = "python ./udp_server/udp_server.py";
        Runtime.getRuntime().exec(udpServerCmd);

        String userInputIP = "127.0.0.1";
        String userInputPort = "8889";

        testDrone.setInputConnectionIP(userInputIP);
        testDrone.setInputConnectionPort(userInputPort);
        testDrone.connectToDrone();

        Scanner input = new Scanner(System.in);
        String userIn = input.nextLine();
        // Testing basic manual input with drone simulator
        while (!userIn.equals("STOP")) {
            testDrone.sendMessage(userIn);
            userIn = input.nextLine();
        }
    }
}
