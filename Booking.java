
    // Booking.java

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/*
 * Interface
 */
interface Bookable {

    void book();
}


/*
 * Interface
 */
interface Cancellable {

    void cancel();
}


/*
 * Enum
 */
enum TicketStatus {

    CONFIRMED,
    CANCELLED
}


public class Booking implements Bookable, Cancellable {

    private static int bookingCounter = 1000;

    private String bookingId;
    private Passenger passenger;
    private Vehicle vehicle;
    private int seatNumber;
    private double fare;
    private TicketStatus status;
    private LocalDateTime bookingTime;

    public Booking(Passenger passenger,
                   Vehicle vehicle,
                   int seatNumber,
                   double baseFare) {

        bookingCounter++;

        this.bookingId = "B" + bookingCounter;
        this.passenger = passenger;
        this.vehicle = vehicle;
        this.seatNumber = seatNumber;

        // Polymorphism
        this.fare = passenger.calculateFare(baseFare);

        this.status = TicketStatus.CONFIRMED;

        this.bookingTime = LocalDateTime.now();
    }

    // ---------------- GETTERS ----------------

    public String getBookingId() {
        return bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getFare() {
        return fare;
    }

    public TicketStatus getStatus() {
        return status;
    }

    // ---------------- INTERFACE METHODS ----------------

    @Override
    public void book() {
        status = TicketStatus.CONFIRMED;
    }

    @Override
    public void cancel() {
        status = TicketStatus.CANCELLED;
    }

    // ---------------- DISPLAY ----------------

    public void displayBooking() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println();
        System.out.println("============================================");
        System.out.println("              BOOKING DETAILS");
        System.out.println("============================================");

        System.out.println("Booking ID   : " + bookingId);
        System.out.println("Passenger    : " + passenger.getName());
        System.out.println("Passenger ID : " + passenger.getPassengerId());
        System.out.println("Vehicle      : " + vehicle.getVehicleNo());
        System.out.println("Type         : " + vehicle.getVehicleType());

        System.out.println(
            "Route        : " +
            vehicle.getSource() +
            " -> " +
            vehicle.getDestination()
        );

        System.out.println("Seat Number  : " + seatNumber);
        System.out.printf("Fare         : Rs. %.2f%n", fare);
        System.out.println("Status       : " + status);
        System.out.println(
            "Booking Time : " +
            bookingTime.format(formatter)
        );

        System.out.println("============================================");
    }
}


/*
 * Custom Exception
 */
class InvalidSeatException extends Exception {

    public InvalidSeatException(String message) {
        super(message);
    }
}