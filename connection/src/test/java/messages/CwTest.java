package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class CwTest {

    @Test
    public void getKeyWord() {
        assertEquals("cw ", Cw.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Cw bq = new Cw("cw 180");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Cw bq = new Cw("cw 180");
        assertEquals("cw 180", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Cw bq = new Cw("cwX 180");
        assertNull(bq.getMessageText());
    }
}