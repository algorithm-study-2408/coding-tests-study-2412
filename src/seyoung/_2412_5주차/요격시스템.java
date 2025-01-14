package seyoung._2412_5주차;

import java.util.Arrays;

public class 요격시스템 {

    public int solution(int[][] targets) {
        int answer = 0;

        // e 좌표 오름차순
        Arrays.sort(targets,(o1,o2)-> {
            return o1[1]-o2[1];
        });

        int x = 0; //B국가 요격미사일 좌표

        for(int i = 0;i < targets.length; i++){
            //현 요격미사일 좌표가 타겟 미사일 안쪽이 아니면
            // 1. x 좌표를 타겟 미사일 끝점으로 지정하고
            // 2. 요격미사일 갯수 추가
            if(x <= targets[i][0]){
                x = targets[i][1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        요격시스템 T = new 요격시스템();
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        System.out.println(T.solution(targets));
    }
}
