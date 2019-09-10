package drone;

import org.junit.Test;
import static org.junit.Assert.*;
import utils.UdpTestServer;

public class DroneTest {

    @Test
    public void testDroneStartConnection() throws Exception{
        UdpTestServer serv = new UdpTestServer("5005");
        Drone testDrone = new Drone();
        String ipAddr = "127.0.0.1";
        String port = "5005";

        serv.runServer();

        // test attempting to connect without setting IP address/Port
        assertFalse(testDrone.startConnection());
        assertFalse(testDrone.getConnectionStatus());
        testDrone.setDroneIP(ipAddr);
        assertFalse(testDrone.getConnectionStatus());
        assertFalse(testDrone.startConnection());
        testDrone.setDronePort(port);
        assertTrue(testDrone.startConnection());
        assertTrue(testDrone.getConnectionStatus());

        serv.killServer();
    }

    @Test
    public void testDroneMissions() throws Exception {
        UdpTestServer serv = new UdpTestServer("5005");
        Drone testDrone = new Drone();
        String ipAddr = "127.0.0.1";
        String port = "5005";

        // test attempting to connect without setting IP address/Port
        testDrone.setDroneIP(ipAddr);
        testDrone.setDronePort(port);
        serv.runServer();
        testDrone.startConnection();

        testDrone.flyMission(1);
        testDrone.flyMission(2);
        testDrone.flyMission(3);
        testDrone.flyMission(4);

        serv.killServer();
    }

    @Test
    public void testDroneConstructorAndGetters() throws Exception{
        Drone testDrone = new Drone();
        String ipAddr = "127.0.0.1";
        String port = "5005";

        testDrone.setDroneIP(ipAddr);
        testDrone.setDronePort(port);

        assertEquals(ipAddr, testDrone.getDroneIP());
        assertEquals(port, testDrone.getDronePort());
    }

}
