package task_1;

import org.apache.commons.math3.fraction.BigFraction;

import java.math.BigInteger;

public class Bernoulli {
    /** Generates the task_1.Bernoulli number, B_n, by a double sum.
     * @param n The index of the task_1.Bernoulli number.
     * @return The task_1.Bernoulli number at n.
     */
    public static BigFraction bernoulli(int n) {
        BigFraction result = BigFraction.ZERO;
        for (int k = 0; k <= n; k++) {
            BigFraction jSum = BigFraction.ZERO;
            BigInteger bInt = BigInteger.ONE;
            for (int j = 0; j <= k; j++) {
                BigInteger jPowN = (new BigInteger("" + j))
                        .pow(n);
                if (j % 2 == 0) {
                    jSum = jSum.add(bInt.multiply(jPowN));
                } else {
                    jSum = jSum.subtract(bInt.multiply(jPowN));
                }

                /* update binomial(k,j) recursively
                 */
                bInt = bInt.multiply(new BigInteger("" + (k - j))).
                        divide(new BigInteger("" + (j + 1)));
            }
            result = result.add(jSum.divide(new BigInteger("" + (k + 1)))
            );
        }
        return result;
    }
}
