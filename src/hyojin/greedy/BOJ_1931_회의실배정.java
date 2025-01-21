package hyojin.greedy;

// https://www.acmicpc.net/problem/1931

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1931_회의실배정 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] A = new int[N][2];

        // 배열 정렬
        for (int i = 0; i < N; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }

        // 우선순위 정렬
        Arrays.sort(A, (o1, o2) -> {
            // 종료시간이 같을 경우
            if (o1[1] == o2[1]) {
                // 시작시간 기준으로 정렬
                return o1[0] - o2[0];

            }
            // 종료시간 기준으로 정렬
            return  o1[1] - o2[1];
        });

        int count = 0;
        int end = -1;

        // 반복
        for (int i = 0; i < N; i++) {
            if (A[i][0] >= end) {
                end = A[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}
