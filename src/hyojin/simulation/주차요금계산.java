package hyojin.simulation;

import java.util.*;

public class 주차요금계산 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
        };
        int[] answer = solution(fees, records);
        System.out.println(Arrays.toString(answer)); // 결과: [14600, 34400, 5000]
    }

    public static int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimes = new HashMap<>();    // 차량별 입차 시간
        Map<String, Integer> totalTimes = new HashMap<>(); // 차량별 총 주차 시간

        for (String record : records) {
            String[] split = record.split(" ");
            String time = split[0];
            String carNumber = split[1];
            String action = split[2];

            int minutes = toMinutes(time);

            if (action.equals("IN")) {
                inTimes.put(carNumber, minutes); // 입차 시간 저장
            } else {
                int inTime = inTimes.remove(carNumber);
                int parkedTime = minutes - inTime; // 주차 시간 계산
                totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + parkedTime);
            }
        }

        // 23:59 기준으로 출차되지 않은 차량 처리
        int endOfDay = toMinutes("23:59");
        for (String carNumber : inTimes.keySet()) {
            int inTime = inTimes.get(carNumber);
            int parkedTime = endOfDay - inTime;
            totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + parkedTime);
        }

        // 차량 번호 정렬 후 요금 계산
        List<String> sortedCars = new ArrayList<>(totalTimes.keySet());
        Collections.sort(sortedCars);

        int[] result = new int[sortedCars.size()];
        for (int i = 0; i < sortedCars.size(); i++) {
            String carNumber = sortedCars.get(i);
            result[i] = calculateFee(fees, totalTimes.get(carNumber));
        }

        return result;
    }

    // 시간 문자열을 분 단위로 변환
    private static int toMinutes(String time) {
        String[] split = time.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);
        return hours * 60 + minutes;
    }

    // 요금 계산
    private static int calculateFee(int[] fees, int totalTime) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (totalTime <= basicTime) {
            return basicFee; // 기본 시간 이하
        } else {
            int extraTime = totalTime - basicTime;
            int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;
            return basicFee + extraFee;
        }
    }
}
