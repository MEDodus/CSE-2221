import java.util.Comparator;

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
public final class SelectionSort {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SelectionSort() {
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = q.newInstance();
        temp.transferFrom(q);
        String min = "";
        if (temp.length() > 1) {
            while (temp.length() > 1) {
                String s1 = temp.dequeue();
                String s2 = temp.dequeue();

                if (order.compare(s1, s2) < 0) {
                    temp.enqueue(s1);
                    q.enqueue(s2);
                } else {
                    temp.enqueue(s2);
                    q.enqueue(s1);
                }
                if (temp.length() == 1) {
                    min = temp.dequeue();
                }
            }
        } else {
            min = temp.dequeue();
        }

        return min;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> temp = q.newInstance();
        temp.transferFrom(q);
        while (temp.length() > 0) {
            String min = removeMin(temp, order);
            q.enqueue(min);
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

        Queue<String> q = new Queue1L<>();
        q.enqueue("Hello");
        q.enqueue("Champion");
        q.enqueue("Tiger");
        q.enqueue("Lion");
        q.enqueue("Mouse");
        q.enqueue("Marne");
        Comparator<String> order = new StringComparator();
        out.println(q);
        sort(q, order);
        out.println(q);
        in.close();
        out.close();
    }

}
