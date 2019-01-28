import java.util.ArrayList;

public class ParkingOfficer {
	
	/** the amount of time the officer will pause for after issuing a ticket*/
	private static final int PAUSE_TIME = 1000;
	
	/** the parking lot */
	private ParkingLot lot;
	
	/** the collection of tickets that have been issued to vehicles */
	private ArrayList<Ticket> tickets;
	
	/**
	 * Create the parking officer. Initially, there is no lot, and no
	 * tickets have been issued yet.
	 */
	public ParkingOfficer() {
		this.lot = null;
		this.tickets = new ArrayList <Ticket> ();
	}
	
	/**
	 * Determine the type of fine a vehicle *would* get if they parked 
	 * in a spot. If a vehicle is in a handicapped spot and either 
	 * doesn't have a permit, or a handicapped permit, the fine is 
	 * Fine.PARKING_HANDICAPPED. If a vehicle is in a reserved spot and 
	 * either doesn't have a permit, or a handicapped or reserved permit, 
	 * the fine is Fine.PARKING_RESERVED. If a vehicle is parked in a general
	 *  spot and does not have a permit, the fine is NO_PERMIT. Otherwise 
	 *  there is no fine, Fine.NO_FINE.
	 *  
	 * @param vehicle the vehicle (with or without a permit)
	 * @param spot the spot
	 * @return the fine for parking in that spot, Fine.NO_FINE if there is none
	 * <dt><b>Preconditions:</b><dd> the parking lot has been created, the vehicle has been created, the parking spot is in range
	 */
	public static Fine getFineVehicleSpot(Vehicle vehicle, ParkingSpot spot) {
		Permit permit = vehicle.getPermit();
		Permit.Type type = spot.getType();
		
		if (permit == null) {
			return Fine.NO_PERMIT;
		}
		else if ((permit == null || permit.getType() != Permit.Type.HANDICAPPED) && type==Permit.Type.HANDICAPPED) {
			return Fine.PARKING_HANDICAPPED;
		}
		else if ((permit == null || permit.getType() != Permit.Type.RESERVED) && type==Permit.Type.RESERVED) {
			if (permit.getType() == Permit.Type.HANDICAPPED) {
				return Fine.NO_FINE;
			}
			else {
				return Fine.PARKING_RESERVED;
			}
			
		}
		
		else {
			return Fine.NO_FINE;
		}
		
	}
	
	/**
	 * Connect the parking officer to the parking lot they will be responsible for patrolling.
	 * 
	 * @param lot the parking lot
	 * <dt><b>Preconditions:</b><dd> the lot has already been created (is not null)
	 */
	public void setParkingLot (ParkingLot lot) {
		this.lot = lot;
	}
	
	/**
	 * Get all the tickets the officer has issued.
	 * 
	 * @return the collection of tickets
	 */
	public ArrayList <Ticket> getTickets(){
		return tickets;
	}
	
	/**
	 * Issue a ticket to a vehicle with a fine.
	 * 
	 * @param vehicle the vehicle to get the ticket
	 * @param spot the spot number
	 * @param fine the fine
	 * <dt><b>Preconditions:</b><dd> the vehicle exists, there is a fine (not Fine.NO_FINE)
	 */
	private void issueTicket(Vehicle vehicle, int spot, Fine fine) {
		Ticket ticket = new Ticket(vehicle.getPlate(), fine);
		System.out.println("Issuing ticket to: " + vehicle + " in spot " + spot + " for " + fine);
		this.tickets.add(ticket);
		vehicle.giveTicket(ticket);
	}
	
