// BookingThread.java

public class BookingThread extends Thread {

    private Passenger passenger;
    private Vehicle vehicle;
    private int seatNumber;

    public BookingThread(Passenger passenger,
                         Vehicle vehicle,
                         int seatNumber) {

        this.passenger = passenger;
        this.vehicle = vehicle;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {

        System.out.println(
            passenger.getName()
            + " -> attempting to book Seat "
            + seatNumber
            + "..."
        );

        try {

            Thread.sleep(300);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        /*
         * bookSeat() is synchronized in Vehicle.
         */
        boolean success = vehicle.bookSeat(seatNumber);

        if (success) {

            System.out.println(
                passenger.getName()
                + " -> Seat "
                + seatNumber
                + " BOOKED SUCCESSFULLY"
            );

        } else {

            System.out.println(
                passenger.getName()
                + " -> Seat "
                + seatNumber
                + " ALREADY BOOKED"
            );
        }
    }
}

