package drone;

import connection.DroneConnection;
import messages.Message;
import messages.Status;

public class DroneStatusListener implements Runnable {

    private DroneConnection statusConnection;
    private DroneFlyerState flyerState;
    private Thread statusThread;
    private boolean listening = false;

    public DroneStatusListener(Integer port) {
        try {
            this.statusConnection = new DroneConnection(port);
            listening = true;
            flyerState = DroneFlyerState.getInstance();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            listening = false;
        }
    }

    private void listenForDroneUpdates() throws Exception {

        while (this.listening) {
            Message msg = this.statusConnection.listenForMessage();
            if (msg.getMessageType().equals("status")) {
//                System.out.println("Updating status...: " + msg.getMessageText());
                this.flyerState.updateState((Status)msg);
            }
        }
    }

    @Override
    public void run() {
        try {
            listenForDroneUpdates();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void stop() {
        this.listening = false;
    }
}
