import java.util.HashMap;
import java.util.Scanner;

public class OnlineExamination {

    private HashMap<String, String> users = new HashMap<>();
    private HashMap<String, String> profiles = new HashMap<>();
    private HashMap<String, String> passwords = new HashMap<>();
    private String loggedInUser;
    private boolean isLoggedIn = false;

    // Storing questions, correct answers, and user's answers
    private String[] questions = {
        "Is Java a programming language? (true/false): ",
        "Is Python a reptile? (true/false): ",
        "Does 2+2 equal 4? (true/false): ",
        "Is the Earth flat? (true/false): ",
        "Is water wet? (true/false): "
    };
    private boolean[] correctAnswers = {true, false, true, false, true};
    private boolean[] userAnswers = new boolean[5];
    private int timeLimit; // Time limit for the exam

    public void signUp(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try a different one.");
        } else {
            users.put(username, password);
            profiles.put(username, "Default profile for " + username);
            passwords.put(username, password);
            System.out.println("Sign-up successful! You can now log in.");
        }
    }

    public void login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            isLoggedIn = true;
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    public void logout() {
        loggedInUser = null;
        isLoggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public void updateProfile(String newProfile) {
        if (isLoggedIn) {
            profiles.put(loggedInUser, newProfile);
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Please login to update your profile.");
        }
    }

    public void updatePassword(String newPassword) {
        if (isLoggedIn) {
            passwords.put(loggedInUser, newPassword);
            users.put(loggedInUser, newPassword); // Update the user's password
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Please login to update your password.");
        }
    }

    public void askQuestionsAndRecordAnswers(Scanner scanner) {
        if (isLoggedIn) {
            System.out.println("Please answer the following questions:");

            for (int i = 0; i < questions.length; i++) {
                System.out.print(questions[i]);
                userAnswers[i] = Boolean.parseBoolean(scanner.nextLine());
            }
        } else {
            System.out.println("Please login to participate in the exam.");
        }
    }

    public void startTimerAndAskQuestions(Scanner scanner) {
        if (isLoggedIn) {
            System.out.println("Timer started for " + timeLimit + " seconds.");
            
            Thread timerThread = new Thread(() -> {
                try {
                    Thread.sleep(timeLimit * 1000); // Simulate timer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Time's up! Auto-submitting your answers.");
                displayResults();
            });
            
            timerThread.start();

            askQuestionsAndRecordAnswers(scanner);

            try {
                timerThread.join(); // Wait for the timer to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please login to start the exam.");
        }
    }

    private void displayResults() {
        if (isLoggedIn) {
            System.out.println("\nYour exam results:");
            int correctCount = 0;

            for (int i = 0; i < questions.length; i++) {
                System.out.println(questions[i]);
                System.out.println("Your answer: " + (userAnswers[i] ? "true" : "false"));
                System.out.println("Correct answer: " + (correctAnswers[i] ? "true" : "false"));
                
                if (userAnswers[i] == correctAnswers[i]) {
                    System.out.println("Result: Correct\n");
                    correctCount++;
                } else {
                    System.out.println("Result: Incorrect\n");
                }
            }

            System.out.println("Total correct answers: " + correctCount + " out of " + questions.length);
        } else {
            System.out.println("Please login to see the results.");
        }
    }

    public static void main(String[] args) {
        OnlineExamination exam = new OnlineExamination();
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Welcome to the Online Examination System ===");

            while (true) {
                System.out.println("\n1. Sign Up");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline

                if (choice == 1) {
                    System.out.print("Enter username for sign up: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password for sign up: ");
                    String password = scanner.nextLine();
                    exam.signUp(username, password);
                } else if (choice == 2) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    exam.login(username, password);

                    if (exam.isLoggedIn) {
                        System.out.print("\nDo you want to update your profile? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new profile information: ");
                            String newProfile = scanner.nextLine();
                            exam.updateProfile(newProfile);
                        }

                        System.out.print("Do you want to update your password? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.nextLine();
                            exam.updatePassword(newPassword);
                        }

                        // Ask for the time dura2tion before starting the questions
                        System.out.print("Enter time duration (in seconds) for the exam: ");
                        exam.timeLimit = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        // Start the timer and ask the questions
                        exam.startTimerAndAskQuestions(scanner);

                        // Logout
                        System.out.print("Do you want to logout? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) {
                            exam.logout();
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}
