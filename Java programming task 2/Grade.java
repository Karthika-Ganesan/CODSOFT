import java.util.Scanner;  // Scanner class

public class Grade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // initialize scanner class to take input

        System.out.println("Enter the number of Subjects: ");
        int subjs = sc.nextInt();  // No. of subjects to be calculated
        sc.nextLine(); // consuming next line to avoid input issues

        String[] subjnames = new String[subjs];  // array to store subjects
        int[] marks = new int[subjs];            // array to store marks
        int totalmarks = 0;                      // tracking marks, initialize it as 0

        for (int i = 0; i < subjs; i++) {
            System.out.println("Enter your subject's name: ");
            subjnames[i] = sc.nextLine();  // store subject names inside i

            while (true) {  // infinite loop for input validation
                System.out.println("Enter marks for " + subjnames[i] + " (Out of 100):");import java.util.Scanner;

// Class for managing bank account
class BankAccount {
    private double balance;

    public BankAccount(double IB) {
        balance = IB; // initial balance
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class for ATM operations
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void PT() {
        Scanner sc = new Scanner(System.in);
        int choice;
        double amount;

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: Rs. " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: Rs. ");
                    amount = sc.nextDouble();
                    if (amount > 0) {
                        account.deposit(amount);
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Invalid deposit amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: Rs. ");
                    amount = sc.nextDouble();
                    if (amount > 0 && account.withdraw(amount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Invalid withdrawal amount or insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Main class
public class ATM_INTERFACE {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(10000); // Starting balance
        ATM atm = new ATM(userAccount);
        atm.PT();
    }
}

                marks[i] = sc.nextInt();
                sc.nextLine();

                if (marks[i] >= 0 && marks[i] <= 100) {  // validate marks in range 1 to 100
                    break;
                } else {
                    System.out.println("Invalid Marks! Please enter your marks between 1 to 100");
                }
            }

            totalmarks += marks[i];  // add to total marks
        }

        double avgper = (double) totalmarks / subjs;  // compute average percentage. Use double to avoid int division

        char grade;

        // assign grade based on marks
        if (avgper >= 90) {
            grade = 'A';
        } else if (avgper >= 80) {
            grade = 'B';
        } else if (avgper >= 70) {
            grade = 'C';
        } else if (avgper >= 60) {
            grade = 'D';
        } else if (avgper >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        // Output subject-wise marks
        System.out.println("\nSubject wise Marks: ");
        for (int i = 0; i < subjs; i++) {
            System.out.println(subjnames[i] + " : " + marks[i]);
        }

        // Print total marks, average percentage, grade
        System.out.println("\nTotal Marks: " + totalmarks);
        System.out.println("Average Marks: " + avgper + "%");
        System.out.println("Grade: " + grade);

        sc.close(); // close scanner
    }
}
