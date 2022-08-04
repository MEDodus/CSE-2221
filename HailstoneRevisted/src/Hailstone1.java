import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {
        NaturalNumber hailstone = new NaturalNumber1L(n);
        NaturalNumber remainder = new NaturalNumber1L();
        final NaturalNumber zero = new NaturalNumber1L();
        final NaturalNumber one = new NaturalNumber1L(1);
        final NaturalNumber two = new NaturalNumber1L(2);
        final NaturalNumber three = new NaturalNumber1L(3);

        out.print(hailstone + " ");
        while (hailstone.compareTo(one) > 0) {
            remainder = hailstone.divide(two);
            if (remainder.compareTo(zero) > 0) {
                hailstone.multiply(two);
                hailstone.add(remainder);
                hailstone.multiply(three);
                hailstone.add(one);
            }
            out.print(hailstone + " ");
        }

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

        out.print("Enter a number to generate a series: ");
        String strSeries = in.nextLine();
        NaturalNumber series = new NaturalNumber1L();

        while (!series.canSetFromString(strSeries)) {
            out.print("Enter a valid positive integer to generate a series: ");
            strSeries = in.nextLine();
        }
        series.setFromString(strSeries);

        generateSeries(series, out);

        in.close();
        out.close();
    }

}
