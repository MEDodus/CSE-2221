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
public final class Recursion {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Recursion() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int count = 1; // base case every NN has at least one digit
        n.divideBy10();
        if (!n.isZero()) {
            count = numberOfDigits(n);
            count++;
        }
        return count;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits1(NaturalNumber n) {
        int digit;
        int sum = 0;
        if (!n.isZero()) {
            digit = n.divideBy10();
            sum = sumOfDigits1(n);
            sum += digit;
        }
        return sum;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits2(NaturalNumber n) {
        NaturalNumber digit;
        NaturalNumber sum = new NaturalNumber2();
        if (!n.isZero()) {
            digit = new NaturalNumber2(n.divideBy10());
            sum = sumOfDigits2(n);
            sum.add(digit);
        }
        return sum;

    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        if (!n.isZero()) {
            int onesDigit = n.divideBy10();
            int tensDigit = n.divideBy10();
            int digit = 0;
            n.multiplyBy10(tensDigit);

            if (tensDigit % 2 != 0) {
                digit = (onesDigit / 2) + 5;
            } else {
                digit = onesDigit / 2;
            }

            divideBy2(n);
            n.multiplyBy10(digit);
        }

    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        boolean palindrome = false;
        String sLow = s.toLowerCase();
        if (s.length() > 2) {
            String sub = sLow.substring(1, sLow.length() - 2);
            palindrome = isPalindrome(sub);
        }
        palindrome = sLow.charAt(0) == sLow.charAt(sLow.length() - 1) ? true
                : false;
        return palindrome;
    }

    /**
     * Sums every odd digit.
     *
     * @param n
     *            {@code NaturalNumber} to be checked
     * @return the sum of all odd digits
     * @restores n
     * @ensures sumOdd = [sum of odd digits of n]
     */
    private static int sumOdd(NaturalNumber n) {
        int odd;
        int even;
        int sum = 0;
        if (!n.isZero()) {
            odd = n.divideBy10();
            even = n.divideBy10();
            sum = sumOdd(n);
            sum += odd;
            n.multiplyBy10(even);
            n.multiplyBy10(odd);
        }
        return sum;
    }

    /**
     * Checks is the one before the last character in a string is equal to the
     * first character.
     *
     * @param s
     *            {@code String} to be checked
     * @return boolean of whether they are equal
     * @ensures checkOdd = [last previous char is equals to first char]
     */
    private static boolean checkOdd(String s) {
        String sLow = s.toLowerCase();
        boolean odd = false;
        if (sLow.length() > 3) {
            String sub = sLow.substring(1, sLow.length() - 3);
            odd = checkOdd(sub);
        }
        if (sLow.length() > 1) {
            odd = sLow.charAt(0) == sLow.charAt(sLow.length() - 2) ? true
                    : false;
        }
        return odd;
    }

    /**
     * Sum all digits that are odd in a number.
     *
     * @param n
     *            the {@code NaturalNumber} to be summed
     * @updates n
     * @return the sum of the odd digits
     * @ensures n = [sum of odd digits] of #n
     */
    private static void sumOdds(NaturalNumber n) {
        int digit = n.divideBy10();
        if (!n.isZero()) {
            sumOdds(n);
        }
        if (digit % 2 != 0) {
            n.add(new NaturalNumber2(digit));
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
        sumOdds(test);
        out.println(test);

        in.close();
        out.close();
    }

}
