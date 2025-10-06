import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.TreeMap;


/*
 * Class representing a bank with students, branches, accounts, and loans.
 * Each entity is stored in a TreeMap for efficient access and management.
 * @author Juan Daniel Bogotá
 * @version 1.0
 * @since 2025-09-15
 */

 /* Invariante de clase:
 * 1) students, branches, accounts y loans son no nulos durante toda la vida del objeto.
 * 2) Las llaves en cada TreeMap son únicas (garantizado por el propio TreeMap).
 * 3) Toda cuenta (accounts) referencia a un Student existente (por studentId).
 * 4) Todo préstamo (loans) referencia a un Student existente (por studentId)
 * 5) No deben existir cuentas con identificadores negativos ni préstamos con montos negativos
 * 6) No deben existir estudiantes con identificadores negativos.
 */

public class ECIBank {
    
    private TreeMap<Integer, Student> students;
    private TreeMap<Integer, Branch> branches;
    private TreeMap<Integer, ECIAccount> accounts;
    private TreeMap<Integer, ECILoan> loans;

    public ECIBank() {
        students = new TreeMap<>();
        branches = new TreeMap<>();
        accounts = new TreeMap<>();
        loans = new TreeMap<>();
    }


    /*
     * Method to load a student by their ID.
     * @param studentId The ID of the student to load.
     * @return The Student object if found, null otherwise.
     */
    public Student loadStudent(int studentId){
		if (studentId < 0) {
			return null;
		}
		Student student = students.get(studentId);
		if (student != null) {
        return new Student(
            student.getStudentId(),
            student.getName(),
            student.getLastName(),
            student.getEmail(),
            student.getBirthDay(),
            student.getPhone(),
            student.getSemester()
            );
    	}
    	return null;
    }


    /*
     * Method to create automatic payments for a loan from all accounts of a student.
     * It attempts to create an automatic payment from each account until one succeeds.
     * @param studentId The ID of the student whose accounts will be used for payment.
     * @param loanValue The amount to be paid towards the loan.
     * @param loanId The ID of the loan to which the payment is to be made.
     * @return true if the payment was successfully created and processed, false otherwise.
     */
    public boolean createAutomaticPayment(int loanId, BigDecimal loanValue){
        for (Student student : students.values()) {
            if (student.createAutomaticPayment(loanId, loanValue)) {
                return true;
            }
        }
        return false;
    }

}


