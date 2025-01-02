package jaehoon._05주차;

import java.util.Arrays;
import java.util.Comparator;

// https://school.programmers.co.kr/learn/courses/30/lessons/181188
public class Lv02_요격시스템 {

  public static void main(String[] args) {
    int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
    System.out.println("result = " + solution(targets));
  }

  public static int solution(int[][] targets) {
    // 1. end를 기준으로 오름차순 정렬
    Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));

    // 2. 요격 미사일 개수, 요격 지점 초기화
    int end = -1;
    int answer = 0;

    // 3. 정렬된 폭격 미사일 하나씩 ㅅ접근하기
    for(int[] tar : targets){
      // 다음 요격미사일의 시작 지점이 요격의 end지점보다 크거나 같으면 (겹치지 않으면)
      if(tar[0] >= end){
        end = tar[1]; // 해당 요격미사일의 끝지점으로 요격지점 변경
        answer++; // 시점이 요격 시스템의 상한보다 오른쪽에 있기 때문에 새로운 요격 시스템 추가
      }
    }
    return answer;
  }
}
