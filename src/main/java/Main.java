import drone.Drone;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Drone testDrone = new Drone();
        String udpServerCmd = "python ./udp_server/udp_server.py";
        Runtime.getRuntime().exec(udpServerCmd);

        String userInputIP = "127.0.0.1";
        String userInputPort = "8889";

        testDrone.setDroneIP(userInputIP);
        testDrone.setDronePort(userInputPort);
        testDrone.startConnection();
        testDrone.flyMission(1);

//        Scanner input = new Scanner(System.in);
//        String userIn = input.nextLine();
//        // Testing basic manual input with drone simulator
//        while (!userIn.equals("STOP")) {
//            testDrone.sendMessage(userIn);
//            userIn = input.nextLine();
//        }
    }
}
