package hyojin.test;

//https://school.programmers.co.kr/learn/courses/30/lessons/92335

public class K진수에서_소수점_구하기_정답 {

    public static int solution(int n, int k) {
        int answer = 0;
        String temp = "";

        // N진법 변환
        while (n != 0) {
            temp = n % k + temp;
            n /= k;
        }
        // 211020101011 -> 211 2 1(0)1 11
        String[] arr = temp.split("0");

        for (String data : arr) {
            if (data.equals("")) continue;
            long num = Long.parseLong(data);
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    //소수확인 메소드
    public static boolean isPrime(long a) {

        if (a < 2) return false;

        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int number = 437674;
        int binary = 3;

        int result = solution(number, binary);
        System.out.println("result = " + result);
    }
}
