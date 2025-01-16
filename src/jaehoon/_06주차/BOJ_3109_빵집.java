package jaehoon._06주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 핵심 아이디어:<p>
 * 1. 탐색의 순서<p>
 * - 이미 간 곳은 파이프를 설치 할 수 없음 (지도에 표시)<p>
 * - 공유한 칸은 사용하지 않고, 자신만 갈 수 있는 곳으로 감<p>
 * <br>
 * 2. DFS -> 갈 수 있는 곳을 계속 탐색 (오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선 순서로 진행)<p>
 * - 먼저 오른쪽 위 대각선을 선택해 최대한 위쪽으로 올라가 경로를 확보.
 * - 처음 간 곳이 성공하면, 다른 곳을 방문하지 않고 끝내야 함 (그래야 다른 시작이 시도 할 수 있음)<p>
 * - 재귀에서 boolean 타입을 리턴해서, 값에 따라 다음 재귀가 갈지 말지 결정하게 됨<p>
 * <br>
 * 3. 실패하면 취소처리 해주지 않아야 함<p>
 * - 이전에 실패하면 실패하는 경로임<p>
 */
public class BOJ_3109_빵집 {

  static int R, C;

  static char[][] map;

  static boolean[][] visited;

  static int[] dr = {-1, 0, 1}; // 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선

  static int pipelineCount = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    map = new char[R][];
    visited = new boolean[R][C];

    for (int i = 0; i < R; i++) {
      map[i] = br.readLine().toCharArray();
    }

    for (int i = 0; i < R; i++) {
      if (makePipe(i, 0)) {
        pipelineCount++;
      }
    }

    System.out.println(pipelineCount);
  }

  // DFS
  static boolean makePipe(int r, int c) {
    // 마지막 열에 도달하면 파이프 연결 성공
    if (c == C - 1) {
      return true;
    }

    // 3방향으로 표시하면서 가보기
    for (int i = 0; i < 3; i++) {
      int nr = r + dr[i];
      int nc = c + 1;

      if (isIn(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
        // 방문 표시
        visited[nr][nc] = true;
        if (makePipe(nr, nc)) { // boolean 타입을 리턴해서, 다음 재귀가 갈지 말지 판단
          return true; // 경로 성공 시 반환
        }
      }
    }
    return false; // 경로를 찾지 못한 경우
  }

  private static boolean isIn(int nr, int nc) {
    return nr >= 0 && nr < R && nc >= 0 && nc < C;
  }
}
