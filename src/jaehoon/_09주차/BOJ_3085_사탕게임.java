package jaehoon._09주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3085
public class BOJ_3085_사탕게임 {

  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    char[][] arr = new char[N][N];
    for (int i = 0; i < N; i++) {
      arr[i] = br.readLine().toCharArray();
    }


    // 1. 전치 행렬 생성 (가로, 세로 모두 확인하기 위함)
    char[][] arrT = transpose(arr);
    // 2. 교환해서 최대 개수 찾기
    int answer = Math.max(solution(arr), solution(arrT));
    System.out.println(answer);
    br.close();
  }

  private static char[][] transpose(char[][] arr) {
    char[][] transposed = new char[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        transposed[j][i] = arr[i][j];
      }
    }
    return transposed;
  }

  private static int solution(char[][] arr) {
    int max = 0;
    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < N; j++) {
        // 오른쪽 사탕 교환 (범위체크)
        if (j + 1 < N) {
          swap(arr, i, j, i, j + 1);
          max = Math.max(max, count(arr[i]));
          swap(arr, i, j, i, j + 1);
        }

        // 아래쪽 사탕 교환 (현재하고 아래까지 확인)
        swap(arr, i, j, i + 1, j);
        max = Math.max(max, Math.max(count(arr[i]), count(arr[i + 1])));
        swap(arr, i, j, i + 1, j);
      }
    }
    return max;
  }

  private static void swap(char[][] arr, int i, int j, int i1, int i2) {
    char temp = arr[i][j];
    arr[i][j] = arr[i1][i2];
    arr[i1][i2] = temp;
  }

  private static int count(char[] arr) {
    int max = 1;
    int cnt = 1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] == arr[i - 1]) {
        cnt++;
        max = Math.max(max, cnt);
      } else {
        cnt = 1;
      }
    }
    return max;
  }
}
