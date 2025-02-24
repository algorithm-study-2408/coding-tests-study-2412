package jaehoon._09주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/20125
public class BOJ_20125_쿠키의_신체_측정 {

  static int N;
  static char[][] map;
  static StringBuilder st = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    map = new char[N][N];
    for (int i = 0; i < N; i++) {
      map[i] = br.readLine().toCharArray();
    }

    searchHeart();
    br.close();
  }

  public static void searchHeart() {
    for (int i = 1; i < N - 1; i++) {
      for (int j = 1; j < N - 1; j++) {
        if (map[i][j] == '*' && map[i + 1][j] == '*' && map[i - 1][j] == '*' && map[i][j + 1] == '*' && map[i][j - 1] == '*') {
          st.append(i + 1).append(" ").append(j + 1).append("\n");
          searchLeftArm(i, j - 1);
          searchRightArm(i, j + 1);
          int[] waist = searchWaist(i + 1, j);
          searchLeftLeg(waist[0] + 1, waist[1] - 1);
          searchRightLeg(waist[0] + 1, waist[1] + 1);
          System.out.println(st);
          return;
        }
      }
    }
  }

  private static void searchLeftArm(int row, int col) {
    int len = 0;
    while (col >= 0 && map[row][col] == '*') {
      len++;
      col--;
    }
    st.append(len).append(" ");
  }

  private static void searchRightArm(int row, int col) {
    int len = 0;
    while (col < N && map[row][col] == '*') {
      len++;
      col++;
    }
    st.append(len).append(" ");
  }

  private static int[] searchWaist(int row, int col) {
    int len = 0;
    while (row < N && map[row][col] == '*') {
      len++;
      row++;
    }
    st.append(len).append(" ");
    return new int[] {row - 1, col};
  }

  private static void searchLeftLeg(int row, int col) {
    int len = 0;
    while (row < N && col >= 0 && map[row][col] == '*') {
      len++;
      row++;
    }
    st.append(len).append(" ");
  }

  private static void searchRightLeg(int row, int col) {
    int len = 0;
    while (row < N && col < N && map[row][col] == '*') {
      len++;
      row++;
    }
    st.append(len).append("\n");
  }
}
