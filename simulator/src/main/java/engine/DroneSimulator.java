package engine;

import communication.DroneStatePublisher;
import connection.DroneConnection;
import messages.Message;
import state.DroneState;

public class DroneSimulator {

    private DroneState simState;
    private DroneConnection simConnection;
    private DroneStatePublisher simPublisher;

    public DroneSimulator() throws Exception{
        final String simIPAddr = "127.0.0.1";
        final String simPort = "8889";
        simState = new DroneState();
        simConnection = new DroneConnection();
        simConnection.setInputConnectionIP(simIPAddr);
        simConnection.setInputConnectionPort(simPort);
        simPublisher = new DroneStatePublisher(simConnection);
    }

    public void startSimulator() {

    }

    // parses message and handles updating the state as
    // needed.
    private void executeRequest(Message msg) {

    }

    // triggers event, causing drone state publisher
    // to notify observers and update info
    private void updateStatus(Message msg) {

    }
}
