package jaehoon._00연습.순열_조합;

import java.util.ArrayList;
import java.util.List;

public class 조합_재귀 {

  static int n = 5;

  static int r = 3;

  static int[] arr = {1, 2, 3, 4, 5};

  public static void main(String[] args) {
    List<Integer> result = new ArrayList<>();
    combinations(-1, result);
  }

  public static void combinations(int start, List<Integer> b) {
    if (b.size() == r) {
      // 로직
      print(b);
      return;
    }
    for (int i = start + 1; i < n; i++) {
      b.add(i); // 인덱스로 추가
      // result.add(arr[i]); // 값 추가
      combinations(i, b); // 조합 재귀호출
      b.remove(b.size() - 1); // 맨 마지막 원복
    }
  }

  public static void print(List<Integer> result) {
    for (int i : result) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
}
