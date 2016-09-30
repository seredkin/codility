package com.seredkin.test.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Anton on 29.09.2016.
 */
public class PermCheck {

    public int solutionA(int[] A) {
        if (A.length == 0)
            return 0;
        TreeSet<Integer> set = new TreeSet();
        Arrays.stream(A).boxed().forEach(integer -> set.add(integer));
        for (int i = 1; i <= A.length; i++) {
            if (!set.contains(i))
                return 0;
        }
        return 1;
    }

    @Test public void testMe() {
        solutionA(new int[]{4, 1, 3, 2});
        solution(new int[]{4, 1, 3, 2});
    }

    public int solution(int[] ints) {
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            if(ints[i]!=i+1)
                return 0;
        }
        return 1;
    }
}
