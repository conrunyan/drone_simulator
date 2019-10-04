package state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;

public abstract class DroneActionHandler {
     public static DroneState handleMoveBack(DroneState state, String distance) {
         DroneState newState = new DroneState(state);
         newState.move(0, -Double.parseDouble(distance), 0);
         return newState;
     }

     public static DroneState handleCommand(DroneState state, boolean commandToggle) {
         DroneState newState = new DroneState(state);
         newState.setInCommandMode(commandToggle);
         return newState;
     }

     public static DroneState handleCw(DroneState state, String direction) {
         DroneState newState = new DroneState(state);
         newState.rotate(Integer.parseInt(direction));
         return newState;
     }

     public static DroneState handleCww(DroneState state, String direction) {
         DroneState newState = new DroneState(state);
         newState.rotate(-Integer.parseInt(direction));
         return newState;
     }

     public static DroneState handleMoveDown(DroneState state, String distance) {
         DroneState newState = new DroneState(state);
         newState.move(0, 0, -Double.parseDouble(distance));
         return newState;
     }

     public static DroneState handleFlip(DroneState state, String direction) {
         DroneState newState = new DroneState(state);
         String stdDistance = "10";

         // only move up if the battery is not low
         if (!state.lowBattery()) {
            state = handleMoveUp(state, stdDistance);
         }
         if (direction.equals("l")) {
             newState = handleMoveLeft(state, stdDistance);
         }
         else if (direction.equals("r")) {
             newState = handleMoveRight(state, stdDistance);
         }
         else if (direction.equals("b")) {
             newState = handleMoveBack(state, stdDistance);
         }
         else if (direction.equals("f")) {
             newState = handleMoveForward(state, stdDistance);
         }
         else {
             System.out.println(String.format("ERROR: invalid flip direction '%s' in DroneStateHandler", direction));
         }
         return newState;
     }

    public static DroneState handleMoveForward(DroneState state, String distance) {
        DroneState newState = new DroneState(state);
        newState.move(0, Double.parseDouble(distance), 0);
        return newState;
    }

    public static DroneState handleGo(DroneState state, String commands) {
        ArrayList<String> goDirections = new ArrayList<String>(Arrays.asList(commands.split(" ")));
        DroneState newState = new DroneState(state);
        newState.move(
                 Double.parseDouble(goDirections.get(0)),
                 Double.parseDouble(goDirections.get(1)),
                 Double.parseDouble(goDirections.get(2))
         );
        return newState;
    }

    public static DroneState handleLand(DroneState state) {
        DroneState newState = new DroneState(state);
        newState.setHasTakenOff(false);
        return newState;
    }

    public static DroneState handleMoveLeft(DroneState state, String distance) {
        DroneState newState = new DroneState(state);
        newState.move(-Double.parseDouble(distance), 0, 0);
        return newState;
    }

    public static DroneState handleMoveRight(DroneState state, String distance) {
        DroneState newState = new DroneState(state);
        newState.move(Double.parseDouble(distance), 0, 0);
        return newState;
    }

    public static DroneState handleStop(DroneState state) {
        double xPos = state.getPositionX();
        double yPos = state.getPositionY();
        double zPos = state.getPositionZ();
        String args = String.format("%s %s %s 0", xPos, yPos, zPos);

        return handleGo(state, args);
    }

    public static DroneState handleTakeoff(DroneState state) {
        DroneState newState = new DroneState(state);
        newState.setHasTakenOff(true);
        return newState;
    }

    public static DroneState handleMoveUp(DroneState state, String distance) {
        DroneState newState = new DroneState(state);
        newState.move(0, 0, Double.parseDouble(distance));
        return newState;
    }
}
