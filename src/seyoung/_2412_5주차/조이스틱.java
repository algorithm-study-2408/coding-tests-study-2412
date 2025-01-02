package seyoung._2412_5주차;

public class 조이스틱 {

    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        int minMove = n - 1;
        int moves = 0;

        for (int i = 0; i < n; i++) {
            moves += calculation(name.charAt(i));

            // 다음이 A인 연속된 갯수 찾기
            int next = i + 1;
            while (next < n && name.charAt(next)=='A') {
                next++;
            }

            if (next - i > 1) {
                //오른쪽으로 진행 중 왼쪽으로 이동
                int toLeft = i*2 + n-next;
                //왼쪽으로 진행 중 오른쪽으로 이동
                int toRight = (n-next)*2+i;

                minMove = Math.min(minMove, toLeft);
                minMove = Math.min(minMove, toRight);
            }
        }
        return moves+minMove;
    }

    public static int calculation(char c) {
        return Math.min(c-'A', 'Z'-c+1);
    }

    public static void main(String[] args) {
        조이스틱 T = new 조이스틱();
        String name = "JEROEN";
        System.out.println(T.solution(name));
    }
}
