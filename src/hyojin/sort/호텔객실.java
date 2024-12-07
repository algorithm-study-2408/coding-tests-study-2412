package hyojin.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/155651

public class 호텔객실 {
    public static void main(String[] args) {
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
//        String[][] book_time = {{"09:10", "10:10"}, {"10:20", "12:20"}};
//        String[][] book_time = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        int result = solution(book_time);
        System.out.println("result = " + result);
    }


    public static int solution(String[][] bookTime) {
        // 예약 시각을 저장하는 자료구조 선언
        List<Reservation> reservationList = new ArrayList<>();

        // 시작시간, 종료시간, 이용시간 -> 분으로 환산
        for (String[] times : bookTime) {
            int startTime = toMinutes(times[0]);
            int endTime = toMinutes(times[1]) + 10;

            reservationList.add(new Reservation(startTime, endTime));
        }

        // 예약시간 정렬
        Collections.sort(reservationList);

        System.out.println("reservationList = " + reservationList);

        List<Room> roomList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            boolean assigned = false;

            // 기존 방들 중 하나에 예약 가능하면 배정
            for (Room room : roomList) {
                if (room.reservation.getEndTime() <= reservation.getStartTime()) {
                    room.reservation = reservation;
                    assigned = true;
                    break;
                }
            }

            // 모든 방이 겹치면 새로운 방 추가
            if (!assigned) {
                roomList.add(new Room(reservation));
            }
        }

        // 필요한 객실 수는 roomList의 크기
        return roomList.size();
    }

    private static int toMinutes(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    private static class Reservation implements Comparable<Reservation> {
        int startTime;
        int endTime;

        public Reservation(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Reservation other) {
            // 시작 시간 기준 정렬, 시작 시간이 같으면 종료 시간 기준 정렬
            if (this.startTime == other.startTime) {
                return this.endTime - other.endTime;
            }
            return this.startTime - other.startTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }

    private static class Room {
        Reservation reservation;

        public Room(Reservation reservation) {
            this.reservation = reservation;
        }
    }
}