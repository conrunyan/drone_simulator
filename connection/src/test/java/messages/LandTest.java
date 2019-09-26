package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class LandTest {

    @Test
    public void getKeyWord() {
        assertEquals("land", Land.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Land bq = new Land("land");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Land bq = new Land("land");
        assertEquals("land", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Land bq = new Land("land awef aw3f");
        assertNull(bq.getMessageText());
    }
}