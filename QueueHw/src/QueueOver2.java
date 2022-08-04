import components.queue.Queue;
import components.queue.Queue1L;
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
public final class QueueOver2<T> extends Queue1L<T> {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private QueueOver2() {
        super();
    }

    /**
     * Reverses ("flips") {@code s}.
     *
     * @param s
     *            the {@code Queue} to be modified
     * @updates s
     * @ensures s = rev(#this);
     */
    public static void flip(Queue<T> s) {
        if (s.length() > 0) {
            T x = s.dequeue();
            this.flip(s);
            s.enqueue(x);
        }
    }

    /*
     * Original Sequences: (X) denotes if it has been used TATACAT X
     * AGCTGTTTTCGTT X CACTCCATTTTA X CATTTTAGCTGTT X TTTCGTTATACAT X
     * CTGTTTTCGTTA X
     *
     * TATACAT U TTTCGTTATACAT = TTTCGTTATACAT (7 Overlap) X AGCTGTTTTCGTT U
     * CATTTTAGCTGTT = CATTTTAGCTGTTTTCGTT (7 Overlap) X CACTCCATTTTA U
     * CATTTTAGCTGTTTTCGTT = CACTCCATTTTAGCTGTTTTCGTT (7 Overlap) X CTGTTTTCGTTA
     * U TTTCGTTATACAT = CTGTTTTCGTTATACAT (8 Overlap) X
     * CACTCCATTTTAGCTGTTTTCGTT U CTGTTTTCGTTATACAT =
     * CACTCCATTTTAGCTGTTTTCGTTATACAT (11 Overlap)
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Queue<Integer> m = new QueueOver2<>();
        m.enqueue(1);
        m.enqueue(2);
        m.enqueue(3);
        m.enqueue(4);
        m.enqueue(5);
        m.enqueue(6);
        out.println(m);
        this.flip(m);
        out.println(m);
        in.close();
        out.close();
    }

}
