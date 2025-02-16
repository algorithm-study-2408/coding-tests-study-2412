package seyoung._2502_7주차;

import java.util.Scanner;

public class 기적의_매매법 {

    public static void main(String[] args) {
        기적의_매매법 T = new 기적의_매매법();
        Scanner sc = new Scanner(System.in);
        int cash = Integer.parseInt(sc.nextLine());
        int[] arr = new int[14];
        String[] stocks = sc.nextLine().split(" ");
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(stocks[i]);
        }
        int[] junhyunResult = T.junhyun(cash, arr);
        int[] sungminResult = T.sungmin(cash, arr);

        int bnp = junhyunResult[0] + arr[arr.length - 1] + junhyunResult[1];
        int timing = sungminResult[0] + arr[arr.length - 1] + sungminResult[1];

        if(bnp > timing) {
            System.out.println("BNP");
        } else if(bnp < timing) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }

    }

    // 준현 BNP
    public int[] junhyun(int cash, int[] arr) {
        int money = cash; //남은 돈
        int stock = 0; //보유주식

        // 그리디
        for(int i = 0; i < arr.length; i++) {
            if ( money / arr[i] > 0 ) {
                int cnt = money / arr[i];
                stock += cnt;
                money -= arr[i] * cnt;
            }
        }
        int[] result = new int[2];
        result[0] = money;
        result[1] = stock;
        return result;
    }

    // 성민 TIMING
    public int[] sungmin(int cash, int[] arr) {
        int money = cash; //남은 돈
        int stock = 0; //보유주식

        for(int i = 0; i < arr.length - 3; i++) {
            // 3일 연속으로 상승하면 전부 판다
            if((arr[i] < arr[i+1]) && (arr[i+1] < arr[i+2])) {
                if(stock == 0) {
                    continue;
                }
                money += arr[i+3] * stock;
                stock = 0;
            // 3일 연속 하락하면 전부 산다
            } else if(arr[i] > arr[i+1] && arr[i+1] > arr[i+2]) {
                if ( money / arr[i+3] > 0) {
                    int cnt = money / arr[i+3];
                    stock += cnt;
                    money -= arr[i+3] * cnt;
                }
            }

        }
        int[] result = new int[2];
        result[0] = money;
        result[1] = stock;
        return result;
    }
}
