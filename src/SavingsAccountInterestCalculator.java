import java.util.Scanner;

public class SavingsAccountInterestCalculator {
    public static void main(String[] args) {
        // Fixed interest rate
        final double INTEREST_RATE = 0.06;

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the deposit amount
        System.out.print("Enter the deposit amount: ");
        double depositAmount = scanner.nextDouble();

        // Calculate the interest
        double interest = depositAmount * INTEREST_RATE;

        // Calculate the total amount after one year
        double totalAmount = depositAmount + interest;

        // Display the result
        System.out.printf("After one year, you will have: £%.2f%n", totalAmount);

        // Close the scanner
        scanner.close();
    }
}
