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
	 * Checks if the current position is equal to the given longitude and latitude.
	 * @param lon the longitude to compare
	 * @param lat the latitude to compare
	 * @return true if both longitude and latitude match, false otherwise
	 */
	public boolean equals(int lon, int lat) {
		return this.longitude == lon && this.latitude == lat;
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

	/**
	 * Sets a new longitude for the position.
	 * @param lon
	 */
	public void setNewLongitude(int lon) {
		this.longitude = lon;
	}

	/**
	 * Sets a new latitude for the position.
	 * @param lat
	 */
	public void setNewLatitude(int lat) {
		this.latitude = lat;
	}
}
