import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple Oddity program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class Oddity {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Oddity() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(12345 + 54321);
        out.close();
    }

}
