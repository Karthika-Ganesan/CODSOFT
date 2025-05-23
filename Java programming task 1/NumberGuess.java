import java.util.Scanner;

public class NumberGuess {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int wins = 0, totalTries = 0;

        while (true) {
            System.out.print("Enter minimum number: ");
            int min = sc.nextInt();
            System.out.print("Enter maximum number: ");
            int max = sc.nextInt();

            int secret = (int) (Math.random() * (max - min + 1)) + min;
            int tries = 0;

            while (true) {
                System.out.print("Guess the number: ");
                int guess = sc.nextInt();
                tries++;

                if (guess < secret) {
                    System.out.println("Too low!");
                } else if (guess > secret) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You win!");
                    wins++;
                    break;
                }
            }

            totalTries += tries;
            System.out.println("Tries: " + tries);
            System.out.println("Total wins: " + wins);
            System.out.printf("Win rate: %.2f%%\n", (double) wins / totalTries * 100);

            System.out.print("Play again? (y/n): ");
            String play = sc.next();
            if (!play.equalsIgnoreCase("y")) {
                System.out.println("Thanks for playing!");
                break;
            }
        }

        sc.close();
    }
}
