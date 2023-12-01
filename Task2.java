import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter marks obtained (out of 100) in each subject:");

        // Define subjects
        String[] subjects = {"Physics", "Chemistry", "Maths", "Java Programming", "Algorithm", "DBMS"};

        // Initialize variables
        int totalMarks = 0;
        int numSubjects = subjects.length;

        // Take marks for each subject
        for (String subject : subjects) {
            System.out.print(subject + " :- ");
            int marks = scanner.nextInt();

            // Validate input marks
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Marks should be between 0 and 100.");
                return;
            }

            totalMarks += marks;
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Display total marks and average percentage
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");

        // Assign grades based on average percentage
        System.out.print("Grade: ");
        if (averagePercentage >= 90) {
            System.out.println("A+");
        } else if (averagePercentage >= 80) {
            System.out.println("A");
        } else if (averagePercentage >= 70) {
            System.out.println("B");
        } else if (averagePercentage >= 60) {
            System.out.println("C");
        } else if (averagePercentage >= 50) {
            System.out.println("D");
        } else if (averagePercentage >= 40) {
            System.out.println("E");
        } else {
            System.out.println("F");
        }

        // Close the scanner
        scanner.close();
    }
}
