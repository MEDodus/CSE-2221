import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program to compute an estimate within a specified error of the square root of
 * a number.
 *
 * @author Michael Dodus
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param error
     *            error value input by the user for the square root to be within
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        // Error for the estimate to be within
        final double constantError = error;

        // Integer to square a value with Math.pow() method
        final int squared = 2;

        // If the user tries to calculate the square root of zero
        final double zero = 0.0;

        // Initialize r to x
        double r = x;

        if (x != zero) {
            while ((Math.abs(Math.pow(r, squared) - x) / x) >= Math
                    .pow(constantError, squared)) {
                /*
                 * If greater or less than the actual square root +
                 * errorConstant, r will converge to the correct square root
                 * value
                 */
                r = (r + (x / r)) / 2;
            }
        }

        return r;
    }

    /**
     * Prompts user for positive double until entered.
     *
     * @param in
     *            SimpleReader to get user input from
     * @param out
     *            SimplerWriter to write to console
     * @return positive double value
     */
    private static double promptForDouble(SimpleReader in, SimpleWriter out) {
        // Value that input must be greater than or equal to to compute sqrt
        final double negativeDouble = 0.0;

        out.print("Enter a positive double: ");
        double x = in.nextDouble();

        // Prompts for double until greater than negativeDouble
        while (x < negativeDouble) {
            out.println("Double is not positive.");
            out.print("Enter a positive double: ");
            x = in.nextDouble();
        }
        return x;
    }

    /**
     * Prompts user to input an error value for the square root function.
     *
     * @param in
     *            SimpleReader to get user input from
     * @param out
     *            SimpleWriter to write to console
     * @return error value to be used in sqrt() method
     */
    private static double promptForError(SimpleReader in, SimpleWriter out) {
        // Prompts user for double value for the error
        out.println("Negative values will have their abosolute value taken.");
        out.print(
                "Within what error would you like to calculate the square root: ");
        double errorInput = in.nextDouble();
        double error = Math.abs(errorInput);
        return error;
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

        // Prompt user to calculate a square root
        out.print("Would you like to calculate a square root? ");
        String response = in.nextLine();

        /*
         * Calculates a square root input by the user until a character other
         * than "y" is input
         */
        while (response.equals("y")) {

            // Method call to get a positive double
            double x = promptForDouble(in, out);

            // Method call to get a user input error value
            double error = promptForError(in, out);

            // Method call to calculate the square root of x
            double r = sqrt(x, error);

            out.println("Square root estimate = " + r);
            out.print("Would you like to calculate another square root? ");
            response = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
