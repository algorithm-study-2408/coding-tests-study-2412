package seyoung._2501_6주차;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 도서관 {

    public static void main(String[] args) {
        도서관 T = new 도서관();
        Scanner sc = new Scanner(System.in);
        String[] bookNum = sc.nextLine().split(" ");
        String[] walks = sc.nextLine().split(" ");
        int totBooks = Integer.parseInt(bookNum[0]);
        int sejoonsBooks = Integer.parseInt(bookNum[1]);
        int[] positions = new int[totBooks];
        for (int i = 0; i < totBooks; i++) {
            positions[i] = Integer.parseInt(walks[i]);
        }
        System.out.println(T.solution(totBooks, sejoonsBooks, positions));
    }

    public int solution(int totBooks, int sejoonsBooks, int[] positions) {
        int answer = 0;
        int endpoint = 0;

        // 내림차순 우선순위 큐
        PriorityQueue<Integer> plus=new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minus=new PriorityQueue<>(Collections.reverseOrder());

        //각 위치의 음수와 양수를 각각의 우선순위 큐에 삽입
        for (int position : positions) {
            if (position >= 0) {
                plus.add(position);
            } else {
                //절대값
                minus.add(Math.abs(position));
            }
        }

        // 양수가 없으면 endpoint는 음수 중 가장 먼 거리
        if (plus.isEmpty()) {
            endpoint = minus.peek();
        // 음수가 없으면 endpoint는 양수 중 가장 먼 거리
        } else if (minus.isEmpty()) {
            endpoint = plus.peek();
        // 둘 다 존재하면 둘 중 가장 먼거리
        } else {
            endpoint = Math.max(minus.peek(), plus.peek());
        }

        //갔다가 오므로 *2
        while(!plus.isEmpty()) {
            answer += plus.peek()*2;
            for (int i = 0; i < sejoonsBooks; i++) {
                plus.poll();
            }
        }

        while(!minus.isEmpty()) {
            answer += minus.peek()*2;
            for (int i = 0; i < sejoonsBooks; i++) {
                minus.poll();
            }
        }

        answer -= endpoint;

        return answer;
    }
}
