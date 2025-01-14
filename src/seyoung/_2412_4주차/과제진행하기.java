package seyoung._2412_4주차;

import java.util.*;

public class 과제진행하기 {

    public static void main(String[] args) {
        과제진행하기 T = new 과제진행하기();
        String[][] plans1 = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[] solution1 = T.solution(plans1);
        for (String s : solution1) {
            System.out.print(s+" ");
        }
        System.out.println();

        String[][] plans2 = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        String[] solution2 = T.solution(plans2);
        for (String s : solution2) {
            System.out.print(s+" ");
        }
        System.out.println();

        String[][] plans3 = {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
        String[] solution3 = T.solution(plans3);
        for (String s : solution3) {
            System.out.print(s+" ");
        }
    }


    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];;

        //과목명을 담을 hashmap
        HashMap<Integer, String> subjectMap = new HashMap<>();
        //task를 저장할 데크
        Deque<Integer[]> taskQ = new ArrayDeque<>();

        //시작시간 순으로 정렬
        Arrays.sort(plans, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });

        // 시간 형태를 분으로 재계산하여 배열에 담기
        int[][] rePlans = new int[plans.length][3];
        for (int i = 0; i < plans.length; i++) {
            rePlans[i][0] = i; //인덱스
            rePlans[i][1] = toMinute(plans[i][1]); //시작시간
            rePlans[i][2] = Integer.parseInt(plans[i][2]); //걸리는 시간
            subjectMap.put(i, plans[i][0]);
        }

        int time = 0;
        int index = 0;
        for (int[] now : rePlans) {
            int id = now[0];
            int start = now[1];
            int duration = now[2];

            while (!taskQ.isEmpty() && time + taskQ.peekFirst()[1] <= start) {
                time += taskQ.peekFirst()[1];
                answer[index++] = subjectMap.get(taskQ.pollFirst()[0]);
            }
            if (taskQ.isEmpty()) {
                taskQ.addLast(new Integer[] { id, duration });
                time = start;
            } else if (time + taskQ.peekFirst()[1] > start) {
                taskQ.peekFirst()[1] -= (start - time);
                taskQ.addFirst(new Integer[] { id, duration });
                time = start;
            }
        }

        //남은 과제 처리
        while (!taskQ.isEmpty()) {
            time += taskQ.peekFirst()[1];
            answer[index++] = subjectMap.get(taskQ.pollFirst()[0]);
        }
        return answer;
    }
    
    int toMinute(String hour) {
        String[] hourArr = hour.split(":");
        return Integer.parseInt(hourArr[0]) * 60 + Integer.parseInt(hourArr[1]);
    }

}
