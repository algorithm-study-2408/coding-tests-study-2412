package jaehoon._04주차.순열_조합;

import java.util.ArrayList;
import java.util.List;

public class 조합_중첩for문 {

  public static void main(String[] args) {
    // 총 숫자의 개수
    int n = 5;
    // 뽑아낼 숫자
    int r = 3;
    // 대상이 되는 숫자들
    int[] arr = {1, 2, 3, 4, 5};
    List<String> result = new ArrayList<>();

    // 뽑아낼 숫자의 개수(r) 만큼 중첩 for문
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
          result.add(arr[i] + " " + arr[j] + " " + arr[k]);
        }
      }
    }

    System.out.println("result = " + result);
  }
}
