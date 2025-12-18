/**
 * Represents a plane in the BattleShip game.
 * A plane is a type of machine that can operate in the air and has a pilot and a copilot.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */


public class Plane extends Machine {

	private String plate;
	private boolean inAir;
	private Sailor pilot;
	private Sailor copilot;

    /**
     * Constructs a Plane with the specified parameters.
     * 
     * @param plate   The plane's plate identifier.
     * @param inAir   Indicates whether the plane is currently in the air.
     * @param pilot   The sailor acting as the pilot.
     * @param copilot The sailor acting as the copilot.
     * @param location The initial position of the plane.
     */
    public Plane(String plate, boolean inAir, Sailor pilot, Sailor copilot, Position location) {
        super(location);
        this.plate = plate;
        this.inAir = inAir;
        this.pilot = pilot;
        this.copilot = copilot;
    }

    /**
     * Gets if the plane is currently in the air.
     * @return true if the plane is in the air, false otherwise
     */
    public boolean isInAir() {
        return inAir;
    }

    /**
     * Determines if the plane is weak.
     * A plane is considered weak if it does not have a pilot assigned.
     * @return true if the plane is weak, false otherwise
     */
    public boolean isWeak() {
        boolean isWeak = false;
        if(!inAir){
            isWeak = super.isGoodAttack();
        }
        return isWeak;
    }

    /**
     * Returns true if the assigned pilot is qualified (rank >= 5) and does NOT
     * belong to the crew of the given aircraft carrier.
     * @param carrier The aircraft carrier to check against.
     * @return true if the pilot is qualified and not part of the carrier's crew,
     *         false otherwise.
     */
    public boolean isPilotNotCarrierCrew(AircraftCarrier carrier) {
        if (pilot == null || carrier == null) return false;
        if (!pilot.isPilot()) return false;
        return !carrier.hasSailor(pilot);
    }
}