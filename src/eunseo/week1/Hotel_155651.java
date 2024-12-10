package eunseo.week1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Hotel_155651 {

    public static void main(String[] args) {
        String[][] book_time = {
                {"15:00", "17:00"},
                {"16:40", "18:20"},
                {"14:20", "15:20"},
                {"14:10", "19:20"},
                {"18:20", "21:20"}
        };
        int result = solution(book_time);
        System.out.println("result = " + result);
    }

    public static int solution(String[][] book_time) {
        int answer = 0;

        // 1. LocalTime으로 변경
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime[][] localTimes = Arrays.stream(book_time).map(times -> new LocalTime[] {
                LocalTime.parse(times[0], formatter),
                LocalTime.parse(times[1], formatter)
        }).toArray(LocalTime[][]::new);

        // 2. 시작 시각으로 정렬
        Arrays.sort(localTimes, (a, b) -> a[0].compareTo(b[0]));

        // 3. 종료 시각 + 10분 이후의 시간이 시작 시각보다 앞선지 확인
        PriorityQueue<LocalTime> pq = new PriorityQueue<>(); // 종료 시각을 관리하는 우선순위 큐

        for (LocalTime[] time : localTimes) {
            LocalTime startTime = time[0];
            LocalTime endTime = time[1];

            // 가장 빠른 종료 시각 + 10분이 현재 시작 시각보다 이전이면 큐에서 제거
            if (!pq.isEmpty() && (pq.peek().plusMinutes(10).isBefore(startTime) || pq.peek().plusMinutes(10).equals(startTime))) {
                pq.poll(); // 가장 오래된 예약 공간 반환
            }

            // 종료 시각을 큐에 추가
            pq.add(endTime);

            // 큐 크기는 현재 필요한 공간 개수
            answer = Math.max(answer, pq.size());
        }

        return answer;
    }
}
