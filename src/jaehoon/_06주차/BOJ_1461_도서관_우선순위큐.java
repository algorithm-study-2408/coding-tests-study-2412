package jaehoon._06주차;

import java.util.PriorityQueue;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1461
public class BOJ_1461_도서관_우선순위큐 {
  // 입력예시:
  // 7 2
  // -37 2 -6 -39 -29 11 -28
  //
  // 출력예시:
  // 131
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt(); // 책의 개수
    int m = scanner.nextInt(); // 한 번에 들 수 있는 책의 개수
    PriorityQueue<Integer> negatives = new PriorityQueue<>((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));
    PriorityQueue<Integer> positives = new PriorityQueue<>((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));

    // 책 위치 입력
    for (int i = 0; i < n; i++) {
      int position = scanner.nextInt();
      if (position < 0) {
        negatives.add(position);
      } else {
        positives.add(position);
      }
    }

    int totalDistance = 0;
    // 가장 먼 곳은 편도로 이동하므로 머저 빼고 시작하기
    if (!negatives.isEmpty() && !positives.isEmpty()) {
      totalDistance -= Math.max(Math.abs(negatives.peek()), positives.peek());
    } else if (!negatives.isEmpty()) {
      totalDistance -= Math.abs(negatives.peek());
    } else if (!positives.isEmpty()) {
      totalDistance -= positives.peek();
    }

    // 음수 그룹 처리
    while (!negatives.isEmpty()) {
      // 현재 그룹에서 가장 먼 위치
      totalDistance += negatives.poll() * -2; // 왕복 거리

      // m-1개의 책을 더 처리 (현재 그룹)
      for (int i = 0; i < m - 1 && !negatives.isEmpty(); i++) {
        negatives.poll();
      }
    }

    // 양수 그룹 처리
    while (!positives.isEmpty()) {
      // 현재 그룹에서 가장 먼 위치
      totalDistance += positives.poll() * 2; // 왕복 거리

      // m-1개의 책을 더 처리 (현재 그룹)
      for (int i = 0; i < m - 1 && !positives.isEmpty(); i++) {
        positives.poll();
      }
    }

    scanner.close();

    // 결과 출력
    System.out.println(totalDistance);
  }
}
