package jaehoon._08주차;

import java.util.*;

public class BOJ_11559_PuyoPuyo {

  static char[][] map = new char[12][6];

  private static boolean[][] visited;

  static int[] dy = {-1, 0, 1, 0}; // 위, 오른, 아래, 왼

  static int[] dx = {0, 1, 0, -1};

  static int answer = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 입력 받기
    for (int i = 0; i < 12; i++) {
      map[i] = sc.nextLine().toCharArray();
    }

    // 연쇄가 계속 발생할 때까지 반복
    while (true) {
      visited = new boolean[12][6];
      boolean isPopped = false;

      // 1. 같은 색 뿌요 4개이상 그룹 찾고 터뜨리기
      for (int i = 0; i < 12; i++) {
        for (int j = 0; j < 6; j++) {
          if (map[i][j] != '.' && !visited[i][j]) {
            if (bfs(i, j)) {
              isPopped = true;
            }
          }
        }
      }

      // 2. 연쇄가 없으면 종료
      if (!isPopped) {
        break;
      }
      // 3. 뿌요가 터지면 중력에 의해 아래로 떨어뜨리기, 연쇄 횟수 증가
      applyGravity();
      answer++;
    }
    System.out.println(answer);
  }

  static boolean bfs(int row, int col) {
    Queue<int[]> queue = new ArrayDeque<>();
    List<int[]> popList = new ArrayList<>();
    queue.offer(new int[] {row, col});
    popList.add(new int[] {row, col});
    visited[row][col] = true;

    while (!queue.isEmpty()) {
      int[] now = queue.poll();
      for (int i = 0; i < 4; i++) {
        int ny = now[0] + dy[i];
        int nx = now[1] + dx[i];

        // 범위 체크 & 같은색인지 확인
        if (nx < 0 || ny < 0 || nx >= 6 || ny >= 12) {
          continue;
        }

        if (visited[ny][nx] || map[ny][nx] != map[row][col]) {
          continue;
        }

        queue.offer(new int[] {ny, nx});
        popList.add(new int[] {ny, nx});
        visited[ny][nx] = true;
      }
    }

    // 4개 이상 연결되어 있으면 터뜨리기
    if (popList.size() >= 4) {
      for (int[] pos : popList) {
        map[pos[0]][pos[1]] = '.';
      }
      return true;
    }
    return false;
  }

  // 중력 처리: 위에 있는 뿌요들이 아래로 떨어짐
  static void applyGravity() {
    // 열별로 검사
    for (int j = 0; j < 6; j++) {
      List<Character> puyos = new ArrayList<>();
      /*
       * 기존의 뿌요를 모두 리스트에 넣고 '.'으로 바꿈
       * -> 맵에 있는 가장 아래부터 뿌요들을 차례로 다시 채움
       */

      // 기존의 뿌요가 임시저장
      for (int i = 11; i >= 0; i--) {
        if (map[i][j] != '.') {
          puyos.add(map[i][j]);
          map[i][j] = '.';
        }
      }
      // 뿌요를 가장 밑에 있는 빈 칸에 채워나감
      int idx = 11;
      for (char puyo : puyos) {
        map[idx--][j] = puyo;
      }
    }
  }

}