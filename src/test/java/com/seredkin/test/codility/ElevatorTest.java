package com.seredkin.test.codility;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Anton on 28.09.2016.
 */
public class ElevatorTest {

    @Test public void elevator() {
        int s;
        s = solution(new int[]{40, 40, 100, 80, 20},
                new int[]{3, 3, 2, 2, 3}, 3, 5, 200);
        assertEquals(6, +s);

        s = solution(new int[]{120, 40, 100, 80, 120},
                new int[]{1, 2, 3, 4, 5}, 3, 5, 200);
        assertEquals(7, s);

        s = solution(new int[]{140, 140, 140, 140, 140},
                new int[]{3, 3, 3, 3, 3}, 3, 5, 200);
        assertEquals(10, s);
    }

    public int solution(int[] A, int[] B, int M, int X, int Y) {

        Elevator elevator = new Elevator(M, X, Y);
        int i = 0;
        while (i < A.length) {
            if (elevator.fits(A[i])) {
                if (B[i] <= M)
                    elevator.fitPerson(A[i], B[i]);
                else
                    System.out.println("Wrong floor number :" + B[i]);
                i++;
            } else if (elevator.isEmpty()) {
                System.out.println("Found a fatty who doesn't fit the elevator: " + i + " " + A[i]);
                i++;
            } else
                elevator.go();
        }

        if (!elevator.isEmpty())
            elevator.go();

        return elevator.getTotalStops();

    }

    private class Elevator {

        private final int[] floors;
        private final int capacity;
        private final int maxLoad;
        private int currentPeople = 0;
        private int currentLoad = 0;
        private int totalStops = 0;

        Elevator(int floors, int capacity, int maxLoad) {
            this.floors = new int[floors + 1];
            this.capacity = capacity;
            this.maxLoad = maxLoad;
        }

        boolean fits(int weight) {
            return weight <= maxLoad - currentLoad && currentPeople + 1 <= capacity;
        }

        void fitPerson(int weight, int floor) {
            if (!fits(weight))
                throw new RuntimeException("This passenger doesn't fit!");
            currentLoad += weight;
            currentPeople++;
            floors[floor]++;
        }

        boolean isEmpty() {
            return currentPeople == 0 && currentLoad == 0;
        }

        void go() {
            int stops = 1;
            for (int floor : floors) {
                if (floor > 0)
                    stops++;
            }
            currentLoad = 0;
            currentPeople = 0;
            totalStops += stops;
        }

        int getTotalStops() {
            return totalStops;
        }
    }

}
