package engine;

import messages.Status;
import state.DroneState;

public class DroneSimulatorState {
    private static DroneSimulatorState instance = null;
    private DroneState flyerState;

    private DroneSimulatorState() {
        flyerState = new DroneState();
    }

    public static DroneSimulatorState getInstance() {
        if (instance == null) {
            instance = new DroneSimulatorState();
        }
        return instance;
    }

    public DroneState getDroneState() {
        return this.flyerState;
    }

    public void updateState(Status status) {
        System.out.println("UPDATING FLYER STATUS!");
        flyerState.updateFlyingInfo(status);
    }

    public void updateState(DroneState state) {
        flyerState = new DroneState(state);
    }


}
