import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Michael Dodus
 *
 */
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
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
        out.print("Enter input for a positive integer: ");
        int n = in.nextInteger();
        generateSeries(n, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
