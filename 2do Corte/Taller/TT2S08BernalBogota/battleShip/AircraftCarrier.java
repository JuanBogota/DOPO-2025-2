import java.util.ArrayList;

/**
 * AircraftCarrier class represents an aircraft carrier ship in the battleship game.
 * It extends the Ship class and includes additional attributes specific to aircraft carriers.
 * @author Juan Daniel Bogotá
 * @version 1.0
 */
public class AircraftCarrier extends Ship {

	private int number;
	private int capacity;
	private ArrayList<Plane> airPlanes;

	public AircraftCarrier(Position location,int number, int capacity) {
		super(location);
		this.number = number;
		this.capacity = capacity;
		this.airPlanes = new ArrayList<Plane>();
	}

	/**
	 * Gets the list of airplanes assigned to the aircraft carrier.
	 * @return an ArrayList of Plane objects representing the airplanes
	 */
	public ArrayList<Plane> getAirplanes() {
        return airPlanes;
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
