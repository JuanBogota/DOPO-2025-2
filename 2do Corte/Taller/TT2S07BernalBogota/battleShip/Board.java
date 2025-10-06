import java.util.ArrayList;

/**
 * The Board class represents the game board where fleets are deployed
 * in the battleship simulation.
 * 
 * A board manages a collection of Fleet objects that interact
 * with each other during the game.
 * 
 * @author Juan Daniel Bogotá
 * @author Nicolas Felipe Bernal
 * @version 1.0
 */
public class Board {

    private ArrayList<Fleet> fleets;
    
    public Board() {
        this.fleets = new ArrayList<>();
    }

}
