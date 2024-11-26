package hyojin.sort;

import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 */
public class 제출내역 {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        int result = solution(citations);
        System.out.println(result);
    }

    /**
     * |index|논문의 인용 횟수| 남아있는 논문 수(현재 인덱스와 그 뒤의 논문의 개수) = n - i |
     * |0   | 0          | 5                 |
     * |1   | 1          | 4                 |
     * |2   | 3          | 3                 |
     * |3   | 5          | 2                 |
     * |4   | 6          | 1                 |
     */
    private static int solution(int[] citations) {
        // 최댓값 h를 찾기 위해 정렬
        Arrays.sort(citations);

        int n = citations.length;
        int hIndex = 0;

        // 인용 횟수가 낮은 논문부터 높은 논문까지 순차적으로 검사
        for (int i = 0; i < n; i++) {
            // 현재 위치에서 남은 논문 수(현재 인덱스와 그 뒤의 논문의 개수)
            int h = n - i;

            // h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면
            // h의 최댓값이 이 과학자의 H-Index
            // 0~5번의 인덱스 값과 5~0값의 남은 논문 수 비교하여
            // 현재 논문 인용 횟수가 남아있는 논문 수의 이상일 때
            if (citations[i] >= h) {
                hIndex = h;
                break;
            }
        }

        return hIndex;
    }

}
