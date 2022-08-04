import java.util.Comparator;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
