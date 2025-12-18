import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ECIPocket {

	private int pocketId;
	private String name;
	private String description;
	private BigDecimal savingTarget;
	private String state;
	private BigDecimal balance;
	private BigDecimal profitability;
	private LocalDateTime openDate;
	private ArrayList<EciMovement> eciMovements;
	protected ECIAccount account;

	public ECIPocket(int pocketId, String name, String description, BigDecimal savingTarget, String state,
			BigDecimal balance, BigDecimal profitability, LocalDateTime openDate, ECIAccount account) {
		this.pocketId = pocketId;
		this.name = name;
		this.description = description;
		this.savingTarget = savingTarget;
		this.state = state;
		this.balance = balance;
		this.profitability = profitability;
		this.openDate = openDate;
		this.eciMovements = new ArrayList<>();
		this.account = account;
	}

	/**
	 * Method to get the pocket balance.
	 * @return The balance as a BigDecimal.
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Method to create an automatic payment from the pocket to pay a loan.
	 * @param l The ECILoan to be paid.
	 * @param loanValue The value of the loan to be paid.
	 * @return true if the payment was successful, false otherwise.
	 */
	public boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue){
		new EciMovement(l, loanValue);
		if(this.balance.compareTo(loanValue) >= 0){
			this.balance = this.balance.subtract(loanValue);
			return true;
		} else {
			return false;
		}
	}

}	
