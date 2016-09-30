package com.seredkin.test.codility;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Anton on 25.09.2016.
 * https://codility.com/programmers/task/max_profit/
 * <p>
 * 44%
 *
 * For example, for the input [8, 9, 3, 6, 1, 2] the solution returned a wrong answer (got 1 expected 3).
 *
 *
 ▶
 two_hills
 two increasing subsequences
 ✘
 WRONG ANSWER
 got 3000 expected 99000
 1.
 1.450 s
 WRONG ANSWER, got 3000 expected 99000
 ▶
 max_profit_after_max_and_before_min
 max profit is after global maximum and before global minimum
 ✘
 WRONG ANSWER
 got 1 expected 3
 1.
 1.475 s
 WRONG ANSWER, got 1 expected 3


 ▶
 large_1
 large value (99) followed by short pattern (values from [1..6]) repeated 10K times
 ✘
 WRONG ANSWER
 got 3 expected 7
 1.
 1.612 s
 WRONG ANSWER, got 3 expected 7
 ▶
 large_2
 chaotic sequence of 200K values from [100K..120K], then 200K values from [0..100K]
 ✘
 WRONG ANSWER
 got 34706 expected 100000
 1.
 2.478 s
 WRONG ANSWER, got 34706 expected 100000
 ▶
 large_3
 chaotic sequence of 200K values from [1..200K]
 ✘
 WRONG ANSWER
 got 108851 expected 199998
 1.
 1.964 s
 WRONG ANSWER, got 108851 expected 199998



 *
 *
 */
public class MaxProfit {

    int[] rates = {23171, 21011, 21123, 21366, 21013, 21367};
    int[] myRates = {100, 99, 98, 100, 95, 98, 99, 103, 99, 100};

    @Test
    public void testMe() {

        int solution = solution(rates);
        assertTrue(356 == solution);

    }

    public int solution(int[] A) {

        if (A == null || A.length == 0)
            return 0;

        int minTotal = A[0];
        int maxTotal = A[0];
        int minIndex = 0;
        int maxIndex = 0;

        int cursor = 0;

        while (cursor < A.length) {
            if (A[cursor] < minTotal) {
                minTotal = A[cursor];
                minIndex = cursor;
            }
            if (A[cursor] > maxTotal) {
                maxTotal = A[cursor];
                maxIndex = cursor;
            }

            cursor++;
        }

        if (maxIndex > minIndex)
            return maxTotal - minTotal;
        else {
            maxIndex = 0;

            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] < A[i + 1]) {
                    maxIndex = i+1;
                }
            }
            if (maxIndex > minIndex)
                return A[maxIndex] - A[minIndex];
            else
                return 0;

        }

    }
}
