package communication;

import state.DroneState;

public class DroneObserver {

    private DroneState state;
    private boolean subscribed;

    public DroneObserver() {
        state = new DroneState();
    }

    public void updateState(DroneState newState) {
        this.state = newState;
    }

    public DroneState getDroneState() {
        return state;
    }
}
