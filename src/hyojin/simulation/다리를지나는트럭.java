package hyojin.simulation;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583

public class 다리를지나는트럭 {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};

        int result = solution(bridge_length, weight, truck_weights);
        System.out.println("result = " + result); // Expected output: 8
    }

    private static int solution(int bridge_length, int weight, int[] truck_weights) {
        Bridge bridge = new Bridge(bridge_length, weight); // 다리 객체 생성
        Queue<Truck> waitingQueue = new LinkedList<>();   // 대기 트럭 큐 생성

        // 대기 큐에 트럭 추가
        for (int truckWeight : truck_weights) {
            waitingQueue.add(new Truck(truckWeight));
        }

        int time = 0;

        // 대기 트럭이 남아있거나 다리 위에 트럭이 있는 동안 반복
        while (!waitingQueue.isEmpty() || !bridge.isEmpty()) {
            time++;

            // 다리 위 트럭 이동
            bridge.moveTrucks();

            // 대기 중인 트럭이 다리에 올라갈 수 있는지 확인
            if (!waitingQueue.isEmpty() && bridge.canEnter(waitingQueue.peek())) {
                Truck truck = waitingQueue.poll(); // 대기 트럭에서 꺼내기
                bridge.enter(truck);              // 다리에 트럭 추가
            }
        }

        return time;
    }

    // 트럭 클래스
    static class Truck {
        private final int weight; // 트럭의 무게
        private int distance;     // 다리 위에서 이동한 거리

        public Truck(int weight) {
            this.weight = weight;
            this.distance = 0;
        }

        public int getWeight() {
            return weight;
        }

        public int getDistance() {
            return distance;
        }

        public void move() {
            distance++;
        }

    }

    // 다리 클래스
    static class Bridge {
        private final int bridge_length;    // 다리 길이
        private final int bridge_weight;    // 다리 최대 하중
        private final Queue<Truck> trucksOnBridge; // 다리 위 트럭
        private int currentWeight;      // 다리 위 트럭들의 총 무게

        public Bridge(int bridge_length, int bridge_weight) {
            this.bridge_length = bridge_length;
            this.bridge_weight = bridge_weight;
            this.trucksOnBridge = new LinkedList<>();
            this.currentWeight = 0;
        }

        // 트럭 이동
        public void moveTrucks() {
            if (!trucksOnBridge.isEmpty()) {
                Truck frontTruck = trucksOnBridge.peek();

                // 트럭 이동
                for (Truck truck : trucksOnBridge) {
                    truck.move();
                }

                // 다리를 건넌 트럭 제거
                if (frontTruck.getDistance() > bridge_length) {
                    trucksOnBridge.poll();
                    currentWeight -= frontTruck.getWeight();
                }
            }
        }

        // 트럭이 다리에 올라갈 수 있는지 확인
        public boolean canEnter(Truck truck) {
            return currentWeight + truck.getWeight() <= bridge_weight && trucksOnBridge.size() < bridge_length;
        }

        // 트럭을 다리에 올리기
        public void enter(Truck truck) {
            truck.move();
            trucksOnBridge.add(truck);
            currentWeight += truck.getWeight();
        }

        // 다리가 비어 있는지 확인
        public boolean isEmpty() {
            return trucksOnBridge.isEmpty();
        }
    }
}