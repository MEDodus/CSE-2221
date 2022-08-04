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
public final class Invariant {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Invariant() {
    }

    //NN this is 1 initially
    //Seq<String> seq = {5,3,4,6}
    /*
     * this = #this.multiply(#s) while (s.length() > 0) { ... }
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        in.close();
        out.close();
    }

}
