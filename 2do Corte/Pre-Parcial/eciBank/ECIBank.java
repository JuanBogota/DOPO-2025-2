import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;
import java.math.BigDecimal;


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
    
    private ArrayList<Branch> branches;
    private TreeMap<Integer, Product> products;
    private TreeMap<String, Student> students;
    private ArrayList<EciMovement> eciMovements;

    public ECIBank() {
        students = new TreeMap<>();
        branches = new ArrayList<>();
        products = new TreeMap<>();
        eciMovements = new ArrayList<>();
    }

    /**
     * Method to load a student by their ID.
     * @param studentId The ID of the student to be loaded.
     * @return The Student object corresponding to the given ID.
     * @throws ECIBankException if no student is found with the given ID.
     */
    public Student loadStudent(int studentId) throws ECIBankException {
        if (students.containsKey(studentId)) {
            return (Student) students.get(studentId);
        } else {
            throw new ECIBankException(ECIBankException.NO_STUDENT_FOUND);
        }
    }

    /**
     * Method to create an automatic payment for a student's loan.
     * @param studentId The ID of the student.
     * @param loanValue The value of the loan to be paid.
     * @param loanId The ID of the loan to be paid.
     * @return true if the automatic payment was created successfully, false otherwise.
     * @throws ECIBankException if there is an error during the process.
     */
    public boolean createAutomaticPayment(int studentId, BigDecimal loanValue, int loanId) throws ECIBankException {
        Student s = loadStudent(studentId);
        if(s != null){
            return s.createAutomaticPayment(loanId, loanValue);
        } else{
            return false;
        }
    }   

}


