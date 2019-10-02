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
        Message error = Message.decode("INVALID MESSAGE".getBytes(), 0, "INVALID MESSAGE".length());

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
        assertEquals(10, (int)state.getOrientation());

        state = DroneStateHandler.handleMessage(cww, state);
        assertEquals(0, (int)state.getOrientation());

        state = DroneStateHandler.handleMessage(down, state);
        assertEquals(-10, state.getPositionZ(), 0.01);

        state = DroneStateHandler.handleMessage(flip, state);
        assertEquals(-10, state.getPositionX(), 0.01);

        state = DroneStateHandler.handleMessage(forward, state);
        assertEquals(10, state.getPositionY(), 0.01);

        state = DroneStateHandler.handleMessage(go, state);
        assertEquals(0, state.getPositionX(), 0.01);
        assertEquals(20, state.getPositionY(), 0.01);
        assertEquals(0, state.getPositionZ(), 0.01);

        state = DroneStateHandler.handleMessage(land, state);
        assertFalse(state.hasTakenOff());

        state = DroneStateHandler.handleMessage(takeoff, state);
        assertTrue(state.hasTakenOff());

        state = DroneStateHandler.handleMessage(left, state);
        assertEquals(-10, state.getPositionX(), 0.01);

        state = DroneStateHandler.handleMessage(right, state);
        assertEquals(0, state.getPositionX(), 0.01);

        DroneState tmpState = new DroneState(state);
        state = DroneStateHandler.handleMessage(stop, state);
        assertEquals(tmpState.getPositionX(), state.getPositionX(), 0.01);
        assertEquals(tmpState.getPositionY(), state.getPositionY(), 0.01);
        assertEquals(tmpState.getPositionZ(), state.getPositionZ(), 0.01);

        state = DroneStateHandler.handleMessage(up, state);
        assertEquals(10, state.getPositionZ(), 0.01);

        state = DroneStateHandler.handleMessage(error, state);

    }
}