package communication;

import connection.DroneConnection;
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

        conn.setLocalIP("127.0.0.1");
        conn.setLocalPort("5005");
        listener.setLocalPort(conn.getLocalPort());
        DroneStatePublisher pub = new DroneStatePublisher(conn);
        pub.subscribeNewObserver(obs);
        listener.setLocalPort(conn.getLocalPort());
        listener.setLocalIP(conn.getLocalIP());

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