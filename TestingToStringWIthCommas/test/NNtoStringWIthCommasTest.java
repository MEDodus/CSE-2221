import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWIthCommasTest {

    @Test
    public void test() {
        this.toStringWithCommasTest1();
        this.toStringWithCommasTest2();
        this.toStringWithCommasTest3();
        this.toStringWithCommasTest4();
        this.toStringWithCommasTest5();
    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

    @Test
    public void toStringWithCommasTest1() {
        NaturalNumber n = new NaturalNumber2("123456789101112");
        String nToString = redirectToMethodUnderTest(n);
        String nExpected = "123,456,789,101,112";

        assertEquals(nExpected, nToString);
    }

    /*
     * Routine/Challenging case: Possibly challenging in that if the
     * implementation does not work with digits under three then it may print a
     * comma before or after those digits.
     */
    @Test
    public void toStringWithCommasTest2() {
        NaturalNumber n = new NaturalNumber2("104");
        String nToString = redirectToMethodUnderTest(n);
        String nExpected = "104";

        assertEquals(nExpected, nToString);
    }

    /*
     * Boundary case: Test "0" which of of NaturalNumber's requires clause is
     * the mathematical model's smallest number that can possibly be input. A
     * good candidate for a boundary case.
     */
    @Test
    public void toStringWithCommasTest3() {
        NaturalNumber n = new NaturalNumber2();
        String nToString = redirectToMethodUnderTest(n);
        String nExpected = "0";

        assertEquals(nExpected, nToString);
    }

    /*
     * Routine case: Testing a large number, however, this is well within bounds
     * of NaturalNumber's range and has no need to be listed as challenging or
     * boundary.
     */
    @Test
    public void toStringWithCommasTest4() {
        NaturalNumber n = new NaturalNumber2("111111111111111111111111111111");
        String nToString = redirectToMethodUnderTest(n);
        String nExpected = "111,111,111,111,111,111,111,111,111,111";

        assertEquals(nExpected, nToString);
    }

    /*
     * Challenging case: Implementation pads single digit outputs with 0's and
     * maybe be implemented incorrectly where this occurs every 4 digits instead
     * of 3.
     */
    @Test
    public void toStringWithCommasTest5() {
        NaturalNumber n = new NaturalNumber2("303003303");
        String nToString = redirectToMethodUnderTest(n);
        String nExpected = "303,003,303";

        assertEquals(nExpected, nToString);
    }

}
