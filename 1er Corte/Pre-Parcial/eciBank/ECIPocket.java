import java.math.BigDecimal;
import java.util.ArrayList;

public class ECIPocket {

	private int pocketId;
	private String name;
	private String description;
	private BigDecimal savingTarget;
	private String state;
	private BigDecimal balance;
	private ArrayList<EciMovement> eciMovements;
	private Student student;

	public ECIPocket(int pocketId, String name, String description, BigDecimal savingTarget, String state,
			BigDecimal balance) {
		this.pocketId = pocketId;
		this.name = name;
		this.description = description;
		this.savingTarget = savingTarget;
		this.state = state;
		this.balance = balance;
		this.eciMovements = new ArrayList<>();
	}

	/*
	 * Methot to create an automatic payment towards a loan
	 * It will create a movement and update the balance if the account is active
	 * and has enough balance
	 * @param l the loan to pay
	 * @param loanValue the value to pay
	 * @return true if the movement was successful, false otherwise
	 */
	public boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue) {
    	
		ECILoan loan = this.student.loadLoan(l.getLoanId());
    
		if (loan != null && this.state.equals("ACTIVE") && this.balance.compareTo(loanValue) >= 0) {
			EciMovement movement = new EciMovement(loan, loanValue);
			this.eciMovements.add(movement);
			this.balance = this.balance.subtract(loanValue);
			return true;
			}
		return false;
	}

}	
