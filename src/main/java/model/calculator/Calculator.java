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
    private final Map<Ranking, Integer> rankingCounts = initMap();

    private final List<LottoResult> lottoResults;

    private Calculator(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
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
        lottoResults.forEach(
            lottoResult -> {
                Ranking ranking = lottoResult.getRanking();
                rankingCounts.put(ranking, rankingCounts.get(ranking) + 1);
            }
        );
    }

    private Map<Ranking, Integer> initMap() {
        Map<Ranking, Integer> rankingCounts = new EnumMap<>(Ranking.class);
        asList(Ranking.values()).forEach(
            ranking -> rankingCounts.put(ranking, 0)
        );
        return rankingCounts;
    }

    public double calculateProfitRate() {
        return divideAndRound((double) totalSum() / purchaseAmount());
    }

    private long totalSum() {
        return rankingCounts.entrySet()
            .stream()
            .mapToLong(entry -> (long) entry.getKey().getReward() * entry.getValue())
            .sum();
    }

    private long purchaseAmount() {
        return rankingCounts.values()
            .stream()
            .mapToInt(count -> LOTTO_UNIT_PRICE * count)
            .sum();
    }

    private double divideAndRound(double num) {
        return Math.floor(num * 100) / 100;
    }

    public Map<Ranking, Integer> getRankingCounts() {
        return rankingCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calculator that = (Calculator) o;
        return Objects.equals(lottoResults, that.lottoResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoResults);
    }
}
