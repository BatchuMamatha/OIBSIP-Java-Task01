import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private Date dateOfJourney;
    private String from;
    private String to;

    public Reservation(String name, String trainNumber, String classType, Date dateOfJourney, String from, String to) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    // Getter and Setter methods for the Reservation fields

    public int getPNR() {
        // Implement your logic to generate and return a unique PNR number for the reservation.
        // This is just a placeholder method.
        return 12345;
    }

    @Override
    public String toString() {
        // Implement your logic to convert Reservation data to a formatted string representation.
        // This is just a placeholder method.
        return "Reservation details";
    }
}

public class Main {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        // Sample data for testing
        User user1 = new User("Admin", "admin123");
        User user2 = new User("Riya", "riya123");
        users.add(user1);
        users.add(user2);

        // Simulate user login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = null;
        for (User user : users) {
            if (user.authenticate(username, password)) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Invalid login credentials. Exiting the program.");
            return;
        }

        System.out.println("Login successful!");
        System.out.println();

        // Reservation form
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.println("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.println("Enter date of journey (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        Date dateOfJourney = null;
        try {
            dateOfJourney = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter source: ");
        String from = scanner.nextLine();
        System.out.println("Enter destination: ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(name, trainNumber, classType, dateOfJourney, from, to);
        reservations.add(reservation);
        System.out.println("Reservation successfully added!");
        System.out.println();

        // Cancellation form
        System.out.println("Cancellation Form: ");
        System.out.println("Enter your PNR number for cancellation: ");
        int pnr = scanner.nextInt();
        for (Reservation r : reservations) {
            if (r.getPNR() == pnr) {
                System.out.println("Cancellation details:");
                // Display cancellation details
                System.out.println(r.toString());
                System.out.println("Press 'OK' to confirm cancellation");
                String confirm = scanner.next();
                if (confirm.equalsIgnoreCase("OK")) {
                    reservations.remove(r);
                    System.out.println("Reservation successfully cancelled!");
                }
                break;
            }
        }
    }
}

