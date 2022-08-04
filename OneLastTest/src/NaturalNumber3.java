import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class NaturalNumber3 extends NaturalNumber2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumber3() {
        super();
    }

    private NaturalNumber3(int i) {
        super(i);
    }

    @Override
    public int toInt() {
        int digit = 0;
        int report = 0;
        if (!this.isZero()) {
            digit = this.divideBy10();
            report = this.toInt();
            report = (report * 10) + digit;
        }
        return report;
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

        NaturalNumber n = new NaturalNumber3(1053);
        int result = n.toInt();
        out.println(result);
        NaturalNumber s = new NaturalNumber3(100);
        int result2 = s.toInt();
        out.println(result2);
        in.close();
        out.close();
    }

}
