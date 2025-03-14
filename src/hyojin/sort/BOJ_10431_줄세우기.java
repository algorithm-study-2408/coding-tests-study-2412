package hyojin.sort;

import java.util.Scanner;

/**
 * 핵심문장
 * 우선 아무나 한 명을 뽑아 줄의 맨 앞에 세운다. 그리고 그 다음부터는 학생이 한 명씩 줄의 맨 뒤에 서면서 다음 과정을 거친다.
 * 1. 자기 앞에 자기보다 키가 큰 학생이 없다면 그냥 그 자리에 서고 차례가 끝난다.
 * 2. 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다. 이때, A부터 그 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다.
 * 이 과정을 반복하면 결국 오름차순으로 줄을 설 수가 있다.
 *
 * 시간복잡도
 * P (1 ≤ P ≤ 1000)
 *
 * 슈도 코드
 * 입력값 P
 * for (P의 개수)
 *  heights[] 입력
 *  입력값 total
 *  for (20만큼 반복하되 1부터 시작)
 *
 */

public class BOJ_10431_줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int P = sc.nextInt();

        for (int t = 0; t < P; t++) {
            int T = sc.nextInt();  // 테스트 케이스 번호
            int[] heights = new int[20];

            for (int i = 0; i < 20; i++) {
                heights[i] = sc.nextInt();
            }

            int total = 0;

            for (int i = 1; i < 20; i++) {
                int currentHeight = heights[i];
                int index = i;  // 현재 학생이 들어갈 위치는 처음엔 i

                // 현재 학생보다 키가 큰 학생이 있는지 확인
                for (int j = 0; j < i; j++) {
                    if (heights[j] > currentHeight) {  // 키가 더 큰 학생을 찾으면
                        index = j;  // 그 학생보다 앞에 서야 하므로 그 자리를 찾음
                        total += (i - j);  // 뒤로 물러난 걸음 수
                        break;
                    }
                }

                // 현재 학생은 그 자리에 배치하고, 뒤의 학생들은 한 칸씩 뒤로 밀기
                for (int k = i - 1; k >= index; k--) {
                    heights[k + 1] = heights[k];
                }

                // 현재 학생을 그 위치에 배치
                heights[index] = currentHeight;
            }

            System.out.println(T + " " + total);
        }

        sc.close();
    }
}
