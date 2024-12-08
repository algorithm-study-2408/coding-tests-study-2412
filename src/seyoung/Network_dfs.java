package seyoung;

public class Network_dfs {
    //방문 여부 체크 배열
    boolean[] visited;
    int answer = 0;

    public static void main(String[] args) {
        Network_dfs T = new Network_dfs();
        int n1 = 3;
        int[][] computers1 = {{1, 1, 0},{1, 1, 0}, {0, 0, 1}};
        System.out.println(T.solution(n1, computers1));

        int n2 = 3;
        int[][] computers2 = {{1, 1, 0},{1, 1, 1},{0, 1, 1}};
        System.out.println(T.solution(n2, computers2));
    }

    public void dfs(int n, int start, int[][] computers) {
        visited[start] = true; //방문처리
        for(int i = 0; i < n; i++) {
            //computers[i][i]는 카운트하지 않음
            if (i == start) continue;

            //첫 방문이면서 연결되어 있으면
            if (!visited[i] && computers[start][i] == 1) {
                dfs(n, i, computers);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            //방문하지 않은 컴퓨터마다 방문
            if (!visited[i]) {
                dfs(n, i, computers);
                //네트워크 갯수 1 증가
                answer++;
            }
        }

        return answer;
    }
}
