package jaehoon._04주차;

public class Lv02_점프와_순간이동 {

  public static void main(String[] args) {
    int n1 = 5;
    System.out.println("result1 = " + solution(n1));
    int n2 = 6;
    System.out.println("result2 = " + solution(n2));
    int n3 = 5000;
    System.out.println("result3 = " + solution(n3));
  }

  public static int solution(int n) {
    int zump = 0;

    while(n != 0){
      // 짝수면 순간이동으로 끝낼수 있음
      if(n % 2 == 0){
        n /= 2;
      }
      // 홀수면 점프 1 필요
      else {
        n--;
        zump++;
      }
    }
    return zump;
  }
}
