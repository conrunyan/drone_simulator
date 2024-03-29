package state;

import messages.*;

import java.util.ArrayList;

public abstract class DroneStateHandler {

    public static DroneState handleMessage(Message msg, DroneState state) {

        ArrayList<String> payloadModifiers = msg.getPayloadModifiers();
        String data = msg.getMessageText();
        DroneState newState = new DroneState(state);

        if (data == null) {
            return newState;
        }

        // special conditions for drone behavior
        if (state.lowBattery()) {
            newState = DroneActionHandler.handleLand(newState);
            return newState;
        }

        if (state.getHighTemperature() >= state.getMaxTemperature()) {
            newState = DroneActionHandler.handleLand(newState);
            return newState;
        }

        if (data.startsWith(Back.getKeyWord()))
            newState = DroneActionHandler.handleMoveBack(newState, payloadModifiers.get(0));
        else if (data.startsWith(Command.getKeyWord()))
            newState = DroneActionHandler.handleCommand(newState, true);
        else if (data.startsWith(Cw.getKeyWord()))
            newState = DroneActionHandler.handleCw(newState, payloadModifiers.get(0));
        else if (data.startsWith(Cww.getKeyWord()))
            newState = DroneActionHandler.handleCww(newState, payloadModifiers.get(0));
        else if (data.startsWith(Down.getKeyWord()))
            newState = DroneActionHandler.handleMoveDown(newState, payloadModifiers.get(0));
        else if (data.startsWith(Flip.getKeyWord()))
            newState = DroneActionHandler.handleFlip(newState, payloadModifiers.get(0));
        else if (data.startsWith(Forward.getKeyWord()))
            newState = DroneActionHandler.handleMoveForward(newState, payloadModifiers.get(0));
        else if (data.startsWith(Go.getKeyWord())) {
            String args = String.format(
                    "%s %s %s 0",
                    payloadModifiers.get(0),
                    payloadModifiers.get(1),
                    payloadModifiers.get(2)
            );
            newState = DroneActionHandler.handleGo(newState, args);
        }
        else if (data.startsWith(Land.getKeyWord()))
            newState = DroneActionHandler.handleLand(newState);
        else if (data.startsWith(Left.getKeyWord()))
            newState = DroneActionHandler.handleMoveLeft(newState, payloadModifiers.get(0));
        else if (data.startsWith(Right.getKeyWord()))
            newState = DroneActionHandler.handleMoveRight(newState, payloadModifiers.get(0));
        else if (data.startsWith(Stop.getKeyWord()))
            newState = DroneActionHandler.handleStop(newState);
        else if (data.startsWith(Takeoff.getKeyWord()))
            newState = DroneActionHandler.handleTakeoff(newState);
        else if (data.startsWith(Up.getKeyWord()))
            newState = DroneActionHandler.handleMoveUp(newState, payloadModifiers.get(0));
        else {
            System.out.println("ERROR: Invalid request to modify drone state: " + msg);
        }
        return newState;
    }
}
