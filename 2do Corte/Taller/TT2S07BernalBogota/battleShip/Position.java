
/**
 * Position class represents a geographical position with longitude and latitude.
 * It is used to specify the location of machines in the battleship game.
 * @author Juan Daniel Bogotá
 * @author Nicolas Felipe Bernal
 * @version 1.0
 */
public class Position {

	private int longitude;
	private int latitude;

	public Position(int longitude, int latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude of the position.
	 * @return the longitude value
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * Gets the latitude of the position.
	 * @return the latitude value
	 */
	public int getLatitude() {
		return latitude;
	}

}
