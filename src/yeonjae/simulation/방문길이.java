package yeonjae.simulation;

import java.util.*;

public class 방문길이 {
    public int solution(String dirs) {
        int x = 0, y = 0;
        // 방문한 경로를 저장할 Set
        Set<String> visitedPaths = new HashSet<>();

        // 방향 이동 정의 (위, 아래, 오른쪽, 왼쪽)
        Map<Character, int[]> moves = new HashMap<>();
        moves.put('U', new int[]{0, 1});
        moves.put('D', new int[]{0, -1});
        moves.put('R', new int[]{1, 0});
        moves.put('L', new int[]{-1, 0});

        for (char dir : dirs.toCharArray()) {
            int[] move = moves.get(dir);
            int nx = x + move[0];
            int ny = y + move[1];

            // 좌표평면 경계 체크
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
                continue;
            }

            // 경로를 문자열로 표현 (양방향 모두 저장)
            String path1 = x + " " + y + "->" + nx + " " + ny;
            String path2 = nx + " " + ny + "->" + x + " " + y;

            visitedPaths.add(path1);
            visitedPaths.add(path2);

            x = nx;
            y = ny;
        }

        // 방문한 길의 개수 계산 (양방향 중 한쪽만 계산)
        return visitedPaths.size() / 2;
    }

    public static void main(String[] args) {
        방문길이 solution = new 방문길이();

        // 예제 입력
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        // 출력
        System.out.println(solution.solution(dirs1));
        System.out.println(solution.solution(dirs2));
    }
}
