package jaehoon._04주차.순열_조합;

public class 순열 {

  static int[] arr = {1, 2, 3};

  static int n = 3; // 총 숫자의 개수

  static int r = 3; // 뽑을 숫자의 개수

  public static void main(String[] args) {
    makePermutation(n, r, 0);
  }

  public static void print() {
    for (int i = 0; i < r; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void makePermutation(int n, int r, int depth) {
    // 기저조건
    if (depth == r) {
      print();
      return;
    }
    // depth 부터 n까지
    for (int i = depth; i < n; i++) {
      System.out.println("(" + i + " : " + depth + ") - 순서변경");
      swap(i, depth); // 위치변경
      System.out.println("func(" + n + ", " + r + ", " + depth + ")");
      makePermutation(n, r, depth + 1); // 재귀함수호출
      System.out.println("(" + i + " : " + depth + ") - 원복");
      swap(i, depth); // 원복
    }
  }

  public static void swap(int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

}
