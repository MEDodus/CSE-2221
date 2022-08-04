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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        final int minLength = 8;
        final int passwordLength = s.length();
        final int start = 0;

        if (passwordLength < minLength) {
            out.println("Password must be at least 8 characters long. . .");
        } else {
            out.println("Password satisfies character length criteria.");
        }

        // Three conditions have not already been satisfied
        final int totalCriteria = 4;
        int[] criteriaArr = new int[totalCriteria];
        final int upperCaseIdx = 0;
        final int digitIdx = 1;
        final int lowerCaseIdx = 2;
        final int specialIdx = 3;
        final int trueValue = 1;

        String[] printFalseCriteria = {
                "Password does not contain an upper case letter. . .",
                "Password does not contain a digit character. . .",
                "Password does not contain a lower case letter. . .",
                "Password does not contain a special character [!@#$%^&*()_-+={}[]:;,.?]. . ." };

        String[] printTrueCriteria = {
                "Password satisfies upper case letter criteria.",
                "Password satisfies digit character criteria.",
                "Password satisfies lower case letter criteria.",
                "Password satisfies special character criteria. [!@#$%^&*()_-+={}[]:;,.?]" };

        /*
         *
         * I guess it doesn't matter if I use this to save space, but I used
         * inline if statements here to save space. Here's what I would do if I
         * rewrote them.
         *
         * if (passwordMethod(s)) { criteriaArr[passwordCriteriaIdx] = 1; } else
         * { criteriaArr[passwordCriteriaIdx = 0; }
         *
         */
        criteriaArr[upperCaseIdx] = (containsUpperCaseLetter(s)) ? 1 : 0;
        criteriaArr[digitIdx] = (containsDigitChar(s)) ? 1 : 0;
        criteriaArr[lowerCaseIdx] = (containsLowerCaseLetter(s)) ? 1 : 0;
        criteriaArr[specialIdx] = (containsSpecialCharacter(s)) ? 1 : 0;
        int criteriaSum = 0;

        for (int i = start; i < totalCriteria; i++) {
            criteriaSum += criteriaArr[i];
        }

        for (int k = start; k < totalCriteria; k++) {
            if (criteriaArr[k] == trueValue) {
                out.println(printTrueCriteria[k]);
            } else {
                out.println(printFalseCriteria[k]);
            }
        }

        final int minOfThree = 2;

        if ((criteriaSum >= minOfThree) && (passwordLength >= minLength)) {
            out.println("");
            out.println("Password was accepted");

        } else {
            out.println("");
            out.println("Password rejected.");
            out.println(
                    "Password must meet at least three of the criteria to be accepted.");
        }

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {
        final int passwordLength = s.length();
        final int start = 0;
        boolean containsUpperCase = false;

        for (int i = start; i < passwordLength; i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                containsUpperCase = true;
            }
        }

        return containsUpperCase;
    }

    /**
     * Checks if the given String contains a numeric digit.
     *
     * @param s
     *            the String to check
     * @return true if s contains a digit, false otherwise
     */
    private static boolean containsDigitChar(String s) {
        final int passwordLength = s.length();
        final int start = 0;
        boolean containsDigit = false;

        for (int i = start; i < passwordLength; i++) {
            if (Character.isDigit(s.charAt(i))) {
                containsDigit = true;
            }
        }

        return containsDigit;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains a lower case character, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {
        final int passwordLength = s.length();
        final int start = 0;
        boolean containsLowerCase = false;

        for (int i = start; i < passwordLength; i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                containsLowerCase = true;
            }
        }

        return containsLowerCase;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains a lower case character, false otherwise
     */
    private static boolean containsSpecialCharacter(String s) {
        final String specials = "!@#$%^&*()_-+={}[]:;,.?";
        final int start = 0;
        final int specialLength = specials.length();
        final int passwordLength = s.length();
        boolean containsSpecial = false;

        for (int i = start; i < specialLength; i++) {
            for (int k = start; k < passwordLength; k++) {
                if (s.charAt(k) == specials.charAt(i)) {
                    containsSpecial = true;
                }
            }
        }

        return containsSpecial;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.print("Enter a password: ");
        String password = in.nextLine();
        checkPassword(password, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
