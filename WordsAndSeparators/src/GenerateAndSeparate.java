import components.set.Set;
import components.set.Set1L;
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
public final class GenerateAndSeparate {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private GenerateAndSeparate() {
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!charSet.contains(str.charAt(i))) {
                charSet.add(str.charAt(i));
            } else {
                /*
                 * Replaces mode so must replace old reference of same value
                 * with the new one.
                 */
                charSet.remove(str.charAt(i));
                charSet.add(str.charAt(i));
            }
        }
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        int length = text.length();
        String wordSeparate = "";
        if (!separators.contains(text.charAt(position))) {
            boolean contains = false;
            while (!contains && position < length) {
                wordSeparate += text.charAt(position);
                contains = separators.contains(text.charAt(position));
                position++;
            }
        } else {
            boolean contains = true;
            while (contains && position < length) {
                wordSeparate += text.charAt(position);
                contains = separators.contains(text.charAt(position));
                position++;
            }
        }
        return wordSeparate;
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
        out.print("Enter a string: ");
        String input = in.nextLine();
        int position = 5;
        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add('/');
        separators.add(' ');
        separators.add('\\');
        String separate = nextWordOrSeparator(input, position, separators);
        out.println(separate);
        in.close();
        out.close();
    }

}
