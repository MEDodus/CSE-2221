import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Review {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Review() {
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        NaturalNumber digit;
        NaturalNumber product = new NaturalNumber2(1);
        if (!n.isZero()) {
            digit = new NaturalNumber2(n.divideBy10());
            product = productOfDigits1(n);
            product.multiply(digit);
        }
        return product;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        int digit = 0;
        NaturalNumber product = new NaturalNumber2(1);
        if (!n.isZero()) {
            digit = n.divideBy10();
            product = productOfDigits2(n);
            product.multiply(new NaturalNumber2(digit));
        }
        n.multiplyBy10(digit);
        return product;
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        assert n.compareTo(new NaturalNumber2(Integer.MAX_VALUE)) <= 0;
        int digit = 0;
        int integer = 0;
        final int ten = 10;
        if (!n.isZero()) {
            digit = n.divideBy10();
            integer = toInt(n);
            integer = integer * ten;
            integer += digit;
        }
        n.multiplyBy10(digit);
        return integer;
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        boolean found = false;
        if (xml.isTag()) {
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                boolean search = findTag(xml.child(i), tag);
                if (search) {
                    found = search;
                }
            }
            if (xml.label().equals(tag)) {
                found = true;
            }

        }
        return found;
    }

    /**
     * Returns NaturalNumber divided by 3.
     *
     * @param n
     *            the {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 3 * n <= #n < 3 * (n + 1)
     */
    private static void divideBy3(NaturalNumber n) {
        int ones = n.divideBy10();
        int tens = n.divideBy10();
        int digit = 0;
        if (!n.isZero()) {
            divideBy3(n);
            digit = ((tens * 10) + ones) / 3;
            n.multiplyBy10(digit);
        }
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

        out.print("Enter a NN: ");
        NaturalNumber test = new NaturalNumber2(in.nextLine());
        NaturalNumber product = productOfDigits2(test);
        out.println(test);
        out.println(product);
        in.close();
        out.close();
    }

}
