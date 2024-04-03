package model.calculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import model.random.LottoNumbers;
import model.winningLottery.Lottery;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;

public class LottoResult {
    private final LottoNumbers lottoNumbers;
    private Ranking ranking = Ranking.NONE;

    public Ranking getRanking() {
        return ranking;
    }

    public LottoResult(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoResult(LottoNumbers lottoNumbers, Ranking ranking) {
        this.lottoNumbers = lottoNumbers;
        this.ranking = ranking;
    }

    public void saveRanking(Lottery lottery) {
        this.ranking = lottery.rank(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoResult that = (LottoResult) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers) && ranking == that.ranking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers, ranking);
    }
}
