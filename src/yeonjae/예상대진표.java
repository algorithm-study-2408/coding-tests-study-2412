package yeonjae;

public class 예상대진표 {
  public int solution(int n, int a, int b) {
    int round = 0;

    // A와 B가 만날 때까지 반복
    while (a != b) {
      // 라운드 증가
      round++;

      // 다음 라운드의 번호 계산
      a = (a + 1) / 2;
      b = (b + 1) / 2;
    }

    return round;
  }

  public static void main(String[] args) {
    예상대진표 main = new 예상대진표();
    int n = 8;
    int a = 4;
    int b = 7;

    int result = main.solution(n, a, b);
    System.out.println(result); // 3
  }

}
