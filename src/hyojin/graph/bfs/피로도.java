package hyojin.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 피로도 {

    static class State {
        int fatigue;
        int depth;
        boolean[] visited;

        State(int fatigue, int depth, boolean[] visited) {
            this.fatigue = fatigue;
            this.depth = depth;
            this.visited = visited.clone();
        }
    }

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));
    }

    public static int solution(int k, int[][] dungeons) {
        int maxCount = 0;
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(k, 0, new boolean[dungeons.length])); // 초기 상태 추가

        while (!queue.isEmpty()) {
            State current = queue.poll();
            maxCount = Math.max(maxCount, current.depth);

            // 던전 탐험 시도
            for (int i = 0; i < dungeons.length; i++) {
                if (!current.visited[i] && current.fatigue >= dungeons[i][0]) {
                    current.visited[i] = true;
                    queue.add(new State(
                            current.fatigue - dungeons[i][1],
                            current.depth + 1,
                            current.visited
                    ));
                    current.visited[i] = false;
                }
            }
        }

        return maxCount;
    }
}