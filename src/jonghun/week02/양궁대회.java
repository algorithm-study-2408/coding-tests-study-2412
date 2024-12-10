package jonghun.week02;

/**
 * 백트래킹을 통한 조합으로 가지치기된 모든 경우의 수를 찾는다<br/> 종료조건에 대해 주의깊게 봐야 함<br/> 라이언이 n개의 화살을 꽂을때 차이값구함<br/> 차이값이
 * 최대일때 최대값 갱신. answer에 탐색한 배열 복사.<br/> ++ 가장 큰 점수차로 이기는 방법이 한개가 아니라면, 가장 낮은 점수를 더 많이 맞힌 경우를 선택해야
 * 한다. <br/>라이언이 전 우승자<br/>
 * [pseudocode]
 ** <br/>
 * * backTrack(arrow, n, info):<br/>
 * *     // 1. 종료 조건: n발의 화살을 모두 사용한 경우<br/>
 * *     if (arrow == n):<br/>
 * *         diff = 점수차계산(info)<br/>
 * *         if (max <= diff):<br/>
 * *             max = diff<br/>
 * *             lion = 현재화살배치복사<br/>
 * *         return<br/>
 * * <br/>
 * *     // 2. 각 과녁에 대해 화살 배치 시도<br/>
 * *     for i = 0 to 10:<br/>
 * *         if (현재과녁의라이언화살 > 어피치화살):<br/>
 * *             break  // 더 많이 쏜 과녁은 고려하지 않음<br/>
 * *         <br/>
 * *         현재과녁에화살추가<br/>
 * *         backTrack(arrow + 1, n, info)<br/>
 * *         현재과녁에서화살제거<br/>
 * <br/>
 * 점수차계산(info):<br/>
 *     어피치점수 = 0<br/>
 *     라이언점수 = 0<br/>
 *     <br/>
 *     for i = 0 to 10:<br/>
 *         if (둘다과녁에못맞춤): continue<br/>
 *         if (어피치가더많이맞춤):<br/>
 *             어피치점수 += (10-i)<br/>
 *         else:<br/>
 *             라이언점수 += (10-i)<br/>
 *     <br/>
 *     점수차 = 라이언점수 - 어피치점수<br/>
 *     if (점수차 <= 0): return -1<br/>
 *     return 점수차<br/>
 * <br/>
 */

public class 양궁대회 {

    static int max = Integer.MIN_VALUE;// 점수차이 최대값
    static int[] lion = {-1};// 라이언의 최종 정답배열
    static int[] res = new int[11];// 점수차가 최대일때 라이언이 쏜 현제ㅐ 화살배열

    public static int[] solution(int n, int[] info) {
        backTrack(0,n,info);//라이언이 쏜 화살에 대한 조합

        if(max==-1) {//라이언이 어피치를 못 이길떄
            lion = new int[1];
            lion[0]=-1;
        }
        return lion;
    }


    public static void backTrack(int arrow, int n, int[] info) {
        // 1. 화살 다 꽂았을때(종료조건)
        if (arrow == n) {
            int diff = score(info);//점수차 구하기
            if (max <= diff) {//점수차 최대값 갱신
                max = diff;
                lion = res.clone();// 복사
            }
            return;
        }

        //res[i]<=info[i] -> i과녁에 라이언이 화살을 더 많이 맞추면 반복문 종료한다.
        for (int i = 0; i < info.length && res[i] <= info[i]; i++) {
            res[i] += 1; // 현재 과녁에 화살한발 쏨
            backTrack(arrow + 1, n, info);
            res[i] -= 1; // 백트래킹 : 추가했던 화살 제거
        }
    }

    //점수차 구하기
    public static int score(int[] info) {
        int apeach = 0, lion = 0;
        for (int i = 0; i < res.length; i++) {
            if (info[i] == 0 && res[i] == 0) {
                continue;//i원소에 둘다 0개 맞췄을땐 무시.
            }
            if (info[i] >= res[i]) {
                apeach += (10 - i);
            } else {
                lion += (10 - i);
            }
        }

        int diff = lion - apeach;
        if (diff <= 0) {
            return -1;
        }
        return diff;
    }

}
