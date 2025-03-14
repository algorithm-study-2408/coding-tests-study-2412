package hyojin.tree;

// https://school.programmers.co.kr/learn/courses/30/lessons/43238

import java.io.IOException;
import java.util.Arrays;

/**
 * n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.
 *
 * 1. 핵심문장
 * 처음에 모든 심사대는 비어있습니다.
 * 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다.
 * 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다.
 * 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.
 *
 * 2. 시간 복잡도
 * 입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다. -> O(1) -> 트리로 풀어야한다 -> 사실은 이진탐색
 *
 * 3. 슈도 코드
 * 입력값 n(6), times([7, 10])
 * 시간 카운트
 * 인원 카운트
 * while (true)
 *  시간 카운트++
 *  7과 10의 배수이면 count++ (o(n) + n) // break continue
 *  count == n 이면 종료
 *  time 시간을 반환한다.
 *
 *  4. 확인사항
 *  - 파라메트릭서치
 */

public class 입국심사 {
    static int N;
    public static void main(String[] args) throws IOException {
        int n = 6;
        int[] times = {7, 10};
        long result = 0L;

        // 최대 10억 명(n = 1,000,000,000)이면 TLE가 발생, 1억 이하여야 함
        // 어떤 경우에는 7분의 카운팅을 더 했을 때 -> 예외 상황을 찾아보기
        /**
         * 테스트 1 〉	통과 (0.08ms, 87.7MB)
         * 테스트 2 〉	통과 (3.71ms, 79.1MB)
         * 테스트 3 〉	실패 (시간 초과)
         * 테스트 4 〉	실패 (시간 초과)
         * 테스트 5 〉	실패 (시간 초과)
         * 테스트 6 〉	실패 (시간 초과)
         * 테스트 7 〉	실패 (시간 초과)
         * 테스트 8 〉	실패 (시간 초과)
         * 테스트 9 〉	실패 (시간 초과)
         */
        result = 완전탐색(n, times);
        System.out.println("완전탐색 = " + result);

        result = 이진탐색(n, times);
        System.out.println("이진탐색 = " + result);
    }

    // long이 가질 수 있는 최대 범위 10^18? 64비트?
    private static long 이진탐색(int n, int[] times) {
        Arrays.sort(times); // 정렬 O(NlogN)

        long left = 1; // 최소 시간
        long right = (long) times[times.length - 1] * n; // 최악의 경우 (가장 느린 심사대가 모든 인원을 처리)
        long answer = right;


        while (left <= right) {
            // 중간 시간
            long mid = (left + right) / 2;

            long count = 0;
            for (int time : times) { // O(N)
                // 각 심사대에서 mid 시간 동안 처리 가능한 사람 수
                count += mid / time;
            }
            // 충분한 사람을 처리할 수 있으면 시간을 줄여본다.
            if (count >= n) {
                answer = mid;
                right = mid - 1;
            }
            // 부족하면 시간을 늘린다.
            else {
                left = mid + 1;
            }
        }

        return answer; // 최소 시간 반환
    }

    private static long 완전탐색(int n, int[] times) {
        long t = 0;

        while (true) {
            t++;  // 시간 증가
            int count = 0; // 매번 초기화

            for (int time : times) {
                count += t / time; // 현재 시간까지 심사받은 사람 수
            }

            if (count >= n) { // 모든 인원을 심사했다면 종료
                return t;
            }
        }
    }

}
