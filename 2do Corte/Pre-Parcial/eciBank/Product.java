import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Abstract class Product
 * 
 * @author Juan Bogotá
 * @version 1.0
 */
public abstract class Product
{
    protected int id;
    protected String state;
    private ArrayList<EciMovement> eciMovements = new ArrayList<>();

    /**
     * Methot to get the product ID.
     * @return The product ID as an integer.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Methot to get the product state.
     * @return The product state as a String.
     */
    public String getState() {
        return this.state;
    }

    /**
     * Abstract method to create an automatic payment for a loan.
     * @param l The loan for which the automatic payment is to be created.
     * @param loanValue The value of the loan.
     * @return true if the automatic payment was created successfully, false otherwise.
     * @throws ECIBankException if there is an error during the process.
     */
    public abstract boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue) throws ECIBankException;

    /**
     * Method to register a payment.
     * @param payment The EciMovement object representing the payment.
     */
    public void registerPayment(EciMovement payment) {
        eciMovements.add(payment);
    }

    /**
     * Method to get the number of payments made.
     * @return The number of payments made as an integer.
     */
    public int numberPaymentsMade() {
        return eciMovements.size();
    }
}
