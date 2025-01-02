package jaehoon._00연습.스택;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17298
public class BOJ_17298_오큰수 {

  public static void main(String[] args) throws IOException {
    // 입력 예시
    // 4
    // 3 5 2 7
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] intArr = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      intArr[i] = Integer.parseInt(st.nextToken());
    }

    // 출력 예시
    // 5 7 7 -1
    int[] result = solution(n, intArr);
    for (int j : result) {
      bw.write(j + " ");
    }
    bw.flush();
    br.close();
  }

  public static int[] solution(int n, int[] a) {
    int[] answer = new int[n];
    ArrayDeque<Integer> stack = new ArrayDeque<>();

    // 1. 뒤에서 부터 확인하면서 오큰수를 탐색
    for (int i = n - 1; i >= 0; i--) {
      // 1-1. 스택에 있는 값이 자신보다 큰 수일 때 까지 제거
      while (!stack.isEmpty() && stack.peekLast() <= a[i]) { // O(N)
        stack.pollLast();
      }
      // 1-2. 만약 스택이 비어 있으면 -1 리턴, 남아있다면 남아있는 수가 오큰수
      answer[i] = stack.isEmpty() ? -1 : stack.peekLast();

      // 1-3. 자기 자신 넣기
      stack.addLast(a[i]);
    }
    return answer;
  }
}
