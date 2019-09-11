package menu;

import drone.*;

import java.util.Scanner;


public class Menu {

	private Drone drone;
	private Scanner userInput;
	private boolean runMenu;

	/**
	 * Constructor to load drone into menu, and create new command menu items.
	 */
	public Menu(Drone drone) {
		this.drone = drone;
		this.userInput = new Scanner(System.in);
		this.runMenu = true;
	}

	public void menuCycle() throws Exception{
		displayMenu();
		int input = getUserInput();
		executeMenuItem(input);
	}

	private void displayMenu() {
		System.out.println("Drone Menu:");
		System.out.println("Current IP Address: " + this.drone.getDroneIP());
		System.out.println("Current Port: " + this.drone.getDronePort());
		System.out.println("1 - Set IP Address");
		System.out.println("2 - Set Port");
		System.out.println("3 - Connect to drone");
		System.out.println("4 - Run Mission One");
		System.out.println("5 - Run Mission Two");
		System.out.println("6 - Run Mission Three");
		System.out.println("7 - Exit");
	}

	private int getUserInput() {
		int menuItem = 0;
		String input = null;
		System.out.println("Please enter an option: ");
		try {
			input = this.userInput.nextLine();
			menuItem = Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid menu option: " + input + ". Must be an integer.");
		}
		return menuItem;

	}

	private void executeMenuItem(int menuItemID) throws Exception{
		switch (menuItemID) {
			case 1 : {
				System.out.println("Please enter IP address: ");
				String input = this.userInput.nextLine();
				this.drone.setDroneIP(input);
				break;
			}
			case 2 : {
				System.out.println("Please enter Port: ");
				String input = this.userInput.nextLine();
				this.drone.setDronePort(input);
				break;
			}
			case 3 : {
				this.drone.startConnection();
				break;
			}
			case 4 : {
				this.drone.flyMission(1);
				break;
			}
			case 5 : {
				this.drone.flyMission(2);
				break;
			}
			case 6 : {
				this.drone.flyMission(3);
				break;
			}
			case 7 : {
				this.runMenu = false;
				break;
			}
			default : {
				System.out.println("Invalid menu option: " + menuItemID);
				break;
			}
		}
	}

	public boolean getRunMenu() {
		return this.runMenu;
	}

}