package state;

import engine.DroneSimulator;
import messages.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneStateHandlerTest {

    @Test
    public void testHandleMessage() throws Exception{
        Message back = Message.decode("back 10".getBytes(), 0, "back 10".length());
        Message command = Message.decode("command".getBytes(), 0, "command".length());
        Message cw = Message.decode("cw 10".getBytes(), 0, "cw 10".length());
        Message cww = Message.decode("cww 10".getBytes(), 0, "cww 10".length());
        Message down = Message.decode("down 10".getBytes(), 0, "down 10".length());
        Message flip = Message.decode("flip l".getBytes(), 0, "flip l".length());
        Message forward = Message.decode("forward 10".getBytes(), 0, "forward 10".length());
        Message go = Message.decode("go 10 10 10 10".getBytes(), 0, "go 10 10 10 10".length());
        Message land = Message.decode("land".getBytes(), 0, "land".length());
        Message left = Message.decode("left 10".getBytes(), 0, "left 10".length());
        Message right = Message.decode("right 10".getBytes(), 0, "right 10".length());
        Message stop = Message.decode("stop".getBytes(), 0, "stop".length());
        Message takeoff = Message.decode("takeoff".getBytes(), 0, "takeoff".length());
        Message up = Message.decode("up 10".getBytes(), 0, "up 10".length());

        DroneState state = new DroneState();
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        state = DroneStateHandler.handleMessage(back, state);
        assertEquals(-10.0, state.getPositionY(), .01);

        state.setHasTakenOff(false);
        state.setInCommandMode(false);

        state = DroneStateHandler.handleMessage(command, state);
        assertTrue(state.isInCommandMode());

        state.setHasTakenOff(true);

        state = DroneStateHandler.handleMessage(cw, state);
        assertEquals(10, state.getOrientation());

        state = DroneStateHandler.handleMessage(cww, state);
        assertEquals(0, state.getOrientation());

    }
}