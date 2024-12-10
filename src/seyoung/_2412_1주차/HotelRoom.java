package seyoung._2412_1주차;


// https://school.programmers.co.kr/learn/courses/30/lessons/155651

public class HotelRoom {

    public static void main(String[] args) {
       HotelRoom T =new HotelRoom();
       String[][] book_time1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
       System.out.println(T.solution(book_time1));

        String[][] book_time2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        System.out.println(T.solution(book_time2));

        String[][] book_time3 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println(T.solution(book_time3));

    }


    public int solution(String[][] book_time) {
        // 모든 시간 배열 미리 0으로 초기화
        int[] times = new int[25*60]; // 24*60으로 하면 런타임에러 발생 => 23:00이후는 적용이 안되기 때문
        for( String[] time : book_time ){
            int start =  toMinutes(time[0]);
            int end = toMinutes(time[1])+10;
            //예약이 잡혀있는 시간대를 배열에 1씩 카운트
            for(int i = start; i< end ; i++) times[i]++;
        }
        int max = 0;
        //회의가 잡힌 시간대들 중 가장 예약 카운트가 높은 시간 = 필요한 방 수
        for(int i : times) max = Math.max(max, i);
        return max;
    }

    private static int toMinutes(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

}



