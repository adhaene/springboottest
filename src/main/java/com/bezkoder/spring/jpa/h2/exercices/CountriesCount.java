package com.bezkoder.spring.jpa.h2.exercices;


class CountriesCount {

    public static void main(String[] args) {
        int[][] A = new int[7][3];
        A[0][0] = 5;    A[0][1] = 4;    A[0][2] = 4;
        A[1][0] = 4;    A[1][1] = 3;    A[1][2] = 4;
        A[2][0] = 3;    A[2][1] = 2;    A[2][2] = 4;
        A[3][0] = 2;    A[3][1] = 2;    A[3][2] = 2;
        A[4][0] = 3;    A[4][1] = 3;    A[4][2] = 4;
        A[5][0] = 1;    A[5][1] = 4;    A[5][2] = 4;
        A[6][0] = 4;    A[6][1] = 1;    A[6][2] = 1;
        CountriesCount test = new CountriesCount();;
        System.out.println(test.solution(A));
    }

    public int solution(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        boolean[][] visited = new boolean[n][m];
        int countries = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (!visited[r][c]) {
                    countries++;
                    int color = A[r][c];

                    // BFS flood fill
                    java.util.ArrayDeque<int[]> queue = new java.util.ArrayDeque<>();
                    queue.add(new int[]{r, c});
                    visited[r][c] = true;

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int cr = cell[0], cc = cell[1];

                        for (int k = 0; k < 4; k++) {
                            int nr = cr + dr[k];
                            int nc = cc + dc[k];

                            if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                                    !visited[nr][nc] && A[nr][nc] == color) {

                                visited[nr][nc] = true;
                                queue.add(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }

        return countries;
    }
}
