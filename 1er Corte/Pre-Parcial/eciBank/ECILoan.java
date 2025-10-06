import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ECILoan {

	private int loanId;
	private String type;
	private String name;
	private BigDecimal loanValue;
	private int instalments;
	private String state;
	private ArrayList<EciMovement> eciMovements;
	public int loadLoan;

	public ECILoan(int loanId, String type, String name, BigDecimal loanValue, int instalments, String state) {
		this.loanId = loanId;
		this.type = type;
		this.name = name;
		this.loanValue = loanValue;
		this.instalments = instalments;
		this.state = state;
		this.eciMovements = new ArrayList<>();
	}

	/*
	 * Methot to clone the current ECILoan object.
	 * @return A new ECILoan object that is a copy of the current one.
	 */
	public int getLoanId() {
		return this.loanId;
	}

	/*
	 * Methot to get the loan type.
	 * @return The type of the loan as a String.
	 */
	public String getType() {
		return this.type;
	}

	/*
	 * Methot to get the loan name.
	 * @return The name of the loan as a String.
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Methot to get the loan value.
	 * @return The value of the loan as a BigDecimal.
	 */
	public BigDecimal getLoanValue() {
		return this.loanValue;
	}

	/*
	 * Methot to get the number of instalments.
	 * @return The number of instalments as an integer.
	 */
	public int getInstalments() {
		return this.instalments;
	}

	/*
	 * Methot to get the loan state.
	 * @return The state of the loan as a String.
	 */
	public String getState() {
		return this.state;
	}
}
