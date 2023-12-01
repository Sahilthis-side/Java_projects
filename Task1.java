import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        // Set the range for the random number
        int lowerLimit = 1;
        int upperLimit = 100;

        // Set the number of attempts allowed
        int maxAttempts = 10;
        int attempts = 0;

        // Set the initial score
        int score = 0;

        do {
            // Generate a random number
            int secretNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;

            System.out.println("\nNew Round!");

            while (attempts < maxAttempts) {
                // Prompt the user to enter their guess
                System.out.print("Guess the number (" + lowerLimit + "-" + upperLimit + "): ");

                try {
                    int userGuess = scanner.nextInt();

                    // Compare the user's guess with the generated number
                    if (userGuess == secretNumber) {
                        System.out.println("Congratulations! You guessed the correct number (" + secretNumber + ")!");
                        score++;
                        break;
                    } else if (userGuess < secretNumber) {
                        System.out.println("Too low. Try again.");
                    } else {
                        System.out.println("Too high. Try again.");
                    }

                    attempts++;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    scanner.next(); // Clear the invalid input from the scanner
                }
            }

            // Check if the user wants to play another round
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();

            if (playAgain.equals("no")) {
                break;
            }

            attempts = 0; // Reset attempts for the new round
            clearScreen();
        } while (true);

        // Display the final score
        System.out.println("Your final score is: " + score);

        // Close the scanner
        scanner.close();
    }
}
