package hyojin.string;

// https://school.programmers.co.kr/learn/courses/30/lessons/60058

public class 괄호변환 {

    public static void main(String[] args) {
//        String p = "(()())()";
        String p = "()))((()";
        String result = solution(p);
        System.out.println("result = " + result);
    }

    public static String solution(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (p.isEmpty()) {
            return p;
        }

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
        // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며,
        // v는 빈 문자열이 될 수 있습니다.
        String[] split = splitBalanced(p); // 균형잡힌 문자열 u, v로 분리
        String u = split[0];
        String v = split[1];

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
        //  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
        if (isValid(u)) {
            return u + solution(v);
        }
        //4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
        //  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
        //  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
        //  4-3. ')'를 다시 붙입니다.
        //  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
        //  4-5. 생성된 문자열을 반환합니다.
        else {

            return "(" + solution(v) + ")" + reverse(u);
        }
    }

    // 균형잡힌 문자열 u와 v로 분리
    private static String[] splitBalanced(String p) {
        int balance = 0;
        for (int i = 0; i < p.length(); i++) {
            balance += (p.charAt(i) == '(') ? 1 : -1;
            if (balance == 0) {
                return new String[] { p.substring(0, i + 1), p.substring(i + 1) };
            }
        }
        return new String[] { p, "" }; // 기본 반환
    }

    private static boolean isValid(String u) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            // 닫는 괄호가 더 많아지면 올바른 괄호 문자열이 아님
            if (right > left) {
                return false;
            }
        }
        return true;
    }

    private static String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        // 첫 번째와 마지막 문자 제거
        for (int i = 1; i < u.length() - 1; i++) {
            // 나머지 문자열의 괄호 방향 뒤집기
            if (u.charAt(i) == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}
