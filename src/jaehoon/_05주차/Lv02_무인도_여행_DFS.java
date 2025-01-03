package jaehoon._05주차;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/154540
public class Lv02_무인도_여행_DFS {

  public static void main(String[] args) {
    String[] maps1 = {"X591X", "X1X5X", "X231X", "1XXX1"};
    String[] maps2 = {"XXX", "XXX", "XXX"};

    System.out.println(java.util.Arrays.toString(solution(maps1))); // [1, 1, 27]
    System.out.println(java.util.Arrays.toString(solution(maps2))); // [-1]
  }

  // 북, 동, 남, 서 이동을 위한 방향 배열
  private static final int[] dy = {-1, 0, 1, 0};
  private static final int[] dx = {0, 1, 0, -1};
  private static boolean[][] visited;

  public static int[] solution(String[] maps) {
    List<Integer> result = new ArrayList<>();
    int rows = maps.length;
    int cols = maps[0].length();
    visited = new boolean[rows][cols];

    // 1. 열부터 확인하면서 시작지점에서 DFS
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        // 1-1. 땅이고 방문하지 않은 경우 DFS 시작
        if (!visited[y][x] && maps[y].charAt(x) != 'X') {
          int dateSum = dfs(maps, y, x);
          result.add(dateSum);
        }
      }
    }

    // 2. 만약 머무를 수 있는 섬이 없다면 -1 리턴
    if (result.isEmpty()) {
      return new int[]{-1};
    }

    // 3. 결과를 오름차순 정렬해서 반환
    Collections.sort(result);
    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  private static int dfs(String[] maps, int y, int x) {
    int rows = maps.length;
    int cols = maps[0].length();

    // 방문 처리
    visited[y][x] = true;

    // 현재 칸의 식량 값 추가
    int foodSum = maps[y].charAt(x) - '0';

    // 상하좌우 탐색
    for (int i = 0; i < 4; i++) {
      int ny = y + dy[i];
      int nx = x + dx[i];

      // 경계를 벗어나면 무시
      if (ny < 0 || ny >= rows || nx < 0 || nx >= cols) {
        continue;
      }

      // 바다 무시
      if (maps[ny].charAt(nx) == 'X') {
        continue;
      }

      // 이미 방문했다면 무시
      if (visited[ny][nx]) {
        continue;
      }

      // 인접 칸 DFS 탐색
      foodSum += dfs(maps, ny, nx);
    }

    return foodSum;
  }
}
