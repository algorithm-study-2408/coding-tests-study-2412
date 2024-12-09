package jonghun.week01;

/**
 * --문제--
 * 각 시간대별로 동시에 필요한 최대 객실 수
 *
 * pseudocode
 * <br/>
 * 1. 배열 timeline[1440] 생성  // 24시간을 분단위로 표현 (24 * 60 = 1440)<p>
 * = new int[24*60 + 60 + 10(청소시간)]  // 약 1450분<br/>
 * <br/>
 * 2. 각 예약에 대해:<br/>
 *     시작시간 = HH:MM을 분으로 변환<br/>
 *     종료시간 = HH:MM을 분으로 변환<br/>
 * <br/>
 * 3. 시작시간부터 (종료시간 + 청소시간(10분))까지:<br/>
 *         if (현재시간 >= 1440) continue  // 다음날로 넘어가면 스킵<br/>
 *         timeline[현재시간]++  // 해당 시간에 방 사용 카운트 증가<br/>
 *
 * <br/>
 * 4.최대값 찾기:<br/>
 *     answer = check 배열의 최대값
 *
 */



public class 호텔객실 {

    public static void main(String[] args) {
        String[][] example = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(example));

    }




    public static int solution(String[][] book_time) {
        int answer = 0;
        int[] timeline = new int[1440];

        for (String[] time : book_time) {
            String[] startTime = time[0].split(":");
            String[] endTime = time[1].split(":");

            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);

            for (int i = start; i <= end + 9; i++) {
                if (i >= 1440) continue;
                timeline[i]++;
            }
        }

        for (int room : timeline) {
            answer = Math.max(answer, room);
        }

        return answer;
    }
}