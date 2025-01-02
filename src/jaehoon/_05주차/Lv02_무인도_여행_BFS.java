package jaehoon._05주차;

import java.util.*;

public class Lv02_무인도_여행_BFS {

  public static void main(String[] args) {
    String[] maps1 = {"X591X", "X1X5X", "X231X", "1XXX1"};
    String[] maps2 = {"XXX", "XXX", "XXX"};

    System.out.println(java.util.Arrays.toString(solution(maps1))); // [1, 1, 27]
    System.out.println(java.util.Arrays.toString(solution(maps2))); // [-1]
  }

  // 상, 하, 좌, 우 이동을 위한 방향 배열
  private static final int[] dy = {-1, 0, 1, 0};
  private static final int[] dx = {0, 1, 0, -1};
  private static boolean[][] visited;

  public static int[] solution(String[] maps) {
    List<Integer> result = new ArrayList<>();
    int rows = maps.length;
    int cols = maps[0].length();
    visited = new boolean[rows][cols];

    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < cols; x++) {
        // 땅이고 방문하지 않은 경우 BFS 시작
        if (!visited[y][x] && maps[y].charAt(x) != 'X') {
          int dateSum = bfs(maps, y, x);
          result.add(dateSum);
        }
      }
    }

    // 결과를 오름차순 정렬
    if (result.isEmpty()) return new int[]{-1};
    Collections.sort(result);
    return result.stream().mapToInt(i -> i).toArray();
  }

  // BFS 구현
  private static int bfs(String[] maps, int startY, int startX) {
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    // 시작 지점 큐에 넣고, 방문 처리
    queue.add(new int[]{startY, startX});
    visited[startY][startX] = true;

    int foodSum = 0;
    while (!queue.isEmpty()) {
      int[] current = queue.pollFirst();
      int y = current[0];
      int x = current[1];

      // 현재 칸의 식량 값을 합산
      foodSum += maps[y].charAt(x) - '0';

      // 상, 하, 좌, 우 인접 칸 탐색
      for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];


        // 경계를 벗어나면 무시
        if (ny < 0 || ny >= maps.length || nx < 0 || nx >= maps[0].length()) {
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

        queue.addLast(new int[]{ny, nx});
        visited[ny][nx] = true; // 방문 표시
      }
    }

    return foodSum;
  }
}
