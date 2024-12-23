package hyojin.test;

//https://school.programmers.co.kr/learn/courses/30/lessons/92335

public class K진수에서_소수점_구하기_오답 {

    public static Integer solution(int n, int binary) {
        // K진수로 변환하는 메소드
        String number = toBinary(n, binary);
        // 중복 소수 포함 -> int 사용
        int count = 0;  // 소수 개수를 셀 변수
        // 중복 소수 제거 -> set 사용
//        HashSet<Integer> primeNumbers = new HashSet<>();

        // K진수에서 연속된 숫자들을 추출하여 소수인지 판별
        for (int i = 0; i < number.length(); i++) {
            for (int j = i + 1; j <= number.length(); j++) {
                String subStr = number.substring(i, j);
                if (subStr.contains("0")) {
                    continue;  // 0이 포함된 부분은 소수가 아님
                }
                int num = Integer.parseInt(subStr, binary);  // k진수로 해석하여 10진수로 변환
                if (isPrime(num)) {
                    count++;
//                    primeNumbers.add(num);
                }
            }
        }

        return count;
//        return primeNumbers.size();
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        // 숫자의 제곱근까지만 나누어봐도 소수를 판별할 수 있다
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String toBinary(int n, int k) {
        if (n == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int number = 437674;
        int binary = 3;

        int result = solution(number, binary);
        System.out.println("result = " + result);
    }
}
