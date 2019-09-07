package drone;

import org.junit.Test;

import java.net.*;

import static org.junit.Assert.*;


public class DroneConnectionTest {

    @Test
    public void testDroneConnectionConstructorAndGetterSetters() {
        DroneConnection testDrone = new DroneConnection();

        String userInputIP = "192.168.10.1";
        String userInputPort = "9000";

        // check default getters
        assertEquals("Failed default getter - IP", "N/A", testDrone.getConnectionIP());
        assertEquals("Failed default getter - Port", "N/A", testDrone.getConnectionPort());

        testDrone.setConnectionIP(userInputIP);
        testDrone.setConnectionPort(userInputPort);

        assertEquals("Failed setter - IP", userInputIP, testDrone.getConnectionIP());
        assertEquals("Failed setter - Port", userInputPort, testDrone.getConnectionPort());

    }

    @Test
    public void testDroneConnectionSplitIP() {
        DroneConnection testDrone = new DroneConnection();
        byte[] expectedIP = new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1};

        String userInputIP = "192.168.10.1";

        byte[] result = testDrone.splitIP(userInputIP);

        assertArrayEquals(result, expectedIP);
    }

    @Test
    public void testDroneConnectionParseIPAddress() throws UnknownHostException {
        DroneConnection testDrone = new DroneConnection();
        InetAddress expectedIP = InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
        InetAddress expectedIP2 = InetAddress.getByAddress(new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0});
        String testUserInput = "0.0.0.0";

        // test default IP case first
        InetAddress result = testDrone.parseIPAddress();
        assertArrayEquals(result.getAddress(), expectedIP.getAddress());

        // test user setting IP address
        testDrone.setConnectionIP(testUserInput);
        InetAddress result2 = testDrone.parseIPAddress();
        assertArrayEquals(result2.getAddress(), expectedIP2.getAddress());
    }

}
