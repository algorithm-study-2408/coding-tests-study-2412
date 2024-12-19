package seyoung._2412_2주차;

import java.util.LinkedList;
import java.util.Queue;


public class 다리를_지나는_트럭 {

    public static void main(String[] args) {
        다리를_지나는_트럭 T = new 다리를_지나는_트럭();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weight = {7, 4, 5, 6};
        System.out.println(T.solution(bridge_length, weight, truck_weight));
    }


    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int bridgeWeight = 0; //현재 다리 위 트럭 총 무게
        int bridgeTruckCount = 0; //현재 다리 위에 있는 트럭 수
        int totCount = 0; //다리를 완전히 지난 트럭 수

        //대기열
        Queue<Integer> waitingQueue = new LinkedList<>();
        //다리 지나는 중
        Queue<Integer> progressQueue = new LinkedList<>();
        //진입 시간 저장 큐
        Queue<Integer> timeQueue = new LinkedList<>();

        //대기열에 모든 트럭을 대기시킴
        for (int truck : truck_weights) {
            waitingQueue.add(truck);
        }

        //모든 트럭들이 다리를 빠져나갈때까지 반복
        while (totCount != truck_weights.length) {
            answer++;

            if (timeQueue.peek() != null && timeQueue.peek() == bridge_length) {
                timeQueue.poll();
                bridgeWeight -= progressQueue.poll();
                bridgeTruckCount -= 1;
                totCount++;
            }

            if (waitingQueue.peek() != null) {
                int truck = waitingQueue.peek();
                if (truck + bridgeWeight <= weight && bridgeTruckCount <= bridge_length) {
                    bridgeWeight += truck;
                    bridgeTruckCount++;
                    progressQueue.add(waitingQueue.poll());
                    timeQueue.add(0);
                }
            }

            for (int i = 0; i < timeQueue.size(); i++) {
                timeQueue.add(timeQueue.poll() + 1);
            }
        }

        return answer;
    }

}
