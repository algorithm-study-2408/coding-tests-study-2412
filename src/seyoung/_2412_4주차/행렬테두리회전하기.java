package seyoung._2412_4주차;

import java.util.LinkedList;
import java.util.Queue;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        행렬테두리회전하기 T = new 행렬테두리회전하기();
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[] solution = T.solution(rows, columns, queries);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    static int [] answer;
    static int [][] map;
    static int [] dx = {0,1,0,-1};
    static int [] dy = {1,0,-1,0};
    static Queue<Integer> queue = new LinkedList<>();

    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];
        int cnt = 1;
        map = new int[rows][columns];
        
        // 행렬 생성
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = cnt++;
            }
        }

        for(int i = 0; i < queries.length; i++){
            rotateAndFindMin(queries[i], i);
        }
        return answer;
    }
    
    //한 쿼리에서 가장 작은 수 찾기 메서드
    public static void rotateAndFindMin(int[] query, int index){
        int x = query[0] - 1;
        int y = query[1] - 1;
        int sx = x;
        int sy = y;
        int ex = query[2];
        int ey = query[3];
        int min = map[x][y];

        queue.add(map[x][y]);

        for(int i = 0; i < 4; i++){
            while(true){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(sx <= nx && sy <= ny && nx < ex && ny < ey){
                    if(nx != sx || ny != sy){
                        queue.add(map[nx][ny]);
                        min = Math.min(min, map[nx][ny]);
                    }
                    x = nx; y = ny;
                }
                else break;
            }
        }
        x = sx; y = sy;
        for(int i = 0; i < 4; i++){
            while(!queue.isEmpty()){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(sx <= nx && sy <= ny && nx < ex && ny < ey){
                    map[nx][ny] = queue.poll();
                    x = nx; y = ny;
                }
                else break;
            }
        }
        answer[index] = min;
    }
}
