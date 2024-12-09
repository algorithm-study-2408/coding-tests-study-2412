package yeonjae.prefixsum;

import java.util.*;

class 호텔객실 {
    public static void main(String[] args) {
        호텔객실 main = new 호텔객실();
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        int result = main.solution(book_time);
        System.out.println("result = " + result);
    }

    public int solution(String[][] book_time) {
        List<int[]> timePoints = new ArrayList<>();

        for (String[] time : book_time) {
            int start = convertToMinutes(time[0]);
            int end = convertToMinutes(time[1]) + 10;
            timePoints.add(new int[]{start, 1});
            timePoints.add(new int[]{end, -1});
        }

        Collections.sort(timePoints, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int maxRooms = 0;
        int currentRooms = 0;

        for (int[] timePoint : timePoints) {
            currentRooms += timePoint[1];
            if (currentRooms > maxRooms) {
                maxRooms = currentRooms;
            }
        }

        return maxRooms;
    }

    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
