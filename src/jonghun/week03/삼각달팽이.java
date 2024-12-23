package jonghun.week03;

import java.util.Arrays;

public class 삼각달팽이 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(4)));
    }

    public static int[] solution(int n) {
        int[] answer = new int[n * (n + 1) / 2];
        int[][] triangle = new int[n][n];
        // 현재 위치
        int x = 0, y = 0;
        // 채울 숫자
        int num = 1;
        // 특정방향으로 이동할 거리
        int len = n;

        while(len > 0) {
            // 대각선 아래 먼저
            for(int i = 0; i < len; i++) {
                triangle[x++][y] = num++;
            }
            x--;
            y++;
            len--;

            // 오른쪽으로
            for(int i = 0; i < len; i++) {
                triangle[x][y++] = num++;
            }
            x--; y -= 2;
            len--;

            // 대각선 위로
            for(int i = 0; i < len; i++) {
                triangle[x--][y--] = num++;
            }
            x += 2; y++;
            len--;
        }

        // 종합
        int index = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[index++] = triangle[i][j];
            }
        }

        return answer;
    }

}
