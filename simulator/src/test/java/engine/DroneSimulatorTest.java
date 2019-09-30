package engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class DroneSimulatorTest {

    @Test
    public void testExecuteRequest() throws Exception{
        DroneSimulator sim = new DroneSimulator();
        sim.getSimState();
    }
}