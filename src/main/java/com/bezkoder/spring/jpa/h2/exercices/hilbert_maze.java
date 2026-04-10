package com.bezkoder.spring.jpa.h2.exercices;

/*
https://app.codility.com/programmers/trainings/2/hilbert_maze/
 */

class hilbert_maze {
    public static void main(String[] args) {

        hilbert_maze unit = new hilbert_maze();
        System.out.println(unit.solution(1,2,1,3,4)); // 8
        System.out.println(unit.solution(2,2,5,6,6));  //7
        System.out.println(unit.solution(3,6,6,10,13));  //39
    }

    public int solution(int N, int A, int B, int C, int D) {
        long h1 = hilbert(N, A, B);
        long h2 = hilbert(N, C, D);
        return (int)Math.abs(h1 - h2);
    }

    private long hilbert(int N, int x, int y) {
        long index = 0;
        int size = 1 << (N + 1); // 2^(N+1)

        for (int level = N; level >= 0; level--) {
            int side = 1 << level; // 2^level

            int rx = (x & side) > 0 ? 1 : 0;
            int ry = (y & side) > 0 ? 1 : 0;

            int quadrant = (ry << 1) | rx;

            long blockSize = (long)side * side;

            switch (quadrant) {
                case 0: // bottom-left, rotate +90
                    int temp = x;
                    x = y;
                    y = temp;
                    break;

                case 1: // top-left
                    index += blockSize;
                    break;

                case 2: // top-right
                    index += 2L * blockSize;
                    break;

                case 3: // bottom-right, rotate -90
                    index += 3L * blockSize;
                    x = side - 1 - x;
                    y = side - 1 - y;
                    int t = x;
                    x = y;
                    y = t;
                    break;
            }
        }

        return index;
    }
}
