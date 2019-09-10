package mission;

import missions.*;
import org.junit.*;
import utils.UdpTestServer;
import drone.DroneConnection;
import static org.junit.Assert.*;

public class MissionsTest {

    @Test
    public void testMission() throws Exception {
        UdpTestServer serv = new UdpTestServer("5005");
        MissionOne missOne = new MissionOne();
        MissionTwo missTwo = new MissionTwo();
        MissionThree missThree = new MissionThree();
        DroneConnection drone = new DroneConnection();

        serv.runServer();
        drone.setInputConnectionIP("127.0.0.1");
        drone.setInputConnectionPort("5005");
        drone.connectToDrone();

        missOne.setTimeBetweenCommands(100);
        missTwo.setTimeBetweenCommands(100);
        missThree.setTimeBetweenCommands(100);


        missOne.executeMission(drone);
        missTwo.executeMission(drone);
        missThree.executeMission(drone);

        serv.killServer();
    }
}
