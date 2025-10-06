/**
 * The Machine class represents a general machine in the battleship game.
 * A machine has a Position that indicates its current location
 * on the game board.
 * 
 * This class serves as a parent class for other types of machines,
 * such as Ship and Plane, which extend its functionality.
 * 
 * @author Juan Daniel Bogotá
 * @author Nicolas Felipe Bernal
 * @version 1.0
 */

public class Machine {

	private Position location;

	public Machine(Position location) {
		this.location = location;
	}

	/**
	 * Gets the current location of the machine.
	 * @return the Position object representing the machine's location
	 */
	public Position getLocation() {
		return location;
	}

	/**
	 * Moves the machine to a new location.
	 * @param location the new Position to move the machine to
	 */
	public void moveTo(Position location) {
        this.location = location;
    }

}
