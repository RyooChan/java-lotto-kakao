package model.calculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Ball;
import model.random.LottoNumbers;
import model.winningLottery.WinMatch;
import model.winningLottery.Ranking;
import model.winningLottery.WinningBonusNumber;
import model.winningLottery.WinningNumbers;

import static java.util.Arrays.asList;
import static model.Ball.*;
import static model.winningLottery.Ranking.FIFTH;
import static model.winningLottery.Ranking.FIRST;
import static model.winningLottery.Ranking.FOURTH;
import static model.winningLottery.Ranking.NONE;
import static model.winningLottery.Ranking.SECOND;
import static model.winningLottery.Ranking.THIRD;
import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void 로또번호들을_저장한다() {
        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(Ball.createBallOrThrowException(7));
        WinMatch winMatch = new WinMatch(winningNumbers, bonusNumber);

        LottoNumbers lottoNumbers = new LottoNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        LottoResult lottoResult = LottoResult.createLottoResult(lottoNumbers, winMatch);

        int expectedFirstCount = 1;
        Calculator calculator = Calculator.createCalculator(List.of(lottoResult), winMatch);

        assertThat(calculator.getRankingCountMap().get(FIRST)).isEqualTo(expectedFirstCount);
    }

    @Test()
    void 각_등수를_가진_로또번호들의_갯수를구한다() {
        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(Ball.createBallOrThrowException(7));
        WinMatch winMatch = new WinMatch(winningNumbers, bonusNumber);

        LottoResult firstOne = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), winMatch);
        LottoResult firstTwo = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), winMatch);
        LottoResult secondOne = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7))), winMatch);

        Calculator calculator = Calculator.createCalculator(asList(firstOne, firstTwo, secondOne), winMatch);

        Map<Ranking, Integer> expectedEnumMap = new EnumMap<>(Ranking.class);
        expectedEnumMap.put(FIRST, 2);
        expectedEnumMap.put(SECOND, 1);
        expectedEnumMap.put(THIRD, 0);
        expectedEnumMap.put(FOURTH, 0);
        expectedEnumMap.put(FIFTH, 0);
        expectedEnumMap.put(NONE, 0);

        Map<Ranking, Integer> rankingCountMap = calculator.getRankingCountMap();

        assertThat(rankingCountMap).isEqualTo(expectedEnumMap);
    }

    @Test()
    void 각_등수를_통해_전체_수익률을_구한다() {
        WinningNumbers winningNumbers = new WinningNumbers(createBallSet(Set.of(1,2,3,4,5,6)));
        WinningBonusNumber bonusNumber = new WinningBonusNumber(Ball.createBallOrThrowException(7));
        WinMatch winMatch = new WinMatch(winningNumbers, bonusNumber);

        LottoResult firstOne = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), winMatch);
        LottoResult firstTwo = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 6))), winMatch);
        LottoResult secondOne = LottoResult.createLottoResult(new LottoNumbers(createBallSet(Set.of(1, 2, 3, 4, 5, 7))), winMatch);
        Calculator calculator = Calculator.createCalculator(asList(firstOne, firstTwo, secondOne), winMatch);

        double profitRate = calculator.calculateProfitRate();

        assertThat(profitRate).isEqualTo(1343333.33);
    }

}