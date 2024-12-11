package yeonjae.hash;

import com.sun.tools.javac.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class 베스트앨범 {
  public int[] solution(String[] genres, int[] plays) {
    Map<String, PriorityQueue<Album>> albumMap = new HashMap<>();
    Map<String, Integer> genrePlayCount = new HashMap<>();

    // Step 1: albumMap 생성 및 장르별 총 재생 횟수 계산
    for (int i = 0; i < genres.length; i++) {
      String genre = genres[i];
      Album album = new Album(i, plays[i]);

      albumMap.computeIfAbsent(genre,
          k -> new PriorityQueue<>(Comparator.comparingInt(Album::getPlayCount).reversed()));
      albumMap.get(genre).add(album);

      genrePlayCount.put(genre, genrePlayCount.getOrDefault(genre, 0) + plays[i]);
    }

    // Step 2: 장르별 총 재생 횟수를 기준으로 정렬
    List<String> sortedGenres = new ArrayList<>(genrePlayCount.keySet());
    sortedGenres.sort((g1, g2) -> genrePlayCount.get(g2) - genrePlayCount.get(g1));

    // Step 3: 정렬된 장르와 PriorityQueue에서 값 꺼내기
    List<Integer> result = new ArrayList<>();
    for (String genre : sortedGenres) {
      PriorityQueue<Album> albums = albumMap.get(genre);
      int count = 0;

      while (!albums.isEmpty() && count < 2) { // 각 장르에서 최대 2개의 곡만 선택
        result.add(albums.poll().getIndex());
        count++;
      }
    }

    // 결과 변환
    return result.stream().mapToInt(i -> i).toArray();
  }
  public static void main(String[] args) {
    베스트앨범 main = new 베스트앨범();
    String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    int[] plays = {500, 600, 150, 800, 2500};

    int[] result = main.solution(genres, plays);
    System.out.println(Arrays.toString(result)); // [4, 1, 3, 0]
  }
}

class Album {

  private final int index;
  private final int playCount;

  public Album(int index, int playCount) {
    this.index = index;
    this.playCount = playCount;
  }

  public int getIndex() {
    return index;
  }

  public int getPlayCount() {
    return playCount;
  }

}

