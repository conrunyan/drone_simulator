package drone;

import messages.Status;
import state.DroneState;

public class DroneFlyerState {
    private static DroneFlyerState instance = null;
    private DroneState flyerState;

    private DroneFlyerState() {
        flyerState = new DroneState();
    }

    public static DroneFlyerState getInstance() {
        if (instance == null) {
            instance = new DroneFlyerState();
        }
        return instance;
    }

    public DroneState getDroneState() {
        return this.flyerState;
    }

    public void updateState(Status status) {
        flyerState.updateFlyingInfo(status);
    }



}
