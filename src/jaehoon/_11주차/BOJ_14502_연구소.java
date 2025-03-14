package jaehoon._11주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

  private static int[] dy = {-1, 0, 1, 0};
  private static int[] dx = {0, 1, 0, -1};
  private static int[][] map;
  private static int N;
  private static int M;
  private static ArrayDeque<Node> virus = new ArrayDeque<>();

  private static class Node {
    int r;
    int c;

    public Node(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }

  // 입력예시:
  // 7 7
  // 2 0 0 0 1 1 0
  // 0 0 1 0 1 2 0
  // 0 1 1 0 1 0 0
  // 0 1 0 0 0 0 0
  // 0 0 0 0 0 1 1
  // 0 1 0 0 0 0 0
  // 0 1 0 0 0 0 0
  //
  // 출력예시:
  // 27
  //
  public static void main(String[] args) throws IOException {
    // 입력 받기
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 2) {
          virus.addLast(new Node(i, j));
        }
      }
    }

    int answer = 0;

    ArrayDeque<Node> wall = new ArrayDeque<>();
    // 벽을 세울 수 있는 빈칸의 조합을 만듬 (3칸)
    // 조합 생성 시 빈 칸만 탐색하므로, map[i][j] == 0인 칸들을 리스트 wall에 저장 - [i / M]하면 행의 위치, [i % M]하면 열의 위치
    for (int i = 0; i < N * M - 2; i++) {
      if (map[i / M][i % M] != 0) {
        continue;
      }
      wall.addLast(new Node(i / M, i % M));
      for (int j = i + 1; j < N * M - 1; j++) {
        if (map[j / M][j % M] != 0) {
          continue;
        }
        wall.addLast(new Node(j / M, j % M));
        for (int k = j + 1; k < N * M; k++) {
          if (map[k / M][k % M] != 0) {
            continue;
          }
          wall.addLast(new Node(k / M, k % M));
          // 조합을 가지고 bfs 탐색
          answer = Math.max(answer, bfs(wall));
          wall.pollLast();
        }
        wall.pollLast();
      }
      wall.pollLast();
    }

    System.out.println(answer);
    br.close();
  }

  private static int bfs(ArrayDeque<Node> wall) {
    // 맵을 복사해서 벽을 세우고, 바이러스를 전염시킬 것임
    int[][] cMap = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        cMap[i][j] = map[i][j];
      }
    }

    // 벽(1) 3개를 세움
    for (Node node : wall) {
      cMap[node.r][node.c] = 1;
    }

    // 바이러스가 있는 위치 복사해서 사용
    ArrayDeque<Node> queue = new ArrayDeque<>();
    for (Node node : virus) {
      queue.addLast(node);
    }

    // 바이러스(2)가 있는 위치 기준으로 BFS로 탐색하면서 전염시킴
    while (!queue.isEmpty()) {
      Node now = queue.poll();
      // 4방향 이동
      for (int i = 0; i < 4; i++) {
        int ny = now.r + dy[i];
        int nx = now.c + dx[i];

        // 맵의 경계를 넘으면 넘김
        if (ny < 0 || nx < 0 || ny >= N || nx >= M) {continue;}

        // 전염될 수 있는 곳이면, 감염시킴 (방문처리도 같이 함)
        if (cMap[ny][nx] == 0) {
          cMap[ny][nx] = 2;
          queue.addLast(new Node(ny, nx));
        }

      }
    }

    // 남아있는 빈칸(안전영역) 개수 세기
    int count = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (cMap[i][j] == 0) {
          count++;
        }
      }
    }

    return count;
  }

}