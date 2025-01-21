package jaehoon._07주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20546
public class BOJ_20546_기적의_매매법 {

  // 입력예시:
  // 100
  // 10 20 23 34 55 30 22 19 12 45 23 44 34 38
  //
  // 출력예시:
  // BNP
  //
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int initialCash = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 준현이와 성민이의 초기 상태
    Investor bnp = new Investor(initialCash);
    Investor timing = new Investor(initialCash);

    int previousPrice = 0;
    int upCount = 0;
    int downCount = 0;

    while (st.hasMoreTokens()) {
      int currentPrice = Integer.parseInt(st.nextToken());

      // BNP 전략
      if (bnp.cash / currentPrice > 0) {
        bnp.buyAll(currentPrice);
      }

      // TIMING 전략
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

      if (upCount == 3) {
        timing.sellAll(currentPrice);
      } else if (downCount == 3) {
        timing.buyAll(currentPrice);
      }

      previousPrice = currentPrice;
    }

    // 마지막 날 자산 계산
    int finalPrice = previousPrice;
    int bnpAsset = bnp.getTotalAssets(finalPrice);
    int timingAsset = timing.getTotalAssets(finalPrice);

    if (bnpAsset > timingAsset) {
      System.out.println("BNP");
    } else if (bnpAsset < timingAsset) {
      System.out.println("TIMING");
    } else {
      System.out.println("SAMESAME");
    }

    br.close();
  }

  // 투자자 클래스
  private static class Investor {
    private int cash;
    private int stocks;

    public Investor(int initialCash) {
      this.cash = initialCash;
      this.stocks = 0;
    }

    private void buyAll(int price) {
      if (price > 0) {
        this.stocks += cash / price;
        this.cash %= price;
      }
    }

    private void sellAll(int price) {
      if (stocks > 0) {
        this.cash += stocks * price;
        this.stocks = 0;
      }
    }

    private int getTotalAssets(int currentPrice) {
      return cash + (stocks * currentPrice);
    }
  }
}