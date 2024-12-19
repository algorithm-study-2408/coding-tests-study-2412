package hyojin.hash;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42579

public class 베스트앨범 {
    public static void main(String[] args) {
        베스트앨범 solution = new 베스트앨범();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = solution.solution(genres, plays);
        // 결과 출력
        System.out.println(Arrays.toString(result)); // [4, 1, 3, 0]
    }
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genresPlayCount = new HashMap<>();
        HashMap<String, List<int[]>> genresSongs = new HashMap<>();
        // 장르별총재생횟수, 노래별재생횟수 저장
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int cnt = plays[i];
            genresPlayCount.merge(genre, cnt, Integer::sum);
            genresSongs.computeIfAbsent(genre, k -> new ArrayList<>()).add(new int[]{i, cnt});
        }
        // 총 재생 횟수 기준으로 내림차순 정렬
        List<String> sortedGenres = new ArrayList<>(genresPlayCount.keySet());
        sortedGenres.sort((g1, g2) -> genresPlayCount.get(g2) - genresPlayCount.get(g1));
        // 각 장르별로 노래를 재생 횟수 내림차순으로 정렬하고 두 개씩 선택
        List<Integer> bestAlbum = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<int[]> songs = genresSongs.get(genre);
            songs.sort((g1, g2) -> {
                if (g1[1] == g2[1]) {
                    return g1[0] - g2[0];
                }
                return g2[1] - g1[1];
            });
            int cnt = Math.min(songs.size(), 2);
            for (int i = 0; i < cnt; i++) {
                bestAlbum.add(songs.get(i)[0]);
            }
        }
        return bestAlbum.stream().mapToInt(Integer::intValue).toArray();
    }
}
/**
 * # 장르별, 노래별 변수 선언
 * 장르별총재생횟수
 * 노래별재생횟수
 * <p>
 * # 장르별, 노래별 정보 저장
 * 각 장르 i에 대해:
 * 장르 = 장르들[i]
 * 재생횟수 = 재생횟수들[i]
 * 장르별재생횟수[장르] += 재생횟수
 * 장르별노래들[장르]에 (노래 고유 번호, 재생횟수) 추가
 * <p>
 * # 장르별 총 재생 횟수 기준으로 장르 정렬
 * 정렬된장르들 = 장르별재생횟수 값을 기준으로 내림차순 정렬
 * <p>
 * # 각 장르별로 노래 정렬 후 두 곡을 선택
 * 결과 = 빈 리스트 생성
 * 각 장르에 대해:
 * # 장르 내에서 재생횟수 내림차순, 재생횟수가 같으면 고유 번호 오름차순으로 정렬
 * 정렬된노래들 = 장르별노래들[장르]를 재생횟수 내림차순, 고유 번호 오름차순으로 정렬
 * <p>
 * # 최대 두 곡 선택
 * 두곡선택 = 2와 정렬된노래들 길이 중 작은 값 만큼 반복:
 * 결과에 정렬된노래들[i]의 노래 고유 번호 추가
 * 결과 반환
 */