	/**
	 * When the officer patrols the lot, they go to each spot (starting at the first until last).
	 * If there is a vehicle in the spot, they check whether a fine should be assessed
	 * If there is a fine (not Fine.NO_FINE), then a ticket should be issued,
	 * and then the officer should pause for a second.
	 */
	public void patrolLot() {
		for (int i = 0; i < lot.getCapacity(); i++) {
			ParkingSpot spot = lot.getSpot(i);
			if(lot.isSpotVacant(i)==false) {
				Fine fine = getFineVehicleSpot(spot.getVehicle(), spot);
				if (fine != Fine.NO_FINE) {
					issueTicket(spot.getVehicle(), i, fine);
					RITParking.pause(PAUSE_TIME);
				}
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		
		//Creates 5 new vehicles
		Vehicle v1 = new Vehicle (10);
		Vehicle v2 = new Vehicle (20);
		Vehicle v3 = new Vehicle (30);
		Vehicle v4 = new Vehicle (40);
		
		//Creates a permit with Type general
		Permit p1 = new Permit(0, Permit.Type.GENERAL);
		//Creates a permit with Type handicapped
		Permit p2 = new Permit(1, Permit.Type.HANDICAPPED);
		//Creates a permit with Type reserved
		Permit p3 = new Permit(2, Permit.Type.RESERVED);
		
		//Creates a lot with 4 handicapped spots, 26 reserved spots, and 70 general spots
		ParkingLot lot1 = new ParkingLot(4,26,70);
		ParkingOfficer officer = new ParkingOfficer();
		
		//Checks if there are no tickets
		System.out.println(
				"officer" + ": tickets=" + "[]" + "? " + (officer.getTickets() != null && officer.getTickets().size() == 0 ? "OK" : "FAIL, got: " + officer.getTickets()));
		//Sets the officer to patrol lot1
		officer.setParkingLot(lot1);
		
		//Set permits to each of the vehicles
		v2.setPermit(p1);
		v3.setPermit(p2);
		v4.setPermit(p3);
		
		//Parks v1 in a handicapped spot
		lot1.parkVehicle(v1, 0);
		//Parks v2 in a general spot
		lot1.parkVehicle(v2, 30);
		//Parks v3 in a handicapped spot
		lot1.parkVehicle(v3, 1);
		//Parks v4 in a reserved spot
		lot1.parkVehicle(v4, 5);
		
		//Have the officer patrol the lot
		officer.patrolLot();
		//Check to see if there are tickets issued
		System.out.println(
				"officer" + ": tickets!=" + "[]" + "? " + (officer.getTickets() != null && officer.getTickets().size() != 0 ? "OK" : "FAIL, got: " + officer.getTickets()));
		
		//Checks to see if a ticket was issued to v1
		System.out.println(
				"v1" + ": tickets!=" + "[]" + "? " + (v1.getTickets() != null && v1.getTickets().size() != 0 ? "OK" : "FAIL, got: " + v1.getTickets()));
		//Checks to see if no tickets were issued to v2, v3, and v4
		System.out.println(
				"v2" + ": tickets=" + "[]" + "? " + (v2.getTickets() != null && v2.getTickets().size() == 0 ? "OK" : "FAIL, got: " + v2.getTickets()));
		System.out.println(
				"v3" + ": tickets=" + "[]" + "? " + (v3.getTickets() != null && v3.getTickets().size() == 0 ? "OK" : "FAIL, got: " + v3.getTickets()));
		System.out.println(
				"v4" + ": tickets=" + "[]" + "? " + (v4.getTickets() != null && v4.getTickets().size() == 0 ? "OK" : "FAIL, got: " + v4.getTickets()));
		
		//Checks to see if the ticket issued has fine of NO_PERMIT
		System.out.println(
				"v1" + ": tickets.getFine()=" + "NO_PERMIT" + "? " + (v1.getTickets().get(0).getFine() == Fine.NO_PERMIT ? "OK" : "FAIL, got: " + v1.getTickets().get(0).getFine()));		
		
		//Unpark all of the vehicles
		lot1.removeVehicle(v1);
		lot1.removeVehicle(v2);
		lot1.removeVehicle(v3);
		lot1.removeVehicle(v4);
		
		//Parks v1 in a reserved spot
		lot1.parkVehicle(v1, 6);
		//Parks v2 in a handicapped spot
		lot1.parkVehicle(v2, 1);
		//Parks v3 in a reserved spot
		lot1.parkVehicle(v3, 5);
		//Parks v4 in a general spot
		lot1.parkVehicle(v4, 30);
		
		//Have the officer patrol the lot
		officer.patrolLot();
		
		//Checks to see if a ticket was issued to v2
		System.out.println(
				"v2" + ": tickets=" + "[]" + "? " + (v2.getTickets() != null && v2.getTickets().size() != 0 ? "OK" : "FAIL, got: " + v2.getTickets()));
		//Checks to see if no tickets were issued to v3 and v4
		System.out.println(
				"v3" + ": tickets=" + "[]" + "? " + (v3.getTickets() != null && v3.getTickets().size() == 0 ? "OK" : "FAIL, got: " + v3.getTickets()));
		System.out.println(
				"v4" + ": tickets=" + "[]" + "? " + (v4.getTickets() != null && v4.getTickets().size() == 0 ? "OK" : "FAIL, got: " + v4.getTickets()));
		
		//Checks to see if the ticket issued to v1 has fine of NO_PERMIT
		System.out.println(
				"v1" + ": tickets.getFine()=" + "NO_PERMIT" + "? " + (v1.getTickets().get(1).getFine() == Fine.NO_PERMIT ? "OK" : "FAIL, got: " + v1.getTickets().get(1).getFine()));		
		//Checks to see if the ticket issued to v2 has fine of PARKING_HANDICAPPED
		System.out.println(
				"v2" + ": tickets.getFine()=" + "PARKING_HANDICAPPED" + "? " + (v2.getTickets().get(0).getFine() == Fine.PARKING_HANDICAPPED ? "OK" : "FAIL, got: " + v2.getTickets().get(0).getFine()));
		
		//Unpark vehicles v1 and v2
		lot1.removeVehicle(v1);
		lot1.removeVehicle(v2);
		
		//Parks v1 in a general spot
		lot1.parkVehicle(v1, 31);
		//Parks v2 in a reserved spot
		lot1.parkVehicle(v2, 6);
		
		//Have the officer patrol the lot
		officer.patrolLot();
		
		//Checks to see if the ticket issued to v1 has fine of NO_PERMIT 
		System.out.println(
				"v1" + ": tickets.getFine()=" + "NO_PERMIT" + "? " + (v1.getTickets().get(2).getFine() == Fine.NO_PERMIT ? "OK" : "FAIL, got: " + v1.getTickets().get(2).getFine()));		
		//Checks to see if the ticket issued to v2 has fine of PARKING_RESERVED
		System.out.println(
				"v2" + ": tickets.getFine()=" + "PARKING_RESERVED" + "? " + (v2.getTickets().get(1).getFine() == Fine.PARKING_RESERVED ? "OK" : "FAIL, got: " + v2.getTickets().get(1).getFine()));
		
		//Checks to see if the total number of tickets issued is 5
		System.out.println(
				"officer" + ": tickets.size=" + "5" + "? " + (officer.getTickets() != null && officer.getTickets().size() == 5 ? "OK" : "FAIL, got: " + officer.getTickets().size()));
	}

}
