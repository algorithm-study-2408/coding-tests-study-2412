package jaehoon._06주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 핵심 아이디어<p>
 * 1. 숫자 배열의 합을 기반으로 지뢰 계산<p>
 * - 첫 번째 줄에는 각 위치와 그 주변에 숨겨진 지뢰 개수를 나타내는 숫자가 제공<p>
 * - 이 숫자들의 총합(total)을 구하면, 배열에 포함된 모든 지뢰의 총합을 계산할 수 있음<p>
 * - (숫자의 합은 숨겨진 지뢰와 *로 표시된 이미 발견된 지뢰를 모두 포함)<p>
 * <br>
 * 2. 3개가 하나의 지뢰 그룹을 형성<p>
 * - 각 지뢰는 해당 숫자의 영향을 주기 때문에 숫자 합을 3으로 나누는 방식으로 지뢰의 최대 개수를 추정할 수 있음<p>
 * - (total + 2) / 3은 주어진 숫자 합을 3으로 나눈 결과의 올림 값을 계산하는 방식
 * - 이는 지뢰가 하나라도 남아있으면 새로운 지뢰를 추가로 고려하는 결과를 제공<p>
 */
// https://www.acmicpc.net/problem/9082
public class BOJ_9082_지뢰찾기 {
  /**
   * 입력예시:
   * 2
   * 5
   * 11122
   * ####*
   * 5
   * 23321
   * #####
   *
   * 출력예시:
   * 3
   * 4
   */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    // 테스트 케이스 개수
    for (int t = 0; t < T; t++) {
      int total = 0;
      int N = Integer.parseInt(br.readLine());
      String str = br.readLine();
      for (int i = 0; i < N; i++) {
        // 각 숫자의 합을 계산 (total이 모든 지뢰의 영향을 포함)
        // total은 배열에 있는 지뢰가 각 숫자에 "중복 포함"되어 더해진 값 (한 지뢰가 최대 3번 포함될 수 있음)
        total += (str.charAt(i) - '0');
      }
      br.readLine(); // 빈 문자 제거

      System.out.println((total + 2) / 3); // 최대 지뢰 개수 계산 "+2는 올림을 구현하기 위한 보정"
    }
    br.close();
  }
}
