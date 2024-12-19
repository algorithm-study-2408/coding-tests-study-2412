package jaehoon._02주차.live;

import java.util.Arrays;

public class Lv02_거리두기_확인하기 {
  public static void main(String[] args) {
    String[][] places = {
        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
    };
    int[] result = solution(places);
    System.out.println("result = " + Arrays.toString(result));
  }

  private static int[] dy = {0, 0, 1, -1};
  private static int[] dx = {1, -1, 0, 0};

  public static int[] solution(String[][] places) {
    int[] answer = {1, 1, 1, 1, 1};

    // 1. 대기실의 자리값을 2차원배열로 초기화
    for (int t = 0; t < places.length; t++) {
      char[][] place = new char[5][5];
      for (int i = 0; i < 5; i++) {
        place[i] = places[t][i].toCharArray();
      }

      // 2. P를 기준으로 거리 확인
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          // 대기실에서 응시자를 찾음
          if (place[i][j] == 'P') {
            for (int k = 0; k < 4; k++) {
              int ny = i + dx[k];
              int nx = j + dy[k];

              if (ny < 0 || ny > 4 || nx < 0 || nx > 4) continue;
              if (place[ny][nx] == 'P' || place[ny][nx] == 'p') {
                answer[t] = 0;
              }
              if (place[ny][nx] == 'O') {
                place[ny][nx] = 'p';
              }
            }
          }
        }
      }

    }
    return answer;
  }
}
