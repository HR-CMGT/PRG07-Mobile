package nl.hr.cmtprg037.week7tst;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void checkCalculatorAdd() {
        assertEquals(4, Calculator.add(2,2));
    }

    @Test
    public void checkCalculatorAddNegative() {
        assertEquals(-4, Calculator.add(-2,-2));
    }

    @Test
    public void checkCalculatorAddZero() {
        assertEquals(2, Calculator.add(2,0));
    }
}