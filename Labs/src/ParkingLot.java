import java.util.ArrayList;

public class ParkingLot {
	
	/** an illegal spot number */
	public static final int ILLEGAL_SPOT = -1;
	
	/** when printing the lot, the number of spots to display per line */
	private static final int SPOTS_PER_LINE = 10;
	
	/**the total number of spots*/
	private int capacity;
	
	/** the number of general spots */
	private int generalSpots;
	
	/**	the number of handicapped spots */
	private int handicappedSpots;
	
	/**the collection of parking spots */
	private ArrayList <ParkingSpot> lot;
	
	/** the number of vehicles currently parked in the lot */
	private int parkedVehicles;
	
	/** the number of reserved spots */
	private int reservedSpots;
	
	/**
	 * Create a new parking lot.
	 * 
	 * @param handicappedSpots number of handicapped spots
	 * @param reservedSpots number of reserved spots
	 * @param generalSpots number of general spots
	 * <dt><b>Preconditions:</b><dd> the number of spots for each kind is non-negative
	 */
	public ParkingLot(int handicappedSpots, int reservedSpots, int generalSpots) {
		this.capacity = handicappedSpots + reservedSpots + generalSpots;
		this.generalSpots = generalSpots;
		this.handicappedSpots = handicappedSpots;
		this.parkedVehicles = 0;
		this.reservedSpots = reservedSpots;
		this.lot = new ArrayList <ParkingSpot> ();
		this.initializeSpots();
		
	}
	
	/**
	 * Create the parking spots for the lot. This is a helper method that
	 * is called by the constructor, after the lot has been created, to 
	 * initialize and add each new spot to the lot.
	 */
	private void initializeSpots() {
		int n = 0;
		for (int i = 0; i<this.handicappedSpots; i++) {
			this.lot.add(new ParkingSpot(n, Permit.Type.HANDICAPPED));
			n++;
		}
		for (int i = 0; i<this.reservedSpots; i++) {
			this.lot.add(new ParkingSpot(n, Permit.Type.RESERVED));
			n++;
		}
		for (int i = 0; i<this.generalSpots; i++) {
			ParkingSpot s = new ParkingSpot(n, Permit.Type.GENERAL);
			this.lot.add(s);
			n++;
		}
	}
	
	/**
	 * Get the total number of spots in the lot.
	 * 
	 * @return the lot's capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Get the number of spots that are occupied by vehicles.
	 * 
	 * @return the number of parked vehicles
	 */
	public int getNumParkedVehicles () {
		return parkedVehicles;
	}
	
	/**
	 * Tells whether a spot is in a valid range within the parking lot or not.
	 * 
	 * @param spot the spot to check
	 * @return whether the spot is in range or not
	 */
	public boolean isSpotValid(int spot) {
		boolean valid = false;
		for (ParkingSpot s : lot) {
			if (s.getSpot() == spot) {
				valid = true;
				break;
			}
		}
		return valid;
	}
	
	/**
	 * Get a parking spot.
	 * 
	 * @param spot the spot number
	 * @return the spot
	 * <dt><b>Preconditions:</b><dd> spot is within the range of the lot
	 */
	public ParkingSpot getSpot(int spot) {
		return lot.get(spot);
	}
	
	/**
	 * Is a parking spot open at the moment (not occupied by a vehicle)?
	 * 
	 * @param spot the spot to check
	 * @return true if the spot is open, false otherwise
	 * <dt><b>Preconditions:</b><dd> the spot is within the range of the lot
	 */
	public boolean isSpotVacant(int spot) {
		boolean vacant = false;
		ParkingSpot s = getSpot(spot);
		if (s.getVehicle() == null) {
			vacant = true;
		}
		return vacant;
	}
	
	/**
	 * Park a vehicle in a spot. If the spot is already occupied by another
	 * vehicle, it cannot be parked in this spot.
	 * 
	 * @param vehicle the vehicle to park
	 * @param spot the spot to park in
	 * @return whether the vehicle was parked in this spot or not
	 * <dt><b>Preconditions:</b><dd> the spot is within the range of the lot, the vehicle exists and is not already parked
	 */
	public boolean parkVehicle(Vehicle vehicle, int spot) {
		boolean parked = false;
		ParkingSpot s = getSpot(spot);
		s.occupySpot(vehicle);
		if (s.getVehicle()!=null) {
			parked = true;
		}
		return parked;
	}
	
