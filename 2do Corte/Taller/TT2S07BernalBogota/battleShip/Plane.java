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
 * @author Nicolas Felipe Bernal
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
}
