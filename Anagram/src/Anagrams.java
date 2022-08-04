import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public final class Anagrams {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Anagrams() {
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        /*
         * Go through the list of strings
         */
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());

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
        String[] str = { "eat", "tea", "tan", "ate", "nat", "bat" };
        Anagrams ana = new Anagrams();
        List<List<String>> list = ana.groupAnagrams(str);
        out.println(list);
        in.close();
        out.close();
    }

}
