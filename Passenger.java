// Passenger.java

public class Passenger {

    private String passengerId;
    private String name;
    private String phone;

    public Passenger(String passengerId, String name, String phone) {
        this.passengerId = passengerId;
        this.name = name;
        this.phone = phone;
    }

    // Encapsulation - getters
    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // Polymorphic method
    public double calculateFare(double baseFare) {
        return baseFare;
    }

    public String getPassengerType() {
        return "Regular";
    }

    public void displayPassenger() {
        System.out.println("----------------------------------------");
        System.out.println("Passenger ID : " + passengerId);
        System.out.println("Name         : " + name);
        System.out.println("Phone        : " + phone);
        System.out.println("Type         : " + getPassengerType());
        System.out.println("----------------------------------------");
    }
}


/*
 * Inheritance:
 * StudentPassenger IS-A Passenger
 */
class StudentPassenger extends Passenger {

    public StudentPassenger(String passengerId, String name, String phone) {
        super(passengerId, name, phone);
    }

    // Polymorphism
    @Override
    public double calculateFare(double baseFare) {
        return baseFare * 0.75;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}


/*
 * Inheritance:
 * RegularPassenger IS-A Passenger
 */
class RegularPassenger extends Passenger {

    public RegularPassenger(String passengerId, String name, String phone) {
        super(passengerId, name, phone);
    }

    @Override
    public double calculateFare(double baseFare) {
        return baseFare;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}