package state;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneActionHandlerTest {

    @Test
    public void handleMoveBack() {
        DroneState state = new DroneState();
        double expected = state.getPositionY() - 100;
        state.setInCommandMode(true);
        state.setHasTakenOff(true);

        DroneState newState = DroneActionHandler.handleMoveBack(state, "100");

        assertEquals(expected, newState.getPositionY(), .01);
    }
}