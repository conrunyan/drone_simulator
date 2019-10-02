package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class CwwTest {

    @Test
    public void getKeyWord() {
        assertEquals("cww ", Cww.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Cww bq = new Cww("cww 180");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Cww bq = new Cww("cww 180");
        assertEquals("cww 180", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Cww bq = new Cww("cwX 180");
        assertNull(bq.getMessageText());
    }
}