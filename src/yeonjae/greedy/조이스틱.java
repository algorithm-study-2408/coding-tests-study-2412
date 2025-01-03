package yeonjae.greedy;

public class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int move = name.length() - 1; // 기본 최소 좌우이동 횟수 (좌, 우 커서)

        for (int i = 0; i < len; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            // 연속된 'A'가 끝나는 지점 찾기
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, (i * 2) + len - next);
            move = Math.min(move, (len - next) * 2 + i);
        }
        answer += move;

        return answer;
    }

    public static void main(String[] args) {
        조이스틱 solution = new 조이스틱();
        System.out.println(solution.solution("JEROEN")); // Expected: 56
        System.out.println(solution.solution("JAN"));    // Expected: 23
    }
}
