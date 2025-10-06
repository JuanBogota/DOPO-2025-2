import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.ArrayList;

public class ECIAccount {

	private int accountId;
	private LocalDateTime creationDate;
	private String state;
	private BigDecimal balance;
	private Student student;
	private TreeMap<Integer, ECIPocket> pockets;
	private ArrayList<EciMovement> eciMovements;

	public ECIAccount(int accountId, LocalDateTime creationDate, String state, BigDecimal balance, Student student) {
		this.accountId = accountId;
		this.creationDate = creationDate;
		this.state = state;
		this.balance = balance;
		this.student = student;
		this.pockets = new TreeMap<>();
		this.eciMovements = new ArrayList<>();
	}

	/*
	 * Create an automatic payment movement for a loan if the account has sufficient balance.
	 * The payment is attempted from each pocket until one succeeds.
	 * @param l The loan for which the payment is to be made.
	 * @param loanValue The amount to be paid towards the loan.
	 * @return true if the movement was successfully created and processed, false otherwise.
	 */
	public boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue) {
		ECILoan loan = this.student.loadLoan(l.getLoanId());
		
		if (loan != null && this.balance.compareTo(loanValue) > 0) {
			EciMovement movement = new EciMovement(loan, loanValue);
			this.eciMovements.add(movement);
			
			for (ECIPocket pocket : pockets.values()) {
				if (pocket.createAutomaticPayment(loan, loanValue)) {
					this.balance = this.balance.subtract(loanValue);
					return true;
				}
			}
		}
		return false;
	}

}