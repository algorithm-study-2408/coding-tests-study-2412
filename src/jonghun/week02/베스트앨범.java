package jonghun.week02;

import java.util.*;


/**
 * 1. 가변형 answer 리스트 생성<br/> 2. 장르별 재생 횟수 HashMap<br/> 3. 장르-노래별 번호 및 재생횟수 HashMap(이중해시뱀)<br/> 4.
 * 3번의 내부해시맵 value 내림차순 정렬<br/> 5. key추출 but 2개씩만<br/> 6. answer에 적재 but 장르별재생횟수에 따른 순서로<br/> (리스트
 * 없이 종류*2하면 primitive 배열 될수도 있지만 1개일때 고려)<br/> 7. primitive array 변환<br/>
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

        Map<String, Integer> num = new HashMap<>();
        Map<String, Map<Integer, Integer>> music = new HashMap<>();

        // 적재 로직
        for (int i = 0; i < plays.length; i++) {
            // genres와 plays의 길이가 같으니 맵을 하나씩 채워주자. 단, num에 키가 이미 존재하면 num에는 합산을 하고 music에는 추가적재 해야한다.
            if (!num.containsKey(genres[i])) {
                Map<Integer, Integer> temp = new HashMap<>();
                temp.put(i, plays[i]);
                music.put(genres[i], temp);
                num.put(genres[i], plays[i]);
            } else {
                // num에 키가 이미 존재할 때 처리
                music.get(genres[i]).put(i, plays[i]);
                num.put(genres[i], num.get(genres[i]) + plays[i]);
            }
        }

        // num 정렬 로직
        List<String> keySet = new ArrayList<>(num.keySet());
        keySet.sort((Comparator.comparingInt(num::get)).reversed());

        // music 해시맵 정렬 및 answer 적재 로직
        for (String key : keySet) {
            // temp 꺼내옴
            Map<Integer, Integer> temp = music.get(key);
            // index 저장되어있는 temp의 key들 나열함
            List<Integer> keys = new ArrayList<>(temp.keySet());
            // temp의 value대로 keys 정렬
            keys.sort((Comparator.comparingInt(temp::get).reversed()));

            // 내림차순 정렬된 상태에서 두개까지만 적재해야 되니까 여기서 get(0)하고 조건문에서 get(1)
            answer.add(keys.get(0));
            if(keys.size()>1){
                // 내림차순 정렬된 상태에서 두개까지만 적재해야 되니까 get(1)
                answer.add(keys.get(1));
            }

        }

        // primitive array로 변환 return
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
