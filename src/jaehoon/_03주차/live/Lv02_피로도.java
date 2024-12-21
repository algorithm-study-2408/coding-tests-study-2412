package jaehoon._03주차.live;

public class Lv02_피로도 {

  public static void main(String[] args) {
    int k = 80;
    int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
    System.out.println("result = " + solution(k, dungeons));
  }

  static boolean[] visited;

  static int answer;

  public static int solution(int k, int[][] dungeons) {
    visited = new boolean[dungeons.length];
    dfs(0, k, dungeons);
    return answer;
  }

  public static void dfs(int count, int k, int[][] dungeons) {
    for (int i = 0; i < dungeons.length; i++) {
      // 방문하지 않았고, 최소 필요 피로도 요건을 충족하면 -> 방문
      if (!visited[i] && dungeons[i][0] <= k) {
        visited[i] = true; // 방문표시
        dfs(count + 1, k - dungeons[i][1], dungeons);
        visited[i] = false; // 방문표시 제거
      }
    }
    answer = Math.max(count, answer); // 최대 던전 방문횟수를 갱신
  }
}
