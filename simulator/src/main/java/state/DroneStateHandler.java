package state;

import messages.Message;

public abstract class DroneStateHandler {

    public static DroneState handleMessage(Message msg, DroneState state) {



        return state;
    }
}
