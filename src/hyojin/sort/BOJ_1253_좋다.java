package hyojin.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 핵심 문장
 * 1. 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 -> 투 포인터
 *
 * 시간복잡도
 * 1. N(1 ≤ N ≤ 2,000) 이므로 O(N^2)도 가능!
 *
 * 슈도 코드
 * 입력값 N, arr
 * arr 정렬
 * for (N의 개수만큼)
 *  좋은수일경우 카운트 증가
 *
 * fun 좋은수(인덱스)
 * left, right 입력
 * while(true)
 *  1. left가 인덱스일경우
 *  2. right가 인덱스일경우
 *  3. 그게 아니라면 두 수를 합한다.
 *  4. 좋은수가 되면 종료
 *  5. 아닐경우 left 또는 right 이동
 *
 */

public class BOJ_1253_좋다 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (isGoodNumber(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean isGoodNumber(int index) {
        int target = arr[index];
        int left = 0, right = N - 1;

        // 투포인트의 조건, left가 작아야한다.
        while (left < right) {
            // left, right가 index라면 스킵한다.
            if (left == index) {
                left++;
                continue;
            }

            if (right == index) {
                right--;
                continue;
            }

            int sum = arr[left] + arr[right];

            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
