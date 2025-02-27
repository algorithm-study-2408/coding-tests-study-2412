package jaehoon._10주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2468
public class BOJ_2468_안전영역 {

  // 입력예시:
  // 5
  // 6 8 2 6 2
  // 3 2 3 4 6
  // 6 7 3 3 2
  // 7 2 5 3 6
  // 8 9 5 2 7
  //
  // 출력예시:
  // 5
  //

  static int N;

  static int[][] map;

  static boolean[][] visited;

  static int[] dy = {-1, 0, 1, 0};
  static int[] dx = {0, 1, 0, -1};

  static int max = 0;

  public static void main(String[] args) throws IOException {
    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    int maxHight = 0;
    map = new int[N][N];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        maxHight = Math.max(maxHight, map[i][j]);
      }
    }

    // 1. 높이 순회 (0 ~ maxHight) - 최소높이가 1이므로 무조건 1개를 보장해야함
    for (int h = 0; h <= maxHight; h++) {
      // 2. 비가 온 만큼 N*N 전체를 순회하면서 탐색
      int count = getSafetyCount(h);
      // 3. 반환 받은 최대 안전영역이 최대값이면 갱신
      max = Math.max(max, count);
    }

    // 4. 가장 큰 최대값 출력
    System.out.println(max);
    br.close();
  }

  private static int getSafetyCount(int hight) {
    // 방문 배열 초기화
    visited = new boolean[N][N];
    int count = 0;

    // 전체 맵을 순회
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 잠기지 않는곳 BFS 탐색 시작
        if (!visited[i][j] && map[i][j] > hight) {
          bfs(i, j, hight);
          count++;
        }
      }
    }
    return count;
  }

  private static void bfs(int row, int col, int hight) {
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    queue.offerLast(new int[] {row, col});
    visited[row][col] = true;

    while (!queue.isEmpty()) {
      int[] now = queue.pollFirst();
      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
          continue;
        }

        if (visited[ny][nx] || map[ny][nx] <= hight) {
          continue;
        }

        visited[ny][nx] = true;
        queue.offerLast(new int[] {ny, nx});
      }
    }
  }
}
