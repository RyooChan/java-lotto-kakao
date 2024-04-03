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

    public static WinningBonusNumber createWinningBonusNumber(WinningNumbers winningNumbers,
                                                              Ball winningBonusNumber) {
        validateWinningNumbers(winningNumbers, winningBonusNumber);
        return new WinningBonusNumber(winningBonusNumber);
    }

    private static void validateWinningNumbers(WinningNumbers winningNumbers,
                                               Ball winningBonusNumber) {
        validateIsNotWinningNumber(winningNumbers, winningBonusNumber);
    }

    private static void validateIsNotWinningNumber(WinningNumbers winningNumbers,
                                                   Ball winningBonusNumber) {
        Set<Ball> winningNumberSet = winningNumbers.getWinningNumbers();

        if (winningNumberSet.contains(winningBonusNumber)) {
            throw new IllegalArgumentException("지난주 당첨 번호와 보너스 번호는 달라야 합니다.");
        }
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
