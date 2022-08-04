import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Michael Dodus
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Test of reduceToGCD boundary case: where n and m are the smallest values
     * a NaturalNumber can be. n = 0, m = 0.
     */
    @Test
    public void testReduceToGCD_0_0() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "0";
        final String nOut = "0";
        final String mIn = "0";
        final String mOut = "0";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber m = new NaturalNumber2(mIn);
        NaturalNumber mExpected = new NaturalNumber2(mOut);
        CryptoUtilities.reduceToGCD(n, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Test of reduceToGCD routine case: where n and m are both composite
     * numbers. n = 225, m = 85.
     */
    @Test
    public void testReduceToGCD_225_85() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "225";
        final String nOut = "5";
        final String mIn = "85";
        final String mOut = "0";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber m = new NaturalNumber2(mIn);
        NaturalNumber mExpected = new NaturalNumber2(mOut);
        CryptoUtilities.reduceToGCD(n, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Test of reduceToGCD routine case: where n and m are the same input value.
     * n = 25, m = 25.
     */
    @Test
    public void testReduceToGCD_25_25() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "25";
        final String nOut = "25";
        final String mIn = "25";
        final String mOut = "0";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber m = new NaturalNumber2(mIn);
        NaturalNumber mExpected = new NaturalNumber2(mOut);
        CryptoUtilities.reduceToGCD(n, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Test of reduceToGCD routine/?challenging? case: both n and m are prime
     * numbers so resulting reduction should be 1. Checks if our reference
     * swapping indeed works as intended. n = 13, m = 27
     */
    @Test
    public void testReduceToGCD_13_27() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "13";
        final String nOut = "1";
        final String mIn = "27";
        final String mOut = "0";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber m = new NaturalNumber2(mIn);
        NaturalNumber mExpected = new NaturalNumber2(mOut);
        CryptoUtilities.reduceToGCD(n, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Test of isEven boundary case: where n = 0.
     */
    @Test
    public void testIsEven_0() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "0";
        final String nOut = "0";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean result = CryptoUtilities.isEven(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Test of isEven routine case: where n = 1.
     */
    @Test
    public void testIsEven_1() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "1";
        final String nOut = "1";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean result = CryptoUtilities.isEven(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Test of isEven routine case: where n = 4.
     */
    @Test
    public void testIsEven_4() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "4";
        final String nOut = "4";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean result = CryptoUtilities.isEven(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }
    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Test of isWitnessToCompositeness boundary case: requires n > 2 and 1 < w
     * < n - 1. Thus, n = 4 and w = 2 is our boundary.
     */
    @Test
    public void testisWitnessToCompositenessTest_4_2() {
        // Setting up named input/output variables so checkstyle doens't yell at me.
        final String nIn = "4";
        final String nOut = "4";
        final String wIn = "2";
        final String wOut = "2";
        NaturalNumber w = new NaturalNumber2(wIn);
        NaturalNumber wExpected = new NaturalNumber2(wOut);
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean isComposite = CryptoUtilities.isWitnessToCompositeness(w, n);
        // Assert that results meet expectations.
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(true, isComposite);
    }

    /*
     * Test of isWitnessToCompositeness routine case: try routine numbers that
     * should result in n being composite. n = 15, w = 4
     */
    @Test
    public void testisWitnessToCompositeness_15_4() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "15";
        final String nOut = "15";
        final String wIn = "4";
        final String wOut = "4";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber w = new NaturalNumber2(wIn);
        NaturalNumber wExpected = new NaturalNumber2(wOut);
        boolean isComposite = CryptoUtilities.isWitnessToCompositeness(w, n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, isComposite);
    }

    /*
     * Test of isWitnessToCompositeness routine case: try routine numbers that
     * should result in n being prime. n = 17, w = 5
     */
    @Test
    public void testisWitnessToCompositeness_17_5() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "17";
        final String nOut = "17";
        final String wIn = "5";
        final String wOut = "5";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber w = new NaturalNumber2(wIn);
        NaturalNumber wExpected = new NaturalNumber2(wOut);
        boolean isComposite = CryptoUtilities.isWitnessToCompositeness(w, n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, isComposite);
    }

    /*
     * Test of isWitnessToCompositeness boundary case: try a boundary where only
     * w is the boundary case. n = 35, w = 33
     */
    @Test
    public void testIsWitnessToCompositeness_35_33() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "35";
        final String nOut = "35";
        final String wIn = "33";
        final String wOut = "33";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        NaturalNumber w = new NaturalNumber2(wIn);
        NaturalNumber wExpected = new NaturalNumber2(wOut);
        boolean isComposite = CryptoUtilities.isWitnessToCompositeness(w, n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, isComposite);
    }

    /*
     * Test of isPrime2 boundary case: n = 2.
     */
    @Test
    public void testIsPrime2_2() {
        // Setting up named input/output variables so checkstyle doens't yell at me.
        final String nIn = "2";
        final String nOut = "2";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(true, isPrime);
    }

    /*
     * Test of isPrime2 routine case: n = 37.
     */
    @Test
    public void testIsPrime2_37() {
        // Setting up named input/output variables so checkstyle doens't yell at me.
        final String nIn = "37";
        final String nOut = "37";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(true, isPrime);
    }

    /*
     * Test of IsPrime2 routine: n = 879259.
     */
    @Test
    public void testIsPrime2_879259() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "879";
        final String nOut = "879";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        boolean isPrime = CryptoUtilities.isPrime2(n);
        // Assert that results meet expectations.
        assertEquals(nExpected, n);
        assertEquals(true, isPrime);
    }

    /*
     * Test of generateNextLikelyPrime routine case: n = 13
     */
    @Test
    public void testGenerateNextLikelyPrime_13() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "13";
        final String nOut = "17";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    /*
     * Test of generateNextLikelyPrime boundary case: n = 2
     */
    @Test
    public void testGenerateNextLikelyPrime_2() {
        // Setting up named input/output variables so checkstyle doesn't yell at me.
        final String nIn = "2";
        final String nOut = "3";
        NaturalNumber n = new NaturalNumber2(nIn);
        NaturalNumber nExpected = new NaturalNumber2(nOut);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }
}
