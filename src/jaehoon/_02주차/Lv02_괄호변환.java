package jaehoon._02주차;

import java.util.ArrayDeque;

// https://school.programmers.co.kr/learn/courses/30/lessons/60058
public class Lv02_괄호변환 {

  public static void main(String[] args) {
    String p1 = "(()())()";
    String p2 = ")(";
    String p3 = "()))((()";

    System.out.println("result1 = " + solution(p1));
    System.out.println("result2 = " + solution(p2));
    System.out.println("result3 = " + solution(p3));
  }

  // 1. 재귀 구현
  public static String solution(String w) {
    // 1-1. 빈문자열 처리
    if (w.isEmpty()) {
      return w;
    }

    // 1-2. u, v 나누기
    int index = 0;
    int count = 0;
    for (int i = 0; i < w.length(); i++) {
      if (w.charAt(i) == '(') {
        count++;
      } else {
        count--;
      }
      if(count == 0) {
        index = i;
        break;
      }
    }
    String u = w.substring(0, index + 1);
    String v = w.substring(index + 1);
    
    // 1-3. u가 올바른 괄호인 경우
    if (isRight(u)) {
      return u + solution(v);
    }
    // 1-4. u가 올바른 괄호가 아닌경우
    return "(" + solution(v) + ")" + reverse(u.substring(1, u.length() - 1));
  }

  public static boolean isRight(String u) {
    ArrayDeque<Character> stack = new ArrayDeque<>();
    for(char ch : u.toCharArray()) {
      if(ch == '(') {
        stack.addLast(ch);
      } else {
        if (stack.isEmpty()) {
          return false;
        } else {
          stack.removeLast();
        }
      }
    }
    return stack.isEmpty();
  }

  public static String reverse(String u) {
    StringBuilder sb = new StringBuilder();
    for (char ch : u.toCharArray()) {
      if (ch == '(') {
        sb.append(')');
      } else {
        sb.append('(');
      }
    }
    return sb.toString();
  }

}
