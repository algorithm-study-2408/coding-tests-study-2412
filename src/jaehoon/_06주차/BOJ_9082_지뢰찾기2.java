package jaehoon._06주차;

import java.io.*;
import java.util.Arrays;

/**
 * 핵심 아이디어<p>
 * 1. 숫자 배열 `first`의 각 숫자는 해당 칸과 인접한 칸에 배치 가능한 지뢰 수를 나타냄.<p>
 * 2. 최대 지뢰를 배치하려면:<p>
 *    - 첫 번째 칸과 마지막 칸은 각각 두 칸만 확인하면 됨<p>
 *    - 중간 칸은 왼쪽, 자신, 오른쪽의 숫자를 확인해 모두 0이 아닌 경우 지뢰를 배치함<p>
 * 3. 매번 지뢰를 배치할 때 해당 숫자를 감소시켜 더 이상 지뢰를 배치할 수 없는 상태를 반영<p>
 * <br>
 * 풀이법<p>
 * - 각 칸을 순회하며 가능한 곳에 지뢰를 배치<p>
 * - 첫 번째 칸과 마지막 칸은 따로 처리하고, 중간 칸은 반복문으로 처리<p>
 */
public class BOJ_9082_지뢰찾기2 {
  /**
   * 입력예시:
   * 2
   * 5
   * 11122
   * ####*
   * 5
   * 23321
   * #####
   * <p>
   * 출력예시:
   * 3
   * 4
   */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

    for (int i = 0; i < T; i++) {
      int result = 0; // 총 지뢰 개수
      int N = Integer.parseInt(br.readLine()); // 배열 크기
      String first = br.readLine(); // 숫자 배열 (지뢰의 개수 정보)
      String second = br.readLine(); // 숨겨진 블록 정보 (# 또는 *)

      char[] arr = first.toCharArray(); // 숫자 배열을 문자 배열로 변환
      // **첫 번째 칸 처리**
      // 첫 번째 칸과 두 번째 칸이 0이 아니면, 지뢰를 배치
      if (N > 1 && arr[0] != '0' && arr[1] != '0') {
        arr[0]--;
        arr[1]--;
        result++;
      }

      // **마지막 칸 처리**
      // 마지막 칸과 그 앞의 칸이 0이 아니면, 지뢰를 배치
      if (N > 1 && arr[N - 1] != '0' && arr[N - 2] != '0') {
        arr[N - 1]--;
        arr[N - 2]--;
        result++;
      }

      // **중간 칸 처리**
      // 중간 칸 (1부터 N-2까지)에 대해 인접한 세 칸이 모두 0이 아닐 경우, 지뢰를 배치
      for (int j = 1; j < N - 1; j++) {
        if (arr[j - 1] != '0' && arr[j] != '0' && arr[j + 1] != '0') {
          arr[j - 1]--;
          arr[j]--;
          arr[j + 1]--;
          result++;
        }
        System.out.println("arr = " + Arrays.toString(arr));
      }

      // 결과 출력
      bw.write(result + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
