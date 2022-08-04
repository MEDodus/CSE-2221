import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

public class NaturalNumberInstanceOpsTest {

    @Test
    public void powerTest1() {
        NaturalNumber n = new NaturalNumberInstanceOps(2);
        int p = 7;
        NaturalNumber nExpected = new NaturalNumberInstanceOps(128);
        n.power(p);

        assertEquals(nExpected, n);
    }

}
