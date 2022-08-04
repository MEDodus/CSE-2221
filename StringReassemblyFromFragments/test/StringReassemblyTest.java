import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class StringReassemblyTest {

    /**
     * Construct and return a {@code Set<String>} containing the given
     * {@code String}s.
     *
     * @param args
     *            the {@code String}s to put in the set
     * @return {@code Set<String>} of the given {@code String}s
     * @ensures createFromArgs = [the Set<String> of the given Strings]
     */
    private static Set<String> createFromArgs(String... args) {
        Set<String> set = new Set1L<String>();
        for (String s : args) {
            set.add(s);
        }
        return set;
    }

    @Test
    public void combinationTest1() {
        /*
         * Routine case
         */
        String str1Expected = "Michael";
        String str1 = "Michael";
        String str2Expected = "aelello";
        String str2 = "aelello";
        String concatExpected = "Michaelello";
        int overlap = StringReassembly.overlap(str1, str2);
        String concat = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(concatExpected, concat);
    }

    @Test
    public void combinationTest2() {
        /*
         * Routine case
         */
        String str1Expected = "pasta";
        String str1 = "pasta";
        String str2Expected = "stanford";
        String str2 = "stanford";
        String concatExpected = "pastanford";
        int overlap = StringReassembly.overlap(str1, str2);
        String concat = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(concatExpected, concat);
    }

    @Test
    public void combinationTest3() {
        /*
         * Boundary case: smallest possible mathematical case given the contract
         * would be Strings of length >= 1 where the result length will always
         * be 2 if two valid strings of length 1 are passed in.
         */
        String str1Expected = "z";
        String str1 = "z";
        String str2Expected = "p";
        String str2 = "p";
        String concatExpected = "zp";
        int overlap = StringReassembly.overlap(str1, str2);
        String concat = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(concatExpected, concat);

    }

    /*
     * Test all possible type of avoiding substrings to be passed in, in the
     * next 3 test units.
     */
    @Test
    public void addToSetAvoidingSubstringsTest1() {
        /*
         * Test with case where str is a substring.
         */
        String str = "cat";
        Set<String> strSet = createFromArgs("catatonic", "creative",
                "courtship", "cumpleanos", "cannon");
        Set<String> strSetExpected = createFromArgs("catatonic", "creative",
                "courtship", "cumpleanos", "cannon");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    @Test
    public void addToSetAvoidingSubstringsTest2() {
        /*
         * Test with case where str is not a substring but IS a superstring.
         */
        String str = "catatonic";
        Set<String> strSet = createFromArgs("cat", "creative", "tonic",
                "cumpleanos", "cannon");
        Set<String> strSetExpected = createFromArgs("catatonic", "creative",
                "cumpleanos", "cannon");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    @Test
    public void addToSetAvoidingSubstringsTest3() {
        /*
         * Test with case where str is not a substring but is NOT a superstring.
         */
        String str = "casey";
        Set<String> strSet = createFromArgs("cat", "creative", "courtship",
                "cumpleanos", "cannon");
        Set<String> strSetExpected = createFromArgs("cat", "creative",
                "courtship", "cumpleanos", "cannon", "casey");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
    }

    /*
     * No boundary case since we cannot test the boundary of whether "in" is
     * open
     */
    @Test
    public void linesFromInputTest1() {
        /*
         * Routine case: follows the same guidelines as linesFromInputTest2.
         * Does that make it useless. I don't think so. I always double check my
         * work.
         */
        SimpleReader input = new SimpleReader1L("cheer-8-2.txt");
        Set<String> outputExpected = createFromArgs("Bucks -- Beat", "Go Bucks",
                "o Bucks -- B", "Beat Mich", "Michigan~");
        Set<String> output = StringReassembly.linesFromInput(input);
        assertEquals(outputExpected, output);
    }

    /*
     * No boundary case since we cannot test the boundary of whether "in" is
     * open
     */
    @Test
    public void linesFromInputTest2() {
        /*
         * Routine case with multiple overlapping substrings.
         *
         */

        /*
         * Note: test.txt is only for this method test NOT to be used in the
         * StringReassembly class.
         */
        SimpleReader input = new SimpleReader1L("test.txt");
        Set<String> outputExpected = createFromArgs("Lanky", "Stanky", "Cranky",
                "Mankey", "Train Key", "Keanu Reeves", "Vesper", "treasure",
                "leisure", "measure", "surely", "assured");
        Set<String> output = StringReassembly.linesFromInput(input);
        assertEquals(outputExpected, output);
    }

    /*
     * Possible test of assemble. Just for my own curiosity.
     */
    @Test
    public void assembleTest1() {
        /*
         * Valid since we don't have to worry about assembles require clause if
         * we use linesFromInput to generate fragments.
         */
        Set<String> fragments = StringReassembly
                .linesFromInput(new SimpleReader1L("cheer-8-2.txt"));
        StringReassembly.assemble(fragments);
        Set<String> fragmentsExpected = createFromArgs(
                "Go Bucks -- Beat Michigan~");
        assertEquals(fragmentsExpected, fragments);
    }

    /*
     * NOTE: printWithLineSeparators will not be tested as we have not knowledge
     * on how to test output streams at this point. Does make a good question
     * for Rob though.
     */

}
