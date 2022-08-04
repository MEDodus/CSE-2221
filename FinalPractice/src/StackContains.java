import components.sequence.Sequence;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class StackContains {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private StackContains() {
    }

    public static boolean contains(Stack<String> stack, String str) {
        boolean contains = false;
        if (stack.length() > 0) {
            String check = stack.pop();
            boolean trulyContains = contains(stack, str);
            if (str.equals(check)) {
                contains = true;
            } else {
                contains = trulyContains;
            }
            stack.push(check);
        }
        return contains;
    }

    public static boolean contains2(Stack<String> stack, String str) {
        boolean contains = false;
        if (stack.length() > 0) {
            String check = stack.pop();
            contains = contains2(stack, str);
            if (str.equals(check) || contains) {
                contains = true;
            }
            stack.push(check);
        }
        return contains;
    }

    public static boolean isPalindrome(Sequence<Character> seq) {
        boolean palindrome = false;
        if (seq.length() > 1) {
            char first = seq.remove(0);
            char last = seq.remove(seq.length() - 1);
            palindrome = isPalindrome(seq);
            if (first == last && palindrome) {
                palindrome = true;
            }
            seq.add(0, first);
            seq.add(seq.length(), last);
        } else {
            palindrome = true;
        }
        return palindrome;
    }

    public static boolean strCopies(String str, String sub, int n) {
        int idx = str.indexOf(sub);
        boolean copy = false;
        if (str.length() == 0) {
            if (n <= 0) {
                copy = true;
            }
        } else {
            if (idx >= 0) {
                copy = strCopies(str.substring(idx + 1), sub, n - 1);
            } else {
                copy = strCopies("", sub, n);
            }
        }
        return copy;
    }

    private static int maxNodes(XMLTree xml) {
        int cnt = 0;
        if (xml.isTag()) {
            cnt = xml.numberOfChildren();
            for (int i = 0; i < xml.numberOfChildren(); i++) {
                int nextCnt = maxNodes(xml.child(i));
                if (nextCnt > cnt) {
                    cnt = nextCnt;
                }
            }
        }
        return cnt;
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
//        Stack<String> stack = new Stack1L<>();
//        stack.push("Michael");
//        stack.push("Surya");
//        stack.push("Jackson");
//        stack.push("Jim");
//        String name = "Surya";
//        Stack<String> stack2 = new Stack1L<>();
//        out.println(stack);
//        out.println(stack2);
//        boolean contains2 = contains2(stack2, name);
//        boolean contains = contains2(stack, name);
//        out.println(contains);
//        out.println(contains2);
//        out.println(stack);
//        out.println(stack2);
//        Sequence<Character> seq = new Sequence1L<>();
//        seq.add(0, 'r');
//        seq.add(0, 'a');
//        seq.add(0, 'c');
//        seq.add(0, 'e');
//        seq.add(0, 'c');
//        seq.add(0, 'a');
//        seq.add(0, 'r');
//        out.println(seq);
//        out.println(isPalindrome(seq));
//        out.println(seq);
//        String testStrCopies = "catcowcatcat";
//        String sub = "cat";
//        out.println(strCopies(testStrCopies, sub, 2));

        XMLTree xml = new XMLTree2("test.xml");
        int max = maxNodes(xml);
        out.println(max);

        in.close();
        out.close();
    }

}
