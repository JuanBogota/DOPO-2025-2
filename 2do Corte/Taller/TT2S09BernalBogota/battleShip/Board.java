import java.util.ArrayList;

/**
 * Represents the game board in the BattleShip game.
 * The board manages multiple fleets and provides operations
 * to validate positions and move fleets.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */
public class Board {

    private ArrayList<Fleet> fleets;

    public Board() {
        this.fleets = new ArrayList<Fleet>();
    }

    /**
     * Checks if the given coordinates are valid positions on the board.
     * A valid position is defined as having both longitude and latitude
     * within the range of 0 to 9.
     * @param longitude the longitude coordinate to check
     * @param latitude the latitude coordinate to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isAValidPosition(int lon, int lat) {
        return lon >= 0 && lon <= 180 && lat >= -90 && lat <= 90;
    }

    /**
     * Returns the maximum latitude allowed on the board.
     * @return the maximum latitude value
     */
    public int getMaxLatitude() {
        return 90;
    }

    /**
     * Returns the maximum longitude allowed on the board.
     * @return the maximum longitude value
     */    public int getMaxLongitude() {
        return 180;
     }

     /**
      * Attempts to move all fleets on the board to the north.
      * @return The number of fleets that successfully moved north.
      */
    public int toNorth() {
        int successful = 0;
        for (Fleet f : fleets) {
            try {
                f.moveNorth();
                successful++;
            } catch (BattleShipException e) {
                // La flota no pudo moverse; se ignora y no se cuenta
            }
        }
        return successful;
    }


    /**
     * Returns a list of fleets that have been infiltrated.
     * @return An ArrayList of fleets that has infiltrated pilots.
     */
    public ArrayList<Fleet> infiltrated(){
        ArrayList<Fleet> infiltratedFleets = new ArrayList<Fleet>();
        for (Fleet f : fleets) {
            try {
                ArrayList<Sailor> pilots = f.pilots();
                if (pilots != null && !pilots.isEmpty()) {
                    infiltratedFleets.add(f);
                }
            } catch (BattleShipException e) {
                // Si una flota no puede obtener sus pilotos, se ignora
            }
        }
        return infiltratedFleets;
    }

    /**
     * Return the power of the board, the power is the sum of the power of each fleet.
     * @return The total power of the board.
     */
    public int power() {
        int totalPower = 0;
        for (Fleet f : fleets) {
            try {
                totalPower += f.power();
            } catch (BattleShipException e) {
                // Si una flota no puede calcular su poder, se ignora
            }
        }
        return totalPower;
    }
}


