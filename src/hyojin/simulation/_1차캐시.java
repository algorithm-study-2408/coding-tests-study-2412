package hyojin.simulation;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/17680

public class _1차캐시 {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA",
                "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int result = solution(cacheSize, cities);
        System.out.println(result);
    }

    private static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return 5 * cities.length;

        Queue<String> cache = new LinkedList<>();
        int totalTime = 0;

        for (String city : cities) {
            String lowerCity = city.toLowerCase();
            // 캐시 히트: 캐시에 이미 존재하면 제거 후 다시 넣음 (순서 갱신)
            if (cache.contains(lowerCity)) {
                cache.remove(lowerCity); // 기존 위치 제거
                totalTime += 1; // Cache Hit
            } else {
                // 캐시 미스: 캐시가 가득 차면 가장 오래된 값 제거
                if (cache.size() >= cacheSize) {
                    cache.poll(); // 가장 오래된 값 제거
                }
                totalTime += 5; // Cache Miss
            }

            cache.offer(lowerCity); // 최신 값 추가
        }

        return totalTime;
    }
}
