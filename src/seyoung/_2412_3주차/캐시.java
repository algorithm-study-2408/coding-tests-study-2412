package seyoung._2412_3주차;

import java.util.LinkedList;

public class 캐시 {

    int cacheHit = 1;
    int cacheMiss = 5;

    public int solution(int cacheSize, String[] cities) {
        // 실행시간
        int answer = 0;
        // LRU 캐시 링크드리스트
        LinkedList<String> LRU = new LinkedList<>();

        for (String city : cities) {
            String upperCity = city.toUpperCase();
            // LRU 안에 해당 도시가 있는지 확인
            if (!LRU.isEmpty()) {
                // 도시가 있으면 => hit : 해당 도시 위치를 삭제
                if (LRU.contains(upperCity)) {
                    answer+=cacheHit;
                    if (LRU.size() == cacheSize) {
                        LRU.remove(upperCity);
                    }
                // 도시가 없으면 => miss : 첫번째 도시를 지움
                } else {
                    answer+=cacheMiss;
                    if (LRU.size() == cacheSize) {
                        LRU.poll();
                    }
                }
            // LRU가 비어있으면 항상 miss
            } else {
                answer+=cacheMiss;
            }

            //캐시 사이즈가 0보다 크면 해당 도시를 넣어줌
            if (cacheSize > 0) {
                LRU.offer(upperCity);
            }
        }

        return answer;
    }



    public static void main(String[] args) {
        캐시 T = new 캐시();
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int solution = T.solution(cacheSize, cities);
        System.out.println(solution);
    }

}
