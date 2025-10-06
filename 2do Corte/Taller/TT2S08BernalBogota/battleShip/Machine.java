
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
     * Determines if the machine will be destroyed at the given coordinates.
     * A machine is considered destroyed if it is located at the specified
     * longitude and latitude.
     * 
     * @param longitude the longitude coordinate to check
     * @param latitude the latitude coordinate to check
     * @return true if the machine will be destroyed, false otherwise
     */
    public boolean willBeDestroyed(int lon, int lat) {
        return location.equals(lon, lat);
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

    /**
     * Moves the machine to a new location specified by longitude and latitude.
     * @param lon the target longitude to move the machine to
     * @param lat the target latitude to move the machine to
     */
    public void moveTo(int lon, int lat) {
        if (location != null) {
            location.setNewLongitude(lon);
            location.setNewLatitude(lat);
        }
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
     * Determines if the machine can perform a good attack.
     * This method should be overridden in subclasses to provide specific criteria.
     * @return true if the machine can perform a good attack, false otherwise
     */
    public boolean isGoodAttack() {
        return false;
    }

}
