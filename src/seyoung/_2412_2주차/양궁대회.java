package seyoung._2412_2주차;

public class 양궁대회 {
    public static void main(String[] args) {
        양궁대회 T = new 양궁대회();
        int n1 = 5;
        int[] info1 = {2,1,1,1,0,0,0,0,0,0,0};
        int[] solution1 = T.solution(n1, info1);
        System.out.print("solution 1 : ");
        for (int i : solution1) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int n2 = 1;
        int[] info2 = {1,0,0,0,0,0,0,0,0,0,0};
        int[] solution2 = T.solution(n2, info2);
        System.out.print("solution 2 : ");
        for (int i : solution2) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int n3 = 9;
        int[] info3 = {0,0,1,2,0,1,1,1,1,1,1};
        int[] solution3 = T.solution(n3, info3);
        System.out.print("solution 3 : ");
        for (int i : solution3) {
            System.out.print(i + ", ");
        }
        System.out.println();

        int n4 = 10;
        int[] info4 = {0,0,0,0,0,0,0,0,3,4,3};
        int[] solution4 = T.solution(n4, info4);
        System.out.print("solution 4 : ");
        for (int i : solution4) {
            System.out.print(i + ", ");
        }
        System.out.println();

    }

    static int[] answer = {-1}; // 라이언이 쏜 화살 (answer)
    static int[] maxDiffScore = new int[11]; //점수차가 최대일때 라이언이 쏜 화살
    static int max = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        backTracking(0, n, info);//라이언이 쏜 화살에 대한 조합

        //라이언이 어피치를 못 이기면
        if(max == -1) {
            answer = new int[1];
            answer[0]=-1;
        }
        return answer;
    }

    // 백트래킹
    public static void backTracking(int depth, int n, int[] info) {
        //화살 다 쓰면
        if(depth == n) {
            int diff = calculateScore(info); //점수차 구하기
            if(max <= diff) { //점수차 최대값 갱신
                max = diff;
                answer = maxDiffScore.clone(); // answer에 복사
            }
            return;
        }

        // i 과녁에 라이언이 화살을 더 많이 맞추면 반복문 종료한다.
        for(int i = 0; i < info.length && maxDiffScore[i] <= info[i]; i++) {
            maxDiffScore[i] += 1;
            backTracking(depth+1, n, info);
            maxDiffScore[i] -= 1;
        }
    }

    //점수차 계산하기
    public static int calculateScore(int[] info) {
        int apeach=0, lion=0;
        for(int i = 0; i < maxDiffScore.length; i++) {
            if(info[i] == 0 && maxDiffScore[i] == 0) continue;// i 과녁에 둘다 0개 맞췄을땐 무시.
            if(info[i] >= maxDiffScore[i]) apeach += ( 10 - i );
            else lion += ( 10 - i );
        }

        int diff = lion - apeach;
        if(diff <= 0) return -1;
        return diff;
    }

}
