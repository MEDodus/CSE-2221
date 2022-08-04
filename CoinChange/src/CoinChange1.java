import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculates greedy change remainder based on user input cents amount.
 *
 * @author Michael Dodus
 *
 */
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here
         */

        // Final values for all coin denominations
        final int DOLLAR = 100;
        final int HALF_DOLLAR = 50;
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;

        // Declaring variables . . .
        int cents, dollar, halfDollar, quarter, dime, nickel, penny;

        out.print("How much cents do you want to make change for? ");
        cents = in.nextInteger();

        // Calculates amount of each denomination of coin
        dollar = cents / DOLLAR;
        cents = cents - (dollar * DOLLAR);

        halfDollar = cents / HALF_DOLLAR;
        cents = cents - (halfDollar * HALF_DOLLAR);

        quarter = cents / QUARTER;
        cents = cents - (quarter * QUARTER);

        dime = cents / DIME;
        cents = cents - (dime * DIME);

        nickel = cents / NICKEL;
        cents = cents - (nickel * NICKEL);

        penny = cents;
        out.println(penny);

        out.println("CHANGE: " + dollar + " dollars, " + halfDollar
                + " half dollars, " + quarter + " quarters, " + dime
                + " dimes, " + nickel + " nickels, " + penny + " pennies.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
