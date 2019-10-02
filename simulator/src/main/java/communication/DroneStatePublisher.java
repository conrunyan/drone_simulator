package communication;

import connection.DroneConnection;
import messages.Status;
import state.DroneState;

import java.util.ArrayList;

// watches drone state, and updates observers upon
// state update
public class DroneStatePublisher {

    private DroneConnection publisherConnection;
    private DroneState simState;
    private ArrayList<DroneObserver> observers;

    public DroneStatePublisher(DroneConnection simConnect) throws Exception {
        publisherConnection = new DroneConnection();
        publisherConnection.setRemoteIP(simConnect.getLocalIP());
        publisherConnection.setRemotePort(8890);

        observers = new ArrayList<DroneObserver>();
        simState = new DroneState();
    }

    public int getObserverCount() {
        return observers.size();
    }

    public void notifyObservers() throws Exception{
        Status newStatus = new Status(
            simState.getPitch(),
            simState.getRoll(),
            simState.getYaw(),
            simState.getSpeedX(),
            simState.getSpeedY(),
            simState.getSpeedZ(),
            simState.getLowTemperature(),
            simState.getHighTemperature(),
            simState.getFlightDistance(),
            simState.getHeight(),
            simState.getBatteryPercentage(),
            simState.getBarometerMeasurement(),
            simState.getMotorTime(),
            simState.getAccelerationX(),
            simState.getAccelerationY(),
            simState.getAccelerationZ(),
            simState.getPositionX(),
            simState.getPositionY(),
            simState.getPositionZ(),
            simState.getOrientation()
        );
        for (DroneObserver obs : observers) {
            obs.updateState(simState);
        }
        // also send out UDP message with new status
        publisherConnection.sendMessage(newStatus);
    }

    public void updatePublisherState(DroneState state) {
        simState = state;
    }

    public void subscribeNewObserver(DroneObserver newObs) {
        observers.add(newObs);
    }

    public void unsubscribe(DroneObserver obsToRemove) {
        observers.remove(obsToRemove);
    }
}
