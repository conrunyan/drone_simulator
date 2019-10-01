package missions;

import org.junit.*;
import connection.DroneConnection;

public class MissionsTest {

    @Test
    public void testMission() throws Exception {
        DroneConnection server = new DroneConnection();
        MissionOne missOne = new MissionOne();
        MissionTwo missTwo = new MissionTwo();
        MissionThree missThree = new MissionThree();
        DroneConnection drone = new DroneConnection();

        drone.setLocalIP("127.0.0.1");
        drone.setLocalPort("5005");
        server.setLocalIP(drone.getLocalIP());
        drone.connectToDrone();

        missOne.setTimeBetweenCommands(100);
        missTwo.setTimeBetweenCommands(100);
        missThree.setTimeBetweenCommands(100);


        missOne.executeMission(drone);
        missTwo.executeMission(drone);
        missThree.executeMission(drone);
    }
}
