package hyojin.graph.bfs;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/81302

public class 거리두기확인하기 {

    public static void main(String[] args) {
        String[][] p = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        int[] result = solution(p);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            //
            String[] p = places[i];

            boolean isOk = true;
            for (int r = 0; r < 5 && isOk; r++) {
                for (int c = 0; c < 5 && isOk; c++) {
                    if (p[r].charAt(c) == 'P') {
                        if (!bfs(r, c, p)) isOk = false;
                    }
                }
            }
            answer[i] = isOk ? 1 : 0;
        }

        return answer;
    }

    private static boolean bfs(int r, int c, String[] p) {
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nr = position[0] + dr[i];
                int nc = position[1] + dc[i];

                // 배열 범위를 벗어나거나, 현재 위치(r, c)와 동일한 좌석을 다시 큐에 추가하는 것을 방지
                if (nr < 0 || nc < 0
                        || nr >= 5 || nc >= 5
                        || (nr == r && nc == c)
                ) continue;

                // 멘헤튼 거리: 두 점 사이의 수평 + 수직 거리
                int d = Math.abs(nr - r) + Math.abs(nc - c);
                // 거리 2 미만의 P가 있을 경우
                if (p[nr].charAt(nc) == 'P' && d <= 2)
                    return false;
                    // 거리 2 이하의 O가 있을 경우
                else if (p[nr].charAt(nc) == 'O' && d < 2)
                    queue.offer(new int[]{nr, nc});
            }
        }

        return true;
    }
}
