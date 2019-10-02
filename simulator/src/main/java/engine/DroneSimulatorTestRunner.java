package engine;

public class DroneSimulatorTestRunner implements Runnable {

    private DroneSimulator sim;
    public DroneSimulatorTestRunner(Integer port) {
        sim = new DroneSimulator(port);
    }

    @Override
    public void run() {
        try {
            this.sim.runSimulator();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void killThread() {
        Thread.currentThread().interrupt();
    }
}
