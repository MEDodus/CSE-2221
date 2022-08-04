import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
public final class SequenceRecursion<T> extends Sequence1L<T> {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceRecursion() {
        super();
    }

    @Override
    public void flip() {
        if (this.length() > 0) {
            T x = this.remove(this.length() - 1);
            this.flip();
            this.add(0, x);
        }
    }

//    @Override
//    public void flip() {
//        Sequence<T> temp = new Sequence1L<>();
//        temp.transferFrom(this);
//        int size = temp.length();
//        for (int i = 0; i < size; i++) {
//            T x = temp.remove(0);
//            this.add(0, x);
//        }
//    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Sequence<Integer> s = new SequenceRecursion<>();
        s.add(0, 6);
        s.add(0, 5);
        s.add(0, 4);
        s.add(0, 3);
        s.add(0, 2);
        s.add(0, 1);
        out.println(s);
        s.flip();
        out.println(s);

        in.close();
        out.close();
    }

}
