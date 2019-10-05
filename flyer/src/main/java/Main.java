import drone.Drone;
import drone.DroneFlyerState;
import drone.DroneStatusListener;
import menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Drone testDrone = new Drone();
        DroneStatusListener droneListener = new DroneStatusListener(8890);
        DroneFlyerState flyerState = DroneFlyerState.getInstance();
        String file = "droneConfig.properties";
        System.out.println(System.getProperty("user.dir"));
        Menu menu = new Menu(testDrone, file);

        Thread t = new Thread(droneListener, "drone_listener");
        t.start();

        while (menu.getRunMenu()) {
            menu.menuCycle();
        }

        t.interrupt();
    }
}
