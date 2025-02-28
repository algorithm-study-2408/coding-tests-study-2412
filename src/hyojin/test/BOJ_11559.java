package hyojin.test;

import java.util.*;

/**
 * 슈도 코드
 * 1. 입력값 받기
 * 상하좌우 값 설정
 * 12*6 칸을 입력 받음
 *
 * 2. 반복문에서는 모든 문자를 확인하여 같은 문자들이 여러개 존재하는지 파악한다. (동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가됨)
 *   1) '.'이나 'x' 아닌 값을 만났을 때 상하좌우로 같은 문자열이 있는지 파악한다.
 *   2) 같은 값을 모두 찾았으면 4개 이상인지 확인한다.
 *   3) 참일 경우 연쇄될 문자는 'x'로 표시한다.
 *   4) 1번으로 돌아가서 조건에 부합한 그룹들이 있는지 확인한다.
 *   5) 모든 문자열을 확인했으면 그룹들을 제거하고 1연쇄를 추가한 뒤 중력을 적용한다.
 *   6) 중력 적용을 위해 6개 열을 기준으로 [확인해야하는 열][해당 행] 반복문을 돈다
 *      i) 해당 위치에 해당하는 값들은 stack에 저장하고 'x'값이 존재하면 stack에 저장하지 않는다. 마지막 열에 위치하면 역방향으로 값을 넣는다.
 *
 * 3. 최종 반복문이 끝났을 경우 결과값을 출력한다.
 */
public class BOJ_11559 {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static char[][] board = new char[12][6];
    private static boolean[][] visited = new boolean[12][6];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 12; i++) {
            board[i] = sc.nextLine().toCharArray();
        }

        int chainCount = 0;
        while (true) {
            // 방문 여부 초기화
            for (int i = 0; i < 12; i++) {
                Arrays.fill(visited[i], false);
            }

            // 터질 그룹이 있는지 확인하고, 터트리면 true 리턴
            if (!checkAndPop()) {
                break;
            }

            // 연쇄 수 증가
            chainCount++;

            // 중력 적용
            setGravity();
        }

        System.out.println(chainCount);
    }

    public static boolean checkAndPop() {
        boolean popOccurred = false;
        boolean[][] toPop = new boolean[12][6]; // 연쇄값 위치

        // 모든 칸을 돌면서 같은 색이 4개 이상인 그룹을 찾아 터뜨린다
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] != '.' && !visited[i][j]) {
                    char color = board[i][j];
                    // 문자 그룹 초기화
                    List<int[]> group = new ArrayList<>();
                    dfs(i, j, color, group);

                    // 4개 이상인 경우 터진다
                    if (group.size() >= 4) {
                        for (int[] pos : group) {
                            toPop[pos[0]][pos[1]] = true;
                        }
                        popOccurred = true;
                    }
                }
            }
        }

        // 터진 그룹을 'x'로 표시
        if (popOccurred) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (toPop[i][j]) {
                        board[i][j] = 'x';
                    }
                }
            }
        }

        return popOccurred;
    }

    public static void dfs(int x, int y, char color, List<int[]> group) {
        // 범위 체크
        if (x < 0 || x >= 12 || y < 0 || y >= 6) return;
        if (board[x][y] != color || visited[x][y]) return;

        // 방문 처리
        visited[x][y] = true;
        group.add(new int[] {x, y});

        // 상하좌우 DFS
        for (int i = 0; i < 4; i++) {
            dfs(x + dx[i], y + dy[i], color, group);
        }
    }

    public static void setGravity() {
        // 열을 중심으로 반복하기
        for (int j = 0; j < 6; j++) {
            Stack<Character> stack = new Stack<>();
            // 현재 열에 있는 뿌요들을 stack에 저장
            for (int i = 0; i < 12; i++) {
                // 'x'가 아닌 '.'과 color를 stack에 저장한다 -> '.'은 맨 위에 고정되어 있기 때문에
                if (board[i][j] != 'x') {
                    stack.push(board[i][j]);
                }
            }
            // stack에서 값을 빼서 아래로 내리기
            for (int i = 11; i >= 0; i--) {
                if (!stack.isEmpty()) {
                    board[i][j] = stack.pop();
                } else {
                    board[i][j] = '.';  // 빈 공간 채우기
                }
            }
        }
    }
}
