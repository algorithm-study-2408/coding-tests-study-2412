package jaehoon._02주차;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/92342
public class Lv02_양궁대회 {

  private static int maxDiffScore;

  private static int[] apeach;

  private static int[] answer;

  public static int[] solution(int n, int[] info) {
    apeach = info; // 어피치 과녁판을 전역변수로 사용
    maxDiffScore = 0; // 최대 점수 차
    backtrack(n, 0, new int[11]);
    // max가 0인 경우 -1 리턴
    return maxDiffScore == 0 ? new int[]{-1} : answer;
  }

  // n: 남은 화살개수, idx: 과녁의 인덱스, ryan: 라이언의 과녁 배열
  private static void backtrack(int n, int idx, int[] ryan) {
    if(n == 0) {
      calculateDiff(ryan);
      return;
    }

    for(int i = idx; i <= 10; i++) {
      int count = Math.min(n, apeach[i] + 1);
      ryan[i] = count; // 해당 과녁에 화살
      backtrack(n - count, i + 1, ryan);
      ryan[i] = 0; // 과녁 복원
    }

  }

  private static void calculateDiff(int[] ryan) {
    int diffScore = getDiffScore(ryan);
    if(maxDiffScore < diffScore) {
      maxDiffScore = diffScore; // 점수차가 더크면 갱신
      answer = ryan.clone();
    }
    //  가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우,
    //  가장 낮은 점수를 더 많이 맞힌 경우를 찾음
    else if (maxDiffScore > 0 && maxDiffScore == diffScore) {
      for(int i = 10; i >= 0; i--) {
        if(answer[i] != ryan[i]) {
          // 더 낮은 점수를 맞춘 경우 과녁 갱신
          if(answer[i] < ryan[i]) {
            answer = ryan.clone();
          }
          break;
        }
      }
    }
  }

  private static int getDiffScore(int[] ryan) {
    int score = 0;
    for(int i = 0; i <= 10; i++) {
      if(ryan[i] + apeach[i] > 0) {
        score += ryan[i] > apeach[i] ? (10 - i) : -(10 - i);
      }
    }
    return score;
  }

  public static void main(String[] args) {
    int n1 = 5;
    int[] info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    int[] result1 = solution(n1, info1);
    System.out.println("result1 = " + Arrays.toString(result1));

    int n2 = 1;
    int[] info2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] result2 = solution(n2, info2);
    System.out.println("result2 = " + Arrays.toString(result2));
  }
}
