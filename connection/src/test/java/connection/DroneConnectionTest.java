package connection;

import java.net.*;

import messages.Message;
import org.junit.Test;
import static org.junit.Assert.*;

public class DroneConnectionTest {

    @Test
    public void testDroneConnectionConstructorAndGetterSetters() throws Exception{
        DroneConnection testDrone = new DroneConnection();

        String userInputIP = "192.168.10.1";
        String userInputPort = "9000";
        InetAddress realIP = InetAddress.getByAddress(new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1});
        int realPort = 9000;

        // check default getters
        assertEquals("Failed default getter - IP", "N/A", testDrone.getLocalIP());
        assertEquals("Failed default getter - Port", "N/A", testDrone.getLocalPort());

        testDrone.setLocalIP(userInputIP);
        testDrone.setLocalPort(userInputPort);

        assertEquals("Failed setter - IP", userInputIP, testDrone.getLocalIP());
        assertEquals("Failed setter - Port", userInputPort, testDrone.getLocalPort());
        assertEquals(realIP, testDrone.getLocalConnIP());
        assertEquals(realPort, testDrone.getLocalConnPort());

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
        InetAddress result = testDrone.parseIPAddress(testUserInput);
        assertArrayEquals(result.getAddress(), expectedIP.getAddress());

        // test user setting IP address
        testDrone.setLocalIP(testUserInput);
        InetAddress result2 = testDrone.parseIPAddress(testUserInput);
        assertArrayEquals(result2.getAddress(), expectedIP2.getAddress());
    }

    @Test
    public void testDroneConnectionInitialConnection() throws Exception{
        DroneConnection testDrone = new DroneConnection();
        DroneConnection testDrone2 = new DroneConnection();

        String userInputIP = "127.0.0.1";
        String userInputPort = "5005";
        String msg = "command";
        Message outMsg = Message.decode(msg.getBytes(), 0, msg.length());

        testDrone.setLocalIP(userInputIP);
        testDrone.setLocalPort(userInputPort);
        testDrone2.setRemoteIP(testDrone.getLocalIP());
        testDrone2.setRemotePort(testDrone.getLocalConnPort());

        testDrone2.sendMessage(outMsg);
        Message result = testDrone.listenForMessage();


        assertEquals("command", result.getMessageText());

        // dead connection
//        String result2 = testDrone.communicateWithDrone(outMsg);
//        assertNull(result2);
    }

    @Test
    public void testDroneConnectionDontSendCommandsUntilConnected() throws Exception{
        DroneConnection testDrone = new DroneConnection();

        String userInputIP = "127.0.0.1";
        String userInputPort = "30000";
        String msg = "takeoff";
        String cmd = "command";
        Message outMsg = Message.decode(msg.getBytes(), 0, msg.length());
        Message outMsgCmd = Message.decode(cmd.getBytes(), 0, cmd.length());

        testDrone.setLocalIP(userInputIP);
        testDrone.setLocalPort(userInputPort);

        // try to send a message. result should be null, as it's not connected yet
        Message result = testDrone.communicateWithDrone(outMsg);
        testDrone.connectToDrone();
        assertFalse(testDrone.getConnectionStatus());
        assertEquals("error", result.getMessageText());

        // "command" message should work
        testDrone.connectToDrone();
        Message result2 = testDrone.communicateWithDrone(outMsg);
        assertEquals(result2.getMessageText(), "ok");
        assertTrue(testDrone.getConnectionStatus());

        // "takeoff" command should now work
        Message result3 = testDrone.communicateWithDrone(outMsgCmd);
        assertEquals("error", result3.getMessageText());

        // kill drone server
    }

}
