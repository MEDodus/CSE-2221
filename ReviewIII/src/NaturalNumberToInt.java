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
public final class NaturalNumberToInt extends NaturalNumber2 {

    /**
     * No-argument constructor.
     */
    public NaturalNumberToInt() {
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberToInt(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberToInt(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberToInt(NaturalNumber n) {
        super(n);
    }

    @Override
    public int toInt() {
        assert this.compareTo(new NaturalNumber2(Integer.MAX_VALUE)) <= 0;
        int digit = 0;
        int integer = 0;
        final int ten = 10;
        if (!this.isZero()) {
            digit = this.divideBy10();
            integer = this.toInt();
            integer = integer * ten;
            integer += digit;
        }
        this.multiplyBy10(digit);
        return integer;
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

        NaturalNumber test = new NaturalNumberToInt(16544);
        int integer = test.toInt();
        out.println(test);
        out.println(integer);

        in.close();
        out.close();
    }

}
