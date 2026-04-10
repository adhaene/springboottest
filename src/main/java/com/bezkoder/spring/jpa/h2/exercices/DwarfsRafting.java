package com.bezkoder.spring.jpa.h2.exercices;

import java.util.*;

/*
https://app.codility.com/programmers/trainings/2/dwarfs_rafting/
 */
class DwarfsRafting {

    public static void main(String[] args) {

        DwarfsRafting unit = new DwarfsRafting();
        System.out.println(unit.solution(4, "1B 1C 4B 1D 2A","3B 2D")); //6
    }

    public int solution(int N, String S, String T) {
        int half = N / 2;
        int quadrantSize = half * half;

        int[] blocked = new int[4]; // Q1, Q2, Q3, Q4

        // Process barrels and dwarfs
        processSeats(S, blocked, half);
        processSeats(T, blocked, half);

        // Compute free seats per quadrant
        int minFree = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int free = quadrantSize - blocked[i];
            if (free < 0) return -1;
            minFree = Math.min(minFree, free);
        }

        return 4 * minFree;
    }

    private void processSeats(String seats, int[] blocked, int half) {
        if (seats == null || seats.isEmpty()) return;

        for (String seat : seats.split(" ")) {
            if (seat.isEmpty()) continue;

            int row = Integer.parseInt(seat.replaceAll("[A-Z]", ""));
            char colChar = seat.replaceAll("[0-9]", "").charAt(0);
            int col = colChar - 'A' + 1;

            int quadrant = getQuadrant(row, col, half);
            blocked[quadrant]++;
        }
    }

    private int getQuadrant(int row, int col, int half) {
        boolean front = row <= half;
        boolean left = col <= half;

        if (front && left) return 0;   // Q1
        if (front) return 1;           // Q2
        if (left) return 2;            // Q3
        return 3;                      // Q4
    }
}
