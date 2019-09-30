package state;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneActionHandlerTest {

    @Test
    public void testHandleMoveBack() {
        DroneState state = new DroneState();
        double expected = state.getPositionY() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveBack(state, "100");

        assertEquals(expected, newState.getPositionY(), .01);
    }

    @Test
    public void testHandleCommand() {
        DroneState state = new DroneState();

        DroneState newState = DroneActionHandler.handleCommand(state, true);

        assertFalse(state.isInCommandMode());
        assertTrue(newState.isInCommandMode());
    }

    @Test
    public void testHandleCw() {
        DroneState state = new DroneState();
        int expected = state.getOrientation() + 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleCw(state, "100");

        assertEquals(expected, newState.getOrientation());
    }

    @Test
    public void testHandleCww() {
        DroneState state = new DroneState();
        int expected = state.getOrientation() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleCww(state, "100");

        assertEquals(expected, newState.getOrientation());
    }

    @Test
    public void testHandleMoveDown() {
        DroneState state = new DroneState();
        double expected = state.getPositionZ() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveDown(state, "100");

        assertEquals(expected, newState.getPositionZ(), .01);
    }
}