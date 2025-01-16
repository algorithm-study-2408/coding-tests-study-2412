package jaehoon._06주차;

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13458
public class BOJ_13458_시험감독 {

  // 입력예시:
  // 5
  // 10 9 10 9 10
  // 7 2
  //
  // 출력예시:
  // 13
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 시험장 수 입력
    int N = Integer.parseInt(br.readLine());
    int[] A = new int[N];

    // 각 시험장의 학생 수 입력
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    int B = Integer.parseInt(st.nextToken()); // 총 감독관
    int C = Integer.parseInt(st.nextToken()); // 부 감독관

    long proctorCount = 0;
    for (int students : A) {
      if (students == 0) {
        continue;
      }
      // 총 감독관 배정
      students -= B;
      proctorCount++;
      // 부 감독관 배정
      if (students > 0) {
        proctorCount += (students + (C - 1)) / C; // 올림 계산
      }
    }
    bw.write(String.valueOf(proctorCount));
    bw.flush();
    br.close();
    bw.close();
  }
}
