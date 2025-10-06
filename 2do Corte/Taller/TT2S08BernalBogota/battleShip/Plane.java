/**
 * The Plane class represents an aircraft in the battleship game.
 * It extends the Machine class and includes specific attributes
 * such as its plate number, flight status, and assigned crew members
 * (pilot and copilot).
 * 
 * This class can be used to simulate the behavior and crew
 * of airplanes within the game environment.
 * 
 * @author Juan Daniel Bogotá
 * @version 1.0
 */


public class Plane extends Machine {

	private String plate;
	private boolean inAir;
	private Sailor pilot;
	private Sailor copilot;

	public Plane(Position location, String plate, boolean inAir, Sailor pilot, Sailor copilot) {
		super(location);
		this.plate = plate;
		this.inAir = inAir;
		this.pilot = pilot;
		this.copilot = copilot;
	}

	/**
	 * Gets if the plane is currently in the air.
	 * @return true if the plane is in the air, false otherwise
	 */
	public boolean isInAir() {
		return inAir;
	}

	/**
	 * Gets the pilot of the plane.
	 * @return the Sailor object representing the pilot
	 */
	public Sailor getPilot() {
		return pilot;
	}

	/**
	 * Determines if the plane will be destroyed at the given coordinates.
	 * A plane can only be destroyed if it is not currently in the air and
	 * is located at the specified longitude and latitude.
	 * @param longitude the longitude coordinate to check
	 * @param latitude the latitude coordinate to check
	 * @return true if the plane will be destroyed, false otherwise
	 */
	public boolean willBeDestroyed(int lon, int lat) {
		boolean willBeDestroyed = false;
		if (!inAir) {
			willBeDestroyed = super.willBeDestroyed(lon, lat);
		}
		return willBeDestroyed;
	}

	/**
	 * Determines if the plane is weak.
	 * A plane is considered weak if it does not have a pilot assigned.
	 * @return true if the plane is weak, false otherwise
	 */
	public boolean isWeak() {
		boolean isWeak = false;
		if(!inAir){
			isWeak = super.isGoodAttack();
		}
		return isWeak;
	}

	/**
	 * Determines if the plane can perform a good attack.
	 * A plane can only perform a good attack if it is not in the air
	 * and meets the criteria defined in the Machine class.
	 * @return true if the plane can perform a good attack, false otherwise
	 */
	public boolean isGoodAttack(){
		boolean goodAttack = false;
		if(!inAir){
			goodAttack = super.isGoodAttack();
		}
		return goodAttack;
	}
}