	/**
	 * Remove a vehicle from a parked spot. If the vehicle is found in a parked spot in the lot, it is removed from the spot.
	 * 
	 * @param vehicle the vehicle to remove
	 * @return the spot the vehicle was removed from. if the vehicle was not parked in the lot it returns ILLEGAL_SPOT.
	 * <dt><b>Preconditions:</b><dd> the vehicle exists
	 */
	public int removeVehicle(Vehicle vehicle) {
		int spot = ILLEGAL_SPOT;
		boolean inLot = false;
		for (ParkingSpot s : lot) {
			if (s.getVehicle() == vehicle) {
				s.vacateSpot();
				spot = s.getSpot();
				inLot = true;
			}
		}
		if (inLot == false) {
			vehicle.setParked(false);
		}
		return spot;
	}
	
	@Override
	public String toString() {
		int n = 0;
		String str = "";
		for (int i = 0; i<lot.size(); i++) {
			if (n%SPOTS_PER_LINE==0) {
				str+="\n";
			}
			str+=this.lot.get(i)+" ";
			n++;
		}
		str+="\nVacant Spots: "+(this.capacity-this.parkedVehicles);
		return str;
	}
	

	public static void main(String [] args) {
		//Creates a new vehicle with plate 10
		Vehicle v1 = new Vehicle(10);
		//Create a new parking lot with 4 handicapped spots, 26 reserved spots, and 70 general spots
		ParkingLot lot1 = new ParkingLot(4, 26, 70);
		
		//Checks the capacity of the lot
		System.out.println(
				"lot1" + ": capacity=" + "100" + "? " + (lot1.getCapacity() == 100 ? "OK" : "FAIL, got: " + lot1.getCapacity()));
		//Checks to see if the number of parked vehicles is 0
		System.out.println(
				"lot1" + ": numParkedVehicles=" + "0" + "? " + (lot1.getNumParkedVehicles() == 0 ? "OK" : "FAIL, got: " + lot1.getNumParkedVehicles()));
		
		//Checks to see if spot 0 is valid
		System.out.println(
				"lot1" + ": isSpotValid(0)=" + "true" + "? " + (lot1.isSpotValid(0) ? "OK" : "FAIL, got: " + lot1.isSpotValid(0)));
		//Checks to see if spot 101 is valid
		System.out.println(
				"lot1" + ": isSpotValid(101)=" + "false" + "? " + (lot1.isSpotValid(101) ? "FAIL, got: " + lot1.isSpotValid(101) : "OK"));
		
		//Checks to see if spot 0 is vacant
		System.out.println(
				"lot1" + ": isSpotVacant(0)=" + "true" + "? " + (lot1.isSpotVacant(0) ? "OK" : "FAIL, got: " + lot1.isSpotVacant(0)));
		//Checks to see if vehicle was parked successfully in spot 0
		System.out.println(
				"lot1" + ": parkVehicle=" + "true" + "? " + (lot1.parkVehicle(v1, 0) ? "OK" : "FAIL, got: " + false));
		//Checks to see if spot 0 is not vacant
		System.out.println(
				"lot1" + ": isSpotVacant(0)=" + "false" + "? " + (lot1.isSpotVacant(0) == false ? "OK" : "FAIL, got: " + lot1.isSpotVacant(0)));
		
		//Create a new vehicle with plate 20
		Vehicle v2 = new Vehicle(20);
		//Set it so it is parked
		v2.setParked(true);
		//Checks to see if v1 was removed from spot 0
		System.out.println(
				"lot1" + ": removeVehicle=" + "0" + "? " + (lot1.removeVehicle(v1) == 0 ? "OK" : "FAIL, got: " + lot1.removeVehicle(v1)));
		//Checks to see if v2 was removed from an illegal spot
		System.out.println(
				"lot1" + ": removeVehicle=" + "-1" + "? " + (lot1.removeVehicle(v2) == -1 ? "OK" : "FAIL, got: " + lot1.removeVehicle(v2)));
		//Checks to see if spot 0 is vacant
		System.out.println(
				"lot1" + ": isSpotVacant(0)=" + "true" + "? " + (lot1.isSpotVacant(0) ? "OK" : "FAIL, got: " + lot1.isSpotVacant(0)));
		
		//Have the user visually verify that the lot1's toString is correct
		System.out.println("visually verify " + "lot1" + "'s toString: " + lot1);
		System.out.println();
		
	}
}
