package menu;

import drone.*;


public class Menu {

	private MenuConnectToDrone m_menuItemDroneConnection;
	private Drone drone;
	private DroneConnection droneConnect;

	/**
	 * Constructor to load drone into menu, and create new command menu items.
	 */
	public Menu(Drone drone, DroneConnection droneConnection) {
		// TODO - implement Menu.Menu
		this.drone = drone;
		this.droneConnect = droneConnection;
		m_menuItemDroneConnection = new MenuConnectToDrone();
	}

	public void displayMenu() {
		// TODO - implement Menu.displayMenu
		System.out.println("Drone Menu:");
		System.out.println("Current IP Address: " + this.droneConnect.getInputConnectionIP());
		System.out.println("Current Port: " + this.droneConnect.getInputConnectionPort());
		System.out.println("1 - Set IP Address");
		System.out.println("Drone Menu:");
		System.out.println("Drone Menu:");

		throw new UnsupportedOperationException();
	}

	public String getUserInput() {
		// TODO - implement Menu.getUserInput
		throw new UnsupportedOperationException();
	}

	public void executeMenuItem(int menuItemID) {
		// TODO - implement Menu.executeMenuItem
		throw new UnsupportedOperationException();
	}

}