
import java.util.ArrayList;

/**
 * Represents an aircraft carrier in the BattleShip game.
 * 
 * An aircraft carrier is a specialized type of {@link Ship} capable of
 * transporting and deploying multiple airplanes. It has a specific
 * identification number, a defined carrying capacity, and a list
 * of airplanes currently on board.
 * 
 * This class extends the {@link Ship} class, inheriting its general
 * properties and behaviors, while adding attributes specific to
 * aircraft carriers.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */


public class AircraftCarrier extends Ship {

	private int number;
	private int capacity;
	private ArrayList<Plane> airPlanes;

	/**
	 * Constructs an AircraftCarrier with the specified name, position,
	 * identification number, and carrying capacity.
	 * 
	 * @param name     The name of the aircraft carrier.
	 * @param position The current position of the aircraft carrier.
	 * @param number   The identification number of the aircraft carrier.
	 * @param capacity The maximum number of airplanes the carrier can hold.
	 */

	public AircraftCarrier(String name, Position position, int number, int capacity) {
			super(name, position);
			this.number = number;
			this.capacity = capacity;
			this.airPlanes = new ArrayList<Plane>();
	}

	/**
     * Determines if the aircraft carrier is weak.
     * An aircraft carrier is considered weak if it has fewer than 5 sailors
     * or if it has at least one airplane that is not in the air.
     * @return true if the aircraft carrier is weak, false otherwise
     */
    public boolean isWeak(){
        boolean weakShip = super.isWeak();
        boolean isWeak = false;
        if(!weakShip){
            for(Plane plane : airPlanes){
                if(!plane.isInAir()){
                    isWeak = true;
                    break;
                }
            }
            return isWeak;
        } else {
            return false;
        }
    }

}
	
	
	

