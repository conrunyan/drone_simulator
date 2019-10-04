import communication.DroneStatePublisher;
import engine.DroneSimulator;
import engine.DroneSimulatorState;

public class Main {

    public static void main(String[] args) {
        DroneSimulatorState simulatorState = DroneSimulatorState.getInstance();
        DroneSimulator sim = new DroneSimulator(8889);
        try {
            DroneStatePublisher simPublisher = new DroneStatePublisher();
            Thread t = new Thread(simPublisher, "simulator_status_publisher");
            t.start();
            sim.runSimulator();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
