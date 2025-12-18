import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Clase responsable de un tipo de inversion de tiempo estimado 
 * 
 * @author Juan Bogota Y Nicolas Bernal 
 * @version 1.0L
 */
public class CDT extends ECIPocket implements ICertificable {
    
    private static final int MAX_DURATION = 24;
    private int duration;

    /**
     * Constructor de la clase CDT
     */
    public CDT(int pocketId, String name, String description, BigDecimal savingTarget, String state,
            BigDecimal balance, BigDecimal profitability, LocalDateTime openDate, int duration, ECIAccount account) {
        super(pocketId, name, description, savingTarget, state, balance, profitability, openDate, account);
        this.duration = duration;
            }
    

    /**
     * Method to calculate the cost of the CDT.
     * @return The cost as a BigDecimal.
     */
	@Override
	public BigDecimal calculateProductCost() throws ECIBankException{
		
        BigDecimal interestRate;
        if (duration <= 6) {
            interestRate = BigDecimal.ZERO; 
        } else if (duration <= 12) {
            interestRate = new BigDecimal("0.08");
        } else if (duration <= 18) {
            interestRate = new BigDecimal("0.15");
        } else if (duration <= 24) {
            interestRate = new BigDecimal("0.20");
        } else {
            throw new ECIBankException(ECIBankException.DURATION_EXCEEDED);
        }

        BigDecimal totalValue = getBalance().add(getBalance().multiply(interestRate));

        BigDecimal cost = totalValue.multiply(new BigDecimal("4")).divide(new BigDecimal("1000"), BigDecimal.ROUND_HALF_EVEN);

        return cost;
	}
}