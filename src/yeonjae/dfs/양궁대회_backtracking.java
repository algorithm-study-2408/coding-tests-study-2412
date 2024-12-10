package yeonjae.dfs;

import java.util.*;

public class 양궁대회_backtracking {

    static int max = 0; // 최대 점수 차이
    static int[] best; // 가장 좋은 화살 배치

    public static int[] solution(int n, int[] info) {
        best = new int[11];
        Arrays.fill(best, -1);

        // 백트래킹 시작
        backtracking(0, n, new int[11], info, 0, 0);

        // 라이언이 우승할 방법이 없는 경우
        if (max == 0) {
            return new int[]{-1};
        }
        return best;
    }

    private static void backtracking(int index, int arrows, int[] lionScores, int[] apeachScores, int lionScore, int apeachScore) {
        // 가지치기: 화살이 모두 소진된 경우
        if (arrows < 0) return;

        // 과녁 점수를 모두 탐색한 경우
        if (index == 11) {
            // 남은 화살은 0점에 추가
            lionScores[10] += arrows;

            // 점수 차이 계산
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

        // 가지치기: 점수 차이를 줄 수 없는 경우
        if (max > 0 && apeachScore >= lionScore + 10 * (11 - index)) {
            return;
        }

        // 1. 현재 점수를 맞히는 경우
        int lionShot = apeachScores[index] + 1;
        lionScores[index] = lionShot;
        backtracking(index + 1, arrows - lionShot, lionScores, apeachScores, lionScore + (10 - index), apeachScore);
        lionScores[index] = 0; // 원상 복구

        // 2. 현재 점수를 맞히지 않는 경우
        int apeachShot = apeachScores[index];
        backtracking(index + 1, arrows, lionScores, apeachScores, lionScore, apeachScore + (apeachShot > 0 ? 10 - index : 0));
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

