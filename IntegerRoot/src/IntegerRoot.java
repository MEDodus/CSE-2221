import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Compute integer power with interval halving and test it.
 *
 * @author Put your name here
 *
 */
public final class IntegerRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private IntegerRoot() {
    }

    /**
     * Returns {@code n} to the power {@code p}.
     *
     * @param n
     *            the number to which we want to apply the power
     * @param p
     *            the power
     * @return the number to the power
     * @requires Integer.MIN_VALUE <= n ^ (p) <= Integer.MAX_VALUE and p >= 0
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int result = 1, count = 0;
        while (count < p) {
            result *= n;
            count++;
        }
        return result;
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {

        int tooLow = 0;
        int tooHigh = n + 1;
        int mid = (tooLow + tooHigh) / 2;
        int midPwr = power(mid, r);
        int midOnePwr = power(midOne, r);
        if (n == 1) {
            mid = 1;
        } else {
            while (tooHigh - tooLow > 1) {
                if (midPwr > n) {
                    mid = (tooLow + tooHigh) / 2;
                    tooHigh = mid;
                    midOne = mid + 1;
                    midOnePwr = power(midOne, r);
                    midPwr = power(mid, r);
                } else {
                    mid = (tooLow + tooHigh) / 2;
                    tooLow = mid;
                    midPwr = power(mid, r);
                    midOne = mid + 1;
                    midOnePwr = power(mid + 1, r);
                }
            }
        }
        return mid;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final int[] numbers = { 0, 0, 0, 1, 1, 1, 82, 82, 82, 82, 82, 3, 9, 27,
                81, 243 };
        final int[] roots = { 1, 2, 5, 1, 2, 17, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 };
        final int[] results = { 0, 0, 0, 1, 1, 1, 82, 9, 4, 3, 2, 3, 3, 3, 3,
                3 };

        for (int i = 0; i < numbers.length; i++) {
            int x = root(numbers[i], roots[i]);
            if (x == results[i]) {
                out.println("Test passed: root(" + numbers[i] + ", " + roots[i]
                        + ") = " + results[i]);
            } else {
                out.println("*** Test failed: root(" + numbers[i] + ", "
                        + roots[i] + ") expected <" + results[i] + "> but was <"
                        + x + ">");
            }
        }

        out.close();
    }

}
