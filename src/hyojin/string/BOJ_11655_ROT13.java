package hyojin.string;

// https://www.acmicpc.net/problem/11655

import java.util.Scanner;

/**
 * 핵심문장
 * 영어 알파벳을 13글자씩 밀어서 만든다.
 * 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다.
 *
 * 시간 복잡도
 * S <= 100이므로 자유로움?
 */

public class BOJ_11655_ROT13 {
    public static String rot13(String S) {
        StringBuilder result = new StringBuilder();

        for (char c : S.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                result.append((char) ('A' + (c - 'A' + 13) % 26));
            } else if ('a' <= c && c <= 'z') {
                result.append((char) ('a' + (c - 'a' + 13) % 26));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        System.out.println(rot13(S));
        sc.close();
    }
}
