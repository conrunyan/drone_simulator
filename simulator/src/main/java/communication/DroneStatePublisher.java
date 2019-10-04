package communication;

import connection.DroneConnection;
import engine.DroneSimulatorState;
import messages.Status;
import state.DroneState;

import java.util.ArrayList;

// watches drone state, and updates observers upon
// state update
public class DroneStatePublisher implements Runnable {

    private DroneConnection publisherConnection;
    private DroneSimulatorState simState;
    private ArrayList<DroneObserver> observers;
    private boolean running;
    private final int timeBetweenNotifications; // in milliseconds

    public DroneStatePublisher() throws Exception {
        publisherConnection = new DroneConnection();
        publisherConnection.setRemoteIP("127.0.0.1");
        publisherConnection.setRemotePort(8890);

        observers = new ArrayList<>();
        running = true;
        simState = DroneSimulatorState.getInstance();
        timeBetweenNotifications = 500;
    }

    @Override
    public void run() {
        try {
            while (running) {
                notifyObservers();
                Thread.sleep(timeBetweenNotifications);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getObserverCount() {
        return observers.size();
    }

    public void notifyObservers() throws Exception{
        DroneState tmpState = simState.getDroneState();
        Status newStatus = new Status(
            tmpState.getPitch(),
            tmpState.getRoll(),
            tmpState.getYaw(),
            tmpState.getSpeedX(),
            tmpState.getSpeedY(),
            tmpState.getSpeedZ(),
            tmpState.getLowTemperature(),
            tmpState.getHighTemperature(),
            tmpState.getFlightDistance(),
            tmpState.getHeight(),
            tmpState.getBatteryPercentage(),
            tmpState.getBarometerMeasurement(),
            tmpState.getMotorTime(),
            tmpState.getAccelerationX(),
            tmpState.getAccelerationY(),
            tmpState.getAccelerationZ(),
            tmpState.getPositionX(),
            tmpState.getPositionY(),
            tmpState.getPositionZ(),
            tmpState.getOrientation()
        );
        for (DroneObserver obs : observers) {
            obs.updateState(tmpState);
        }
        // also send out UDP message with new status
        publisherConnection.sendMessage(newStatus);
    }

    public void subscribeNewObserver(DroneObserver newObs) {
        observers.add(newObs);
    }

    public void unsubscribe(DroneObserver obsToRemove) {
        observers.remove(obsToRemove);
    }
}
