import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Michael Dodus
 *
 */
public final class TermBuilder {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TermBuilder() {
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
        SimpleWriter terms = new SimpleWriter1L("doc/terms2.txt");
        out.print("Press q to quit or any key to continue: ");
        while (!in.nextLine().equals("q")) {
            out.print("Enter a term: ");
            terms.println(in.nextLine());
            out.print("Enter a definition: ");
            terms.print(in.nextLine());
            terms.println();
        }
        in.close();
        out.close();
    }

}
