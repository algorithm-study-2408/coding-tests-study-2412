package yeonjae.dfs;

public class 네트워크 {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networkCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 방문하지 않은 컴퓨터라면
                dfs(i, computers, visited); // DFS로 연결된 컴퓨터 탐색
                networkCount++;
            }
        }

        return networkCount;
    }

    private void dfs(int node, int[][] computers, boolean[] visited) {
        visited[node] = true; // 현재 컴퓨터 방문 표시

        for (int neighbor = 0; neighbor < computers.length; neighbor++) {
            // 연결되어 있고, 방문하지 않은 컴퓨터라면 탐색
            if (computers[node][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, computers, visited);
            }
        }
    }

    public static void main(String[] args) {
        네트워크 solution = new 네트워크();

        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(3, computers1)); // 출력: 2

        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution.solution(3, computers2)); // 출력: 1
    }
}
