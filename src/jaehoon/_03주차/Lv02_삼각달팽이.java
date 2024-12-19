package jaehoon._03주차;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/68645
public class Lv02_삼각달팽이 {

  public static void main(String[] args) {
    int n1 = 4;
    int n2 = 5;
    int n3 = 6;
    System.out.println("result1 = " + Arrays.toString(solution(n1)));
    System.out.println("result2 = " + Arrays.toString(solution(n2)));
    System.out.println("result3 = " + Arrays.toString(solution(n3)));
  }

  public static int[] solution(int n) {
    int num = 1; // 증가할 숫자
    int[][] arr = new int[n][n];

    int y = -1; // 행 : 맨처음은 0부터 시작하므로 -1로 시작
    int x = 0; // 열
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (i % 3 == 0) {
          y++; // 아래로 이동
        }
        if (i % 3 == 1) {
          x++; // 오른쪽으로 이동
        }
        if (i % 3 == 2) {
          y--; // 위로 이동
          x--; // 왼쪽으로 이동
        }

        arr[y][x] = num++;
      }
    }

    int[] answer = new int[num - 1];
    int index = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // 숫자가 없으면 break
        if (arr[i][j] == 0) {
          break;
        }
        answer[index++] = arr[i][j];
      }
    }
    return answer;
  }
}
