package seyoung._2412_3주차;

public class 삼각달팽이 {

    public static void main(String[] args) {
        삼각달팽이 T = new 삼각달팽이();
        int n = 4;
        int[] solution = T.solution(n);
        for (int i : solution) {
            System.out.print(i + ", ");
        }
    }

    public int[] solution(int n) {
        int[] answer = {};
        //x, y 방향을 가리키는 배열
        int[] dx = {0, 1, -1};
        int[] dy = {1, 0, -1};
        int[][] board = new int[n][n];
        int x = 0;
        int y = 0;
        int value = 1;
        int dIdx = 0;

        while(true) {
            board[y][x] = value;
            value++;
            int nextX = x + dx[dIdx];
            int nextY = y + dy[dIdx];
            //방향전환
            if ( nextX == n || nextY == n || board[nextY][nextX] != 0) {
                dIdx = (dIdx + 1) % 3; //for문 없이 다음 dx dy 선택
                nextX = x + dx[dIdx];
                nextY = y + dy[dIdx];
                if ( nextX == n || nextY == n || board[nextY][nextX] != 0) {
                    break;
                }
            }
            x = nextX;
            y = nextY;

        }
        answer = new int[value-1];
        int idx = 0;
        for(int i = 0; i< n; i++) {
            for(int j = 0; j <= i; j++) {
                answer[idx] = board[i][j];
                idx++;
            }
        }

        return answer;
    }
}
