/**
 * Represents a machine in the BattleShip game.
 * A machine has a location represented by a Position object.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */
public class Machine {

	private Position location;

	/**
	 * Constructs a Machine with the specified location.
	 * @param location The initial position of the machine.
	 */
	public Machine(Position location) {
		this.location = location;
	}

	/**
	 * Returns the current location of the machine.
	 * @return The position of the machine.
	 */
	public Position getLocation() {
		return location;
	}

	/**
	 * Move the machine to the north by updating its position.
	 */
	public void moveNorth() {
		location.moveNorth();
	}

	/**
     * Determines if the machine can perform a good attack.
     * This method should be overridden in subclasses to provide specific criteria.
     * @return true if the machine can perform a good attack, false otherwise
     */
    public boolean isGoodAttack() {
        return false;
    }

	/**
     * Determines if the machine is weak.
     * This method should be overridden in subclasses to provide specific criteria.
     * @return true if the machine is weak, false otherwise
     */
    public boolean isWeak() {
        return false;
    }

	/**
	 * Returns true if the assigned pilot is qualified (rank >= 5) and does NOT
	 * belong to the crew of the given aircraft carrier.
	 * This method should be overridden in subclasse Plane.
	 * @return true if the pilot is qualified and not part of the carrier crew, false otherwise
	 */
	public boolean isPilotNotCarrierCrew() {
		return false;
	}

}
