package hyojin.simulation;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/181188

public class 요격시스템 {

    public static int solution(int[][] targets) {
        // 폭격 미사일의 범위를 끝점을 기준으로 오름차순 정렬
        Arrays.sort(targets, Comparator.comparingInt(target -> target[1]));

        int interceptors = 0; // 요격 미사일 개수
        double lastIntercept = Double.NEGATIVE_INFINITY; // 마지막 요격 미사일의 위치

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            // 현재 폭격 미사일이 이전 요격 범위에 포함되지 않으면 새 요격 미사일 발사
            if (lastIntercept <= start) {
                interceptors++;
                lastIntercept = end;
            }
        }

        return interceptors;
    }

    public static void main(String[] args) {

        // 테스트 케이스
        int[][] targets = {
                {4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}
        };

        System.out.println(solution(targets));
    }
}
