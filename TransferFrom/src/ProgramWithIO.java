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

    private static void swap(String x, String y) {
        String tmp = x;
        SimpleWriter out = new SimpleWriter1L();
        out.println(tmp == x);
        x = y;
        out.println(x == y);
        y = tmp;
        out.println(y == tmp);

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

//        out.print("Enter a number: ");
//        String num = in.nextLine();
//        NaturalNumber x = new NaturalNumber1L(num);
//        out.print("Enter another: ");
//        String num2 = in.nextLine();
//        NaturalNumber y = new NaturalNumber1L(num2);
//
//        out.println("x: " + x + " y: " + y);
//
//        // Check transferFrom
//        y.transferFrom(x);
//        out.println("x: " + x + " y: " + y);

        String x = "legends";
        String y = "leaders";
        swap(x, y);

        out.println(x);
        out.println(y);

        in.close();
        out.close();
    }

}
