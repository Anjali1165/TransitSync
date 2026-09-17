// TransitSync.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class TransitSync {

    static Scanner sc = new Scanner(System.in);

    // Collections
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    // Stack for cancellation history
    static Stack<Booking> cancelledBookings = new Stack<>();

    // HashMap for passengers
    static HashMap<String, Passenger> passengers = new HashMap<>();

    static Passenger currentPassenger = null;

    static int passengerCounter = 100;


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        loadSampleVehicles();

        while (true) {

            showMainMenu();

            int choice = readInt("Enter Choice: ");

            switch (choice) {

                case 1:
                    passengerLogin();
                    break;

                case 2:
                    registerPassenger();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    System.out.println();
                    System.out.println("Thank you for using TransitSync!");
                    System.out.println("Goodbye.");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }


    // =====================================================
    // SAMPLE VEHICLES
    // =====================================================

    static void loadSampleVehicles() {

        vehicles.add(
            new Bus(
                "B102",
                "Delhi",
                "Jaipur",
                40
            )
        );

        vehicles.add(
            new Train(
                "T201",
                "Bhopal",
                "Varanasi",
                60
            )
        );

        vehicles.add(
            new Bus(
                "B303",
                "Indore",
                "Bhopal",
                35
            )
        );

        vehicles.add(
            new Train(
                "T401",
                "Delhi",
                "Mumbai",
                80
            )
        );
    }


    // =====================================================
    // MAIN MENU
    // =====================================================

    static void showMainMenu() {

        System.out.println();
        System.out.println("====================================================");
        System.out.println("          TRANSITSYNC RESERVATION SYSTEM");
        System.out.println("====================================================");

        System.out.println("1. Passenger Login");
        System.out.println("2. Register Passenger");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");

        System.out.println("====================================================");
    }


    // =====================================================
    // REGISTER PASSENGER
    // =====================================================

    static void registerPassenger() {

        System.out.println();
        System.out.println("------------- PASSENGER REGISTRATION --------------");

        String name = readLine("Enter Name       : ");
        String phone = readLine("Enter Phone      : ");

        System.out.println();
        System.out.println("Passenger Type:");
        System.out.println("1. Student");
        System.out.println("2. Regular");

        int type = readInt("Enter Choice     : ");

        passengerCounter++;

        String id = "P" + passengerCounter;

        Passenger passenger;

        if (type == 1) {

            passenger =
                new StudentPassenger(
                    id,
                    name,
                    phone
                );

        } else {

            passenger =
                new RegularPassenger(
                    id,
                    name,
                    phone
                );
        }

        passengers.put(id, passenger);

        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("Registration Successful!");
        System.out.println("Your Passenger ID : " + id);
        System.out.println("Name              : " + name);
        System.out.println("Type              : " +
                           passenger.getPassengerType());
        System.out.println("--------------------------------------------");
    }


    // =====================================================
    // PASSENGER LOGIN
    // =====================================================

    static void passengerLogin() {

        System.out.println();
        System.out.println("---------------- PASSENGER LOGIN ----------------");

        String id = readLine("Enter Passenger ID: ");

        Passenger passenger = passengers.get(id);

        if (passenger == null) {

            System.out.println();
            System.out.println("Passenger not found.");
            System.out.println("Please register first.");
            return;
        }

        currentPassenger = passenger;

        System.out.println();
        System.out.println(
            "Welcome, " + currentPassenger.getName() + "!"
        );

        passengerDashboard();
    }


    // =====================================================
    // PASSENGER DASHBOARD
    // =====================================================

    static void passengerDashboard() {

        while (currentPassenger != null) {

            System.out.println();
            System.out.println("--------------- DASHBOARD ----------------");

            System.out.println("1. View All Vehicles");
            System.out.println("2. Search Vehicles");
            System.out.println("3. View Available Seats");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Booking History");
            System.out.println("7. Generate Ticket");
            System.out.println("8. Reports");
            System.out.println("9. Concurrent Booking Demo");
            System.out.println("10. Logout");

            int choice = readInt("Enter Choice: ");

            switch (choice) {

                case 1:
                    viewVehicles();
                    break;

                case 2:
                    searchVehicles();
                    break;

                case 3:
                    viewSeats();
                    break;

                case 4:
                    bookTicket();
                    break;

                case 5:
                    cancelTicket();
                    break;

                case 6:
                    bookingHistory();
                    break;

                case 7:
                    generateTicket();
                    break;

                case 8:
                    reports();
                    break;

                case 9:
                    concurrentBookingDemo();
                    break;

                case 10:
                    System.out.println("Logged out successfully.");
                    currentPassenger = null;
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }


    // =====================================================
    // VIEW VEHICLES
    // =====================================================

    static void viewVehicles() {

        System.out.println();
        System.out.println(
            "================ AVAILABLE VEHICLES ================"
        );

        System.out.printf(
            "%-8s %-8s %-15s %-15s %s%n",
            "Number",
            "Type",
            "Source",
            "Destination",
            "Seats"
        );

        System.out.println(
            "----------------------------------------------------"
        );

        for (Vehicle v : vehicles) {
            v.displayVehicle();
        }

        System.out.println(
            "===================================================="
        );
    }


    // =====================================================
    // SEARCH VEHICLES
    // =====================================================

    static void searchVehicles() {

        System.out.println();
        System.out.println("---------------- SEARCH VEHICLES ----------------");

        String source =
            readLine("Enter Source      : ");

        String destination =
            readLine("Enter Destination : ");

        boolean found = false;

        System.out.println();

        System.out.printf(
            "%-8s %-8s %-15s %-15s %s%n",
            "Number",
            "Type",
            "Source",
            "Destination",
            "Seats"
        );

        System.out.println(
            "----------------------------------------------------"
        );

        for (Vehicle v : vehicles) {

            if (
                v.getSource().equalsIgnoreCase(source)
                &&
                v.getDestination().equalsIgnoreCase(destination)
            ) {

                v.displayVehicle();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicle found for this route.");
        }
    }


    // =====================================================
    // VIEW SEATS
    // =====================================================

    static void viewSeats() {

        viewVehicles();

        String vehicleNo =
            readLine("Enter Vehicle Number: ");

        Vehicle vehicle =
            findVehicle(vehicleNo);

        if (vehicle == null) {

            System.out.println("Vehicle not found.");
            return;
        }

        vehicle.displaySeats();
    }


    // =====================================================
    // BOOK TICKET
    // =====================================================

    static void bookTicket() {

        System.out.println();
        System.out.println("---------------- BOOK TICKET ----------------");

        viewVehicles();

        String vehicleNo =
            readLine("Enter Vehicle Number: ");

        Vehicle vehicle =
            findVehicle(vehicleNo);

        if (vehicle == null) {

            System.out.println("Vehicle not found.");
            return;
        }

        if (vehicle.getAvailableSeats() == 0) {

            System.out.println("No seats available.");
            return;
        }

        vehicle.displaySeats();

        int seatNumber =
            readInt("Enter Seat Number: ");

        try {

            if (
                seatNumber < 1
                ||
                seatNumber > vehicle.getTotalSeats()
            ) {

                throw new InvalidSeatException(
                    "Invalid seat number."
                );
            }

            boolean booked =
                vehicle.bookSeat(seatNumber);

            if (!booked) {

                System.out.println(
                    "Seat is already booked."
                );

                return;
            }

            double baseFare =
                calculateBaseFare(vehicle);

            Booking booking =
                new Booking(
                    currentPassenger,
                    vehicle,
                    seatNumber,
                    baseFare
                );

            bookings.add(booking);

            System.out.println();
            System.out.println(
                "Ticket booked successfully!"
            );

            booking.displayBooking();

        } catch (InvalidSeatException e) {

            System.out.println(
                "Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // CANCEL TICKET
    // =====================================================

    static void cancelTicket() {

        System.out.println();
        System.out.println("---------------- CANCEL TICKET ----------------");

        ArrayList<Booking> myBookings =
            getPassengerBookings();

        boolean found = false;

        for (Booking b : myBookings) {

            if (
                b.getStatus()
                == TicketStatus.CONFIRMED
            ) {

                found = true;

                System.out.println(
                    b.getBookingId()
                    + " | Vehicle: "
                    + b.getVehicle().getVehicleNo()
                    + " | Seat: "
                    + b.getSeatNumber()
                    + " | Status: "
                    + b.getStatus()
                );
            }
        }

        if (!found) {

            System.out.println(
                "No active bookings found."
            );

            return;
        }

        String bookingId =
            readLine("Enter Booking ID: ");

        Booking selected = null;

        for (Booking b : myBookings) {

            if (
                b.getBookingId().equalsIgnoreCase(bookingId)
                &&
                b.getStatus()
                == TicketStatus.CONFIRMED
            ) {

                selected = b;
                break;
            }
        }

        if (selected == null) {

            System.out.println(
                "Booking not found."
            );

            return;
        }

        boolean released =
            selected.getVehicle().cancelSeat(
                selected.getSeatNumber()
            );

        if (released) {

            selected.cancel();

            cancelledBookings.push(selected);

            System.out.println();
            System.out.println(
                "Ticket cancelled successfully."
            );

            System.out.println(
                "Booking ID : "
                + selected.getBookingId()
            );

            System.out.println(
                "Seat "
                + selected.getSeatNumber()
                + " is now available."
            );
        }
    }


    // =====================================================
    // BOOKING HISTORY
    // =====================================================

    static void bookingHistory() {

        System.out.println();
        System.out.println(
            "---------------- BOOKING HISTORY ----------------"
        );

        ArrayList<Booking> myBookings =
            getPassengerBookings();

        if (myBookings.isEmpty()) {

            System.out.println(
                "No booking history found."
            );

            return;
        }

        for (Booking b : myBookings) {

            System.out.println();
            System.out.println(
                "Booking ID : "
                + b.getBookingId()
            );

            System.out.println(
                "Vehicle    : "
                + b.getVehicle().getVehicleNo()
            );

            System.out.println(
                "Route      : "
                + b.getVehicle().getSource()
                + " -> "
                + b.getVehicle().getDestination()
            );

            System.out.println(
                "Seat       : "
                + b.getSeatNumber()
            );

            System.out.println(
                "Fare       : Rs. "
                + String.format("%.2f", b.getFare())
            );

            System.out.println(
                "Status     : "
                + b.getStatus()
            );

            System.out.println(
                "----------------------------------------"
            );
        }
    }


    // =====================================================
    // GENERATE TICKET
    // =====================================================

    static void generateTicket() {

        System.out.println();
        System.out.println(
            "---------------- GENERATE TICKET ----------------"
        );

        String bookingId =
            readLine("Enter Booking ID: ");

        Booking booking = findBooking(bookingId);

        if (booking == null) {

            System.out.println(
                "Booking not found."
            );

            return;
        }

        if (
            !booking.getPassenger()
                .getPassengerId()
                .equals(currentPassenger.getPassengerId())
        ) {

            System.out.println(
                "You cannot access this ticket."
            );

            return;
        }

        booking.displayBooking();

        System.out.println(
            "Ticket generated successfully."
        );
    }


    // =====================================================
    // REPORTS
    // =====================================================

    static void reports() {

        System.out.println();
        System.out.println(
            "================ REPORTS ================"
        );

        int confirmed = 0;
        int cancelled = 0;

        double revenue = 0;

        for (Booking b : bookings) {

            if (
                b.getStatus()
                == TicketStatus.CONFIRMED
            ) {

                confirmed++;
                revenue += b.getFare();

            } else {

                cancelled++;
            }
        }

        System.out.println(
            "Total Vehicles       : "
            + vehicles.size()
        );

        System.out.println(
            "Total Bookings       : "
            + bookings.size()
        );

        System.out.println(
            "Confirmed Bookings   : "
            + confirmed
        );

        System.out.println(
            "Cancelled Bookings   : "
            + cancelled
        );

        System.out.printf(
            "Total Revenue        : Rs. %.2f%n",
            revenue
        );

        System.out.println(
            "Cancelled Stack Size : "
            + cancelledBookings.size()
        );

        System.out.println(
            "=========================================="
        );
    }


    // =====================================================
    // MULTITHREADING DEMO
    // =====================================================

    static void concurrentBookingDemo() {

        System.out.println();
        System.out.println(
            "=========== CONCURRENT BOOKING DEMO ==========="
        );

        Vehicle vehicle = vehicles.get(0);

        System.out.println(
            "Vehicle : "
            + vehicle.getVehicleNo()
        );

        System.out.println(
            "Route   : "
            + vehicle.getSource()
            + " -> "
            + vehicle.getDestination()
        );

        int testSeat = 1;

        /*
         * Make sure Seat 1 is available before demo.
         */
        vehicle.cancelSeat(testSeat);

        Passenger p1 =
            new RegularPassenger(
                "D01",
                "Passenger A",
                "9000000001"
            );

        Passenger p2 =
            new RegularPassenger(
                "D02",
                "Passenger B",
                "9000000002"
            );

        Passenger p3 =
            new RegularPassenger(
                "D03",
                "Passenger C",
                "9000000003"
            );

        System.out.println();
        System.out.println(
            "Three passengers will try to book"
        );

        System.out.println(
            "the SAME seat simultaneously."
        );

        System.out.println();

        BookingThread t1 =
            new BookingThread(
                p1,
                vehicle,
                testSeat
            );

        BookingThread t2 =
            new BookingThread(
                p2,
                vehicle,
                testSeat
            );

        BookingThread t3 =
            new BookingThread(
                p3,
                vehicle,
                testSeat
            );

        /*
         * Start all threads.
         */
        t1.start();
        t2.start();
        t3.start();

        /*
         * Wait until all threads finish.
         */
        try {

            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println(
            "----------------------------------------------"
        );

        System.out.println(
            "Synchronization demonstration completed."
        );

        System.out.println(
            "Only one thread could book Seat "
            + testSeat
            + "."
        );

        System.out.println(
            "----------------------------------------------"
        );
    }


    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    static void adminLogin() {

        System.out.println();
        System.out.println(
            "---------------- ADMIN LOGIN ----------------"
        );

        String username =
            readLine("Username: ");

        String password =
            readLine("Password: ");

        if (
            username.equals("admin")
            &&
            password.equals("admin123")
        ) {

            System.out.println(
                "Admin login successful."
            );

            adminDashboard();

        } else {

            System.out.println(
                "Invalid admin credentials."
            );
        }
    }


    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    static void adminDashboard() {

        while (true) {

            System.out.println();
            System.out.println(
                "--------------- ADMIN DASHBOARD ---------------"
            );

            System.out.println("1. View Vehicles");
            System.out.println("2. View All Bookings");
            System.out.println("3. View Passenger Details");
            System.out.println("4. Reports");
            System.out.println("5. Logout");

            int choice =
                readInt("Enter Choice: ");

            switch (choice) {

                case 1:
                    viewVehicles();
                    break;

                case 2:
                    viewAllBookings();
                    break;

                case 3:
                    viewPassengers();
                    break;

                case 4:
                    reports();
                    break;

                case 5:
                    System.out.println(
                        "Admin logged out."
                    );
                    return;

                default:
                    System.out.println(
                        "Invalid Choice."
                    );
            }
        }
    }


    // =====================================================
    // VIEW ALL BOOKINGS
    // =====================================================

    static void viewAllBookings() {

        System.out.println();
        System.out.println(
            "---------------- ALL BOOKINGS ----------------"
        );

        if (bookings.isEmpty()) {

            System.out.println(
                "No bookings available."
            );

            return;
        }

        for (Booking b : bookings) {

            System.out.println(
                b.getBookingId()
                + " | "
                + b.getPassenger().getName()
                + " | "
                + b.getVehicle().getVehicleNo()
                + " | Seat "
                + b.getSeatNumber()
                + " | "
                + b.getStatus()
            );
        }
    }


    // =====================================================
    // VIEW PASSENGERS
    // =====================================================

    static void viewPassengers() {

        System.out.println();
        System.out.println(
            "---------------- PASSENGERS ----------------"
        );

        if (passengers.isEmpty()) {

            System.out.println(
                "No passengers registered."
            );

            return;
        }

        for (Passenger p : passengers.values()) {

            p.displayPassenger();
        }
    }


    // =====================================================
    // FIND VEHICLE
    // =====================================================

    static Vehicle findVehicle(String vehicleNo) {

        for (Vehicle v : vehicles) {

            if (
                v.getVehicleNo()
                    .equalsIgnoreCase(vehicleNo)
            ) {

                return v;
            }
        }

        return null;
    }


    // =====================================================
    // FIND BOOKING
    // =====================================================

    static Booking findBooking(String bookingId) {

        for (Booking b : bookings) {

            if (
                b.getBookingId()
                    .equalsIgnoreCase(bookingId)
            ) {

                return b;
            }
        }

        return null;
    }


    // =====================================================
    // GET CURRENT PASSENGER BOOKINGS
    // =====================================================

    static ArrayList<Booking> getPassengerBookings() {

        ArrayList<Booking> result =
            new ArrayList<>();

        for (Booking b : bookings) {

            if (
                b.getPassenger()
                    .getPassengerId()
                    .equals(
                        currentPassenger.getPassengerId()
                    )
            ) {

                result.add(b);
            }
        }

        return result;
    }


    // =====================================================
    // BASE FARE
    // =====================================================

    static double calculateBaseFare(Vehicle vehicle) {

        if (
            vehicle.getVehicleType()
            == VehicleType.BUS
        ) {

            return 400;

        } else {

            return 700;
        }
    }


    // =====================================================
    // INTEGER INPUT
    // =====================================================

    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                    sc.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                    "Please enter a valid number."
                );
            }
        }
    }


    // =====================================================
    // STRING INPUT
    // =====================================================

    static String readLine(String message) {

        System.out.print(message);

        return sc.nextLine().trim();
    }
}