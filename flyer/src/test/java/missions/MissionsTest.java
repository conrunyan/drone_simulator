package missions;

import missions.*;
import org.junit.*;
import utils.UdpTestServer;
import connection.DroneConnection;
import static org.junit.Assert.*;

public class MissionsTest {

    @Test
    public void testMission() throws Exception {
        DroneConnection server = new DroneConnection();
        MissionOne missOne = new MissionOne();
        MissionTwo missTwo = new MissionTwo();
        MissionThree missThree = new MissionThree();
        DroneConnection drone = new DroneConnection();

        drone.setInputConnectionIP("127.0.0.1");
        drone.setInputConnectionPort("5005");
        server.setInputConnectionIP(drone.getInputConnectionIP());
        drone.connectToDrone();

        missOne.setTimeBetweenCommands(100);
        missTwo.setTimeBetweenCommands(100);
        missThree.setTimeBetweenCommands(100);


        missOne.executeMission(drone);
        missTwo.executeMission(drone);
        missThree.executeMission(drone);
    }
}
