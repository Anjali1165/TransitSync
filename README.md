# TransitSync

## Railway and Bus Reservation System

TransitSync is a command-line based Railway and Bus Reservation System developed using Java. It allows passengers to register, log in, search buses and trains, check seat availability, book and cancel tickets, view booking history, and generate ticket details. It also includes an Admin module and a concurrent booking demonstration.

## Features

- Passenger registration and login
- Bus and train search
- Seat availability checking
- Ticket booking and cancellation
- Booking history and reports
- Admin login and management
- Multithreading and synchronization to prevent double booking

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections
- Exception Handling
- Multithreading
- Synchronization

## Project Structure

```text
TransitSync/
├── TransitSync.java
├── Passenger.java
├── Vehicle.java
├── Booking.java
├── BookingThread.java
└── README.md
```


## Environment Setup
1. Install JDK 17 or later.
2. Verify the Java installation:

```bash
java -version
javac -version
```

3. Clone the repository:

```bash
git clone https://github.com/Anjali1165/TransitSync.git
cd TransitSync
```


## Dependency Installation

No external dependencies or third-party libraries are required.

The project uses only standard Java libraries included with the JDK.

## Configuration

No additional configuration is required.

The application automatically loads sample buses and trains when it starts.

## Admin Login

Username: `admin`  
Password: `admin123`


## Execution
1. Open the terminal in the TransitSync project folder.
2. Compile all Java files:
```bash
javac *.java
```

3. Run the application:
```bash
java TransitSync
```

4. The TransitSync main menu will be displayed in the terminal.
5. Select the required option from the menu and follow the instructions shown on the screen.


## Usage

After starting the application, the main menu provides the following options:


- Passenger Login
- Register Passenger
- Admin Login
- Exit
  
Passengers can register or log in to search buses and trains, check seat availability, book and cancel tickets, view booking history, and generate ticket details.

Administrators can log in to view vehicles, bookings, passenger details, and reports.

## Results

The TransitSync application was successfully implemented and tested. All major features, including passenger registration, vehicle search, seat availability, ticket booking, cancellation, booking history, admin operations, and concurrent booking were tested successfully.

