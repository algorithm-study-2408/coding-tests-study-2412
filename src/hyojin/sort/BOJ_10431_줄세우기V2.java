package hyojin.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class BOJ_10431_줄세우기V2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int caseNumber = Integer.parseInt(st.nextToken());

            int[] arr = new int[20]; // 20명 고정

            for (int i = 0; i < 20; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer> line = new ArrayList<>();
            int count = 0;
            for (int source : arr) {
                int index = 0;

                // 현재 학생이 들어갈 위치 찾기
                while (index < line.size() && line.get(index) < source) {
                    index++;
                }

                // 뒤로 한 칸씩 밀림
                count += line.size() - index;

                // 현재 학생을 위치에 삽입
                line.add(index, source);
            }
            System.out.println(caseNumber + " " + count);
        }

        br.close();
    }
}