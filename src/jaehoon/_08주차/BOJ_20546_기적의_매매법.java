package jaehoon._08주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20546
public class BOJ_20546_기적의_매매법 {

  // 기본 트레이딩 로직 (공통 부분 추상화)
  private static abstract class StockTrader {
    protected int stocks = 0;
    protected int cash;

    public StockTrader(int cash) {
      this.cash = cash;
    }

    protected void buyAll(int stockPrice) {
      if (cash >= stockPrice) {
        int buyCount = cash / stockPrice;
        stocks += buyCount;
        cash -= buyCount * stockPrice;
      }
    }

    protected void sellAll(int stockPrice) {
      cash += stocks * stockPrice;
      stocks = 0;
    }

    public int getProperty(int stockPrice) {
      return cash + (stocks * stockPrice);
    }

    abstract void trade(int stockPrice);
  }

  // BNP (Buy and Pray) 전략
  private static class BNPTrader extends StockTrader {
    public BNPTrader(int cash) {
      super(cash);
    }

    @Override
    public void trade(int stockPrice) {
      buyAll(stockPrice); // 항상 최대한 매수
    }
  }

  // TIMING (33 매매법) 전략
  private static class TimingTrader extends StockTrader {
    private int up = 0;
    private int down = 0;
    private int prevStockPrice = -1;

    public TimingTrader(int cash) {
      super(cash);
    }

    @Override
    public void trade(int stockPrice) {
      // 첫날이 아닐때만 가격 상승, 하락 기록
      if (prevStockPrice != -1) {
        if (stockPrice > prevStockPrice) {
          up++;
          down = 0;
        } else if (stockPrice < prevStockPrice) {
          down++;
          up = 0;
        } else {
          up = down = 0;
        }
      }
      prevStockPrice = stockPrice;

      if (up == 3) {
        sellAll(stockPrice);
      } else if (down == 3) {
        buyAll(stockPrice);
      }
    }
  }

  private static class StockMarket {
    private final StockTrader bnpTrader;
    private final StockTrader timingTrader;
    private final int[] stockPrices;

    public StockMarket(int cash, int[] stockPrices) {
      this.bnpTrader = new BNPTrader(cash);
      this.timingTrader = new TimingTrader(cash);
      this.stockPrices = stockPrices;
    }

    public void simulate() {
      for (int price : stockPrices) {
        bnpTrader.trade(price);
        timingTrader.trade(price);
      }

      int bnpValue = bnpTrader.getProperty(stockPrices[stockPrices.length - 1]);
      int timingValue = timingTrader.getProperty(stockPrices[stockPrices.length - 1]);

      if (bnpValue > timingValue) {
        System.out.println("BNP");
      } else if (bnpValue < timingValue) {
        System.out.println("TIMING");
      } else {
        System.out.println("SAMESAME");
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int cash = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] stockPrices = new int[st.countTokens()];
    for (int i = 0; i < stockPrices.length; i++) {
      stockPrices[i] = Integer.parseInt(st.nextToken());
    }
    br.close();

    StockMarket market = new StockMarket(cash, stockPrices);
    market.simulate();
  }
}