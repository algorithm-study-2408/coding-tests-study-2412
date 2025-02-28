package jaehoon._10주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2606
public class BOJ_2606_바이러스 {

  // 입력예시:
  // 7
  // 6
  // 1 2
  // 2 3
  // 1 5
  // 5 2
  // 5 6
  // 4 7
  //
  // 출력예시:
  // 4
  //

  static boolean[] check;

  static ArrayList<Integer>[] adj;

  static int count = 0;

  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    M = Integer.parseInt(br.readLine());

    adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }

    check = new boolean[N + 1];
    for (int i = 0; i < M; i++) {
      StringTokenizer str = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(str.nextToken());
      int b = Integer.parseInt(str.nextToken());
      adj[a].add(b);
      adj[b].add(a);
    }
    dfs(1);
    System.out.println(count);

  }

  public static void dfs(int start) {
    check[start] = true;
    for (int v : adj[start]) {
      if (!check[v]) {
        dfs(v);
        count++;
      }
    }
  }
}
