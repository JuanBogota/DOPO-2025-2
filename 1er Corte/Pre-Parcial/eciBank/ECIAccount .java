import java.util.Collection;
public class ECIAccount  {

	private int accountId;

	private LocalDateTime creationDate;

	private String state;

	private BigDecimal balance;

	private Student student;

	private Collection<ECIPocket> pockets;

	private Collection<EciMovement> eciMovements;

}
