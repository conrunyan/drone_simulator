package communication;

import connection.DroneConnection;
import state.DroneState;
import communication.DroneObserver;
import java.util.ArrayList;

// watches drone state, and updates observers upon
// state update
public class DroneStatePublisher {

    private DroneConnection publisherConnection;
    private DroneState simState;
    private ArrayList<DroneObserver> observers;

    public DroneStatePublisher(DroneConnection simConnect) throws Exception {
        publisherConnection = new DroneConnection();
        publisherConnection.setInputConnectionIP(simConnect.getInputConnectionIP());
        publisherConnection.setInputConnectionPort("8890");
    }

    public void notifyObservers() {
        for (DroneObserver obs : observers) {
            // TODO: notify observers here
        }
    }

    public void subsribeNewObserver(DroneObserver newObs) {
        observers.add(newObs);
    }

    public void unsubscribe(DroneObserver obsToRemove) {
        observers.remove(obsToRemove);
    }
}
