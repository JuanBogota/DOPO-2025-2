/**
 * Represents a sailor in the BattleShip game.
 * A sailor has a name and a rank, and can be evaluated
 * to determine if they are qualified to pilot a machine.
 * 
 * @author Juan Daniel Bogotá Fuentes
 * @author Nicolás Felipe Bernal Gallo
 * @version 1.0
 */
public class Sailor {

	private String name;
	private int rank;

	public Sailor(String name, int rank) {
		this.name = name;
		this.rank = rank;
	}

	/**
	 * Determines if the sailor is qualified to pilot a machine.
	 * A sailor is considered a pilot if their rank is 5 or higher.
	 * 
	 * @return true if the sailor is a pilot, false otherwise.
	 */
	public boolean isPilot() {
		return rank >= 5;
	}
}
