package yeonjae.greedy;

public class 마법의엘레베이터 {
  public static int solution(int storey) {
    int answer = 0;
    int carry = 0; // 올림 여부를 저장

    while (storey > 0) {
      int digit = storey % 10; // 현재 자리 숫자
      storey /= 10; // 나머지 자리

      digit += carry; // 올림 반영

      if (digit > 5 || (digit == 5 && storey % 10 >= 5)) {
        // 반올림
        answer += 10 - digit;
        carry = 1;
      } else {
        // 그냥 처리
        answer += digit;
        carry = 0;
      }
    }

    // 마지막에 올림이 남으면 처리
    if (carry > 0) {
      answer += carry;
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution(16));
    System.out.println(solution(2554));
  }
}
