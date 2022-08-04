import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        boolean isInCircle = false;
        final int SQUARED = 2;
        if (Math.pow(xCoord - 1, SQUARED) + Math.pow(yCoord - 1, SQUARED) < 1) {
            isInCircle = true;
        }
        return isInCircle;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {

        Random rnd = new Random1L();
        int pointsInCircle = 0;
        int pointsInInterval = 0;

        while (pointsInInterval < n) {
            double xCoord = 2 * rnd.nextDouble();
            double yCoord = 2 * rnd.nextDouble();
            if (pointIsInCircle(xCoord, yCoord)) {
                pointsInCircle++;
            }
            pointsInInterval++;
        }
        return pointsInCircle;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        final int QUADRANTS = 4;
        int pointsInCircle = numberOfPointsInCircle(n);
        double estimateOfPi = QUADRANTS * (double) pointsInCircle / n;

        output.println("Estimate of pi: " + estimateOfPi);

        input.close();
        output.close();
    }

}
