package yeonjae.dfs;

import java.util.*;

public class 양궁대회_dfs {

    static int max = 0; // 최대 점수 차이
    static int[] best; // 가장 좋은 화살 배치

    public static int[] solution(int n, int[] info) {
        best = new int[11];
        Arrays.fill(best, -1);

        // DFS 탐색 시작
        dfs(0, n, new int[11], info);

        // 라이언이 우승할 방법이 없는 경우
        if (max == 0) {
            return new int[]{-1};
        }
        return best;
    }

    private static void dfs(int index, int arrows, int[] lionScores, int[] apeachScores) {
        if (index == 11) { // 과녁 점수 모두 탐색 완료
            if (arrows < 0) return; // 화살 초과

            // 남은 화살은 0점에 추가
            lionScores[10] += arrows;

            // 점수 계산
            int apeachScore = 0, lionScore = 0;
            for (int i = 0; i < 11; i++) {
                if (apeachScores[i] == 0 && lionScores[i] == 0) continue;
                if (apeachScores[i] >= lionScores[i]) {
                    apeachScore += 10 - i;
                } else {
                    lionScore += 10 - i;
                }
            }

            // 점수 차이 갱신 및 조건 처리
            int diff = lionScore - apeachScore;
            if (diff > max) {
                max = diff;
                best = lionScores.clone();
            } else if (diff == max) {
                // 낮은 점수를 더 많이 맞힌 경우 선택
                for (int i = 10; i >= 0; i--) {
                    if (lionScores[i] > best[i]) {
                        best = lionScores.clone();
                        break;
                    } else if (lionScores[i] < best[i]) {
                        break;
                    }
                }
            }

            // 남은 화살 초기화
            lionScores[10] -= arrows;
            return;
        }

        // 1. 현재 점수를 맞히는 경우
        lionScores[index] = apeachScores[index] + 1;
        dfs(index + 1, arrows - lionScores[index], lionScores, apeachScores);
        lionScores[index] = 0; // 원상 복구

        // 2. 현재 점수를 맞히지 않는 경우
        dfs(index + 1, arrows, lionScores, apeachScores);
    }

    public static void main(String[] args) {
        // 예제 테스트
        int n1 = 5;
        int[] info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(solution(n1, info1))); // [0,2,2,0,1,0,0,0,0,0,0]

        int n2 = 1;
        int[] info2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(solution(n2, info2))); // [-1]

        int n3 = 9;
        int[] info3 = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(n3, info3))); // [1,1,2,0,1,2,2,0,0,0,0]

        int n4 = 10;
        int[] info4 = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        System.out.println(Arrays.toString(solution(n4, info4))); // [1,1,1,1,1,1,1,1,0,0,2]
    }
}
