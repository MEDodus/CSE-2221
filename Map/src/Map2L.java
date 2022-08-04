import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;

/**
 * Put a short phrase describing the program here.
 *
 * @author Michael Dodus
 *
 */
public final class Map2L {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Map2L() {
        super();
    }

    /**
     * Inputs a "menu" of words (items) and their prices from the given file and
     * stores them in the given {@code Map2L}.
     *
     * @param fileName
     *            the name of the input file
     * @param priceMap
     *            the word -> price map
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "word" (unique in the file) and one price (in cents)
     *  per line, with word and price separated by ','; the "word" may
     *  contain whitespace but no ',']
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    private static void getPriceMap(String fileName,
            Map<String, Integer> priceMap) {
        SimpleReader in = new SimpleReader1L(fileName);
        /*
         * Confused on how to read until empty input. Standard Java has method
         * hasNext() but SimpleReader does not.
         */

        while (!in.atEOS()) {
            String line = in.nextLine();
            int separate = line.indexOf(',');
            if (separate == -1) {
                separate = line.indexOf(' ');
            }
            String key = line.substring(0, separate);
            String value = line.substring(separate + 1);
            if (!priceMap.hasKey(key)) {
                priceMap.add(key, Integer.parseInt(value));
            }
        }
    }

    /**
     * Input one pizza order and compute and return the total price.
     *
     * @param input
     *            the input stream
     * @param sizePriceMap
     *            the size -> price map
     * @param toppingPriceMap
     *            the topping -> price map
     * @return the total price (in cents)
     * @updates input
     * @requires <pre>
     * input.is_open and
     * [input.content begins with a pizza order consisting of a size
     *  (something defined in sizePriceMap) on the first line, followed
     *  by zero or more toppings (something defined in toppingPriceMap)
     *  each on a separate line, followed by an empty line]
     * </pre>
     * @ensures <pre>
     * input.is_open and
     * #input.content = [one pizza order (as described
     *              in the requires clause)] * input.content and
     * getOneOrder = [total price (in cents) of that pizza order]
     * </pre>
     */
    private static int getOneOrder(SimpleReader input,
            Map<String, Integer> sizePriceMap,
            Map<String, Integer> toppingPriceMap) {

        int sizePrice = 0;
        int toppingsPrice = 0;
        while (!input.atEOS()) {
            String line = input.nextLine();

            if (sizePriceMap.hasKey(line)) {
                sizePrice = sizePriceMap.value(line);
            } else if (toppingPriceMap.hasKey(line)) {
                toppingsPrice += toppingPriceMap.value(line);
            } else {
                Reporter.fatalErrorToConsole(
                        "Cannot find specified size/topping. Exiting program...");
            }
        }
        return (sizePrice + toppingsPrice);
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

        out.print("Enter a file name: ");
        String fileName = in.nextLine();

        Map<String, Integer> sizePriceMap = new Map1L<>();
        sizePriceMap.add("large", 14);
        sizePriceMap.add("medium", 11);
        sizePriceMap.add("small", 9);

        Map<String, Integer> toppingPriceMap = new Map1L<>();
        toppingPriceMap.add("pepperoni", 1);
        toppingPriceMap.add("bacon", 2);
        toppingPriceMap.add("chicken", 3);
        toppingPriceMap.add("banana pepper", 1);
        toppingPriceMap.add("sausage", 2);
        toppingPriceMap.add("ranch", 1);

        out.println(getOneOrder(new SimpleReader1L(fileName), sizePriceMap,
                toppingPriceMap));
        in.close();
        out.close();
    }

}
