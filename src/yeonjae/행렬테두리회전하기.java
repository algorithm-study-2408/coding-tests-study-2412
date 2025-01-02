package yeonjae;

import java.util.Arrays;

public class 행렬테두리회전하기 {
    int[][] board;

    public int[] solution(int rows, int columns, int[][] queries) {
        // 행렬 초기화
        board = new int[rows][columns];
        int value = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                board[i][j] = value;
                value++;
            }
        }

        int[] answer = new int[queries.length];
        int minIdx=0;

        for(int[] query : queries){
            int x1 = query[0]-1;
            int y1 = query[1]-1;
            int x2 = query[2]-1;
            int y2 = query[3]-1;
            int tmp = board[x1][y2];
            int min = tmp;

            // 위 값들 오른쪽으로 이동
            for(int i=y2-1; i>=y1; i--){
                min = Math.min(min, board[x1][i]);
                board[x1][i+1] = board[x1][i];
            }

            // 왼쪽 값들 위로 이동
            for(int i=x1+1; i<=x2; i++){
                min = Math.min(min, board[i][y1]);
                board[i-1][y1] = board[i][y1];
            }

            // 아래 값들 왼쪽으로 이동
            for(int i=y1+1; i<=y2; i++){
                min = Math.min(min, board[x2][i]);
                board[x2][i-1] = board[x2][i];
            }

            // 오른쪽 값들 아래로 이동
            for(int i=x2-1; i>=x1; i--){
                min = Math.min(min, board[i][y2]);
                board[i+1][y2] = board[i][y2];
            }

            board[x1+1][y2] = tmp;
            answer[minIdx] = min;
            minIdx++;
        }

        return answer;
    }

    public static void main(String[] args) {
        행렬테두리회전하기 solution = new 행렬테두리회전하기();

        // 메서드 직접 호출과 결과 출력
        System.out.println(Arrays.toString(solution.solution(
                6,
                6,
                new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}}
        )));

        System.out.println(Arrays.toString(solution.solution(
                3,
                3,
                new int[][]{{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}}
        )));

        System.out.println(Arrays.toString(solution.solution(
                100,
                97,
                new int[][]{{1, 1, 100, 97}}
        )));
    }
}
