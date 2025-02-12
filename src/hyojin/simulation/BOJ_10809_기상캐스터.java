package hyojin.simulation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10709

public class BOJ_10809_기상캐스터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        char[][] map = new char[H][W];
        int [][] answer = new int[H][W];
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < H; i++) {
            boolean flag = false;
            int count = 0;
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'c') {
                    flag = true;
                    answer[i][j] = 0;
                    count = 0;
                    continue;
                }
                if (flag) {
                    answer[i][j] = ++count;
                    continue;
                }
                answer[i][j] = -1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

    }
}
