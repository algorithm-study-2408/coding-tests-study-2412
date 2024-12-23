package yeonjae;

public class 괄호변환 {
  public String solution(String w) {
    if (w.isEmpty()) {
      return ""; // 1단계: 빈 문자열 처리
    }

    // 2단계: u, v로 나누기
    int open = 0;
    int close = 0;
    int cut = 0;
    for (int i = 0; i < w.length(); i++) {
      if (w.charAt(i) == '(') {
        open++;
      } else {
        close++;
      }
      if (open == close) {
        cut = i;
        break;
      }
    }

    String u = w.substring(0, cut + 1);
    String v = w.substring(cut + 1);

    if (check(u)) { // 3단계: u가 올바른 괄호 문자열인지 확인
      return u + solution(v);
    }

    // 4단계: u가 올바르지 않을 때 변환
    return "(" + solution(v) + ")" + reverse(u);
  }

  private boolean check(String u) {
    int count = 0;
    for (int i = 0; i < u.length(); i++) {
      if (u.charAt(i) == '(') {
        count++;
      } else {
        count--;
      }
      if (count < 0) {
        return false; // 올바르지 않음
      }
    }
    return true;
  }

  private String reverse(String u) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < u.length() - 1; i++) {
      if (u.charAt(i) == '(') {
        sb.append(')');
      } else {
        sb.append('(');
      }
    }
    return sb.toString();
  }

}
