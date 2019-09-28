import drone.Drone;
import menu.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Drone testDrone = new Drone();
        Menu menu = new Menu(testDrone);

        while (menu.getRunMenu()) {
            menu.menuCycle();
        }
    }
}