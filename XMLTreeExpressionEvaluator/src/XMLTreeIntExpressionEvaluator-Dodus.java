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

        // Integers to recursively call evaluate and get our expression value
        int leftResult = 0;
        int rightResult = 0;

        // Expression value for each node to be returned
        int expressionVal = 0;

        // To check if we are at the bottom of the tree
        int numChild = exp.numberOfChildren();
        final int zero = 0;

        /*
         * If we are not at the bottom of the tree keep calling evaluate on the
         * children
         */
        if (numChild > zero) {
            leftResult = evaluate(exp.child(left));
            rightResult = evaluate(exp.child(right));
        }

        // If we are at a number node get the value attributes' value
        if (exp.label().equals(num)) { // <number> node
            expressionVal = Integer.parseInt(exp.attributeValue(value));
        } else {
            /*
             * If not then check what node we are at and evaluate based of of
             * both children
             */
            if (exp.label().equals(tim)) { // <times> node
                expressionVal = leftResult * rightResult;
            } else if (exp.label().equals(div)) { // <divide> node
                expressionVal = leftResult / rightResult;
            } else if (exp.label().equals(pls)) { // <plus> node
                expressionVal = leftResult + rightResult;
            } else { // <minus> node
                expressionVal = leftResult - rightResult;
            }
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
