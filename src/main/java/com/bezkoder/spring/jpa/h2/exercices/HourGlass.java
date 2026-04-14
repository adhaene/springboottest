package com.bezkoder.spring.jpa.h2.exercices;

//https://www.hackerrank.com/challenges/java-2d-array/problem
public class HourGlass {

    public static void main(String[] args) {
        int[] A = {-1,6,3,4,7,4};
        int[][] twoDArray = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0}
        };
        System.out.println(solution(twoDArray));
    }

    public static int solution(int[][] twoDArray){
        int best = 0;
        for (int i=1;i<=4;i++){
            for (int j=1;j<=4;j++) {
            //center
                int i1 = twoDArray[i][j-1];
                int i2 = twoDArray[i][j];
                int i3 = twoDArray[i][j+1];
             //top
                int i11 = twoDArray[i-1][j-1];
                int i12 = twoDArray[i-1][j];
                int i13 = twoDArray[i-1][j+1];

                //bottom
                int i14 = twoDArray[i+1][j-1];
                int i15 = twoDArray[i+1][j];
                int i16 = twoDArray[i+1][j+1];
                int sum = i1+i2+i3+i11+i12+i13+i14+i15+i16;
                best = Math.max(best,sum);
            }
        }

        return best;
    }
}
