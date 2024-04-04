package model.calculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.winningLottery.WinMatch;
import model.winningLottery.Ranking;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static model.Amount.LOTTO_UNIT_PRICE;

public class Calculator {
    private final Map<Ranking, Integer> rankingCountMap = initMap();

    private final List<LottoResult> lottoResultList;

    private Calculator(List<LottoResult> lottoResultList) {
        this.lottoResultList = lottoResultList;
        calculateRankingCount();
    }

    public static Calculator createCalculator(List<LottoResult> lottoResultList, WinMatch winMatch) {
        lottoResultList = lottoResultList
            .stream().map(
                lottoResult -> LottoResult.createLottoResult(lottoResult.getLottoNumbers(), winMatch)
            ).collect(toList());

        return new Calculator(lottoResultList);
    }

    private void calculateRankingCount() {
        lottoResultList.forEach(
            lottoResult -> {
                Ranking ranking = lottoResult.getRanking();
                rankingCountMap.put(ranking, rankingCountMap.get(ranking) + 1);
            }
        );
    }

    private Map<Ranking, Integer> initMap() {
        Map<Ranking, Integer> rankingCountMap = new EnumMap<>(Ranking.class);
        asList(Ranking.values()).forEach(
            ranking -> rankingCountMap.put(ranking, 0)
        );
        return rankingCountMap;
    }

    public double calculateProfitRate() {
        return divideAndRound((double) totalSum() / purchaseAmount());
    }

    private long totalSum() {
        return rankingCountMap.entrySet()
            .stream()
            .mapToLong(entry -> (long) entry.getKey().getReward() * entry.getValue())
            .sum();
    }

    private long purchaseAmount() {
        return rankingCountMap.values()
            .stream()
            .mapToInt(count -> LOTTO_UNIT_PRICE * count)
            .sum();
    }

    private double divideAndRound(double num) {
        return Math.floor(num * 100) / 100;
    }

    public Map<Ranking, Integer> getRankingCountMap() {
        return rankingCountMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Objects.equals(lottoResultList, that.lottoResultList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResultList);
    }
}
