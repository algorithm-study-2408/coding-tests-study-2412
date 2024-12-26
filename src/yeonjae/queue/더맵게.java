package yeonjae.queue;

import java.util.PriorityQueue;

public class 더맵게 {
    public static void main(String[] args) {
        더맵게 solution = new 더맵게();
        System.out.println(solution.solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i=0; i<scoville.length; i++) {
            pq.add(scoville[i]);
        }

        int answer = 0;
        while (pq.peek()<K) {

            if(pq.size()<2) {
                return -1;
            }

            answer++;
            pq.add(pq.poll() + (pq.poll()*2));

        }

        return answer;
    }
}

