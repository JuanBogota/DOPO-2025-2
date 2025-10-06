import java.time.LocalDateTime;
import java.util.TreeMap;

public class Branch {

	private String typeRequest;
	private LocalDateTime creationRequest;
	private LocalDateTime schedule;
	private int branchId;
	private TreeMap<Integer,ECIAccount > placeOfShipment;
	private TreeMap<Integer,ECILoan> placeOfLoanShipment;
	private Location location;

}
