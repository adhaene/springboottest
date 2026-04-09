package com.bezkoder.spring.jpa.h2.exercices;


class ParkingBill {

    public static void main(String[] args) {
            int[] A = {1,4,5,2,-1,3,2};
        ParkingBill parkingBill = new ParkingBill();
        System.out.println(parkingBill.solution("10:00","13:21"));
        System.out.println(parkingBill.solution("09:42","11:42"));
    }
    public int solution(String E, String L) {
        int start = toMinutes(E);
        int end = toMinutes(L);

        int duration = end - start;

        // Round up to full hours
        int hours = (duration + 59) / 60;

        // Entrance fee + first hour + remaining hours
        return 2 + 3 + (hours - 1) * 4;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }
}

