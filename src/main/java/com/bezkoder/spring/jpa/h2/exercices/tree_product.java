package com.bezkoder.spring.jpa.h2.exercices;

/*
https://app.codility.com/programmers/trainings/2/tree_product/
 */


import java.util.ArrayList;
import java.util.List;

class tree_product {
    int n;                 // N + 1 nodes
    List<Integer>[] g;
    int[] parent, sz;

    public String solution(int[] A, int[] B) {
        int N = A.length;
        n = N + 1;
        buildTree(A, B);
        parent = new int[n];
        sz = new int[n];
        dfsSize(0, -1);

        // best0
        long best0 = n;

        // best1
        long best1 = 0;
        for (int v = 1; v < n; v++) {
            int s = sz[v];
            int t = n - s;
            best1 = Math.max(best1, 1L * s * t);
        }

        // best2: computed via DSU-on-tree + rerooting as described
        long best2 = computeBestTwoCuts();

        long best = Math.max(best0, Math.max(best1, best2));
        return Long.toString(best); // or BigInteger if you want extra safety
    }

    void buildTree(int[] A, int[] B) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int u = A[i], v = B[i];
            g[u].add(v);
            g[v].add(u);
        }
    }

    void dfsSize(int u, int p) {
        parent[u] = p;
        sz[u] = 1;
        for (int v : g[u]) if (v != p) {
            dfsSize(v, u);
            sz[u] += sz[v];
        }
    }

    long computeBestTwoCuts() {
        // Implement DSU-on-tree + rerooting as per the outline.
        // Maintain multisets of subtree sizes, query nearest to half,
        // and update global best2 for both ancestor–descendant
        // and independent cases.
        return 0L; // placeholder
    }
}
