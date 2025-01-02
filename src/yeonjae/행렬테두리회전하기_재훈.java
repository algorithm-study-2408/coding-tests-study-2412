package yeonjae;

import java.util.ArrayDeque;
import java.util.Arrays;

public class 행렬테두리회전하기_재훈 {

    public static void main(String[] args) {
        int[][] queries = {
                {2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}
        };
        int rows = 6;
        int columns = 6;
        System.out.println("result = " + Arrays.toString(solution(rows, columns, queries)));
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        // 1. 배열 초기화
        int num = 1;
        int[][] rc = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rc[i][j] = num++;
            }
        }

        // 2. 쿼리 순회
        for (int[] query : queries) {
            int[] start = {query[0], query[1]};
            int[] end = {query[2], query[3]};

            // 회전할 범위 구하기
            int[][] ints = new int[query[2] - query[0] + 1][query[3] - query[1] + 1];
            int row = -1;
            for (int i = start[0]; i <= end[0]; i++) {
                row++;
                int col = -1;
                for (int j = start[1]; j <= end[1]; j++) {
                    col++;
                    ints[row][col] = rc[i - 1][j - 1];
                }
            }

            // 회전하기
            rotate(ints);

            // 원본 배열에 합치기

            // 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담기
        }

        int[] answer = {};
        return answer;
    }

    private static void rotate(int[][] ints) {
        ArrayDeque<Integer> left = new ArrayDeque<>();
        ArrayDeque<ArrayDeque<Integer>> mid = new ArrayDeque<>();
        ArrayDeque<Integer> right = new ArrayDeque<>();

        // 데큐구조로 변경
        for (int i = 0; i < ints.length; i++) {
            left.addFirst(ints[i][0]);
            ArrayDeque<Integer> midQ = new ArrayDeque<>();
            for (int j = 1; j < ints[i].length - 1; j++) {
                midQ.addLast(ints[i][j]);
            }
            mid.addLast(midQ);
            right.addLast(ints[i][ints[i].length - 1]);
        }

        //  회전
        mid.peekFirst().addFirst(left.pollFirst());
        right.addFirst(mid.peekFirst().pollLast());
        mid.peekLast().addLast(right.pollLast());
        right.addLast(mid.peekFirst().pollFirst());
    }
}

