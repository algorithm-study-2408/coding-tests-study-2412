package jaehoon._04주차;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/49994
public class Lv02_방문길이 {

  public static void main(String[] args) {
    String dirs1 = "ULURRDLLU";
    System.out.println("result1 = " + solution(dirs1));

    String dirs2 = "LULLLLLLU";
    System.out.println("result2 = " + solution(dirs2));
  }

  // 1. 이동을 위한 해시맵 초기화
  static Map<Character, int[]> moveMap = Map.of(
      'U', new int[] {0, 1},
      'D', new int[] {0, -1},
      'R', new int[] {1, 0},
      'L', new int[] {-1, 0}
  );

  public static int solution(String dirs) {
    // 2. 방문경로를 저장할 배열 생성
    Set<String> pathSet = new HashSet<>();

    // 3. 현재 위치 초기화
    int x = 5;
    int y = 5;

    // 4. 캐릭터 이동
    for (Character ch : dirs.toCharArray()) {
      int[] offset = moveMap.get(ch);
      int nx = x + offset[0];
      int ny = y + offset[1];

      // 4-1. 경계값 무시
      if (ny < 0 || ny >= 11 || nx < 0 || nx >= 11) {
        continue;
      }

      // 4-2. 이동 경로를 저장
      pathSet.add(x + " " + y + " " + nx + " " + ny);
      pathSet.add(nx + " " + ny + " " + x + " " + y);

      // 4-3. 좌표 이동
      x = nx;
      y = ny;
    }
    System.out.println("pathSet = " + pathSet);
    return pathSet.size() / 2;
  }
}
