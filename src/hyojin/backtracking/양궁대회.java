package hyojin.backtracking;

public class 양궁대회 {

    private static int max;       // 최대 점수
    private static int[] answer;  // 라이언의 화살 배열 결과값
    private static int[] apeach;  // 어피치의 화살 배열

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] solution = solution(n, info);
        for (int i : solution) {
            System.out.print(i + " ");
        }
    }

    public static int[] solution(int n, int[] info) {
        apeach = info;
        max = 0;
        backtrack(n, 0, new int[11]);  // 백트래킹 시작 (0번 과녁부터 시작)

        // 결과 반환: 최대 점수 차이가 0이라면 -1 반환, 아니면 최대 점수 차이를 얻은 라이언의 화살 배열 반환
        return max == 0 ? new int[]{-1} : answer;
    }

    // 백트래킹을 이용하여 라이언의 화살 분배를 시도하는 함수
    private static void backtrack(int n, int idx, int[] ryan) {
        // 화살을 모두 분배했으면, 점수 차이를 계산하고 종료
        if (n == 0) {
            calcuateDiff(ryan);
            return;
        }

        // 현재 idx부터 10까지 각 과녁에 대해 반복
        for (int i = idx; i <= 10; i++) {
            // 각 과녁에 쏘는 화살의 수는 남은 화살 수 n과 어피치가 맞힌 화살 수 + 1 중 적은 값
            int cnt = Math.min(n, apeach[i] + 1);
            ryan[i] = cnt;  // 라이언이 현재 과녁에 쏠 화살 수를 설정

            // 다음 과녁에 대해 재귀 호출 (화살 수 n에서 cnt만큼 사용)
            backtrack(n - cnt, i + 1, ryan);

            // 백트래킹: 현재 과녁에 쏘았던 화살을 되돌려 놓음
            ryan[i] = 0;
        }
    }

    // 라이언의 점수 차이를 계산하는 함수
    private static void calcuateDiff(int[] ryan) {
        int score = getScore(ryan);  // 점수 계산
        if (max < score) {
            // 만약 현재 점수가 max보다 크면, 최대 점수를 갱신하고 화살 분배 배열을 저장
            max = score;
            answer = ryan.clone();  // 라이언의 화살 배열 복사
        } else if (max > 0 && max == score) {
            // 점수가 동일하면, 사전순으로 더 작은 화살 배열을 선택 (우선순위 비교)
            for (int i = 10; i >= 0; i--) {
                if (answer[i] != ryan[i]) {
                    if (answer[i] < ryan[i]) {
                        answer = ryan.clone();  // 더 좋은 배열을 선택
                    }
                    break;
                }
            }
        }
    }

    // 라이언과 어피치의 점수 차이를 계산하는 함수
    private static int getScore(int[] ryan) {
        int score = 0;
        for (int i = 0; i <= 10; i++) {
            // 라이언과 어피치가 모두 0인 경우는 무시하고, 둘 중 하나라도 화살을 쏜 경우에 점수를 계산
            if (ryan[i] + apeach[i] > 0) {
                score += ryan[i] > apeach[i] ? (10 - i) : -(10 - i);  // 라이언이 이기면 점수, 어피치가 이기면 마이너스 점수
            }
        }
        return score;  // 계산된 점수 반환
    }
}
