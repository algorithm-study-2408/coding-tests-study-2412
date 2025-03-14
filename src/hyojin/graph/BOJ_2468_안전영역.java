package hyojin.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2468

/**
 * 핵심 문장 요약
 * 1. 일정 값 이하는 물에 잠긴다
 * 2. 안전한 영역이라함은 상화좌우 연결되있어야한다, 꼭지점은 인접하지 않다
 * 3. 내리는 비의 양에 따라서 물에 잠기지 않는 안전한 영역의 개수는 다르게 된다
 * 4. 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수
 *
 * 시간복잡도는 N이 100이하이므로 자유롭다
 */

/**
 * 슈도 코드
 * 1. 맵의 값을 읽어온다
 * 2. 조건에 부합하면 dfs를 이용하여 인급 값들을 visited true로 처리하고 count를 1 증가시킨다
 * 3. 조건에 부합하지 않으면 visited를 true로 하고 다음으로 이동한다.
 *
 */
public class BOJ_2468_안전영역 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int maxHeight = 0; // 지역에서 가장 높은 지점 찾기

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeZone = 1; // 안전 영역 개수 (최소 1개는 존재)

        for (int h = 1; h <= maxHeight; h++) { // 강수량을 1부터 최대 높이까지 변경
            visited = new boolean[N][N];
            int count = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        dfs(i, j, h);
                        count++;
                    }
                }
            }
            maxSafeZone = Math.max(maxSafeZone, count); // 최대값 갱신
        }

        System.out.println(maxSafeZone);
    }

    private static void dfs(int x, int y, int height) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny] && map[nx][ny] > height) {
                    dfs(nx, ny, height);
                }
            }
        }
    }

}
