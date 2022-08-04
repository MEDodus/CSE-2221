import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader1L;

public class GlossaryTest {

    /*
     * Includes test units for methods in GlossaryMap. In addition, some of
     * these unit tests will use input from files. all files that will be tested
     * will be labeled in the manner (test#.txt) where number is the numbered
     * test file we will be using.
     */

    /**
     * Construct and return a {@code Sequence<String>} containing the given
     * {@code String}s.
     *
     * @param args
     *            the {@code String}s to put in the set
     * @return {@code Sequence<String>} of the given {@code String}s
     * @ensures createFromArgs = [the Sequence<String> of the given Strings]
     */
    private static Sequence<String> createFromArgs(String... args) {
        Sequence<String> sequence = new Sequence1L<>();
        for (String s : args) {
            sequence.add(0, s);
        }
        return sequence;
    }

    @Test
    public void combineLinesTest1() {
        /*
         * String combineLines
         */

        /*
         * Routine: Definition and term line test following formatting
         * guidelines in project description. ONLY TESTS SINGLE LINE DEFINITIONS
         */
        Sequence<String> outputExpected = createFromArgs("ocean",
                "a large body of water", "tree",
                "a plant that grows from the ground and gets big");
        Sequence<String> output = createFromArgs("ocean",
                "a large body of water", "", "tree",
                "a plant that grows from the ground and gets big", "");
        GlossaryMap.combineLines(output);
        assertEquals(outputExpected, output);
    }

    @Test
    public void combineLinesTest2() {
        /*
         * String combineLines
         */

        /*
         * Routine: Definition and term line test following formatting
         * guidelines in project description. TESTS MULTIPLE LINE DEFINITIONS
         */
        Sequence<String> outputExpected = createFromArgs("person",
                "a human that may be alive", "creature",
                "a thing that could be anything, so long as it is alive");
        Sequence<String> output = createFromArgs("person", "a human that ",
                "may be alive", "", "creature", "a thing ",
                "that could be anything", ", so long as it is alive", "");
        GlossaryMap.combineLines(output);
        assertEquals(outputExpected, output);
    }

    @Test
    public void getDictionaryTest1() {
        /*
         * Routine: Uses test1.txt file. See file for corresponding input.
         * Properly checks if a correct dictionary {@code Map<String, String>}
         * is created from the file. No need to check for multiple line
         * definitions since already checked in combineLinesTest1/2.
         */
        Map<String, String> outputExpected = new Map1L<>();
        // Add our expected key value pairs
        outputExpected.add("physical environment", "place");
        outputExpected.add(
                "an object or entity not precisely designated or capable of being designated",
                "thing");
        Map<String, String> output = GlossaryMap
                .getDictionary(new SimpleReader1L("doc/test1.txt"));
        assertEquals(outputExpected, output);
    }

    @Test
    public void getDictionaryTest2() {
        /*
         * Boundary: Uses a one term/definition file "test2.txt" as input.
         * Checks if {@code Map<String, String>} properly outputs exactly one
         * pair in Map<String, String> given our boundary case.
         */
        Map<String, String> outputExpected = new Map1L<>();
        // Add our expected key value pairs
        outputExpected.add("a thing you sit on and possibly rest on", "seat");
        Map<String, String> output = GlossaryMap
                .getDictionary(new SimpleReader1L("doc/test2.txt"));
        assertEquals(outputExpected, output);
    }

    @Test
    public void createMapTest1() {
        /*
         * Boundary: Uses a {@code Sequence<String>} with only two elements.
         */
        Sequence<String> input = createFromArgs("alien",
                "an extraterrestrial being");
        Map<String, String> output = GlossaryMap.createMap(input);
        Map<String, String> outputExpected = new Map1L<>();
        outputExpected.add("an extraterrestrial being", "alien");
        assertEquals(outputExpected, output);
    }

    @Test
    public void createMapTest2() {
        /*
         * Routine: Uses a {@code Sequence<String>} with an even number of
         * elements greater than two.
         */
        Sequence<String> input = createFromArgs("alien",
                "an extraterrestrial being", "tree", "a plant that grows",
                "table", "a place to set things down on");
        Map<String, String> output = GlossaryMap.createMap(input);
        Map<String, String> outputExpected = new Map1L<>();
        outputExpected.add("an extraterrestrial being", "alien");
        outputExpected.add("a plant that grows", "tree");
        outputExpected.add("a place to set things down on", "table");
        assertEquals(outputExpected, output);
    }

    @Test
    public void removeMinTest1() {
        /*
         * Boundary: Uses a {@code Map<String, String>} with a minimum of one
         * entry.
         */
        Map<String, String> input = new Map1L<>();
        input.add("a person that saves the day", "superhero");
        Map<String, String> inputExpected = new Map1L<>();
        /*
         * Make a Map<String, String> that only contains the minimum value for
         * our expected output.
         */
        Map<String, String> minPair = new Map1L<>();
        minPair.add("a person that saves the day", "superhero");
        Map.Pair<String, String> output = GlossaryMap.removeMin(input);
        Map.Pair<String, String> outputExpected = minPair.removeAny();
        assertEquals(inputExpected, input);
        assertEquals(outputExpected, output);
    }

    @Test
    public void removeMinTest2() {
        /*
         * Boundary: Uses a {@code Map<String, String>} with greater than one
         * entry.
         */
        Map<String, String> input = new Map1L<>();
        input.add("a person that saves the day", "superhero");
        input.add("a place to go to see exotic animals", "zoo");
        Map<String, String> inputExpected = new Map1L<>();
        inputExpected.add("a place to go to see exotic animals", "zoo");
        /*
         * Make a Map<String, String> that only contains the minimum value for
         * our expected output.
         */
        Map<String, String> minPair = new Map1L<>();
        minPair.add("a person that saves the day", "superhero");
        Map.Pair<String, String> output = GlossaryMap.removeMin(input);
        Map.Pair<String, String> outputExpected = minPair.removeAny();
        assertEquals(inputExpected, input);
        assertEquals(outputExpected, output);
    }
}
