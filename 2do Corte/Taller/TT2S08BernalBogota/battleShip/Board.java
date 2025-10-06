import java.util.ArrayList;

/**
 * The Board class represents the game board where fleets are deployed
 * in the battleship simulation.
 * 
 * A board manages a collection of Fleet objects that interact
 * with each other during the game.
 * 
 * @author Juan Daniel Bogotá
 * @version 1.0
 */
public class Board {

    private ArrayList<Fleet> fleets;
    
    public Board() {
        this.fleets = new ArrayList<>();
    }

    /**
     * Checks if the given coordinates are valid positions on the board.
     * A valid position is defined as having both longitude and latitude
     * within the range of 0 to 9.
     * 
     * @param longitude the longitude coordinate to check
     * @param latitude the latitude coordinate to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isAValidPosition(int lon, int lat) {
        return lon >= 0 && lon <= 9 && lat >= 0 && lat <= 9;
    }
}
