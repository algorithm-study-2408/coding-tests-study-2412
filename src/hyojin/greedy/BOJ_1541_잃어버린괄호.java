package hyojin.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer string = new StringTokenizer(br.readLine());
        String s = string.nextToken();
        String[] str = s.split("-");

        int answer = 0;
        for (int i = 0; i < str.length; i++) {
            int temp = strSum(str[i]);
            if (i == 0) {
                answer += temp;
            } else {
                answer -= temp;
            }
        }
        System.out.println("answer = " + answer);
        br.close();
    }

    // String.split(String regex) 메서드는 정규표현식(regex)을 사용하여 문자열을 분리합니다.
    // "+"는 정규표현식에서 특수문자로, "하나 이상의 반복"을 의미합니다.
    // 해결 방법으로 이스케이프 문자(\)를 사용합니다.
    private static int strSum(String s) {
        int sum = 0;
        String[] split = s.split("\\+");
        for (int i = 0; i < split.length; i++) {
            sum += Integer.parseInt(split[i]);
        }
        return sum;
    }

}
