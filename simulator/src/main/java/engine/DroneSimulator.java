package engine;

import communication.DroneStatePublisher;
import connection.DroneConnection;
import messages.Message;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import state.DroneState;
import state.DroneStateHandler;

public class DroneSimulator {

    private DroneState simState;
    private DroneConnection simConnection;
    private DroneStatePublisher simPublisher;
    private boolean running = false;

    public DroneSimulator(Integer port) {
        final String simIPAddr = "127.0.0.1";
        final Integer simPort = port;
        simState = new DroneState();
        try {
            simConnection = new DroneConnection(simPort);
            simConnection.setLocalIP(simIPAddr);
            simConnection.setLocalPort(simPort.toString());
            simPublisher = new DroneStatePublisher(simConnection);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public DroneState getSimState() {
        return new DroneState(simState);
    }

    public void runSimulator() {
        running = true;
        while (running) {
            try{
                System.out.println("Waiting for input...");
                Message msg = simConnection.listenForMessage();
                executeRequest(msg);
                sendResponse(msg);
                updateStatus();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void stopSimulator() {
        running = false;
    }

    // parses message and handles updating the state as
    // needed.
    void executeRequest(Message msg) {
        System.out.println("Drone Simulator: Received message " + msg);
        simState = DroneStateHandler.handleMessage(msg, simState);
    }

    void sendResponse(Message msg) {
        Message outMsg;
        DroneConnection tmpConn = null;
        try {
            tmpConn = new DroneConnection();
            tmpConn.setRemoteIP(msg.getRemoteIPAddr());
            tmpConn.setRemotePort(msg.getRemotePort());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


        if (msg.getMessageType().equals("failure")) {
            outMsg = Message.decode("error".getBytes(), 0, "error".length());
        }
        else {
            outMsg = Message.decode("ok".getBytes(), 0, "ok".length());
        }

        try {
            tmpConn.sendMessage(outMsg);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
