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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        // Note: For future reference, if outside of the method we should be using ALL_CAPS naming convention on final types.
        //       When inside the method, we should using regular camelCase naming convention.
        // Final denomination values for each coin
        final int DOLLAR = 100;
        final int HALF_DOLLAR = 50;
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;
        final int PENNY = 1;

        // Final array to hold all coin denomination values
        final int[] CENTS = { DOLLAR, HALF_DOLLAR, QUARTER, DIME, NICKEL,
                PENNY };

        // Final array to hold all coin denomination names
        final String[] CENTS_NAME = { "Dollars", "Half dollars", "Quarters",
                "Dimes", "Nickels", "Pennies" };

        // Amount of denominations we will be using
        final int DENOMINATIONS = 6;

        // Array index to be looped through. Starts from zero.
        final int ARR_START = 0;
        final int ARR_END = CENTS.length - 1;

        // Array for counted number of change.
        int[] centsCount = new int[DENOMINATIONS];
        int cents;

        out.print("How much cents do you want to make change for? ");
        cents = in.nextInteger();

        out.print("CHANGE: ");
        for (int denom = ARR_START; denom < CENTS.length; denom++) {
            // Calculates amount of each denomination on each loop
            centsCount[denom] = cents / CENTS[denom];

            // Decreases based on amount of each denomination
            cents -= (centsCount[denom] * CENTS[denom]);

            // Formatting
            if (denom < ARR_END) {
                out.print(centsCount[denom] + " " + CENTS_NAME[denom] + ", ");
            } else {
                out.println(centsCount[denom] + " " + CENTS_NAME[denom] + ".");
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
