package com.seredkin.test.codility;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * Created by Anton on 26.09.2016.
 */
public class RotateArray {
    @Test public void testMe(){
        int[] data = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("data = " + ArrayUtils.toString(data));
        System.out.println("revs = " + ArrayUtils.toString(solution(data, 43)));

    }

    int[] solution(int[]A, int K){
        if(A==null || A.length==0)
            return A;
        K = K % A.length;//avoid cycling
        if(A.length == K)
            return A;
        int[] temp = new int[K];
        System.arraycopy(A, A.length-K, temp, 0, K);
        System.arraycopy(A, 0, A, K, A.length-K);
        System.arraycopy(temp, 0, A, 0, K);

        return A;
    }
}
