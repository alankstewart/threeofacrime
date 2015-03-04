package alankstewart.threeofacrime;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alanstewart on 4/03/15.
 */
public class MathUtilTest {

    @Test
    public void shouldCalculateCombinations() {
        assertEquals(35, MathUtil.combination(7, 3));
    }

    @Test
    public void shouldCalculateFactorial() {
        assertEquals(3628800, MathUtil.factorial(10));
    }
}
