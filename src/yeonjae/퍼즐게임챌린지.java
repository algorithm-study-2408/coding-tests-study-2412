package yeonjae;

class 퍼즐게임챌린지 {
    //현재 level 값으로 가능한지 판단하는 함수
    static boolean isImpossible(int[] diffs, int[] times, long level, long limit){
        long t = (long)times[0];

        for(int i =1; i<times.length; i++){
            if(diffs[i] > level){
                t += ((long)diffs[i] - level) * ((long)times[i-1] + (long)times[i]);
            }
            t += (long)times[i];
        }

        return limit < t;
    }
    /*
    이진탐색을 활용, mid값을 level로 설정
    */
    public int solution(int[] diffs, int[] times, long limit) {
        //diff: 난이도, time_cur: 소요시간, time_prev: 이전 소요시간, level: 숙련도
        //diff<=level time_cur만큼 사용
        //diff>level diff-level번 틀림 틀릴때 마다 time_cur 사용, 추가로 time_prev 시간을 사용해 이전 퍼즐 풀고 와야함 이 때는 난이도 상관없이 틀리지 않음, diff - level번 틀린 후에 다시 풀면 time_cur 시간 사용하여 해결
        long left = 1;
        long right = limit;

        while(left < right){
            long mid = (left + right) / 2;

            if(isImpossible(diffs, times, mid, limit)){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return (int)left;
    }
}