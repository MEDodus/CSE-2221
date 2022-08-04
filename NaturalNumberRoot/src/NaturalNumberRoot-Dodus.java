import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // Copy NaturalNumber n so that we can have a constant to compare
        // our updated NaturalNumber n to
        final int zero = 0;
        final NaturalNumber nCopy = new NaturalNumber2(n); // Unchanged n value
        final NaturalNumber two = new NaturalNumber2(2); // For division
        // tooLow guess will be 0
        NaturalNumber tooLow = new NaturalNumber2();
        // tooHigh guess will be n + 1 initially
        NaturalNumber tooHigh = new NaturalNumber2(n);
        tooHigh.increment();
        // mid guess will be tooLow + tooHigh / 2
        NaturalNumber mid = new NaturalNumber2();
        // n + 1 NaturalNumber
        NaturalNumber nPlusOne = new NaturalNumber2(n);

        // Set mid to average between tooHigh and tooLow
        mid.add(tooHigh);
        mid.divide(two);

        // Get first root guess
        n.copyFrom(mid);
        n.power(r);
        nPlusOne.increment();
        nPlusOne.power(r);

        // Checks (n + 1)^r <= 0 || n^r > 0
        // root answer will be bounded between these conditions
        while ((nPlusOne.compareTo(nCopy) <= zero)
                || (n.compareTo(nCopy) > zero)) {

            if (n.compareTo(nCopy) > zero) {

                // PREVIOUS IMPLEMENTATION USING COPYFROM
                //  tooHigh.copyFrom(mid);
                //  mid.add(tooLow);
                //  mid.divide(two);

                tooHigh.transferFrom(mid);
                mid.add(tooHigh);
                mid.add(tooLow);
                mid.divide(two);
            } else if (n.compareTo(nCopy) < zero) {

                // PREVIOUS IMPLEMENTATION USING COPYFROM
                //  tooLow.copyFrom(mid);
                //  mid.add(tooHigh);
                //  mid.divide(two);

                tooLow.transferFrom(mid);
                mid.add(tooLow);
                mid.add(tooHigh);
                mid.divide(two);
            }

            /*
             * Could use transferFrom and then add tooLow and tooHigh and divide
             * to get mid again after the method calls on n and nPlusOne.
             */

            n.transferFrom(mid);
            n.power(r);

            mid.add(tooLow);
            mid.add(tooHigh);
            mid.divide(two);

            nPlusOne.transferFrom(mid);
            nPlusOne.increment();
            nPlusOne.power(r);

            mid.add(tooLow);
            mid.add(tooHigh);
            mid.divide(two);
        }
        n.transferFrom(mid);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
