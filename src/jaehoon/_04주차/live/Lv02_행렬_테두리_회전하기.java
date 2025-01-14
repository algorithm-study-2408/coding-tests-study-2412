package jaehoon._04주차.live;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/77485
public class Lv02_행렬_테두리_회전하기 {

  static int[][] rc;

  public static void main(String[] args) {
    int[][] queries = {
        {2, 2, 5, 4},
        {3, 3, 6, 6},
        {5, 1, 6, 3}
    };
    int rows = 6;
    int columns = 6;
    System.out.println("result = " + Arrays.toString(solution(rows, columns, queries)));
  }

  public static int[] solution(int rows, int columns, int[][] queries) {
    // 1. 배열 초기화
    int num = 1;
    rc = new int[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        rc[i][j] = num++;
      }
    }

    // 2. 정답을 저장할 배열
    int[] answer = new int[queries.length];

    // 3. 쿼리 순회
    for (int i = 0; i < queries.length; i++) {
      answer[i] = rotate(queries[i]);
    }
    return answer;
  }

  private static int rotate(int[] query) {
    // 인덱스를 0부터로 맞춰주기 위해 -1
    int r1 = query[0] - 1;
    int c1 = query[1] - 1;
    int r2 = query[2] - 1;
    int c2 = query[3] - 1;

    // 시작 위치의 값 (상단 행의 오른쪽 끝 값) 임시 저장
    int temp = rc[r1][c2];
    // 최소값 초기화
    int min = temp;

    // 상단 행 오른쪽으로 이동
    for (int i = c2; i > c1; i--) {
      rc[r1][i] = rc[r1][i - 1];
      min = Math.min(min, rc[r1][i]);
    }

    // 좌측 열 위로 이동
    for (int i = r1; i < r2; i++) {
      rc[i][c1] = rc[i + 1][c1];
      min = Math.min(min, rc[i][c1]);
    }

    // 하단 행 왼쪽으로 이동
    for (int i = c1; i < c2; i++) {
      rc[r2][i] = rc[r2][i + 1];
      min = Math.min(min, rc[r2][i]);
    }

    // 우측 열 아래로 이동
    for (int i = r2; i > r1 + 1; i--) {
      rc[i][c2] = rc[i - 1][c2];
      min = Math.min(min, rc[i][c2]);
    }

    // 임시 저장 값 배치
    rc[r1 + 1][c2] = temp;
    return min;
  }
}
