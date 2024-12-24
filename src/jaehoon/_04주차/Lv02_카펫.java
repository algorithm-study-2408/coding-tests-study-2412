package jaehoon._04주차;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42842
public class Lv02_카펫 {

  public static void main(String[] args) {
    int brown = 10;
    int yellow = 2;
    System.out.println("result = " + Arrays.toString(solution(brown, yellow)));
  }

  public static int[] solution(int brown, int yellow) {
    // 결과를 저장할 배열 && 총 격자 개수 생성
    int[] answer = new int[2];
    int sum = brown + yellow;

    // 카펫은 가로와 세로의 크기가 3 이상이어야 함 (옐로우 격자를 만들 수 있는 최소 크기이기때문)
    // 1. 노란색 격자의 개수가 1 이상, 행은 3부터 시작하는 범위에서 반복 (row와 col은 sum의 약수 쌍으로만 계산)
    for (int row = 3; row < sum / 2; row++) {
      int col = sum / row;

      // 옐로우 카펫의 넓이는 (가로 - 2) * (세로 - 2)가 되어야 한다.
      // 1-1. (행-2) * (열-2) 가 노란색 격자의 개수와 일치하는 경우
      if ((row - 2) * (col - 2) == yellow) {
        answer[0] = col;
        answer[1] = row;
        break;
      }
    }
    return answer;
  }
}
