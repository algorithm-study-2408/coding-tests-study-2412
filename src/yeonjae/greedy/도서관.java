package yeonjae.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 도서관 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 입력 처리
    int N = sc.nextInt();
    int M = sc.nextInt();

    // 두 우선순위 큐는 내림차순 정렬.
    PriorityQueue<Integer> positiveQ = new PriorityQueue<>((p1, p2) -> p2 - p1);
    PriorityQueue<Integer> negativeQ = new PriorityQueue<>((p1, p2) -> p2 - p1);

    for (int i = 0; i < N; i++) {
      int temp = sc.nextInt();

      if (temp > 0) {
        positiveQ.offer(temp);
      } else {
        negativeQ.offer(Math.abs(temp));
      }
    }

    int answer = 0;
    int max = 0;

    if (positiveQ.isEmpty()) {
      max += negativeQ.peek();
    } else if (negativeQ.isEmpty()) {
      max += positiveQ.peek();
    } else {
      if (positiveQ.peek() < negativeQ.peek()) {
        max += negativeQ.peek();
      } else {
        max += positiveQ.peek();
      }
    }

    while (!positiveQ.isEmpty()) {
      int temp = positiveQ.poll();
      for (int i = 0; i < M - 1; i++) {
        if (positiveQ.isEmpty()) {
          break;
        }
        positiveQ.poll();
      }
      answer += 2 * temp;
    }

    while (!negativeQ.isEmpty()) {
      int temp = negativeQ.poll();
      for (int i = 0; i < M - 1; i++) {
        if (negativeQ.isEmpty()) {
          break;
        }
        negativeQ.poll();
      }
      answer += 2 * temp;
    }

    answer -= max;
    System.out.println(answer);

    sc.close();
  }
}
