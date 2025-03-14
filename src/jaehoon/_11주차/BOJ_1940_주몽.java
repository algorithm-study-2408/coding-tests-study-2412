package jaehoon._11주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1940
public class BOJ_1940_주몽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(arr);

    int count = 0;
    int lt = 0, rt = N - 1;
    while (lt < rt) {
      int sum = arr[lt] + arr[rt];
      if (sum == M) {
        count++;
        lt++;
        rt--;
        continue;
      }
      if (sum < M) {
        lt++;
      } else {
        rt--;
      }
    }
    System.out.println(count);
    br.close();
  }
}
