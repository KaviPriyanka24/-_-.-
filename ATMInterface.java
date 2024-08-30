
import java.util.Scanner;

class ATM {
    private double balance;
    private String transactionHistory = "";

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public void startATM() {
        try (Scanner scanner = new Scanner(System.in)) {
            showMenu(scanner);
        }
    }

    public void showMenu(Scanner scanner) {
        int choice;

        do {
            System.out.println("\nATM INTERFACE");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw(scanner);
                    break;
                case 3:
                    deposit(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            System.out.println(transactionHistory);
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory += "Withdrawn: $" + amount + "\n";
            System.out.println("Withdrawal successful. New balance: $" + balance);
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        balance += amount;
        transactionHistory += "Deposited: $" + amount + "\n";
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter recipient's account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            transactionHistory += "Transferred: $" + amount + " to account " + accountNumber + "\n";
            System.out.println("Transfer successful. New balance: $" + balance);
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        ATM atm = new ATM(1000.00);  // Initialize ATM with $1000 balance
        atm.startATM();
    }
}
