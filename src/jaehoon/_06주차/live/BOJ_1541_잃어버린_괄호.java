package jaehoon._06주차.live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1541
public class BOJ_1541_잃어버린_괄호 {
  // 입력예시:
  // 55-50+40
  //
  // 출력예시:
  // -35
  public static void main(String[] args) throws IOException {
    // 1. 입력받은 문자를 - 기준으로 split
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), "-");
    List<Integer> list = new ArrayList<>();
    while (st.hasMoreTokens()) {
      String str = st.nextToken();
      // 1-1. +가 있는 문자이면 + 연산해서 리스트에 값을 넣어준다.
      int num = 0;
      if (str.contains("+")) {
        String[] split = str.split("\\+");
        for (String i : split) {
          num += Integer.parseInt(i);
        }
      }
      // 1-2. +가 없는 문자면 그대로 넣어준다.
      else {
        num = Integer.parseInt(str);
      }
      list.add(num);
    }

    // 2. 맨 처음 값을 설정하고, 리스트에서 하나씩 빼면서 값을 계산한다.
    int calculation = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      calculation -= list.get(i);
    }
    System.out.println(calculation);
    br.close();
  }
}
