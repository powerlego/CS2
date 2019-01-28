

public class ParkingSpot {
	
	/** used in the string representation of the lot to show an occupied spot */
	private static final String OCCUPIED_STR = "*";
	
	/** The unique number for this spot */
	private int spot;
	
	/** The spot type */
	private Permit.Type type;
	
	/** the Vehicle object parked in the spot (null if no vehicle) */
	private Vehicle vehicle;

	/**
	 * Create a new parking spot.
	 * 
	 * @param spot the unique spot number
	 * @param type the type of spot
	 */
	public ParkingSpot(int spot, Permit.Type type) {
		this.spot = spot;
		this.type = type;
		this.vehicle = null;
	}

	/**
	 * Get the spot number.
	 * 
	 * @return spot number
	 */
	public int getSpot() {
		return spot;
	}

	/**
	 * Get the spot type.
	 * 
	 * @return spot type
	 */
	public Permit.Type getType() {
		return type;
	}

	/**
	 * Get the vehicle in the spot.
	 * 
	 * @return the vehicle (null if none).
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Have a vehicle take this spot and become parked.
	 * 
	 * @param vehicle the vehicle to occupy the spot
	 * <dt><b>Preconditions:</b><dd> there is no vehicle in the spot
	 */
	public void occupySpot(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Have a vehicle leave a spot and become unparked.
	 * 
	 * <dt><b>Preconditions:</b><dd> there is a vehicle in the spot
	 */
	public void vacateSpot() {
		this.vehicle = null;
	}

	@Override
	public String toString() {
		Permit.Type type = this.getType();
		if (this.getVehicle() == null)
			if (type == Permit.Type.HANDICAPPED) {
				return this.spot + ":" + "H";
			}
			else if (type == Permit.Type.RESERVED) {
				return this.spot + ":" + "R";
			} 
			else {
				return this.spot + ":" + "G";
			}
		else {
			return this.spot + ":" + OCCUPIED_STR;
		}
	}
	
	/**
	 * Verify a parking spot has the correct spot id, type and vehicle.
	 * 
	 * @param spotVar the name of the variable
	 * @param s the ParkingSpot object to check
	 * @param spot the expected spot id
	 * @param type the expected permit type
	 * @param vehicle the expected vehicle (null if none)
	 */
	private static void verifySpot(String spotVar, ParkingSpot s, int spot, Permit.Type type, Vehicle vehicle) {
		System.out.println(
				spotVar + ": spot=" + spot + "? " + (s.getSpot() == spot ? "OK" : "FAIL, got: " + s.getSpot()));
		System.out.println(
				spotVar + ": type=" + type + "? " + (s.getType() == type ? "OK" : "FAIL, got: " + s.getType()));
		if (vehicle == null) {
			System.out.println(
					spotVar + ": vehicle=" + "null" + "? " + (s.getVehicle() == null ? "OK" : "FAIL, was not null"));
		} 
		else if (vehicle != null) {
			System.out.println(spotVar + ": vehicle=" + vehicle + "? "
					+ (vehicle.equals(s.getVehicle()) ? "OK" : "FAIL, got: " + s.getVehicle()));
			System.out.println(spotVar + ": vehicle.isParked=" + "true" + "? "
					+ (vehicle.isParked() ? "OK" : "FAIL, got: " + s.getVehicle().isParked()));
		}
		System.out.println("visually verify " + spotVar + "'s toString: " + s);
		System.out.println();
	}
	
	/**
	 * The main test function for the ParkingSpot class.
	 * 
	 * @param args command line arguments (unused)
	 */
	public static void main(String[] args) {
		//Create a vehicle, v, whose plate is 10
		Vehicle v = new Vehicle(10);
		
		//Create a spot, s1, whose spotNum is 1, Permit.Type is general, and has no vehicle
		ParkingSpot s1 = new ParkingSpot(1, Permit.Type.GENERAL);
		verifySpot("s1", s1, 1, Permit.Type.GENERAL, null);
		
		//Create a spot, s2, whose spotNum is 2, Permit.Type is handicapped, and has no vehicle
		ParkingSpot s2 = new ParkingSpot(2, Permit.Type.HANDICAPPED);
		verifySpot("s2", s2, 2, Permit.Type.HANDICAPPED, null);

		//Create a spot, s3, whose spotNum is 3, Permit.Type is reserved, and has no vehicle
		ParkingSpot s3 = new ParkingSpot(3, Permit.Type.RESERVED);
		verifySpot("s3", s3, 3, Permit.Type.RESERVED, null);
		
		//Create a spot, s4, whose spotNum is 4, Permit.Type is general, and has a vehicle, v, that is unparked
		ParkingSpot s4 = new ParkingSpot(4, Permit.Type.GENERAL);
		s4.occupySpot(v);
		verifySpot("s4", s4, 4, Permit.Type.GENERAL, v);
		
		v.setParked(true);
		verifySpot("s4", s4, 4, Permit.Type.GENERAL, v);
		v.setParked(false);
		
		s4.vacateSpot();
		verifySpot("s4", s4, 4, Permit.Type.GENERAL, null);
		
	}
}
