import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        // Index for left and right child
        final int left = 0;
        final int right = 1;

        // For checking tags of a node
        final String num = "number";
        final String tim = "times";
        final String div = "divide";
        final String pls = "plus";

        // Attribute name to find
        final String value = "value";

        // NaturalNumbers to recursively call evaluate and get our expression value
        NaturalNumber leftResult = new NaturalNumber1L();
        NaturalNumber rightResult = new NaturalNumber1L();

        // Expression value for each node to be returned
        NaturalNumber expressionVal = new NaturalNumber1L();

        // To check if we are at the bottom of the tree
        int numChild = exp.numberOfChildren();
        final int zero = 0;

        // Boolean and String value to check setFromString precondition
        boolean canSetString = false;
        String numberValue;

        /*
         * If we are not at the bottom of the tree keep calling evaluate on the
         * children
         */
        if (numChild > zero) {
            leftResult.transferFrom(evaluate(exp.child(left)));
            rightResult.transferFrom(evaluate(exp.child(right)));
        }
        /*
         * If we are at a number node get the value attributes' value only if
         * the string can be set from the NaturalNumber i.e. the number is not
         * negative
         */
        if (exp.label().equals(num)) { // <number> node
            numberValue = exp.attributeValue(value);
            canSetString = expressionVal.canSetFromString(numberValue);
            if (canSetString) {
                expressionVal.setFromString(numberValue);
            } else {
                Reporter.fatalErrorToConsole("FATAL ERROR HAS OCCURRED! Cannot "
                        + "parse String into a NaturalNumber. Number may be "
                        + "in an invalid format or less than zero.");
            }

        } else {
            /*
             * If not then check what node we are at and evaluate based of of
             * both children
             */
            if (exp.label().equals(tim)) { // <times> node
                leftResult.multiply(rightResult);

            } else if (exp.label().equals(div)) { // <divide> node
                // Check for divide by zero / 0
                if (rightResult.isZero()) {
                    Reporter.fatalErrorToConsole("FATAL ERROR HAS OCCURRED! "
                            + "Cannot divide a NaturalNumber by 0.");
                } else {
                    leftResult.divide(rightResult);
                }

            } else if (exp.label().equals(pls)) { // <plus> node
                leftResult.add(rightResult);

            } else { // <minus> node
                // Check for this is greater than n
                if (leftResult.compareTo(rightResult) < zero) {
                    Reporter.fatalErrorToConsole(
                            "FATAL ERROR HAS OCCURRED! Left operand must be "
                                    + "greater than right operand with "
                                    + "NaturalNumber subtraction.");
                } else {
                    leftResult.subtract(rightResult);
                }
            }
            expressionVal.transferFrom(leftResult);
        }
        return expressionVal;
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
