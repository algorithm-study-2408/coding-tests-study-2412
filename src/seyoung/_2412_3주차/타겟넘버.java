package seyoung._2412_3주차;

public class 타겟넘버 {

    public static void main(String[] args) {
        타겟넘버 T = new 타겟넘버();
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(T.solution(numbers, target));
    }


    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    public int dfs(int[] numbers, int index, int currentSum, int target) {
        if (index == numbers.length) {
            // 현재 합계가 목표 값과 일치하는지 확인
            return currentSum == target ? 1 : 0;
        }

        // 현재 인덱스의 숫자를 더하거나 빼는 두 가지 경우의 결과를 재귀적으로 계산하고 합산
        return dfs(numbers, index + 1, currentSum + numbers[index], target)
                + dfs(numbers, index + 1, currentSum - numbers[index], target);
    }
}
