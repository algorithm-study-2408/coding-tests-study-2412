package jaehoon._02주차.live;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/92335
public class Lv02_k진수에서_소수점_구하기 {

  public static void main(String[] args) {
    int n = 437674;
    int k = 3;
    System.out.println("result = " + solution(n, k));
  }

  public static int solution(int n, int k) {
    // 1. 정수 n을 k진수로 변경
    String base = Integer.toString(n, k);

    // 2. 조건에 맞는 소수찾기
    int result = 0;
    for (String str : split(base, "0")) {
      if (str.isEmpty()) continue;
      if (isPrimeNumber(Long.parseLong(str))) {
        result++;
      }
    }
    return result;
  }

  private static boolean isPrimeNumber(long n) {
    if (n < 2) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static List<String> split(String str, String delimiter) {
    List<String> result = new ArrayList<>();
    int start = 0;
    int end = str.indexOf(delimiter);
    while (end != -1) {
      result.add(str.substring(start, end));
      start = end + delimiter.length();
      end = str.indexOf(delimiter, start);
    }
    result.add(str.substring(start));
    return result;
  }
}
