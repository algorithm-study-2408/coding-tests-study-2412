package seyoung;


// https://school.programmers.co.kr/learn/courses/30/lessons/43162

public class Network {

    public static void main(String[] args) {
        Network T = new Network();
        int n1 = 3;
        int[][] computers1 = {{1, 1, 0},{1, 1, 0}, {0, 0, 1}};
        System.out.println(T.solution(n1, computers1));

        int n2 = 3;
        int[][] computers2 = {{1, 1, 0},{1, 1, 1},{0, 1, 1}};
        System.out.println(T.solution(n2, computers2));
    }


    public int solution(int n, int[][] computers) {
        int cnt = 0;
        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if (i != j && computers[i][j] == 1) {
                    cnt++;
                }
            }
        }
        cnt = cnt/2;
        return n - cnt;
    }
}
