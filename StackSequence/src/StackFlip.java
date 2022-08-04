import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class StackFlip<T> extends Stack1L<T> {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private StackFlip() {
        super();
    }

    @Override
    public void flip() {
        Stack<T> temp = new Stack1L<>();
        while (this.length() > 0) {
            T x = this.pop();
            temp.push(x);
        }
        this.transferFrom(temp);
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
        Stack<Integer> s = new StackFlip<>();
        s.push(6);
        s.push(5);
        s.push(4);
        s.push(3);
        s.push(2);
        s.push(1);
        out.println(s);
        s.flip();
        out.println(s);

        in.close();
        out.close();
    }

}
