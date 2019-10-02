package engine;

import connection.DroneConnection;
import messages.Message;
import org.junit.Test;
import state.DroneState;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class DroneSimulatorTest {

    @Test
    public void testExecuteRequest() throws Exception{
        DroneSimulator sim = new DroneSimulator(6000);
        DroneState tmpState = sim.getSimState();
        Message msg = Message.decode("command".getBytes(), 0, "command".length());

        sim.executeRequest(msg);

        assertTrue(sim.getSimState().isInCommandMode());
    }

    @Test
    public void testExecuteRequestBad() throws Exception {
        DroneSimulator sim = new DroneSimulator(6001);
        DroneState tmpState = sim.getSimState();
        Message msg = Message.decode("commandBAD".getBytes(), 0, "commandBAD".length());

        sim.executeRequest(msg);

        assertFalse(sim.getSimState().isInCommandMode());
    }

    @Test
    public void testSendResponse() throws Exception {
        DroneSimulator sim = new DroneSimulator(6002);
        DroneConnection receiver = new DroneConnection();
        Message msg = Message.decode("command".getBytes(), 0, "command".length());
        msg.setRemoteIPAddr(InetAddress.getByAddress(new byte[] { (byte) 127, (byte) 0, (byte) 0, (byte) 1}));
        msg.setRemotePort(receiver.getLocalConnPort());

        receiver.setLocalIP("127.0.0.1");
        receiver.setLocalPort("5006");

        sim.sendResponse(msg);
        Message receivedMsg = receiver.listenForMessage();

        assertEquals("ok", receivedMsg.getMessageText());
    }
}