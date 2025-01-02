package seyoung._2412_5주차;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class 무인도여행 {

    static int[][] pos = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동서남북(y,x)
    static int n;
    static int m;
    static boolean[][] visited;
    static ArrayList<Integer> result;

    public ArrayList<Integer> solution(String[] maps) {
        result = new ArrayList<>();
        n = maps.length; //세로
        m = maps[0].length(); //가로
        visited = new boolean[n][m];

        // 맵 짜기
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X'){
                    result.add(bfs(j,i,maps)); // i=y, j=x
                }
            }
        }

        if(result.isEmpty()){
            result.add(-1);
        }else{
            Collections.sort(result);
        }
        return result;
    }

    private int bfs(int x, int y, String[] graph) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;

        int count = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] arr = queue.poll();
                int nowY = arr[0];
                int nowX = arr[1];
                count += Integer.parseInt(graph[nowY].substring(nowX,nowX+1));

                for(int[] p : pos){
                    int newY = nowY + p[0];
                    int newX = nowX + p[1];
                    if(newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newY][newX] && graph[newY].charAt(newX) != 'X'){
                        visited[newY][newX] = true;
                        queue.add(new int[]{newY, newX});

                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        무인도여행 T = new 무인도여행();
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        ArrayList<Integer> solution = T.solution(maps);
        for (Integer i : solution) {
            System.out.print(i + " ");
        }
    }

}
