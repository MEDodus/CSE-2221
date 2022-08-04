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
public final class QueueOver<T> extends Queue1L<T> {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private QueueOver() {
        super();
    }

    @Override
    public void flip() {
        if (this.length() > 0) {
            T x = this.dequeue();
            this.flip();
            this.enqueue(x);
        }
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

        Queue<Integer> m = new QueueOver<>();
        m.enqueue(1);
        m.enqueue(2);
        m.enqueue(3);
        m.enqueue(4);
        m.enqueue(5);
        m.enqueue(6);
        out.println(m);
        m.flip();
        out.println(m);
        in.close();
        out.close();
    }

}
