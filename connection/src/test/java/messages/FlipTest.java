package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlipTest {

    @Test
    public void getKeyWord() {
        assertEquals("flip", Flip.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Flip bq = new Flip("flip 180");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageTextL() {
        Flip bq = new Flip("flip l");
        assertEquals("flip l", bq.getMessageText());
    }
    @Test
    public void getMessageTextR() {
        Flip bq = new Flip("flip r");
        assertEquals("flip r", bq.getMessageText());
    }
    @Test
    public void getMessageTextU() {
        Flip bq = new Flip("flip f");
        assertEquals("flip f", bq.getMessageText());
    }
    @Test
    public void getMessageTextD() {
        Flip bq = new Flip("flip b");
        assertEquals("flip b", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Flip bq = new Flip("flip q");
        assertNull(bq.getMessageText());
    }
}