package drone;

import java.net.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class DroneConnectionTest {

    @Test
    public void testDroneConnectionConstructorAndGetterSetters() throws Exception{
        DroneConnection testDrone = new DroneConnection();

        String userInputIP = "192.168.10.1";
        String userInputPort = "9000";

        // check default getters
        assertEquals("Failed default getter - IP", "N/A", testDrone.getInputConnectionIP());
        assertEquals("Failed default getter - Port", "N/A", testDrone.getInputConnectionPort());

        testDrone.setInputConnectionIP(userInputIP);
        testDrone.setInputConnectionPort(userInputPort);

        assertEquals("Failed setter - IP", userInputIP, testDrone.getInputConnectionIP());
        assertEquals("Failed setter - Port", userInputPort, testDrone.getInputConnectionPort());

    }

    @Test
    public void testDroneConnectionSplitIP() throws Exception{
        DroneConnection testDrone = new DroneConnection();
        byte[] expectedIP = new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1};

        String userInputIP = "192.168.10.1";

        byte[] result = testDrone.splitIP(userInputIP);

        assertArrayEquals(result, expectedIP);
    }

    @Test
    public void testDroneConnectionParseIPAddress() throws Exception {
        DroneConnection testDrone = new DroneConnection();
        InetAddress expectedIP = InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
        InetAddress expectedIP2 = InetAddress.getByAddress(new byte[] { (byte) 0, (byte) 0, (byte) 0, (byte) 0});
        String testUserInput = "0.0.0.0";

        // test default IP case first
        InetAddress result = testDrone.parseIPAddress();
        assertArrayEquals(result.getAddress(), expectedIP.getAddress());

        // test user setting IP address
        testDrone.setInputConnectionIP(testUserInput);
        InetAddress result2 = testDrone.parseIPAddress();
        assertArrayEquals(result2.getAddress(), expectedIP2.getAddress());
    }

    @Test
    public void testDroneConnectionInitialConnection() throws Exception{
        DroneConnection testDrone = new DroneConnection();
        String udpServerCmd = "python ./udp_server/udp_server.py 5005";
        Runtime.getRuntime().exec(udpServerCmd);

        String userInputIP = "127.0.0.1";
        String userInputPort = "5005";

        testDrone.setInputConnectionIP(userInputIP);
        testDrone.setInputConnectionPort(userInputPort);

        // live connection
        testDrone.sendMessage("command");
        String result = testDrone.listenForMessage();
        testDrone.communicateWithDrone("KILL");

        assertEquals(result, "ok");

        // dead connection
        testDrone.sendMessage("takeoff");
        String result2 = testDrone.listenForMessage();

        assertNull(result2);
    }

    @Test
    public void testDroneConnectionDontSendCommandsUntilConnected() throws Exception{
        DroneConnection testDrone = new DroneConnection();
        String udpServerCmd = "python ./udp_server/udp_server.py 30000";
        Runtime.getRuntime().exec(udpServerCmd);

        String userInputIP = "127.0.0.1";
        String userInputPort = "30000";

        testDrone.setInputConnectionIP(userInputIP);
        testDrone.setInputConnectionPort(userInputPort);

        // try to send a message. result should be null, as it's not connected yet
        testDrone.sendMessage("takeoff");
        String result = testDrone.listenForMessage();

        assertNull(result);

        // "command" message should work
        testDrone.sendMessage("command");
        String result2 = testDrone.listenForMessage();
        assertEquals(result2, "ok");

        // "takeoff" command should now work
        testDrone.sendMessage("takeoff");
        String result3 = testDrone.listenForMessage();
        assertEquals(result3, "ok");

        // kill drone server
        testDrone.sendMessage("KILL");
    }

}
