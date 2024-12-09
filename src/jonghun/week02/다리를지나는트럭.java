package jonghun.week02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <br> 1. 다리에 있는 트럭과 대기 트럭을 구분하여 관리<br> 2. 각 트럭의 무게와 위치(index)를 함께 관리<br> 3.
 * bridge_length만큼 트럭이 이동하면 다리를 건넘<br>
 * <br>
 * [pseudocode]<br> solution(bridge_length, weight, truck_weights):<br> bridge = new
 * ArrayList<Truck>()  // 다리 위 트럭들<br> ready = new Queue<Truck>()       // 대기 중인 트럭들<br> time = 0
 * // 경과 시간<br> bridge_weight = 0                // 현재 다리 위 무게 합<br>
 * <br>
 * // 대기 트럭 큐 초기화<br> for each weight in truck_weights:<br> ready에 new Truck(weight, 0) 추가<br>
 * <br>
 * while(true):<br> // 1. 새 트럭이 들어갈 수 있는지 체크<br> if (다리가 비었거나 다음 트럭 무게 추가 가능):<br> bridge_weight +=
 * ready.peek().weight<br> bridge에 ready.poll() 추가<br>
 * <br>
 * // 2. 다리 위 트럭들 한 칸씩 전진<br> for each truck in bridge:<br> truck.index++<br>
 * <br>
 * // 3. 다리를 다 건넌 트럭 처리<br> if (첫 트럭의 index가 bridge_length):<br> bridge_weight -=
 * bridge.get(0).weight<br> bridge에서 첫 트럭 제거<br>
 * <br>
 * // 4. 종료 조건 체크<br> if (ready가 비었음):<br> time += bridge_length<br> break<br>
 * <br>
 * time++<br>
 * <br>
 * return time + 1<br>
 * <br>
 * class Truck:<br> weight  // 트럭의 무게<br> index   // 다리 위에서의 위치<br>
 */

public class 다리를지나는트럭 {

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));
    }


    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int bweight = 0;
        ArrayList<Truck> bridge = new ArrayList<>();
        Queue<Truck> ready = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            ready.add(new Truck(truckWeight, 0));
        }
        while (true) {
            if (bridge.isEmpty() || bweight + ready.peek().weight <= weight) {
                bweight += ready.peek().weight;
                bridge.add(ready.poll());
            }
            for (Truck truck : bridge) {
                truck.index++;
            }
            if (bridge.get(0).index == bridge_length) {
                bweight = bweight - bridge.get(0).weight;
                bridge.remove(0);
            }

            if (ready.isEmpty()) {
                answer += bridge_length;
                break;
            }
            answer++;
        }
        return ++answer;
    }


    static class Truck {

        int weight;
        int index;

        public Truck(int weight, int index) {
            this.weight = weight;
            this.index = index;
        }
    }
}
