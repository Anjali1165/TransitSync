// Vehicle.java

import java.util.ArrayList;
import java.util.List;

enum VehicleType {
    BUS,
    TRAIN
}

enum SeatStatus {
    AVAILABLE,
    BOOKED
}

public class Vehicle {

    private String vehicleNo;
    private VehicleType vehicleType;
    private String source;
    private String destination;
    private int totalSeats;

    private ArrayList<SeatStatus> seats;

    public Vehicle(String vehicleNo,
                   VehicleType vehicleType,
                   String source,
                   String destination,
                   int totalSeats) {

        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;

        seats = new ArrayList<>();

        for (int i = 0; i < totalSeats; i++) {
            seats.add(SeatStatus.AVAILABLE);
        }
    }

    // ---------------- GETTERS ----------------

    public String getVehicleNo() {
        return vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // ---------------- SEAT METHODS ----------------

    public synchronized int getAvailableSeats() {

        int count = 0;

        for (SeatStatus status : seats) {

            if (status == SeatStatus.AVAILABLE) {
                count++;
            }
        }

        return count;
    }

    public synchronized void displaySeats() {

        System.out.println();
        System.out.println("------------- SEAT STATUS -------------");

        for (int i = 0; i < seats.size(); i++) {

            int seatNumber = i + 1;

            if (seats.get(i) == SeatStatus.AVAILABLE) {
                System.out.print("[" + seatNumber + ":A] ");
            } else {
                System.out.print("[" + seatNumber + ":B] ");
            }

            if (seatNumber % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("A = Available");
        System.out.println("B = Booked");
        System.out.println("---------------------------------------");
    }

    /*
     * synchronized method
     *
     * Only one thread can execute this method
     * on the same Vehicle object at one time.
     */
    public synchronized boolean bookSeat(int seatNumber) {

        if (seatNumber < 1 || seatNumber > totalSeats) {
            return false;
        }

        int index = seatNumber - 1;

        if (seats.get(index) == SeatStatus.BOOKED) {
            return false;
        }

        seats.set(index, SeatStatus.BOOKED);

        return true;
    }

    /*
     * Cancels a particular seat.
     */
    public synchronized boolean cancelSeat(int seatNumber) {

        if (seatNumber < 1 || seatNumber > totalSeats) {
            return false;
        }

        int index = seatNumber - 1;

        if (seats.get(index) == SeatStatus.AVAILABLE) {
            return false;
        }

        seats.set(index, SeatStatus.AVAILABLE);

        return true;
    }

    /*
     * Finds first available seat.
     */
    public synchronized int getFirstAvailableSeat() {

        for (int i = 0; i < seats.size(); i++) {

            if (seats.get(i) == SeatStatus.AVAILABLE) {
                return i + 1;
            }
        }

        return -1;
    }

    public void displayVehicle() {

        System.out.printf(
            "%-8s %-8s %-15s %-15s %3d/%-3d%n",
            vehicleNo,
            vehicleType,
            source,
            destination,
            getAvailableSeats(),
            totalSeats
        );
    }
}


/*
 * Inheritance example
 */
class Bus extends Vehicle {

    public Bus(String vehicleNo,
               String source,
               String destination,
               int totalSeats) {

        super(vehicleNo,
              VehicleType.BUS,
              source,
              destination,
              totalSeats);
    }
}


/*
 * Inheritance example
 */
class Train extends Vehicle {

    public Train(String vehicleNo,
                 String source,
                 String destination,
                 int totalSeats) {

        super(vehicleNo,
              VehicleType.TRAIN,
              source,
              destination,
              totalSeats);
    }
}