import java.util.Collection;
import java.util.ArrayList;

/**
 * Ship class represents a Ship in the battleship game.
 * This class can be used to model different types of ships in the game,
 * including aircraft carriers or other specialized vessels.
 * @author Juan Daniel Bogotá
 * @version 1.0
 */
public class Ship extends Machine implements Nodriza{

    private Collection<Sailor> sailors;

    public Ship(Position location) {
        super(location);
        this.sailors = new ArrayList<>();
    }

    /**
     * Gets the collection of sailors assigned to this ship.
     * @return the collection of sailors assigned to this ship
     */
    public Collection<Sailor> getSailors() {
        return sailors;
    }

    /**
     * Determines if the ship is weak.
     * A ship is considered weak if it has fewer than 5 sailors.
     * @return true if the ship is weak, false otherwise
     */
    public boolean isWeak() {
        boolean isWeak = false;
		if(sailors.size() < 5) {
			isWeak = super.isGoodAttack();
		}
		return isWeak;
	}

}