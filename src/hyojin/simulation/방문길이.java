package hyojin.simulation;

import java.util.HashSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/49994?language=java

public class 방문길이 {

    public static int solution(String dirs) {
        int answer = 0;

        HashSet<String> visited = new HashSet<>();

        // 시작 위치
        int currentX = 0;
        int currentY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            int nextX = currentX;
            int nextY = currentY;
            String moving = "";

            if (dirs.charAt(i) == 'U') {
                nextY++; // 위로 한칸
                moving += currentX + "" + currentY + "->" + nextX +  nextY;
            } else if (dirs.charAt(i) == 'D') {
                nextY--; // 밑으로 한칸
                moving += nextX + "" + nextY + "->" + currentX + currentY ;
            } else if (dirs.charAt(i) == 'R') {
                nextX++; // 오른쪽으로 한칸
                moving += currentX + "" + currentY + "->" + nextX + nextY;
            } else {
                nextX--; // 왼쪽으로 한칸
                moving += nextX + "" + nextY + "->" + currentX + currentY;
            }

            // 좌표평면의 경계를 넘어가면 추가하지 않고 다음 명령으로 넘어간다.
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }
            visited.add(moving);

            currentX = nextX;
            currentY = nextY;
        }

        answer = visited.size();

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("ULURRDLLU")); // 7
        System.out.println(solution("LULLLLLLU")); // 7
    }
}
