package hyojin.string;

// https://www.acmicpc.net/problem/14502

import java.util.*;

/**
 * 슈도 코드
 * 입력값 N, M, map
 * 빈 칸(0) 좌표를 리스트에 저장
 * 벽을 세울 수 있는 모든 (빈 칸 중 3개 선택) 조합을 구한다.
 * 각 조합에 대해:
 *    1 벽을 세운다.
 *    2 BFS를 수행하여 바이러스를 퍼뜨린다.
 *    3 안전 영역 크기를 계산한다.
 *    4 최댓값을 갱신한다.
 *    5 벽을 원상복구한다.
 * 결과 출력
 */
public class BOJ_14502_연구소 {
    static int N, M;
    static int[][] lab;
    static List<int[]> emptySpaces = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();
    static int maxSafeZone = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                lab[i][j] = sc.nextInt();
                if (lab[i][j] == 0) emptySpaces.add(new int[]{i, j});
                else if (lab[i][j] == 2) viruses.add(new int[]{i, j});
            }
        }

        buildWalls(0, 0);
        System.out.println(maxSafeZone);
    }

    static void buildWalls(int count, int start) {
        if (count == 3) {
            maxSafeZone = Math.max(maxSafeZone, getSafeZoneSize());
            return;
        }

        for (int i = start; i < emptySpaces.size(); i++) {
            int[] pos = emptySpaces.get(i);
            lab[pos[0]][pos[1]] = 1;  // 벽 세우기
            buildWalls(count + 1, i + 1);
            lab[pos[0]][pos[1]] = 0;  // 원상복구
        }
    }

    static int getSafeZoneSize() {
        int[][] tempLab = new int[N][M];
        for (int i = 0; i < N; i++)
            tempLab[i] = lab[i].clone(); // 배열 복사

        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : viruses) {
            queue.offer(virus);
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && tempLab[nx][ny] == 0) {
                    tempLab[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int safeCount = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (tempLab[i][j] == 0) safeCount++;

        return safeCount;
    }
}
