package drone;

import org.junit.Test;
import static org.junit.Assert.*;


public class DroneConnectionTest {

    @Test
    public void testDroneConnectionSplitIP() {
        DroneConnection testDrone = new DroneConnection();
        byte[] expectedIP = new byte[] { (byte) 192, (byte) 168, (byte) 10, (byte) 1};

        String userInputIP = "192.168.10.1";

        byte[] result = testDrone.splitIP(userInputIP);

        assertArrayEquals(result, expectedIP);
    }

}
