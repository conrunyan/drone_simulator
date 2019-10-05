package drone;

import messages.Message;
import messages.Status;
import state.DroneActionHandler;
import state.DroneState;
import state.DroneStateHandler;

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
//        System.out.println("UPDATING FLYER STATUS!");
        flyerState.updateFlyingInfo(status);
    }

    public void updateState(Message msg) {
        flyerState = DroneStateHandler.handleMessage(msg, flyerState);
    }



}
