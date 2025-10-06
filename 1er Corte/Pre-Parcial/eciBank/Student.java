import java.time.LocalDateTime;
import java.util.TreeMap;
import java.math.BigDecimal;


public class Student {

	private int semester;
	private String name;
	private String lastName;
	private String email;
	private LocalDateTime birthDay;
	private int studentId;
	private int phone;
	private TreeMap<Integer,ECIAccount> accounts;
	private TreeMap<Integer,ECILoan> loans;

	public Student(int semester, String name, String lastName, String email, LocalDateTime birthDay, int studentId,
			int phone) {
		this.semester = semester;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDay = birthDay;
		this.studentId = studentId;
		this.phone = phone;
		this.accounts = new TreeMap<>();
		this.loans = new TreeMap<>();
	}

	/*
	 * Methot to get the student ID.
	 * @return The student ID as an integer.
	 */
	public int getStudentId() {
		return this.studentId;
	}

	/*
	 * Methot to get the student name.
	 * @return The student name as a String.
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Methot to get the student last name.
	 * @return The student last name as a String.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/*
	 * Methot to get the student email.
	 * @return The student email as a String.
	 */
	public String getEmail() {
		return this.email;
	}

	/*
	 * Methot to get the student birth date.
	 * @return The student birth date as a LocalDateTime.
	 */
	public LocalDateTime getBirthDay() {
		return this.birthDay;
	}

	/*
	 * Methot to get the student phone number.
	 * @return The student phone number as an integer.
	 */
	public int getPhone() {
		return this.phone;
	}

	/*
	 * Methot to get the student semester.
	 * @return The student semester as an integer.
	 */
	public int getSemester() {
		return this.semester;
	}

	/*
	 * Methot to load a loan by its ID.
	 * @param loanId The ID of the loan to be loaded.
	 * @return The ECILoan object if found, otherwise null.
	 */
	public ECILoan loadLoan(int loanId){
		if (loanId < 0) {
			return null;
		}
        	ECILoan loan = loans.get(loanId);
		if (loan != null) {
            return new ECILoan(
            loan.getLoanId(),
            loan.getType(),
            loan.getName(),
            loan.getLoanValue(),
            loan.getInstalments(),
            loan.getState()
        	);
        	}
        	return null;
        }


	/*
	 * Method to create automatic payments for a loan from all accounts of the student.
	 * It attempts to create an automatic payment from each account until one succeeds.
	 * @param loanId The ID of the loan to which the payment is to be made.
	 * @param loanValue The amount to be paid towards the loan.
	 * @return true if the payment was successfully created and processed, false otherwise.
	 */
	public boolean createAutomaticPayment(int loanId, BigDecimal loanValue) {
		ECILoan loan = this.loadLoan(loanId);
		if (loan != null) {
			for (ECIAccount account : accounts.values()) {
				if (account.createAutomaticPayment(loan, loanValue)) {
					return true;
				}
			}
		}
		return false;
	}

}
