import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework 15 on Queue 1.
 *
 * @author Michael Dodus
 *
 */
public final class Hw15 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hw15() {
    }

    /**
     * Reports the smallest integer in the given {@code Hw15<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int min = Integer.MAX_VALUE;
        int size = q.length();
        for (int i = 0; i < size; i++) {
            int x = q.dequeue();
            if (x < min) {
                min = x;
            }
        }
        return min;
    }
    /*
     * i. We need the requires clause because we can't get the minimum from a
     * Queue with no elements since that means there would be no integers to
     * compare or get values from.
     *
     * ii. If min is not in entries(q) than anything can can be returned outside
     * of entries(q). Being within entries(q) allows us to define where we want
     * the minimum value to come from.
     */

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int[] temp = new int[q.length()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = q.dequeue();
        }

        int[] minMax = new int[2];
        minMax[0] = temp[0];
        minMax[1] = temp[0];
        for (int i = 0; i < temp.length; i++) {
            if (minMax[0] > temp[i]) {
                minMax[0] = temp[i];
            }
            if (minMax[1] < temp[i]) {
                minMax[1] = temp[i];
            }
            q.enqueue(temp[i]);
        }
        return minMax;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q, String s) {
        int[] minMax = new int[2];
        minMax[0] = q.dequeue();
        minMax[1] = minMax[0];
        q.enqueue(minMax[1]);
        while (q.length() != 0) {
            int[] temp = new int[2];
            if (q.length() == 1) {
                temp[0] = q.dequeue();
                temp[1] = temp[0];
            } else {
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = q.dequeue();
                }
            }
            if (temp[0] < temp[1]) {
                if (temp[0] < minMax[0]) {
                    minMax[0] = temp[0];
                }
                if (temp[1] > minMax[1]) {
                    minMax[1] = temp[1];
                }
            } else if (temp[0] > temp[1]) {
                if (temp[1] < minMax[0]) {
                    minMax[0] = temp[0];
                }
                if (temp[0] > minMax[1]) {
                    minMax[1] = temp[0];
                }
            } else {
                if (temp[0] > minMax[1]) {
                    minMax[1] = temp[0];
                }
                if (temp[0] < minMax[0]) {
                    minMax[0] = temp[0];
                }
            }
        }
        return minMax;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Queue<Integer> q = new Queue1L<>();
        q.enqueue(349);
        q.enqueue(295);
        q.enqueue(532);
        q.enqueue(63);
        q.enqueue(34);
        q.enqueue(68);
        out.println(min(q));
        in.close();
        out.close();
    }

}
