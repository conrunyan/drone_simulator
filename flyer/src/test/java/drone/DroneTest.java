package drone;

import engine.DroneSimulator;
import engine.DroneSimulatorTestRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.UdpTestServer;

public class DroneTest {



    @Test
    public void testDroneStartConnection() throws Exception {
        DroneSimulatorTestRunner sim = new DroneSimulatorTestRunner(54321);
        Drone testDrone = new Drone();
        String ipAddr = "127.0.0.1";
        String port = "54321";

        Thread t = new Thread(sim, "drone_start_conn_test");
        t.start();

        // test attempting to connect without setting IP address/Port
        assertFalse(testDrone.startConnection());
        assertFalse(testDrone.getConnectionStatus());
        testDrone.setDroneIP(ipAddr);
        assertFalse(testDrone.getConnectionStatus());
        assertFalse(testDrone.startConnection());
        testDrone.setDronePort(port);
        assertTrue(testDrone.startConnection());
        assertTrue(testDrone.getConnectionStatus());

        sim.killThread();
    }

    @Test
    public void testDroneMissions() throws Exception {
        DroneSimulatorTestRunner sim = new DroneSimulatorTestRunner(5010);
        Drone testDrone = new Drone();
        String ipAddr = "127.0.0.1";
        String port = "5010";

        Thread t = new Thread(sim, "drone_missions_test_thread");
        t.start();

        // test attempting to connect without setting IP address/Port
        testDrone.setDroneIP(ipAddr);
        testDrone.setDronePort(port);
        testDrone.startConnection();

        testDrone.flyMission(1);
        testDrone.flyMission(4);

        sim.killThread();
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
