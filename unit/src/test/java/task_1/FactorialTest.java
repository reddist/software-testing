package task_1;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FactorialTest {

    private Map<Integer, BigInteger> expectedMap = new HashMap<>();

    {
        expectedMap.put(0, BigInteger.ONE);
        expectedMap.put(3, BigInteger.valueOf(6));
        expectedMap.put(4, BigInteger.valueOf(24));
        expectedMap.put(5, BigInteger.valueOf(120));
        expectedMap.put(15, BigInteger.valueOf(1307674368000L));
        expectedMap.put(16, BigInteger.valueOf(20922789888000L));
        expectedMap.put(17, BigInteger.valueOf(355687428096000L));
    }

    @Test
    public void factorialCheck() {
        for (var entry: expectedMap.entrySet()) {
            var key = entry.getKey();
            var expectedValue = entry.getValue();
            var actualValue = Tangens.factorial(key);

            assertEquals(expectedValue, actualValue);
        }
    }
}