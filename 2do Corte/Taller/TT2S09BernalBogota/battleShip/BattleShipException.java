/**
 * A class to represent exceptions in the BattleShip game.
 * @author Juan Daniel Bogotá Fuentes y Nicolás Felipe Bernal Gallo
 * @version 1.0
 */



public class BattleShipException extends Exception{
    
    public static final String CANT_MOVE_MACHINE = "Can not move the machine to the north.";
    public static final String IS_NOT_FLEET_PILOT = "The sailor is not a pilot in the fleet.";
    public static final String FEWER_SAILORS_THAN_FLEETS = "There are fewer sailors than fleets.";


    /**
     * Constructor for objects of class BattleShipException
     */
    public BattleShipException(String message){
        super(message); 
    }
}