package jonghun.week02;

import java.util.*;


/**
 * 1. 가변형 answer 리스트 생성<br/> 2. 장르별 재생 횟수 HashMap<br/> 3. 장르-노래별 번호 및 재생횟수 HashMap<`String,int[]><br/> 4.
 * 3번의 int[] value 내림차순 정렬<br/> 5. key추출 but 2개씩만<br/> 6. answer에 적재 but 장르별재생횟수에 따른 순서로<br/> (리스트
 * 없이 종류*2하면 primitive 배열 될수도 있지만 1개일때 고려)<br/> 7. primitive array 변환<br/>
 *
 */
public class 베스트앨범 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
            new String[]{"classic", "pop", "classic", "classic", "pop"},
            new int[]{500, 600, 150, 800, 2500}
        )));
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> genreSum = new HashMap<>();
        Map<String, List<int[]>> musics = new HashMap<>();  // [id, play count]

        // 적재 로직
        for (int i = 0; i < plays.length; i++) {
            // merge 메서드 굿..
            genreSum.merge(genres[i],plays[i],Integer::sum);
            // computeIfAbsent 활용. genres[i]가 존재하지 않으면 배열생성하고 add, 존재하면 그냥 add
            musics.computeIfAbsent(genres[i], key->new ArrayList<>()).add(new int[]{i,plays[i]});
        }

        // genreSum 내림차순 정렬 로직
        List<String> keySet = new ArrayList<>(genreSum.keySet());
        keySet.sort((a, b) -> genreSum.get(b) - genreSum.get(a));

        // 각 장르별로 노래 선택 2개
        for (String key : keySet) {
            // 노래 꺼내옴
            List<int[]> genreSongs = musics.get(key);
            // 재생 횟수로 내림차순 정렬
            genreSongs.sort((a, b) -> b[1] - a[1]);

            answer.add(genreSongs.get(0)[0]);  // 첫 번째 곡
            if(genreSongs.size() > 1) {        // 두 번째 곡이 있다면
                answer.add(genreSongs.get(1)[0]);
            }
        }
        // primitive array로 변환 return
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
