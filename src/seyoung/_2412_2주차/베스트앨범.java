package seyoung._2412_2주차;

import java.util.*;

public class 베스트앨범 {

    public static void main(String[] args) {
        베스트앨범 T = new 베스트앨범();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] solution = T.solution(genres, plays);
        for (int i : solution) {
            System.out.print(i + ",");
        }

    }


    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        // 플레이 수가 많은 장르 정렬을 위한 맵
        HashMap<String, Integer> genreSort = new HashMap<>();
        // 장르 별 앨범 리스트 저장을 위한 맵
        HashMap<String, List<Album>> playSort = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreSort.put(genres[i], genreSort.getOrDefault(genres[i], 0)+plays[i]);
            playSort.putIfAbsent(genres[i], new ArrayList<>());
            playSort.get(genres[i]).add(new Album(i, plays[i]));
        }
        //플레이 수가 가장 많은 장르 우선으로 정렬
        List<String> keySet = new ArrayList<>(genreSort.keySet());
        keySet.sort((o1, o2) -> genreSort.get(o2).compareTo(genreSort.get(o1)));

        // 장르 안에서 플레이 수로 정렬 / 각 장르에서 가장 많이 들은 곡 2곡 선택
        for (String genre : keySet) {
            List<Album> albums = playSort.get(genre);
            Collections.sort(albums);
            for (int i = 0; i < Math.min(2, albums.size()); i++) {
                answer.add(albums.get(i).idx);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class Album  implements Comparable<Album>{
        int idx;
        int play;

        public Album(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        @Override
        public int compareTo(Album o) {
            if(this.play == o.play) {
                return this.idx - o.idx;
            } else {
                return o.play - this.play;
            }
        }
    }

}
