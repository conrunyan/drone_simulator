package engine;

import communication.DroneStatePublisher;
import connection.DroneConnection;
import messages.Message;
import state.DroneState;
import state.DroneStateHandler;

public class DroneSimulator {

    private DroneState simState;
    private DroneConnection simConnection;
    private DroneStatePublisher simPublisher;

    public DroneSimulator() throws Exception{
        final String simIPAddr = "127.0.0.1";
        final String simPort = "8889";
        simState = new DroneState();
        simConnection = new DroneConnection();
        simConnection.setLocalIP(simIPAddr);
        simConnection.setLocalPort(simPort);
        simPublisher = new DroneStatePublisher(simConnection);
    }

    public DroneState getSimState() {
        return new DroneState(simState);
    }

    public void startSimulator() {
        while (true) {
            try{
                Message msg = simConnection.listenForMessage();
                executeRequest(msg);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // parses message and handles updating the state as
    // needed.
    void executeRequest(Message msg) {
        System.out.println("Drone Simulator: Received message " + msg);
        simState = DroneStateHandler.handleMessage(msg, simState);
        updateStatus();
    }

    void sendResponse(Message msg) {
        if (msg.getMessageType().equals("failure")) {
            // TODO: Send error message
        }
        else {
            // TODO: Send ok message
        }
    }

    // triggers event, causing drone state publisher
    // to notify observers and update info
    private void updateStatus() {
        simPublisher.updatePublisherState(simState);
        try {
            simPublisher.notifyObservers();
        }
        catch (Exception e) {
            System.out.println("ERROR: DroneSimulator - updateStatus - " + e.getMessage());
        }
    }
}
