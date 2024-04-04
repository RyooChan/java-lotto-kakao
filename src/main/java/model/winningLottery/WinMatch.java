package model.winningLottery;

import java.util.Set;

import model.Ball;
import model.random.LottoNumbers;

import static model.winningLottery.Ranking.*;

public class WinMatch {
    private static final int NUMBER_COUNT = 6;

    private final WinningNumbers winningNumbers;
    private final WinningBonusNumber winningBonusNumber;

    public WinMatch(WinningNumbers winningNumbers, WinningBonusNumber winningBonusNumber) {
        validate(winningNumbers, winningBonusNumber);
        this.winningNumbers = winningNumbers;
        this.winningBonusNumber = winningBonusNumber;
    }

    public Ranking rank(LottoNumbers lottoNumbers) {
        int winningCount = winningNumbers.matchCount(lottoNumbers);

        if (winningCount == 6) {
            return FIRST;
        }

        if (winningCount == 5) {
            return winningBonusNumber.isMatch(lottoNumbers);
        }

        if (winningCount == 4) {
            return FOURTH;
        }

        if (winningCount == 3) {
            return FIFTH;
        }

        return NONE;
    }


    private static void validate(WinningNumbers winningNumbers, WinningBonusNumber winningBonusNumber) {
        validateUniqueNumberCount(winningNumbers);
        validateIsNotWinningNumber(winningNumbers, winningBonusNumber);
    }

    private static void validateUniqueNumberCount(WinningNumbers winningNumbers) {
        if (winningNumbers.getWinningNumbers().size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("서로 다른 %d개 숫자를 입력해주세요", NUMBER_COUNT));
        }
    }

    private static void validateIsNotWinningNumber(WinningNumbers winningNumbers,
                                                   WinningBonusNumber winningBonusNumber) {
        Set<Ball> winningNumberSet = winningNumbers.getWinningNumbers();

        if (winningNumberSet.contains(winningBonusNumber.getBonusNumber())) {
            throw new IllegalArgumentException("지난주 당첨 번호와 보너스 번호는 달라야 합니다.");
        }
    }
}
