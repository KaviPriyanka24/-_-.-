import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    String userID;
    String password;

    User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

class Reservation {
    String trainNumber;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnrNumber;

    Reservation(String trainNumber, String classType, String dateOfJourney, String from, String to, String pnrNumber) {
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnrNumber = pnrNumber;
    }

    void displayReservationDetails() {
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Class: " + classType);
        System.out.println("Date of Journey: " + dateOfJourney);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("PNR Number: " + pnrNumber);
    }
}
public class Level_1 {

    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        System.out.println("**Welcome to the Online Reservation System**");

        System.out.println("Do you have an account? (yes/no): ");
        String hasAccount = scanner.nextLine().trim().toLowerCase();

        if (hasAccount.equals("no")) {
            signUp(scanner);
        }

        System.out.println("Please log in.");
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();

        if (users.containsKey(userID)) {
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            User user = users.get(userID);

            if (user.authenticate(password)) {
                System.out.println("Login Successful!");

                boolean exit = false;

                while (!exit) {
                    System.out.println("h\n1. Make a Reservation");
                    System.out.println("\n2. Cancel a Reservation");
                    System.out.println("\n3. Exit");
                    System.out.print("Choose an option: ");
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (option) {
                        case 1:
                            makeReservation(scanner);
                            break;
                        case 2:
                            cancelReservation(scanner);
                            break;
                        case 3:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid option! Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid Password!");
            }
        } else {
            System.out.println("User ID not found!");
        }

        scanner.close();
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password123"));
        users.put("user2", new User("user2", "pass456"));
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter a new User ID: ");
        String newUserID = scanner.nextLine();

        System.out.print("Enter a new Password: ");
        String newPassword = scanner.nextLine();

        users.put(newUserID, new User(newUserID, newPassword));
        System.out.println("Sign up successful! You can now log in.");
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter Class Type (e.g., AC, Sleeper): ");
        String classType = scanner.nextLine();

        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter From (Place): ");
        String from = scanner.nextLine();

        System.out.print("Enter To (Destination): ");
        String to = scanner.nextLine();

        System.out.print("Enter PNR Number: ");
        String pnrNumber = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, classType, dateOfJourney, from, to, pnrNumber);
        reservations.put(pnrNumber, reservation);

        
        System.out.println("Reservation Successful!");
        reservation.displayReservationDetails();
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter PNR Number to Cancel: ");
        String pnrNumber = scanner.nextLine();

        if (reservations.containsKey(pnrNumber)) {
            reservations.remove(pnrNumber);
            System.out.println("Reservation with PNR " + pnrNumber + " has been successfully cancelled.");
        } else {
            System.out.println("No reservation found with PNR " + pnrNumber);
        }
    }
}

