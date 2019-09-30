package communication;

import connection.DroneConnection;
import messages.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneStatePublisherTest {

    @Test
    public void testUnsubscribe() throws Exception {
        DroneConnection conn = new DroneConnection();
        DroneObserver obs = new DroneObserver();
        DroneStatePublisher pub = new DroneStatePublisher(conn);

        pub.subscribeNewObserver(obs);
        int len = pub.getObserverCount();
        pub.unsubscribe(obs);

        assertEquals(1, len);
        assertEquals(0, pub.getObserverCount());
    }

    @Test
    public void notifyObservers() throws Exception{
        DroneConnection conn = new DroneConnection();
        DroneConnection listener = new DroneConnection();
        DroneObserver obs = new DroneObserver();

        conn.setInputConnectionIP("127.0.0.1");
        conn.setInputConnectionPort("5005");
        listener.setInputConnectionPort(conn.getInputConnectionPort());
        DroneStatePublisher pub = new DroneStatePublisher(conn);
        pub.subscribeNewObserver(obs);
        listener.setInputConnectionPort(conn.getInputConnectionPort());
        listener.setInputConnectionIP(conn.getInputConnectionIP());

        pub.notifyObservers();
    }

    @Test
    public void subscribeNewObserver() throws Exception {
        DroneConnection conn = new DroneConnection();
        DroneObserver obs = new DroneObserver();
        DroneStatePublisher pub = new DroneStatePublisher(conn);

        pub.subscribeNewObserver(obs);
        int len = pub.getObserverCount();
    }
}