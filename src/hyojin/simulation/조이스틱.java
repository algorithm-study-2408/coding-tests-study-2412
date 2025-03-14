package hyojin.simulation;

// https://school.programmers.co.kr/learn/courses/30/lessons/42860

public class 조이스틱 {
    public static void main(String[] args) {
//        String str = "JEROEN";
        String str = "BBBBAAAAAAAB";
        int solution = solution(str);
        System.out.println(solution);
    }

    public static int solution(String name) {

        int answer = 0;
        int length = name.length();

        int index;
        int move = length - 1;

        for(int i = 0; i < name.length(); i++){
            // 문자 변경 로직
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            // 이동 로직
            index = i + 1;

            while(index < length && name.charAt(index) == 'A'){
                index++;
            }

            // 현재 위치에서 앞으로 이동한 후, 뒤로 돌아가는 경우를 고려함
            /**
             * 샘플 데이터: "BBBBAAAAAAAB"
             * 길이: length = 12
             * i = 3 (현재 'B' 위치).
             * 연속된 'A'가 시작되는 index = 4부터, 'B'가 다시 나타나는 위치는 index = 11.
             * 계산
             * i * 2 + length - index == 3 * 2 + 12 - 11 = 6 + 1 = 7
             */
            move = Math.min(move, i * 2 + length - index);

            // 뒤로 돌아가는 경로를 먼저 탐색한 후, 다시 앞으로 이동하는 경우를 고려.
            move = Math.min(move, (length - index) * 2 + i);
        }
        return answer + move;
    }
}
