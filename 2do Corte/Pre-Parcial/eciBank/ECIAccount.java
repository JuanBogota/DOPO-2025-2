import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.math.BigDecimal;



public class ECIAccount extends Product{

	private LocalDateTime creationDate;
	private String state;
	private BigDecimal balance;
	private ArrayList<ECIPocket> pockets;

	public ECIAccount(int productId, String productName, LocalDateTime creationDate, String state,
			BigDecimal balance) {

		this.creationDate = creationDate;
		this.state = state;
		this.balance = balance;
		this.pockets = new ArrayList<>();
	}

	/**
	 * Method to create an automatic payment from the account to pay a loan.
	 * @param l The ECILoan to be paid.
	 * @param loanValue The value of the loan to be paid.
	 * @return true if the payment was successful, false otherwise.
	 * @throws ECIBankException if the student has not enough resources to pay the loan.
	 */
	@Override
	public boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue) throws ECIBankException{
		if (this.balance.compareTo(loanValue) < 0){
			for (ECIPocket p : this.pockets){
				if (p.createAutomaticPayment(l, loanValue)){
					this.balance = this.balance.subtract(loanValue);
					return true;
				}
			}
			return false;
		} else if (this.balance.compareTo(loanValue) >= 0){
			new EciMovement(l, loanValue);
			this.balance = this.balance.subtract(loanValue);
			return true;
		} else {
			throw new ECIBankException(ECIBankException.STUDENT_HAS_NOT_RESOURCES);
		}
	} 
}

