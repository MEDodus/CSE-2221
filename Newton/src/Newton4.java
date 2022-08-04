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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
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
        out.println("Negatve values will have their abosolute value taken.");
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

        // Value to check if user input is greater than or equal to zero
        final double positive = 0.0;

        // If the user inputs a "y" character for String response this block will execute
        if (response.equals("y")) {
            out.print("Enter a double to take the square root of: ");
            double x = in.nextDouble();

            // Takes the square root if positive values and zero only
            while (x >= positive) {
                // Method call to get a user input error value
                double error = promptForError(in, out);

                // Method call to calculate the square root of x
                double r = sqrt(x, error);

                out.println("Square root estimate = " + r);
                out.print("Enter next double to take the square root of: ");
                x = in.nextDouble();

            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
