import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private TreeMap<Integer, Product> products;

	public Student(int semester, String name, String lastName, String email, LocalDateTime birthDay, int studentId,
			int phone) {
		this.semester = semester;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.birthDay = birthDay;
		this.studentId = studentId;
		this.phone = phone;
		this.products = new TreeMap<>();
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
	 * Method to load a loan by its ID.
	 * @param idLoan The ID of the loan to be loaded.
	 * @return The ECILoan object corresponding to the given ID.
	 * @throws ECIBankException if no loan is found with the given ID.
	 */
	public ECILoan loadLoan(int idLoan) throws ECIBankException{
		if (products.containsKey(idLoan)){
			return (ECILoan) products.get(idLoan);
		} else{
			throw new ECIBankException(ECIBankException.NO_LOAN_FOUND);
		}
	}

	/*
	 * Method to create an automatic payment from the student to pay a loan.
	 * @param l The ECILoan to be paid.
	 * @param loanValue The value of the loan to be paid.
	 * @return true if the payment was successful, false otherwise.
	 * @throws ECIBankException if the student has not enough resources to pay the loan.
	 */
	public boolean createAutomaticPayment(int loanId, BigDecimal loanValue) throws ECIBankException {
		ECILoan l = loadLoan(loanId);
		for (Product p : products.values()) {
			try {
				if (p.createAutomaticPayment(l, loanValue)) {
					return true;
				}
			} catch (ECIBankException e) {
				System.err.println("Error al intentar pago del prestamo " + p.getId() + ": " + e.getMessage());
			}
		}
		return false;
	}
}
