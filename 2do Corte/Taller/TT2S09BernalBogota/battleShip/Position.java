/**
 * Represents a geographical position in the BattleShip game.
 * A position is defined by its longitude and latitude coordinates,
 * which indicate the location of a machine, sailor, or object on the board.
 * 
 * This class provides methods to access the coordinates but does not allow modification,
 * making it effectively immutable once created.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */


public class Position {

	private int longitude;
	private int latitude;


	/**
	 * Constructs a Position with the specified longitude and latitude.
	 * 
	 * @param longitude The longitude coordinate.
	 * @param latitude  The latitude coordinate.
	 */
	public Position(int longitude, int latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Returns the longitude of this position.
	 * @return The longitude coordinate.
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * Returns the latitude of this position.
	 * @return The latitude coordinate.
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * Move to the north
	 * @return The new latitude after moving north.
	 */
	public void moveNorth(){
		latitude = latitude + 1;
	}

	
}
