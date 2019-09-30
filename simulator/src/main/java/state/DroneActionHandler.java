package state;

import java.util.ArrayList;

public class DroneActionHandler {
     public static DroneState handleMoveBack(DroneState state, String distance) {
         DroneState newState = new DroneState(state);
         newState.move(0, -Double.parseDouble(distance), 0);
         return newState;
     }

     public static void handleCommand(DroneState state, boolean commandToggle) {
         state.setInCommandMode(commandToggle);
     }

     public static void handleCw(DroneState state, String direction) {
         state.rotate(Integer.parseInt(direction));
     }

     public static void handleCww(DroneState state, String direction) {
         state.rotate(-Integer.parseInt(direction));
     }

     public static void handleMoveDown(DroneState state, String distance) {
         state.move(0, 0, Double.parseDouble(distance));
     }

     public static void handleFlip(DroneState state, String direction) {
         String stdDistance = "10";
         if (state.lowBattery()) {
             if (direction.equals("l")) {
                 // TODO: go left
             }
             else if (direction.equals("r")) {
                 // TODO: go left
             }
             else if (direction.equals("b")) {
                 handleMoveBack(state, stdDistance);
             }
             else if (direction.equals("f")) {
                 handleMoveForward(state, stdDistance);
             }
             else {
                 System.out.println(String.format("ERROR: invalid flip direction '%s' in DroneStateHandler", direction));
             }
         }
     }

    public static void handleMoveForward(DroneState state, String distance) {
        state.move(0, Double.parseDouble(distance), 0);
    }

    public static void handleGo(DroneState state, ArrayList<String> goDirections) {
         state.move(
                 Double.parseDouble(goDirections.get(0)),
                 Double.parseDouble(goDirections.get(1)),
                 Double.parseDouble(goDirections.get(2))
         );
    }
}
