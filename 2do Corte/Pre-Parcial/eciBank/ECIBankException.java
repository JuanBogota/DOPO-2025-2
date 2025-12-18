
/**
 * ECIBankException class represents custom exceptions for the ECI Bank application.
 * 
 * @author Juan Bogotá
 * @version 1.0
 */
public class ECIBankException extends Exception{
    
    public static final String NO_STUDENT_FOUND = "El estudiante no existe.";
    public static final String NO_LOAN_FOUND = "El préstamo no fue econtrado.";
    public static final String STUDENT_HAS_NOT_RESOURCES = "El estudiante no cuenta con recursos para pagar el crédito.";
    public static final String AUTOMATIC_PAYMENTS_NOT_ALLOWED = "No se pueden realizar pagos automáticos desde un préstamo.";
    public static final String NO_PAYMENTS_MADE = "No se han realizado pagos aún.";
    public static final String DURATION_EXCEEDED = "La duración máxima permitida para un CDT es de 24 meses.";
    public static final String INSUFFICIENT_BALANCE = "Saldo insuficiente para realizar la operación.";

    public ECIBankException(String message){
        super(message);
    }
}
