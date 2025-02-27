package jaehoon._09주차.live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 슈도 코드<p>
 * 1. 입력값을 받아 2차원 배열 map[N][M]을 생성한다.<p>
 * 2. 방향 벡터를 이용해 DFS로 4칸을 탐색하며 최댓값을 갱신한다.<p>
 * - DFS는 현재 좌표 (x, y)에서 4칸을 탐색하며, 방문한 위치를 `visited`로 체크한다.<p>
 * 3. 'ㅗ' 모양 테트로미노는 DFS로 탐색할 수 없으므로 별도로 계산<p>
 * - '+' 형태에서 한 칸을 제거하는 방식으로 4가지 경우를 검사한다.<p>
 * 4. 최댓값을 출력한다.<p>
 */
// https://www.acmicpc.net/problem/14500
public class BOJ_14500_테트로미노 {

  static int N, M;

  static int[][] map;

  static boolean[][] visited;

  static int max = 0;

  // 북, 동, 남, 서
  static int[] dy = {-1, 0, 1, 0}; // 행
  static int[] dx = {0, 1, 0, -1}; // 열

  // 중앙을 기준으로 모양 생성
  static int[][][] specialCase = {
      {{-1, 0}, {1, 0}, {0, 1}}, // ㅏ
      {{0, -1}, {0, 1}, {1, 0}}, // ㅜ
      {{0, -1}, {-1, 0}, {1, 0}}, // ㅓ
      {{0, -1}, {-1, 0}, {0, 1}} // ㅗ
  };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    visited = new boolean[N][M];
    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      map[i] = Arrays.stream(str.split(" "))
                     .mapToInt(Integer::parseInt)
                     .toArray();
    }

    // 모든 방향 진행
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        visited[i][j] = true;
        dfs(i, j, 1, map[i][j]);
        visited[i][j] = false;

        // 'ㅗ' 모양 특수 케이스에 대해 결과값 처리
        checkSpecial(i, j);
      }
    }
    System.out.println(max);
    br.close();
  }

  // ㅓ, ㅜ, ㅏ, ㅗ 테트로미노
  private static void dfs(int row, int col, int count, int sum) {
    if (count == 4) {
      max = Math.max(max, sum);
      return;
    }

    for (int i = 0; i < 4; i++) {
      int ny = row + dy[i];
      int nx = col + dx[i];

      if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
        continue;
      }

      if (visited[ny][nx]) {
        continue;
      }

      visited[ny][nx] = true;
      dfs(ny, nx, count + 1, sum + map[ny][nx]);
      visited[ny][nx] = false;
    }
  }

  private static void checkSpecial(int row, int col) {
    // 'ㅗ' 모양 처리
    for (int[][] ways : specialCase) {
      int sum = map[row][col];
      boolean isValid = true;
      for (int[] way : ways) {
        int ny = row + way[0];
        int nx = col + way[1];
        if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
          sum += map[ny][nx];
        } else {
          isValid = false;
          break;
        }
      }
      if (isValid) {
        max = Math.max(max, sum);
      }
    }
  }
}