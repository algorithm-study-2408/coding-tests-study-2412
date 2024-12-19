package jaehoon._03주차;

public class Lv02_예상대진표 {

  public static void main(String[] args) {
    int N = 8;
    int A = 4;
    int B = 7;
    int result = solution(N, A, B);
    System.out.println("result = " + result);
  }

  public static int solution(int n, int a, int b) {
    int answer = 0;
    // 1. 두 라이벌이 만날때까지 반복
    while (a != b) {
      // 1-1. 승리할때 마다 대진번호를 변경 (항상이김)
      a = (a + 1) / 2;
      b = (b + 1) / 2;
      answer++;
    }
    return answer;
  }
}
