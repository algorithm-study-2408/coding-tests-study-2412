package jaehoon._11주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11655
public class BOJ_11655_ROT13 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();

    StringBuilder st = new StringBuilder();
    for (char ch : str.toCharArray()) {
      if (Character.isLetter(ch)) {
        st.append(ROT13(ch));
        continue;
      }
      st.append(ch);
    }
    System.out.println(st);
    br.close();
  }

  static char ROT13(char ch) {
    if (Character.isUpperCase(ch)) {
      return (char) ((ch - 'A' + 13) % 26 + 'A');
    } else {
      return (char) ((ch - 'a' + 13) % 26 + 'a');
    }
  }
}
