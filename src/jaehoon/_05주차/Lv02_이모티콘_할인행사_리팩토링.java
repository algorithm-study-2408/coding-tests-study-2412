package jaehoon._05주차;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/150368
public class Lv02_이모티콘_할인행사_리팩토링 {

  public static void main(String[] args) {
    int[][] users1 = {{40, 10000}, {25, 10000}};
    int[] emoticons1 = {7000, 9000};
    System.out.println(Arrays.toString(solution(users1, emoticons1))); // [1, 5400]

    int[][] users2 = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
    int[] emoticons2 = {1300, 1500, 1600, 4900};
    System.out.println(Arrays.toString(solution(users2, emoticons2))); // [4, 13860]
  }

  private static final int[] DISCOUNT_RATES = {10, 20, 30, 40}; // 할인율 상수
  private static int maxSubscribers = 0; // 최대 가입자 수
  private static int maxProfit = 0;      // 최대 매출액

  public static int[] solution(int[][] users, int[] emoticons) {
    maxSubscribers = 0; // 초기화
    maxProfit = 0;      // 초기화
    int[] discounts = new int[emoticons.length]; // 이모티콘별 할인율 저장
    dfs(0, discounts, users, emoticons);  // 완전탐색 시작
    return new int[]{maxSubscribers, maxProfit}; // 결과 반환
  }

  private static void dfs(int depth, int[] discounts, int[][] users, int[] emoticons) {
    if (depth == emoticons.length) { // 모든 이모티콘에 대해 할인율을 정했을 때
      calculate(users, emoticons, discounts); // 결과 계산
      return;
    }

    // 모든 할인율 조합을 확인
    for (int discount : DISCOUNT_RATES) { // 할인율 선택
      discounts[depth] = discount; // 현재 이모티콘에 할인율 적용
      dfs(depth + 1, discounts, users, emoticons); // 다음 이모티콘 처리
    }
  }

  private static void calculate(int[][] users, int[] emoticons, int[] discounts) {
    int subscribers = 0; // 이번 할인 조합에서의 가입자 수
    int profit = 0;      // 이번 할인 조합에서의 매출

    for (int[] user : users) {
      int userTotalPrice = calculateUserTotalPrice(user, emoticons, discounts);
      if (userTotalPrice >= user[1]) { // 유저의 총 구매 금액이 최대 지출 가능 금액 이상이면 구독
        subscribers++;
      } else { // 그렇지 않으면 매출에 추가
        profit += userTotalPrice;
      }
    }
    updateMaxValues(subscribers, profit);
  }

  private static int calculateUserTotalPrice(int[] user, int[] emoticons, int[] discounts) {
    int requiredDiscount = user[0]; // 유저가 원하는 최소 할인율
    int totalPrice = 0;            // 유저의 총 구매 금액

    for (int i = 0; i < emoticons.length; i++) {
      if (discounts[i] >= requiredDiscount) { // 유저가 원하는 할인율 이상이라면 구매금액 계산
        totalPrice += emoticons[i] * (100 - discounts[i]) / 100;
      }
    }
    return totalPrice;
  }

  private static void updateMaxValues(int subscribers, int profit) {
    if (subscribers > maxSubscribers) { // 최대 가입자 수 갱신
      maxSubscribers = subscribers;
      maxProfit = profit;
    } else if (subscribers == maxSubscribers) { // 가입자 수 동일 시 최대 매출 갱신
      maxProfit = Math.max(maxProfit, profit);
    }
  }
}
