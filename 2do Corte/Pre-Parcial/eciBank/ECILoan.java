import java.math.BigDecimal;

public class ECILoan extends Product implements ICertificable{

	private String productName;
	private String type;
	private String name;
	private BigDecimal loanValue;
	private int instalments;

	public ECILoan(int productId, String productName, String type, String name, BigDecimal loanValue,
			int instalments) {
		// asignar datos heredados/propios
		this.id = productId; // campo protegido de Product
		this.productName = productName;
		this.type = type;
		this.name = name;
		this.loanValue = loanValue;
		this.instalments = instalments;
		// estado por defecto si se requiere (puede ser sobrescrito después)
		this.state = "CREATED";
	}
	
	/*
	 * Methot to get the loan ID.
	 * @return The loan ID as an integer.
	 */
	public int getId() {
		return this.id;
	}

	/*
	 * Methot to get the loan type.
	 * @return The type of the loan as a String.
	 */
	public String getType() {
		return this.type;
	}

	/*
	 * Methot to get the loan name.
	 * @return The name of the loan as a String.
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Methot to get the loan value.
	 * @return The value of the loan as a BigDecimal.
	 */
	public BigDecimal getLoanValue() {
		return this.loanValue;
	}

	/*
	 * Methot to get the number of instalments.
	 * @return The number of instalments as an integer.
	 */
	public int getInstalments() {
		return this.instalments;
	}

	/*
	 * Methot to get the loan state.
	 * @return The state of the loan as a String.
	 */
	public String getState() {
		return this.state;
	}

	/*
	 * Get product name (stored in this class)
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * Method to create an automatic payment for the loan.
	 * @param l The ECILoan object.
	 * @param loanValue The value of the loan as a BigDecimal.
	 * @return Always throws an ECIBankException since automatic payments cannot be made from a loan.
	 * @throws ECIBankException indicating that automatic payments cannot be made from a loan
	 */
	@Override
	public boolean createAutomaticPayment(ECILoan l, BigDecimal loanValue) throws ECIBankException {
    	throw new ECIBankException(ECIBankException.AUTOMATIC_PAYMENTS_NOT_ALLOWED);
	}

	/**
	 * Method to get the number of payments made for the loan.
	 * @return The number of payments made as an integer.
	 */
	@Override
	public int numberPaymentsMade() {
		return super.numberPaymentsMade(); // Devuelve el tamaño de la lista de pagos
	}

	/**
	 * Method to calculate the cost of the loan product.
	 * @return The cost of the loan as a BigDecimal.
	 * @throws ECIBankException if no payments have been made yet.
	 */
	@Override
	public BigDecimal calculateProductCost() throws ECIBankException {
		int paymentsMade = this.numberPaymentsMade();
		if (paymentsMade == 0) {
			throw new IllegalStateException("No payments made yet.");
		}
		BigDecimal monthlyPayment = loanValue.divide(new BigDecimal(instalments), BigDecimal.ROUND_HALF_EVEN);
		BigDecimal cost = monthlyPayment.subtract(
			loanValue.divide(new BigDecimal(paymentsMade), BigDecimal.ROUND_HALF_EVEN)
		);

		return cost;
	}

}
