package com.bezkoder.spring.jpa.h2.exercices;

class Point2D {
    public int x;
    public int y;
}
class PolygonConcavityIndex {
    public int solution(Point2D[] A) {
        int n = A.length;

        // Find the first non-zero turn direction
        int firstSign = 0;
        for (int i = 0; i < n; i++) {
            long cross = cross(A[i], A[(i + 1) % n], A[(i + 2) % n]);
            if (cross != 0) {
                firstSign = cross > 0 ? 1 : -1;
                break;
            }
        }

        // If all turns were collinear, polygon is convex
        if (firstSign == 0) return -1;

        // Check all turns
        for (int i = 0; i < n; i++) {
            long cross = cross(A[i], A[(i + 1) % n], A[(i + 2) % n]);
            if (cross == 0) continue; // collinear is allowed

            int sign = cross > 0 ? 1 : -1;
            if (sign != firstSign) {
                // The middle point of the triple is concave
                return (i + 1) % n;
            }
        }

        return -1; // convex
    }

    private long cross(Point2D p, Point2D q, Point2D r) {
        long x1 = q.x - p.x;
        long y1 = q.y - p.y;
        long x2 = r.x - q.x;
        long y2 = r.y - q.y;
        return x1 * y2 - y1 * x2;
    }
}
