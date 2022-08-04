import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Michael Dodus
 *
 */
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer as well as outputting the length of the series and the max
     * integer of the series.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        /*
         * Put your code for generateSeries here
         */
        final int STOP_INT = 1;
        int seriesCount = 0;
        int maxInt = n;
        while (n > STOP_INT) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = (3 * n) + 1;
            }
            if (maxInt < n) {
                maxInt = n;
            }
            out.println("Hailstone series int \"n\" = " + n);
            seriesCount++;
        }
        out.println("Series length is: " + seriesCount);
        out.println("Series max is: " + maxInt);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.print("Enter input for a positive integer: ");
        final int zero = 0;
        String input = in.nextLine();
        boolean canParse = FormatChecker.canParseInt(input);
        int posInt = -1;
        if (canParse) {
            posInt = Integer.parseInt(input);
        }
        while (!canParse && (posInt <= zero)) {
            out.print("Enter input for a positive integer: ");
            input = in.nextLine();
            canParse = FormatChecker.canParseInt(input);
            if (canParse) {
                posInt = Integer.parseInt(input);
            }
        }
        return posInt;
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
         * Put your main program code here; it may call generateSeries as shown
         */
        int n = getPositiveInteger(in, out);
        generateSeries(n, out);
        out.println("Would you like to calculate another series? ");
        out.print("\"y\" for continue, any other input to quit: ");
        String calcSeries = in.nextLine();

        while (calcSeries.equals("y")) {
            out.print("Enter input for a positive integer: ");
            n = in.nextInteger();
            generateSeries(n, out);
            out.println("Would you like to calculate another series? ");
            out.print("\"y\" for continue, any other input to quit: ");
            calcSeries = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
