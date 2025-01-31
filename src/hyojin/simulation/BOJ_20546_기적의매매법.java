package hyojin.simulation;

import java.util.Scanner;

// https://www.acmicpc.net/problem/20546

// 100
// 10 20 23 34 55 30 22 19 12 45 23 44 34 38
// 15
// 20 20 33 98 15 6 4 1 1 1 2 3 6 14

public class BOJ_20546_기적의매매법 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int initialCash = sc.nextInt(); // 초기 현금
        int[] stockPrices = new int[14]; // 14일간 주가
        for (int i = 0; i < 14; i++) {
            stockPrices[i] = sc.nextInt();
        }

        int jAssets = calculateBNF(initialCash, stockPrices);
        int sAssets = calculateTimingStrategy(initialCash, stockPrices);

        if (jAssets > sAssets) {
            System.out.println("BNP");
        } else if (jAssets < sAssets) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }

    // BNP 전략: 전량 매수 후 최대 보유 전략
    private static int calculateBNF(int initialCash, int[] stockPrices) {
        int cash = initialCash;
        int stocks = 0;

        for (int price : stockPrices) {
            if (cash >= price) {
                stocks += cash / price;
                cash %= price;
            }
        }

        return calculateTotalAssets(cash, stocks, stockPrices[stockPrices.length - 1]);
    }

    // TIMING 전략: 3일 연속 상승/하강 시 매도/매수
    private static int calculateTimingStrategy(int initialCash, int[] stockPrices) {
        int cash = initialCash;
        int stocks = 0;
        int upCount = 0;
        int downCount = 0;

        for (int i = 1; i < stockPrices.length; i++) {
            int currentPrice = stockPrices[i];
            int previousPrice = stockPrices[i - 1];

            if (currentPrice > previousPrice) {
                upCount++;
                downCount = 0;
            } else if (currentPrice < previousPrice) {
                downCount++;
                upCount = 0;
            } else {
                upCount = 0;
                downCount = 0;
            }

            // 3일 연속 상승 시 전량 매도
            if (upCount == 3 && stocks > 0) {
                cash += stocks * currentPrice;
                stocks = 0;
            }
            // 3일 연속 하강 시 전량 매수
            if (downCount == 3 && cash >= currentPrice) {
                stocks += cash / currentPrice;
                cash %= currentPrice;
            }
        }

        return calculateTotalAssets(cash, stocks, stockPrices[stockPrices.length - 1]);
    }

    // 최종 자산 계산 (현금 + 보유 주식 가치)
    private static int calculateTotalAssets(int cash, int stocks, int finalPrice) {
        return cash + (stocks * finalPrice);
    }
}
