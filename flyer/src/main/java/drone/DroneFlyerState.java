package drone;

import messages.Status;
import state.DroneState;

public class DroneFlyerState {
    private static DroneFlyerState instance = null;
    private DroneState flyerState = new DroneState();

    public static DroneFlyerState getInstance() {
        if (instance == null) {
            instance = new DroneFlyerState();
        }
        return instance;
    }

    public DroneState getDroneState() {
        return flyerState;
    }

    public void updateState(Status status) {
        flyerState.updateFlyingInfo(status);
    }



}
