package com.bezkoder.spring.jpa.h2.exercices;
import java.util.*;

/*
https://app.codility.com/programmers/trainings/2/rectangle_builder_greater_area/
 */
public class RectangleBuilderGreaterArea {
    public static void main(String[] args) {
        int[] A = {1,2,5,1,1,2,3,5,1};
        RectangleBuilderGreaterArea rectangleBuilderGreaterArea = new RectangleBuilderGreaterArea();
        System.out.println(rectangleBuilderGreaterArea.solution(A,5));
    }
    public int solution(int[] A, int X) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int a : A) freq.put(a, freq.getOrDefault(a, 0) + 1);

        List<Integer> sides = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() >= 2) {
                sides.add(e.getKey());
            }
        }

        Collections.sort(sides);
        long count = 0;
        int n = sides.size();

        int left = 0;
        int right = n - 1;

        while (left <= right) {
            long area = (long) sides.get(left) * sides.get(right);
            if (area >= X) {
                count += (right - left + 1);
                if (count > 1_000_000_000) return -1;
                right--;
            } else {
                left++;
            }
        }

        // Remove squares that are invalid (need 4 pieces)
        for (int s : sides) {
            if ((long) s * s >= X && freq.get(s) < 4) {
                count--; // remove invalid square
            }
        }

        return count > 1_000_000_000 ? -1 : (int) count;
    }
}

