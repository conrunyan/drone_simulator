package communication;

import org.junit.Test;
import state.DroneState;

import static org.junit.Assert.*;

public class DroneObserverTest {

    @Test
    public void testConstructor() {
        DroneObserver testObs = new DroneObserver();
        DroneState testState = testObs.getDroneState();
        assertFalse(testState.hasTakenOff());
    }

    @Test
    public void testUpdateState() {
        DroneObserver testObs = new DroneObserver();
        DroneState otherState = new DroneState();

        otherState.setInCommandMode(true);
        testObs.updateState(otherState);
        DroneState resultState = testObs.getDroneState();

        assertTrue(testObs.getDroneState().isInCommandMode());
    }
}