package jaehoon._04주차;

import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/42626
public class Lv02_더_맵게 {

  public static void main(String[] args) {
    int[] scoville1 = {1, 2, 3, 9, 10, 12};
    int[] scoville2 = {5, 5, 5, 5, 5}; // 18번 테스트케이스
    int k1 = 7;
    int k2 = 4;
    System.out.println("result1 = " + solution(scoville1, k1));
    System.out.println("result2 = " + solution(scoville2, k2));
  }

  public static int solution(int[] scoville, int k) {
    // 1. 최소힙에 음식 추가
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int sco : scoville) {
      minHeap.add(sco);
    }

    int count = 0;
    // 2. 작은값부터 꺼내면서 새로운 스코빌지수 음식 조합
    while (!minHeap.isEmpty()) {
      // 2-1. 가장 스코빌지수가 작은 음식이 k 이상이면 셔플 횟수 리턴
      if (minHeap.peek() >= k) {
        return count;
      }

      // 2-2. 음식조합
      int newScoville = minHeap.poll() + (minHeap.poll() * 2);
      // 2-3. 남은게 하나일때 k 미만이면 -1 리턴
      if (minHeap.isEmpty() && newScoville < k) {
        return -1;
      }
      // 2-4. 새로운 스코빌지수 음식이 조합되면 최소힙에 추가
      minHeap.add(newScoville);
      count++;
    }
    return -1;
  }
}
