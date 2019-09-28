package communication;

import connection.DroneConnection;
import messages.Status;
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
            simState.getAccelerationZ()
        );
        for (DroneObserver obs : observers) {

            publisherConnection.sendMessage(newStatus);
        }
    }

    public void subsribeNewObserver(DroneObserver newObs) {
        observers.add(newObs);
    }

    public void unsubscribe(DroneObserver obsToRemove) {
        observers.remove(obsToRemove);
    }
}
