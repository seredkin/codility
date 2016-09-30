package com.seredkin.test.codility;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Anton on 27.09.2016.
 */
public class CodilityDemo {

    @Test public void testGrouping() {
        int[] data = new int[1_000_000];
        Random random = new Random();
        Arrays.fill(data, 1001);
        for (int i = 0; i < data.length; i++) {
            if(i%3==0)
                data[i] = random.nextInt(1000);

        }
        assertEquals(1001, solutionCount(data));
    }

    public int solutionCount(int[] A) {

        Map<Integer, Long> counted = Arrays.stream(A).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Stream<Map.Entry<Integer, Long>> result = counted.entrySet().stream()
                .sorted((o1, o2) -> o1.getValue() < o2.getValue() ? 1 : -1);

        return result.findFirst().orElse(new AbstractMap.SimpleEntry<>(0, 0l)).getValue().intValue();

    }

    /*
    *
    ▶
    extreme_large_numbers
    Sequence with extremely large numbers testing arithmetic overflow.
    ✘
    WRONG ANSWER
    got 2, but it is not equilibrium point, sum[0..1]=4294967294, sum[3..3]=-2
    1.
    2.030 s
    WRONG ANSWER, got 2, but it is not equilibrium point, sum[0..1]=4294967294, sum[3..3]=-2
    2.
    2.019 s
    OK
    ▶
    extreme_negative_numbers
    Sequence with extremely large numbers testing arithmetic overflow.
    ✘
    WRONG ANSWER
    got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
    1.
    2.030 s
    OK
    2.
    2.029 s
    OK
    3.
    1.996 s
    WRONG ANSWER, got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
    ▶
    overflow_tests1
    arithmetic overflow tests
    ✘
    WRONG ANSWER
    got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
    1.
    2.014 s
    WRONG ANSWER, got 0, but it is not equilibrium point, left sum (empty set)=0, sum[1..2]=-4294967296
    2.
    2.048 s
    WRONG ANSWER, got 2, but it is not equilibrium point, sum[0..1]=-4294967296, right sum (empty set)=0
    3.
    2.044 s
    OK
    4.
    2.002 s
    OK
    ▶
    overflow_tests2
    arithmetic overflow tests
    ✘
    WRONG ANSWER
    got 2, but it is not equilibrium point, sum[0..1]=-4294967296, right sum (empty set)=0
    */
    @Test public void equiTest() {
        int[] data = {4, 5, 1, 6, 1, 1};
        assertEquals(equiSolution(data), 1);

        data = new int[]{-4, 5, 1, 6, 1, 1};
        assertEquals(equiSolution(data), -1);

        data = new int[]{7, 7, 1, 1, 1, 11};
        assertEquals(equiSolution(data), 1);

       data = new int[]{-1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, -1, Integer.MAX_VALUE};
        assertEquals(equiSolution(data), 2);

        data = new int[1_000_000];
        Arrays.fill(data, Integer.MAX_VALUE);
        //data = Arrays.stream(data).map(m -> Integer.MAX_VALUE).toArray();
        assertEquals(equiSolution(data), data.length/2-1);

    }

    public int equiSolution(int[] A) {
        if (A.length == 0)
            return -1;
        if (A.length == 2)
            return A[0] == A[1] ? 0 : -1;

        LongAdder sum = new LongAdder();
        Arrays.stream(A).boxed().forEach(i -> sum.add(i.longValue()));

        long sumLeft = 0;

        for (int i = 0; i < A.length; i++) {
            sumLeft += A[i];

            if (sumLeft == sum.longValue() - sumLeft)
                return i;
        }
        return -1;

    }
}
