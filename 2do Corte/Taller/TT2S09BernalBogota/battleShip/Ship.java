import java.util.ArrayList;
import java.util.Collection;
/**
 * Represents a ship in the BattleShip game.
 * A ship is a type of machine that has a name, position,
 * and a collection of sailors on board.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */

public class Ship extends Machine {
	protected String name;
    protected Position position;
	private Collection<Sailor> sailors;

    public Ship(String name, Position position) {
        super(position);
        this.name = name;
        this.position = position;
        this.sailors = new ArrayList<Sailor>();
    }


    /**
     * Returns the collection of sailors on board the ship.
     * @return A collection of sailors.
     */
    public Collection<Sailor> getCrew() {
        return sailors;
    }

    /**
     * Checks if a specific sailor is part of the ship's crew.
     * @param s The sailor to check.
     * @return true if the sailor is part of the crew, false otherwise.
     */
    public boolean hasSailor(Sailor s) {
        return sailors != null && s != null && sailors.contains(s);
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

