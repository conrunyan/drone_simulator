package engine;

import communication.DroneStatePublisher;
import connection.DroneConnection;
import messages.Message;
import state.DroneState;
import state.DroneStateHandler;

public class DroneSimulator {

    private DroneSimulatorState simState;
    private DroneConnection simConnection;
    private boolean running = false;

    public DroneSimulator(Integer port) {
        final String simIPAddr = "127.0.0.1";
        simState = DroneSimulatorState.getInstance();
        try {
            simConnection = new DroneConnection(port);
            simConnection.setLocalIP(simIPAddr);
            simConnection.setLocalPort(port.toString());
            simConnection.setVerboseOutput(true);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public DroneState getSimState() {
        return new DroneState(simState.getDroneState());
    }

    public void runSimulator() {
        running = true;
        while (running) {
            try{
                System.out.println("Waiting for input...");
                Message msg = simConnection.listenForMessage();
                executeRequest(msg);
                sendResponse(msg);
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
        simState.updateState(DroneStateHandler.handleMessage(msg, simState.getDroneState()));
    }

    void sendResponse(Message msg) {
        Message outMsg;
        DroneConnection tmpConn = null;
        try {
            tmpConn = new DroneConnection();
            tmpConn.setRemoteIP(msg.getRemoteIPAddr());
            tmpConn.setRemotePort(msg.getRemotePort());
            tmpConn.setVerboseOutput(true);
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
}
