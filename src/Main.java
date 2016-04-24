import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Map<String, Long> resultMap = new HashMap<>();
        while (T-- > 0 && T >= 0 && T <= 100) {
            List<Integer> coins;
            long result = 0;
            long M = sc.nextInt();
            long C = sc.nextInt();
            coins = countCoins(C, sc);
            if (M > 5000 || M < 0) {
                continue;
            } else if (C > 100 || C < 0) {
                continue;
            }
            //            long start = System.currentTimeMillis();
            result = calculate(coins, C, M, resultMap);
            //            long end = System.currentTimeMillis();
            System.out.println(result % 1000000007);
            //            System.out.println(end - start);
        }
    }

    private static long calculate(List<Integer> coins, long coinCount, long price, Map<String, Long> result) {
        long lastCoin = coins.get((int) (coinCount - 1));
        long loopCount = price / lastCoin;
        String key = makeKey(lastCoin, price, coins);

        Long value = result.get(key);
        long count = 0;
        if (value != null) {
            return value;
        } else if (coinCount == 1) {
            value = 1L;
            result.put(key, value);
            return value;
        }

        for (int i = 0; i <= loopCount; i++) {
            count += calculate(coins.subList(0, (int) coinCount - 1), coinCount -1, price - (lastCoin * i), result);
        }
        result.put(key, count);
        return count;
    }

    private static String makeKey(long lastCoin, long price, List<Integer> coins) {
        String key = "";
        key += String.valueOf(lastCoin) + "_" + String.valueOf(price) + "_" + coins.size();
        return key;
    }

    private static List<Integer> countCoins(long coinCases, Scanner sc) {
        List<Integer> coins = new ArrayList<>();
        for (int count = 0 ; count < coinCases ; count++) {
            coins.add(sc.nextInt());
        }
        return coins;
    }
}
