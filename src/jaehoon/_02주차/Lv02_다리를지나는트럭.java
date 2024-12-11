package jaehoon._02주차;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class Lv02_다리를지나는트럭 {

    public static void main(String[] args) {
        int[] truck_weights1 = {7, 4, 5, 6};
        int weight1 = 10;
        int bridge_length1 = 2;
        int result1 = solution(bridge_length1, weight1, truck_weights1);
        System.out.println("result1 = " + result1);

        int[] truck_weights2 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int weight2 = 100;
        int bridge_length2 = 100;
        int result2 = solution(bridge_length2, weight2, truck_weights2);
        System.out.println("result2 = " + result2);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        ArrayDeque<Integer> bridge = new ArrayDeque<>(); // 다리에 있는 트럭을 순서대로 저장할 Deque 자료구조 사용
        int timer = 0; // 다리를 지나는 시간
        int currentWeight = 0; // 다리 위의 트럭들의 총 무게
        int index = 0; // 대기 중인 트럭의 인덱스

        // 1. 다리에 올라갈 수 있는 트럭의 수 만큼 0으로 초기화 (다리에 올라갈수 있는 트럭의 수 제한)
        for (int i = 0; i < bridge_length; i++) {
            bridge.addLast(0);
        }

        // 2. 트럭들이 모두 다리에 올라갈때까지 반복
        while (index < truck_weights.length) {
            // 2-1. 현재 다리에 올라간 트럭을 순서대로 빼주어 도착한 트럭의 무게를 업데이트 "빈칸(0)은 무시됨"
            currentWeight -= bridge.pollFirst();

            // 2-2. 새로운 트럭을 다리에 올릴 수 있는지 확인 (다리위의 트럭의 무게에 대기중인 트럭의 무게를 더해서 제한 무게확인)
            if (currentWeight + truck_weights[index] <= weight) {
                bridge.addLast(truck_weights[index]);
                currentWeight += truck_weights[index];
                index++; // 다음 트럭으로 이동
            }
            // 2-3. 새로운 트럭을 다리에 올릴 수 없는 경우 빈칸(0)을 올림
            else {
                bridge.addLast(0);
            }
            timer++; // 시간증가
        }
        // 3. (마지막 트럭이 올라간시간 + 다리를 건널때까지 남은시간) 반환
        return timer + bridge_length;
    }
}