package hyojin.dfs;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162

public class 네트워크 {
    public static void main(String[] args) {
        int n1 = 3;
        int[][] computers1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println(solution(n1, computers1));

        int n2 = 3;
        int[][] computers2 = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        System.out.println(solution(n2, computers2));
    }

    public static int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networkCount = 0;

        // 각 컴퓨터를 순회
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {              // 아직 방문하지 않은 컴퓨터라면
                dfs(computers, visited, i); // DFS로 네트워크 탐색
                networkCount++;             // 탐색이 끝나면 네트워크 개수 증가
            }
        }

        return networkCount;
    }

    // 깊이 우선 탐색(DFS) 메서드
    private static void dfs(int[][] computers, boolean[] visited, int current) {
        visited[current] = true; // 현재 컴퓨터 방문 처리

        // 연결된 모든 컴퓨터를 순회
        for (int i = 0; i < computers.length; i++) {
            // 연결되어 있고 아직 방문하지 않은 경우
            if (computers[current][i] == 1 && !visited[i]) {
                dfs(computers, visited, i); // 재귀적으로 탐색
            }
        }
    }
}
