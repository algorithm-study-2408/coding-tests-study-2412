package jaehoon._08주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10709
public class BOJ_10709_기상캐스터 {

  private static class Cloud {
    int row;
    int col;

    public Cloud(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public void move() {
      col++;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int H = Integer.parseInt(st.nextToken());
    int W = Integer.parseInt(st.nextToken());

    List<Cloud> clouds = new ArrayList<>();

    int[][] map = new int[H][W];
    for (int i = 0; i < H; i++) {
      char[] charArray = br.readLine().toCharArray();
      for (int j = 0; j < W; j++) {
        if (charArray[j] == 'c') {
          map[i][j] = 0;
          clouds.add(new Cloud(i, j));
        } else {
          map[i][j] = -1;
        }
      }
    }

    // 구름 이동
    int time = 0;
    while (!clouds.isEmpty()) {
      time++;
      Iterator<Cloud> iterator = clouds.iterator();
      while (iterator.hasNext()) {
        Cloud now = iterator.next();
        now.move();
        if (now.col >= W) {
          iterator.remove();
          continue;
        }
        if (map[now.row][now.col] == -1) {
          map[now.row][now.col] = time;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < H; i++) {
      for (int j = 0; j < W; j++) {
        sb.append(map[i][j]);
        if (j < (W - 1)) {
          sb.append(" ");
        }
      }
      sb.append("\n");
    }
    System.out.println(sb);

    br.close();
  }
}
