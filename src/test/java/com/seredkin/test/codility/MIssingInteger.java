package com.seredkin.test.codility;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Anton on 29.09.2016.
 */
public class MIssingInteger {

    //    77%...
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 0)
            return 1;
        if(A.length==1)
            return Math.max(1, A[0] - 1);

        boolean[] mask = new boolean[A.length + 1];
        for (int val : A) {
            if (0 < val && val < mask.length)
                mask[val] = true;
        }

        for (int i = 1; i < mask.length; i++) {
            if (!mask[i])
                return i;

        }
        return 1;

    }

    @Test public void testMe() {
        Assert.assertEquals(solution(new int[]{Integer.MAX_VALUE}), 1);
        Assert.assertEquals(solution(new int[]{-9, -9, -8, -6, -5, -4, -3}), 1);
        Assert.assertEquals(solution(new int[]{2, 3, 5, 6, 7}), 1);
        Assert.assertEquals(solution(new int[]{1, 2, 3, 5, 6, 7}), 4);
        Assert.assertEquals(solution(new int[]{3, 4, 5, 6, 7}), 1);

        Random r = new Random();
        int bigSize = 10;
        int missing = r.nextInt(bigSize);
        int[] data = new int[bigSize - 1];
        LongAdder adder = new LongAdder();
        for (int i = 0; i < data.length; i++) {
            if (i < missing - 1)
                data[i] = i + 1;
            else
                data[i] = i + 2;
        }

        Assert.assertEquals(missing, solution(data));

    }

}
