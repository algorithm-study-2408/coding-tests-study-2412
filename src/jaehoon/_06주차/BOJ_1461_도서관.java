package jaehoon._06주차;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.acmicpc.net/problem/1461
public class BOJ_1461_도서관 {
  // 입력예시:
  // 7 2
  // -37 2 -6 -39 -29 11 -28
  //
  // 출력예시:
  // 131

  /**
   * 풀이 방법 (그리디)<p>
   * 1. 아이디어:<p>
   * - 책의 위치를 음수와 양수로 분리한다.<p>
   * - 각각의 위치를 절댓값 기준 내림차순으로 정렬한다.<p>
   * - 가장 먼 곳부터 이동하며, 한 번에 최대 M 권씩 옮긴다.<p>
   * - 마지막으로 가장 먼 곳은 돌아오지 않아도 되므로, 왕복 거리를 절약할 수 있다.<p>
   * <br>
   * 2. 구체적 접근:<p>
   * - 음수와 양수를 분리하여 정렬:<p>
   * --- 음수는 작은 값부터 큰 값으로 (절댓값 기준 내림차순).<p>
   * --- 양수는 큰 값부터 작은 값으로 (절댓값 기준 내림차순).<p>
   * - 각 그룹에서 한 번에 M 권씩 이동하며 총 이동 거리를 계산한다.<p>
   * - 마지막 이동은 편도로 처리하여 최적화를 이룬다.<p>
   * <br>
   * 3. 복잡도:<p>
   * <br>
   * - 정렬: O(NlogN)<p>
   * - 이동 거리 계산: O(N)<p>
   * - 총 시간 복잡도: O(NlogN)<p>
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt(); // 책의 개수
    int m = scanner.nextInt(); // 한 번에 들 수 있는 책의 개수
    List<Integer> negatives = new ArrayList<>();
    List<Integer> positives = new ArrayList<>();

    // 책 위치 입력
    for (int i = 0; i < n; i++) {
      int position = scanner.nextInt();
      if (position < 0) {
        negatives.add(position);
      } else {
        positives.add(position);
      }
    }

    // 음수와 양수를 절댓값 기준 내림차순 정렬
    negatives.sort((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));
    positives.sort((a, b) -> Integer.compare(Math.abs(b), Math.abs(a)));

    int totalDistance = 0;

    // 음수 그룹 처리
    for (int i = 0; i < negatives.size(); i += m) {
      totalDistance += Math.abs(negatives.get(i)) * 2; // 왕복 거리
    }

    // 양수 그룹 처리
    for (int i = 0; i < positives.size(); i += m) {
      totalDistance += positives.get(i) * 2; // 왕복 거리
    }

    // 가장 먼 곳은 편도로 이동하므로 한 번 빼기
    if (!negatives.isEmpty() && !positives.isEmpty()) {
      totalDistance -= Math.max(Math.abs(negatives.get(0)), positives.get(0));
    } else if (!negatives.isEmpty()) {
      totalDistance -= Math.abs(negatives.get(0));
    } else if (!positives.isEmpty()) {
      totalDistance -= positives.get(0);
    }

    // 결과 출력
    System.out.println(totalDistance);
  }
}
