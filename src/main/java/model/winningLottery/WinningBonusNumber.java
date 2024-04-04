package model.winningLottery;

import java.util.Objects;
import java.util.Set;

import model.Ball;
import model.random.LottoNumbers;

import static model.winningLottery.Ranking.*;

public class WinningBonusNumber {
    private final Ball bonusNumber;

    public WinningBonusNumber(Ball bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Ranking isMatch(LottoNumbers lottoNumbers) {

        if (lottoNumbers.getLottoNumbers().contains(bonusNumber)) {
            return SECOND;
        }

        return THIRD;
    }

    public Ball getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningBonusNumber that = (WinningBonusNumber) o;
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusNumber);
    }
}
