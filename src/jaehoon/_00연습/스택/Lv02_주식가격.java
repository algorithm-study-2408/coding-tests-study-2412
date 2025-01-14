package jaehoon._00연습.스택;

import java.util.ArrayDeque;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42584
public class Lv02_주식가격 {

  public static void main(String[] args) {
    int[] prices = {1, 2, 3, 2, 3};
    System.out.println("result = " + Arrays.toString(solution(prices)));
  }

  public static int[] solution(int[] prices) {
    int len = prices.length;
    int[] answer = new int[len];
    ArrayDeque<Num> stack = new ArrayDeque<>();

    // 1. 가격을 뒤에서 부터 확인하면서 거리를 계산
    for (int i = len - 1; i >= 0; i--) {
      // 1-1. 스택이 비어있지 않으면, 스택에서 꺼내면서 가격이 떨어진 시간 탐색
      while (!stack.isEmpty()) {
        if (stack.peekLast().num >= prices[i]) { // 스택에 저장되어있는 다음시간이 현재시간보다 크다면 스택에서 제거
          stack.pollLast();
        } else { // 작다면 while문 종료 (가격이 떨어진 시간을 찾음)
          break;
        }
      }

      // 1-2. 스택이 비어 있다면, 맨뒤의 인덱스에서 현재 인덱스를 빼면 차이값을 알 수 있음
      if (stack.isEmpty()) {
        answer[i] = (len - 1) - i;
      } else { // 비어있지 않다면, 떨어진 가격까지의 시간 저장
        answer[i] = stack.peekLast().idx - i;
      }
      // 1-3. 새로운 번호 스택에 저장
      stack.addLast(new Num(i, prices[i]));
    }
    return answer;
  }

  private static class Num {
    int idx;
    int num;

    public Num(int idx, int num) {
      this.idx = idx;
      this.num = num;
    }
  }
}
