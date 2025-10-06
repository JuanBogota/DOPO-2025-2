
/**
 * Write a description of class Capsula here.
 * 
 * @author Juan Daniel Bogotá
 * @version 1.0
 */
public class Capsula extends Machine implements Nodriza{

    private int depth;

    /**
     * Constructor for objects of class Capsula
     */
    public Capsula(Position location, int depth) {
        super(location);
        this.depth = depth;
    }

    /**
     * Determines if the capsula will be destroyed at the given coordinates.
     * A capsula is considered destroyed if it is located at the specified
     * longitude and latitude.
     * @param longitude the longitude coordinate to check
     * @param latitude the latitude coordinate to check
     * @return true if the capsula will be destroyed, false otherwise
     */
    public boolean willBeDestroyed(int lon, int lat) {
	boolean willBeDestroyed = false;
	if (depth > -8000) {
		willBeDestroyed = super.willBeDestroyed(lon, lat);
	}
	return willBeDestroyed;
	}

    /**
     * Determines if the capsula is weak.
     * A capsula is NEVER considered weak.
     * @return false always, as capsulas are never weak
     */
    public boolean isWeak() {
        return false;
    }

    /**
     * Gets the depth of the capsula.
     * @return the depth value
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Determines if the capsula can perform a good attack.
     * A capsula is considered to perform a good attack if its depth
     * is greater than 8000 meters below sea level (i.e., depth < -8000).
     * @return true if the capsula can perform a good attack, false otherwise
     */
    public boolean isGoodAttack() {
       boolean goodAttack = false;
	if(depth > -8000) {
		goodAttack = super.isGoodAttack();
        }
	return goodAttack;
	}
}
