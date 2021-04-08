package task_1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TangensTest {
    double eps = 0.001;
    
    @Test
    public void CheckPositive() {
        double[] params = {0.555, 0.567, 0.780, 14, 30};
        for (double param : params) {
            assertEquals(Math.tan(param), Tangens.tan_from_sincos(param, eps), eps);
        }
    }

    @Test
    public void CheckZero() {
        assertEquals(Math.tan(0), Tangens.tan_from_sincos(0, eps), eps);
    }

    @Test
    public void CheckNegative() {
        double[] params = {-0.456, -0.777, -1.780, -16, -50};
        for (double param : params) {
            assertEquals(Math.tan(param), Tangens.tan_from_sincos(param, eps), eps);
        }
    }

    @Test
    public void CheckBounds() {
        assertEquals(Double.MAX_VALUE, Tangens.tan_from_sincos(Math.PI / 2, eps), eps);
        assertEquals(Double.MIN_VALUE, Tangens.tan_from_sincos(-Math.PI / 2, eps), eps);
    }
}
