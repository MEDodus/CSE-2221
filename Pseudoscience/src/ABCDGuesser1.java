import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program to calculate any given constant within 1% error based on the
 * "charming theory".
 *
 * @author Michael Dodus
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        final double zero = 0.0;

        out.print("Enter a positive real number: ");
        String numInput = in.nextLine();

        // Initialize to false value in terms of the while loop condition
        double positiveDouble = -1.0;

        // Checks if input is parsable into a double
        if (FormatChecker.canParseDouble(numInput)) {
            positiveDouble = Double.parseDouble(numInput);
        }

        // Prompts user to input a double greater than zero until input
        while (positiveDouble <= zero) {
            out.print("Enter a positive real number: ");
            numInput = in.nextLine();
            if (FormatChecker.canParseDouble(numInput)) {
                positiveDouble = Double.parseDouble(numInput);
            }
        }
        return positiveDouble;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        final double one = 1.0;
        final double zero = 0.0;

        out.print("Enter a positive real number not equal to 1.0: ");
        String numInput = in.nextLine();

        // Initialize to false value in terms of the while loop condition
        double positiveDblNotOne = -1.0;

        // Parses numInput String if it can be converted into a type double
        if (FormatChecker.canParseDouble(numInput)) {
            positiveDblNotOne = Double.parseDouble(numInput);
        }

        // Prompts user continuously until a value greater than zero and not one is input
        while (positiveDblNotOne == one || positiveDblNotOne <= zero) {
            out.print("Enter a positive real number not equal to 1.0: ");
            numInput = in.nextLine();
            if (FormatChecker.canParseDouble(numInput)) {
                positiveDblNotOne = Double.parseDouble(numInput);
            }
        }
        return positiveDblNotOne;
    }

    /**
     * Finds all combinations of exponents and returns the combination that has
     * the smallest relative error to the constant.
     *
     * @param abcd
     *            array of different values in the "charming theory"
     * @param constant
     *            user defined and input constant value we are trying to
     *            approximate with the "charming theory"
     * @param wxyz
     *            user defined and input values that will be used to calculate
     *            the "charming theory" solution
     * @return finalCombo an array with the combination with the least relative
     *         error of our constant value
     */
    private static double[] findCombinations(double[] abcd, double constant,
            double[] wxyz) {

        // Start index for our arrays to be used
        final int start = 0;

        // End length of the abcd array to be used when defining our end point
        // in our arrays
        final int abcdEnd = abcd.length;

        // Size of our combination array to be declared
        final int comboSize = 4;

        double[] combo = new double[comboSize];

        // Final combo will return the combination that minimizes the error
        double[] finalCombo = new double[comboSize];

        // Index's of the digits of the combinations in the array
        final int firstDigit = 0;
        final int secondDigit = 1;
        final int thirdDigit = 2;
        final int fourthDigit = 3;

        /*
         * Two error variables to hold our minimum and current error values to
         * be compared NOTE: Error takes the difference from the constant not
         * actual percent error, this will be done in main
         */
        double error = Double.MAX_VALUE;
        double minError = Double.MAX_VALUE;

        /*
         * Each for loop will iterate 17 times meaning at the end of all the for
         * loops executions we should have a combination array with [5.0, 5.0,
         * 5.0, 5,0]
         */
        int a, b, c, d, digits;
        a = start;
        while (a < abcdEnd) {
            combo[firstDigit] = abcd[a];
            b = start;
            while (b < abcdEnd) {
                combo[secondDigit] = abcd[b];
                c = start;
                while (c < abcdEnd) {
                    combo[thirdDigit] = abcd[c];
                    d = start;

                    // Goes through every combination in for the four digits
                    while (d < abcdEnd) {
                        combo[fourthDigit] = abcd[d];

                        // Calculates the error for all combinations
                        error = calcBestError(wxyz, combo, constant);
                        // Gets smallest difference between the constant value
                        if (error < minError) {
                            minError = error;
                            digits = start;
                            while (digits < comboSize) {
                                finalCombo[digits] = combo[digits];
                                digits++;
                            }
                        }
                        d++;
                    }
                    c++;
                }
                b++;
            }
            a++;
        }
        return finalCombo;
    }

    /**
     * Calculates the difference between the "constant" value and the actual
     * value from the given wxyz and combo arrays.
     *
     * @param combo
     *            array of different values in the "charming theory" to be used
     *            as exponents with wxyz
     * @param wxyz
     *            array of user input values with each raised to the exponents
     *            from the combo array
     * @param constant
     *            user defined constant we are seeking to approximate with the
     *            "charming theory"
     * @return the difference between the constant and actual value
     */
    private static double calcBestError(double[] wxyz, double[] combo,
            double constant) {

        // Start index for our arrays
        final int start = 0;

        // Size of our combination array "combo"
        final int comboSize = combo.length;

        // Initialized to 1.0 so that multiplication results
        // are unaffected by the initial guess value
        double guess = 1.0;

        // Difference between constant and actual value to be returned
        double error;

        // Formula ("charming theory"): (w^a) * (x^b) * (y^c) * (z^d)
        int digit = start;
        while (digit < comboSize) {
            guess *= (Math.pow(wxyz[digit], combo[digit]));
            digit++;
        }

        error = Math.abs(guess - constant);
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

        // Max relative error
        final double maxError = 1.0;

        // Start index for our arrays
        final int start = 0;

        // Size of our wxyz array to be declared
        final int wxyzSize = 4;

        // List of all possible exponent values in the "charming theory"
        final double[] abcd = { -5, -4, -3, -2, -1, -0.5, -1.0 / 3.0, -0.25, 0,
                0.25, 1.0 / 3.0, 0.5, 1, 2, 3, 4, 5 };
        double constant;
        double percentError;
        double[] wxyz = new double[wxyzSize];

        constant = getPositiveDouble(in, out);

        // Loops to get input for each input (index) to approximate the constant
        int numOrder = start;
        while (numOrder < wxyzSize) {
            wxyz[numOrder] = getPositiveDoubleNotOne(in, out);
            numOrder++;
        }

        // Gets the combination that minimizes the error between the constant
        // and actual value
        double[] finalCombo = findCombinations(abcd, constant, wxyz);

        // Prints the minimized exponent combination to the console
        int numExponent = start;

        // Prints combination in order of user inputs
        out.print("Best combination: ");
        while (numExponent < wxyzSize) {
            out.print(finalCombo[numExponent], 2, false);
            out.print(" ");
            numExponent++;
        }
        out.println("");

        /*
         * Written in calcBestError method as well, thus it is redundant but
         * since we cannot return multiple variables we will recalculate it here
         */
        int digit = start;
        double guess = 1.0;
        while (digit < wxyzSize) {
            guess *= (Math.pow(wxyz[digit], finalCombo[digit]));
            digit++;
        }
        out.print("Approximation of constant: ");
        out.print(guess, 2, false);
        out.println("");

        // Final int to convert decimal percentage into percentage value
        final int oneHundredPercent = 100;
        percentError = (Math.abs(guess - constant) / constant)
                * oneHundredPercent;

        // Prints percent error to hundredth of a percent
        out.print("Relative error: ");
        out.print(percentError, 2, false);
        out.print("%");
        out.println("");

        // Prints if our relative error is above the 1.0% threshold
        if (percentError > maxError) {
            out.print("Relative error is greater than 1.0% at ");
            out.print(percentError, 2, false);
        }

        in.close();
        out.close();
    }

}
