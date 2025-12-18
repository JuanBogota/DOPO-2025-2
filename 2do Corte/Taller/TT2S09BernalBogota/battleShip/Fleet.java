import java.util.ArrayList;

/**
 * Represents a fleet in the BattleShip game.
 * A fleet is composed of multiple machines and sailors,
 * and it is associated with a specific game board.
 * 
 * This class provides operations to control the fleet's behavior,
 * such as moving its machines or managing its members.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */

public class Fleet {

	private String name;
	private ArrayList<Machine> machines;
	private ArrayList<Sailor> sailors;
	private Board board;

	/**
	 * Constructs a Fleet object with a given name and board.
	 * Initializes empty lists for machines and sailors. 
	 * @param name  The name of the fleet.
	 * @param board The game board where the fleet is placed.
	 */
	public Fleet(String name, Board board) {
		this.name = name;
		this.board = board;
		this.machines = new ArrayList<Machine>();
		this.sailors = new ArrayList<Sailor>();
	}

	/**
	 * Attempts to move the fleet's machine(s) to the north.
	 * Moves the machine to the north
	 * @throws BattleShipException if the machine cannot move north
	 */
	public void moveNorth() throws BattleShipException {
		for (Machine m : machines) {
            if (m == null || m.getLocation() == null) {
                throw new BattleShipException(BattleShipException.CANT_MOVE_MACHINE);
            }
            int lat = m.getLocation().getLatitude();
			if (lat + 1 > board.getMaxLatitude()) {
				throw new BattleShipException(BattleShipException.CANT_MOVE_MACHINE);
			}
			m.moveNorth();
		}
	}

	/**
	 * Returns a list of sailors who are pilots in the fleet.
	 * @return An ArrayList of sailors who are pilots.
	 * @throws BattleShipException if any sailor or machine crew member is not a pilot,
	 * 		or if a pilot of an airplane assigned to an aircraft carrier is not a sailor 
	 * 		of the aircraftcarrier.
	 */
	public ArrayList<Sailor> pilots() throws BattleShipException {
		ArrayList<Sailor> pilotSailors = new ArrayList<Sailor>();
		if (sailors != null) {
			for (Sailor s : sailors) {
				if (!s.isPilot()) {
					throw new BattleShipException(BattleShipException.IS_NOT_FLEET_PILOT);
				}
			}
		}
		if (machines != null){
			for (Machine m : machines) {
				if (m.isPilotNotCarrierCrew()) {
					throw new BattleShipException(BattleShipException.IS_NOT_FLEET_PILOT);
				}
			}
		}
		for (Sailor s : sailors){
			if (s.isPilot()) {
					pilotSailors.add(s);
			} 
		}

		return pilotSailors;
	}

	/**
     * Returns the total power of the fleet (number of non-weak machines).
     * @return The total power of the fleet.
     * @throws BattleShipException if there are fewer sailors than machines.
     */
    public int power() throws BattleShipException {
        if (machines == null || machines.isEmpty()) return 0;
        int machineCount = machines.size();
        int sailorCount = (sailors == null) ? 0 : sailors.size();
        if (sailorCount < machineCount) {
            throw new BattleShipException(BattleShipException.FEWER_SAILORS_THAN_FLEETS);
        }
        int power = 0;
        for (Machine m : machines) {
            if (m == null) continue;
            if (!m.isWeak()) {
                power++;
            }
        }
        return power;
    }

}

