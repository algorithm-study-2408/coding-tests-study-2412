package jaehoon._10주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7576
public class BOJ_7576_토마토 {

  // 입력예시:
  // 6 4
  // 1 -1 0 0 0 0
  // 0 -1 0 0 0 0
  // 0 0 0 0 -1 0
  // 0 0 0 0 -1 1
  //
  // 출력예시:
  // 6
  //

  static int[] dy = {-1, 0, 1, 0};

  static int[] dx = {0, 1, 0, -1};

  static int[][] storage;

  static int[][] dist;

  public static void main(String[] args) throws IOException {
    // 입력 받기
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    storage = new int[N][M]; // 토마토가 저장될 창고 배열 초기화
    dist = new int[N][M]; // 토마토가 익는데 걸린 시간을 저장할 배열 초기화
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        storage[i][j] = Integer.parseInt(st.nextToken());

        // 시작점이 여러개인 경우 미리 큐에 넣어둠
        if (storage[i][j] == 1) {
          queue.offerLast(new int[] {i, j});
        }
        // 익지 않은 토마토는 방문을 -1로 초기화 (익지 않은 토마토만 방문할 수 있도록)
        else if (storage[i][j] == 0) {
          dist[i][j] = -1;
        }

      }
    }
    // 2. 익은 토마토 기준으로 BFS 탐색
    while (!queue.isEmpty()) {
      int[] now = queue.pollFirst();
      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];

        if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
          continue;
        }

        if (dist[ny][nx] != -1) {
          continue; // 이미 방문했거나 벽(-1)인 경우 스킵
        }

        dist[ny][nx] = dist[now[0]][now[1]] + 1; // 토마토가 익는데 걸린 최대시간 + 1
        queue.offerLast(new int[] {ny, nx});
      }
    }

    // 3. 토마토가 익는데 걸린 최대시간 출력
    int result = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (dist[i][j] == -1) {
          // 익지 않은 토마토가 있다면 -1 출력
          System.out.println(-1);
          return;
        }
        result = Math.max(result, dist[i][j]);
      }
    }

    // 4. 토마토가 모두 익는데 걸린 시간 출력
    System.out.println(result);
    br.close();
  }
}
