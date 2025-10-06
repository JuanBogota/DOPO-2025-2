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
    * @param longitude the longitude coordinate to check
    * @param latitude the latitude coordinate to check
    * @return an ArrayList of Machine objects that will be destroyed
    */
    public ArrayList<Machine> willBeDestroyed(int lon, int lat) {
        ArrayList<Machine> machineWillBeDestroyed = new ArrayList<Machine>();
        if(board.isAValidPosition(lon, lat)){
            for(Machine machine : machines){
                    if(machine.willBeDestroyed(lon, lat)) machineWillBeDestroyed.add(machine);
            }
        }
        return machineWillBeDestroyed;
        }

    /**
    * Identifies weak machines in the fleet based on specific criteria:
    * Ships with fewer than 5 sailors.
    * Planes without a pilot.
    * Aircraft carriers that either have fewer than 5 sailors or have at least one
    * Airplane in the air without a pilot. 
    * @return an ArrayList of Machine objects that are considered weak
    */
    public ArrayList<Machine> weakMachines() {
        ArrayList<Machine> weakMachines = new ArrayList<Machine>();
        for(Machine machine : machines){
            if(machine.isWeak()) weakMachines.add(machine);
        }
        return weakMachines;
    }

    /**
    * Move all machines in the fleet that are not considered weak,
    * moving them to the specified longitude and latitude.
    * Weak machines, as determined by the weakMachines() method,
    * will not be moved.
    * @param lon the target longitude for the attack
    * @param lat the target latitude for the attack
    */
    public void attack(int lon, int lat) {
        if(board.isAValidPosition(lon, lat)){
            for(Machine machine : machines){
                if(!machine.isWeak()) machine.moveTo(lon, lat);
            }
        }
    }

    /**
    * Delete a machine from the fleet.
    * @param machine
    */
        public void deleteMachine(Machine machine) {
        	machines.remove(machine);
        }

    /**
    * Determines if an attack at the given coordinates is successful.
    * An attack is considered successful if there is a machine located
    * at the specified longitude and latitude. If a machine is found,
    * it is removed from the fleet.
    * @param longitude the longitude coordinate to check
    * @param latitude the latitude coordinate to check
    * @return true if the attack is successful (a machine is found and removed), false otherwise
    */
	public boolean isGoodAttack(int lon, int lat) {
		boolean isGoodAttack = false;
		if (board.isAValidPosition(lon, lat)) {
			for (Machine machine : machines) {
				if (machine.getLocation().equals(lon, lat)) {
					isGoodAttack = true;
					deleteMachine(machine);
				}
			}
		}
		return isGoodAttack;
    }
}