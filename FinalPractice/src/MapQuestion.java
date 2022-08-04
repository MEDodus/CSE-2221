import components.map.Map;
import components.map.Map1L;
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
public final class MapQuestion {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MapQuestion() {
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
        Sequence<Integer> seq = new Sequence1L<>();
        seq.add(0, 5);
        seq.add(0, 5);
        seq.add(0, 7);
        seq.add(0, 4);
        seq.add(0, 7);
        Map<Integer, Integer> map = new Map1L<>();
        out.println(map);
        for (int i : seq) {
            if (map.hasKey(i)) {
                map.replaceValue(i, map.value(i) + 1);
            } else {
                map.add(i, 1);
            }
            out.println(map);
        }
        out.println(map);
        in.close();
        out.close();
    }

}
