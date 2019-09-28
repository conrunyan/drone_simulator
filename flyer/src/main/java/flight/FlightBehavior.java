package flight;

import messages.Message;

public interface FlightBehavior {

	Message flyInDirection();
	void displayBehaviorName();

}