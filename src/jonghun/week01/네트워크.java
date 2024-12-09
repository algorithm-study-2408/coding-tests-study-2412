package jonghun.week01;

/**
 * [pseudocode]<br>
 * solution(n, computers):<br>
 *    1. visited 배열 생성해서 방문여부 체크<br>
 *    2. 네트워크 개수 0으로 초기화<br>
 *    <br>
 *    3. for i = 0 to n-1:<br>
 *        if (i번째 컴퓨터 미방문):<br>
 *            i번째 컴퓨터부터 DFS 수행<br>
 *            네트워크 개수 증가<br>
 *    <br>
 *    4. 네트워크 개수 반환<br>
 * <br>
 * dfs(n, computers, visited, now):<br>
 *    현재 컴퓨터 방문처리<br>
 *    <br>
 *    for i = 0 to n-1:<br>
 *        if (현재 컴퓨터와 i가 연결 && i번 미방문 && 자기자신이 아님):<br>
 *            i번 컴퓨터로 DFS 수행(재귀)<br>
 *
 * */

public class 네트워크 {

    public static void main(String[] args) {
        int n1 = 3;
        int[][] computers = {{1, 1, 0},{1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n1, computers));
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        int num = 0;

        /* 모든 컴퓨터를 살펴보며 네트워크에 연결되어있는 컴퓨터는 true처리하며 개수 세기*/
        for(int i=0; i<n;i++){
            if(!visited[i]){
                dfs(n,computers, visited, i);
                num++;
            }

        }
        answer = num;
        return answer;
    }

    private static void dfs(int n, int[][] computers, boolean[] visited, int i){
        visited[i]=true;

        // 연결되어있다면 타고 들어가기
        for(int j=0;j<n;j++){
            if(computers[i][j]==1 && !visited[j] && j!=i){
                dfs(n,computers,visited,j);

            }
        }

    }

}
