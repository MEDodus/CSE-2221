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
public final class AddStrings {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private AddStrings() {
    }

    public String addStrings(String num1, String num2) {
        /*
         * "122,589", "1,502"
         *
         */
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        String result = "";
        int carry = 0;
        for (int i = 0; i < num1.length() || i < num2.length(); i++) {
            int c1 = 0;
            int c2 = 0;
            if (i < num1.length()) {
                c1 = num1.charAt(i) - '0';
            }
            if (i < num2.length()) {
                c2 = num2.charAt(i) - '0';
            }
            int sum = c1 + c2 + carry;
            int digit = sum % 10;
            carry = sum / 10;
            result = digit + result;
        }
        if (carry != 0) {
            result = carry + result;
        }
        return result;
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
        AddStrings strs = new AddStrings();
        out.print("Enter a string to input: ");
        String one = in.nextLine();
        out.print("Enter a string to input: ");
        String two = in.nextLine();
        String sum = strs.addStrings(one, two);
        out.println(sum);
        in.close();
        out.close();
    }

}
