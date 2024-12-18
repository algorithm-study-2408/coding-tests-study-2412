package jaehoon._03주차;

public class Lv02_타겟넘버 {

  public static void main(String[] args) {
    int[] numbers1 = {1, 1, 1, 1, 1};
    int target1 = 3;
    int result1 = solution(numbers1, target1);
    System.out.println("result1 = " + result1);
    count = 0; // count 초기화

    int[] numbers2 = {4, 1, 2, 1};
    int target2 = 4;
    int result2 = solution(numbers2, target2);
    System.out.println("result2 = " + result2);
  }

  static int count = 0;

  public static int solution(int[] numbers, int target) {
    recursive(numbers, target, 0, 0);
    return count;
  }

  public static void recursive(int[] numbers, int target, int level, int sum) {
    // 기저조건
    if (level == numbers.length) {
      if (sum == target) {
        count++;
      }
      return;
    }
    // 더하기
    recursive(numbers, target, level + 1, sum + numbers[level]);
    // 빼기
    recursive(numbers, target, level + 1, sum - numbers[level]);
  }
}
