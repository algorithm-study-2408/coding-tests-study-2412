package hyojin.bfs;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165

public class 타겟넘버 {
    static class TreeNode {
        int sum;
        int index;

        public TreeNode(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

    public int solution(int[] numbers, int target) {
        // 트리 구조를 사용하여, BFS 최종 값들은 구해서 target값과 같을 경우 카운트하기
        int count = 0;

        // 트리 구조를 사용한 BFS로 접근
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(new TreeNode(0, 0));

        // BFS 순회
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.index == numbers.length) {
                // 모든 숫자를 다 사용한 경우, 타겟과 일치하는지 확인
                if (current.sum == target) {
                    count++;
                }
            } else {
                // 현재 인덱스의 숫자를 더하는 경우
                queue.add(new TreeNode(current.sum + numbers[current.index], current.index + 1));
                // 현재 인덱스의 숫자를 빼는 경우
                queue.add(new TreeNode(current.sum - numbers[current.index], current.index + 1));
            }
        }

        return count;
    }
}
