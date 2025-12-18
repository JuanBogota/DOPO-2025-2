import java.time.LocalDateTime;
import java.util.TreeMap;

public class Branch {

	private String typeRequest;
	private LocalDateTime creationRequest;
	private LocalDateTime schedule;
	private int branchId;
	private TreeMap<Integer, Product> placeOfShipment;
	private Location location;

}
