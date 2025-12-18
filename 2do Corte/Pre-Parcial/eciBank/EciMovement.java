import java.time.LocalDateTime;
import java.math.BigDecimal;

public class EciMovement {

	private int movementId;
	private LocalDateTime eciMovementsDate;
	private BigDecimal amount;
	private String type;
	private Branch transactionPlace;
	private Product product;

	public EciMovement(int movementId, LocalDateTime eciMovementsDate, BigDecimal amount, String type, Branch transactionPlace, Product account) {
		this.movementId = movementId;
		this.eciMovementsDate = eciMovementsDate;
		this.amount = amount;
		this.type = type;
		this.transactionPlace = transactionPlace;
		this.product = account;
	}

	
	public EciMovement(Product product, BigDecimal value){
		this.movementId = 0;
		this.eciMovementsDate = LocalDateTime.now();
		this.amount = value;
		this.type = null;
		this.transactionPlace = null;
		this.product = product;
	}
}
