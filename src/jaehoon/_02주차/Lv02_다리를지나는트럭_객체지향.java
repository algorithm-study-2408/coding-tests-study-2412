package jaehoon._02주차;

import java.util.ArrayDeque;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class Lv02_다리를지나는트럭_객체지향 {

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
        // 1. 대기 큐 생성 & 시간 초기화
        int time = 0;
        ArrayDeque<Truck> waitQ = new ArrayDeque<>();
        for (int truckWeight : truck_weights) {
            Truck newTruck = new Truck(truckWeight);
            waitQ.addLast(newTruck);
        }

        // 2. 다리 생성
        Bridge bridge = new Bridge(bridge_length, weight);

        // 3. 대기 큐 순회
        while (!waitQ.isEmpty() || !bridge.isEmpty()) {
            time++; // 시간 증가
            bridge.moveAll(); // 다리 위 트럭 이동

            // 다리에 트럭이 올라갈 수 있으면 트럭 추가
            if (!waitQ.isEmpty() && bridge.isAvailable(waitQ.peek().getWeight())) {
                Truck newTruck = waitQ.pollFirst();
                bridge.add(newTruck);
            }
        }

        return time;
    }
}

class Bridge {
    private final int maxWeight;
    private int currentWeight;
    private final int bridgeLength;
    private final ArrayDeque<Truck> trucks = new ArrayDeque<>();

    public Bridge(int bridgeLength, int maxWeight) {
        this.bridgeLength = bridgeLength;
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void add(Truck truck) {
        trucks.addLast(truck);
        currentWeight += truck.getWeight();
    }

    public void moveAll() {
        // 다리 위 모든 트럭 이동
        for (Truck truck : trucks) {
            truck.move();
        }

        // 다리를 완전히 건넌 트럭 제거
        if (!trucks.isEmpty() && trucks.peekFirst().isCompleted(bridgeLength)) {
            Truck completedTruck = trucks.pollFirst();
            currentWeight -= completedTruck.getWeight();
        }
    }

    public boolean isAvailable(int truckWeight) {
        return (currentWeight + truckWeight) <= maxWeight;
    }

    public boolean isEmpty() {
        return trucks.isEmpty();
    }

    public int getBridgeLength() {
        return bridgeLength;
    }
}

class Truck {
    private final int weight;
    private int progressDistance;

    public Truck(int weight) {
        this.weight = weight;
        this.progressDistance = 0;
    }

    public void move() {
        progressDistance++;
    }

    public boolean isCompleted(int bridgeLength) {
        return progressDistance >= bridgeLength;
    }

    public int getWeight() {
        return weight;
    }
}