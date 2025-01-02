package yeonjae.simulation;

import java.util.*;

class 과제진행하기 {
    public String[] solution(String[][] plans) {
        // plans를 시작 시간 기준으로 정렬
        Arrays.sort(plans, (a, b) -> timeToMinutes(a[1]) - timeToMinutes(b[1]));

        // 결과 리스트와 스택 초기화
        List<String> result = new ArrayList<>();
        Deque<Task> stack = new ArrayDeque<>();
        int currentTime = 0;

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int startTime = timeToMinutes(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);

            // 현재 과제 시작 전까지 진행 중인 과제를 처리
            while (!stack.isEmpty() && currentTime < startTime) {
                Task prevTask = stack.pop();
                if (currentTime + prevTask.remainingTime <= startTime) {
                    // 과제를 끝낼 수 있는 경우
                    currentTime += prevTask.remainingTime;
                    result.add(prevTask.name);
                } else {
                    // 끝내지 못하면 다시 스택에 저장
                    prevTask.remainingTime -= (startTime - currentTime);
                    stack.push(prevTask);
                    currentTime = startTime;
                    break;
                }
            }

            // 새로운 과제 시작
            currentTime = startTime;
            stack.push(new Task(name, playtime));
        }

        // 남은 과제 처리
        while (!stack.isEmpty()) {
            Task task = stack.pop();
            result.add(task.name);
        }

        // 결과를 배열로 변환하여 반환
        return result.toArray(new String[0]);
    }

    // 시간을 분으로 변환하는 메서드 (클래스 레벨에서 정의)
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // Task 클래스 정의
    static class Task {
        String name;
        int remainingTime;

        Task(String name, int remainingTime) {
            this.name = name;
            this.remainingTime = remainingTime;
        }
    }

    // 테스트 메서드
    public static void main(String[] args) {
        과제진행하기 solution = new 과제진행하기();

        String[][] plans1 = {
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"}
        };
        System.out.println(Arrays.toString(solution.solution(plans1)));
        // ["korean", "english", "math"]

        String[][] plans2 = {
                {"science", "12:40", "50"},
                {"music", "12:20", "40"},
                {"history", "14:00", "30"},
                {"computer", "12:30", "100"}
        };
        System.out.println(Arrays.toString(solution.solution(plans2)));
        // ["science", "history", "computer", "music"]

        String[][] plans3 = {
                {"aaa", "12:00", "20"},
                {"bbb", "12:10", "30"},
                {"ccc", "12:40", "10"}
        };
        System.out.println(Arrays.toString(solution.solution(plans3)));
        // ["bbb", "ccc", "aaa"]
    }
}
