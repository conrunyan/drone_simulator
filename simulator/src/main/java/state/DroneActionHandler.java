package state;

import java.util.ArrayList;

public class DroneActionHandler {
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
         if (state.lowBattery()) {
             if (direction.equals("l")) {
                 // TODO: go left
             }
             else if (direction.equals("r")) {
                 // TODO: go left
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
         }
         return newState;
     }

    public static DroneState handleMoveForward(DroneState state, String distance) {
        DroneState newState = new DroneState(state);
        newState.move(0, Double.parseDouble(distance), 0);
        return newState;
    }

    public static DroneState handleGo(DroneState state, ArrayList<String> goDirections) {
        DroneState newState = new DroneState(state);
        newState.move(
                 Double.parseDouble(goDirections.get(0)),
                 Double.parseDouble(goDirections.get(1)),
                 Double.parseDouble(goDirections.get(2))
         );
        return newState;
    }
}
