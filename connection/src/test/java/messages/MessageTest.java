package messages;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void decode() {
        String upMessage = "up 120";
        String backMessage = "back 120";
        String batteryMessage = "battery?";
        String commandMessage = "command";
        String cwMessage = "cw 120";
        String cwwMessage = "cww 120";
        String downMessage = "down 120";
        String flipMessage = "flip l";
        String forwardMessage = "forward 120";
        String goMessage = "go 10 10 10 30";
        String landMessage = "land";
        String leftMessage = "left 120";
        String okMessage = "ok";
        String rightMessage = "right 120";
        String speedMessage = "speed?";
        String stopMessage = "stop";
        String takeoffMessage = "takeoff";
        String timeMessage = "time?";

        Message up = Message.decode(upMessage.getBytes(), 0, upMessage.length());
        Message back = Message.decode(backMessage.getBytes(), 0, backMessage.length());
        Message battery = Message.decode(batteryMessage.getBytes(), 0, batteryMessage.length());
        Message command = Message.decode(commandMessage.getBytes(), 0, commandMessage.length());
        Message cw = Message.decode(cwMessage.getBytes(), 0, cwMessage.length());
        Message cww = Message.decode(cwwMessage.getBytes(), 0, cwwMessage.length());
        Message down = Message.decode(downMessage.getBytes(), 0, downMessage.length());
        Message flip = Message.decode(flipMessage.getBytes(), 0, flipMessage.length());
        Message forward = Message.decode(forwardMessage.getBytes(), 0, forwardMessage.length());
        Message go = Message.decode(goMessage.getBytes(), 0, goMessage.length());
        Message land = Message.decode(landMessage.getBytes(), 0, landMessage.length());
        Message left = Message.decode(leftMessage.getBytes(), 0, leftMessage.length());
        Message ok = Message.decode(okMessage.getBytes(), 0, okMessage.length());
        Message right = Message.decode(rightMessage.getBytes(), 0, rightMessage.length());
        Message speed = Message.decode(speedMessage.getBytes(), 0, speedMessage.length());
        Message stop = Message.decode(stopMessage.getBytes(), 0, stopMessage.length());
        Message takeoff = Message.decode(takeoffMessage.getBytes(), 0, takeoffMessage.length());
        Message time = Message.decode(timeMessage.getBytes(), 0, timeMessage.length());

        assertTrue(up instanceof Up);
        assertTrue(back instanceof Back);
        assertTrue(battery instanceof BatteryQuery);
        assertTrue(command instanceof Command);
        assertTrue(cw instanceof Cw);
        assertTrue(cww instanceof Cww);
        assertTrue(down instanceof Down);
        assertTrue(flip instanceof Flip);
        assertTrue(forward instanceof Forward);
        assertTrue(go instanceof Go);
        assertTrue(land instanceof Land);
        assertTrue(left instanceof Left);
        assertTrue(ok instanceof Ok);
        assertTrue(right instanceof Right);
        assertTrue(speed instanceof SpeedQuery);
        assertTrue(stop instanceof Stop);
        assertTrue(takeoff instanceof Takeoff);
        assertTrue(time instanceof TimeQuery);
    }

    @Test
    public void encode() {
        String testCommand = "command";
        byte[] testCommandBytes = testCommand.getBytes();
        Message cmd = Message.decode(testCommandBytes, 0, testCommand.length());
        byte[] result = cmd.encode();

        assertArrayEquals(testCommandBytes, result);

    }

    @Test
    public void isValid() {
        String testCommand = "command";
        byte[] testCommandBytes = testCommand.getBytes();
        Message cmd = Message.decode(testCommandBytes, 0, testCommand.length());

        assertTrue(cmd.isValid());

        String testCommand2 = "commandNOT";
        byte[] testCommandBytes2 = testCommand2.getBytes();
        Message cmd2 = Message.decode(testCommandBytes2, 0, testCommand2.length());

        assertFalse(cmd2.isValid());
    }

    @Test
    public void testToString() {
        String testCommand = "command";
        byte[] testCommandBytes = testCommand.getBytes();
        Message cmd = Message.decode(testCommandBytes, 0, testCommand.length());

        assertEquals("Command command", cmd.toString());
    }

    @Test
    public void testParseIncomingData() {
        String goMsg = "go 10 20 20 5";
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("10", "20", "20", "5"));
        Message goObj = Message.decode(goMsg.getBytes(), 0, goMsg.length());
        ArrayList<String> output = goObj.getPayloadModifiers();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), output.get(i));
        }
    }
}