package hyojin.test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/68645

/**
슈도
1. 삼각형 배열 초기화
   - 크기 n x n의 2차원 배열 생성, 모든 값을 0으로 초기화.

2. 방향 설정
   - 아래 → 오른쪽 → 대각선 위 순으로 이동하며 값을 채움.
   - 방향 전환 조건:
     - 배열 경계를 벗어나거나.
     - 이미 값이 채워진 경우.

3. 값 채우기
   - 시작 위치는 (0, 0), 채울 값은 1.
   - 최대 값은 n * (n + 1) / 2
   - 현재 위치에 값을 채운 후 다음 위치 계산.
   - 조건에 따라 방향 전환 후 계속 채움.

4. 결과 배열 생성
   - 삼각형 내부 값만 추출해 1차원 배열로 변환.

5. 결과 반환
   - 1차원 배열을 반환.
 */

public class 삼각달팽이 {
    public static int[] solution(int n) {
        // 1. 삼각형 배열 생성
        int[][] triangle = new int[n][n];

        // 2. 방향 설정 (아래, 오른쪽, 대각선 위)
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};

        // 시작점과 방향
        int x = 0, y = 0, direction = 0;
        // 1부터 n * (n + 1) / 2까지 채움
        int value = 1, max = n * (n + 1) / 2;

        while (value <= max) {
            triangle[x][y] = value++;

            // 다음 위치 계산
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 경계 조건 확인 (범위를 벗어나거나 이미 값이 채워진 경우)
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || triangle[nx][ny] != 0) {
                direction = (direction + 1) % 3; // 방향 전환
                nx = x + dx[direction];
                ny = y + dy[direction];
            }

            x = nx;
            y = ny;
        }

        // 3. 결과 배열 생성
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result.add(triangle[i][j]);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int n = 4;
        int[] result = solution(n);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
