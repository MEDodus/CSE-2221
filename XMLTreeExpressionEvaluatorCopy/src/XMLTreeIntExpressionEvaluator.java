import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // For children counts and array indexing
        final int zero = 0;
        final int one = 1;

        // For checking tags of a node
        final String times = "times";
        final String divide = "divide";
        final String plus = "plus";
        final String minus = "minus";

        // Attribute name to find
        final String value = "value";

        // Check if the tree's root is a tag and how many children it has
        boolean hasTag = exp.isTag();
        int children = exp.numberOfChildren();

        // Check if the specific tag has an attribute named value and get that value
        boolean hasAttribute = exp.hasAttribute(value);
        int getValue = 0;
        if (hasAttribute) {
            getValue = Integer.parseInt(exp.attributeValue(value));
        }

        // Get label of whatever root node or text node we are at
        String label = exp.label();

        // Integer to return our current expression value to
        int numTagValue = one;
        int count = zero;
        int timesConst = one;
        int plusSum = zero;
        int minus1 = zero;
        int minus2 = zero;
        int divide1 = zero;
        int divide2 = zero;

        // We know that this XMLTree will be rooted at a tag other than <number>
        if (hasTag && (children >= one)) {

            // Recurse for every child of the root of the tree
            for (int childIdx = zero; childIdx < children; childIdx++) {
                // Every value should get returned via this call
                numTagValue = evaluate(exp.child(childIdx));
                if (label.equals(times)) {
                    timesConst *= numTagValue;
                    numTagValue = timesConst;
                } else if (label.equals(plus)) {
                    plusSum += numTagValue;
                    numTagValue = plusSum;
                } else if (label.equals(minus)) {
                    if (count == zero) {
                        minus1 = numTagValue;
                        // numTagValue = minus1;
                    } else {
                        minus2 = numTagValue;
                        numTagValue = minus1 - minus2;
                    }
                } else if (label.equals(divide)) {
                    if (count == zero) {
                        divide1 = numTagValue;
                        // numTagValue = divide1;
                    } else {
                        divide2 = numTagValue;
                        numTagValue = divide1 / divide2;
                    }
                }
                count++;
            }
        } else {
            // Child must be a number tag if it has no children
            numTagValue = getValue;
        }
        System.out.println(exp.label() + ", numTagValue: " + numTagValue);
        // Return whatever value we get from our number nodes
        return numTagValue;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }
        in.close();
        out.close();
    }

}
