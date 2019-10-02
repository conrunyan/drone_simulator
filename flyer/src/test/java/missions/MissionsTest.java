package missions;

import engine.DroneSimulatorTestRunner;
import org.junit.*;
import connection.DroneConnection;

public class MissionsTest {

    @Test
    public void testMission() throws Exception {
        DroneSimulatorTestRunner server = new DroneSimulatorTestRunner(20001);
        MissionOne missOne = new MissionOne();
        MissionTwo missTwo = new MissionTwo();
        MissionThree missThree = new MissionThree();
        DroneConnection drone = new DroneConnection();

        Thread t = new Thread(server, "drone_sim");
        t.start();

        drone.setLocalIP("127.0.0.1");
        drone.setLocalPort("5005");
        drone.setRemoteIP("127.0.0.1");
        drone.setRemotePort(20001);
        drone.connectToDrone();

        missOne.setTimeBetweenCommands(100);
        missTwo.setTimeBetweenCommands(100);
        missThree.setTimeBetweenCommands(100);


        missOne.executeMission(drone);
        missTwo.executeMission(drone);
        missThree.executeMission(drone);

        server.killThread();
    }
}
