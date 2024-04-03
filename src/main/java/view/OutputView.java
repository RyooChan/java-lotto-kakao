package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.random.LottoNumbers;
import model.winningLottery.Ranking;

import static java.lang.String.valueOf;

public class OutputView {
    public static void printPurchaseCount(int count) {
        System.out.printf("%d 개를 구매했습니다.%n", count);
    }

    public static void printLottoNumberList(List<LottoNumbers> lottoNumberList) {
        lottoNumberList.forEach(lottoNumbers ->
        System.out.println("[" + lottoNumbers.getLottoNumbers().stream()
            .map(lottoNumber -> valueOf(lottoNumber.getBall()))
            .collect(Collectors.joining(",")) + "]"));
    }

    public static void printRankingResult(Map<Ranking, Integer> rankingCountMap) {
        System.out.println("\n당첨 통계\n---------");
        rankingCountMap.forEach(
            (key, value) -> System.out.printf("%d개 일치   (%d원)- %d개\n", key.getCondition(),
            key.getReward(), value));

    }

    public static void printProfitRateResult(double profitRate) {
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
    }
}
