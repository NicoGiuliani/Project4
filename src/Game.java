import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        playGame();
    }

    // Generates a random int between the max and min values
    private static int generateRandom(int min, int max) {
        Random random = new Random();
        return Math.abs(random.nextInt()) % (max - min + 1) + min;
    }

    private static double getShellDistance(double theta, double speed) {
        // Converts degrees into radians
        theta = (theta * Math.PI) / 180;
        return (Math.pow(speed, 2) * Math.sin(2 * theta)) / 9.8;
    }

    // returns a boolean to be used for the keepGoing variable
    private static boolean computerTurn(int[] enemyRange) {
        DecimalFormat df = new DecimalFormat("#.##");
        int angle = generateRandom(0, 90);
        int speed = generateRandom(0, 100);
        System.out.println("\nEnemy fired at " + angle + " degrees at " + speed + " m/s.");

        double shellDistance = getShellDistance(angle, speed);
        shellDistance = Double.parseDouble(df.format(shellDistance));

        System.out.println("\nShell exploded at " + shellDistance + " meters.");

        if (shellDistance >= enemyRange[0] && shellDistance <= enemyRange[1]) {
            System.out.println("Enemy was destroyed.");
            return false;
        } else {
            return true;
        }
    }

    private static void playGame() {
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        boolean keepGoing = true;
        // Player one is assumed to be at distance 0 since it sits at the origin

        // This is the distance between players 1 & 2
        int enemyDistance = generateRandom(50, 300);
        int radius = 5;
        int[] enemyRange = { enemyDistance - radius, enemyDistance + radius };

        do {
            String message = "\nEnemy artillery is at approximately " + enemyDistance + " yard(s).";
            String messageTwo = "\nEnemy range is between " + enemyRange[0] + " yard(s) and " + enemyRange[1] + " yard(s).";
            System.out.println(message);
            System.out.println(messageTwo);
            System.out.print("Enter an angle in degrees: ");
            double angle = scan.nextDouble();
            System.out.print("Enter a speed: ");
            double speed = scan.nextDouble();
            double shellDistance = getShellDistance(angle, speed);
            shellDistance = Double.parseDouble(df.format(shellDistance));
            System.out.println("\nShell exploded at " + shellDistance + " meters.");

            if (shellDistance >= enemyRange[0] && shellDistance <= enemyRange[1]) {
                System.out.println("\nEnemy was destroyed.");
                keepGoing = false;
            } else {
                // now computer should take a turn
                computerTurn(enemyRange);
            }


        } while (keepGoing);


    }


}
