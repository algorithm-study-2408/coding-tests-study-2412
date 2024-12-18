package jaehoon._03주차;

// https://school.programmers.co.kr/learn/courses/30/lessons/17680

import java.util.ArrayDeque;

/**
 * LRU (Least Recently Used) <p>
 * - 가장 오랫동안 참조되지 않은 페이지를 교체하는 기법
 */
public class Lv02_캐시 {

  public static void main(String[] args) {
    int cacheSize1 = 2;
    String[] cities1 = {"Jeju", "Pangyo", "NewYork", "newyork"};
    System.out.println("result1 = " + solution(cacheSize1, cities1));

    int cacheSize2 = 0;
    String[] cities2 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
    System.out.println("result2 = " + solution(cacheSize2, cities2));
  }

  static int CACHE_HIT_TIME = 1;

  static int CACHE_MISS_TIME = 5;

  public static int solution(int cacheSize, String[] cities) {
    int totalTime = 0;
    ArrayDeque<String> cache = new ArrayDeque<>(cacheSize);

    for (String city : cities) {
      city = city.toLowerCase();
      if (cache.contains(city)) {
        totalTime += CACHE_HIT_TIME;
        cache.remove(city);
        cache.addLast(city);
      } else {
        totalTime += CACHE_MISS_TIME;
        cache.addLast(city);
        if (cache.size() > cacheSize) {
          cache.removeFirst();
        }
      }
    }
    return totalTime;
  }
}
