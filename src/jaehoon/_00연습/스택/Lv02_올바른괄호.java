package jaehoon._00연습.스택;

import java.util.ArrayDeque;
import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/12909
public class Lv02_올바른괄호 {

  public static void main(String[] args) {
    String s1 = "[{()}()]";
    System.out.println("result1 = " + solution(s1));
    String s2 = "({{}))(";
    System.out.println("result2 = " + solution(s2));
  }

  public static boolean solution(String s) {
    HashMap<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('{', '}');
    map.put('[', ']');

    ArrayDeque<Character> stack = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
      // 1. "("이면 스택에 넣고
      if (map.containsKey(ch)) {
        stack.addLast(ch);
      }
      // 2. ")"이면 스택에서 뺀다, 만약 스택이 비어있으면 false
      else {
        if (stack.isEmpty()) {
          return false;
        }
        if (ch != map.get(stack.pollLast())) {
          return false;
        }
      }
    }
    // 3. 문자열을 모두 순회했는데, 스택이 남아있어도 false
    return stack.isEmpty();
  }
}
