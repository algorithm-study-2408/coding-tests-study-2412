package jaehoon._06주차.live;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1931
public class BOJ_1931_회의실_배정 {
  // 입력예시:
  // 11
  // 1 4
  // 3 5
  // 0 6
  // 5 7
  // 3 8
  // 5 9
  // 6 10
  // 8 11
  // 8 12
  // 2 13
  // 12 14
  //
  // 출력예시:
  // 4
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    Meeting[] meetings = new Meeting[n];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      meetings[i] = new Meeting(Integer.parseInt(st.nextToken()),
                                Integer.parseInt(st.nextToken()));
    }
    // 회의를 끝나는 시간으로 오름차순 정렬
    Arrays.sort(meetings, (a, b) -> {
      if (a.end == b.end) {
        return Integer.compare(a.start, b.start);
      }
      return Integer.compare(a.end, b.end);
    });

    int count = 1;
    int end = meetings[0].end;
    // 1. 회의 순회하면서, 진행할 수 있는 회의 확인
    for (int i = 1; i < n; i++) {
      // 1-1. 끝나는 시간 <= 시작 시간이면 회의 가능
      if (end <= meetings[i].start) {
        end = meetings[i].end;
        count++;
      }
    }
    System.out.println(count);
    br.close();
  }

  private static class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
