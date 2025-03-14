package hyojin.string;

// https://www.acmicpc.net/problem/1940

import java.util.Arrays;
import java.util.Scanner;

/**
 * 핵심 문장
 * 주어진 재료 중 두 개의 숫자의 합이 M이 되는 경우의 수를 찾아라.
 *
 * 2. 시간 복잡도 (투포인터)
 * O(N log N)
 *
 * 3. 슈도코드
 * 입력값(N, M, 재료 목록)
 * 재료 몰록 정렬
 * 투 포인터를 사용하여 반복:
 *    - left 0, right= N-1
 *    - left < right 반복:
 *      - numbers[left] + numbers[right] == M이면 count 증가, left++, right--
 *      - numbers[left] + numbers[right] < M이면 left++
 *      - numbers[left] + numbers[right] > M이면 right--
 * count 값을 출력
 */
public class BOJ_1940_주몽 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] materials = new int[N];

        for (int i = 0; i < N; i++) {
            materials[i] = sc.nextInt();
        }

        // 1. 정렬
        Arrays.sort(materials);

        // 2. 투 포인터 탐색
        int left = 0, right = N - 1, count = 0;

        while (left < right) {
            int sum = materials[left] + materials[right];

            if (sum == M) {
                count++;
                left++;
                right--;
            } else if (sum < M) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(count);
        sc.close();
    }
}
