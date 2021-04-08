package task_1;

import org.apache.commons.math3.fraction.BigFraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

public class Tangens {

    public static BigInteger factorial(int n)
    {
        BigInteger res = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static double tan(double x, double eps) {
        if(!(abs(x) > -PI / 2 && abs(x) < PI / 2)) {
            return Double.NaN;
        }
        BigDecimal result = new BigDecimal(x);
        BigDecimal prev_result = new BigDecimal(x)
            .add(new BigDecimal(eps))
            .add(new BigDecimal(1));
        for (int k = 2; abs(result.subtract(prev_result).doubleValue()) > eps && k < 80; k++) {
            BigFraction coef = new BigFraction(-1).pow(k - 1)
                .multiply(new BigInteger("2").pow(2 * k))
                .multiply(
                    new BigInteger("2").pow(2 * k)
                        .subtract(new BigInteger("task_1"))
                )
                .divide(factorial(2 * k))
                .multiply(Bernoulli.bernoulli(2 * k));

            prev_result = result;
            result = BigDecimal.valueOf(coef.doubleValue())
                .multiply(new BigDecimal(x).pow(2 * k - 1))
                .add(result);
//            System.out.printf("x = %f, k = %d | ", x, k);
        }
        return result.doubleValue();
    }

    public static double tan_from_sincos (double x, double eps) {
        if (x < -PI) {
            while (x < -PI) x += 2 * PI;
        }
        if (x > PI) {
            while (x > PI) x -= 2 * PI;
        }
        if (x == Math.PI / 2) return Double.MAX_VALUE;
        if (x == -Math.PI / 2) return Double.MIN_VALUE;
        return sin(x, eps / 2.0).divide(cos(x, eps / 2.0), RoundingMode.CEILING).doubleValue();
    }

    public static BigDecimal sin(double x, double eps) {
        BigDecimal result = new BigDecimal(x);
        BigDecimal prev_result = new BigDecimal(x)
                .add(new BigDecimal(eps))
                .add(new BigDecimal(1));
        for (int k = 3; abs(result.subtract(prev_result).doubleValue()) > eps && k < 30; k += 2) {
            prev_result = result;
            result = BigDecimal.valueOf(x).pow(k)
                .multiply(BigDecimal.valueOf(
                    new BigFraction(1)
                        .divide(factorial(k))
                        .doubleValue()
                ))
                .multiply(BigDecimal.valueOf(-1).pow((k - 1) / 2))
                .add(result);
        }
        return result;
    }

    public static BigDecimal cos (double x, double eps) {
        BigDecimal result = new BigDecimal(1);
        BigDecimal prev_result = new BigDecimal(2)
                .add(new BigDecimal(eps));
        for (int k = 2; abs(result.subtract(prev_result).doubleValue()) > eps && k < 30; k += 2) {
            prev_result = result;
            result = BigDecimal.valueOf(x).pow(k)
                    .multiply(BigDecimal.valueOf(
                            new BigFraction(1)
                                    .divide(factorial(k))
                                    .doubleValue()
                    ))
                    .multiply(BigDecimal.valueOf(-1).pow(k / 2))
                    .add(result);
        }
        return result;
    }
}
