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
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    private static void swap(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber tmp = n1;
        n1 = n2;
        n2 = tmp;
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

        NaturalNumber x = new NaturalNumber1L(41);
        NaturalNumber y = new NaturalNumber1L(78);
        swap(x, y);

        out.println(x);
        out.println(y);
        in.close();
        out.close();
    }

}
