package yeonjae.queue;

import java.util.LinkedList;
import java.util.Queue;

public class 다리를지나는트럭 {

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        int time = 0; // 경과 시간
        int currentWeight = 0; // 다리 위의 총 무게
        Queue<Integer> bridge = new LinkedList<>(); // 다리 상태를 저장

        for (int truck : truckWeights) {
            while (true) {
                time++;

                // 1. 다리 끝에 도달한 트럭 제거
                if (bridge.size() == bridgeLength) {
                    currentWeight -= bridge.poll();
                }

                // 2. 트럭을 다리에 올릴 수 있는 경우
                if (currentWeight + truck <= weight) {
                    bridge.add(truck);
                    currentWeight += truck;
                    break; // 트럭을 다리에 올렸으면 다음 트럭으로 이동
                }

                // 3. 트럭을 올릴 수 없는 경우, 시간 경과를 처리하기 위해 빈 공간 추가
                bridge.add(0);
            }
        }

        // 마지막 트럭이 다리를 완전히 건너는 시간 추가
        return time + bridgeLength;
    }

    public static void main(String[] args) {
        다리를지나는트럭 bto = new 다리를지나는트럭();

        // 테스트 케이스
        System.out.println(bto.solution(2, 10, new int[]{7, 4, 5, 6})); // 8
        System.out.println(bto.solution(100, 100, new int[]{10})); // 101
        System.out.println(bto.solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10})); // 110
    }
}
