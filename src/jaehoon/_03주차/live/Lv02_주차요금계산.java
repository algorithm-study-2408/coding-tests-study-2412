package jaehoon._03주차.live;

import java.util.*;

public class Lv02_주차요금계산 {

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

    System.out.println("result = " + Arrays.toString(solution(fees, records)));
  }

  public static int[] solution(int[] fees, String[] records) {
    // 1. 차량별 스택을 가진 맵 생성
    Map<String, ArrayDeque<Integer>> recordMap = new HashMap<>();
    // 2. 차량별 누적 주차시간을 저장할 맵 생성
    Map<String, Integer> totalTimeMap = new HashMap<>();

    // 3. 기록 처리
    for (String record : records) {
      String[] split = record.split(" ");
      int minute = convertTo(split[0]);
      String carNumber = split[1];
      String action = split[2];

      if (action.equals("IN")) {
        // 3-1. 키가 존재하지 않을 경우 ArrayDeque<String> 생성 후 값 추가
        recordMap.computeIfAbsent(carNumber, key -> new ArrayDeque<Integer>())
                 .addLast(minute);
        // 3-2. 키가 존재하지 않을 경우 차량 누적 시각 맵 생성
        totalTimeMap.putIfAbsent(carNumber, 0);
      } else {
        int startTime = recordMap.get(carNumber).pollLast();
        int diffTime = minute - startTime;
        totalTimeMap.put(carNumber, totalTimeMap.get(carNumber) + diffTime); // 차량 누적시간 갱신
      }
    }

    // 4. 출차가 기록되지 않은 경우 처리
    for (Map.Entry<String, ArrayDeque<Integer>> entry : recordMap.entrySet()) {
      if (!entry.getValue().isEmpty()) {
        int diffTime = (24 * 60 - 1) - entry.getValue().pollLast();
        totalTimeMap.put(entry.getKey(), totalTimeMap.get(entry.getKey()) + diffTime);
      }
    }

    // 5. 차량 번호 정렬 후 요금 계산
    List<String> sortedCars = new ArrayList<>(totalTimeMap.keySet());
    Collections.sort(sortedCars);

    int[] result = new int[sortedCars.size()];
    for (int i = 0; i < sortedCars.size(); i++) {
      String carNumber = sortedCars.get(i);
      result[i] = calculateFee(fees, totalTimeMap.get(carNumber));
    }
    return result;
  }

  private static int convertTo(String time) {
    int minute = 0;
    String[] split = time.split(":");
    minute += Integer.parseInt(split[0]) * 60;
    minute += Integer.parseInt(split[1]);
    return minute;
  }

  private static int calculateFee(int[] fees, int totalTime) {
    if (totalTime <= fees[0]) {
      return fees[1];
    } else {
      int extraTime = totalTime - fees[0];
      int extraFee = (int) Math.ceil((double) extraTime / fees[2]) * fees[3];
      return fees[1] + extraFee;
    }
  }
}
