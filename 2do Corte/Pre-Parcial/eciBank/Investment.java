import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Write a description of class Investment here.
 * 
 * @author Juan Bogotá y Nicolás Bernal
 * @version 1.0
 */
public class Investment extends ECIPocket implements ICertificable{

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("500000");
    private static final BigDecimal COST_PERCENTAGE = new BigDecimal("0.02");
    private int riskLevel;
    private boolean hasProfit;

    /**
     * Constructor de la clase Investment
     */
    public Investment(int pocketId, String name, String description, BigDecimal savingTarget, String state,
            BigDecimal balance, BigDecimal profitability, LocalDateTime openDate, int riskLevel, boolean hasProfit, ECIAccount account) {
        super(pocketId, name, description, savingTarget, state, balance, profitability, openDate, account);
        this.riskLevel = riskLevel;
        this.hasProfit = hasProfit;
    }

    /**
     * Method to calculate the cost of the Investment.
     * @return The cost as a BigDecimal.
     */
    @Override
    public BigDecimal calculateProductCost() throws ECIBankException {
        BigDecimal balance = getBalance();
        // Validar si el balance es menor al monto mínimo permitido
        if (balance.compareTo(MIN_AMOUNT) < 0) {
            throw new ECIBankException(ECIBankException.INSUFFICIENT_BALANCE);
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        // Calcular el costo por pérdidas si las hay
        if (!hasProfit) {
            BigDecimal maxLoss = balance.multiply(new BigDecimal("0.50")); // Pérdida máxima permitida (50% del balance)
            BigDecimal actualLoss = balance.multiply(new BigDecimal("0.08")); // Pérdida mensual (8% del balance)
            BigDecimal lossCost = actualLoss.min(maxLoss); // La pérdida no puede exceder el 50% del balance
            totalCost = totalCost.add(lossCost);
        }

        // Calcular el costo de administración (2% mensual del balance)
        BigDecimal adminCost = balance.multiply(COST_PERCENTAGE);
        totalCost = totalCost.add(adminCost);

        return totalCost;
    
    }

   
}
