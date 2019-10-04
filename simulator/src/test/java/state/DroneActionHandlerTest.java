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

        assertEquals(expected, (int)newState.getOrientation());
    }

    @Test
    public void testHandleCww() {
        DroneState state = new DroneState();
        int expected = state.getOrientation() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleCww(state, "100");

        assertEquals(expected, (int)newState.getOrientation());
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

    @Test
    public void testHandleMoveForward() {
        DroneState state = new DroneState();
        double expected = state.getPositionY() + 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveForward(state, "100");

        assertEquals(expected, newState.getPositionY(), .01);
    }

    @Test
    public void testHandleGo() {
        DroneState state = new DroneState();
        double expectedX = state.getPositionX() - 50;
        double expectedY = state.getPositionY() + 100;
        double expectedZ = state.getPositionZ() + 35;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleGo(state, "-50 100 35 20");

        assertEquals(expectedX, newState.getPositionX(), .01);
        assertEquals(expectedY, newState.getPositionY(), .01);
        assertEquals(expectedZ, newState.getPositionZ(), .01);
    }

    @Test
    public void testHandleFlip() {
        DroneState state = new DroneState();
        double expectedXAfterRFlip = state.getPositionX() + 10;
        double expectedXAfterLFlip = state.getPositionX() - 10;
        double expectedXAfterFFlip = state.getPositionY() + 10;
        double expectedXAfterBFlip = state.getPositionY() - 10;
        double expectedZAfterFlipSuccess = state.getPositionZ() + 10;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleFlip(state, "r");
        assertEquals(expectedXAfterRFlip, newState.getPositionX(), .01);
        assertEquals(expectedZAfterFlipSuccess, newState.getPositionZ(), .01);

        newState = DroneActionHandler.handleFlip(state, "l");
        assertEquals(expectedXAfterLFlip, newState.getPositionX(), .01);
        assertEquals(expectedZAfterFlipSuccess, newState.getPositionZ(), .01);

        newState = DroneActionHandler.handleFlip(state, "f");
        assertEquals(expectedXAfterFFlip, newState.getPositionY(), .01);
        assertEquals(expectedZAfterFlipSuccess, newState.getPositionZ(), .01);

        newState = DroneActionHandler.handleFlip(state, "b");
        assertEquals(expectedXAfterBFlip, newState.getPositionY(), .01);
        assertEquals(expectedZAfterFlipSuccess, newState.getPositionZ(), .01);

    }

    @Test
    public void testHandleFlipWithLowBattery() {
        DroneState state = new DroneState();
        double expectedXAfterRFlip = state.getPositionX() + 10;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);
//        state.set
    }

    @Test
    public void testHandleLand() {
        DroneState state = new DroneState();
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleLand(state);

        assertFalse(newState.hasTakenOff());
    }

    @Test
    public void testHandleMoveLeft() {
        DroneState state = new DroneState();
        double expected = state.getPositionX() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveLeft(state, "100");

        assertEquals(expected, newState.getPositionX(), .01);
    }

    @Test
    public void testHandleMoveRight() {
        DroneState state = new DroneState();
        double expected = state.getPositionX() + 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveRight(state, "100");

        assertEquals(expected, newState.getPositionX(), .01);
    }

    @Test
    public void testHandleStop() {
        DroneState state = new DroneState();
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleStop(state);

        assertEquals(state.getPositionX(), newState.getPositionX(), .01);
        assertEquals(state.getPositionY(), newState.getPositionY(), .01);
        assertEquals(state.getPositionZ(), newState.getPositionZ(), .01);
    }

    @Test
    public void testHandleTakeoff() {
        DroneState state = new DroneState();
        state.setInCommandMode(true);

        DroneState newState = DroneActionHandler.handleTakeoff(state);

        assertFalse(state.hasTakenOff());
        assertTrue(newState.hasTakenOff());
    }

    @Test
    public void testHandleMoveUp() {
        DroneState state = new DroneState();
        double expected = state.getPositionZ() + 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveUp(state, "100");
        assertEquals(expected, newState.getPositionZ(), .01);
    }


}