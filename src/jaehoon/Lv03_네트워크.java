package jaehoon;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162
public class Lv03_네트워크 {

  private static boolean[] visited;

  public static int solution(int n, int[][] computers) {
    visited = new boolean[n]; // 컴퓨터가 네트워크로 연결되는지를 기록할 배열
    int count = 0; // 네트워크 수

    // 1. 컴퓨터 배열을 순회
    for (int i = 0; i < computers.length; i++) {
      // 1-1. 연결되지 않은 네트워크라면 DFS 탐색
      if (!visited[i]) {
        dfs(computers, i);
        // 1-2. 탐색이 끝나면 네트워크 1증가
        count++;
      }
    }
    return count;
  }

  private static void dfs(int[][] computers, int n) {
    // 1. 방문처리 하고
    visited[n] = true;
    // 2. 인접한 노드 순회
    for (int i = 0; i < computers.length; i++) {
      // 2-1. 연결되어 있는데, 방문하지 않은 노드라면 방문하러 감
      if (computers[n][i] == 1 && !visited[i]) {
        dfs(computers, i);
      }
    }
  }

  public static void main(String[] args) {
    int n = 3;
    int[][] computers1 = {
        {1, 1, 0},
        {1, 1, 0},
        {0, 0, 1}
    };
    int[][] computers2 = {
        {1, 1, 0},
        {1, 1, 1},
        {0, 1, 1}
    };

    int result1 = solution(n, computers1);
    System.out.println("result1 = " + result1);

    int result2 = solution(n, computers2);
    System.out.println("result2 = " + result2);
  }
}
