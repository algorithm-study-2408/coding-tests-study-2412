package eunseo.week2;

import java.util.*;

public class BestAlbum_42579 {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = solution(genres, plays);
        System.out.println("result = " + Arrays.toString(result));
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<Integer>();
        // 1. 장르별 재생 횟수(group by)
        HashMap<String, Integer> genreHash = new HashMap<String, Integer>();
        for (int i = 0; i < genres.length; i++) {
            genreHash.put(genres[i], genreHash.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 2. 정렬(sort by)
        List<String> keys = new ArrayList<>(genreHash.keySet());
        Collections.sort(keys, (v1, v2) -> (genreHash.get(v2).compareTo(genreHash.get(v1))));

        // 3. 정렬한 장르에서 plays 크기 순
        for (String g : keys) {
            HashMap<Integer, Integer> playHash = new HashMap<Integer, Integer>();
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(g)) {
                    playHash.put(i, plays[i]);
                }
            }
            List<Integer> playKey = new ArrayList<>(playHash.keySet());
            Collections.sort(playKey, (v1, v2) -> (playHash.get(v2).compareTo(playHash.get(v1))));
            int two = 0;

            // 2번 노래는 수록되지 않음 처리
            for (int i : playKey) {
                answer.add(i);
                two++;
                if (two >= 2) {
                    break;
                }
            }

        }
        
        // List에서 int[]로 변경
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
