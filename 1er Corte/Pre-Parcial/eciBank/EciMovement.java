import java.time.LocalDateTime;
import java.math.BigDecimal;

public class EciMovement {

	private int movementId;
	private LocalDateTime eciMovementsDate;
	private BigDecimal amount;
	private String type;
	private Branch transactionPlace;
	private Student student;

	public EciMovement(int movementId, LocalDateTime eciMovementsDate, BigDecimal amount, String type, Branch transactionPlace) {
		this.movementId = movementId;
		this.eciMovementsDate = eciMovementsDate;
		this.amount = amount;
		this.type = type;
		this.transactionPlace = transactionPlace;
	}

	public EciMovement(ECILoan l, BigDecimal loanValue) {
        ECILoan loan = this.student.loadLoan(l.getLoanId());
        this.amount = loanValue;
	}
}
