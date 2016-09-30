package com.seredkin.test.codility;

import junit.framework.Assert;
import org.junit.Test;

/**
 * 100%
 */
public class FrogRiverOne {

    @Test public void test(){
        Assert.assertEquals(6, solution(5, new int[]{1,3,1,4,2,3,5,4}));
    }


    public int solution(int X, int[] A) {
        boolean[] mask = new boolean[X+1];

        int ones = 0;
        for (int i = 0; i < A.length; i++) {
            mask[A[i]] = true;
            ones = leadingOnes(mask, ones);
            if(ones ==X)
                return i;
        }
        return -1;
    }

    private int leadingOnes(boolean[] mask, int skip){
        for (int i=skip+1; i<mask.length; i++) {
            if(mask[i])
                skip++;
            else
                return skip;
        }
        return skip;
    }

}
