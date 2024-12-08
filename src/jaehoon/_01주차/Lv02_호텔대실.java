package jaehoon._01주차;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/155651
public class Lv02_호텔대실 {

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
    // 1. 시간 계산을 위해 시간 문자열을 정수값으로 변경
    int[][] roomTimes = new int[book_time.length][book_time[0].length];
    for (int i = 0; i < book_time.length; i++) {
      roomTimes[i][0] = toMinute(book_time[i][0]);
      roomTimes[i][1] = toMinute(book_time[i][1]) + 10; // 청소시간 10분 반영
    }

    // 2. 대실 시작 시간을 기준으로 정렬
    Arrays.sort(roomTimes, Comparator.comparingInt(a -> a[0]));

    // 3. 룸 객체(마지막 예약시간을 저장하고 있는)를 만들어서 리스트로 관리, 예약 진행
    List<Room> rooms = new ArrayList<>();

    // 4. 룸 리스트를 순회하면서 예약할 수 있으면 예약하고 룸의 마지막 퇴실 예약 시간 갱신
    for (int[] time : roomTimes) {
      // 4-1. 룸 순회하면서 예약 가능하면 추가
      boolean added = false;
      for (Room room : rooms) {
        added = room.add(time[0], time[1]);
        if (added) {
          break;
        }
      }

      // 4-2. 예약이 불가능하면 새 객실 생성
      if (!added) {
        Room newRoom = new Room(time[1]);
        rooms.add(newRoom);
      }
    }

    // 최소 객실수 리턴
    return rooms.size();
  }

  public static int toMinute(String time) {
    String[] timeArr = time.split(":");
    return Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
  }

}

class Room {

  private int endTime; // 현재 방의 마지막 예약 종료 시간

  public Room(int endTime) {
    this.endTime = endTime;
  }

  // 새로운 예약을 방에 추가할 수 있는지 확인하고 가능하면 추가
  public boolean add(int startTime, int endTime) {
    // 새로운 예약의 시작 시간이 현재 예약 종료 시간(청소시간 포함) 이후라면 추가 가능
    if (startTime >= this.endTime) {
      this.endTime = endTime; // 종료 시간 업데이트
      return true;
    }
    return false;
  }
}