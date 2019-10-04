package state;

import org.junit.Test;

import static org.junit.Assert.*;

public class DroneStateTest {

    @Test
    public void isInCommandMode() {
        DroneState state = new DroneState();
        assertFalse(state.isInCommandMode());
        state.setInCommandMode(true);
        assertTrue(state.isInCommandMode());
    }

    @Test
    public void hasTakenOff() {
        DroneState state = new DroneState();
        assertFalse(state.hasTakenOff());
        state.setInCommandMode(true);
        state.setHasTakenOff(true);
        assertTrue(state.hasTakenOff());
    }

    @Test
    public void lowBattery() {
        DroneState state = new DroneState();
        assertFalse(state.lowBattery());
        int numTimesToLoopUntilBatteryIsLow = 80;

        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        for (int i = 0; i < numTimesToLoopUntilBatteryIsLow; i++) {
            state.rotate(1);
        }

        assertTrue(state.lowBattery());
    }

    @Test
    public void isVideoStreamOn() {
        DroneState state = new DroneState();
        assertFalse(state.isVideoStreamOn());
        state.setInCommandMode(true);
        state.setVideoStreamOn(true);
        assertTrue(state.isVideoStreamOn());
    }

    @Test
    public void getCurrentFlightTime() {
        DroneState state = new DroneState();
        state.setInCommandMode(true);
        assertEquals(0, state.getCurrentFlightTime(), 0.01);

        state.setCurrentFlightTime(20d);
        assertEquals(20d, state.getCurrentFlightTime(), 0.01);
    }

    @Test
    public void updateFlyingInfo() {
    }

    @Test
    public void move() {
        DroneState state = new DroneState();
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        state.move(1,0,0);
        assertEquals(1, state.getPositionX(), 0.01);

        state.move(0,1,0);
        assertEquals(1, state.getPositionY(), 0.01);

        state.move(0,0,1);
        assertEquals(1, state.getPositionZ(), 0.01);
    }

    @Test
    public void rotate() {
        DroneState state = new DroneState();
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        assertEquals((Integer)0, state.getOrientation());

        state.rotate(50);
        assertEquals((Integer)50, state.getOrientation());

        state.rotate(-50);
        assertEquals((Integer)0, state.getOrientation());

    }
}