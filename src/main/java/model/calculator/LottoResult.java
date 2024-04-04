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
    private final Ranking ranking;

//    private LottoResult(LottoNumbers lottoNumbers) {
//        this.lottoNumbers = lottoNumbers;
//    }

    private LottoResult(LottoNumbers lottoNumbers, Ranking ranking) {
        this.lottoNumbers = lottoNumbers;
        this.ranking = ranking;
    }

    public static LottoResult createLottoResult(LottoNumbers lottoNumbers, Lottery lottery) {
        Ranking rank = lottery.rank(lottoNumbers);
        return new LottoResult(lottoNumbers, rank);
    }

//    public void saveRanking(Lottery lottery) {
//        this.ranking = lottery.rank(lottoNumbers);
//    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public Ranking getRanking() {
        return ranking;
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
