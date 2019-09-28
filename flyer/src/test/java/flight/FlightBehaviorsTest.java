package flight;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlightBehaviorsTest {
    @Test
    public void testFlightReturns() {
        FlyBackward flyBack = new FlyBackward(100);
        FlyForward flyFwd = new FlyForward(100);
        FlyLeft flyLeft = new FlyLeft(100);
        FlyRight flyRight = new FlyRight(100);
        FlyLiftOff flyLiftOff = new FlyLiftOff();
        FlyLand flyLand = new FlyLand();
        FlyFlip flyFlip = new FlyFlip("b");

        flyBack.displayBehaviorName();
        assertEquals(flyBack.flyInDirection().getMessageText(), "back 100");
        flyFwd.displayBehaviorName();
        assertEquals(flyFwd.flyInDirection().getMessageText(), "forward 100");
        flyLeft.displayBehaviorName();
        assertEquals(flyLeft.flyInDirection().getMessageText(), "left 100");
        flyRight.displayBehaviorName();
        assertEquals(flyRight.flyInDirection().getMessageText(), "right 100");
        flyLiftOff.displayBehaviorName();
        assertEquals(flyLiftOff.flyInDirection().getMessageText(), "takeoff");
        flyLand.displayBehaviorName();
        assertEquals(flyLand.flyInDirection().getMessageText(), "land");
        flyFlip.displayBehaviorName();
        assertEquals(flyFlip.flyInDirection().getMessageText(), "flip b");

    }
}
