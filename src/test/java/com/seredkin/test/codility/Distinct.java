package com.seredkin.test.codility;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Anton on 26.09.2016.
 */
public class Distinct {

    @Test public void testMe() {
        Random r = new Random();
        int[] data = new int[100_000];
        int uplimit = data.length / r.nextInt(10);
        if (uplimit == 0)
            uplimit = Integer.MAX_VALUE;
        int finalUplimit = uplimit;
        Arrays.stream(data).map(ints -> data[ints] = r.nextInt(finalUplimit));
        solution(data);

        solution(new int[]{0});
        solution(new int[]{});
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        HashSet<Integer> set = new HashSet<>();

        return (int) Arrays.stream(A).distinct().count();

    }

}
