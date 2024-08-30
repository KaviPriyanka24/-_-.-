import java.util.Random;
import java.util.Scanner;

public class Level_2  {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int maxRounds = 3;  // Number of rounds
        int totalScore = 0;
        int maxAttempts = 3;  // Limit the number of attempts per round

        for (int round = 1; round <= maxRounds; round++) {
            System.out.println("Round " + round + " of " + maxRounds);
            int randomNumber = random.nextInt(100) + 1;  // Generate a random number between 1 and 100
            int attempts = 0;
            boolean isGuessed = false;

            while (attempts < maxAttempts) {
                System.out.print("Guess the number (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    totalScore += (maxAttempts - attempts + 1) * 10;  // Points based on remaining attempts
                    isGuessed = true;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("The number is higher than " + userGuess);
                } else {
                    System.out.println("The number is lower than " + userGuess);
                }
            }

            if (!isGuessed) {
                System.out.println("You've used all attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Round " + round + " finished.\n");
        }

        System.out.println("Game over! Your total score is: " + totalScore);
        scanner.close();
    }
}
