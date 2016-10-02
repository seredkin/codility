package com.seredkin.test.codility;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 100%, but performance could be better
 */
public class TapeEqui {

    public int solution (int[] A){
        if (A.length == 1)
            return A[0];
        if (A.length == 2)
            return Math.abs(A[0] - A[1]);
        int total = Arrays.stream(A).sum();
        int left = 0;
        int dif = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            left += A[i];
            int d = Math.abs(left - (total - left));
            if (d < dif) {
                dif = d;
            }
            if(d > dif && i>A.length/2)
                break;
        }
        return dif;
    }

    @Test public void test() {
        Assert.assertEquals(1, solution(new int[]{1}));
        Assert.assertEquals(1, solution(new int[]{-1, -2}));
        Assert.assertEquals(1, solution(new int[]{3, 1, 2, 4, 3}));
        Assert.assertEquals(1, solution(new int[]{-3, -1, -2, -4, -3}));

        int[] b = new int[4000];
        //Arrays.fill(b, 100);
        Assert.assertEquals(solution(b), 0);

        b = new int[]{100, 100, 100};
        //Assert.assertEquals(solution(b), 100);

        b = new int[]{-1100, 100, 1000, 101, -100};
        Assert.assertEquals(solution(b), 1);
    }

}
