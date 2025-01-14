package yeonjae.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class 지뢰찾기 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int row = sc.nextInt();
    for (int i = 0; i < row; i++) {
      int n = sc.nextInt();
      int[] firstRow = Arrays.stream(sc.next().split("")).mapToInt(Integer::parseInt).toArray();
      String[] secondRow = sc.next().split("");
      int answer = 0;

      // * 지뢰 처리
      for (int j = 0; j < n; j++) {
        if (secondRow[j].equals("*")) {
          if (j > 0) {
            firstRow[j - 1]--;
          }
          firstRow[j]--;
          if (j < n - 1) {
            firstRow[j + 1]--;
          }
          answer++;
        }
      }

      // # 지뢰 처리
      for (int j = 0; j < n; j++) {
        if (secondRow[j].equals("#") && firstRow[j] > 0) {
          if (j == 0 && firstRow[j] != 0 && firstRow[j + 1] != 0) {
            firstRow[j]--;
            firstRow[j + 1]--;
            answer++;
          } else if (j == n - 1 && firstRow[j - 1] != 0 && firstRow[j] != 0) {
            firstRow[j - 1]--;
            firstRow[j]--;
            answer++;
          } else if (j >= 1 && j <= n - 2 && firstRow[j - 1] != 0 && firstRow[j] != 0
              && firstRow[j + 1] != 0) {
            firstRow[j - 1]--;
            firstRow[j]--;
            firstRow[j + 1]--;
            answer++;
          }
        }
      }
      System.out.println(answer);
    }
  }
}
