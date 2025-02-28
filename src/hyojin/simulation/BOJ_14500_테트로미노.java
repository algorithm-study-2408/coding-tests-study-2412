package hyojin.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 핵심 문장 요약
 * 테트리스 도형 규칙
 * 정사각형 4개 총 5가지
 * 테트로미노 하나 놓인 칸에 쓰여 있는 수들의 합을 최대
 * 회전이나 대칭을 시켜도 된다.
 */

/**
 * 시간복잡도 확인
 * 세로 크기 N과 가로 크기 M(4 ≤ N, M ≤ 500)
 * N * M * dfs 깊이 최대 4번 = 496 * 496 * 4^2 = 약 백만 (10^6)
 */

public class BOJ_14500_테트로미노 {
    static int N, M, max;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        max = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기
        paper = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;

                combi(i, j, 0, 0, paper[i][j]);
            }
        }

        System.out.println(max);
    }

    // ㅓ, ㅜ, ㅏ, ㅗ 테트로미노 (x, y 중심을 기준으로 start + 1하여 3개 방향의 칸을 선택하는 방식)
    public static void combi(int x, int y, int depth, int start, int sum) {
        if(depth == 3) { // 3개의 블록을 선택하면 최댓값 갱신
            max = Math.max(max, sum);
            return;
        }

        // 상하좌우 중 3개를 선택
        for(int i = start; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                combi(x, y, depth + 1, i + 1, sum + paper[nx][ny]);
            }
        }
    }

    public static void dfs(int x, int y, int depth, int sum) {
        if(depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        // 상하좌우 이동
        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if(visited[nx][ny]) continue; // 이미 방문한 곳이면 스킵

                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
                visited[nx][ny] = false; // 백트래킹

            }
        }
    }
}
