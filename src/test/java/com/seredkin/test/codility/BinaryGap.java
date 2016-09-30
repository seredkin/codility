package com.seredkin.test.codility;

import org.junit.Test;

/**
 * Created by Anton on 25.09.2016.
 */
public class BinaryGap {

    /*Completed at 100%*/

    int[] smaples = {5, 16, 1024, 9, 11, 19, 42, 1024, 2048, 2049, 4095, 1162, 51712, 20, 2147483647};


    @Test public void testMe() {

        for (int one : smaples) {
            System.out.println("value = " + one+"\t"+Integer.toBinaryString(one));
            int solution = solution(one);
            System.out.println("solution =\t\t" + solution+"\n");
        }

    }

    public int solution(int N) {
        int maxSeq = 0;
        int currSeq = 0;
        int cursor = 0;
        String s = Integer.toBinaryString(N);
        char[] chars = s.toCharArray();
        if(chars.length<3)
            return 0;
        else if (chars.length==3)
            return s.equals("101")?1:0;

        while (cursor < chars.length) {
            if (chars[cursor] == '1') {
                if(cursor++ >= chars.length)
                    return maxSeq;
                while (cursor < chars.length) {
                    if (chars[cursor] == '0') {
                        currSeq++;
                        cursor++;
                    } else {
                        maxSeq = Math.max(maxSeq, currSeq);
                        currSeq = 0;
                        break;
                    }
                }
            }
        }
        return maxSeq;
    }

}
