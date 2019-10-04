package engine;

import messages.Message;
import messages.Status;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DroneSimulatorStateTest {

    @Test
    public void getInstance() {
        assertNotNull(DroneSimulatorState.getInstance());
    }

    @Test
    public void getDroneState() {
        DroneSimulatorState flyer = DroneSimulatorState.getInstance();
        assertNotNull(flyer.getDroneState());
    }

    @Test
    public void updateState() {
        String msg = "mid:-1;x:0;y:0;z:0;mpry:0,0,0;pitch:0;roll:0;yaw:0;vgx:0;vgy:0;vgz:0;templ:0;temph:0;tof:0;h:0;bat:0;baro:0.00;time:0;agx:0.00;agy:3.14;agz:0.00;posx:0.00;posy:0.00;posz:0.00;ornt:0";
        Status stat = (Status) Message.decode(msg.getBytes(), 0, msg.length());
        DroneSimulatorState flyer = DroneSimulatorState.getInstance();
        double expectedAgy = 3.14;
        flyer.getDroneState().setInCommandMode(true);
        flyer.getDroneState().setHasTakenOff(true);

        flyer.updateState(stat);
        double result = flyer.getDroneState().getAccelerationY();

        assertEquals(expectedAgy, result, 0.01);
    }
}