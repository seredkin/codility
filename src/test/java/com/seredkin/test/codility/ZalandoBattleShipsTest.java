package com.seredkin.test.codility;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Anton on 28.09.2016.
 */
public class ZalandoBattleShipsTest {

    public String solution(int N, String S, String T) {
        String none = "0,0";
        if ((S == null || T == null) || ("".equals(S) || "".equals(T)))
            return none;
        if (N < 2 || S.split(",").length < 2) {
            return none;
        }
        ArrayList<Ship> ships = new ArrayList<>();
        try {
            ships.add(toShip(S.split(",")[0].trim()));
            ships.add(toShip(S.split(",")[1].trim()));
        } catch (Exception e) {
            e.printStackTrace();
            return none;
        }

        String[] split = T.split(" ", N * N);
        for (String s : split) {
            try {
                int x = Integer.valueOf(s.substring(0, 1));
                int y = charToX(s.charAt(1));
                ships.forEach(ship -> ship.hit(x, y));
            } catch (Exception e) {
                e.printStackTrace();
                return none;
            }
        }

        int sank = 0;
        int hit = 0;
        for (Ship ship : ships) {
            if (ship.sank())
                sank++;
            else if (ship.wasHit())
                hit++;
        }

        return sank + "," + hit;
    }

    private Ship toShip(String s) {
        String[] split = s.split(" ", 2);
        String s1 = split[0];
        String s2 = split[1];
        int bX = Integer.valueOf(s1.substring(0, 1));
        int bY = charToX(s1.charAt(1));
        int eX = Integer.valueOf(s2.substring(0, 1));
        int eY = charToX(s2.charAt(1));

        return new Ship(bX, bY, eX, eY);
    }

    private int charToX(char c) {
        switch (c) {
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            case 'D':
                return 4;
            default:
                throw new IllegalArgumentException("Wrong field character: "+c);
        }
    }

    @Test public void battleshipTest() {
        assertEquals("1,1", solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
        assertEquals("0,1", solution(4, "1A 1B,2C 2C", "1B"));
        assertEquals("1,1", solution(4, "1B 2C,2D 4C", "2B 2D 3D 4D 4A"));
    }

    private class Ship {

        final int cells;
        int bX;
        int bY;
        int eX;
        int eY;
        int hits = 0;

        public Ship(int bX, int bY, int eX, int eY) {
            this.bX = bX;
            this.bY = bY;
            this.eX = eX;
            this.eY = eY;

            cells = (eY - eX + 1) * (bY - bX + 1);
        }

        boolean hit(int x, int y) {
            boolean hit = (x >= bX && x <= eX) && (y >= bY && y <= eY);
            if (hit)
                hits++;
            return hit;
        }

        boolean wasHit() {
            return hits > 0;
        }

        boolean sank() {
            return hits >= cells;
        }
    }

}
