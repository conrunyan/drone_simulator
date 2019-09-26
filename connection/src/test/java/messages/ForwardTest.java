package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class ForwardTest {

    @Test
    public void getKeyWord() {
        assertEquals("forward", Forward.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Forward bq = new Forward("forward 180");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Forward bq = new Forward("forward 180");
        assertEquals("forward 180", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Forward bq = new Forward("cwX 180");
        assertNull(bq.getMessageText());
    }
}