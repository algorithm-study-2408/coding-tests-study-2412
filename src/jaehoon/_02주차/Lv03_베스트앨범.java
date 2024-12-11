package jaehoon._02주차;

import java.util.*;
import java.util.stream.Collectors;

class Music {
  int id;
  String genre;
  int playOfNumber;

  public Music(int id, String genre, int playOfNumber) {
    this.id = id;
    this.genre = genre;
    this.playOfNumber = playOfNumber;
  }
}

public class Lv03_베스트앨범 {

  public static int[] solution(String[] genres, int[] plays) {
    // 1. 장르별 총 재생 수, 음악 정보 저장
    Map<String, Integer> genreCounts = new HashMap<>();
    Map<String, PriorityQueue<Music>> genreMusics = new HashMap<>();

    for (int i = 0; i < genres.length; i++) {
      // 장르별 총 재생 수 기록
      genreCounts.put(genres[i], genreCounts.getOrDefault(genres[i], 0) + plays[i]);
      // 음악 객체 생성해서 우선 순위 큐에 넣어줌
      genreMusics.computeIfAbsent(genres[i],
                                  k -> new PriorityQueue<>((a, b) -> {
                                    if (b.playOfNumber != a.playOfNumber) {
                                      return b.playOfNumber - a.playOfNumber; // 재생 횟수 내림차순
                                    }
                                    return a.id - b.id; // ID 오름차순
                                  })).add(new Music(i, genres[i], plays[i]));
    }

    // 2. 장르별 총 재생 횟수를 기준으로 내림차순 정렬
    List<String> sortedGenres = genreCounts.entrySet().stream()
                                           .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                           .map(Map.Entry::getKey)
                                           .collect(Collectors.toList());

    // 3. 정답 배열에 각 장르의 상위 2개 음악 id 저장해서 리턴
    List<Integer> result = new ArrayList<>();
    for (String genre : sortedGenres) {
      PriorityQueue<Music> pq = genreMusics.get(genre);
      result.add(pq.poll().id); // 1번째 곡 추가
      if (!pq.isEmpty()) {
        result.add(pq.poll().id); // 2번째 곡 추가
      }
    }
    return result.stream().mapToInt(Integer::intValue).toArray();
    
    // 배열에 저장시 해당 장르가 없는 경우 0으로 채워져서 실패함
    /**
     * int[] answer = new int[sortedGenres.size() * 2];
     *     int index = 0;
     *     for (String genre : sortedGenres) {
     *       PriorityQueue<Music> pq = genreMusics.get(genre);
     *       answer[index++] = pq.poll().id;
     *       if (!pq.isEmpty()) {
     *         answer[index++] = pq.poll().id;
     *       }
     *     }
     *     return answer;
     */
  }

  public static void main(String[] args) {
    String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    int[] plays = {500, 600, 150, 800, 2500};
    int[] result = solution(genres, plays);
    System.out.println("result1 = " + Arrays.toString(result));
  }
}