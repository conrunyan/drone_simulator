package flight;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlightBehaviorsTest {
    @Test
    public void testFlightReturns() {
        FlyBackward flyBack = new FlyBackward();
        FlyForward flyFwd = new FlyForward();
        FlyLeft flyLeft = new FlyLeft();
        FlyRight flyRight = new FlyRight();
        FlyLiftOff flyLiftOff = new FlyLiftOff();
        FlyLand flyLand = new FlyLand();
        FlyFlip flyFlip = new FlyFlip();

        flyBack.displayBehaviorName();
        assertEquals(flyBack.flyInDirection(), "back 100");
        flyFwd.displayBehaviorName();
        assertEquals(flyFwd.flyInDirection(), "forward 100");
        flyLeft.displayBehaviorName();
        assertEquals(flyLeft.flyInDirection(), "left 100");
        flyRight.displayBehaviorName();
        assertEquals(flyRight.flyInDirection(), "right 100");
        flyLiftOff.displayBehaviorName();
        assertEquals(flyLiftOff.flyInDirection(), "takeoff");
        flyLand.displayBehaviorName();
        assertEquals(flyLand.flyInDirection(), "land");
        flyFlip.displayBehaviorName();
        assertEquals(flyFlip.flyInDirection(), "flip b");

    }
}
