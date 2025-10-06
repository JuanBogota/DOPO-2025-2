import java.util.ArrayList;

/**
 * The Fleet class represents a group of machines and sailors
 * that operate together within the battleship game.
 * 
 * A fleet has a name, a collection of Machine objects such as
 * Ship or Plane, a list of Sailor crew members,
 * and is associated with a {@link Board} where it operates.
 * 
 * @author Juan Daniel Bogotá
 * @author Nicolas Felipe Bernal
 * @version 1.0
 */
public class Fleet {

	private String name;
	private ArrayList<Machine> machines;
	private ArrayList<Sailor> sailors;
	private Board board;

	public Fleet(String name, Board board) {
		this.name = name;
		this.board = board;
		this.machines = new ArrayList<Machine>();
		this.sailors = new ArrayList<Sailor>();
	}


	/**
	 * Determines which machines will be destroyed at the given coordinates.
	 * A machine is considered destroyed if it is located at the specified
	 * longitude and latitude, and if it is a Plane, it must not be in the air.
	 * 
	 * @param longitude the longitude coordinate to check
	 * @param latitude the latitude coordinate to check
	 * @return an ArrayList of Machine objects that will be destroyed
	 */
	public ArrayList<Machine> willBeDestroyed(int longitude, int latitude) {
    ArrayList<Machine> destroyedMachines = new ArrayList<Machine>();
    for (Machine machine : machines) {
        Position location = machine.getLocation();
        if (location.getLongitude() == longitude && location.getLatitude() == latitude) {
            if (machine instanceof Plane) {
                Plane plane = (Plane) machine;
                if (!plane.isInAir()) {
                    destroyedMachines.add(machine);
                }
            } else {
                destroyedMachines.add(machine);
            	}
        	}
    	}
    	return destroyedMachines;
	}

	/**
	 * Identifies weak machines in the fleet based on specific criteria:
	 * - Ships with fewer than 5 sailors.
	 * - Planes without a pilot.
	 * - Aircraft carriers that either have fewer than 5 sailors or have at least one
	 *   airplane in the air without a pilot.
	 * 
	 * @return an ArrayList of Machine objects that are considered weak
	 */
	public ArrayList<Machine> weakMachines() {
        ArrayList<Machine> weakMachines = new ArrayList<>();
        for (Machine machine : machines) {
            if (machine instanceof Ship) {
                Ship ship = (Ship) machine;
                if (ship.getSailors().size() < 5) {
                    weakMachines.add(machine);
                }
            } else if (machine instanceof Plane) {
                Plane plane = (Plane) machine;
                if (plane.getPilot() == null) {
                    weakMachines.add(machine);
                }
            } else if (machine instanceof AircraftCarrier) {
                AircraftCarrier carrier = (AircraftCarrier) machine;
                if (((Ship) carrier).getSailors().size() < 5) {
                    weakMachines.add(carrier);
                } else {
                    for (Plane plane : carrier.getAirplanes()) {
                        if (plane.isInAir() && plane.getPilot() == null) {
                            weakMachines.add(carrier);
                            break;
                        }
                    }
                }
            }
        }
        return weakMachines;
	}

	/**
	 * Move all machines in the fleet that are not considered weak,
	 * moving them to the specified longitude and latitude.
	 * Weak machines, as determined by the weakMachines() method,
	 * will not be moved.
	 * 
	 * @param lon the target longitude for the attack
	 * @param lat the target latitude for the attack
	 */
    public void attack(int lon, int lat) {
        ArrayList<Machine> weak = weakMachines();
        for (Machine machine : machines) {
            if (!weak.contains(machine)) {
                machine.moveTo(new Position(lon, lat));
            }
        }
    }
}

